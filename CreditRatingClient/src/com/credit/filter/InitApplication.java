/**
 * 
 */
package com.credit.filter;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Component;

import com.credit.service.member.OrganizationService;

@Component
public class InitApplication extends HttpServlet {
	
	@Resource
	private OrganizationService organizationService;
	
	private static final long serialVersionUID = 1L;

	public InitApplication() {
		super();
	}

	public void init(ServletConfig servletConfig) throws ServletException {
		ServletContext application = servletConfig.getServletContext();
		application.setAttribute("organizations",organizationService.selectByAll());
	}
}
