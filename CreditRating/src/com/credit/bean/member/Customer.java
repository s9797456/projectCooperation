package com.credit.bean.member;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.person.PerBaseInfo;
import com.credit.bean.person.PerResult;
import com.credit.bean.privilege.C_PrivilegeGroup;
import com.credit.bean.privilege.Organization;
import com.credit.bean.transferBean.Member2;
import com.credit.bean.transferBean.User2;

@Entity
// 实体类，注册在类头上
@Table(name = "TP_Customer")
// 对应的表，注册在类头上
public class Customer implements Serializable {

	private static final long serialVersionUID = 2245538304881596337L;

	/* 主键,36位 */
	@Id
	@Column(length = 36, nullable = false)
	private String userName;

	/* 20位, 不能为null */
	@Column(length = 50, nullable = false)
	// 用来注册属性
	private String password;

	/* 电子邮件 50 */
	@Column(length = 50)
	private String email;

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
	
	/* 注册域名 不能为null */
	@Column(length = 30)
	private String domainName;

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
	@Column(columnDefinition="number(8) default 0",nullable = false)
	private Integer visible = 0;

	/* 姓名 10位 不能为null */
	@Column(length = 20)
	private String realName;

	/* 联系电话 20 */
	@Column(length = 20)
	private String cellphone;
	
	/*客户类型 0是企业，1是机构客户,2是政府，3是个人*/
	@Column(columnDefinition="number(8) default 0",nullable = false)
	private Integer type=0;
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "EntID")
	private EntBaseInfo entBaseInfo;//企业外键
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "PerID")
	private PerBaseInfo perBaseInfo;//个人外键
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.REFRESH)
	private EntResult entResult;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.REFRESH)
	private PerResult perResult;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "orgID")
	private Organization organization;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "CTP_customer_role", joinColumns = @JoinColumn(name = "userName"), inverseJoinColumns = @JoinColumn(name = "groupID"))
	private Set<C_PrivilegeGroup> groups = new HashSet<C_PrivilegeGroup>();
	
	public Customer() {
		super();
	}

	public Customer(String userName) {
		super();
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		Customer other = (Customer) obj;
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

	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}

	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}

	public PerBaseInfo getPerBaseInfo() {
		return perBaseInfo;
	}

	public void setPerBaseInfo(PerBaseInfo perBaseInfo) {
		this.perBaseInfo = perBaseInfo;
	}

	public EntResult getEntResult() {
		return entResult;
	}

	public void setEntResult(EntResult entResult) {
		this.entResult = entResult;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<C_PrivilegeGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<C_PrivilegeGroup> groups) {
		this.groups = groups;
	}

	public PerResult getPerResult() {
		return perResult;
	}

	public void setPerResult(PerResult perResult) {
		this.perResult = perResult;
	}

	public Customer(Member2 member) {
		this.userName = member.getUserName();
		this.password = member.getPassword();
		this.email = member.getEmail();
		this.lastLoginIP = member.getLastLoginIP();
		this.lastLoginTime = member.getLastLoginTime();
		this.loginTimes = member.getLoginTimes();
		this.regIP = member.getRegIP();
		this.regTime = member.getRegTime();
		this.updateTime = member.getUpdateTime();
		this.visible = member.getVisible();
		this.realName = member.getRealName();
		this.cellphone = member.getCellphone();
	}
	public Customer(User2 member) {
		this.userName = member.getUserName();
		this.password = member.getPassword();
		this.email = member.getEmail();
		this.regTime = member.getRegTime();
		this.updateTime = member.getUpdateTime();
		this.visible = member.getVisible();
		this.realName = member.getRealName();
		this.cellphone = member.getCellphone();
		this.lastLoginIP = "192.168.1.1";
		this.lastLoginTime = new Date();
		this.loginTimes = 1;
		this.regIP = "192.168.1.1";
	}

}
