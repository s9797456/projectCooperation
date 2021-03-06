package com.credit.filter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.credit.model.security.IPLock;
import com.credit.service.security.IPLockService;
import com.credit.util.WebUtil;
import com.credit.util.security.DateUtil;
import com.credit.util.security.SecurityUtil;
import com.credit.util.security.UserAgentUtil;



public class AntiSqlInjectionfilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(AntiSqlInjectionfilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest args0, ServletResponse args1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)args0;
		HttpServletResponse response=(HttpServletResponse)args1;
		
		 //获得所有请求参数名
        Enumeration<?> params = request.getParameterNames();
        String contextPath = request.getContextPath();
        String URI=request.getRequestURI();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = request.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //定时关闭系统
        if(SecurityUtil.getMsg("EnableOpenTime").equals("1")){
			if(DateUtil.isClose(SecurityUtil.getMsg("OpenTime"))){
				request.setAttribute("error", "001");
				request.setAttribute("msg", "系统已关闭，关闭时段为"+DateUtil.getCloseTime(SecurityUtil.getMsg("OpenTime")));
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				//response.sendRedirect(contextPath + "/WEB-INF/Page/error.jsp");
				//response.setCharacterEncoding("UTF-8"); 
	        	//response.sendError(HttpStatus.UNAUTHORIZED.value(),"您已经太长时间没有操作,请刷新页面");
			}else{
				//System.out.println("============================SQL"+sql);
		        //有sql关键字，跳转到error.html
		        //是否开启sql注入过滤功能;0关闭；1开启
		        if(SecurityUtil.getMsg("isOpenSqlInChar").equals("1")){
		        	System.out.println(URI);
		        	System.out.println(sql);
		        	 if(!uriValidate(URI)){
		             	if (sqlValidate(sql)) {
		                     // throw new IOException("您发送请求中的参数中含有非法字符");
		                      //String ip = req.getRemoteAddr();
		             		logger.error( "sql注入："+sql);
		             		if(SecurityUtil.getMsg("WriteSqlIn").equals("1")){
		             			System.out.println("记录sql注入信息");
		             			log(sql,request);
		             			//是否开启IP注入
		             			if(SecurityUtil.getMsg("AttackIPLock").equals("1")){
		             				RecordIP(request);
		             			}
		             		}
		             		request.setAttribute("error", "501");
		    				request.setAttribute("msg", "SQL注入");
		    				request.getRequestDispatcher("/error.jsp").forward(request, response);
		                  } else {
		                      chain.doFilter(args0,args1);
		                  }
		             }else{
		             	logger.info( "屏蔽sql注入的方法："+URI);
		             	 chain.doFilter(args0,args1);
		             }
		        }else{
		        	chain.doFilter(args0,args1);
		        }
          	}
		} else{
			//System.out.println("============================SQL"+sql);
	        //有sql关键字，跳转到error.html
	        //是否开启sql注入过滤功能;0关闭；1开启
	        if(SecurityUtil.getMsg("isOpenSqlInChar").equals("1")){
	        	 if(!uriValidate(URI)){
	             	if (sqlValidate(sql)) {
	                     // throw new IOException("您发送请求中的参数中含有非法字符");
	                      //String ip = req.getRemoteAddr();
	             		logger.error( "sql注入："+sql);
	             		if(SecurityUtil.getMsg("WriteSqlIn").equals("1")){
	             			System.out.println("记录sql注入信息");
	             			log(sql,request);
	             			//是否开启IP注入
	             			if(SecurityUtil.getMsg("AttackIPLock").equals("1")){
	             				RecordIP(request);
	             			}
	             		}
	             		System.out.println("错误501：sql注入");
	             		request.setAttribute("error", "501");
	    				request.setAttribute("msg", "SQL注入");
	    				request.getRequestDispatcher("/error.jsp").forward(request, response);
	                  } else {
	                      chain.doFilter(args0,args1);
	                  }
	             }else{
	             	logger.info( "屏蔽sql注入的方法："+URI);
	             	 chain.doFilter(args0,args1);
	             }
	        }else{
	        	chain.doFilter(args0,args1);
	        }
      	
		}
    }
	
	//效验sql注入
	protected static boolean sqlValidate(String str) {
	    str = str.toLowerCase();//统一转为小写
	  /*  String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
	    		"char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
	    		"table|from|grant|use|group_concat|column_name|" +
	            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
	            "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加*/
	    String badStr=SecurityUtil.getMsg("SqlInChar");
	    String[] badStrs = badStr.split("\\|");
	    for (int i = 0; i < badStrs.length; i++) {
	        if (str.indexOf(badStrs[i]) >= 0) {
	           	System.out.println("SqlInChar："+badStrs[i]);
	            return true;
	        }
	    }
	    return false;
	}
	
	//效验屏蔽sql注入
	protected static boolean uriValidate(String str) {
	    str = str.toLowerCase();//统一转为小写
	    String uriStr=SecurityUtil.getMsg("NotSqlInChar");
	    if(!uriStr.equals("")){
	    	String[] uriStrs = uriStr.split(";");
		    for (int i = 0; i < uriStrs.length; i++) {
		        if (str.indexOf(uriStrs[i]) >= 0) {
		            return true;
		        }
		    }
	    }
	    return false;
	}
	
	@SuppressWarnings("deprecation")
	public void log(String sql,HttpServletRequest request) {
		System.out.println("记录开始");
		String Agent = request.getHeader("User-Agent");
		//得到用户的浏览器名
		String userbrowser = UserAgentUtil.getBrowser(Agent);
		//得到用户的操作系统名
		String useros = UserAgentUtil.getOS(Agent);
		PrintWriter out = null;
		try{
			out = new PrintWriter(new FileOutputStream(SecurityUtil.getMsg("RecordAttackTXTUrl"),true));
			out.println("=================Record Is Run================");
			out.println("Date：：-->"+new java.util.Date().toLocaleString());
			out.println("IP：：-->"+WebUtil.getIpAddress(request));
			out.println("URI：：-->"+request.getRequestURI());
			out.println("SQL inject：：-->"+sql);
			out.println("User Browser：：-->"+userbrowser);
			out.println("User Os：：-->"+useros);
			out.println("=================Record Is Over================");
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			out.close();
		}
		System.out.println("记录结束");
	}
	public void RecordIP(HttpServletRequest request){
		IPLockService<?> ipLockService = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(IPLockService.class);
		String IP=WebUtil.getIpAddress(request);
		List<IPLock>ips=ipLockService.selectByIP(IP);
		if(ips.isEmpty()){
			IPLock ip=new IPLock();
			ip.setUuid(UUID.randomUUID().toString().replace("-", ""));
			ip.setAddtime(new Date());
			ip.setStatue(1);
			ip.setIsforever(0);
			ip.setIslimit(0);
			ip.setLockdate(24);
			ip.setIp(IP);
			ipLockService.insert(ip);
		}
	}
}