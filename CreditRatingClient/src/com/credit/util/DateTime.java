package com.credit.util;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class DateTime {
    public static String getFormatedTime(Timestamp date) {
        if (date == null) return null;
        return getFormatedDateTime(date).substring(11);
    }

    public static String getFormatedDateTime(Timestamp date) {
        if (date == null) return null;
        SimpleDateFormat lFormatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return lFormatTimestamp.format(date);
    }

    public static String getCurTimeStamp()
    {
    	SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	java.sql.Timestamp lTime =  (java.sql.Timestamp.valueOf(lSimpleDateFormat.format(new java.util.Date())));
    	return getFormatedDateTime(lTime);
    }
    
    public static String getDirectory()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	return sdf.format(new java.util.Date());
    }
    
    public static Timestamp getCurrentTimeStamp()
    {
    	SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return (java.sql.Timestamp.valueOf(lSimpleDateFormat.format(new java.util.Date())));
    }
    
    public static String getFormatedDate(Timestamp date) {
        if (date == null) return null;
        SimpleDateFormat lFormatTimestamp = new SimpleDateFormat("yyyy-MM-dd");
        return lFormatTimestamp.format(date);
    }    
    
    public static String getYear()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
    	return sdf.format(new java.util.Date());
    }   
    
    public static String getOptionalYear(int value)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");      
        
        Calendar lastYear = Calendar.getInstance();    
        lastYear.roll(Calendar.YEAR,value);
        return sdf.format(lastYear.getTime());
    }
    public static void main(String[] args) {    
    	
    	System.out.println(DateTime.getOptionalYear(2));
    }

    
}