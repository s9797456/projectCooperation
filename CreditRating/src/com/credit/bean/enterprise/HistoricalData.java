package com.credit.bean.enterprise;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="TE_HistoricalData")
public class HistoricalData implements Serializable {
	
	private static final long serialVersionUID = 4118828708261007287L;

	@Id
	@Column(length = 32)
	private String uuid;//主键

	@Column(length = 300)
	private String EntName;//企业名

	@Column(length = 200)
	private String snapshotUrl;//快照存放地址

	@Column(length = 200)
	private String historicalXMLUrl;//评分后保存的历史数据XML存放地址

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;//更新时间
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "EntID")
	private EntBaseInfo entBaseInfo;//企业外键
	
	public HistoricalData() {
		super();
	}

	public HistoricalData(String uuid) {
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
		HistoricalData other = (HistoricalData) obj;
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
	 * @return the entName
	 */
	public String getEntName() {
		return EntName;
	}

	/**
	 * @param entName the entName to set
	 */
	public void setEntName(String entName) {
		EntName = entName;
	}

	/**
	 * @return the snapshotUrl
	 */
	public String getSnapshotUrl() {
		return snapshotUrl;
	}

	/**
	 * @param snapshotUrl the snapshotUrl to set
	 */
	public void setSnapshotUrl(String snapshotUrl) {
		this.snapshotUrl = snapshotUrl;
	}

	/**
	 * @return the historicalXMLUrl
	 */
	public String getHistoricalXMLUrl() {
		return historicalXMLUrl;
	}

	/**
	 * @param historicalXMLUrl the historicalXMLUrl to set
	 */
	public void setHistoricalXMLUrl(String historicalXMLUrl) {
		this.historicalXMLUrl = historicalXMLUrl;
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
	 * @return the entBaseInfo
	 */
	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}

	/**
	 * @param entBaseInfo the entBaseInfo to set
	 */
	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}
	
	

}
