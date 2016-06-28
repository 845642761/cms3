package org.me.web.plugin.shiro.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.me.core.common.base.CommonDao;
import org.me.web.plugin.shiro.entity.Permission;
import org.me.web.plugin.shiro.entity.UserRole;

/**
 * 安全 权限Dao
 * @author: cheng_bo
 * @date: 2015年8月6日 10:57:24
 */
public interface ISecurityDao extends CommonDao<Permission>{
	public Set<UserRole> getRoles(String strLoginId);//根据用户Id查找其角色
	public ArrayList<Permission> getPermissionsByRoleId(String roleId);//根据角色Id查找其权限
	public ArrayList<Permission> getPermissionsList(HashMap<String,String> hm);
}
