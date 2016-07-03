package org.me.web.system.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.me.web.system.user.entity.SystemUser;

public interface ISystemUserDao{
	public int insert(SystemUser user);
	public List<SystemUser> getList(SystemUser user);
	public int getListSize(SystemUser user);//查询列表大小
	public int updateUserState(@Param("strUserId")String strUserId, @Param("nState")Integer nState);//更改用户状态
	public SystemUser getById(String strUserId);
	public SystemUser getByLoginName(String strLoginName);
	
	public int delById(String strUserId);
	public int update(SystemUser user);
}
