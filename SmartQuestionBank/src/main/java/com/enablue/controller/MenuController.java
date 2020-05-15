package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.dto.MenuTreeSysDTO;
import com.enablue.pojo.MenuTree;
import com.enablue.service.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能请求API
 *
 * @author cn_xjk
 */
@RequestMapping("menuController")
@RestController
public class MenuController {
    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private MenuService menuService;

    @RequestMapping("getMenuTree")
    public JSONObject getMenuTree() {
        return commonReturnValue.CommonReturnValue(0, "成功", menuService.getMenuTree());
    }

    @RequestMapping("menuTreeSys")
    public JSONObject menuTreeSys(@RequestBody MenuTreeSysDTO menuTreeSysDTO) {
        MenuTree menuTree = menuTreeSysDTO.getMenuTree();
        String sysType = menuTreeSysDTO.getSysType();
        System.out.println("参数"+menuTree.toString());
        if (sysType != null) {
            JSONObject jsonObject;
            switch (sysType){
                case "update":
                    jsonObject = menuService.updateMenuTree(menuTree);
                    break;
                case "del":
                    jsonObject = menuService.delMenuTree(menuTree);
                    break;
                default:
                    jsonObject = menuService.addMenuTree(menuTree);
            }
            return jsonObject;
        }
        return commonReturnValue.CommonReturnValue(-1,"操作失败！参数不正确！");
    }

}
