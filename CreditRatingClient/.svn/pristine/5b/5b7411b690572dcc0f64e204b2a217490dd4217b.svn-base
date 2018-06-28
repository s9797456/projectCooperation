package com.credit.util.security;

import java.util.Properties;


public class SecurityUtil {
	private static Properties security = new Properties();
	static{
		try {
			security.load(SecurityUtil.class.getClassLoader().getResourceAsStream("com/config/security.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getMsg(String constant){
		return  security.get(constant).toString().trim();
	}
	
}
