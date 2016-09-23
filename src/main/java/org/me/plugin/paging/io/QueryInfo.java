package org.me.plugin.paging.io;

import org.me.plugin.paging.core.Pagination;

public class QueryInfo extends Pagination {
	private static final long serialVersionUID = 1L;
	private String url;
	private String param;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
