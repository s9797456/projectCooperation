package com.credit.bean.privilege;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Log4j日志
@Entity
@Table(name="TP_Log4j")
public class Log4j implements Serializable {

	private static final long serialVersionUID = -8342531662080546609L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=36,nullable=false,name="logLeve")
	private String level;

	//名称
	@Column(length=36,nullable=false)
	private String userName;
	
	//名称
	@Lob @Column(nullable=false)
	private String message;

	//更新日期
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable=true)
	private Date updateTime;

	
	
	public Log4j(Integer id) {
		this.id = id;
	}

	public Log4j(String userName, String message) {
		this.userName = userName;
		this.message = message;
	}

	public Log4j(String userName, String message, Date updateTime) {
		this.userName = userName;
		this.message = message;
		this.updateTime = updateTime;
	}

	public Log4j(Integer id, String userName, String message, Date updateTime) {
		this.id = id;
		this.userName = userName;
		this.message = message;
		this.updateTime = updateTime;
	}

	public Log4j() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Log4j other = (Log4j) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
