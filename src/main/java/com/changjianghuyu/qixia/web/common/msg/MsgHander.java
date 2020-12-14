package com.changjianghuyu.qixia.web.common.msg;

/**
 * 通用消息传输模型
 * 
 */
public class MsgHander {

	/**
	 * 处理状态
	 */
	private String status;

	/**
	 * 消息句柄
	 */
	private String message;

	/**
	 * 内容句柄
	 */
	private Object context;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

	public MsgHander(String status, String message, Object context) {
		this.status = status;
		this.message = message;
		this.context = context;
	}

	public MsgHander(String message,Object context) {
		this.status = HanderCode.CONTROLLER_CODE_SUCCESS;
		this.message = message;
		this.context = context;
	}

	public MsgHander( Object context) {
		this.status = HanderCode.CONTROLLER_CODE_SUCCESS;
		this.message = "请求成功！";
		this.context = context;
	}

	public MsgHander() {
		this.status = HanderCode.CONTROLLER_CODE_SUCCESS;
		this.message = "请求成功！";
	}
}
