package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class OperScanFile {
	
	private String id;
	
	private String enterprise_id;

	private String scanFile_id;

	private String fileName;

	private long fileSize;
	
	private String fileAddress;
	
	private Date addDate;

	
	
	public OperScanFile(){}
	
	@XmlElement
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public String getEnterprise_id() {
		return enterprise_id;
	}

	/**
	 * @param enterprise_id the enterprise_id to set
	 */
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	@XmlElement
	public String getScanFile_id() {
		return scanFile_id;
	}

	/**
	 * @param scanFile_id the scanFile_id to set
	 */
	public void setScanFile_id(String scanFile_id) {
		this.scanFile_id = scanFile_id;
	}

	@XmlElement
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@XmlElement
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@XmlElement
	public String getFileAddress() {
		return fileAddress;
	}

	/**
	 * @param fileAddress the fileAddress to set
	 */
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	@XmlElement
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	

}
