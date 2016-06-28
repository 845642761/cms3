package org.me.core.common;

import java.io.Serializable;

/**
 * @description 结果类
 * @author cheng_bo
 * @date 2015年6月24日 14:40:35
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code=0;//状态   0:正常  -1:error
	private Object object;
	private int size = 0;//大小
	private String info;//说明信息
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 * 是否成功
	 * @author: chengbo
	 * @date: 2015年8月31日 15:33:02
	 */
	public boolean isSuccess() {
		switch (code) {
		case 0:
			return true;
		case -1:
			return false;
		default:
			return false;
		}
	}
}
