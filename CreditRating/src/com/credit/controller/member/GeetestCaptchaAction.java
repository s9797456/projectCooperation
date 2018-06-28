/**
 * 
 */
package com.credit.controller.member;

import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.util.geetest.GeetestLib;
import com.credit.util.properties.GlobalUtil;

@Controller
@RequestMapping("/geetest")
public class GeetestCaptchaAction {
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request,HttpServletResponse response) throws Exception {
		GeetestLib gtSdk = new GeetestLib(GlobalUtil.getMsg("geetest_id"), GlobalUtil.getMsg("geetest_key"),true);

		String resStr = "{}";
		
		//自定义userid
		String userid = UUID.randomUUID().toString();

		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(userid);
		
		//将服务器状态设置到session中
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//将userid设置到session中
		request.getSession().setAttribute("userid", userid);
		
		resStr = gtSdk.getResponseStr();

		PrintWriter out = response.getWriter();
		out.println(resStr);

		return null;
	}
	
	
}
