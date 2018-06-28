package com.credit.bean.vo.privilege;

public class PrivilegeGroupVO {
	private String uuid;
	private String name;
	private boolean checked;
	
	public PrivilegeGroupVO(){}
	public PrivilegeGroupVO(String uuid){
		this.uuid=uuid;
	}
	public String getUuid() {
		return uuid;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
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
		PrivilegeGroupVO other = (PrivilegeGroupVO) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PrivilegeGroupVo [uuid=" + uuid + ", name=" + name + "]";
	}
}
