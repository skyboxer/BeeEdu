package com.enablue.util.suanfa;

import com.alibaba.fastjson.JSONObject;
import com.enablue.dto.DataLayoutDTO;
import com.enablue.util.RandomNumFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cnxjk
 * 算法库
 */
public class RecursionEquation {

    /**
     * 递等计算(连加、连减、连乘、连除、混合算式)
     * @param nameArray 题数
     * @param
     * @return
     */
    public  List<JSONObject> recursiveComputation(String [] nameArray){
        List<JSONObject> jsonObjects = new ArrayList<>();
        //定义题型 连加、连减、连乘、连除、混合算式
        JSONObject jsonObject = null;
        for(int i=0;i<nameArray.length;i++){
            int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,5});
            System.out.println("循环"+addSize);
            switch (addSize){
                case 0:
                    System.out.println("循环hybridAlgorithm");
                    jsonObject = hybridAlgorithm(nameArray[i]);
                    break;
                case 1:
                    System.out.println("循环seriesAddition");
                    jsonObject = seriesAddition(nameArray[i]);
                    break;
                case 2:
                    System.out.println("循环seriesSubtraction");
                    jsonObject = seriesSubtraction(nameArray[i]);
                    break;
                case 3:
                    System.out.println("循环seriesMultiplicationm");
                    jsonObject = seriesMultiplication(nameArray[i]);
                    break;
                default:
                    System.out.println("循环seriesDivision");
                    jsonObject = seriesDivision(nameArray[i]);
                    break;
            }
            jsonObjects.add(jsonObject);

        }
        return jsonObjects;
    }

    /**
     * 递等计算(连加、连减、连乘、连除、混合算式)
     * @param childArray 题数
     * @param
     * @return
     */
    public  List<DataLayoutDTO>  recursiveComputation(List<DataLayoutDTO> childArray){

        //定义题型 连加、连减、连乘、连除、混合算式
        JSONObject jsonObject = null;
        for(DataLayoutDTO dataLayoutDTO : childArray){
            int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,5});
            System.out.println("循环"+addSize);
            switch (addSize){
                case 0:
                    System.out.println("循环hybridAlgorithm");
                    jsonObject = hybridAlgorithm(dataLayoutDTO.getKey());
                    break;
                case 1:
                    System.out.println("循环seriesAddition");
                    jsonObject = seriesAddition(dataLayoutDTO.getKey());
                    break;
                case 2:
                    System.out.println("循环seriesSubtraction");
                    jsonObject = seriesSubtraction(dataLayoutDTO.getKey());
                    break;
                case 3:
                    System.out.println("循环seriesMultiplicationm");
                    jsonObject = seriesMultiplication(dataLayoutDTO.getKey());
                    break;
                default:
                    System.out.println("循环seriesDivision");
                    jsonObject = seriesDivision(dataLayoutDTO.getKey());
                    break;
            }
            dataLayoutDTO.setValue(jsonObject.getString("value"));

        }
        return childArray;
    }

    /**
     * 递等式混合运算
     * @param name
     * @return
     */
    public JSONObject hybridAlgorithm(String name){
        JSONObject jsonObject1 = new JSONObject();
        int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,2});
        int[] variableArray = RandomNumFactory.RandomNumFactory(2,5);
        String value = "";
        int answer = 0;
        //一个加号
        if(addSize ==0){
             value = "("+variableArray[0]+"×"+variableArray[1]+")+("+variableArray[2]+"×"+variableArray[3]+")";
             answer = (variableArray[0]*variableArray[1])+(variableArray[2]*variableArray[3]);
        }else{
            value = "("+variableArray[0]+"×"+variableArray[1]+")+("+variableArray[2]+"×"+variableArray[3]+")+"+variableArray[4];
            answer = (variableArray[0]*variableArray[1])+(variableArray[2]*variableArray[3])+variableArray[4];

        }
        //两个加号
        jsonObject1.put("name",name);
        jsonObject1.put("value",value);
        jsonObject1.put("answer",answer);
        return jsonObject1;
    }

    /**
     *连续加法
     * @param name
     * @return
     */
    public JSONObject seriesAddition(String name){
        JSONObject jsonObject2 = new JSONObject();
        int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,2});
        int[] variableArray = RandomNumFactory.RandomNumFactory(3,2);//两个三位数
        int variable1=RandomNumFactory.RandomNumFactory(new int[]{10,1000});
        int variable2=RandomNumFactory.RandomNumFactory(new int[]{10,1000});
        String value = "";
        int answer = 0;
        if(addSize ==0){
            //随机三位数+随机三位数+随机三
            value = variableArray[0]+"+"+variableArray[1]+"+"+variable1;
            answer = variableArray[0]+variableArray[1]+variable1;
        }else{
            value = variableArray[0]+"+"+variableArray[1]+"+"+variable1+"+"+variable2;
            answer = variableArray[0]+variableArray[1]+variable1+variable1;
        }
        jsonObject2.put("name",name);
        jsonObject2.put("value",value);
        jsonObject2.put("answer",answer);
        return jsonObject2;
    }

    /**
     *连续减法
     * @param name
     * @return
     */
    public JSONObject seriesSubtraction(String name){
        JSONObject jsonObject3 = new JSONObject();
        int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,4});
        int variable1=RandomNumFactory.RandomNumFactory(new int[]{666,1000});//第一个三位数
        int variable2=RandomNumFactory.RandomNumFactory(new int[]{250,566});//第二个三位数
        int variable3=RandomNumFactory.RandomNumFactory(new int[]{10,(variable1-variable2)});
        int variable4=RandomNumFactory.RandomNumFactory(new int[]{10,(variable1-variable2-variable3)});
        String value = "";
        int answer = 0;
        switch (addSize){
            case 0:
                //随机三位数+随机三位数+随机三
                value = variable1+"-"+variable2+"-"+variable3;
                answer = variable1-variable2-variable3;
                break;
            case 1:
                //随机三位数+随机三位数+随机三
                value = variable1+"-("+variable2+"-"+variable3+")";
                answer = variable1-(variable2-variable3);
                break;
            case 2:
                //随机三位数+随机三位数+随机三
                value = variable1+"-"+variable2+"-"+variable3+"-"+variable4;
                answer = variable1-variable2-variable3-variable4;
                break;
            default:
                //随机三位数+随机三位数+随机三
                value = variable1+"-"+variable2+"-("+variable3+"-"+variable4+")";
                answer = variable1-variable2-(variable3-variable4);
                break;
        }
        jsonObject3.put("name",name);
        jsonObject3.put("value",value);
        jsonObject3.put("answer",answer);
        return jsonObject3;
    }

    /**
     *连续乘法
     * @param name
     * @return
     */
    public JSONObject seriesMultiplication(String name){
        JSONObject jsonObject4 = new JSONObject();
        int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,2});
        int[] variableArray = RandomNumFactory.RandomNumFactory(2,4);
        String value = "";
        int answer = 0;
        if(addSize ==0){
            value = variableArray[0] +"×"+variableArray[1]+"×"+variableArray[2];
            answer = variableArray[0]*variableArray[1]*variableArray[2];
        }else{
            value = variableArray[0] +"×"+variableArray[1]+"×"+variableArray[2]+"×"+variableArray[3];
            answer = variableArray[0]*variableArray[1]*variableArray[2]*variableArray[3];
        }
        jsonObject4.put("name",name);
        jsonObject4.put("value",value);
        jsonObject4.put("answer",answer);
        return jsonObject4;
    }

    /**
     *连续除法
     * @param name
     * @return
     */
    public JSONObject seriesDivision(String name){
        JSONObject jsonObject5 = new JSONObject();
        String value = "";
        int answer = 0;
        int addSize = RandomNumFactory.RandomNumFactory(new int[]{0,4});
        int remainder1 =0;
        int remainder2 =0;
        int remainder3 =0;
        int variable1 = 0;//五位数
        int variable2 =0;//三位数
        int variable3 =0;//两位数
        int variable4 = 0;//一位数
        int variable5 =0;//第二个三位数
        int variable6= 0;//第二个两位数
        switch (addSize){
            case 0:
                do {
                    variable2=RandomNumFactory.RandomNumFactory(new int[]{100,1000});
                    variable3=RandomNumFactory.RandomNumFactory(new int[]{10,100});
                    variable1 = variable2*variable3;
                }while (variable1<10000);
                do {
                    variable4 = RandomNumFactory.RandomNumFactory(new int[]{1,10});
                    remainder1 = variable3%variable4;
                }while (remainder1!=0);
                answer =variable1/variable2/variable4;
                value = variable1+"÷"+variable2+"÷"+variable4;
                System.out.println(value+"="+answer);
                break;
            case 1:
                do {
                    variable2= RandomNumFactory.RandomNumFactory(new int[]{100,1000});
                    variable4 = RandomNumFactory.RandomNumFactory(new int[]{1,10});
                    remainder1 = variable2%variable4;
                }while (remainder1!=0);
                do {
                    variable5= RandomNumFactory.RandomNumFactory(new int[]{100,1000});
                    remainder2=variable5%(variable2/variable4);
                }while (remainder2!=0);
                value = variable5+"÷("+variable2+"÷"+variable4+")";
                answer = variable5/(variable2/variable4);
                System.out.println(value+"="+answer);
                break;
            case 2:
                do {
                    variable1= RandomNumFactory.RandomNumFactory(new int[]{10000,100000});
                    variable3 = RandomNumFactory.RandomNumFactory(new int[]{10,100});
                    remainder1 = variable1%variable3;
                }while (remainder1!=0);
                do {
                    variable6= RandomNumFactory.RandomNumFactory(new int[]{10,100});
                    remainder2=(variable1/variable3)%variable6;
                }while (remainder2!=0);
                do {
                    variable4= RandomNumFactory.RandomNumFactory(new int[]{1,10});
                    remainder3=(variable1/variable3/variable6)%variable4;
                }while (remainder3!=0);
                value = variable1+"÷"+variable3+"÷"+variable6+"÷" +variable4;
                answer = variable1/variable3/variable6/variable4;
                System.out.println(value+"="+answer);
                break;
            default:
                do {
                    variable1= RandomNumFactory.RandomNumFactory(new int[]{10000,100000});
                    variable2 = RandomNumFactory.RandomNumFactory(new int[]{100,1000});
                    remainder1 = variable1%variable2;
                }while (remainder1!=0);
                do {
                    variable3= RandomNumFactory.RandomNumFactory(new int[]{10,100});
                    variable4= RandomNumFactory.RandomNumFactory(new int[]{1,10});
                    remainder2=(variable1/variable2)%(variable3/variable4);
                }while (remainder2!=0);
                value = variable1+"÷"+variable2+"÷("+variable3+"÷" +variable4+")";
                answer = variable1/variable2/(variable3/variable4);
                System.out.println(value+"="+answer);
                break;
        }
        jsonObject5.put("name",name);
        jsonObject5.put("value",value);
        jsonObject5.put("answer",answer);
        return jsonObject5;
    }




}
