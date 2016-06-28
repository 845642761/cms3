package org.me.core.common.base;

import java.util.List;

public interface GetDao<T> {
	public T get(String id);//get
	public List<T> getList(T t);//查询
	public int getListSize(T t);//查询列表大小(分页)
}
