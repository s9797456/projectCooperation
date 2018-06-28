package com.credit.bean.privilege;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统权限
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CTP_SystemPrivilege")
public class C_SystemPrivilege  implements Serializable {


	private SystemPrivilegePK id;
	/* 权限名称 */
	@Column(length=20,nullable=false)
	private String name;
	
	public C_SystemPrivilege(String model, String privilegeValue, String name) {
		this.id = new SystemPrivilegePK(model, privilegeValue);
		this.name = name;
	}
	
	public C_SystemPrivilege(SystemPrivilegePK id) {
		this.id = id;
	}
	
	public C_SystemPrivilege(){}

	
	@EmbeddedId
	public SystemPrivilegePK getId() {
		return id;
	}
	public void setId(SystemPrivilegePK id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		final C_SystemPrivilege other = (C_SystemPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
