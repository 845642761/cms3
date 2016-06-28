package org.me.web.system.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.me.core.common.Result;
import org.me.web.core.common.base.BaseController;
import org.me.web.plugin.shiro.entity.Permission;
import org.me.web.plugin.shiro.service.ISecurityService;
import org.me.web.system.user.entity.SystemUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台管理Controller
 * @author cheng_bo
 * @date 2015年6月9日 14:18:48
 */
@Controller
@RequestMapping("/system/")
public class SystemController extends BaseController {
	
	@Resource
	private ISecurityService securityService;
	
	@RequestMapping("main")
	public ModelAndView main (HttpServletRequest request) {
		Result resoult=new Result();
		resoult.setCode(-1);
		ModelAndView mav = new ModelAndView("/system/core/main");
		try {
			SystemUser u = getLoginUserId();
			Permission p=new Permission();
			p.setnState(0);
			p.setnType(0);
			ArrayList<Permission> menuList=securityService.getPermissionsList(p, securityService.getRoles(u.getStrUserId()));
			
			ArrayList<Permission> menuOneLevel = new ArrayList<>();
			HashMap<String,ArrayList<Permission>> menuTwoLevel = new HashMap<>();
			
			for (Permission pm : menuList) {
				String id = pm.getStrPid();
				if("0".equals(id)){
					menuOneLevel.add(pm);
					if(!menuTwoLevel.containsKey(id)){
						ArrayList<Permission> al = new ArrayList<>();
						menuTwoLevel.put(id, al);
					}
				}else {
					ArrayList<Permission> al = null;
					if(!menuTwoLevel.containsKey(id)){
						al = new ArrayList<>();
					}else {
						al = menuTwoLevel.get(id);
					}
					al.add(pm);
					menuTwoLevel.put(id, al);
				}
			}
			mav.addObject("menuOneLevel",menuOneLevel);
			mav.addObject("menuTwoLevel",menuTwoLevel);
		} catch (Exception e) {
			e.printStackTrace();
			resoult.setInfo("菜单加载失败");
			resoult.setCode(-1);
			mav.addObject(resoult);
		}
		return mav;
	}
	
	/**
	 * 后台首页页面
	 * @author: chengbo
	 * @date: 2015年11月20日 11:23:01
	 */
	@RequestMapping("index")
	public ModelAndView index () {
		return new ModelAndView("/system/core/index");
	}
}
