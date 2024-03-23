package com.verificer.biz.beans.vo.evaluate;

import com.verificer.biz.beans.vo.feature.FeatureSpecVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EvaluateVo extends FeatureSpecVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("状态 1-待审核 2-通过 3-不通过")
    private Integer status;

    @ApiModelProperty("昵称")
    private String userName;


    @ApiModelProperty("评分分数")
    private Integer score;


    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("评论图片列表，多个图片时以符号@隔开")
    private String imgList;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("审核时间")
    private Long reviewTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Long getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Long reviewTime) {
        this.reviewTime = reviewTime;
    }
}
