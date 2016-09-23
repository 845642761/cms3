package org.me.plugin.paging.io;

import org.me.plugin.paging.core.Pagination;

public class QueryPagination extends Pagination {
	private static final long serialVersionUID = 1L;
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
