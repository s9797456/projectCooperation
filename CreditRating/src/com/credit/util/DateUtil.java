package com.credit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {


	/**

	 * 判断时间是否在时间段内 *

	 * @param date

	 * 当前时间 yyyy-MM-dd HH:mm:ss

	 * @param strDateBegin

	 * 开始时间 00:00:00

	 * @param strDateEnd

	 * 结束时间 00:05:00

	 * @return

	 */

	public static boolean isInDate(Date date, String strDateBegin,

			String strDateEnd) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String strDate = sdf.format(date);

		// 截取当前时间时分秒
		System.out.println(strDate);
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		System.out.println("时："+strDateH);
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		System.out.println("分："+strDateM);
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		System.out.println("秒："+strDateS);
		// 截取开始时间时分秒

		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		System.out.println("时1："+strDateBeginH);
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		System.out.println("分1："+strDateBeginM);
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		System.out.println("秒1："+strDateBeginS);
		// 截取结束时间时分秒

		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		System.out.println("时2："+strDateEndH);
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		System.out.println("分2："+strDateEndM);
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		System.out.println("秒2："+strDateEndS);
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {

			// 当前时间小时数在开始时间和结束时间小时数之间

			if (strDateH > strDateBeginH && strDateH < strDateEndH) {

				return true;

				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间

			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM

					&& strDateM <= strDateEndM) {

				return true;

				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间

			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM

					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {

				return true;

			}

			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数

			else if (strDateH >= strDateBeginH && strDateH == strDateEndH

					&& strDateM <= strDateEndM) {

				return true;

				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数

			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH

					&& strDateM == strDateEndM && strDateS <= strDateEndS) {

				return true;

			} else {

				return false;

			}

		} else {

			return false;

		}

	}

	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00)
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();
			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00")) {
				args[1] = "24:00";
			}
			if (end < start) {
				if (now >= end && now < start) {
					return false;
				} else {
					return true;
				}
			} 
			else {
				if (now >= start && now < end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}

	}
	/*public static void main(String[] args) {
	    System.out.println(DataUtil.isInTime("20:00-01:00", "01:00"));
	    System.out.println(DataUtil.isInTime("20:00-01:00", "00:00"));
	    System.out.println(DataUtil.isInTime("20:00-01:00", "03:00"));
	    System.out.println();
	    System.out.println(DataUtil.isInTime("20:00-23:00", "03:00"));
	    System.out.println(DataUtil.isInTime("20:00-23:00", "22:00"));
	    System.out.println(DataUtil.isInTime("20:00-23:00", "18:00"));
	    System.out.println(DataUtil.isInTime("20:00-23:00", "20:00"));
	    System.out.println(DataUtil.isInTime("20:00-23:00", "23:00"));
	    System.out.println();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    String time="08:00-24:00";
	    System.out.println(DataUtil.isInTime(time, sdf.format(new Date())));
	}*/
	/*	
	public static void main(String[] args){

		String strDateBegin="08:00:00";
		String strDateEnd="24:00:00";
		System.out.println(new Date());
       boolean exists=isInDate(new Date(), strDateBegin, strDateEnd);
       System.out.println(exists);
	}*/
	public static String getTenMinuteTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		ca.add(Calendar.MINUTE,-10);
		return sdf.format(ca.getTime());
	}
	public static List<String> getLastTime(){
		List<String> dates=new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		for(int i=1;i<=15;i++){
			ca.setTime(new Date()); 	//设置时间为当前时间 
			ca.add(Calendar.DATE, -i);
			Date last = ca.getTime(); 
			dates.add(sf.format(last));
		}
		//前一年ca.add(Calendar.YEAR, -1); 
		//前一月ca.add(Calendar.MONTH, -1)，
		//前一天ca.add(Calendar.DATE, -1)
		return dates;
	}
	public static String addDate(Date date, int x) 
	{ 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制 
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制  
		if (date == null) return ""; 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.HOUR_OF_DAY, x);//24小时制 
		//cal.add(Calendar.HOUR, x);12小时制 
		date = cal.getTime(); 
		cal = null; 
		return format.format(date); 
	} 	  

	public static boolean judgeInDate(Date date, String strDateBegin,String strDateEnd) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean start=false;
		boolean end=false;
		try {
			start=date.after(sdf.parse(strDateBegin));
			end=date.before(sdf.parse(strDateEnd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(start){
			if(end){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public static boolean isClose(String opentimes) {
		boolean isclose=false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		int H = Integer.parseInt(date.substring(11, 13));
		System.out.println("时："+H);
		String[] times = opentimes.split("\\|");
		if(times[H].equals("0")){
			isclose= true;
		}
		return isclose;
	}

	public static String getCloseTime(String opentimes) {
		List<Integer> open=new ArrayList<Integer>();
		String[] times = opentimes.split("\\|");
		for(int i=0;i<times.length;i++){
			if(times[i].equals("0")){
				open.add(i);
			}
		}
		return open.toString();
	}
	public static long getIntervalTime(Date start,Date end){
		return (end.getTime() - start.getTime()) /1000;

	}
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate)  
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			 smdate=sdf.parse(sdf.format(smdate));
			 bdate=sdf.parse(sdf.format(bdate)); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
}
