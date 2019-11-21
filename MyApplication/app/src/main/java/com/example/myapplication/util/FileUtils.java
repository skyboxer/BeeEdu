package com.example.myapplication.util;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.example.myapplication.MyApplication;


public class FileUtils extends Application {
    public  String getPcmFileAbsolutePath(String name){
        Context context = MyApplication.getContext();
        String path = getCachePath(context)+"/"+name;
       return path;
    }

    public  String getPcmFileAbsolutePath(){
        Context context = MyApplication.getContext();
        String path = getCachePath(context);
        return path;
    }

    /**
     * 获取app缓存路径
     * @param context
     * @return
     */
    public static String getCachePath( Context context ){
        String cachePath ;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = context.getExternalCacheDir().getPath() ;
        }else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() ;
        }
        return cachePath ;
    }

}
