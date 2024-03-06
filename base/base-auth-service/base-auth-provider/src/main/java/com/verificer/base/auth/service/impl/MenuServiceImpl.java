package com.verificer.base.auth.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.auth.entity.Menu;
import com.verificer.base.auth.mapper.MenuMapper;
import com.verificer.base.auth.service.MenuService;
import com.verificer.beans.MenuVo;
import com.verificer.beans.TreeNodeVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.TreeUtils;
import com.verificer.utils.web.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/1/7.
 */
@Transactional(rollbackFor = Exception.class)
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    /**
     * 获取某用户的菜单列表
     * @param userId
     * @return
     */
    @Override
    public List<TreeNodeVo<MenuVo>> getPermissionMenus(Long userId){
        if(userId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        List<Menu> menus = menuMapper.selectPermissionMenus(userId);
        List<MenuVo> voList = toVoList(menus);
        return TreeUtils.buildTreeDatas(voList,"code","parentCode",null);
    }

    @Override
    public List<TreeNodeVo<MenuVo>> getAllMenus() {
        List<Menu> menus = menuMapper.selectAll();
        List<MenuVo> voList = toVoList(menus);
        return TreeUtils.buildTreeDatas(voList,"code","parentCode",null);
    }

    @Override
    public List<MenuVo> listAllMenus() {
        List<Menu> list = menuMapper.selectAll();

        return toVoList(list);
    }

    @Override
    public void updateMenu(String code, String name) {
        if(SStringUtils.isEmpty(code))
            throw new BaseException(ErrCode.PARAMS_ERR);
        if(SStringUtils.isEmpty(name))
            throw new BaseException(ErrCode.PARAMS_ERR);

        Menu temp = new Menu();
        temp.setCode(code);
        temp.setName(name);
        menuMapper.updateByPrimaryKeySelective(temp);
    }


    private List<MenuVo> toVoList(List<Menu> menus){
        List<MenuVo> rst = new LinkedList<>();
        for(Menu menu : menus){
            rst.add(toVo(menu));
        }
        return rst;
    }

    private MenuVo toVo(Menu menu){
        MenuVo menuVo = new MenuVo();

        SBeanUtils.copyProperties2(menu,menuVo);
        return menuVo;
    }

    public static void main(String args[]){
        System.out.println(PasswordUtil.loginPassword("abb123456"));
    }
}
