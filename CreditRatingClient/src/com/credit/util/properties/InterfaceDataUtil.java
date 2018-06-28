package com.credit.util.properties;

import java.util.Properties;


public class InterfaceDataUtil {
	private static Properties business = new Properties();
	static{
		try {
			business.load(InterfaceDataUtil.class.getClassLoader().getResourceAsStream("com/config/interfaceData.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getMsg(String constant){
		return  business.get(constant).toString().trim();
	}
	
}
