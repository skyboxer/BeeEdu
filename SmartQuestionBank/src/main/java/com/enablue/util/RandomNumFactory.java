package com.enablue.util;

import java.util.Arrays;

/**
 * @author cnxjk
 * 获取随机数
 */
public class RandomNumFactory {

    /**
     * 自定义返回区间内的随机数
     * @param range [min,max]
     * @return
     */
    public static int RandomNumFactory(int[] range) {
        int ran = (int)(Math.random()*(range[1]-range[0])+range[0]);
        return ran;
    }

    /**
     * 获取一组数字
     * @param digit 位数
     * @param number 数量
     * @return
     */
    public static int[] RandomNumFactory(int digit,int number){
        int min = (int) Math.pow(10,digit-1);
        int max = (int) Math.pow(10,digit)-1;
        int[] range = new int[]{min,max};
        int [] ranArray = new int[number];
        for(int i = 0; i < number; i++){
            ranArray[i] = RandomNumFactory(range);
        }
        Arrays.sort(ranArray);
        return ranArray;
    }

}
