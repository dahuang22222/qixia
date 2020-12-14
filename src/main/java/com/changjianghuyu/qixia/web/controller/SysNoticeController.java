package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.SysNotice;
import com.changjianghuyu.qixia.web.service.SysNoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 系统公告表(SysNotice)表控制层
 *
 * @author huangkewang
 * @since 2020-11-10 18:34:19
 */
@RestController
@RequestMapping("/sysnotice")
public class SysNoticeController {
    /**
     * 服务对象
     */
    @Resource
    private SysNoticeService sysNoticeService;

    /**
     * 4.1通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getSysNoticeById")
    public MsgHander getSysNoticeById(Long id) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        msg.setContext(sysNoticeService.queryById(id));
        return msg;
    }

    /**
     * 4.2小程序公告列表
     * @param map
     * @return
     */
    @GetMapping("/userGetNoticeListByPage")
    public MsgHander userGetNoticeListByPage(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(sysNoticeService.userGetNoticeListByPage(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }


    /**
     * 4.3 分页获取系统公告列表
     * @param map
     * @return
     */
    @GetMapping("/getNoticeListByPage")
    public MsgHander getNoticeListByPage(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(sysNoticeService.getNoticeListByPage(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 4.4新建公告
     * @param sysNotice
     * @return
     */
    @PostMapping("/insertSysNotice")
    public MsgHander insertSysNotice(@RequestBody SysNotice sysNotice) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        SysNotice insert = sysNoticeService.insert(sysNotice);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 4.5 更新公告信息
     * @param sysNotice
     * @return
     */
    @PostMapping("/updateSysNotice")
    public MsgHander updateSysNotice(@RequestBody SysNotice sysNotice) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        SysNotice update = sysNoticeService.update(sysNotice);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 4.6通过id删除
     * @param sysNotice
     * @return
     */
    @PostMapping("/deleteSysNoticeById")
    public MsgHander deleteSysNoticeById(@RequestBody SysNotice sysNotice) {
        Long id = sysNotice.getId();
        MsgHander msg = new MsgHander();
        boolean deleteResult = sysNoticeService.deleteById(id);
        msg.setContext(deleteResult);
        if(deleteResult){
            msg.setMessage("获取成功");
            msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        }else {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 4.7批量删除
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String,String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.sysNoticeService.deleteByIds(Ids));
    }

}