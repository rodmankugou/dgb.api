package com.verificer.beans;

import java.io.Serializable;
import java.util.Date;

public class MobileUserIdentity implements Serializable{

    private static final long serialVersionUID = -2184798758385059556L;

    private int id;
    private String account;
    private Date lastVisitTime;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
