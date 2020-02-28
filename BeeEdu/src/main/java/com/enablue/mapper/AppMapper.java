package com.enablue.mapper;

import com.enablue.pojo.App;

import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 运营商下应用dao
 */
public interface AppMapper {

    /**
     * 应用条件查询
     * @param map
     * @return
     */
    List<App> queryAppList(Map map);

    /**
     * 应用添加
     * @param app
     * @return
     */
    int insertApp(App app);

    /**
     * 应用修改/删除
     * @param app
     * @return
     */
    int updateApp(App app);

    /**
     * 根据账号id查询应用
     * 王成
     * @param id 账号id
     * @return
     */
    List<App> queryAppListByOperatorId(Integer id);
}
