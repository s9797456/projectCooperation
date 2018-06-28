package com.credit.util.security;

public class IPUtil {
		 

		   /*验证IP是否属于某个IP段

		     *

		     * ipSection    IP段（以'-'分隔）

		     * ip           所验证的IP号码

		     *

		     */

		   public static boolean ipExistsInRange(String ip,String ipSection) {

		        ipSection = ipSection.trim();

		        ip = ip.trim();

		       int idx = ipSection.indexOf('-');

		        String beginIP = ipSection.substring(0, idx);

		        String endIP = ipSection.substring(idx + 1);

		       return getIp2long(beginIP)<=getIp2long(ip) &&getIp2long(ip)<=getIp2long(endIP);

		    }

		   public static long getIp2long(String ip) {

		        ip = ip.trim();

		        String[] ips = ip.split("\\.");

		       long ip2long = 0L;

		       for (int i = 0; i < 4; ++i) {

		            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);

		        }

		       return ip2long;

		    }

		    public static long getIp2long2(String ip) {

		        ip = ip.trim();

		        String[] ips = ip.split("\\.");

		       long ip1 = Integer.parseInt(ips[0]);

		       long ip2 = Integer.parseInt(ips[1]);

		       long ip3 = Integer.parseInt(ips[2]);

		       long ip4 = Integer.parseInt(ips[3]);

		 

		       long ip2long =1L* ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;

		       return ip2long;

		    } 
		    
		    public static boolean isInRange(String network, String mask) {
		        String[] networkips = network.split("\\.");
		        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
		                | (Integer.parseInt(networkips[1]) << 16)
		                | (Integer.parseInt(networkips[2]) << 8)
		                | Integer.parseInt(networkips[3]);
		        int type = Integer.parseInt(mask.replaceAll(".*/", ""));
		        int mask1 = 0xFFFFFFFF << (32 - type);
		        String maskIp = mask.replaceAll("/.*", "");
		        String[] maskIps = maskIp.split("\\.");
		        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
		                | (Integer.parseInt(maskIps[1]) << 16)
		                | (Integer.parseInt(maskIps[2]) << 8)
		                | Integer.parseInt(maskIps[3]);

		        return (ipAddr & mask1) == (cidrIpAddr & mask1);
		    }
		    
		    public static boolean ipIsInNet(String iparea, String ip) {
		        if (iparea == null)
		            throw new NullPointerException("IP段不能为空！");
		        if (ip == null)
		            throw new NullPointerException("IP不能为空！");
		        iparea = iparea.trim();
		        ip = ip.trim();
		        final String REGX_IP = "((25[0-5]|2[0-4]//d|1//d{2}|[1-9]//d|//d)//.){3}(25[0-5]|2[0-4]//d|1//d{2}|[1-9]//d|//d)";
		        final String REGX_IPB = REGX_IP + "//-" + REGX_IP;
		        if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))
		            return false;
		        int idx = iparea.indexOf('-');
		        String[] sips = iparea.substring(0, idx).split("//.");
		        String[] sipe = iparea.substring(idx + 1).split("//.");
		        String[] sipt = ip.split("//.");
		        long ips = 0L, ipe = 0L, ipt = 0L;
		        for (int i = 0; i < 4; ++i) {
		            ips = ips << 8 | Integer.parseInt(sips[i]);
		            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
		            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		        }
		        if (ips > ipe) {
		            long t = ips;
		            ips = ipe;
		            ipe = t;
		        }
		        return ips <= ipt && ipt <= ipe;
		    }

	/*	    public static void main(String[] args){

		       //10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255

		        String ip="10.10.10.116";

		        String ipSection="10.10.1.00-10.10.255.255";

		       boolean exists=ipExistsInRange(ip,ipSection);

		        System.out.println(exists);

		        System.out.println(getIp2long(ip));

		        System.out.println(getIp2long2(ip));
		        
		        System.out.println(isInRange("10.153.48.127", "10.153.48.0/26"));
		        System.out.println(isInRange("10.168.1.2", "10.168.0.224/23"));
		        System.out.println(isInRange("192.168.0.1", "192.168.0.0/24"));
		        System.out.println(isInRange("10.168.0.0", "10.168.0.0/32"));
		        
		        System.out.println(ipIsInNet("10.153.48.127", "10.153.48.0/26"));
		        System.out.println(ipIsInNet("10.168.1.2", "10.168.0.224/23"));
		        System.out.println(ipIsInNet("192.168.0.1", "192.168.0.0/24"));
		        System.out.println(ipIsInNet("10.168.0.0", "10.168.0.0/32"));

		    }*/
		 

		 
}
