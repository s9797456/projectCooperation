package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class ScanFile {
	
	private String id;

	private String name;
	
	private int isMust;
	
	private int indexFileType;
	
	private String remark;
	
	private Date createTime;

	private Date updateTime;
	
	public ScanFile(){}
	
	

	/**
	 * @return the id
	 */
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

	/**
	 * @return the name
	 */
	@XmlElement
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isMust
	 */
	@XmlElement
	public int getIsMust() {
		return isMust;
	}

	/**
	 * @param isMust the isMust to set
	 */
	public void setIsMust(int isMust) {
		this.isMust = isMust;
	}

	/**
	 * @return the indexFileType
	 */
	@XmlElement
	public int getIndexFileType() {
		return indexFileType;
	}

	/**
	 * @param indexFileType the indexFileType to set
	 */
	public void setIndexFileType(int indexFileType) {
		this.indexFileType = indexFileType;
	}

	/**
	 * @return the remark
	 */
	@XmlElement
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the createTime
	 */
	@XmlElement
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	@XmlElement
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
