package com.verificer.biz.biz.pospay.entity.req;

public class UpdCatReq {
    private Long uid;
    private String name;
    private Long parentId; //（0:顶级分类     空,-1:不改变父分类）

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
