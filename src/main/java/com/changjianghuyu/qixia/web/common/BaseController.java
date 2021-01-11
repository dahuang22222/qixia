package com.changjianghuyu.qixia.web.common;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.changjianghuyu.qixia.web.common.jwt.JwtUtils;
import com.changjianghuyu.qixia.web.common.jwt.UserInfo;
import com.changjianghuyu.qixia.web.entity.HyUser;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * 
 * 
 */
public class BaseController extends AbstractController {

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		// SimpleDateFormat dateFormat = new SimpleDateFormat(
		// "yyyy-MM-dd hh:mm:ss");
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(
		// dateFormat, true));

		// -----update-begin---- author:zhangdaihao date:20130227 for:时间转换问题
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		// -----update-end---- author:zhangdaihao date:20130227 for:时间转换问题
	}


	public UserInfo getUserInfo(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("token");
		Claims claims = JwtUtils.verifyAndGetClaimsByToken(token);
		UserInfo user = new UserInfo();
		if(claims.get("phone") != null){
			user.setPhone(String.valueOf(claims.get("phone")));
		}
		if(claims.get("id") != null){
			user.setId(Long.valueOf(String.valueOf(claims.get("id"))));
		}
		if(claims.get("userName") != null){
			user.setUserName(String.valueOf(claims.get("userName")));
		}
		if(claims.get("userType") != null){
			user.setUserType(Integer.valueOf(String.valueOf(claims.get("userType"))));
		}
		if(claims.get("village") != null){
			user.setVillage(String.valueOf(claims.get("village")));
		}
		if(claims.get("villageId") != null){
			user.setVillageId(Long.valueOf(String.valueOf(claims.get("villageId"))));
		}
		if(claims.get("street") != null){
			user.setStreet(String.valueOf(claims.get("street")));
		}
		if(claims.get("streetId") != null){
			user.setStreetId(Long.valueOf(String.valueOf(claims.get("streetId"))));
		}
		if(claims.get("streetId") != null){
			user.setStreetId(Long.valueOf(String.valueOf(claims.get("streetId"))));
		}
		if(claims.get("userInfo") != null){
			HyUser userInfo = JSON.parseObject(JSON.toJSONString(claims.get("userInfo")), HyUser.class);
			user.setUserInfo(userInfo);
		}
		return user;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
