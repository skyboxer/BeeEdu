package com.enablue.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    public static String RandomTextFactory(){//ABCDEFGHIJKLMNOPQRSTUVWXYZ
        String s = "abcdefghijklmnopqrstuvwxyz1234567890";
        char[] c = s.toCharArray();
        Random random = new Random();
        StringBuffer name = new StringBuffer();
        for( int i = 0; i < 16; i ++) {
            name.append(c[random.nextInt(c.length)]);
        }
        System.out.println(name);
        return name.toString();
    }

    /**
     * 三位数 末尾有1-2个0,非0部分为偶数
     * @return
     */
    public static int threeNumFactory1(){
        int returnNum = 0;
        int status = RandomNumFactory(new int[]{0,1});
        //一个0
        if(status==0){
            int twoNum = 0;
            do {
                 twoNum = RandomNumFactory(new int[]{10,100});
            }while (twoNum%2!=0);
            returnNum = twoNum*10;
        }else {
            int oneNum = 0;
            do {
                oneNum = RandomNumFactory(new int[]{2,10});
            }while (oneNum%2!=0);
            returnNum = oneNum*100;
        }
        return returnNum;
    }
    /**
     * 三位数 末尾有1-2个0,非0部分为5
     * @return
     */
    public static int threeNumFactory2(){
        int returnNum = 0;
        int status = RandomNumFactory(new int[]{0,1});
        //一个0
        if(status==0){
            int twoNum = RandomNumFactory(new int[]{1,10});

            returnNum = twoNum*100+5*10;
        }else {
            returnNum = 5*100;
        }
        return returnNum;
    }

    public static int fiveNumFactory1(int d){
        int returnNum = 10000;
        int twoNum;
        for(int i=1;i<3;i++){
            do {
                twoNum = RandomNumFactory(new int[]{10,100});
            }while (twoNum%d!=0);
            if(i==1){
                returnNum = twoNum*1000;
            }else{
                returnNum += twoNum;
            }
        }
        return returnNum;
    }

   /* public static void main(String[] args) {
        System.out.println(fiveNumFactory1(6));
    }*/

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
    public static int RandomNumSize(int x) {
        final int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
        for (int i =0;i<Integer.MAX_VALUE;i++){
            if (x <= sizeTable[i]) {
                return sizeTable[i];
            }
        }
        return x;
    }

    /**
     * 获取随即下标
     * @param max 不包含max
     * @param num 个数
     * @return 返回0到max以内的数字
     */
    public static int[] RandomNumIndex(int max,int num){
        int[] indexList = new int[num];
        for(int i = 0;i<num;i++){
            indexList[i] = (int)(Math.random()*(max));
        }
        return indexList;
        /*Set<Integer> set = new HashSet<>();
        int a;
        int i = 0;
        do {
            a = (int)(Math.random()*(max));
            if(set.add(a)){
                indexList[i] = a;
            }
            i++;
        }while (set.size()==num);
        return indexList;*/
    }


}
