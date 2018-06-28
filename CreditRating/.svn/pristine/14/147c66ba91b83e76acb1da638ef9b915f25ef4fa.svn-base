package com.credit.util.properties;

import java.util.Properties;


public class GlobalUtil {
	private static Properties global = new Properties();
	static{
		try {
			global.load(GlobalUtil.class.getClassLoader().getResourceAsStream("com/config/global.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getMsg(String constant){
		return  global.get(constant).toString().trim();
	}
	
}
