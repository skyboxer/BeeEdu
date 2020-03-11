package com.enablue.mapper;

import com.enablue.pojo.TemplatePool;
import org.apache.ibatis.annotations.Param;

import javax.management.StandardEmitterMBean;
import java.util.List;

/**
 * @author cnxjk
 * 模板持久层接口
 */
public interface TemplatePoolMapper {
    /**
     * 添加试题模板
     * @param templatePool
     * @return
     */
    int addTemplatePool(@Param("templatePool") TemplatePool templatePool);

    /**
     * 多条件查询试题模板
     * @param templatePool
     * @return
     */
    List<TemplatePool> getTemplatePooList(TemplatePool templatePool);

}
