package com.credit.util.properties;

import java.util.Properties;


public class BusinessUtil {
	private static Properties business = new Properties();
	static{
		try {
			business.load(BusinessUtil.class.getClassLoader().getResourceAsStream("com/config/business.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getMsg(String constant){
		return  business.get(constant).toString().trim();
	}
	
}
