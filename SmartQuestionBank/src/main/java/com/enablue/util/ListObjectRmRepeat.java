package com.enablue.util;

import com.enablue.pojo.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author cnxjk
 * 集合对象去重
 */
public class ListObjectRmRepeat {

    public static List<Menu> listMenuRmRepeat(List<Menu> list){
        List<Menu> unique = list.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(Menu::getMenuId))), ArrayList::new)
        );
        return unique;
    }
}
