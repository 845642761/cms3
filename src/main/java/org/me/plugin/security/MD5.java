package org.me.plugin.security;

import java.security.MessageDigest;

import org.me.core.exception.ToolExecption;

public class MD5 {

	/**
	 * 字符串MD5加密
	 * @author cheng_bo
	 * @date 2015年6月25日 13:55:53
	 */
	public static String toMd5(String str) {
		MessageDigest md5 = null;
		StringBuffer hexValue = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
			char[] charArray = str.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = (md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			throw new ToolExecption("string to md5 error!", e);
		}
		return hexValue.toString();
	}
}
