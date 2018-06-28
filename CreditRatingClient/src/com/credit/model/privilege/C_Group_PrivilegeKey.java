package com.credit.model.privilege;

public class C_Group_PrivilegeKey {
    private String groupid;

    private String model;

    private String privilegevalue;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
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
}