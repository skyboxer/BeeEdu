package com.enablue.mapper;

import com.enablue.pojo.Model;

import java.util.List;

/**
 * @author cnxjk
 * model模板
 */
public interface ModelMapper {
    /**
     * @cnxjk
     * 查询模板集合
     * @param model
     * @return
     */
    List<Model> getModelList(Model model);

    /**
     * @cnxjk
     * 查询总页数
     * @param model
     * @return
     */
    int getModelCount(Model model);
}
