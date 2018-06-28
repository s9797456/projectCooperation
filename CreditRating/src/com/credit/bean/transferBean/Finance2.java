package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class Finance2 {
	public Finance2(){}
	String id;
	String enterprise_id;
	String uploadFileName;
	Date uploadDate;
	int uploadSequence;
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	@XmlElement
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	@XmlElement
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	@XmlElement
	public int getUploadSequence() {
		return uploadSequence;
	}
	public void setUploadSequence(int uploadSequence) {
		this.uploadSequence = uploadSequence;
	}
	
}
