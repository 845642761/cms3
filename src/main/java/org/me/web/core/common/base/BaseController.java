package org.me.web.core.common.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.me.core.common.Constant;
import org.me.web.system.user.entity.SystemUser;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder binder)  {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	}
	
	public HttpServletResponse getResponse() {
		return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	public HttpSession getSession(boolean isCreate) {
		return getRequest().getSession(isCreate);
	}
	
	public SystemUser getLoginUserId() {
		HttpSession session = getSession(false);
		if(session == null || session.getAttribute(Constant.LOGINUSER) == null)
			return null;
		return (SystemUser) session.getAttribute(Constant.LOGINUSER);
	}
}
