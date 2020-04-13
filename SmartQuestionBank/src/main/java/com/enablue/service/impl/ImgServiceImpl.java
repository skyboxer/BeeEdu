package com.enablue.service.impl;

import com.enablue.mapper.ImageMapper;
import com.enablue.pojo.Image;
import com.enablue.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;

/**
 * 图片业务处理
 * 王成
 *
 */
@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImageMapper imageMapper;

    /**
     * 修改图片
     * @param image
     * @return
     * 王成
     */
    @Override
    public HashMap<String, Object> updateImg(Image image) {
        HashMap<String, Object> result = new HashMap<>();
        int count =imageMapper.updateImg(image);
        if(count<1){
            result.put("msg","修改图片失败");
            result.put("code",-1);
        }
        result.put("msg","修改图片成功");
        result.put("code",1);

        return result;
    }

    /**
     * 根据模板id查询图片数据
     * @param id 模板id
     * @return
     */
    @Override
    public HashMap<String, Object> queryTemplateById(int id) {
        HashMap<String, Object> result = new HashMap<>();
        Image image = imageMapper.queryTemplateById(id);
        if (image==null){
            result.put("msg","未查询到图片");
            result.put("code",-1);
            return result;
        }
        result.put("msg","查询成功");
        result.put("code",1);
        result.put("data", Base64.getEncoder().encodeToString(image.getImageData()));
        result.put("format",image.getImageFormat());
        return result;
    }
}
