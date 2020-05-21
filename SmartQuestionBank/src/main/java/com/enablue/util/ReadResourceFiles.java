package com.enablue.util;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author cn_xjk
 * 读取配置文件
 */
public class ReadResourceFiles {


    /**
     * 返回键值
     * @param resourcePath
     * @param key
     * @return
     */
    public static String ReadResourceFiles(String resourcePath,String key) {
        ResourceBundle resource = ResourceBundle.getBundle(resourcePath);
        return resource.getString(key);
    }
}
