package com.credit.util.html2pdf.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.credit.util.properties.BusinessUtil;

public class Constant{
	
	public static HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	public static String dir=request.getSession().getServletContext().getRealPath("");
	public static String fontPath=BusinessUtil.getMsg("templateFontUrl");
	public static String root=BusinessUtil.getMsg("root");
	//字体路径
	public static String Font_PATH=dir+root+fontPath;
	//public static String Font_PATH="C:/test/fonts/";
	/*统计图路径*/
	public static String Image_PATH="C:/test/images/";
	
}
