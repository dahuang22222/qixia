package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.entity.HyPunchClock;
import com.changjianghuyu.qixia.web.entity.HyPunchClockExpand;
import com.changjianghuyu.qixia.web.service.HyPunchClockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 用户打卡表(HyPunchClock)表控制层
 *
 * @author huangkewang
 * @since 2020-11-11 16:24:41
 */
@RestController
@RequestMapping("/hyPunchClock")
public class HyPunchClockController {
    /**
     * 服务对象
     */
    @Resource
    private HyPunchClockService hyPunchClockService;

    /**
     * 7.1用户打卡
     * @param hyPunchClock
     * @return
     */
    @PostMapping("/punchClock")
    public MsgHander punchClock(@RequestBody HyPunchClock hyPunchClock) {
        MsgHander msg = new MsgHander();
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            Map<String, Object> result =  hyPunchClockService.punchClock(hyPunchClock);
            msg.setMessage(String.valueOf(result.get("msg")));
            if(null != result.get("status") && "1".equals(String.valueOf(result.get("status")))){}else{
                msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
            }
        } catch (Exception e) {
            msg.setMessage("该区域已打卡成功，请勿重复打卡！");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 7.2获取打卡记录列表
     * @param userId
     * @return
     */
    @GetMapping("/getTodayHyPunchClockList")
    public MsgHander getTodayHyPunchClockList(@RequestParam Long userId) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            HyPunchClockExpand hyPunchClock = new HyPunchClockExpand();
            hyPunchClock.setUserId(userId);
            msg.setContext(hyPunchClockService.getTodayHyPunchClockList(hyPunchClock));
        } catch (Exception e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 7.3通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.hyPunchClockService.queryById(id));
    }

    /**
     * 分页获取列表
     * @param map
     * @return
     */
    @GetMapping("/getHyPunchClockList")
    public MsgHander getHyPunchClockList(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyPunchClockService.getHyPunchClockList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 用户打卡
     * @param hyPunchClock
     * @return
     */
    @PostMapping("/insertHyPunchClock")
    public MsgHander insertHyPunchClock(@RequestBody HyPunchClock hyPunchClock) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClock insert = hyPunchClockService.insert(hyPunchClock);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    @PostMapping("/updateHyPunchClock")
    public MsgHander updateHyPunchClock(@RequestBody HyPunchClock hyPunchClock) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClock update = hyPunchClockService.update(hyPunchClock);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     *
     * @param hyPunchClock
     * @return
     */
    @PostMapping("/deleteHyPunchClockById")
    public MsgHander deleteHyPunchClockById(@RequestBody HyPunchClock hyPunchClock) {
        Long id = hyPunchClock.getId();
        MsgHander msg = new MsgHander();
        boolean deleteResult = hyPunchClockService.deleteById(id);
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