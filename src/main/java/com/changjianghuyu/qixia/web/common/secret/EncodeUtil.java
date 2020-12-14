package com.changjianghuyu.qixia.web.common.secret;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 封装 DES加密 /Base64编码的工具类
 * 
 * @author yulianyu
 * 
 */
public class EncodeUtil {
	private static byte rawKeyData[] = null;

	static {
		/*
		 * SecureRandom sr; try { sr = new SecureRandom("qt".getBytes("utf-8"));
		 * } catch (UnsupportedEncodingException e) { sr = new
		 * SecureRandom("qt".getBytes()); } // 为我们选择的DES算法生成一个KeyGenerator对象
		 * KeyGenerator kg = null; try { kg = KeyGenerator.getInstance("DES"); }
		 * catch (NoSuchAlgorithmException e) { } kg.init(sr); // 生成密匙 SecretKey
		 * key = kg.generateKey(); rawKeyData = key.getEncoded(); // 获取密匙数据
		 */rawKeyData = HEXStringToByte("7ca4f7ceb3e91016");

	}

	/**
	 * 传入字符串，返回一个加密串
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		try {
			byte[] encryptedData = encrypt(s);
			s = byteToHEXString(encryptedData);
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 传入数字，返回一个加密串
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(long goodsId) {
		try {
			byte[] encryptedData = encrypt("" + goodsId);
			return byteToHEXString(encryptedData);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 传入加密串，返回解密串
	 * 
	 * @param s
	 * @return
	 */
	public static String decode(String s) {
		try {
			return decrypt(HEXStringToByte(s));
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 传入字符串，返回一个加密串
	 * 
	 * @param s
	 * @return
	 */
	public static String encodeBase64(String s) {
		byte[] binaryData = null;
		try {
			binaryData = s.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
		// byte[] newbt = Base64.encodeBase64(binaryData);

		return encodeBase64(binaryData);// new String(newbt);
	}

	public static String encodeBase64(byte[] binaryData) {
		byte[] newbt = Base64.encodeBase64(binaryData);

		return new String(newbt);
	}

	public static byte[] enBase64(byte[] binaryData) {
		return Base64.encodeBase64(binaryData);
	}

	public static byte[] deBase64(byte[] bytes) throws IOException {
		return Base64.decodeBase64(bytes);
	}

	@SuppressWarnings("deprecation")
	public static String stringEncode(String str) {
		return java.net.URLEncoder.encode(str);
	}

	/**
	 * 传入加密串，返回解密串
	 * 
	 * @param s
	 * @return
	 */
	public static String decodeBase64(String s) {
		try {
			return decodeBase64(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	public static String decodeBase64(byte[] bytes) {
		byte[] oldbt = null;
		String t = null;
		try {
			oldbt = Base64.decodeBase64(bytes);
			t = new String(oldbt, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return t;
	}

	public static String byteToHEXString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(100);
		for (int i = 0; i < bArray.length; i++) {
			String hex = Integer.toHexString(bArray[i] & 0xff);
			if (hex.length() == 1) {
				sb.append("0").append(hex);
			} else {
				sb.append(hex);
			}
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] HEXStringToByte(String strString) {
		byte[] ret = new byte[strString.length() / 2];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.decode("#" + strString.substring(2 * i, 2 * i + 2)).byteValue();
		}
		return ret;
	}

	/**
	 * 加密方法
	 * 
	 * @param rawKeyData
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encrypt(String str) throws InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException,
            UnsupportedEncodingException {
		// DES算法要求有一个可信任的随机数源
		// SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 现在，获取数据并加密
		byte data[] = str.getBytes("utf-8");
		// 正式执行加密操作
		byte[] encryptedData = cipher.doFinal(data);

		return encryptedData;
	}

	/**
	 * 解密方法
	 *
	 * @param encryptedData
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(byte[] encryptedData) throws IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
            UnsupportedEncodingException {
		// DES算法要求有一个可信任的随机数源
		// SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 正式执行解密操作
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return new String(decryptedData, "utf-8");
	}

	public String getCurrentMillyTime() {
		return Long.valueOf(System.currentTimeMillis()).toString();
	}

	/**
	 * 字节数组转16进制
	 * 
	 * @param bytes
	 *            需要转换的byte数组
	 * @return 转换后的Hex字符串
	 */
	public static String byteToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() < 2) {
				sb.append(0);
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * hex字符串转byte数组
	 * 
	 * @param inHex
	 *            待转换的Hex字符串
	 * @return 转换后的byte数组结果
	 */
	public static byte[] hexToByte(String inHex) {
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1) {
			// 奇数
			hexlen++;
			result = new byte[(hexlen / 2)];
			inHex = "0" + inHex;
		} else {
			// 偶数
			result = new byte[(hexlen / 2)];
		}
		int j = 0;
		for (int i = 0; i < hexlen; i += 2) {
			result[j] = StringToByte(inHex.substring(i, i + 2));
			j++;
		}
		return result;
	}

	/**
	 * Hex字符串转byte
	 * 
	 * @param inHex
	 *            待转换的Hex字符串
	 * @return 转换后的byte
	 */
	public static byte StringToByte(String inHex) {
		return (byte) Integer.parseInt(inHex, 16);
	}
}
