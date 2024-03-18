package com.verificer.biz.biz.service.core.order;

import com.verificer.biz.beans.vo.OrdFlowFormVo;
import com.verificer.biz.beans.vo.order.OrdFormVo;
import com.verificer.biz.biz.service.core.order.notify.IOrdListener;

public interface OrdCoreService {
    void addListener(IOrdListener listener);
    Long create(OrdFormVo ofo);

    void toNextStatus(OrdFlowFormVo flowFormVo);
}
