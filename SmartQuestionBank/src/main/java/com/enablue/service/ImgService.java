package com.enablue.service;

import com.enablue.pojo.Image;

import java.util.HashMap;

/**
 * 图片业务处理
 * 王成
 */
public interface ImgService {
    /**
     * 修改图片
      * @param image
     * @return
     */
    HashMap<String, Object> updateImg(Image image);

    /**
     * 根据模板id查询图片数据
     *
     * @param id 模板id
     * @return
     */
    HashMap<String, Object> queryTemplateById(int id);
}
