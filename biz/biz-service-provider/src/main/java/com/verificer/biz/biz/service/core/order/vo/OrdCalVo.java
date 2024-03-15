package com.verificer.biz.biz.service.core.order.vo;

import java.io.Serializable;
import java.util.List;

public class OrdCalVo implements Serializable {
    private List<OrdCalItemVo> items;

    public List<OrdCalItemVo> getItems() {
        return items;
    }

    public void setItems(List<OrdCalItemVo> items) {
        this.items = items;
    }
}
