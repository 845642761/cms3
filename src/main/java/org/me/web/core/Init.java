package org.me.web.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.me.core.common.alert.StatusPool;
import org.me.web.core.common.system.CmsInfo;


/**
 * 启动时初始化配置
 * @author cheng_bo
 * @date 2015年5月26日 11:26:45
 */
public class Init implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();
		
		CmsInfo cmsInfo = new CmsInfo();
		cmsInfo.setTitle("cms");
		cmsInfo.setTheme("new");
		application.setAttribute("cmsInfo", cmsInfo);
		
		application.setAttribute("theme", "new");
		
		String path=application.getRealPath("/");//程序路径
		StatusPool statusPool = new StatusPool();
		path += "WEB-INF/config/msg_zh_CN.properties";
		statusPool.initStatus(path);
	}
	
}
