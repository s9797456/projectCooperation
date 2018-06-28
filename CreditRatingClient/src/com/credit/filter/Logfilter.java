package com.credit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.MDC;

import com.credit.model.member.Customer;
import com.credit.modelvo.SessionName;
import com.credit.util.WebUtil;

public class Logfilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if (customer == null) {
			MDC.put("userName", WebUtil.getIpAddress(request));
		} else {
			MDC.put("userName", customer.getUsername());
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
	}


}
