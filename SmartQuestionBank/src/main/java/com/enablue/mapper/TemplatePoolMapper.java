package com.enablue.mapper;

import com.enablue.pojo.TemplatePool;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询所有的模板
     * @return
     */
    List<TemplatePool> queryAllTemplate();

    /**
     * 根据条件查询模板
     * @param subjectId
     * @param typeId
     * @return
     */
    List<TemplatePool> queryTemplateBySubjectAndType(@Param("subjectId") int subjectId,@Param("typeId")int typeId);

    /**
     * 修改模板
     * @param templatePool
     * @return
     */
    int updataTemplate(@Param("templatePool") TemplatePool templatePool);

    /**
     * 分页查询模板
     * @param page 开始页
     * @param limit
     * @return
     */
    List<TemplatePool> queryPageTemplatePool(@Param("page") Long page, @Param("limit") Long limit);

    /**
     * 删除模板
     * @param id 模板id
     * @return
     * 王成
     */
    int deleteTemplatePool(@Param("id") int id);

    /**
     * 根据模板id查询
     * @param templateId
     * @return
     */
    TemplatePool queryTemplateById(@Param("templateId") Integer templateId);

    /**
     * 根据科目id查询模板
     * @param id 科目id
     */
    List<TemplatePool> queryTemplateBySubjectId(@Param("id") int id);
    /**
     * 根据类型id查询模板
     * @param id 类型id
     */
    List<TemplatePool> queryTemplateByTypeId(@Param("id")int id);

    /**
     *
     * @param templatePoolList
     * @return
     */
    int addListTemplate(@Param("list") List<TemplatePool> templatePoolList);
}
