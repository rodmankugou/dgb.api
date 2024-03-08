package com.verificer.biz.biz.pospay.entity;

import com.amazonaws.services.rekognition.model.Face;

import java.io.Serializable;

public class YbCat implements Serializable {
    private Long id;
    private Long uid;
    private String name;
    private String parentUid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentUid() {
        return parentUid;
    }

    public void setParentUid(String parentUid) {
        this.parentUid = parentUid;
    }


}
