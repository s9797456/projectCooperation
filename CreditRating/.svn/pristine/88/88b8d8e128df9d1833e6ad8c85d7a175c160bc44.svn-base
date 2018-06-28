package com.credit.util;

import java.util.Properties;

public class SaveFilePathUtil {
	private static Properties saveFilePathDesc = new Properties();
	static {
		try {
			saveFilePathDesc.load(SaveFilePathUtil.class.getClassLoader()
					.getResourceAsStream("saveFilePathDescription.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到相应的等级描述
	 * 
	 * @return
	 */
	public static String saveFilePathDesc(String adr) {
		String value="";
		if(saveFilePathDesc.get(adr)!=null){
			value =saveFilePathDesc.get(adr).toString().trim();
		}
		return value;
	}
}
