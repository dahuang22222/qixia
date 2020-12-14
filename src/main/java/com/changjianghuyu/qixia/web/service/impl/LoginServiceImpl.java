package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.common.jwt.JwtUtils;
import com.changjianghuyu.qixia.web.common.secret.MD5Utils;
import com.changjianghuyu.qixia.web.dao.HyUserDao;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.entity.HyUserExpand;
import com.changjianghuyu.qixia.web.service.HyUserService;
import com.changjianghuyu.qixia.web.service.LoginService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登陆/注册
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private HyUserService hyUserService;

    @Resource
    private HyUserDao hyUserDao;
    /**
     * 用户登陆
     * @param map
     * @return
     */
    @Override
    public Map<String,Object> userLogin(Map<String, String> map) {
        Map<String, Object> result = new HashMap<>();
        //用户密码登录普通：1
        if (!StringUtils.isEmpty(map.get("type")) && Integer.valueOf(map.get("type")) == 1){
            String phone = map.get("phone");
            //验证手机号和激活状态
            HyUser hyUser = new HyUser();
            hyUser.setPhone(map.get("phone"));
            List<HyUser> userList = hyUserDao.queryAll(hyUser);
            //手机号
            if (!CollectionUtils.isEmpty(userList)) {
                //密码
                HyUser user = userList.get(0);
                String userPassWord = userList.get(0).getPassword();

                String passWord = MD5Utils.encodedMD5(map.get("password"));
                if (userPassWord.equals(passWord)) {
                //token的map
                HashMap<String, Object> TokenMap = new HashMap<String, Object>();
                TokenMap.put("id", user.getId());
                TokenMap.put("phone", phone);
                TokenMap.put("userName",user.getUserName());
                TokenMap.put("userType", user.getUserType());
                TokenMap.put("village", user.getVillage());
                TokenMap.put("villageId", user.getVillageId());
                TokenMap.put("street", user.getStreet());
                TokenMap.put("streetId", user.getStreetId());
                TokenMap.put("userInfo",user);
                //添加用户token
                String token = JwtUtils.generateToken(TokenMap);
                HyUserExpand userExpand = new HyUserExpand(user,token);
                result.put("user",userExpand);
                result.put("message","登陆成功！");
                return result;
                }
            }else{
                result.put("message","用户不存在！");
                return result;
            }
        }
        //用户手机号登陆：2
        else if (!StringUtils.isEmpty(map.get("type")) && Integer.valueOf(map.get("type")) == 2){
            String phone = map.get("phone");
            //验证手机号和激活状态
            HyUser hyUser = new HyUser();
            hyUser.setPhone(map.get("phone"));
            hyUser.setStreetId(Long.valueOf(map.get("streetId")));
            hyUser.setVillageId(Long.valueOf(map.get("villageId")));
            List<HyUser> userList = hyUserDao.queryAll(hyUser);
            //手机号
            if (!CollectionUtils.isEmpty(userList)) {
                //密码
                HyUser user = userList.get(0);
                //验证街道和村是否满足要求
                if(StringUtils.isNotBlank(map.get("streetId"))){
                    if(Long.valueOf(map.get("streetId")) != user.getStreetId()) {
                        return null;
                    }
                }
//                String userPassWord = userList.get(0).getPassword();
//
//                String passWord = MD5Utils.encodedMD5(map.get("password"));
//                if (userPassWord.equals(passWord)) {
                //token的map
                HashMap<String, Object> TokenMap = new HashMap<String, Object>();
                TokenMap.put("id", user.getId());
                TokenMap.put("phone", phone);
                TokenMap.put("userName",user.getUserName());
                TokenMap.put("userType", user.getUserType());
                TokenMap.put("village", user.getVillage());
                TokenMap.put("villageId", user.getVillageId());
                TokenMap.put("street", user.getStreet());
                TokenMap.put("streetId", user.getStreetId());
                TokenMap.put("userInfo",user);
                //添加用户token
                String token = JwtUtils.generateToken(TokenMap);
                HyUserExpand userExpand = new HyUserExpand(user,token);
                result.put("user",userExpand);
                result.put("message","登陆成功！");
                return result;
//                }
            }else{
                result.put("message","请核对所选村庄和手机号是否正确！");
                return result;
            }
        } else if (Integer.valueOf(map.get("type")) == 3) {  //小程序登陆token
            //token 获取用户信息  Claims extends Map<String, Object>
            Claims userInfo = JwtUtils.verifyAndGetClaimsByToken(map.get("token"));
            String phone = userInfo.get("phone").toString();
            if (!StringUtils.isEmpty(phone)
//                    && DataFormateUtils.phoneToFormate(phone) == 1
            ) {
//                //验证是否已经登录
//                if (RedisConfig.redisTemplate().get(phone) != null && "1".equals(RedisConfig.redisTemplate().get(phone))) {
//                    //登录之后不做任何请求
//                    //                return null;
//                } else {
//                    RedisConfig.redisTemplate().setex(phone, 1800, "1");
//                }
                //验证手机号和激活状态
                HyUser hyUser = new HyUser();
                List<HyUser> userList = hyUserDao.queryAll(hyUser);
                if (userList != null && userList.size() > 0) {
                    //token的map
//                    HashMap<String, Object> TokenMap = new HashMap<String, Object>();
//                    TokenMap.put("id", user.getId());
//                    TokenMap.put("phone", phone);
//                    //添加用户token
//                    String mytoken = JwtUtils.generateToken(TokenMap);
                    HyUser user = userList.get(0);
                    result.put("user",new HyUserExpand(user,map.get("token")));
                    result.put("message","登陆成功！");
                    return result;
                }
            }else{
                result.put("message","手机号不存在！");
                return result;
            }
        } else{
            result.put("message","请选择登陆方式！");
            return result;
        }
        return null;
    }
}
