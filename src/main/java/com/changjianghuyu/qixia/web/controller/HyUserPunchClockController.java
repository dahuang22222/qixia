package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUserPunchClock;
import com.changjianghuyu.qixia.web.service.HyUserPunchClockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 用户打卡统计表(HyUserPunchClock)表控制层
 *
 * @author makejava
 * @since 2020-11-26 10:37:36
 */
@RestController
@RequestMapping("/hyUserPunchClock")
public class HyUserPunchClockController {
    /**
     * 服务对象
     */
    @Resource
    private HyUserPunchClockService hyUserPunchClockService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public HyUserPunchClock selectOne(Long id) {
        return this.hyUserPunchClockService.queryById(id);
    }


    /**
     * 分页获取统计列表
     * @param map
     * @return
     */
    @GetMapping("/getUserPunchClockList")
    public MsgHander getUserPunchClockList(@RequestParam Map<String,String> map){
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyUserPunchClockService.getUserPunchClockList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 用户获取自己的打卡统计
     * @param hyUserPunchClock
     * @return
     */
    @GetMapping("/getMyUserPunchClockList")
    public MsgHander getMyUserPunchClockList(HyUserPunchClock hyUserPunchClock){
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyUserPunchClockService.queryAll(hyUserPunchClock));
        } catch (Exception e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }
}