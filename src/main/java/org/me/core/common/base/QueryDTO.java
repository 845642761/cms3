package org.me.core.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用查询基类
 * @author: chengbo
 * @date: 2016年3月14日 18:04:32
 */
public class QueryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<BaseDTO> list;

	public QueryDTO() {
		if(list == null)
			list = new ArrayList<BaseDTO>();
	}

	public List<BaseDTO> getList() {
		return list;
	}

	public void setList(List<BaseDTO> list) {
		this.list = list;
	}
}
