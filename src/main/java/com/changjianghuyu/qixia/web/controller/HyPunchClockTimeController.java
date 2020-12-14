package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyPunchClock;
import com.changjianghuyu.qixia.web.entity.HyPunchClockTime;
import com.changjianghuyu.qixia.web.service.HyPunchClockTimeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 打卡时间表(HyPunchClockTime)表控制层
 *
 * @author makejava
 * @since 2020-11-12 15:07:36
 */
@RestController
@RequestMapping("/hyPunchClockTime")
public class HyPunchClockTimeController {
    /**
     * 服务对象
     */
    @Resource
    private HyPunchClockTimeService hyPunchClockTimeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.hyPunchClockTimeService.queryById(id));
    }


    /**
     * 11.1分页获取信息反馈列表
     * @param map
     * @return
     */
    @GetMapping("/getHyPunchClockTimeList")
    public MsgHander getHyPunchClockTimeList(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyPunchClockTimeService.getHyPunchClockTimeList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 11.2插入打卡时间信息
     * @param hyPunchClockTime
     * @return
     */
    @PostMapping("/insertHyPunchClockTime")
    public MsgHander insertHyPunchClockTime(@RequestBody HyPunchClockTime hyPunchClockTime) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClockTime insert = hyPunchClockTimeService.insert(hyPunchClockTime);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 11.3通过id修改打卡时间信息
     * @param hyPunchClockTime
     * @return
     */
    @PostMapping("/updateHyPunchClockTime")
    public MsgHander updateHyPunchClockTime(@RequestBody HyPunchClockTime hyPunchClockTime) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClockTime update = hyPunchClockTimeService.update(hyPunchClockTime);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 11.4通过id删除打卡时间id
     * @param hyPunchClockTime
     * @return
     */
    @PostMapping("/deleteHyPunchClockTimeById")
    public MsgHander deleteHyPunchClockTimeById(@RequestBody HyPunchClockTime hyPunchClockTime) {
        Long id = hyPunchClockTime.getId();
        MsgHander msg = new MsgHander();

        boolean deleteResult = hyPunchClockTimeService.deleteById(id);
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

    /**
     * 11.5批量删除
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String,String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.hyPunchClockTimeService.deleteByIds(Ids));
    }
}