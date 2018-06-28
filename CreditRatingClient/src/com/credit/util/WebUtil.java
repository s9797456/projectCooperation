package com.credit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.credit.model.member.User;



public class WebUtil {
	/**
	 * @title 字符串中是否有中文
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	public static boolean IsChinese(String str) {
		str=new String(str.getBytes());//用GBK编码
        String pattern="[\u4e00-\u9fa5]+";  
        Pattern p=Pattern.compile(pattern);  
        Matcher result=p.matcher(str);
        return result.find(); //是否含有中文字符 
	}
	/**
	 * @title 字符串中是否有空格
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	public static boolean checkBlank(String str) {
		Pattern pattern = Pattern.compile("[\\s]+");
		Matcher matcher = pattern.matcher(str);
		boolean flag = false;
		while (matcher.find()) {
			flag = true;
		}
		return flag;
	}

	
    /**
     * 添加cookie
     * @param response
     * @param name cookie的名称
     * @param value cookie的值
     * @param maxAge cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {        
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge>0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    /**
     * 获取cookie的值
     * @param request
     * @param name cookie的名称
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
    	Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie.getValue();
        }else{
            return null;
        }
    }
    
    protected static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }   
    
/*    public static boolean isNumeric(Object str){ 
  	  Pattern pattern = Pattern.compile("[0-9]*"); 
	  return pattern.matcher((CharSequence) str).matches(); 
	}
  public static boolean isDouble(Object str) {
  	boolean tag = false;
  	final String pattern1 = "^[-\\+]?[,.\\d]*$";
  	final Pattern pattern = Pattern.compile(pattern1);
  	final Matcher mat = pattern.matcher((CharSequence) str);
  	if (!mat.find()){
  		tag = true;
  	}
  	return tag;
  }*/
    
    public static boolean isInteger(String value) {
    	try {
    	Integer.parseInt(value);
    	return true;
    	} catch (NumberFormatException e) {
    	return false;
    	}
    	}


    	public static boolean isDouble(String value) {
    	try {
    	Double.parseDouble( value);
    	if (value.contains("."))
    	return true;
    	return false;
    	} catch (NumberFormatException e) {
    	return false;
    	}
    	}


    	public static boolean isNumber(String value) {
    	return isInteger(value) || isDouble(value);
    	}
    
    
    /**
     * 去除html代码
     * @param inputString
     * @return
     */
    public static String HtmltoText(String inputString) {
        String htmlStr = inputString; //含html标签的字符串
        String textStr ="";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;          
        java.util.regex.Pattern p_ba;
        java.util.regex.Matcher m_ba;
        
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            String patternStr = "\\s+";
            
            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签
         
            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签
            
            p_ba = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
            m_ba = p_ba.matcher(htmlStr);
            htmlStr = m_ba.replaceAll(""); //过滤空格
         
         textStr = htmlStr;
         
        }catch(Exception e) {
                    System.err.println("Html2Text: " + e.getMessage());
        }          
        return textStr;//返回文本字符串
     }
    /**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getLevelByScore(Double preliminaryScore) {
		if (1000 >= preliminaryScore && preliminaryScore >= 700) {
			return "AAA";
		} else if (700 > preliminaryScore && preliminaryScore >= 650) {
			return "AA";
		} else if (650 > preliminaryScore && preliminaryScore >= 600) {
			return "A";
		} else if (600 > preliminaryScore && preliminaryScore >= 550) {
			return "BBB";
		} else if (550> preliminaryScore && preliminaryScore >= 500) {
			return "BB";
		} else if (500 > preliminaryScore && preliminaryScore >= 450) {
			return "B";
		} else if (450 > preliminaryScore && preliminaryScore >= 400) {
			return "CCC";
		} else if (400 > preliminaryScore && preliminaryScore >= 350) {
			return "CC";
		} else if (350 > preliminaryScore) {
			return "C";
		} else {
			return "暂无等级";
		}
	}
}
