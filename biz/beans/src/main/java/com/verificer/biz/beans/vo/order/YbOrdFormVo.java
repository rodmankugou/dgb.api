package com.verificer.biz.beans.vo.order;

import java.util.List;

public class YbOrdFormVo extends OrdFormVo {
    private Long posOrdId;
    private Long posCashierId;
    private Long posOrdTime;
    private Long posMemberId;


    public Long getPosOrdId() {
        return posOrdId;
    }

    public void setPosOrdId(Long posOrdId) {
        this.posOrdId = posOrdId;
    }

    public Long getPosCashierId() {
        return posCashierId;
    }

    public void setPosCashierId(Long posCashierId) {
        this.posCashierId = posCashierId;
    }

    public Long getPosOrdTime() {
        return posOrdTime;
    }

    public void setPosOrdTime(Long posOrdTime) {
        this.posOrdTime = posOrdTime;
    }

    public Long getPosMemberId() {
        return posMemberId;
    }

    public void setPosMemberId(Long posMemberId) {
        this.posMemberId = posMemberId;
    }

}
