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
@Table(name = "TI_Train")
public class Train implements Serializable {
	private static final long serialVersionUID = -461624051003234888L;

	@Id
	@Column(length = 32)
	private String uuid;// 主键
	
	@Column(length = 32)
	private String PerID;//个人信息表外键
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date startTime;//开始时间
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date endTime;//结束时间
	
	@Column(length = 200)
	private String remarks;//备注
	
	@Column(length = 100)
	private String trainOrg;//培训机构
	
	@Column(length = 200)
	private String trainAddress; //培训地址
	
	@Column(length = 100)
	private String trainContent; //培训内容
	
	@Column(length = 30)
	private String certificateNo; //培训证书编号

	public Train() {
		super();
	}

	public Train(String uuid) {
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
		Train other = (Train) obj;
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

	public String getPerID() {
		return PerID;
	}

	public void setPerID(String perID) {
		PerID = perID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTrainOrg() {
		return trainOrg;
	}

	public void setTrainOrg(String trainOrg) {
		this.trainOrg = trainOrg;
	}

	public String getTrainAddress() {
		return trainAddress;
	}

	public void setTrainAddress(String trainAddress) {
		this.trainAddress = trainAddress;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	
}
