package com.credit.model.addition;

import java.util.Date;

public class UploadFileCategory {
    private String uuid;

    private Integer ismust;

    private String name;

    private String remark;

    private Integer type;

    private Date updatetime;

    private Integer isent;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getIsmust() {
        return ismust;
    }

    public void setIsmust(Integer ismust) {
        this.ismust = ismust;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getIsent() {
        return isent;
    }

    public void setIsent(Integer isent) {
        this.isent = isent;
    }
}