package com.verificer.biz.beans.vo.order;


import com.verificer.biz.beans.vo.order.req.OrdFormVo;

/**
 * 补货单
 */
public class SupplyFormVo extends OrdFormVo {
    private Long rootOrderId;
    private Long origOrderId;
    private Long staffId;
    private String staffName;


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
