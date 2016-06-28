package org.me.web.plugin.shiro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.shiro.util.CollectionUtils;
import org.me.core.common.Result;
import org.me.core.exception.ServiceExecption;
import org.me.plugin.util.UuidUtil;
import org.me.web.plugin.shiro.dao.ISecurityDao;
import org.me.web.plugin.shiro.entity.Permission;
import org.me.web.plugin.shiro.entity.UserRole;
import org.me.web.plugin.shiro.service.ISecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 安全 权限service实现类
 * @author: cheng_bo
 * @date: 2015年8月6日 10:49:56
 */
@Service
public class SecurityService implements ISecurityService {
	
	private Logger logger = Logger.getLogger(SecurityService.class);
	@Resource
	private ISecurityDao securityDao;
	
	/**
	 * 根据用户Id查找其角色
	 * @author: cheng_bo
	 * @date: 2015年8月6日 10:53:10
	 */
	@Override
	public Set<String> getRoles(String strLoginId) {
		Set<UserRole> ur=securityDao.getRoles(strLoginId);
		Set<String> roles=new HashSet<String>();
		for (UserRole r : ur) {
			roles.add(r.getStrRoleId());
		}
		return roles;
	}

	/**
	 * 根据roleId查询权限
	 * @author: cheng_bo
	 * @date: 2015年8月6日 11:59:46
	 */
	@Override
	public Set<String> getPermissions(Set<String> roleIds) {
		if(CollectionUtils.isEmpty(roleIds))
			return null;
		Set<String> permissions=new HashSet<String>();
		for (String rId : roleIds) {
			ArrayList<Permission> p=securityDao.getPermissionsByRoleId(rId);
			for (Permission permission : p) {
				permissions.add(permission.getStrPermission());
			}
		}
		return permissions;
	}
	
	@Override
	public ArrayList<Permission> getPermissionsList(Permission p,Set<String> roleIds) {
		ArrayList<Permission> al=new ArrayList<>();
		if(CollectionUtils.isEmpty(roleIds)){
			logger.info("暂无角色信息");
			return al;
		}
		try {
			HashMap<String, String> hm=new HashMap<>();
			if (p != null) {
				if(StringUtils.hasText(p.getStrPid()))
					hm.put("strPid", p.getStrPid());
				if(StringUtils.hasText(p.getStrName()))
					hm.put("strName", p.getStrName());
				if(p.getnType() != null)
					hm.put("nType", p.getnType()+"");
				if(p.getnState() != null)
					hm.put("nState", p.getnState()+"");
				if(p.getnHasChild() != null)
					hm.put("nHasChild", p.getnHasChild()+"");
				if(StringUtils.hasText(p.getOrder()))
					hm.put("order", p.getOrder());
				if(StringUtils.hasText(p.getStart()))
					hm.put("start", p.getStart());
				if(StringUtils.hasText(p.getLimit()))
					hm.put("limit", p.getLimit());
			}
			for (String str : roleIds) {
				hm.put("roleId", str);
				al.addAll(securityDao.getPermissionsList(hm));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new ServiceExecption("查询失败");
		}
		return al;
	}

	@Override
	public Result insert(Permission t) {
		Result resoult=new Result();
		if(t == null){
			logger.error("permission is null!");
			resoult.setCode(-1);
			resoult.setInfo("请选择信息保存");
			return resoult;
		}
		try {
			insertPermission(t);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("保存失败");
		}
		return resoult;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	private void insertPermission(Permission t) {
		/**
		 * 如果是父级添加子级
		 */
		if(StringUtils.hasText(t.getStrPid())){
			Permission parent=securityDao.get(t.getStrPid());
			if(parent.getnHasChild() == 0){
				parent.setnHasChild(1);
				securityDao.update(parent);
			}
		}else {
			t.setStrPid("0");
		}
		t.setnHasChild(0);
		t.setStrId(UuidUtil.getUUID());
		securityDao.insert(t);
	}

	@Override
	public Result get(String id) {
		Result resoult=new Result();
		if(!StringUtils.hasText(id)){
			logger.error("id is null");
			resoult.setCode(-1);
			resoult.setInfo("请选择信息查看");
			return resoult;
		}
		try {
			resoult.setObject(securityDao.get(id));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("查询失败");
		}
		return resoult;
	}

	@Override
	public Result del(List<String> ids) {
		Result resoult=new Result();
		if(ids == null || ids.size() < 1){
			logger.error("id is null!");
			resoult.setCode(-1);
			resoult.setInfo("请选择信息删除");
			return resoult;
		}
		try {
			for (String id : ids) {
				securityDao.del(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("删除失败");
		}
		return resoult;
	}

	@Override
	public Result update(Permission t) {
		Result resoult=new Result();
		if(t == null){
			logger.error("permission is null!");
			resoult.setCode(-1);
			resoult.setInfo("请选择信息修改");
			return resoult;
		}
		try {
			securityDao.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("修改失败");
		}
		return resoult;
	}

	@Override
	public Result getList(Permission t) {
		Result resoult=new Result();
		try {
			resoult.setObject(securityDao.getList(t));
			resoult.setSize(securityDao.getListSize(t));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("查询失败");
		}
		return resoult;
	}
}
