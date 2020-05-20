package com.enablue.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.MenuMapper;
import com.enablue.pojo.Menu;
import com.enablue.pojo.MenuTree;
import com.enablue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private CommonReturnValue commonReturnValue;

    @Override
    public List<MenuTree> getMenuTree() {
        List<Menu> menuList = menuMapper.getMenu(new Menu());
        List<MenuTree> menuTreeList = null;
        for (Menu menu : menuList) {
            MenuTree menuTree = this.menuToTreeNode(menu,true,false,false);
            menuTreeList = this.assemblyTree(menuTree,menuTreeList);
        }
        return menuTreeList;
    }

    @Override
    public JSONObject updateMenuTree(MenuTree menu) {
        Menu menuNew = new Menu();
        menuNew.setMenuId(menu.getId());
        menuNew.setParentId(menu.getParentId());
        String title = menu.getTitle();
        String[] nameAndUrl = title.split(",");
        menuNew.setMenuName(nameAndUrl[0]);
        menuNew.setMenuUrl(nameAndUrl[1]);
        int i = menuMapper.updateMenu(menuNew);
        if(i>0){
            return commonReturnValue.CommonReturnValue(0,"修改成功！");
        }
        return commonReturnValue.CommonReturnValue(-1,"修改失败！");
    }

    @Override
    public JSONObject addMenuTree(MenuTree menu) {
        Menu menuNew = new Menu();
        menuNew.setParentId(menu.getParentId());
        String title = menu.getTitle();
        String[] nameAndUrl = title.split(",");
        menuNew.setMenuName(nameAndUrl[0]);
        menuNew.setMenuUrl(nameAndUrl[1]);
        int i = menuMapper.addMenu(menuNew);
        if(i>0){
            return commonReturnValue.CommonReturnValue(0,"添加成功！");
        }
        return commonReturnValue.CommonReturnValue(-1,"添加失败！");
    }

    @Override
    public JSONObject delMenuTree(MenuTree menu) {
        if(menu.getParentId()==0){
            return commonReturnValue.CommonReturnValue(-1,"不能删除根节点！");
        }
        Menu menuNew = new Menu();
        menuNew.setMenuId(menu.getId());
        int i = menuMapper.delMenu(menuNew);
        if(i>0){
            return commonReturnValue.CommonReturnValue(0,"删除成功！");
        }
        return commonReturnValue.CommonReturnValue(-1,"删除失败！");
    }

    private List<MenuTree> assemblyTree(MenuTree menuTree,List<MenuTree> menuTreeList){
        //初始化
        if(menuTreeList == null){
            menuTreeList = new ArrayList<>();
            menuTreeList.add(menuTree);
            return menuTreeList;
        }
        //判断是否是根节点
        if(menuTree.getParentId() == 0){
            //判断是否有孩子
            Iterator<MenuTree> menuTreeInterator = menuTreeList.iterator();
            MenuTree menuTree1;
            while (menuTreeInterator.hasNext()){
                menuTree1 = menuTreeInterator.next();
                if(menuTree.getId() == menuTree1.getParentId()){
                    menuTree.getChildren().add(menuTree1);
                    menuTreeInterator.remove();
                }
            }
            menuTreeList.add(menuTree);
            return menuTreeList;
        }
        for(MenuTree menuTree1 : menuTreeList){
            //找父亲
            if(menuTree.getParentId() == menuTree1.getId()){
                menuTree1.getChildren().add(menuTree);
                return menuTreeList;
            }
            //下一层不为零，继续找
            if(menuTree1.getChildren().size() !=0){
                assemblyTree(menuTree,menuTree1.getChildren());
            }
        }
        //不是0节点，但是没找到父亲，自己作为根节点
        menuTreeList.add(menuTree);
        return menuTreeList;
    }

    private MenuTree menuToTreeNode(Menu menu, boolean spread, boolean checked, boolean disable) {
        boolean spreadInit = false;//节点是否初始展开，默认 false
        boolean checkedInit = false;//节点是否初始为选中状态
        boolean disabledInit = false;//节点是否为禁用状态。默认 false
        if (spread) {
            spreadInit = true;
        }
        if (checked) {
            checkedInit = true;
        }
        if (disable) {
            disabledInit = true;
        }
        MenuTree treeNode = new MenuTree();
        treeNode.setId(menu.getMenuId());
        treeNode.setTitle(menu.getMenuName()+","+menu.getMenuUrl());
        treeNode.setField("menu");
        treeNode.setParentId( menu.getParentId());
        treeNode.setChildren(new ArrayList<>());
        treeNode.setHref(menu.getMenuUrl());
        treeNode.setSpread(spreadInit);
        treeNode.setChecked(checkedInit);
        treeNode.setDisabled(disabledInit);
        return treeNode;
    }
}
