package org.me.core.common.base;

import java.io.Serializable;

public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String order;
	private String start;
	private String limit;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
}
