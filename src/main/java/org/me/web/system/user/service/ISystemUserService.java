package org.me.web.system.user.service;

import java.util.List;

import org.me.core.common.Result;
import org.me.web.system.user.entity.SystemUser;

/**
 * LoginUserService
 * @author cheng_bo
 * @date:2015年5月24日 21:43:17
 */
public interface ISystemUserService {
	public Result insert(SystemUser user);//添加
	public List<SystemUser> getList(SystemUser user);//用户管理
	public List<SystemUser> listByDeptId(String deptId);//根据部门id查询用户
	public SystemUser getByLoginId(String strLoginId);//根据strLoginId获取用户
	public Result loginIdIsExit(String strLoginId);//用户帐号是否已存在
}
