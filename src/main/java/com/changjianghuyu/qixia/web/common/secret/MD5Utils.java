package com.changjianghuyu.qixia.web.common.secret;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Gynco on 2019/9/16 16:03
 */
public class MD5Utils {
    /**
     * @param password
     *            密码
     * @return
     */

    //干扰数据 盐 防破解
    private static final String SALT = "cjhy";
    //散列算法类型为MD5
    private static final String ALGORITHM_NAME = "md5";
    //hash的次数
    private static final int HASH_ITERATIONS = 1;

    /**
     * 直接生成密码
     * @param password
     * @return
     */
    public static String encodedMD5(String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
    }

    /**
     * 创建密码
     * @param password
     * @param phone
     * @param salt
     * @return
     */
    public static String getPassWorld(String phone, String password, String salt) {
        return new SimpleHash(ALGORITHM_NAME, password+salt, ByteSource.Util.bytes(phone), HASH_ITERATIONS).toHex();
    }

}
