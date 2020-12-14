package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping
public class multipartController {
    @Value("${MultipartFile.imagePath}")
    String imagePath;

    @Value("${MultipartFile.imageRequestPath}")
    String imageRequestPath;

    /**
     * 有file文件时
     * @param multipartFile 封装了需要传递过来的参数
     */
    @PostMapping("/imageUpload")
    @ResponseBody
    public MsgHander imageUpload(@RequestParam("file") MultipartFile multipartFile)  {
        MsgHander msg = new MsgHander();
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        String fileSavePath=imagePath;
        if (null == multipartFile || multipartFile.getSize() <= 0) {
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
            msg.setMessage("请选择上传文件");
        }
        //文件名
        String originalName = multipartFile.getOriginalFilename();
        String fileName= UUID.randomUUID().toString().replace("-", "");
        String picNewName = fileName + originalName.substring(originalName.lastIndexOf("."));
        String imgRealPath = fileSavePath + picNewName;
        try {
            //保存图片-将multipartFile对象装入image文件中
            File imageFile=new File(imgRealPath);
            multipartFile.transferTo(imageFile);
            msg.setContext(imageRequestPath+picNewName);
        } catch (Exception e) {
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
            msg.setMessage("图片存储异常");
        }
        return msg;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(@RequestParam Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String time = String.valueOf(LocalDate.now().getYear()) + LocalDate.now().getMonth() + LocalDate.now().getDayOfMonth()+"";
        String path = imagePath;
        String docx = StringUtils.substringAfterLast(path, ".");
        String fileName = time+"."+docx; // 文件的默认保存名
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取图片
     */
    @RequestMapping(value = "/iomoreimgcom", produces = {
            "application/json;charset=UTF-8" }, method = RequestMethod.GET)
    @ResponseBody
    public synchronized void iomoreimgcom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getParameter("url");
        File file = new File(url);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        response.setHeader("Content-Type", "image/jpeg");
        byte b[] = new byte[1024];
        int read;
        try {
            while ((read = bis.read(b)) != -1) {
                bos.write(b, 0, read);
            }
            //request.getRequestDispatcher("/components/hazard/yscchird.html").forward(request, response);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }

    /**
     * 各种文件上传与判断
     * types 文件类型(1图片 2视频 3文件）
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploadFile")
    @ResponseBody
    public Object uploadFile(@RequestPart("file") MultipartFile file, Integer types) throws FileNotFoundException {
//        if (ToolUtil.isOneEmpty(file, types)) {
//            return "500";
//        }
        String name = file.getOriginalFilename();
        String[] fileNames = name.split("\\.");
        String suffix = fileNames[fileNames.length - 1];

        Boolean is = false;
        if (1 == types) {
            is = validateImager(suffix);
        } else if (2 == types) {
            is = validateVideo(suffix);
        } else if (3 == types) {
            is = validateFile(suffix);
        }
        if (!is) {
            return  "上传文件格式错误!";
        }

        String fileName = UUID.randomUUID().toString() + "." + suffix;
        try {
            String fileSavePath = imagePath;
            file.transferTo(new File(fileSavePath + fileName));
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("url", imagePath + fileName);
        data.put("fileName", fileName);
        return data;
    }

    //验证上传文件（图片）
    public Boolean validateImager(String suffix) {
        List<String> suffixList = Arrays.asList("jpg", "png", "gif", "jpeg", "bmp");
        //判断后缀格式是否正确
        if (suffixList.contains(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    //验证上传文件（视频）
    public Boolean validateVideo(String suffix) {
        List<String> suffixList = Arrays.asList("avi", "wmv", "mpeg", "mp4", "mov", "mkv", "flv", "f4v", "m4v", "rmvb", "rm",
                "3gp", "dat", "ts", "mts", "vob");
        //判断后缀格式是否正确
        if (suffixList.contains(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    //验证上传文件（文件）
    public Boolean validateFile(String suffix) {
        List<String> suffixList = Arrays.asList("css", "js", "txt");
        //判断后缀格式是否正确
        if (suffixList.contains(suffix)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 读取文件内容
     */
    @RequestMapping("/{fileId:.+}")
    public void renderPicture(@PathVariable("fileId") String fileId, HttpServletResponse response) {
        String path = imagePath + fileId;
        try {
            byte[] bytes = path.getBytes();
            response.getOutputStream().write(bytes);//有值返回参数
        } catch (Exception e) {
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/imager/0.jsp");//文件不存在默认加载文件
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 文件下载
     * @param id
     * @param response
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/down")
    @ResponseBody
    public void down(@RequestParam String id, HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        // 下载本地文件
        String fileName = new String("文件名.docx"); // 文件的默认保存名
        // 读到流中
        InputStream inStream =this.getClass().getResourceAsStream("/static/template/文件名.docx"); // 文件的存放路径
        // 设置输出的格式
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
        // 循环取出流中application/octet-stream的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
