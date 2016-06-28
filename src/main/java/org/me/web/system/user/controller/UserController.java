package org.me.web.system.user.controller;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.me.core.common.Result;
import org.me.core.exception.ViewExecption;
import org.me.web.core.common.base.BaseController;
import org.me.web.system.user.entity.SystemUser;
import org.me.web.system.user.service.ISystemUserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户Controller
 * @author: cheng_bo
 * @date: 2015年7月28日 17:58:57
 */
@Controller
@RequestMapping("/system/user/")
public class UserController extends BaseController {
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@Resource
	private ISystemUserService systemUserService;
	
	/**
	 * 登录跳转
	 * @author: chengbo
	 * @date: 2015年11月20日 11:36:33
	 */
	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("/system/user/login");
	}

	/**
	 * 用户登录
	 * @author cheng_bo
	 * @date 2015年6月5日 17:00:42
	 */
	@RequestMapping("ssoLogin")
	@ResponseBody
	public Result ssoLogin(SystemUser lUser){
		Result resoult=new Result();
		resoult.setCode(-1);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(lUser.getStrUserId(), lUser.getStrPassword());
		try {
			subject.login(token);
        	resoult.setCode(0);
		}catch (UnknownAccountException e) {
			resoult.setInfo("帐号不存在！");
		}catch (IncorrectCredentialsException e) {
			resoult.setInfo("密码验证错误！");
		}catch (LockedAccountException e) {
			if("-1".equals(e.getMessage())){
				resoult.setInfo("帐号已禁用！");
			}else if("0".equals(e.getMessage())) {
				resoult.setInfo("帐号审核中！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			resoult.setInfo("登录失败！");
		}
		return resoult;
	}
	
	/**
	 * 用户管理
	 * @author: cheng_bo
	 * @date: 2015年11月23日 11:33:37
	 */
	@RequestMapping("list")
	public ModelAndView getList(SystemUser user) {
		ModelAndView mav = new ModelAndView("/system/user/list");
		return mav;
	}
	
	/**
	 * 查看部门下用户
	 * @author: chengbo
	 * @date: 2015年11月23日 14:50:13
	 */
	@RequestMapping("listByDeptId")
	public ModelAndView listByDeptId(String deptId) {
		ModelAndView mav = new ModelAndView("/system/user/deptUserList");
		try {
			List<SystemUser> users = systemUserService.listByDeptId(deptId);
			mav.addObject("SystemUser", users);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new ViewExecption("用户查询失败！");
		}
		return mav;
	}

	/**
	 * 添加 || 修改跳转
	 * @author: chengbo
	 * @date: 2015年12月14日 20:28:42
	 */
	@RequestMapping("edit")
	public ModelAndView edit(String strUserId) {
		ModelAndView mav = new ModelAndView("/system/user/edit");
		if(StringUtils.hasText(strUserId)){
			mav.addObject("SystemUser", systemUserService.getByLoginId(strUserId));
		}
		return mav;
	}
	
	/**
	 * 注册登录用户
	 * @author cheng_bo
	 * @date 2015年6月5日 21:38:30
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public Result saveOrUpdate(SystemUser user){
		String strLoginId = user.getStrUserId();
		String strPassword = user.getStrPassword();
		Result resoult=new Result();
		resoult.setCode(-1);
		if(StringUtils.isEmpty(strLoginId)){
			resoult.setInfo("请输入用户名！");
			return resoult;
		}
		if(StringUtils.isEmpty(strPassword)){
			resoult.setInfo("请输入密码！");
			return resoult;
		}
		
		resoult = this.systemUserService.loginIdIsExit(strLoginId);
		if(!resoult.isSuccess()){
			return resoult;
		}

		resoult=systemUserService.insert(user);
		return resoult;
	}
	
	/**
	 * 用户帐号是否已存在
	 * @author cheng_bo
	 * @throws IOException 
	 * @date 2015年5月26日 10:23:42
	 */
	@RequestMapping("loginIdIsExit")
	@ResponseBody
	public Result loginIdIsExit(SystemUser user){
		String strLoginId=user.getStrUserId();
		Result resoult=new Result();
		resoult.setCode(-1);
		
		if(!StringUtils.hasText(strLoginId))
		{
			resoult.setInfo("请输入用户帐号！");
			return resoult;
		}
		
		resoult = this.systemUserService.loginIdIsExit(strLoginId);
		if(!resoult.isSuccess()){
			return resoult;
		}
		
		resoult.setCode(0);
		resoult.setInfo("帐号可以使用！");
		return resoult;
	}
	
}