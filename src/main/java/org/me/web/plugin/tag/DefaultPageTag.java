package org.me.web.plugin.tag;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import org.me.plugin.paging.core.Pagination;
import org.me.plugin.paging.io.QueryInfo;

public class DefaultPageTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(DefaultPageTag.class);

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		QueryInfo queryInfo = getQueryInfoFormRequest(request);
		int totalPage = (queryInfo.getTotalRows()-1)/queryInfo.getNumPerPage() +1;
		StringBuffer sb = new StringBuffer();
		sb.append("共");
		sb.append(queryInfo.getTotalRows());
		sb.append("条记录 &nbsp;&nbsp;当前第");
		sb.append(queryInfo.getStartIndex()/queryInfo.getNumPerPage() + 1);
		sb.append("页&nbsp;共");
		sb.append(totalPage);
		sb.append("页&nbsp;每页");
		sb.append(queryInfo.getNumPerPage());
		sb.append("行");
		
		sb.append(queryInfo.getUrl());
		sb.append("?");
		sb.append(queryInfo.getParam());
		try {
			this.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error("DefaultPageTag erroe : ", e);
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 从request中获取QueryInfo
	 * @param request
	 * @return QueryInfo
	 */
	private QueryInfo getQueryInfoFormRequest(HttpServletRequest request) {
		QueryInfo queryInfo = new QueryInfo();
		queryInfo.setUrl(request.getHeader("Referer"));
		
		if(request.getAttribute(Pagination.PAGINATION) != null){
			Pagination pagination = (Pagination) request.getAttribute(Pagination.PAGINATION);
			queryInfo.setStartIndex(pagination.getStartIndex());
			queryInfo.setTotalRows(pagination.getTotalRows());
		}
		
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
			queryInfo.setParam(sb.substring(0, sb.length() - 1));
        }
		return queryInfo;
	}
}
