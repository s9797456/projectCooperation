package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

//import com.casdata.bean.member.Member;

public class Member2 {
	
	public Member2(){}

	/*public Member2(Member member){
		this.uuid  = member.getUuid();
		this.userName = member.getUserName()==null?"":member.getUserName();
		this.password = member.getPassword()==null?"":member.getPassword();
		this.email  = member.getEmail()==null?"":member.getEmail();
		this.lastLoginIP = member.getLastLoginIP()==null?"":member.getLastLoginIP();
		this.lastLoginTime = member.getLastLoginTime()==null?new Date():member.getLastLoginTime();
		this.loginTimes = member.getLoginTimes()==null?0:member.getLoginTimes();
		this.regIP  = member.getRegIP()==null?"":member.getRegIP();
		this.regTime   = member.getRegTime()==null?new Date():member.getRegTime();
		this.updateTime = member.getUpdateTime()==null?new Date():member.getUpdateTime();
		this.visible = member.getVisible()==null?0:member.getVisible();
		this.realName = member.getRealName()==null?"":member.getRealName();
		this.cellphone =member.getCellphone()==null?"":member.getCellphone();
		this.memberType = member.getMemberType()==null?0:member.getMemberType();
		this.enterpriseId = member.getEnterprise_Info()==null?"":member.getEnterprise_Info().getUuid();
	}*/
	String uuid;
	String userName;
	String password;
	String email;
	String lastLoginIP;
	Date lastLoginTime;
	Integer loginTimes=0;
	String regIP;
	Date regTime;
	Date updateTime;
	Integer visible = 0;
	String realName;
	String cellphone;
	Integer memberType;
	String enterpriseId;
	@XmlElement
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	@XmlElement
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@XmlElement
	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	@XmlElement
	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}
	@XmlElement
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	@XmlElement
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@XmlElement
	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	@XmlElement
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@XmlElement
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	@XmlElement
	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	@XmlElement
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
}
