package com.credit.util.security;



import java.io.IOException;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

/**
 * Created by Edward on 2016/7/1.
 */
public class UserAgentUtil {

/*    static UASparser uasParser = null;

    // 初始化uasParser对象
    static {
        try {
            uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static String getBrowser(String agent){
    	String Browser="";
    	 try {
    		 UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
             UserAgentInfo userAgentInfo = uasParser.parse(agent);
             Browser=userAgentInfo.getUaFamily();

         } catch (IOException e) {
             e.printStackTrace();
         }
		return Browser;
    }
    
    public static String getOS(String agent){
    	String OS="";
    	 try {
    		 UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
             UserAgentInfo userAgentInfo = uasParser.parse(agent);
             OS=userAgentInfo.getOsName();

         } catch (IOException e) {
             e.printStackTrace();
         }
		return OS;
    }
  /*  public static void main(String[] args)
    {
        String str = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
        System.out.println(str);
        try {
            UserAgentInfo userAgentInfo = UserAgentUtil.uasParser.parse(str);
            System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
            System.out.println("操作系统："+userAgentInfo.getOsName());//
            System.out.println("浏览器名称："+userAgentInfo.getUaFamily());//
            System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
            System.out.println("设备类型："+userAgentInfo.getDeviceType());
            System.out.println("浏览器:"+userAgentInfo.getUaName());
            System.out.println("类型："+userAgentInfo.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
