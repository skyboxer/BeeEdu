package com.enablue.controller;

import com.enablue.pojo.Image;
import com.enablue.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 图片处理器
 * 王成
 */
@RestController
public class ImgController {
    @Autowired
    private ImgService imgService;

    /**
     * 修改图片
     * @param image
     * @return
     * 王成
     */
    @RequestMapping("/updateImg")
    public HashMap<String,Object> updateImg(Image image){
        return imgService.updateImg(image);

    }

    /**
     *  根据模板id查询图片数据
     * @param id 模板id
     * @return
     * 王成
     */
    @RequestMapping("/queryById")
    public HashMap<String,Object> queryTemplateById(int id){
        return imgService.queryTemplateById(id);

    }
}
