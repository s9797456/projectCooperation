package com.credit.bean.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TI_PerUploadFile")
public class PerUploadFile implements Serializable{
	
	private static final long serialVersionUID = 29722849151251221L;

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
	private String PerID;//个人外键
	
	@Column(length=32)
	private String uploadFileCategoryID;//附件分类表外键
	
	public PerUploadFile() {
		super();
	}

	public PerUploadFile(String uuid) {
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
		PerUploadFile other = (PerUploadFile) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getCrossState() {
		return crossState;
	}

	public void setCrossState(Integer crossState) {
		this.crossState = crossState;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getPerID() {
		return PerID;
	}

	public void setPerID(String perID) {
		PerID = perID;
	}

	public String getUploadFileCategoryID() {
		return uploadFileCategoryID;
	}

	public void setUploadFileCategoryID(String uploadFileCategoryID) {
		this.uploadFileCategoryID = uploadFileCategoryID;
	}

}
