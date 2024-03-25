package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.cart.CartVo;
import com.verificer.biz.beans.vo.cart.ShopCartVo;
import com.verificer.biz.beans.vo.cart.req.CartAddVo;
import com.verificer.biz.beans.vo.cart.req.CartJoinVo;
import com.verificer.biz.beans.vo.cart.req.CartQryVo;

import java.util.List;

public interface CartService {

    /**
     *
     * @return
     */
    List<CartVo> cartPlaList(CartQryVo reqVo);

    /**
     *
     * @return
     */
    List<ShopCartVo> cartShopList(CartQryVo reqVo);

    /**
     * 加入购物车
     * @param reqVo
     * @return
     */
    void cartJoin(CartJoinVo reqVo);

    /**
     * 购物车增减数量
     * @param reqVo
     * @return
     */
    void cartAdd(CartAddVo reqVo);

    /**
     * 删除购物车条目
     * @param reqVo
     */
    void cartDel(CartAddVo reqVo);


    /**
     * 删除购物车内项目，创建订单时调用
     * @param userId
     * @param ids
     */
    void carDel(Long userId,List<Long> ids);
}
