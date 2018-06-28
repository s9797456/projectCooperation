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
@Table(name="TE_Opinion")
public class Opinion implements Serializable {
	
	private static final long serialVersionUID = 4118828708261007287L;

	@Id
	@Column(length = 32)
	private String uuid;//主键

	@Column(length = 200)
	private String opinion;//意见  前 -》后  驳回项

	@Column(columnDefinition="number(8)")
	private Integer feekbackNum;//反馈次数  前 -》后
	
	@Column(columnDefinition="number(8)")
	private Integer isConfirm;//是否确定   前 -》后
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;//更新时间
	
	@Column(length = 100)
	private String dealer;//处理人
	
	@Column(columnDefinition="number(8) default 0")
	private Integer isAdmin;//是否管理员意见;1是，0否
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "EntID")
	private EntBaseInfo entBaseInfo;//企业外键
	
	public Opinion() {
		super();
	}

	public Opinion(String uuid) {
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
		Opinion other = (Opinion) obj;
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
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return the feekbackNum
	 */
	public int getFeekbackNum() {
		return feekbackNum;
	}

	/**
	 * @param feekbackNum the feekbackNum to set
	 */
	public void setFeekbackNum(int feekbackNum) {
		this.feekbackNum = feekbackNum;
	}

	/**
	 * @return the isConfirm
	 */
	public int getIsConfirm() {
		return isConfirm;
	}

	/**
	 * @param isConfirm the isConfirm to set
	 */
	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
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
	 * @return the dealer
	 */
	public String getDealer() {
		return dealer;
	}

	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	/**
	 * @return the isAdmin
	 */
	public int getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
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
