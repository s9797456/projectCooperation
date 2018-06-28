package com.credit.bean.transferBean;

import javax.xml.bind.annotation.XmlElement;

public class Account2 {
	public Account2(){}
	
	String uuid;
    String basicAccountName;
	String basicAccountAddr;
	String basicAccountTel;
	String enterprise_infoId;
	@XmlElement
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@XmlElement
	public String getBasicAccountName() {
		return basicAccountName;
	}
	public void setBasicAccountName(String basicAccountName) {
		this.basicAccountName = basicAccountName;
	}
	@XmlElement
	public String getBasicAccountAddr() {
		return basicAccountAddr;
	}
	public void setBasicAccountAddr(String basicAccountAddr) {
		this.basicAccountAddr = basicAccountAddr;
	}
	@XmlElement
	public String getBasicAccountTel() {
		return basicAccountTel;
	}
	public void setBasicAccountTel(String basicAccountTel) {
		this.basicAccountTel = basicAccountTel;
	}
	@XmlElement
	public String getEnterprise_infoId() {
		return enterprise_infoId;
	}
	public void setEnterprise_infoId(String enterprise_infoId) {
		this.enterprise_infoId = enterprise_infoId;
	}
	
}
