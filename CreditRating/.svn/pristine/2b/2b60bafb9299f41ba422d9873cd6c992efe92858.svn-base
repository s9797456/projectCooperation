package com.credit.util;

import java.text.SimpleDateFormat;

public class CODEUtil {
	public static String getCODE(int x){
		String A="SZXYXH";
		String D=null;
		//b的大小取决于x的取值范围
		/*    	x:  0-26*999-1
		 * 
		 * 
		 * */

		if(x<26*999){
			int b=x/999+1;
			int c=x%999+1;
			char alp=(char) (b+64);
			D=alp+autoGenericCode(c,3);

		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMdd");  
		java.util.Date date=new java.util.Date();  
		String CODE=A+"-"+sdf.format(date)+"-"+D;
		System.out.println(CODE);

		return CODE;
	}
	  private static String autoGenericCode(int code, int num) {  
	        String result = "";  
	        result = String.format("%0" + num + "d", code);  
	        return result;  
	    }  
}
