package com.credit.model.person;

import java.util.Date;

public class PerHistory {
    private String uuid;

    private String historicalxmlurl;

    private String snapshoturl;

    private Date updatetime;

    private String perid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
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

    public String getPerid() {
        return perid;
    }

    public void setPerid(String perid) {
        this.perid = perid == null ? null : perid.trim();
    }
}