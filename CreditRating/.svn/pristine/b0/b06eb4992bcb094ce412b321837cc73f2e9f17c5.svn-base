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
@Table(name = "TS_IPLock")
public class IPLock implements Serializable{
	
	private static final long serialVersionUID = -8128144939068052478L;

	@Id
	@Column(length = 32)
    private String uuid;
	
	@Column(length = 30)
    private String ip;
	
	@Column(length = 30)
    private String iplimit;//如果有IP段，这个IP为IP段的后一位
	
	@Column(columnDefinition="number(8) default 0")
    private Integer islimit;//判断是否为IP段：0为ip；1为ip段
	
	@Column(columnDefinition="number(8) default 0")
    private Integer statue;//0白名单；1黑名单
	
	@Temporal(TemporalType.DATE)
	@Column
    private Date addtime;//添加时间
	
    @Column(columnDefinition="number(8) default 0")
    private Integer isforever;//是否为永久性的封锁:0为暂时封锁；1为永久封锁
    
    @Column(columnDefinition="number(8) default 0")
    private Integer lockdate;//封锁时间（以小时 为单位） 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addtime == null) ? 0 : addtime.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((iplimit == null) ? 0 : iplimit.hashCode());
		result = prime * result
				+ ((isforever == null) ? 0 : isforever.hashCode());
		result = prime * result + ((islimit == null) ? 0 : islimit.hashCode());
		result = prime * result
				+ ((lockdate == null) ? 0 : lockdate.hashCode());
		result = prime * result + ((statue == null) ? 0 : statue.hashCode());
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
		IPLock other = (IPLock) obj;
		if (addtime == null) {
			if (other.addtime != null)
				return false;
		} else if (!addtime.equals(other.addtime))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (iplimit == null) {
			if (other.iplimit != null)
				return false;
		} else if (!iplimit.equals(other.iplimit))
			return false;
		if (isforever == null) {
			if (other.isforever != null)
				return false;
		} else if (!isforever.equals(other.isforever))
			return false;
		if (islimit == null) {
			if (other.islimit != null)
				return false;
		} else if (!islimit.equals(other.islimit))
			return false;
		if (lockdate == null) {
			if (other.lockdate != null)
				return false;
		} else if (!lockdate.equals(other.lockdate))
			return false;
		if (statue == null) {
			if (other.statue != null)
				return false;
		} else if (!statue.equals(other.statue))
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIplimit() {
		return iplimit;
	}

	public void setIplimit(String iplimit) {
		this.iplimit = iplimit;
	}

	public Integer getIslimit() {
		return islimit;
	}

	public void setIslimit(Integer islimit) {
		this.islimit = islimit;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getIsforever() {
		return isforever;
	}

	public void setIsforever(Integer isforever) {
		this.isforever = isforever;
	}

	public Integer getLockdate() {
		return lockdate;
	}

	public void setLockdate(Integer lockdate) {
		this.lockdate = lockdate;
	}


}