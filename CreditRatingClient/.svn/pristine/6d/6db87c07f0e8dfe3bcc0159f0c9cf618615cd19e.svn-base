package com.credit.model.privilege;

public class C_SystemPrivilegeKey {
    private String model;

    private String privilegevalue;

	public C_SystemPrivilegeKey(){}
	
	public C_SystemPrivilegeKey(String model, String privilegeValue) {
		this.model =model;
		this.privilegevalue= privilegeValue;
	}
	
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getPrivilegevalue() {
        return privilegevalue;
    }

    public void setPrivilegevalue(String privilegevalue) {
        this.privilegevalue = privilegevalue == null ? null : privilegevalue.trim();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result
				+ ((privilegevalue == null) ? 0 : privilegevalue.hashCode());
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
		C_SystemPrivilegeKey other = (C_SystemPrivilegeKey) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (privilegevalue == null) {
			if (other.privilegevalue != null)
				return false;
		} else if (!privilegevalue.equals(other.privilegevalue))
			return false;
		return true;
	}
}