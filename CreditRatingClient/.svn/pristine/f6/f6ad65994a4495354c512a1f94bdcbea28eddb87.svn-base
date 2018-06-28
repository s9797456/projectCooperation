package com.credit.modelvo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.credit.model.privilege.C_SystemPrivilegeKey;

public class PermissionTag extends TagSupport {

	private static final long serialVersionUID = -8052955586897827778L;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PermissionTag.class);
	
	private String model;
	private String privilege;

	@SuppressWarnings("unchecked")
	public int doStartTag(HttpServletRequest request) throws JspException {
		boolean result = false;
		List<C_SystemPrivilegeKey> cspks = (List<C_SystemPrivilegeKey>) request.getSession().getAttribute(SessionName.PRIVILEGE);
		C_SystemPrivilegeKey systemPrivilege = new C_SystemPrivilegeKey(this.model,this.privilege);
		if(cspks.contains(systemPrivilege)){
			result=true;
		}
    	if(result){
    		//logger.info("页面功能通过:"+this.module+"模块|"+this.privilege+"功能");
    	}else{
    		//logger.error("页面功能屏蔽:"+this.module+"模块|"+this.privilege+"功能");
    	}
		return result?EVAL_BODY_INCLUDE:SKIP_BODY;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}


}
