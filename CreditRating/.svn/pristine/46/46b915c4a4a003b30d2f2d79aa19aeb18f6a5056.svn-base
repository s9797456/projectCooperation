package com.credit.bean.enterprise;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.transferBean.ShareHolder;
@Entity
@Table(name="TE_Shareholder")
public class Shareholder implements Serializable{
	
	private static final long serialVersionUID = 5784070425451120601L;
	
	@Id
	@Column(length=32)
	private String uuid;//主键 
	
	@Column(length=200)
	private String name;//股东姓名
	
	@Column(length=20)
	private String stockpercent;//股比
	
	@Column(length=100)
	private String type;//股东类型
	
	@Column(length=30)
	private String realcapi ;//实缴金额
	
	@Temporal(TemporalType.DATE)
	private Date realTime;//实缴时间
	
	@Column(length=20)
	private String shouldcapi ;//认缴金额
	
	@Temporal(TemporalType.DATE)
	private Date shouldTime;//认缴时间

	@Column(length=32)
	private String EntID;//企业外键
	
	public Shareholder() {
		super();
	}

	public Shareholder(String uuid) {
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
		Shareholder other = (Shareholder) obj;
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
	 * @return the stockpercent
	 */
	public String getStockpercent() {
		return stockpercent;
	}

	/**
	 * @param stockpercent the stockpercent to set
	 */
	public void setStockpercent(String stockpercent) {
		this.stockpercent = stockpercent;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the realcapi
	 */
	public String getRealcapi() {
		return realcapi;
	}

	/**
	 * @param realcapi the realcapi to set
	 */
	public void setRealcapi(String realcapi) {
		this.realcapi = realcapi;
	}

	/**
	 * @return the realTime
	 */
	public Date getRealTime() {
		return realTime;
	}

	/**
	 * @param realTime the realTime to set
	 */
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}

	/**
	 * @return the shouldcapi
	 */
	public String getShouldcapi() {
		return shouldcapi;
	}

	/**
	 * @param shouldcapi the shouldcapi to set
	 */
	public void setShouldcapi(String shouldcapi) {
		this.shouldcapi = shouldcapi;
	}

	/**
	 * @return the shouldTime
	 */
	public Date getShouldTime() {
		return shouldTime;
	}

	/**
	 * @param shouldTime the shouldTime to set
	 */
	public void setShouldTime(Date shouldTime) {
		this.shouldTime = shouldTime;
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
	
	public Shareholder(ShareHolder sh){
		this.name=sh.getShareholderName();
		this.type=sh.getShareholderType();
		this.uuid=sh.getUuid().replace("-", "");
		this.realcapi=sh.getInvestment();
		this.stockpercent=sh.getStake();
	}

}
