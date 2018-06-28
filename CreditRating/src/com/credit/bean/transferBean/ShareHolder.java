package com.credit.bean.transferBean;

import javax.xml.bind.annotation.XmlElement;


public class ShareHolder {
	
	public ShareHolder(){}
	
	
	

	String uuid;
	String shareholderName;
	String cardNo;
	String investment;
	String stake;
	String shareholderType;
	String enterprise_infoId;
	
	@XmlElement
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@XmlElement
	public String getShareholderName() {
		return shareholderName;
	}


	public void setShareholderName(String shareholderName) {
		this.shareholderName = shareholderName;
	}

	@XmlElement
	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@XmlElement
	public String getInvestment() {
		return investment;
	}


	public void setInvestment(String investment) {
		this.investment = investment;
	}
	
	@XmlElement
	public String getStake() {
		return stake;
	}


	public void setStake(String stake) {
		this.stake = stake;
	}

	@XmlElement
	public String getShareholderType() {
		return shareholderType;
	}


	public void setShareholderType(String shareholderType) {
		this.shareholderType = shareholderType;
	}

	@XmlElement
	public String getEnterprise_infoId() {
		return enterprise_infoId;
	}


	public void setEnterprise_infoId(String enterprise_infoId) {
		this.enterprise_infoId = enterprise_infoId;
	}
	
	
}
