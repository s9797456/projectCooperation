/**
 * 
 */
package com.credit.filter;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @ClassName DelegatingServletProxy
 * @Description TODO
 * @author hzhu
 * @date 2015-11-6
 */
public class DelegatingServletProxy extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String targetBean;
	private Servlet proxy;

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		proxy.service(arg0, arg1);
	}

	@Override
	public void init() throws ServletException {
		this.targetBean = getServletName();
		getServletBean();
		proxy.init(getServletConfig());
	}

	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet) wac.getBean(targetBean);
	}
}