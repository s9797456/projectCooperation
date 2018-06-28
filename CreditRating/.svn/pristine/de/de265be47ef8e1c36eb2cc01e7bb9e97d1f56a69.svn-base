package com.credit.bean.member;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.transferBean.User2;
import com.credit.bean.vo.member.Gender;

@Entity
// 实体类，注册在类头上
@Table(name = "TP_User")
// 对应的表，注册在类头上
public class User implements Serializable {

	private static final long serialVersionUID = 2245538304881596337L;

	/* 主键,36位 */
	@Id
	@Column(length = 36, nullable = false)
	private String userName;

	/* 20位, 不能为null */
	@Column(length = 50, nullable = false)
	// 用来注册属性
	private String password;
	// @Id、@GeneratedValue、 @Column 、
	// @Version，可以用来注册属性，既可以写在Java类的属性上，也可以注册在属性对应的getter上。
	/* 性别5位 */
	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	private Gender gender = Gender.MAN;

	/* 电子邮件 50 */
	@Column(length = 50)
	private String email;

	/* 照片 200 */
	@Column(length = 200)
	private String imgUrl;

	/* 前一次登录ip 2位 不能为null */
	@Column(length = 15)
	private String lastLoginIP;

	/* 前一次登录日期 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date lastLoginTime;

	/* 登录次数 */
	@Column(columnDefinition="number(8) default 0",nullable = false)
	private Integer loginTimes = 0;

	/* 注册ip 2位 不能为null */
	@Column(length = 15, nullable = false)
	private String regIP;

	/* 注册日期 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	// 格式化时间日期
	private Date regTime;

	/* 更新日期 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date updateTime;

	/* 成员状态 true为激活,false为关闭 */
	@Column(columnDefinition="number(8) default 1",nullable = false)
	private Integer visible = 1;

	/* 姓名 10位 不能为null */
	@Column(length = 20)
	private String realName;

	/* 联系电话 20 */
	@Column(length = 20)
	private String cellphone;

	/* 所属机构 */
	@Column
	private String belongdep;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "TP_user_dep", joinColumns = @JoinColumn(name = "userName"), inverseJoinColumns = @JoinColumn(name = "depID"))
	private Set<Department> departments = new HashSet<Department>();

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "TP_user_role", joinColumns = @JoinColumn(name = "userName"), inverseJoinColumns = @JoinColumn(name = "groupID"))
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "TP_user_privilege", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = {
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

	public User() {
	}

	public User(String userName) {
		this.userName = userName;
	}

	/**
	 * 添加机构组
	 * 
	 * @param group
	 */
	public void addDepartments(Department department) {
		this.departments.add(department);
	}

	/**
	 * 添加权限组
	 * 
	 * @param group
	 */
	public void addPrivilegeGroup(PrivilegeGroup group) {
		this.groups.add(group);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		final User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Set<PrivilegeGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<PrivilegeGroup> groups) {
		this.groups = groups;
	}
	/**
	 * @return the departments
	 */
	public Set<Department> getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<SystemPrivilege> getSystemPrivileges() {
		return systemPrivileges;
	}

	public void setSystemPrivileges(Set<SystemPrivilege> systemPrivileges) {
		this.systemPrivileges = systemPrivileges;
	}

	/**
	 * @return the belongdep
	 */
	public String getBelongdep() {
		return belongdep;
	}

	/**
	 * @param belongdep
	 *            the belongdep to set
	 */
	public void setBelongdep(String belongdep) {
		this.belongdep = belongdep;
	}
	public User(User2 u2){
		this.belongdep=u2.getBelongdep();
		this.cellphone=u2.getCellphone();
		this.email=u2.getEmail();
		this.gender=Gender.getGender(u2.getGender());
		this.imgUrl=u2.getImgUrl();
		this.password=u2.getPassword();
		this.realName=u2.getRealName();
		this.userName=u2.getUserName();
		this.visible=u2.getVisible();
		
		
	}

}
