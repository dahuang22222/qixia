package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.BaseController;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.common.msg.RestHanderCode;
import com.changjianghuyu.qixia.web.entity.HyUserExpand;
import com.changjianghuyu.qixia.web.service.LoginService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    /**
     * 1.1用户登陆
     * @param map
     * @return
     */
    @RequestMapping("/login")
    @PostMapping
    public MsgHander login(@RequestBody Map<String, String> map){
        MsgHander msgHander = new MsgHander();

        try {
            Map<String, Object> result = loginService.userLogin(map);
            msgHander.setMessage("登录成功");
            msgHander.setStatus(RestHanderCode.CONTROLLER_CODE_SUCCESS);
            msgHander.setContext(result.get("user"));
            if(null == result){
                msgHander.setMessage("登录失败");
                msgHander.setStatus(RestHanderCode.CONTROLLER_CODE_ERROR);
            }else {
                if(ObjectUtils.isEmpty(result.get("user"))){
                    msgHander.setStatus(RestHanderCode.CONTROLLER_CODE_ERROR);
                }
                msgHander.setMessage(String.valueOf(result.get("message")));
            }
        } catch (Exception e) {
            msgHander.setMessage("登录失败");
            msgHander.setStatus(RestHanderCode.CONTROLLER_CODE_ERROR);
        }
        return msgHander;
    }
}
