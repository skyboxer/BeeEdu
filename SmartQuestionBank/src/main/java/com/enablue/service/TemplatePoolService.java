package com.enablue.service;

import com.enablue.pojo.TemplatePool;

/**
 * @author cnxjk
 * 模板持久层接口
 */
public interface TemplatePoolMapper {

    int addTemplatePool(TemplatePool templatePool);

    int updateTemplatePool(TemplatePool templatePool);

}
