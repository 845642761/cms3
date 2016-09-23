package org.me.web.system.user.service;

import java.util.List;

import org.me.plugin.paging.io.QueryPagination;
import org.me.plugin.paging.vo.PageList;
import org.me.web.system.user.entity.SystemUser;

/**
 * LoginUserService
 * @author cheng_bo
 * @date:2015年5月24日 21:43:17
 */
public interface ISystemUserService {
	public String insert(SystemUser user);//添加
	public List<SystemUser> getList(SystemUser user);//用户管理
	public SystemUser getByLoginName(String strLoginName);//根据strLoginName获取用户
	public SystemUser getById(String strUserId);
	public List<SystemUser> listByDeptId(String deptId);//根据部门id查询用户
	public PageList<SystemUser> getListForPage(SystemUser user, QueryPagination queryPagination);//分页查询
	public PageList<SystemUser> listForPageByDeptId(String deptId, QueryPagination queryPagination);//分页查询
}
