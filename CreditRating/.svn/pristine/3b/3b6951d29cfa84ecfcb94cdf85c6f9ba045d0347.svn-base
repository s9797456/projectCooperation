package com.credit.bean.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.credit.bean.member.User;

//机构
@Entity
@Table(name = "TP_Department")
public class Department implements Serializable {

	private static final long serialVersionUID = -1275489117507208109L;
	// 主键
	@Id
	@Column(length = 36)
	private String uuid;

	// 编号
	@Column(length = 200)
	private String sn;

	// 名称
	@Column(length = 36, nullable = false)
	private String name;
	// 描述
	@Column(length = 200)
	private String description;
	// 联系电话
	@Column(length = 100)
	private String phone;
	// 传真
	@Column(length = 100)
	private String fax;
	// 邮件地址
	@Column(length = 100)
	private String email;
	// 排序号
	/*
	 * @Column(nullable=false) private Integer orderNO;
	 */
	// 启用标志
	@Column(nullable = false)
	private Integer visible = 1;
	// 代表图片
	@Column(length = 200)
	private String imgUrl;
	/**
	 * 业务需求
	 */
	// 二级域名
	@Column(length = 200)
	private String twoDomainNames;
	// 公司网站
	@Column(length = 200)
	private String depUrl;
/*	// 页面头部
	@Lob
	private String headHtml;
	// 页面底部
	@Lob
	private String footHtml;*/
	// 公司logo
	@Column(length = 500)
	private String logoImageUrl;
	// 协议图片
	@Column(length = 500)
	private String proccessImageUrl;
	/**
	 * 积分管理
	 */
	// 剩余积分
	@Column(name = "RESIDUALINTEGRAL")
	private double residualIntegral;
	// 折扣
	@Column(name = "DISCOUNT")
	private double discount;
	// 扣除积分
	@Column(name = "DEDUCTIONINTEGRAL")
	private double deductionIntegral;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE, }, mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<Department> childs = new HashSet<Department>();

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "parentID")
	private Department parent;

	@ManyToMany(mappedBy = "departments", cascade = CascadeType.REFRESH)
	private Set<User> users = new HashSet<User>();

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "TP_dep_role", joinColumns = @JoinColumn(name = "depID"), inverseJoinColumns = @JoinColumn(name = "roleID"))
	private Set<PrivilegeGroup> privilegeGroups = new HashSet<PrivilegeGroup>();

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "TP_dep_privilege", joinColumns = @JoinColumn(name = "depID"), inverseJoinColumns = {
			@JoinColumn(name = "model", referencedColumnName = "model"),
			@JoinColumn(name = "privilegeValue", referencedColumnName = "privilegeValue") })
	private Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();

	/**
	 * 添加权限
	 * 
	 * @param privilege
	 *            权限
	 */
	public void addPrivilege(SystemPrivilege privilege) {
		this.systemPrivileges.add(privilege);
	}

	public Set<SystemPrivilege> getSystemPrivileges() {
		return systemPrivileges;
	}

	public void setSystemPrivileges(Set<SystemPrivilege> systemPrivileges) {
		this.systemPrivileges = systemPrivileges;
	}

	public void addPrivilegeGroup(PrivilegeGroup group) {
		this.privilegeGroups.add(group);
	}

	public Set<PrivilegeGroup> getPrivilegeGroups() {
		return privilegeGroups;
	}

	public void setPrivilegeGroups(Set<PrivilegeGroup> privilegeGroups) {
		this.privilegeGroups = privilegeGroups;
	}

	public Department(String uuid) {
		this.uuid = uuid;
	}

	public Department() {
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
		final Department other = (Department) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Department> getChilds() {
		return childs;
	}

	public void setChilds(Set<Department> childs) {
		this.childs = childs;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSn() {
		return sn;
	}

	/*
	 * public Integer getOrderNO() { return orderNO; } public void
	 * setOrderNO(Integer orderNO) { this.orderNO = orderNO; }
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the depUrl
	 */
	public String getDepUrl() {
		return depUrl;
	}

	/**
	 * @param depUrl the depUrl to set
	 */
	public void setDepUrl(String depUrl) {
		this.depUrl = depUrl;
	}

	/**
	 * @return the proccessImageUrl
	 */
	public String getProccessImageUrl() {
		return proccessImageUrl;
	}

	/**
	 * @param proccessImageUrl
	 *            the proccessImageUrl to set
	 */
	public void setProccessImageUrl(String proccessImageUrl) {
		this.proccessImageUrl = proccessImageUrl;
	}

	/**
	 * @return the twoDomainNames
	 */
	public String getTwoDomainNames() {
		return twoDomainNames;
	}

	/**
	 * @param twoDomainNames
	 *            the twoDomainNames to set
	 */
	public void setTwoDomainNames(String twoDomainNames) {
		this.twoDomainNames = twoDomainNames;
	}

	/**
	 * @return the logoImageUrl
	 */
	public String getLogoImageUrl() {
		return logoImageUrl;
	}

	/**
	 * @param logoImageUrl
	 *            the logoImageUrl to set
	 */
	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}

	/**
	 * @return the residualIntegral
	 */
	public double getResidualIntegral() {
		return residualIntegral;
	}

	/**
	 * @param residualIntegral
	 *            the residualIntegral to set
	 */
	public void setResidualIntegral(double residualIntegral) {
		this.residualIntegral = residualIntegral;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the deductionIntegral
	 */
	public double getDeductionIntegral() {
		return deductionIntegral;
	}

	/**
	 * @param deductionIntegral
	 *            the deductionIntegral to set
	 */
	public void setDeductionIntegral(double deductionIntegral) {
		this.deductionIntegral = deductionIntegral;
	}

}
