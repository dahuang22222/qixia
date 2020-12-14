package com.changjianghuyu.qixia.web.common.secret;

import java.util.UUID;

public class UUIDUtils {

	public static String randomUUID() {
		return randomUUID(32);
	}

	public static String randomUUID(int i) {
		return UUID.randomUUID().toString().replace("-", "").substring(0, i);
	}
	
	public static String rdUUID() {
		return System.currentTimeMillis()/1000 +
				UUID.randomUUID().toString().replace("-", "").substring(0, 6);
	}

	/**
	 * 成length位唯一性的电表
	 * @param length
	 * @return 返回的主键长度
	 */
	public static String getNumberUUID(int length){
		//生成时间戳
		String time = String.valueOf(System.currentTimeMillis());
		//生成uuid的hashCode值
		int hashCode = UUID.randomUUID().toString().hashCode();
		//可能为负数
		if(hashCode<0){
			hashCode = -hashCode;
		}
		String value = time.substring(0,10)+ String.format("%d", hashCode).substring(0,length-10);
		return value;
	}
}
