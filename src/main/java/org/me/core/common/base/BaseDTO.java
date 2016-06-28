package org.me.core.common.base;

import java.io.Serializable;

/**
 * 查询DTO基类
 * @author: chengbo
 * @date: 2016年3月14日 18:02:48
 */
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object key;
	private Object value;
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
