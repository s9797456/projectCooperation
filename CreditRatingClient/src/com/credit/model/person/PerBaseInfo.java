package com.credit.model.person;

import java.util.Date;

public class PerBaseInfo {
    private String uuid;

    private String idcard;

    private String idissuingagency;

    private Date idtermend;

    private Date idtermstart;

    private Date createtime;

    private String fertilitycondition;

    private String gender;

    private String maritalstatus;

    private String name;

    private String nation;

    private String nationality;

    private String nativeplace;

    private String permanentaddress;

    private String politicaloutlook;

    private String presentaddress;

    private String presentzipcode;

    private String uscc;

    private String usedname;

    private Date updatetime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdissuingagency() {
        return idissuingagency;
    }

    public void setIdissuingagency(String idissuingagency) {
        this.idissuingagency = idissuingagency == null ? null : idissuingagency.trim();
    }

    public Date getIdtermend() {
        return idtermend;
    }

    public void setIdtermend(Date idtermend) {
        this.idtermend = idtermend;
    }

    public Date getIdtermstart() {
        return idtermstart;
    }

    public void setIdtermstart(Date idtermstart) {
        this.idtermstart = idtermstart;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFertilitycondition() {
        return fertilitycondition;
    }

    public void setFertilitycondition(String fertilitycondition) {
        this.fertilitycondition = fertilitycondition == null ? null : fertilitycondition.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus == null ? null : maritalstatus.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getPermanentaddress() {
        return permanentaddress;
    }

    public void setPermanentaddress(String permanentaddress) {
        this.permanentaddress = permanentaddress == null ? null : permanentaddress.trim();
    }

    public String getPoliticaloutlook() {
        return politicaloutlook;
    }

    public void setPoliticaloutlook(String politicaloutlook) {
        this.politicaloutlook = politicaloutlook == null ? null : politicaloutlook.trim();
    }

    public String getPresentaddress() {
        return presentaddress;
    }

    public void setPresentaddress(String presentaddress) {
        this.presentaddress = presentaddress == null ? null : presentaddress.trim();
    }

    public String getPresentzipcode() {
        return presentzipcode;
    }

    public void setPresentzipcode(String presentzipcode) {
        this.presentzipcode = presentzipcode == null ? null : presentzipcode.trim();
    }

    public String getUscc() {
        return uscc;
    }

    public void setUscc(String uscc) {
        this.uscc = uscc == null ? null : uscc.trim();
    }

    public String getUsedname() {
        return usedname;
    }

    public void setUsedname(String usedname) {
        this.usedname = usedname == null ? null : usedname.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}