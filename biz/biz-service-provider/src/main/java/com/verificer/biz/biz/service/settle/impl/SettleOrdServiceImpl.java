package com.verificer.biz.biz.service.settle.impl;

import com.verificer.ErrCode;
import com.verificer.beans.num.NumGenerator;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.vo.settle.SettleOrdVo;
import com.verificer.biz.beans.vo.settle.req.SettleOrdQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleTransferVo;
import com.verificer.biz.biz.entity.Settle;
import com.verificer.biz.biz.entity.SettleItem;
import com.verificer.biz.biz.entity.SettleOrder;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.mapper.SettleOrderMapper;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.core.user.MemberService;
import com.verificer.biz.biz.service.settle.SettleItemService;
import com.verificer.biz.biz.service.settle.SettleOrdService;
import com.verificer.biz.biz.service.settle.SettleService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettleOrdServiceImpl implements SettleOrdService {

    @Autowired
    SettleOrderMapper mapper;

    @Autowired
    MemberService memberService;

    @Autowired
    SettleService settleService;

    @Autowired
    ShopCommon shopCommon;

    @Autowired
    SettleItemService settleItemService;

    private NumGenerator ordNumGenerator = new NumGenerator() {
        @Override
        public boolean isNumExist(String num) {
            return mapper.selectByOrdNum(num) != null;
        }
    };


    @Override
    public List<SettleOrdVo> settleOrdPage(SettleOrdQryVo reqVo) {
        List<SettleOrdVo> voList = mapper.page(reqVo);
        for(SettleOrdVo vo : voList){
            vo.setCurMemberCount(memberService.shopMemberCount(vo.getShopId()));
        }
        return voList;
    }

    @Override
    public int settleOrdCount(SettleOrdQryVo reqVo) {
        return mapper.count(reqVo);
    }

    @Override
    public void settleOrdTransfer(SettleTransferVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getCertificateImg(),"Certificate Img");
        SCheckUtil.notEmpty(reqVo.getStaffId(),"Staff ID");
        SCheckUtil.notEmpty(reqVo.getStaffName(),"Staff Name");

        SettleOrder so = mapper.getAndLock(reqVo.getId());
        if(so == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(so.getSettleFlag())
            throw new BaseException(ErrCode.CAN_NOT_REPEAT_SETTLE);

        so.setSettleFlag(true);
        so.setTransferStaffId(reqVo.getStaffId());
        so.setTransferStaffName(reqVo.getStaffName());
        so.setSettleTime(System.currentTimeMillis());
        so.setCertificateImg(reqVo.getCertificateImg());
        mapper.updateByPrimaryKeySelective(so);

        List<SettleItem> items = settleItemService.getBySettleOrdId(so.getId());
        settleService.afterSettle(items);

        settleItemService.afterSettle(so);
    }

    @Override
    public Long trySettle() {
        Long now = System.currentTimeMillis();
        //T 检查当月是否还有未计算完成的订单，如果有，则返回其ID
        SettleOrder so = mapper.selectUnCalFinishLimit1(SDateUtil.getYear(now),SDateUtil.getMonth(now));
        if(so != null)
            return so.getId();

        Settle settle = settleService.getReadySettle(now,null);
        if(settle == null){
            return null;
        }

        //查看是否已存在Settle Order，考虑定时任务中断的情况
        Integer year = SDateUtil.getYear(settle.getNextCalTime());
        Integer month = SDateUtil.getMonth(settle.getNextCalTime());
        so = mapper.selectByShopIdAndMonth(settle.getShopId(),year,month);
        if(so == null){
            Shop shop = shopCommon.getById(settle.getShopId());
            if(shop == null)
                throw new BaseException(ErrCode.RECORD_NOT_EXIST);
            so = new SettleOrder();
            so.setOrdNum(UuidUtils.newUuid());
            so.setShopId(settle.getShopId());
            so.setCommissionRate(shop.getCommissionRate());
            so.setAmount(BigDecimal.ZERO);
            so.setCount(0);
            so.setYear(year);
            so.setMonth(month);
            so.setSettleFlag(false);
            so.setCalFinishFlag(false);
            so.setCreateTime(System.currentTimeMillis());
            mapper.insertSelective(so);

            so.setOrdNum(ordNumGenerator.genNum(BizConst.SETTLE_ORD_NUM_PREFIX,18,so.getId()));
        }
        return so.getId();
    }

    @Override
    public int settle(Long ordId) {
        SettleOrder so = mapper.getAndLock(ordId);
        if(so == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Long now = so.getCreateTime();
        Settle settle = settleService.getReadySettle(now,so.getShopId());

        if(settle == null){
            so.setCalFinishFlag(true);
            mapper.updateByPrimaryKey(so);
            return 0;
        }


        settleService.afterCalSettle(settle);

        settleItemService.createItem( so, settle);

        so.setAmount(so.getAmount().add(settle.getPhaseAmount()));
        so.setCount(so.getCount() +1);
        mapper.updateByPrimaryKeySelective(so);
        return 1;
    }


}
