package org.me.plugin.paging.core;

import java.io.Serializable;

import org.me.plugin.paging.config.DefaultPageingConfig;

public class Pagination implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String STARTINDEX = "startIndex";
	public static final String NUMPERPAGE = "numPerPage";
	public static final String TOTALROWS = "totalRows";
	public static final String PAGINATION = "pagination";
	private int numPerPage = DefaultPageingConfig.numPerPage;//每页数
	private int startIndex;//开始数
	private int totalRows;//总行数
	
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}
