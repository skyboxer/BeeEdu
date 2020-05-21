package com.enablue.util;

/**
 * @author cnxjk
 * 判断操作系统
 */
public class IfOs {

    /*根据操作系统获得指定文件路径*/
    public static String ifOsPath(String testPath,String officialPath){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return testPath;
        }
        return officialPath;
    }

    public static String ifOsResourceValue(String exploitKey,String serverKey,String resourcePath){
        String exploitValue = ReadResourceFiles.ReadResourceFiles(resourcePath,exploitKey);
        String serverValue = ReadResourceFiles.ReadResourceFiles(resourcePath,exploitKey);
        return ifOsPath(exploitValue,serverValue);
    }

}
