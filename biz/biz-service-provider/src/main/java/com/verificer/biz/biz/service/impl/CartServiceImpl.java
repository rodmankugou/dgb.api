package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.cart.CartVo;
import com.verificer.biz.beans.vo.cart.ShopCartVo;
import com.verificer.biz.beans.vo.cart.req.CartAddVo;
import com.verificer.biz.beans.vo.cart.req.CartJoinVo;
import com.verificer.biz.beans.vo.cart.req.CartQryVo;
import com.verificer.biz.biz.entity.Cart;
import com.verificer.biz.biz.mapper.CartMapper;
import com.verificer.biz.biz.service.CartService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.StockCommon;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper mapper;

    @Autowired
    GoodsCommon goodsCommon;
    
    @Autowired
    ShopCommon shopCommon;

    @Autowired
    StockCommon stockCommon;

    @Autowired
    UserCommon userCommon;

    private CartVo toVo(Cart e){
        if(e == null)
            return  null;
        CartVo vo = new CartVo();
        SBeanUtils.copyProperties2(e,vo);
        vo.setGoodsName(goodsCommon.getGoodsName(vo.getGoodsId()));
        vo.setSpecName(goodsCommon.getSpecName(vo.getSpecId()));
        vo.setImg(goodsCommon.getSpecImg(vo.getSpecId()));
        if(e.getShopFlag())
            vo.setStock(stockCommon.getShopStockCount(e.getShopId(),e.getSpecId()));
        else
            vo.setStock(stockCommon.getPlaStockCount(e.getSpecId()));
        return vo;
    }

    private List<CartVo> toVoList(List<Cart> list){

        List<CartVo> voList = new LinkedList<>();
        for(Cart e : list){
            voList.add(toVo(e));
        }
        return voList;
    }

    @Override
    public List<CartVo> cartPlaList(CartQryVo reqVo) {
        reqVo.setShopFlag(false);
        List<Cart> list = mapper.list(reqVo);
        return toVoList(list);
    }

    @Override
    public List<ShopCartVo> cartShopList(CartQryVo reqVo) {
        reqVo.setShopFlag(true);
        List<Cart> list = mapper.list(reqVo);

        return buildTree(list);
    }



    private List<ShopCartVo> buildTree(List<Cart> list){
        Map<String,ShopCartVo> map = new LinkedHashMap<>();

        for(Cart cart : list){
            if(!map.containsKey(cart.getShopId())){
                ShopCartVo shop = new ShopCartVo();
                shop.setShopId(cart.getShopId());
                shop.setShopName(shopCommon.getName(cart.getShopId()));
                shop.setItems(new LinkedList<>());

                map.put(cart.getShopId(),shop);
            }
            CartVo cartVo = toVo(cart);
            map.get(cart.getShopId()).getItems().add(cartVo);
        }

        List<ShopCartVo> rst = new LinkedList<>();
        for(Map.Entry<String,ShopCartVo> entry : map.entrySet())
            rst.add(entry.getValue());

        return rst;
    }

    @Override
    public void cartJoin(CartJoinVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        SCheckUtil.notEmpty(reqVo.getShopFlag(),"shopFlag");
        SCheckUtil.notEmpty(reqVo.getSpecId(),"specId");
        if(reqVo.getShopFlag())
            SCheckUtil.notEmpty(reqVo.getShopId(),"shopId");
        SCheckUtil.lgThanAndNotNull(reqVo.getCount(),false,0,"count");

        //使用用户锁
        userCommon.lockByUser(reqVo.getUserId());

        Cart cart = mapper.selectBySpecIdAndOthers(reqVo.getUserId(),reqVo.getSpecId(),reqVo.getShopFlag(),reqVo.getShopId());
        if(cart != null){
            cart.setCount(cart.getCount()+ reqVo.getCount());
            update(cart);
            return;
        }

        cart = new Cart();
        cart.setUserId(reqVo.getUserId());
        cart.setGoodsId(goodsCommon.getGoodsId(reqVo.getSpecId()));
        cart.setSpecId(reqVo.getSpecId());
        cart.setShopFlag(reqVo.getShopFlag());
        cart.setShopId(reqVo.getShopId());
        cart.setCount(reqVo.getCount());

        mapper.insertSelective(cart);
    }

    @Override
    public void cartAdd(CartAddVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        SCheckUtil.notEmpty(reqVo.getId(),"id");

        userCommon.lockByUser(reqVo.getUserId());
        Cart cart = mapper.selectByPrimaryKey(reqVo.getId());
        if(!cart.getUserId().equals(reqVo.getUserId()))
            throw new BaseException(ErrCode.PERMISSION_DENIED);
        if(cart == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(cart.getCount() == 1)
            throw new BaseException(ErrCode.CART_CAN_NOT_SUB);

        int count = cart.getCount() + reqVo.getCount();
        cart.setCount(count <= 1 ? 1 : count);
        update(cart);
    }

    @Override
    public void cartDel(CartAddVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        SCheckUtil.notEmpty(reqVo.getId(),"id");

        userCommon.lockByUser(reqVo.getUserId());
        Cart cart = mapper.selectByPrimaryKey(reqVo.getId());
        if(cart == null || !cart.getUserId().equals(reqVo.getUserId()))
            return;
        mapper.deleteByPrimaryKey(cart.getId());
    }

    /**
     * 删除购物车内项目，创建订单时调用
     * @param userId
     * @param ids
     */
    public void carDel(Long userId,List<Long> ids){
        SCheckUtil.notEmpty(userId,"userId");

        userCommon.lockByUser(userId);
        for(Long id  : ids){
            Cart cart = mapper.selectByPrimaryKey(id);
            if(cart == null )
                return;
            if(!cart.getUserId().equals(userId))
                throw new BaseException(ErrCode.PERMISSION_DENIED);

            mapper.deleteByPrimaryKey(id);
        }

    }

    private void update(Cart cart){
        if(cart.getCount() < 1)
            cart.setCount(1);
        mapper.updateByPrimaryKeySelective(cart);
    }
}
