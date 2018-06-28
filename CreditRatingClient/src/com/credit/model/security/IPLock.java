package com.credit.model.security;

import java.util.Date;

public class IPLock {
    private String uuid;

    private Date addtime;

    private String ip;

    private String iplimit;

    private Integer isforever;

    private Integer islimit;

    private Integer lockdate;

    private Integer statue;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIplimit() {
        return iplimit;
    }

    public void setIplimit(String iplimit) {
        this.iplimit = iplimit == null ? null : iplimit.trim();
    }

    public Integer getIsforever() {
        return isforever;
    }

    public void setIsforever(Integer isforever) {
        this.isforever = isforever;
    }

    public Integer getIslimit() {
        return islimit;
    }

    public void setIslimit(Integer islimit) {
        this.islimit = islimit;
    }

    public Integer getLockdate() {
        return lockdate;
    }

    public void setLockdate(Integer lockdate) {
        this.lockdate = lockdate;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }
}