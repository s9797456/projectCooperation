package com.credit.bean.security;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TS_RegistLimit")
public class RegistLimit  implements Serializable{

	private static final long serialVersionUID = -8827013963674993629L;

	@Id
	@Column(length = 32)
	private String uuid;
	
	@Temporal(TemporalType.DATE)
	@Column
    private Date registtime;//注册时间

	@Column(length = 100)
    private String ip;//IP地址

	@Column(length = 20)
    private String phone;//注册电话

	@Column(columnDefinition="number(8) default 0")
    private Integer issuccess;//是否成功注册，0为不成功，1为成功

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((issuccess == null) ? 0 : issuccess.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((registtime == null) ? 0 : registtime.hashCode());
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
		RegistLimit other = (RegistLimit) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (issuccess == null) {
			if (other.issuccess != null)
				return false;
		} else if (!issuccess.equals(other.issuccess))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (registtime == null) {
			if (other.registtime != null)
				return false;
		} else if (!registtime.equals(other.registtime))
			return false;
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

	public Date getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Date registtime) {
		this.registtime = registtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIssuccess() {
		return issuccess;
	}

	public void setIssuccess(Integer issuccess) {
		this.issuccess = issuccess;
	}

   
}