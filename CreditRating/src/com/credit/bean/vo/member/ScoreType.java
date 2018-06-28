package com.credit.bean.vo.member;


public enum ScoreType {
	NORMAL{
		public String getName(){return "正常";}
	},
	BATCH{
		public String getName(){return "批量";}
	};
	public abstract String getName();
	
	public static ScoreType getGender(String genderName) {
		if(ScoreType.NORMAL.getName().equals(genderName)) {
			return ScoreType.NORMAL;
		}
		if(ScoreType.BATCH.getName().equals(genderName)) {
			return ScoreType.BATCH;
		}
		return null;
	}
}