package com.credit.model.enterprise;

import java.util.Date;

public class Historical {
    private String uuid;

    private String entname;

    private String historicalxmlurl;

    private String snapshoturl;

    private Date updatetime;

    private String entid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname == null ? null : entname.trim();
    }

    public String getHistoricalxmlurl() {
        return historicalxmlurl;
    }

    public void setHistoricalxmlurl(String historicalxmlurl) {
        this.historicalxmlurl = historicalxmlurl == null ? null : historicalxmlurl.trim();
    }

    public String getSnapshoturl() {
        return snapshoturl;
    }

    public void setSnapshoturl(String snapshoturl) {
        this.snapshoturl = snapshoturl == null ? null : snapshoturl.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid == null ? null : entid.trim();
    }
}