package com.verificer.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class ArtworkSubmitVo implements Serializable {
    private String imageUrl;
    private String miniImageUrl;
    private Boolean lockFlag;
    private Long collectionId;
    private String title;
    private String remark;
    private BigDecimal royalties;
    private String targetAddress; //铸币成功后的发放地址

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMiniImageUrl() {
        return miniImageUrl;
    }

    public void setMiniImageUrl(String miniImageUrl) {
        this.miniImageUrl = miniImageUrl;
    }

    public Boolean getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getRoyalties() {
        return royalties;
    }

    public void setRoyalties(BigDecimal royalties) {
        this.royalties = royalties;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }
}
