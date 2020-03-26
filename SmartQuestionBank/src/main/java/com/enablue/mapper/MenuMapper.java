package com.enablue.mapper;

import com.enablue.pojo.Menu;

import java.util.List;

/**
 * @author cnxjk
 */
public interface MenuMapper {

    List<Menu> getMenu(Menu menu);

    int delMenu(Menu menu);

    int updateMenu(Menu menu);

    int addMenu(Menu menu);
}
