package com.verificer.biz.biz.service;

import com.verificer.beans.AppReqVo;
import com.verificer.biz.beans.vo.AppIdVo;
import com.verificer.biz.beans.vo.addr.AddrVo;
import com.verificer.biz.beans.vo.addr.req.AddrFormVo;
import com.verificer.biz.beans.vo.addr.req.AddrQryVo;
import com.verificer.biz.biz.entity.Addr;

import java.util.List;

public interface AddrService{
    Addr getById(Long addrId, Long userId);



    /**
     * 用户地址列表-分页
     * @param reqVo
     * @return
     */
    List<AddrVo> addrPage(AddrQryVo reqVo);

    /**
     * 获取用户默认地址
     * @param reqVo
     * @return
     */
    AddrVo addrDefault(AppReqVo reqVo);

    /**
     * 新增用户地址
     * @param reqVo
     * @return
     */
    void addrAdd(AddrFormVo reqVo);

    /**
     * 修改用户地址
     * @param reqVo
     * @return
     */
    void addrUpd(AddrFormVo reqVo);

    /**
     * 删除
     * @param reqVo
     * @return
     */
    void addrDel(AppIdVo reqVo);
}
