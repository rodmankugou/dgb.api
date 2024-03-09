package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.req.DbgOrderFormVo2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface YbToolsService {

    /**
     * 同步银豹数据，临时
     * @param formVo
     */
    void ybSync();
}
