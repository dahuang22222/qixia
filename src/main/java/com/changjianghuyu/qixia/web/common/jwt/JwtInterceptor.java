package com.changjianghuyu.qixia.web.common.jwt;

import com.alibaba.fastjson.JSON;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.service.HyUserService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private HyUserService hyUserService;

    public static final String USER_INFO_KEY = "user_info_key";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        //放开静态网页
//        if(request.getRequestURI().indexOf("drainage") != -1 || request.getRequestURI().indexOf("tfbaoGL") != -1 || request.getRequestURI().indexOf("tfbaoYD") != -1 || request.getRequestURI().indexOf("tfbaoFD") != -1 || request.getRequestURI().indexOf("tfbaoPC") != -1 ){
//            return true;
//        }
        //放开图片
//        if(request.getRequestURI().endsWith("jpg") || request.getRequestURI().endsWith("jpeg") || request.getRequestURI().endsWith("png") || request.getRequestURI().endsWith("ico") || request.getRequestURI().endsWith("pdf")){
//            return true;
//        }
        //放开获取地理位置接口  and imageUpload
        if(request.getRequestURI().indexOf("/sysArea/getAreaList") != -1  || request.getRequestURI().indexOf("/imageUpload") != -1 || request.getRequestURI().indexOf("/image") != -1){
            return true;
        }
        if(request.getRequestURI().indexOf("/page") != -1 || (request.getRequestURI().indexOf("/guanli") != -1)){
            return true;
        }
        if(request.getRequestURI().endsWith("css") || request.getRequestURI().endsWith("js") || request.getRequestURI().endsWith("html") || request.getRequestURI().endsWith("ico") || request.getRequestURI().endsWith("pdf")){
            return true;
        }
        //  获取用户 token
        String token = request.getHeader(JwtUtils.getHeaderKey());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(JwtUtils.getHeaderKey());
        }
        //时间安全性校验
      /*  if (!StringUtils.isBlank(request.getHeader("soleTime"))) {
            //String soleTime1 = request.getHeader("soleTime");
            try {
                //String stringSoleTime = SM2Util.decryptBySM2(request.getHeader("soleTime"), "30150ada2056b7ea103ebecd02b9fbac8d0e273d60dbdc7d2664c70a799a89e3");
                long soleTime = Long.valueOf(request.getHeader("soleTime"));
                //现在时间
                long now = System.currentTimeMillis();
                long start = now - 60000;
                long end = now + 60000;
                if (start > soleTime || end < soleTime) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }*/
        //  token为空
        if (StringUtils.isBlank(token)) {
            this.writerErrorMsg("501",
                    JwtUtils.getHeaderKey() + " can not be blank",
                    response);
            return false;
        }
        //token前端对token进行30秒验证，如果token超过30秒做失效处理
//        try {
//            if (!StringUtils.isBlank(token)) {
//                String time = token.substring(token.length() - 13, token.length());
//                long soleTime = Long.valueOf(time);
//                //现在时间
//                long now = System.currentTimeMillis();
//                long start = now - 600000;
//                long end = now + 600000;
//                if (start > soleTime || end < soleTime) {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            return false;
//        }

        //  校验并解析token，如果token过期或者篡改，则会返回null
        Claims claims = JwtUtils.verifyAndGetClaimsByToken(token);
        if (null == claims) {
            this.writerErrorMsg("501",
                    JwtUtils.getHeaderKey() + "失效，请重新登录",
                    response);
            return false;
        }else{
            Long id = Long.valueOf((Integer)claims.get("id"));
            HyUser hyUser = hyUserService.queryById(id);
            if(null == hyUser){
                this.writerErrorMsg("501",
                         "用户已经删除！",
                        response);
                return false;
            }
        }
        /*//token失效，用户无法访问
        String phone = (String)claims.get("phone");
        if(RedisConfig.redisTemplate().get(phone) != null && "1".equals(String.valueOf(RedisConfig.redisTemplate().get(phone)))) {
            //token延时
            RedisConfig.redisTemplate().setex(phone,1800, "1");
        }else{
            //token失效
            return  false;
        }*/
        //  校验通过后，设置用户信息到request里，在Controller中从Request域中获取用户信息
        request.setAttribute(USER_INFO_KEY, claims);
        return true;
    }

    /**
     * 利用response直接输出错误信息
     *
     * @param code
     * @param msg
     * @param response
     * @throws IOException
     */
    private void writerErrorMsg(String code, String msg, HttpServletResponse response) throws IOException {
        MsgHander msgHander = new MsgHander();
        msgHander.setMessage(msg);
        msgHander.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(msgHander));
    }
}