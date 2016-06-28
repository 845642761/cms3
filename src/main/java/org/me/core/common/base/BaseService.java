package org.me.core.common.base;

import java.util.List;
import org.me.core.common.Result;

/**
 * @description: 基类
 * @author: cheng_bo
 * @date: 2015年7月3日 13:56:21
 */
public interface BaseService<T> {
	public Result insert(T t);//插入
	public Result get(String id);//get
	public Result del(List<String> ids);//删除
	public Result update(T t);//修改
	public Result getList(T t);//查询
}
