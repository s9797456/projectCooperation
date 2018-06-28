package com.credit.util.properties;

import java.util.Properties;

public class ScoreLevelDescUtil {
	private static Properties scoreLevelDesc = new Properties();
	static{
		try {
			scoreLevelDesc.load(ScoreLevelDescUtil.class.getClassLoader().getResourceAsStream("com/config/scoreLevelDescription.properties"));
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
