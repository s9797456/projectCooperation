package com.credit.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.credit.bean.member.User;
import com.credit.util.WebUtil;




public class UserInterceptor implements HandlerInterceptor {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// 请求的路径
		String contextPath = request.getContextPath();

		User user = WebUtil.getUser(request);
		if (user == null) {
			response.sendRedirect(contextPath + "/user/logonUI.do");
			MDC.put("userName", WebUtil.getIpAddress(request));
			return false;
		} else {
			MDC.put("userName", user.getUserName());
			return true;
		}

	}
	
}
