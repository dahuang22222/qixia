package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.entity.VillagePunchClockDay;
import com.changjianghuyu.qixia.web.service.VillagePunchClockDayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 村打卡统计(VillagePunchClockDay)表控制层
 *
 * @author makejava
 * @since 2020-11-19 16:49:25
 */
@RestController
@RequestMapping("/villagePunchClockDay")
public class VillagePunchClockDayController {
    /**
     * 服务对象
     */
    @Resource
    private VillagePunchClockDayService villagePunchClockDayService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public VillagePunchClockDay selectOne(Long id) {
        return this.villagePunchClockDayService.queryById(id);
    }

    /**
     * 分页获取信息统计列表
     * @param map
     * @return
     */
    @GetMapping("/getVillagePunchClockDayList")
    public MsgHander getVillagePunchClockDayList(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(villagePunchClockDayService.getVillagePunchClockDayList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    @PostMapping("/insertVillagePunchClockDay")
    public MsgHander insertVillagePunchClockDay(@RequestBody VillagePunchClockDay villagePunchClockDay) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        VillagePunchClockDay insert = villagePunchClockDayService.insert(villagePunchClockDay);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    @PostMapping("/updateVillagePunchClockDay")
    public MsgHander updateVillagePunchClockDay(@RequestBody VillagePunchClockDay villagePunchClockDay) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        VillagePunchClockDay update = villagePunchClockDayService.update(villagePunchClockDay);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     *
     * @param villagePunchClockDay
     * @return
     */
    @PostMapping("/deleteVillagePunchClockDayById")
    public MsgHander deleteVillagePunchClockDayById(@RequestBody HyInformationFeedback villagePunchClockDay) {
        Long id = villagePunchClockDay.getId();
        MsgHander msg = new MsgHander();

        boolean deleteResult = villagePunchClockDayService.deleteById(id);
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

    @GetMapping("/getVillagePunchClockDayListByTime")
    public MsgHander getVillagePunchClockDayListByTime(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        msg.setContext(villagePunchClockDayService.getVillagePunchClockDayListByTime(map));
        return msg;
    }
}