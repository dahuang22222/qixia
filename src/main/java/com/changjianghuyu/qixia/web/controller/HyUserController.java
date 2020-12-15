package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.entity.HyUserArea;
import com.changjianghuyu.qixia.web.service.HyUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表(HyUser)表控制层
 *
 * @author makejava
 * @since 2020-11-12 18:31:23
 */
@RestController
@RequestMapping("  ")
public class HyUserController {
    /**
     * 服务对象
     */
    @Resource
    private HyUserService hyUserService;


    /**
     * 2.1新增单个用户
     *
     * @param hyUser
     * @return
     */
    @PostMapping("/insertUser")
    @ResponseBody
    public MsgHander insertUser(@RequestBody HyUser hyUser) {
        HyUser user = hyUserService.insert(hyUser);
        if (ObjectUtils.isEmpty(user)) {
            return new MsgHander(HanderCode.CONTROLLER_CODE_ERROR, "插入失败！", null);
        } else {
            return new MsgHander(user);
        }
    }

    /**
     * 2.2批量新增用户
     *
     * @param paramUser
     * @return
     */
    @PostMapping("/insertUserList")
    public MsgHander insertUserList(@RequestBody List<HyUser> paramUser) {
        List<HyUser> userList = hyUserService.insertUserList(paramUser);
        if (ObjectUtils.isEmpty(userList)) {
            return new MsgHander(HanderCode.CONTROLLER_CODE_ERROR, "插入失败！", null);
        } else {
            return new MsgHander(userList);
        }
    }

    /**
     * 2.3通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.hyUserService.queryById(id));
    }

    /**
     * 2.4分页查询用户信息
     *
     * @param map 数据集合
     * @return 多条数据
     */
    @GetMapping("/getUserList")
    public MsgHander getUserList(@RequestParam Map<String, String> map) {
        return new MsgHander(this.hyUserService.getUserList(map));
    }

    /**
     * 2.5根据用户id更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUserById")
    public MsgHander updateUserById(@RequestBody HyUser user) {
        return new MsgHander(this.hyUserService.update(user));
    }

    /**
     * 2.6
     *
     * @param user
     * @return
     */
    @PostMapping("/deleteById")
    public MsgHander deleteHyUserAreaById(@RequestBody HyUser user) {
        Long id = user.getId();
        MsgHander msg = new MsgHander();
        boolean deleteResult = hyUserService.deleteById(id);
        msg.setContext(deleteResult);
        if (deleteResult) {
            msg.setMessage("删除成功");
            msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        } else {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 2.7批量删除
     *
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String, String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.hyUserService.deleteByIds(Ids));
    }

    /**
     * 2.8根据旧密码更新密码
     *
     * @param map
     * @return
     */
    @PostMapping("/updateUserPassword")
    public MsgHander updateUserPassword(@RequestBody Map<String, String> map) {
        MsgHander msg = new MsgHander();
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        Map<String, Object> result = hyUserService.updateUserPassword(map);
        msg.setMessage(String.valueOf(result.get("message")));
        if (ObjectUtils.isEmpty(result.get("user"))) {
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        } else {
            msg.setContext(result.get("user"));
        }
        return msg;
    }
}