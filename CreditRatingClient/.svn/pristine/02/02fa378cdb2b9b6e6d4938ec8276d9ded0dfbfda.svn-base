package com.credit.util.properties;

import java.util.Properties;

public class PerScoreLevelDescUtil {
	private static Properties scoreLevelDesc = new Properties();
	static{
		try {
			scoreLevelDesc.load(PerScoreLevelDescUtil.class.getClassLoader().getResourceAsStream("com/config/perScoreLevelDescription.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到相应的等级描述
	 * @return
	 */
	public static String scoreLevelDesc(String scoreLevel){
		String value ="";
		if(scoreLevel!=null||"".equals(scoreLevel)){
			value = (String)scoreLevelDesc.get(scoreLevel);	
		}
		return value;
	}	
}
