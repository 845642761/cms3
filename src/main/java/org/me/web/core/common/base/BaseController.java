package org.me.web.core.common.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.me.core.common.Constant;
import org.me.plugin.paging.core.Pagination;
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
	
	public void setPagination(Pagination pagination) {
		if(pagination == null)
			return;
		HttpServletRequest request = getRequest();
		request.setAttribute("url", request.getRequestURL() + "?" + getParamsFormRequest(request));
		request.setAttribute("totalRows", pagination.getTotalRows());
		request.setAttribute("numPerPage", pagination.getNumPerPage());
		request.setAttribute("startIndex", pagination.getStartIndex());
	}
	
	private String getParamsFormRequest(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> parameters = request.getParameterNames();
		while (parameters.hasMoreElements()) {
			String name = parameters.nextElement();
			if(Pagination.STARTINDEX.equals(name)){
				continue;
			}
			if(Pagination.TOTALROWS.equals(name)){
				continue;
			}
			
			String[] vals = request.getParameterValues(name);
			for (int i = 0; vals != null && i < vals.length; i++) {
				sb.append(name);
				sb.append("=");
				sb.append(vals[i]);
				sb.append("&");
			}
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return "";
	}
}
