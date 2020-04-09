package com.enablue.mapper;

import com.enablue.pojo.Image;
import org.apache.ibatis.annotations.Param;

/**
 * 图片mapper
 * 王成
 */
public interface ImageMapper {
    /**
     * 添加图片
     * @param image
     */
    int addImage(@Param("image") Image image);
}
