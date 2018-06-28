package com.credit.bean.security;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "TS_LoginLimit")
public class LoginLimit  implements Serializable{
	
	private static final long serialVersionUID = -2147654305043205557L;

	@Id
	@Column(length = 32)
	private String uuid;

	@Column(length = 100)
    private String loginname;//登录名

	@Temporal(TemporalType.DATE)
	@Column
    private Date logintime;//登陆时间

	@Column(columnDefinition="number(8) default 0")
    private Integer issuccess;//是否成功登录，0为不成功，1为成功

	@Column(length = 100)
    private String ip;//IP地址

	@Column(length = 200)
    private String failresult;//登录失败原因

	@Column(length = 36)
    private String customerID;//客户主键

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result
				+ ((failresult == null) ? 0 : failresult.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((issuccess == null) ? 0 : issuccess.hashCode());
		result = prime * result
				+ ((loginname == null) ? 0 : loginname.hashCode());
		result = prime * result
				+ ((logintime == null) ? 0 : logintime.hashCode());
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
		LoginLimit other = (LoginLimit) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (failresult == null) {
			if (other.failresult != null)
				return false;
		} else if (!failresult.equals(other.failresult))
			return false;
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
		if (loginname == null) {
			if (other.loginname != null)
				return false;
		} else if (!loginname.equals(other.loginname))
			return false;
		if (logintime == null) {
			if (other.logintime != null)
				return false;
		} else if (!logintime.equals(other.logintime))
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Integer getIssuccess() {
		return issuccess;
	}

	public void setIssuccess(Integer issuccess) {
		this.issuccess = issuccess;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFailresult() {
		return failresult;
	}

	public void setFailresult(String failresult) {
		this.failresult = failresult;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

}