package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.BaseController;
import com.changjianghuyu.qixia.web.common.jwt.UserInfo;
import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.SysArea;
import com.changjianghuyu.qixia.web.service.SysAreaService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 地区表(SysArea)表控制层
 *
 * @author makejava
 * @since 2020-11-12 15:17:16
 */
@RestController
@RequestMapping("/sysArea")
public class SysAreaController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SysAreaService sysAreaService;

    /**
     * 3.1通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.sysAreaService.queryById(id));
    }

    /**
     * 3.2通过地理信息获取
     *
     * @param map 参数集合
     * @return 单条数据
     */
    @GetMapping("/getAreaListByPage")
    public MsgHander getAreaListByPage(@RequestParam Map<String,String> map) {
        UserInfo userInfo = getUserInfo();
        System.out.println(userInfo.toString());
        return  new MsgHander(sysAreaService.getAreaListByPage(map));
    }

    /**
     * 3.3插入地址信息
     * @param sysArea
     * @return
     */
    @PostMapping("/insertSysArea")
    public MsgHander insertSysArea(@RequestBody SysArea sysArea) {
        MsgHander msg = new MsgHander();
        msg.setMessage("插入成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        Map<String, Object> result = sysAreaService.insert(sysArea);
        if(ObjectUtils.isEmpty(result.get("sysArea"))){
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
            msg.setMessage(String.valueOf(result.get("message")));
        }else{
            msg.setContext(result);
        }
        return  msg;
    }

    /**
     * 3.4更新信息
     * @param sysArea
     * @return
     */
    @PostMapping("/updateSysArea")
    public MsgHander updateSysArea(@RequestBody SysArea sysArea) {
        MsgHander msg = new MsgHander();

        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        Map<String, Object> result = sysAreaService.update(sysArea);
        msg.setMessage(String.valueOf(result.get("message")));
        if(null != result.get("sysArea")){
            msg.setContext(result.get("sysArea"));
        }else{
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return  msg;
    }

    /**
     * 3.5删除
     * @param sysArea
     * @return
     */
    @PostMapping("/deleteById")
    public MsgHander deleteById(@RequestBody SysArea sysArea) {
        Long id = sysArea.getId();

        MsgHander msg = new MsgHander();
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        Map<String, Object> result = sysAreaService.deleteById(id);
        msg.setMessage(String.valueOf(result.get("message")));
        if("-1" == result.get("status")){
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);

        }else{
            msg.setContext(result.get("status"));
        }
        return  msg;
    }


    /**
     * 3.6通过地理信息获取
     *
     * @param map
     * @return 所有的地区数据
     */
    @GetMapping("/getAreaList")
    public MsgHander getAreaList(@RequestParam Map<String,String> map) {
        return  new MsgHander(sysAreaService.getAreaList(map));
    }

    /**
     * 3.7通过地理信息获取
     *
     * @param map 参数集合
     * @return 单条数据
     */
    @GetMapping("/getAreaExpandListByPage")
    public MsgHander getAreaExpandListByPage(@RequestParam Map<String,String> map) {
        UserInfo userInfo = getUserInfo();
        return  new MsgHander(sysAreaService.getAreaExpandListByPage(map));
    }

    /**
     * 3.8批量删除
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String,String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.sysAreaService.deleteByIds(Ids));
    }

}