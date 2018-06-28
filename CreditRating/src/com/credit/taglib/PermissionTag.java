package com.credit.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.credit.bean.member.User;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.util.WebUtil;


public class PermissionTag extends TagSupport {

	private static final long serialVersionUID = -8052955586897827778L;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PermissionTag.class);
	
	private String model;
	private String privilege;

	public int doStartTag(HttpServletRequest request) throws JspException {
		boolean result = false;
		User user = WebUtil.getUser(request);
		SystemPrivilege systemPrivilege = new SystemPrivilege(new SystemPrivilegePK(this.model,this.privilege));
    	for (PrivilegeGroup group : user.getGroups()) {
            if(group.getSystemPrivileges().contains(systemPrivilege)){
            	result=true;
            	break;
            }
    	}
    	if(user.getSystemPrivileges().contains(systemPrivilege)) result=true; 
    	if(result){
    		//logger.info("页面功能通过:"+this.module+"模块|"+this.privilege+"功能");
    	}else{
    		//logger.error("页面功能屏蔽:"+this.module+"模块|"+this.privilege+"功能");
    	}
		return result?EVAL_BODY_INCLUDE:SKIP_BODY;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the privilege
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege the privilege to set
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}


}
