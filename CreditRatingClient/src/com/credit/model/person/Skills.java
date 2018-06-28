package com.credit.model.person;

public class Skills {
    private String uuid;

    private String perid;

    private String remarks;

    private String skillname;

    private String skillproficiency;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getPerid() {
        return perid;
    }

    public void setPerid(String perid) {
        this.perid = perid == null ? null : perid.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname == null ? null : skillname.trim();
    }

    public String getSkillproficiency() {
        return skillproficiency;
    }

    public void setSkillproficiency(String skillproficiency) {
        this.skillproficiency = skillproficiency == null ? null : skillproficiency.trim();
    }
}