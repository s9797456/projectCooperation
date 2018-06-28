package com.credit.bean.vo.member;


public enum Gender {
	MAN{
		public String getName(){return "男";}
	},
	WOMEN{
		public String getName(){return "女";}
	};
	public abstract String getName();
	
	public static Gender getGender(String genderName) {
		if(Gender.MAN.getName().equals(genderName)) {
			return Gender.MAN;
		}
		if(Gender.WOMEN.getName().equals(genderName)) {
			return Gender.WOMEN;
		}
		return null;
	}
}