package com.credit.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * @title http跨域请求
 * @author Administrator   2017-12-14
 * @desc
 */
public class CorsFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (request.getHeader("Access-Control-Request-Method") != null&& "OPTIONS".equals(request.getMethod())) {
	           // CORS "pre-flight" request
	           response.addHeader("Access-Control-Allow-Origin","*");
	           response.addHeader("Access-Control-Allow-Methods", "GET,POST, PUT, DELETE, OPTIONS");
	           response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, x-requested-with, sid, mycustom,smuser");
	           response.addHeader("Access-Control-Max-Age","1800");//30 min
	       }
		chain.doFilter(request, response);
	    }
	
}
