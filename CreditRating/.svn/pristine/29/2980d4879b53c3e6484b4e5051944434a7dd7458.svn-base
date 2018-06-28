package com.credit.bean.enterprise;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.transferBean.OperScanFile;
@Entity
@Table(name="TE_UploadFile")
public class UploadFile implements Serializable{
	
	private static final long serialVersionUID = 5784070425451120601L;
	
	@Id
	@Column(length=32)
	private String uuid;//主键 
	
	@Column(length=20)
	private String fileName;//文件名
	
	@Column(length=10)
	private String fileSize;//文件大小
	
	@Column(length=200)
	private String fileUrl;//文件地址
	
	@Column(columnDefinition="number(8) default 0",nullable = false)
	private Integer crossState;//上传文件是否跨域上传至后台
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date addDate;//上传时间
	
	@Column(length=32)
	private String EntID;//企业外键
	
	@Column(length=32)
	private String uploadFileCategoryID;//附件分类表外键
	
	public UploadFile() {
		super();
	}

	public UploadFile(String uuid) {
		super();
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFile other = (UploadFile) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getCrossState() {
		return crossState;
	}

	public void setCrossState(Integer crossState) {
		this.crossState = crossState;
	}

	/**
	 * @return the addDate
	 */
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	/**
	 * @return the entID
	 */
	public String getEntID() {
		return EntID;
	}

	/**
	 * @param entID the entID to set
	 */
	public void setEntID(String entID) {
		EntID = entID;
	}

	/**
	 * @return the uploadFileCategoryID
	 */
	public String getUploadFileCategoryID() {
		return uploadFileCategoryID;
	}

	/**
	 * @param uploadFileCategoryID the uploadFileCategoryID to set
	 */
	public void setUploadFileCategoryID(String uploadFileCategoryID) {
		this.uploadFileCategoryID = uploadFileCategoryID;
	}
	
	public UploadFile(OperScanFile osf){
		this.fileSize=osf.getFileSize()+"";
		this.fileUrl=osf.getFileAddress();
		this.addDate=osf.getAddDate();
		/*对应的企业Id和附件类型查询后存入*/
		
	}

}
