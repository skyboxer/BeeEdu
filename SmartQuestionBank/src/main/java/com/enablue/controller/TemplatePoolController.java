package com.enablue.controller;

import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 模板处理器
 * 王成
 *
 */
@RestController
public class TemplatePoolController {
    @Autowired
    private ImpotTestQuestionsService impotTestQuestionsService;

    /**
     * 分页查询模板
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryPageTemplatePool")
    public HashMap<String,Object> queryPageTemplatePool(Long page,Long limit) {
        return impotTestQuestionsService.queryPageTemplatePool( page, limit);
    }

    /**
     * 删除模板
     * @param id
     * @return
     * 王成
     */
    @RequestMapping("/deleteTemplatePool")
    public HashMap<String,Object> deleteTemplatePool(int id){
        return impotTestQuestionsService.deleteTemplatePool(id);
    }

    /**
     * 修改模板
     * @param templatePool 试题模板
     * @param variableQuantity  试题变量
     * @param tpAnswer  试题答案
     * @param file  试题图片文件
     * @return
     * 王成
     */
    @RequestMapping("/updataTemplatePool")
    public HashMap<String,Object>  updataTemplatePool(TemplatePool templatePool,String variableQuantity ,TPAnswer tpAnswer, MultipartFile file){
        return  impotTestQuestionsService.updataTemplate(templatePool,variableQuantity,tpAnswer,file);
    }
}
