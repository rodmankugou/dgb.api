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
}
