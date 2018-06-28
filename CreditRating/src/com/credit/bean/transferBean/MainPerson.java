package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class MainPerson {
	
	 public MainPerson() {}
	 
	 

	String uuid;
	String position;
	String duty;
	String sex;
	Date birthDay;
	String cardNo;
	String passPost;
	String education;
	String industryExp;
	String manageExp;
	String  workExp;
	String promotionRea;
	String name;
	String enterprise_infoId;
	@XmlElement 
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@XmlElement
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@XmlElement
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	@XmlElement
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@XmlElement
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	@XmlElement
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@XmlElement
	public String getPassPost() {
		return passPost;
	}

	public void setPassPost(String passPost) {
		this.passPost = passPost;
	}
	@XmlElement
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	@XmlElement
	public String getIndustryExp() {
		return industryExp;
	}

	public void setIndustryExp(String industryExp) {
		this.industryExp = industryExp;
	}
	@XmlElement
	public String getManageExp() {
		return manageExp;
	}

	public void setManageExp(String manageExp) {
		this.manageExp = manageExp;
	}
	@XmlElement
	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}
	@XmlElement
	public String getPromotionRea() {
		return promotionRea;
	}

	public void setPromotionRea(String promotionRea) {
		this.promotionRea = promotionRea;
	}
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getEnterprise_infoId() {
		return enterprise_infoId;
	}

	public void setEnterprise_infoId(String enterprise_infoId) {
		this.enterprise_infoId = enterprise_infoId;
	}
	 
	 
}
