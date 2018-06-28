package com.credit.bean.member;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

//临时注册表
//@Entity
//@Table(name="TB_TempRegistration")
public class TempRegistration  implements java.io.Serializable {

	private static final long serialVersionUID = -6805600402791577105L;

	//主键
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid") 
    @Column(length=32)
	private String uuid;
	
	//用户名
	@Column(length=32,nullable=false)
	private String userName;
     
	/* 20位, 不能为null */
	@Column(length=50,nullable=false)
	private String password;
     
	/* 电子邮件 50 */
	@Column(length=50)
	private String email;
	
	/* 注册ip 2位 不能为null */
	@Column(length=15,nullable=false)	
	private String regIP;
	
	/*注册日期*/
	@Temporal(TemporalType.DATE) @Column(nullable=false)
	private Date regTime;
	
	/*更新日期*/
	@Temporal(TemporalType.DATE) @Column(nullable=true)
	private Date updateTime;
	
	/* 姓名 10位 不能为null */
	@Column(length=20)
	private String realName;
	
	/* 联系电话 20 */
	@Column(length=20)
	private String cellphone;

	@Column(length = 255)
	private String name; // 企业名称
	
	@Column(length = 20)
	private String USCC; //统一社会信用代码

	@Column(length = 255)
	private String industry; // 所属行业
	
	/* 信息处理状态0未处理，1已处理*/
	@Column(columnDefinition="number(8) default 0")
	private Integer status = 0;
	
	@Column(length = 32)
	private String entID; // 企业ID
	
	public TempRegistration() {
		super();
	}
	
	public TempRegistration(String uuid) {
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
		TempRegistration other = (TempRegistration) obj;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the regIP
	 */
	public String getRegIP() {
		return regIP;
	}

	/**
	 * @param regIP the regIP to set
	 */
	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}

	/**
	 * @return the regTime
	 */
	public Date getRegTime() {
		return regTime;
	}

	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the cellphone
	 */
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
	 * @return the uSCC
	 */
	public String getUSCC() {
		return USCC;
	}

	/**
	 * @param uSCC the uSCC to set
	 */
	public void setUSCC(String uSCC) {
		USCC = uSCC;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the entID
	 */
	public String getEntID() {
		return entID;
	}

	/**
	 * @param entID the entID to set
	 */
	public void setEntID(String entID) {
		this.entID = entID;
	}


	
}