package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import com.changjianghuyu.qixia.web.service.HyPunchClockLocationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;

/**
 * 设定的打卡地点表(HyPunchClockLocation)表控制层
 *
 * @author huangkewang
 * @since 2020-11-11 10:55:48
 */
@RestController
@RequestMapping("/hyPunchClockLocation")
public class HyPunchClockLocationController {
    /**
     * 服务对象
     */
    @Resource
    private HyPunchClockLocationService hyPunchClockLocationService;

    /**
     * 5.1通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.hyPunchClockLocationService.queryById(id));
    }


    /**
     * 5.2根据入参经纬度、所属的村id 查询最近的打卡地点
     * @param map
     * @return
     */
    @GetMapping("/getPunchClockLocationByAddress")
    public MsgHander getPunchClockLocationByAddress(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            HyPunchClockLocation hyPunchClockLocation =new HyPunchClockLocation();
            hyPunchClockLocation.setVillageId(Long.valueOf(map.get("villageId")));
            hyPunchClockLocation.setClockLongitude(new BigDecimal(map.get("clockLongitude")));
            hyPunchClockLocation.setClockLatitude(new BigDecimal(map.get("clockLatitude")));
            HyPunchClockLocation punchClockLocationByAddress = hyPunchClockLocationService.getPunchClockLocationByAddress(hyPunchClockLocation);
            msg.setContext(punchClockLocationByAddress);
        } catch (Exception e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 5.3分页查询打卡地址列表
     * @param map
     * @return
     */
    @GetMapping("/getPunchClockLocationList")
    public MsgHander getPunchClockLocationList(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyPunchClockLocationService.getPunchClockLocationList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 5.4按条件查询所有的地区集合
     * @param hyPunchClockLocation
     * @return
     */
    @GetMapping("/queryAllList")
    public MsgHander getPunchClockLocationList(HyPunchClockLocation hyPunchClockLocation) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyPunchClockLocationService.queryAllList(hyPunchClockLocation));
        } catch (Exception e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 5.5插入打卡地址信息
     * @param hyPunchClockLocation
     * @return
     */
    @PostMapping("/insertHyPunchClockLocation")
    public MsgHander insertHyPunchClockLocation(@RequestBody HyPunchClockLocation hyPunchClockLocation) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClockLocation insert = hyPunchClockLocationService.insert(hyPunchClockLocation);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 5.6更新打卡地址信息
     * @param hyPunchClockLocation
     * @return
     */
    @PostMapping("/updateHyPunchClockLocation")
    public MsgHander updateHyPunchClockLocation(@RequestBody HyPunchClockLocation hyPunchClockLocation) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyPunchClockLocation update = hyPunchClockLocationService.update(hyPunchClockLocation);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 5.7 真实删除打卡地址信息
     * @param hyPunchClockLocation
     * @return
     */
    @PostMapping("/deleteHyPunchClockLocationById")
    public MsgHander deleteHyPunchClockLocationById(@RequestBody HyPunchClockLocation hyPunchClockLocation) {
        Long id = hyPunchClockLocation.getId();
        MsgHander msg = new MsgHander();

        boolean deleteResult = hyPunchClockLocationService.deleteById(id);
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
     * 5.8批量删除
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String,String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.hyPunchClockLocationService.deleteByIds(Ids));
    }

}