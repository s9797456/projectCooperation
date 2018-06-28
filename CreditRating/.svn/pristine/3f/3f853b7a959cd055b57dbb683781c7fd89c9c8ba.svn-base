package com.credit.bean.enterprise;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.transferBean.Finance2;
@Entity
@Table(name="TE_Finance")
public class Finance implements Serializable{
	
	private static final long serialVersionUID = -2211329356184893204L;

	@Id
	@Column(length=32)
	private String uuid;//主键 
	
	@Column(length=200)
	private String fileurl;//财务路径
	
	@Column(length=10)
	private String filesize;//财务报表大小
	
	@Column(columnDefinition="number(8) default 0",nullable = false)
	private Integer crossState;//上传文件是否跨域上传至后台
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;//更新时间
	
	@Column(length=32)
	private String EntID;//企业外键
	
	public Finance(Finance2 fi){
		this.uuid = fi.getId().replace("-", "");
		this.filesize = String.valueOf(fi.getUploadSequence());
		this.updateTime = fi.getUploadDate();
		this.fileurl = fi.getUploadFileName();
	}
	
	public Finance() {
		super();
	}

	public Finance(String uuid) {
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
		Finance other = (Finance) obj;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * @return the fileurl
	 */
	public String getFileurl() {
		return fileurl;
	}

	/**
	 * @param fileurl the fileurl to set
	 */
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public Integer getCrossState() {
		return crossState;
	}

	public void setCrossState(Integer crossState) {
		this.crossState = crossState;
	}

}
