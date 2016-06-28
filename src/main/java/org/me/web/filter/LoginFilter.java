package org.me.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

/**
 * 登录拦截器
 * @author cheng_bo
 * @date 2015年6月9日 16:03:02
 */
public class LoginFilter implements Filter {
	private Logger logger = Logger.getLogger(LoginFilter.class);
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain fc) throws IOException, ServletException {
		logger.debug("received a request!");
		fc.doFilter(sRequest, sResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
