package com.enablue.common;

import java.util.*;

/**
 * 递等式运算
 * 王成
 */
public class RecursiveEquation {
    public HashMap<String, Object> generativeExpression(){
        HashMap<String, Object> result = new HashMap<>();
        int randomNumber = (int) (Math.random()*10);
        randomNumber=randomNumber%4;
        switch (randomNumber){
            case 0:
                result = addition();
                break;
            case 1:
                result = Subtraction();
                break;
            case 2:
                result = multiplication();
                break;
            case 3:
                result = division();
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 加法运算
     * 王成
     * @return
     */
    private HashMap<String,Object> addition(){
        //封装结果集
        HashMap<String, Object> map = new HashMap<>();
        //生成随机四位数的被加数
        int addend = (int) (Math.random()*10000);
        if (addend<1000){
            addend+=1000;
        }
        //生成随机四位数的加数
        int add = (int) (Math.random()*10000);
        if (add<1000){
            add+=1000;
        }
        //生成运算式
        String expression=addend+" + " + add +" = ";
        //生成答案
        int answer=addend+add;
        //封装结果集
        map.put("addend",addend);
        map.put("add",add);
        map.put("expression",expression);
        map.put("answer",answer);
        map.put("code",1);
        map.put("msg","生成成功");
        return map;

    }
    /**
     * 减法运算（结果不能为负数）
     * @return
     */
     private HashMap<String, Object>  Subtraction(){
        //封装结果集
        HashMap<String, Object> map = new HashMap<>();
        //生成随机四位数的被减数
        int subtracted = (int) (Math.random()*10000);
        if (subtracted<1000){
            subtracted+=1000;
        }
        //生成随机三位数的减数
        int minus = (int) (Math.random()*1000);
        if (minus<100) {
            minus+=100;
        }
        //生成运算式
        String expression=subtracted+" - " + minus +" = ";
        //生成答案
        int answer=subtracted-minus;
        //封装结果集
        map.put("subtracted",subtracted);
        map.put("minus",minus);
        map.put("expression",expression);
        map.put("answer",answer);
        map.put("code",1);
        map.put("msg","生成成功");
        return map;

    }

    /**
     * 乘法运算
     * @return
     */
    private HashMap<String, Object> multiplication(){
        //封装结果集
        HashMap<String, Object> map = new HashMap<>();
        //生成随机两位数的被乘数
        int multiplicand = (int) (Math.random()*100);
        if (multiplicand<10){
            multiplicand+=10;
        }
        //生成随机两位数的乘数
        int multiplier = (int) (Math.random()*100);
        if (multiplier<10){
            multiplier+=10;
        }
        //生成运算式
        String expression=multiplicand+" × " + multiplier +" = ";
        //生成答案
        int answer=multiplicand*multiplier;
        //封装结果集
        map.put("multiplicand",multiplicand);
        map.put("minus",multiplier);
        map.put("expression",expression);
        map.put("answer",answer);
        map.put("code",1);
        map.put("msg","生成成功");
        return map;
    }

    /**
     * 除法运算（要能除尽）
     * @return
     */
    private HashMap<String, Object> division(){
        //封装结果集
        HashMap<String, Object> map = new HashMap<>();
        int dividend=0;
        int divisor=0;
        //生成可以除尽的数
        while (true){
            //生成随机五位数的被除数
            dividend = (int) (Math.random()*100000);
            if (dividend<10000){
                dividend+=10000;
            }
            //生成随机一位数的除数
            divisor = (int) (Math.random()*10);
            if (divisor!=0 && divisor!=1 && dividend%divisor==0){
                break;
            }
        }

        //生成运算式
        String expression=dividend+" ÷ " + divisor +" = ";
        //生成答案
        int answer=dividend/divisor;
        //封装结果集
        map.put("dividend",dividend);
        map.put("divisor",divisor);
        map.put("expression",expression);
        map.put("",answer);
        map.put("code",1);
        map.put("msg","生成成功");
        return map;
    }


}

