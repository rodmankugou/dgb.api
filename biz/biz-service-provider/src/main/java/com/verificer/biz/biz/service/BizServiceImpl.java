package com.verificer.biz.biz.service;

import com.verificer.beans.IdVo;
import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.TreeAdjItemVo;
import com.verificer.biz.beans.vo.adjust.req.AdjItemTreeQryVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.beans.vo.cart.CartVo;
import com.verificer.biz.beans.vo.cart.ShopCartVo;
import com.verificer.biz.beans.vo.cart.req.CartAddVo;
import com.verificer.biz.beans.vo.cart.req.CartJoinVo;
import com.verificer.biz.beans.vo.cart.req.CartQryVo;
import com.verificer.biz.beans.vo.integral.AppIntegralLogVo;
import com.verificer.biz.beans.vo.integral.IntegralListVo;
import com.verificer.biz.beans.vo.member.AppMemberVo;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.beans.vo.order.OrdFormVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.beans.vo.req.adjust.AdjFormVo;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.SettleItemVo;
import com.verificer.biz.beans.vo.settle.SettleOrdVo;
import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.*;
import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.member.MemberPageVo;
import com.verificer.biz.beans.vo.user.member.MemberRankVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.beans.vo.user.member.MemberVo;
import com.verificer.biz.beans.vo.user.req.*;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.user.withdraw.*;
import com.verificer.biz.biz.service.adj.AdjItemService;
import com.verificer.biz.biz.service.adj.AdjOrderService;
import com.verificer.biz.biz.service.core.order.OrdCoreService;
import com.verificer.biz.biz.service.core.user.*;
import com.verificer.biz.biz.service.settle.PlaIncomeLogService;
import com.verificer.biz.biz.service.settle.SettleItemService;
import com.verificer.biz.biz.service.settle.SettleOrdService;
import com.verificer.biz.biz.service.settle.SettleService;
import com.verificer.dubbo.BaseDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bizService")
public class BizServiceImpl extends BaseDubboService implements BizService {
    @Autowired
    BrandService brandService;

    @Autowired
    CatService catService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    StageService stageService;

    @Autowired
    ShopService shopService;

    @Autowired
    AdjustService adjustService;

    @Autowired
    DbgOrderService dbgOrderService;

    @Autowired
    YbToolsService ybToolsService;

    @Autowired
    StockService stockService;

    @Autowired
    PosSyncTaskService posSyncTaskService;

    @Autowired
    OrdCoreService ordCoreService;

    @Autowired
    UserCoreService userCoreService;

    @Autowired
    UserService userService;

    @Autowired
    MemberTypeService memberTypeService;

    @Autowired
    MemberOrderService memberOrderService;

    @Autowired
    ReferrerWithdrawService referrerWithdrawService;

    @Autowired
    MemberService memberService;

    @Autowired
    SettleService settleService;

    @Autowired
    PlaIncomeLogService plaIncomeLogService;

    @Autowired
    SettleOrdService settleOrdService;

    @Autowired
    SettleItemService settleItemService;

    @Autowired
    AdjOrderService adjOrderService;

    @Autowired
    AdjItemService adjItemService;

    @Autowired
    CartService cartService;

    @Autowired
    IntegralService integralService;


    @Override
    public List<BrandVo> brandPage(BrandPageQryVo qryVo) {
        return brandService.brandPage(qryVo);
    }

    @Override
    public int brandCount(BrandPageQryVo qryVo) {
        return brandService.brandCount(qryVo);
    }

    @Override
    public List<BrandVo> brandList(BrandListQryVo qryVo) {
        return brandService.brandList(qryVo);
    }

    @Override
    public void brandAdd(BrandFormVo formVo) {
        brandService.brandAdd(formVo);
    }

    @Override
    public void brandUpd(BrandFormVo formVo) {
        brandService.brandUpd(formVo);
    }

    @Override
    public void brandDel(BrandDelVo delVo) {
        brandService.brandDel(delVo);
    }

    @Override
    public List<CatVo> catList(CatListQryVo qryVo) {
        return catService.catList(qryVo);
    }

    @Override
    public List<CatVo> catPage(CatPageQryVo qryVo) {
        return catService.catPage(qryVo);
    }

    @Override
    public int catCount(CatPageQryVo qryVo) {
        return catService.catCount(qryVo);
    }

    @Override
    public void catAdd(CatFormVo formVo) {
        catService.catAdd(formVo);
    }

    @Override
    public void catUpd(CatFormVo formVo) {
        catService.catUpd(formVo);
    }

    @Override
    public void catDel(CatDelVo delVo) {
        catService.catDel(delVo);
    }

    @Override
    public List<GoodsVo> goodsPage(GoodsQryVo qryVo) {
        return goodsService.goodsPage(qryVo);
    }

    @Override
    public int goodsCount(GoodsQryVo qryVo) {
        return goodsService.goodsCount(qryVo);
    }

    @Override
    public void goodsAdd(GoodsFormVo formVo) {
        goodsService.goodsAdd(formVo);
    }

    @Override
    public void goodsUpd(GoodsFormVo formVo) {
        goodsService.goodsUpd(formVo);
    }

    @Override
    public void goodsRubbish(GoodsRubbishVo reqVo) {
        goodsService.goodsRubbish(reqVo);
    }

    @Override
    public void goodsRecover(GoodsRecoverVo reqVo) {
        goodsService.goodsRecover(reqVo);
    }

    @Override
    public void goodsDel(GoodsDelVo reqVo) {
        goodsService.goodsDel(reqVo);
    }

    @Override
    public void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo) {
        goodsService.goodsUpdSaleFlag(reqVo);
    }

    @Override
    public List<StageVo> stagePage(StagePageVo qryVo) {
        return stageService.stagePage(qryVo);
    }

    @Override
    public int stageCount(StagePageVo qryVo) {
        return stageService.stageCount(qryVo);
    }

    @Override
    public void stageAdd(StageFormVo formVo) {
        stageService.stageAdd(formVo);
    }

    @Override
    public void stageUpd(StageFormVo formVo) {
        stageService.stageUpd(formVo);
    }

    @Override
    public List<StageVo> stageList() {
        return stageService.stageList();
    }

    @Override
    public List<ShopVo> shopList(ShopListVo qryVo) {
        return shopService.shopVoList(qryVo);
    }

    @Override
    public List<ShopVo> shopPage(ShopPageVo qryVo) {
        return shopService.shopPage(qryVo);
    }

    @Override
    public ShopVo shopDetail(ShopIdVo reqVo) {
        return shopService.shopDetail(reqVo);
    }

    @Override
    public int shopCount(ShopPageVo qryVo) {
        return shopService.shopCount(qryVo);
    }

    @Override
    public void shopAdd(ShopFormVo formVo) {
        shopService.shopAdd(formVo);
    }

    @Override
    public void shopUpd(ShopFormVo formVo) {
        shopService.shopUpd(formVo);
    }

    @Override
    public void shopDel(ShopDelVo delVo) {
        shopService.shopDel(delVo);
    }

    @Override
    public void shopUpdFrozenSta(ShopFrozenVo reqVo) {
        shopService.shopUpdFrozenSta(reqVo);
    }



    @Override
    public List<AdjustVo> adjustPage(AdjustPageVo qryVo) {
        return adjustService.adjustPage(qryVo);
    }

    @Override
    public int adjustCount(AdjustPageVo qryVo) {
        return adjustService.adjustCount(qryVo);
    }

    @Override
    public Long orderAdd(OrdFormVo2 formVo) {
        return dbgOrderService.orderAdd(formVo);
    }

    @Override
    public List<DbgOrderVo> orderPage(OrderPageVo qryVo) {
        return dbgOrderService.orderPage(qryVo);
    }

    @Override
    public int orderCount(OrderPageVo qryVo) {
        return dbgOrderService.orderCount(qryVo);
    }

    @Override
    public DbgOrderVo orderDetail(Long id) {
        return dbgOrderService.orderDetail(id);
    }

    @Override
    public void ybSync() {
        ybToolsService.ybSync();
    }

    @Override
    public List<MerStockVo> merStockList(StockMerQryVo qryVo) {
        return stockService.merStockList(qryVo);
    }

    @Override
    public void adjust(AdjFormVo form) {
        adjustService.adjust(form);
    }

    @Override
    public void adjustBatch(List<AdjFormVo> formList) {
        adjustService.adjustBatch(formList);

    }

    @Override
    public int handlePosGoodsSyncTask() {
        return posSyncTaskService.handlePosGoodsSyncTask();
    }

    @Override
    public int handlePosOrderSyncTask(String shopId) throws Exception {
        return posSyncTaskService.handlePosOrderSyncTask(shopId);
    }

    @Override
    public Long orderCreate(OrdFormVo fvo) {
        return ordCoreService.create(fvo);
    }

    @Override
    public List<UserVo> userPage(UserPageVo qryVo) {
        return userService.userPage(qryVo);
    }

    @Override
    public int userCount(UserPageVo qryVo) {
        return userService.userCount(qryVo);
    }

    @Override
    public void userSetReferrer(UserSetRefVo reqVo) {
        userCoreService.userSetReferrer(reqVo);
    }

    @Override
    public UserVo userDetail(IdVo idVo) {
        return userService.userDetail(idVo);
    }

    @Override
    public List<MemberTypeVo> memberTypeList() {

        return memberTypeService.memberTypeList();
    }

    @Override
    public PayReqVo memberCharge(MemberChargeVo reqVo) {

        return memberOrderService.memberCharge(reqVo);
    }

    @Override
    public Long wxLogin(WxLoginReqVo reqVo) {
        return userCoreService.wxLogin(reqVo);
    }

    @Override
    public void userBindMobile(BindMobileVo reqVo) {
        userCoreService.userBindMobile(reqVo);
    }

    @Override
    public void memberOnPaySuc(PaySucVo paySucVo) {
        memberOrderService.onPaySuc(paySucVo);
    }

    @Override
    public List<RefereeVo> userRefereeList(RefereeListReqVo reqVo) {
        return userService.userRefereeList(reqVo);
    }

    @Override
    public int userRefereeCount(RefereeListReqVo reqVo) {
        return userService.userRefereeCount(reqVo);
    }

    @Override
    public ReferrerStaVo userRefereeSta(RefereeStaReqVo reqVo) {
        return userService.userRefereeSta(reqVo);
    }

    @Override
    public List<UserWithdrawVo> userWithdrawPage(ReferrerWithdrawPageReqVo reqVo) {
        return userService.referrerWithdrawPage(reqVo);
    }

    @Override
    public int userWithdrawCount(ReferrerWithdrawPageReqVo reqVo) {
        return userService.referrerWithdrawCount(reqVo);
    }

    @Override
    public long referrerWithdrawApply(ReferrerFormVo reqVo) {
        return referrerWithdrawService.referrerWithdrawApply(reqVo);
    }

    @Override
    public void referrerWithdrawReview(ReferrerReviewVo reqVo) {
        referrerWithdrawService.referrerWithdrawReview(reqVo);
    }

    @Override
    public void referrerWithdrawTransfer(ReferrerTransferVo reqVo) {
        referrerWithdrawService.referrerWithdrawTransfer(reqVo);
    }

    @Override
    public List<ReferrerWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageVo qryVo) {
        return referrerWithdrawService.referrerWithdrawPage(qryVo);
    }

    @Override
    public int referrerWithdrawCount(ReferrerWithdrawPageVo qryVo) {
        return referrerWithdrawService.referrerWithdrawCount(qryVo);
    }

    @Override
    public MemberStaVo memberSta() {
        return memberService.memberSta();
    }

    @Override
    public List<MemberRankVo> memberRank() {
        return memberService.memberRank();
    }

    @Override
    public List<MemberVo> memberPage(MemberPageVo reqVo) {
        return memberService.memberPage(reqVo);
    }

    @Override
    public int memberCount(MemberPageVo reqVo) {
        return memberService.memberCount(reqVo);
    }

    @Override
    public int clearExpireMember() {
        return userCoreService.clearExpireMember();
    }

    @Override
    public SettleStaVo settleSta(SettleStaQryVo reqVo) {
        return settleService.settleSta(reqVo);
    }

    @Override
    public List<PlaIncomeLogVo> plaIncomeLogPage(PlaIncomeLogQryVo reqVo) {
        return plaIncomeLogService.plaIncomeLogPage(reqVo);
    }

    @Override
    public int plaIncomeLogCount(PlaIncomeLogQryVo reqVo) {
        return plaIncomeLogService.plaIncomeLogCount(reqVo);
    }

    @Override
    public List<SettleOrdVo> settleOrdPage(SettleOrdQryVo reqVo) {
        return settleOrdService.settleOrdPage(reqVo);
    }

    @Override
    public int settleOrdCount(SettleOrdQryVo reqVo) {
        return settleOrdService.settleOrdCount(reqVo);
    }

    @Override
    public void settleOrdTransfer(SettleTransferVo reqVo) {
        settleOrdService.settleOrdTransfer(reqVo);
    }

    @Override
    public List<SettleItemVo> settleItemPage(SettleItemQryVo reqVo) {
        return settleItemService.settleItemPage(reqVo);
    }

    @Override
    public int settleItemCount(SettleItemQryVo reqVo) {
        return settleItemService.settleItemCount(reqVo);
    }

    @Override
    public Long trySettle() {
        return settleOrdService.trySettle();
    }

    @Override
    public int settle(Long ordId) {
        return settleOrdService.settle(ordId);
    }

    @Override
    public SettleOrdVo settleOrdDetail(IdVo idVo) {
        return settleOrdService.settleOrdDetail(idVo);
    }

    @Override
    public List<AdjOrderVo> adjOrdPage(AdjOrderQryVo qryVo) {
        return adjOrderService.adjOrdPage(qryVo);
    }

    @Override
    public int adjOrdCount(AdjOrderQryVo qryVo) {
        return adjOrderService.adjOrdCount(qryVo);
    }

    @Override
    public void adjOrdCreate(AdjOrdFormVo formVo) {
        adjOrderService.adjOrdCreate(formVo);
    }

    @Override
    public void adjOrdConfirm(AdjOrdConfirmVo formVo) {
        adjOrderService.adjOrdConfirm(formVo);
    }

    @Override
    public List<TreeAdjItemVo> adjItemTree(AdjItemTreeQryVo qryVo) {
        return adjItemService.adjItemTree(qryVo);
    }

    @Override
    public List<CartVo> cartPlaList(CartQryVo reqVo) {
        return cartService.cartPlaList(reqVo);
    }

    @Override
    public List<ShopCartVo> cartShopList(CartQryVo reqVo) {
        return cartService.cartShopList(reqVo);
    }

    @Override
    public void cartJoin(CartJoinVo reqVo) {
        cartService.cartJoin(reqVo);
    }

    @Override
    public void cartAdd(CartAddVo reqVo) {
        cartService.cartAdd(reqVo);
    }

    @Override
    public AppMemberVo memberInfo(IdVo idVo) {
        return memberService.memberInfo(idVo);
    }

    @Override
    public BigDecimal integralBalance(IdVo idVo) {
        return integralService.integralBalance(idVo);
    }

    @Override
    public List<AppIntegralLogVo> integralList(IntegralListVo reqVo) {
        return integralService.integralList(reqVo);
    }

    @Override
    public void userSetAvatar(SetAvatarVo reqVo) {
        userCoreService.userSetAvatar(reqVo);
    }

    @Override
    public void userSetNickname(SetNicknameVo reqVo) {
        userCoreService.userSetNickname(reqVo);
    }

    @Override
    public AccountVo createAccountIfNeed(String customerId, String subName) {
        return userCoreService.createAccountIfNeed(customerId,subName);
    }
}
