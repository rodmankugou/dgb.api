package com.verificer.biz.biz.service;

import com.verificer.beans.IdVo;
import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.beans.vo.order.OrdFormVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.beans.vo.req.adjust.AdjFormVo;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.SettleItemVo;
import com.verificer.biz.beans.vo.settle.SettleOrdVo;
import com.verificer.biz.beans.vo.settle.req.*;
import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.member.MemberPageVo;
import com.verificer.biz.beans.vo.user.member.MemberRankVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.beans.vo.user.member.MemberVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.user.req.RefereeListReqVo;
import com.verificer.biz.beans.vo.user.req.RefereeStaReqVo;
import com.verificer.biz.beans.vo.user.req.ReferrerWithdrawPageReqVo;
import com.verificer.biz.beans.vo.user.withdraw.*;

import java.util.List;

/**
 * Created by 35336 on 2021/2/28.
 */
public interface BizService {




    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<BrandVo> brandPage(BrandPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int brandCount(BrandPageQryVo qryVo);

    /**
     * 品牌列表
     * @param qryVo
     * @return
     */
    List<BrandVo> brandList(BrandListQryVo qryVo);

    /**
     * 新增Brand
     * @param formVo
     */
    void brandAdd(BrandFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void brandUpd(BrandFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void brandDel(BrandDelVo delVo);

    /**
     * 分类列表（不分页）
     * @param qryVo
     * @return
     */
    List<CatVo> catList(CatListQryVo qryVo);

    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<CatVo> catPage(CatPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int catCount(CatPageQryVo qryVo);


    /**
     * 新增Brand
     * @param formVo
     */
    void catAdd(CatFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void catUpd(CatFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void catDel(CatDelVo delVo);

    /**
     * 分页查询商品
     * @param qryVo
     * @return
     */
    List<GoodsVo> goodsPage(GoodsQryVo qryVo);

    /**
     * 统计符合查询条件的商品数
     * @param qryVo
     * @return
     */
    int goodsCount(GoodsQryVo qryVo);

    /**
     * 新增商品
     * @param formVo
     */
    void goodsAdd(GoodsFormVo formVo);

    /**
     * 修改商品
     * @param formVo
     */
    void goodsUpd(GoodsFormVo formVo);

    /**
     * 删除（放入回收站），商品列表的删除功能使用该接口，商品被删除后将被放入回收站
     * @param reqVo
     */
    void goodsRubbish(GoodsRubbishVo reqVo);

    /**
     * 恢复，将回收站中的商品恢复
     * @param reqVo
     */
    void goodsRecover(GoodsRecoverVo reqVo);

    /**
     * 删除，删除回收站中的商品，回收站中的商品被删除后将不可恢复
     * @param reqVo
     */
    void goodsDel(GoodsDelVo reqVo);


    /**
     * 上架/下架
     * @param reqVo
     */
    void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo);

    /**
     * 仓库列表（分页）
     * @param qryVo
     * @return
     */
    List<StageVo> stagePage(StagePageVo qryVo);

    /**
     * 统计符合条件的仓库数
     * @param qryVo
     * @return
     */
    int stageCount(StagePageVo qryVo);

    /**
     * 新增仓库
     * @param formVo
     */
    void stageAdd(StageFormVo formVo);

    /**
     * 修改仓库
     * @param formVo
     */
    void stageUpd(StageFormVo formVo);

    /**
     * 仓库列表
     * @return
     */
    List<StageVo> stageList();

    /**
     * 店铺列表（不分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopList(ShopListVo qryVo);

    /**
     * 店铺列表（分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopPage(ShopPageVo qryVo);

    /**
     * 店铺详情
     * @param reqVo
     * @return
     */
    ShopVo shopDetail(ShopIdVo reqVo);


    /**
     * 统计符合条件的店铺数
     * @param qryVo
     * @return
     */
    int shopCount(ShopPageVo qryVo);

    /**
     * 增加店铺
     * @param formVo
     * @return
     */
    void shopAdd(ShopFormVo formVo);

    /**
     * 修改店铺
     * @param formVo
     */
    void shopUpd(ShopFormVo formVo);

    /**
     * 删除店铺
     * @param delVo
     */
    void shopDel(ShopDelVo delVo);

    /**
     * 解冻/冻结店铺
     * @param reqVo
     */
    void shopUpdFrozenSta(ShopFrozenVo reqVo);



    /**
     * 调货列表（分页）
     * @param qryVo
     * @return
     */
    List<AdjustVo> adjustPage(AdjustPageVo qryVo);

    /**
     * 统计符合条件的调货记录数
     * @param qryVo
     * @return
     */
    int adjustCount(AdjustPageVo qryVo);

    /**
     * 新增订单
     * @param formVo
     * @return
     */
    Long orderAdd(OrdFormVo2 formVo);

    /**
     * 订单列表
     * @param qryVo
     * @return
     */
    List<DbgOrderVo> orderPage(OrderPageVo qryVo);

    /**
     * 统计符合条件的订单数
     * @param qryVo
     * @return
     */
    int orderCount(OrderPageVo qryVo);

    /**
     * 订单详情
     * @return
     */
    DbgOrderVo orderDetail(Long id);

    /**
     * 同步银豹数据，临时
     * @param
     */
    void ybSync();


    /**
     * 获取某个仓库/店铺所有的商品库存
     * @param qryVo
     * @return
     */
    List<MerStockVo> merStockList(StockMerQryVo qryVo);

    /**
     * 调货
     * @param form
     */
    void adjust(AdjFormVo form);

    /**
     * 批量调货
     * @param formList
     */
    void adjustBatch(List<AdjFormVo> formList);

    /**
     * 处理银豹数据商品同步任务
     * @return
     */
    int handlePosGoodsSyncTask();

    /**
     * 处理银豹数据订单同步任务
     * @return
     */
    int handlePosOrderSyncTask(String shopId) throws Exception;

    Long orderCreate(OrdFormVo fvo);


    List<UserVo> userPage(UserPageVo qryVo);

    int userCount(UserPageVo qryVo);

    /**
     * 设置引荐人
     * @param reqVo
     */
    void userSetReferrer(UserSetRefVo reqVo);

    UserVo userDetail(IdVo idVo);

    List<MemberTypeVo> memberTypeList();

    /**
     * 会员充值
     * @param reqVo
     * @return
     */
    PayReqVo memberCharge(MemberChargeVo reqVo);

    Long wxLogin(WxLoginReqVo reqVo);

    /**
     * 绑定手机号码
     * @param reqVo
     */
    void userBindMobile(BindMobileVo reqVo);

    /**
     * 会员支付成功回调
     * @param paySucVo
     */
    void memberOnPaySuc(PaySucVo paySucVo);

    /**
     * 引荐人的下线列表
     * @param reqVo
     * @return
     */
    List<RefereeVo> userRefereeList(RefereeListReqVo reqVo);

    /**
     * 引荐人的下线人数
     * @param reqVo
     * @return
     */
    int userRefereeCount(RefereeListReqVo reqVo);

    /**
     * 引荐人统计
     * @param reqVo
     * @return
     */
    ReferrerStaVo userRefereeSta(RefereeStaReqVo reqVo);

    /**
     * 引荐人提现记录
     * @param reqVo
     * @return
     */
    List<UserWithdrawVo> userWithdrawPage(ReferrerWithdrawPageReqVo reqVo);

    /**
     * 引荐人提现记录数
     * @param reqVo
     * @return
     */
    int userWithdrawCount(ReferrerWithdrawPageReqVo reqVo);

    /**
     * 提现申请
     * @param reqVo
     */
    long referrerWithdrawApply(ReferrerFormVo reqVo);

    /**
     * 引荐人提现审核
     * @param reqVo
     */
    void referrerWithdrawReview(ReferrerReviewVo reqVo);

    /**
     * 引荐人提现确认转账
     * @param reqVo
     */
    void referrerWithdrawTransfer(ReferrerTransferVo reqVo);

    List<ReferrerWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageVo qryVo);

    int referrerWithdrawCount(ReferrerWithdrawPageVo qryVo);

    /**
     * 会员概况
     * @return
     */
    MemberStaVo memberSta();

    /**
     * 会员排行榜（按省份）
     * @return
     */
    List<MemberRankVo> memberRank();

    List<MemberVo> memberPage(MemberPageVo reqVo);

    int memberCount(MemberPageVo reqVo);

    /**
     * 清理过期会员
     * @return
     */
    int clearExpireMember();

    /**
     * 结算概况
     * @return
     */
    MemberStaVo settleSta(SettleStaQryVo reqVo);

    /**
     * 平台收支日志列表（分页）
     * @param reqVo
     * @return
     */
    List<PlaIncomeLogVo> plaIncomeLogPage(PlaIncomeLogQryVo reqVo);

    /**
     * 平台收支日志数
     * @param reqVo
     * @return
     */
    int plaIncomeLogCount(PlaIncomeLogQryVo reqVo);

    List<SettleOrdVo> settleOrdPage(SettleOrdQryVo reqVo);

    int settleOrdCount(SettleOrdQryVo reqVo);

    /**
     * 结算转账
     * @param reqVo
     */
    void settleOrdTransfer(SettleTransferVo reqVo);

    List<SettleItemVo> settleItemPage(SettleItemQryVo reqVo);

    int settleItemCount(SettleItemQryVo reqVo);

    /**
     * 判断是否需要结算
     * @param
     * @return 如果存在需要的结算项，则返回结算单ID，否则返回null
     */
    Long trySettle();

    /**
     * 结算
     * @param ordId 结算单ID
     * @return 如果存在结算项，则返回1，否则返回0，返回0时表示该结算单已结算完毕
     */
    int settle(Long ordId);
}

