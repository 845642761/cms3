package org.me.plugin.util;

import java.util.UUID;

/**
 * UUID
 * @author: cheng_bo
 * @date: 2015年6月29日 09:57:21
 */
public class UuidUtil {

	/**
	 * 获取UUID
	 * @author: cheng_bo
	 * @date: 2015年6月29日 09:58:00
	 */
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
