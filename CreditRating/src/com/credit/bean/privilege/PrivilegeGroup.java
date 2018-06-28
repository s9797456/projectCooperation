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
import javax.persistence.Table;

import com.credit.bean.member.User;

/**
 * 权限组(角色)
 */
@Entity
@Table(name="TP_PrivilegeGroup")
public class PrivilegeGroup  implements Serializable {

	private static final long serialVersionUID = 5354257719333222279L;

	@Id @Column(length=36)
	private String uuid;
	
	@Column(length=20,nullable=false)
	private String name;
	
	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinTable(name="TP_group_privilege",joinColumns=@JoinColumn(name="groupID"),
			inverseJoinColumns={@JoinColumn(name="model", referencedColumnName="model"),
								@JoinColumn(name="privilegeValue", referencedColumnName="privilegeValue")})
	private Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();

	@ManyToMany(mappedBy="groups",cascade=CascadeType.REFRESH)
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinTable(name="TP_dep_role",joinColumns=@JoinColumn(name="roleID"),
	inverseJoinColumns=@JoinColumn(name="depID"))
	private Set<Department> departments = new HashSet<Department>();

	public void addDepartment(Department department){
		this.departments.add(department);
	}
	/**
	 * 添加权限
	 * @param privilege 权限
	 */
	public void addPrivilege(SystemPrivilege systemPrivilege) {
		this.systemPrivileges.add(systemPrivilege);
	}

	public PrivilegeGroup(){}

	public PrivilegeGroup(String uuid) {
		this.uuid = uuid;
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
	public Set<SystemPrivilege> getSystemPrivileges() {
		return systemPrivileges;
	}
	public void setSystemPrivileges(Set<SystemPrivilege> systemPrivileges) {
		this.systemPrivileges = systemPrivileges;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
}
