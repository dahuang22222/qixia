package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.BaseController;
import com.changjianghuyu.qixia.web.common.jwt.UserInfo;
import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUserArea;
import com.changjianghuyu.qixia.web.service.HyUserAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户角色地区表(HyUserArea)表控制层
 *
 * @author huangkewang
 * @since 2020-11-11 16:24:57
 */
@RestController
@RequestMapping("/hyUserArea")
public class HyUserAreaController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private HyUserAreaService hyUserAreaService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HyUserArea selectOne(Long id) {
        return this.hyUserAreaService.queryById(id);
    }

    /**
     * 分页获取信息反馈列表
     * @param hyUserArea
     * @return
     */
    @GetMapping("/getHyUserAreaList")
    public MsgHander getHyUserAreaList(HyUserArea hyUserArea) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        UserInfo userInfo = getUserInfo();
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyUserAreaService.queryHyUserAreaList(hyUserArea));
        } catch (Exception e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    @PostMapping("/insertHyUserArea")
    public MsgHander insertHyUserArea(@RequestBody HyUserArea hyUserArea) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyUserArea insert = hyUserAreaService.insert(hyUserArea);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    @PostMapping("/updateHyUserArea")
    public MsgHander updateHyUserArea(@RequestBody HyUserArea hyUserArea) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyUserArea update = hyUserAreaService.update(hyUserArea);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     *
     * @param hyUserArea
     * @return
     */
    @PostMapping("/deleteHyUserAreaById")
    public MsgHander deleteHyUserAreaById(@RequestBody HyUserArea hyUserArea) {
        Long id = hyUserArea.getId();
        MsgHander msg = new MsgHander();
        boolean deleteResult = hyUserAreaService.deleteById(id);
        msg.setContext(deleteResult);
        if(deleteResult){
            msg.setMessage("删除成功");
            msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        }else {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }



}