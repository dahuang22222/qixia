package com.changjianghuyu.qixia.web.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


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

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
