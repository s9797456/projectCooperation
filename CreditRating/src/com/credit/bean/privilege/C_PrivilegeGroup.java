package com.credit.bean.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.credit.bean.member.Customer;

/**
 * 权限组(角色)
 */
@Entity
@Table(name="CTP_PrivilegeGroup")
public class C_PrivilegeGroup  implements Serializable {

	private static final long serialVersionUID = 5354257719333222279L;

	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid") 
    @Column(length=32)
	private String uuid;
	
	@Column(length=20,nullable=false)
	private String name;
	
	@Column(length=200,nullable=false)
	private String menuUrl;
	
	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinTable(name="CTP_group_privilege",joinColumns=@JoinColumn(name="groupID"),
			inverseJoinColumns={@JoinColumn(name="model", referencedColumnName="model"),
								@JoinColumn(name="privilegeValue", referencedColumnName="privilegeValue")})
	private Set<C_SystemPrivilege> systemPrivileges = new HashSet<C_SystemPrivilege>();

	@ManyToMany(mappedBy="groups",cascade=CascadeType.REFRESH)
	private Set<Customer> customers = new HashSet<Customer>();
	
	
	/**
	 * 添加权限
	 * @param privilege 权限
	 */
	public void addPrivilege(C_SystemPrivilege systemPrivilege) {
		this.systemPrivileges.add(systemPrivilege);
	}

	public C_PrivilegeGroup(){}

	public C_PrivilegeGroup(String uuid) {
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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Set<C_SystemPrivilege> getSystemPrivileges() {
		return systemPrivileges;
	}

	public void setSystemPrivileges(Set<C_SystemPrivilege> systemPrivileges) {
		this.systemPrivileges = systemPrivileges;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}


}
