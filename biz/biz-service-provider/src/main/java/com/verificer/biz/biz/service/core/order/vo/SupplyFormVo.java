package com.verificer.biz.biz.service.core.order.vo;

import com.verificer.biz.beans.vo.req.OrdFormVo;
import com.verificer.biz.biz.entity.Addr;

import java.io.Serializable;

/**
 * 补货单
 */
public class SupplyFormVo extends OrdFormVo implements Serializable {
    private Addr addr;
    private Long rootOrderId;
    private Long origOrderId;
    private Long staffId;
    private String staffName;

    public Addr getAddr() {
        return addr;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    public Long getRootOrderId() {
        return rootOrderId;
    }

    public void setRootOrderId(Long rootOrderId) {
        this.rootOrderId = rootOrderId;
    }

    public Long getOrigOrderId() {
        return origOrderId;
    }

    public void setOrigOrderId(Long origOrderId) {
        this.origOrderId = origOrderId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
