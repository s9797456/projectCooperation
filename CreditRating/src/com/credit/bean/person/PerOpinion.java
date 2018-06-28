package com.credit.bean.person;

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
@Table(name="TI_PerOpinion")
public class PerOpinion implements Serializable {
	
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
	@JoinColumn(name = "PerID")
	private PerBaseInfo perBaseInfo;//个人外键
	
	public PerOpinion() {
		super();
	}

	public PerOpinion(String uuid) {
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
		PerOpinion other = (PerOpinion) obj;
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

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getFeekbackNum() {
		return feekbackNum;
	}

	public void setFeekbackNum(Integer feekbackNum) {
		this.feekbackNum = feekbackNum;
	}

	public Integer getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public PerBaseInfo getPerBaseInfo() {
		return perBaseInfo;
	}

	public void setPerBaseInfo(PerBaseInfo perBaseInfo) {
		this.perBaseInfo = perBaseInfo;
	}

}
