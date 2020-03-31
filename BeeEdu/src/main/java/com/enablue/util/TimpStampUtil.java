package com.enablue.util;
/**
 * 时间戳工具类
 */
public class TimpStampUtil {
    public static String processingTimeStamp(int time){
        //处理时间戳
        int tempTime=time/1000;
        String hour=""+(tempTime/3600);
        String minut=""+(tempTime/60);
        String sec=""+(tempTime%60);
        if(tempTime/3600<10){
            hour="0"+hour;
        }
        if(tempTime/60<10){
            minut="0"+minut;

        }
        if(tempTime%60<10){
            sec="0"+sec;
        }
        return hour + ":"+minut+":"+sec+","+time%1000;
    }
}
