package com.verificer.base.auth.service;



import com.verificer.beans.MenuVo;
import com.verificer.beans.TreeNodeVo;

import java.util.List;

/**
 * Created by 35336 on 2021/1/7.
 */
public interface MenuService {
    /**
     * 获取某用户的菜单列表
     * @param userId
     * @return
     */
    public List<TreeNodeVo<MenuVo>> getPermissionMenus(Long userId);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeNodeVo<MenuVo>> getAllMenus();

    /**
     * 获取所有菜单
     * @return
     */
    List<MenuVo> listAllMenus();

    /**
     * 更新菜单
     * @param code code
     * @param name 菜单名称
     */
    void updateMenu(String code, String name);
}
