package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyUserExpand;

import java.util.Map;

/**
 * 用户登陆注册相关接口
 */
public interface LoginService {
     Map<String,Object> userLogin(Map<String,String> map);

}
