package com.credit.model.enterprise;

import java.util.Date;

public class Shareholder {
    private String uuid;

    private String entid;

    private String name;

    private Date realtime;

    private String realcapi;

    private Date shouldtime;

    private String shouldcapi;

    private String stockpercent;

    private String type;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid == null ? null : entid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getRealtime() {
        return realtime;
    }

    public void setRealtime(Date realtime) {
        this.realtime = realtime;
    }

    public String getRealcapi() {
        return realcapi;
    }

    public void setRealcapi(String realcapi) {
        this.realcapi = realcapi == null ? null : realcapi.trim();
    }

    public Date getShouldtime() {
        return shouldtime;
    }

    public void setShouldtime(Date shouldtime) {
        this.shouldtime = shouldtime;
    }

    public String getShouldcapi() {
        return shouldcapi;
    }

    public void setShouldcapi(String shouldcapi) {
        this.shouldcapi = shouldcapi == null ? null : shouldcapi.trim();
    }

    public String getStockpercent() {
        return stockpercent;
    }

    public void setStockpercent(String stockpercent) {
        this.stockpercent = stockpercent == null ? null : stockpercent.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}