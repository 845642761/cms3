package org.me.plugin.paging.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.me.plugin.paging.core.Pagination;

public class PageList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pagination pagination = new Pagination();
	private List<T> list = new ArrayList<T>();

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
