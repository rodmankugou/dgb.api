package com.verificer.biz.beans.vo.evaluate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AEvaluateVo {

    @ApiModelProperty("评分，1｜2｜3｜4｜5")
    private Integer score;
    @ApiModelProperty("用户头像")
    private String userAvatar;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("图片列表，多个图片以符号@隔开，可能空")
    private String imgList;
    @ApiModelProperty("创建时间")
    private Long createTime;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
