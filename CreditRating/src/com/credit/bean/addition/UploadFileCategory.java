package com.credit.bean.addition;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.transferBean.ScanFile;

/**
 *  上传文件管理
 */

@Entity
@Table(name="TB_UploadFileCategory")
public class UploadFileCategory implements Serializable{
	
	private static final long serialVersionUID = 5784070425451120601L;
	
	@Id
	@Column(length=32)
	private String uuid;//主键 
	
	@Column(length=20)
	private String name;//附件名称
	
	@Column(columnDefinition="number(8) default 0")
	private Integer isMust;//是否必须上传
	
	@Column(columnDefinition="number(8) default 0")
	private Integer isEnt;//是否是企业文件0企业文件1个人文件
	
	@Column(columnDefinition="number(8) default 0")
	private Integer type;//上传文件类型0单一文件1复数文件
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;//更新时间
	
	@Column(length=200)
	private String remark;//备注
	
	public UploadFileCategory() {
		super();
	}

	public UploadFileCategory(String uuid) {
		super();
		this.uuid = uuid;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isEnt == null) ? 0 : isEnt.hashCode());
		result = prime * result + ((isMust == null) ? 0 : isMust.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFileCategory other = (UploadFileCategory) obj;
		if (isEnt == null) {
			if (other.isEnt != null)
				return false;
		} else if (!isEnt.equals(other.isEnt))
			return false;
		if (isMust == null) {
			if (other.isMust != null)
				return false;
		} else if (!isMust.equals(other.isMust))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public Integer getIsEnt() {
		return isEnt;
	}

	public void setIsEnt(Integer isEnt) {
		this.isEnt = isEnt;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public void setType(Integer type) {
		this.type = type;
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
	 * @return the name
	 */
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public UploadFileCategory(ScanFile sf){
		this.name=sf.getName();
		this.isMust=sf.getIsMust();
		this.remark=sf.getRemark();
		this.type=sf.getIndexFileType();
		this.updateTime=sf.getUpdateTime();
	}
}
