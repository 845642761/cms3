package org.me.web.system.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.me.core.common.Result;
import org.me.core.exception.ServiceExecption;
import org.me.plugin.security.MD5;
import org.me.web.system.user.dao.ISystemUserDao;
import org.me.web.system.user.entity.SystemUser;
import org.me.web.system.user.service.ISystemUserService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements ISystemUserService{

	private Logger logger = Logger.getLogger(SystemUserService.class);
	@Resource
	private ISystemUserDao loginUserDao;
	
	/**
	 * 用户管理
	 * @author: chengbo
	 * @date: 2015年11月23日 11:36:41
	 */
	@Override
	public List<SystemUser> getList(SystemUser user) {
		return loginUserDao.getList(user);
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
		user.setnState(0);
		return loginUserDao.getList(user);
	}
	
	@Override
	public Result insert(SystemUser loginUser) {
		Result resoult=new Result();
		try {
			MD5 md5=new MD5();
			loginUser.setStrPassword(md5.toMd5(loginUser.getStrPassword()));
			loginUserDao.insert(loginUser);
			resoult.setInfo("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setInfo("保存失败");
			resoult.setCode(-1);
		}
		logger.debug("SystemUserService.save successful!");
		return resoult;
	}

	/**
	 * 用户帐号是否已存在
	 * @author cheng_bo
	 * @date 2015年5月26日 11:03:05
	 * @param 用户帐号
	 */
	@Override
	public Result loginIdIsExit(String strLoginId) {
		Result resoult = new Result();
		SystemUser user = new SystemUser();
		user.setStrUserId(strLoginId);
		int size=0;
		try {
			size=loginUserDao.getListSize(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resoult.setCode(-1);
			resoult.setInfo("查询错误");
		}
		if(size > 0){
			resoult.setCode(-1);
			resoult.setInfo("用户已存在");
		}
		return resoult;
	}
	
	/**
	 * 根据strLoginId获取用户
	 * @author: chengbo
	 * @date: 2015年8月11日 14:01:52
	 */
	@Override
	public SystemUser getByLoginId(String strLoginId) {
		SystemUser u = null;
		try {
			u = loginUserDao.get(strLoginId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new ServiceExecption("查询错误");
		}
		return u;
	}
}