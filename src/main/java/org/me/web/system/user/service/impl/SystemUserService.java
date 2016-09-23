package org.me.web.system.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.me.core.exception.ServiceExecption;
import org.me.plugin.paging.core.Pagination;
import org.me.plugin.paging.io.QueryPagination;
import org.me.plugin.paging.vo.PageList;
import org.me.plugin.security.MD5;
import org.me.plugin.util.ObjectUtil;
import org.me.plugin.util.UuidUtil;
import org.me.web.system.user.dao.ISystemUserDao;
import org.me.web.system.user.entity.SystemUser;
import org.me.web.system.user.service.ISystemUserService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements ISystemUserService{

	private Logger logger = Logger.getLogger(SystemUserService.class);
	@Resource
	private ISystemUserDao systemUserDao;
	
	/**
	 * 用户管理
	 * @author: chengbo
	 * @date: 2015年11月23日 11:36:41
	 */
	@Override
	public List<SystemUser> getList(SystemUser user) {
		return systemUserDao.getList(user);
	}
	
	/**
	 * 根据部门id查询用户
	 * @author: chengbo
	 * @date: 2015年11月23日 14:41:08
	 */
	@Override
	public List<SystemUser> listByDeptId(String deptId) {
		SystemUser user = new SystemUser();
		user.setStrDeptId(deptId);
		user.setnState(1);
		return systemUserDao.getList(user);
	}
	
	@Override
	public String insert(SystemUser user) {
		String userId = UuidUtil.getUUID();
		user.setStrUserId(userId);
		user.setStrPassword(MD5.toMd5(user.getStrPassword()));
		systemUserDao.insert(user);
		logger.debug("add one systemUser : userId is:" + userId + " &loginName is :" + user.getStrLoginName());
		return userId;
	}
	
	/**
	 * 根据strLoginId获取用户
	 * @author: chengbo
	 * @date: 2015年8月11日 14:01:52
	 */
	@Override
	public SystemUser getByLoginName(String strLoginName) {
		SystemUser u = null;
		try {
			u = systemUserDao.getByLoginName(strLoginName);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceExecption("getUser error : strLoginName is :" + strLoginName);
		}
		return u;
	}

	@Override
	public SystemUser getById(String strUserId) {
		SystemUser u = null;
		try {
			u = systemUserDao.getById(strUserId);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceExecption("getUser error : strUserId is :" + strUserId);
		}
		return u;
	}

	@Override
	public PageList<SystemUser> getListForPage(SystemUser user, QueryPagination queryPagination) {
		Map<String, Object> map = ObjectUtil.objectToMapIgnoreStatic(user);
		map.putAll(ObjectUtil.objectToMapIgnoreStatic(queryPagination));

		PageList<SystemUser> pageList = new PageList<>();
		pageList.setList(systemUserDao.getListByMap(map));
		Pagination pagination = pageList.getPagination();
		pagination.setNumPerPage(queryPagination.getNumPerPage());
		pagination.setStartIndex(queryPagination.getStartIndex());
		pagination.setTotalRows(systemUserDao.getListSize(user));
		return pageList;
	}

	@Override
	public PageList<SystemUser> listForPageByDeptId(String deptId,
			QueryPagination queryPagination) {
		SystemUser user = new SystemUser();
		user.setStrDeptId(deptId);
		user.setnState(1);
		Map<String, Object> map = new HashMap<>();
		map.put("nState", 1);
		map.put("strDeptId", deptId);
		PageList<SystemUser> pageList = new PageList<>();
		pageList.setList(systemUserDao.getListByMap(map));
		Pagination pagination = pageList.getPagination();
		pagination.setNumPerPage(queryPagination.getNumPerPage());
		pagination.setStartIndex(queryPagination.getStartIndex());
		pagination.setTotalRows(systemUserDao.getListSize(user));
		return pageList;
	}
}