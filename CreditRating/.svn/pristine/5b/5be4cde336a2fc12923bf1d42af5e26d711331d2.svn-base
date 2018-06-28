package com.credit.bean.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.credit.bean.member.Customer;

//机构
@Entity
@Table(name = "TP_Organization")
public class Organization implements Serializable {

	private static final long serialVersionUID = -1275489117507208109L;
	// 主键
	@Id
	@Column(length = 36)
	private String uuid;
	// 名称
	@Column(length = 100, nullable = false)
	private String name;
	// 描述
	@Column(length = 200)
	private String description;
	// 联系电话
	@Column(length = 100)
	private String phone;
	// 邮件地址
	@Column(length = 100)
	private String email;
	// 启用标志
	@Column(nullable = false)
	private Integer visible = 1;
	// 代表图片
	@Column(length = 200)
	private String imgUrl;

	// 二级域名
	@Column(length = 200)
	private String twoDomainNames;
	// 公司网站
	@Column(length = 200)
	private String orgUrl;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE, }, mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<Customer> customers = new HashSet<Customer>();

	


	public Organization(String uuid) {
		this.uuid = uuid;
	}

	public Organization() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final Organization other = (Organization) obj;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the visible
	 */
	public Integer getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the twoDomainNames
	 */
	public String getTwoDomainNames() {
		return twoDomainNames;
	}

	/**
	 * @param twoDomainNames the twoDomainNames to set
	 */
	public void setTwoDomainNames(String twoDomainNames) {
		this.twoDomainNames = twoDomainNames;
	}

	/**
	 * @return the orgUrl
	 */
	public String getOrgUrl() {
		return orgUrl;
	}

	/**
	 * @param orgUrl the orgUrl to set
	 */
	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	/**
	 * @return the customers
	 */
	public Set<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	/* 
	 * @author Administrator @date 2017-10-25 下午1:21:44
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organization [uuid=" + uuid + ", name=" + name
				+ ", description=" + description + ", phone=" + phone
				+ ", email=" + email + ", visible=" + visible + ", imgUrl="
				+ imgUrl + ", twoDomainNames=" + twoDomainNames + ", orgUrl="
				+ orgUrl + ", customers=" + customers + "]";
	}

}
