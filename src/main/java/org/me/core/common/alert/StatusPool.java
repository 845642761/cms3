package org.me.core.common.alert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class StatusPool {
	private static Logger log = Logger.getLogger(StatusPool.class);
	static Map<String,Status> statusMap = new HashMap<String, Status>();
	
	/**
	 * 初始化Status
	 * @author chengbo
	 * @date 2016年5月5日 18:06:55
	 * @param url
	 */
	public void initStatus(String url) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(url));
		} catch (IOException e) {
			log.error("init properties error:"+url,e);
		}

		for(Enumeration<?> e = properties.propertyNames(); e.hasMoreElements();){
		    String key=(String) e.nextElement();
		    String value = properties.getProperty(key);
		    Status status = new Status();
		    status.setCode(key);
		    String[] split = value.split(",");
			if ("true".equals(split[0].toString())) {
		    	status.setSuccess(true);
		    } else {
		    	status.setSuccess(false);
		    }
		    status.setMsg(split.length>1?split[1].toString():"");
		    statusMap.put(key, status);
		}
	}
	
	/**
	 * 根据key获取Status
	 * @author chengbo
	 * @date 2016年5月5日 18:08:29
	 * @param key
	 * @return Status
	 */
	public static Status getStatus(String key) {
		return statusMap.get(key);
	}
}
