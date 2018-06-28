package com.credit.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@SuppressWarnings("rawtypes")
	private static HashMap hUserName = new HashMap();
	@SuppressWarnings("rawtypes")
	private static HashMap clientUserName = new HashMap();
	public void sessionCreated(HttpSessionEvent arg0) {}
	public void sessionDestroyed(HttpSessionEvent arg0) {}
	  /*
	  * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	  * @param sUserName String-登录的用户名称
	  * @return boolean-该用户是否已经登录过的标志
	  */
	  @SuppressWarnings("unchecked")
	public static boolean isAlreadyEnter(HttpSession session,String sUserName){
		  boolean flag = false;
		  //如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
		  //if(hUserName.containsKey(session.getId())&&hUserName.containsValue(sUserName)){
		  if(hUserName.containsValue(sUserName)){
			flag = true;
			//遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
			@SuppressWarnings("rawtypes")
			Iterator iter = hUserName.entrySet().iterator();
			
			while (iter.hasNext()){
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry)iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				//if(((String)key ).equals(session.getId())&&((String)val ).equals(sUserName)){
				if(((String)val ).equals(sUserName)){
					//iter.remove();
					hUserName.remove(key);
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					System.out.println("移除成功");
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					break;
				}
			}
			hUserName.put(session.getId(),sUserName);//添加现在的sessionID和username
		  }else{
			  flag = false;
			  hUserName.put(session.getId(),sUserName);
		  }
		  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		  System.out.println("flag = " + flag);
		  System.out.println("hUserName = " + hUserName);
		  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	  return flag;
	  }

	  /*
	  * isOnline-用于判断用户是否在线
	  *  @param session HttpSession-登录的用户名称
	  *  @return boolean-该用户是否在线的标志
	  */

	  public static boolean isOnline(HttpSession session,String sUserName){
		  boolean flag = true;
		  //if(hUserName.containsKey(session.getId())&&hUserName.containsValue(sUserName)){
		  if(hUserName.containsKey(session.getId())){
			  flag = true;
		  }else{
			  flag = false;
		  }
		  
		  System.out.println("------------------------------------------");
		  System.out.println("flag = " + flag);
		  System.out.println("hUserName = " + hUserName);
		  System.out.println("------------------------------------------");
		  return flag;
	  }
	  
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static boolean isClientAlreadyEnter(HttpSession session,String sUserName){
			  boolean flag = false;
			  //如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
			  if(clientUserName.containsKey(session.getId())&&clientUserName.containsValue(sUserName)){
				flag = true;
				//遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
				Iterator iter = clientUserName.entrySet().iterator();
				Map.Entry entry;
				while (iter.hasNext()){
					entry = (Map.Entry)iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					if(((String)key ).equals(session.getId())&&((String)val ).equals(sUserName)){
//						hUserName.remove(key);//产生java.util.HashMap$HashIterator.nextEntry错误
						iter.remove();
						break;
					}
				}
				clientUserName.put(session.getId(),sUserName);//添加现在的sessionID和username
			  }else{
				  flag = false;
				  clientUserName.put(session.getId(),sUserName);
			  }
		  return flag;
		  }


}
