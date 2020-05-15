package com.enablue.service;


import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Menu;
import com.enablue.pojo.MenuTree;

import java.util.List;

/**
 * 功能业务
 * @author cn_xjk
 */
public interface MenuService {
    /**
     * 获取功能树
     * 二级树
     * @return
     */
    List<MenuTree> getMenuTree();

    JSONObject updateMenuTree(MenuTree menu);

    JSONObject addMenuTree(MenuTree menu);

    JSONObject delMenuTree(MenuTree menu);
}
