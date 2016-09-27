package org.me.web.plugin.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import org.me.plugin.paging.config.DefaultPageingConfig;

public class DefaultPageTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(DefaultPageTag.class);
	
	private String url;
	private int totalRows;
	private int numPerPage;
	private int startIndex;

	@Override
	public int doStartTag() throws JspException {
		if(numPerPage == 0)
			numPerPage = DefaultPageingConfig.numPerPage;
		int totalPage = (totalRows - 1) / numPerPage + 1;//总页数
		int currentPage = startIndex / numPerPage + 1;//当前页
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pagination'><div class='group'>");
		
		//向前翻页
		sb.append("<a class='item btn start' href = '");
		sb.append(url);
		sb.append("&startIndex=0'></a><a class='item btn prev' href = '");
		sb.append(url);
		sb.append("&startIndex=");
		if(startIndex == 0){
			sb.append(0);
		}else if(startIndex - numPerPage >= 0){
			sb.append(startIndex - numPerPage);
		}else {
			sb.append(0);
		}
		sb.append("'></a>");
		
		//当前页
		//sb.append("<div class='item'>第&nbsp;");
		//sb.append(currentPage);
		//sb.append("&nbsp;页</div>");
		
		//向后翻页
		sb.append("<a class='item btn next' href = '");
		sb.append(url);
		sb.append("&startIndex=");
		if(currentPage == totalPage){
			//如果是最后一页，保持不变
			sb.append(startIndex);
		}else {
			sb.append(startIndex + numPerPage);
		}
		sb.append("'></a><a class='item btn end' href = '");
		sb.append(url);
		sb.append("&startIndex=");
		sb.append((totalPage-1) * numPerPage);
		sb.append("''></a>");
		
		//刷新
		sb.append("<div class='item btn reload'></div>");
		
		//附加信息
		sb.append("&nbsp;&nbsp;共");
		sb.append(totalRows);
		sb.append("条记录 &nbsp;当前第");
		sb.append(currentPage);
		sb.append("页&nbsp;共");
		sb.append(totalPage);
		sb.append("页&nbsp;每页");
		sb.append(numPerPage);
		sb.append("行");
		
		sb.append("</div></div>");
		sb.append(url);
		try {
			this.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error("DefaultPageTag erroe : ", e);
		}
		return EVAL_PAGE;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}
