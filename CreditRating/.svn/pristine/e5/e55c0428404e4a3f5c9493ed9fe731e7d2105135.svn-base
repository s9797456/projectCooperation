package com.credit.bean.enterprise;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.credit.bean.transferBean.MainPerson;
@Entity
@Table(name="TE_Executives")
public class Executives implements Serializable{
	
	private static final long serialVersionUID = 5784070425451120601L;
	
	@Id
	@Column(length=32)
	private String uuid;//主键 
	
	@Column(length=50)
	private String name;//姓名
	
	@Column(columnDefinition="number(8)")
	private Integer age;//年龄
	
	@Column(length=10)
	private String gender;//性别
	
	@Column(length=30)
	private String job;//职位
	
	@Column(length=20)
	private String department;//部门
	
	@Column(length=20)
	private String education;//学历
	
	@Column(length=2000)
	private String workExp;//工作经历
	
	@Column(length=20)
	private String IDCard;//身份证号
	
	@Column(length=32)
	private String EntID;//企业外键
	
	public Executives() {
		super();
	}

	public Executives(String uuid) {
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
		Executives other = (Executives) obj;
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
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the workExp
	 */
	public String getWorkExp() {
		return workExp;
	}

	/**
	 * @param workExp the workExp to set
	 */
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	/**
	 * @return the iDCard
	 */
	public String getIDCard() {
		return IDCard;
	}

	/**
	 * @param iDCard the iDCard to set
	 */
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
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
	
	public Executives(MainPerson mp){
		/*年龄*/
		//this.department
		this.education=mp.getEducation();
		this.gender=mp.getSex();
		this.IDCard=mp.getCardNo();
		this.job=mp.getDuty();
		this.name=mp.getName();
		this.workExp=mp.getWorkExp();
		this.uuid=mp.getUuid().replace("-", "");
	}

}
