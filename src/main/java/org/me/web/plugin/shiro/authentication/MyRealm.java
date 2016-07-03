package org.me.web.plugin.shiro.authentication;

import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.me.core.common.Constant;
import org.me.web.plugin.shiro.service.ISecurityService;
import org.me.web.system.user.entity.SystemUser;
import org.me.web.system.user.service.ISystemUserService;

public class MyRealm extends AuthorizingRealm {
	@Resource
	private ISystemUserService loginUserService;
	@Resource
	private ISecurityService securityService;

	/**
	 * 为当前登录的Subject授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String strLoginId = (String)principals.getPrimaryPrincipal();
		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		Set<String> roles=securityService.getRoles(strLoginId);
		Set<String> perms=securityService.getPermissions(roles);
		auth.setStringPermissions(perms);//权限加入AuthorizationInfo认证对象
		return auth;
	}

	/**
	 * 验证当前登录的Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		SystemUser loginUser=null;
		if(null == getSession(Constant.LOGINUSER)){
			// 实际上这个authcToken是从UserController里面subject.login(token)传过来的
			// 两个token的引用都是一样的
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			loginUser=loginUserService.getByLoginName(token.getUsername());
			if(loginUser == null){
				throw new UnknownAccountException();//没找到帐号			
			}
			if(loginUser.getnState() != 1) {
	            throw new LockedAccountException(loginUser.getnState()+""); //帐号锁定
	        }
		}else {
			loginUser=(SystemUser) getSession(Constant.LOGINUSER);
		}
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
				loginUser.getStrUserId(), loginUser.getStrPassword(),getName());
		this.setSession(Constant.LOGINUSER, loginUser);
		return authcInfo;
	}

	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();   
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }
    
    private Object getSession(Object key){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();   
            if(null != session)
                return session.getAttribute(key);
            return null;
        }
        return null;
    } 
}