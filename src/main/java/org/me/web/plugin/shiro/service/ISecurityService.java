package org.me.web.plugin.shiro.service;

import java.util.ArrayList;
import java.util.Set;
import org.me.core.common.base.BaseService;
import org.me.web.plugin.shiro.entity.Permission;
/**
 * 安全 权限service
 * @author: cheng_bo
 * @date: 2015年8月6日 10:49:56
 */
public interface ISecurityService extends BaseService<Permission> {
	public Set<String> getRoles(String strLoginId);//根据用户Id查找其角色
	public Set<String> getPermissions(Set<String> roleIds);//根据角色Id查找其权限
	public ArrayList<Permission> getPermissionsList(Permission p,Set<String> roleIds);
}