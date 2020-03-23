package com.enablue.util;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;

/**
 * @author cnxjk
 * 模板题对应算法
 */
public class TemplateFactory {

    /**
     * 应用题组装变量
     * @param templatePool
     * @param name
     * @return
     */
    public static JSONObject templateJSONObjectFactory(TemplatePool templatePool, String name){
        JSONObject jsonObject =new JSONObject();
        StringBuffer newContent = new StringBuffer(templatePool.getTemplateContent());
        switch (templatePool.getTemplateId()){
            case 14:
                for(VariablePool variablePool : templatePool.getVariablePoolList()){
                    String[] strings = variablePool.getVariableContent().split(",");
                    final int d = RandomNumFactory.RandomNumFactory(new int[]{2,10});
                    String variable ="";
                    switch (strings[0]){
                        case "a":
                            variable =String.valueOf(RandomNumFactory.threeNumFactory1());
                            break;
                        case "b":
                            variable =String.valueOf(RandomNumFactory.threeNumFactory2());
                            break;
                        case "d":
                            variable = String.valueOf(d);
                            break;
                        case "c":
                            variable=String.valueOf(RandomNumFactory.fiveNumFactory1(d));
                            break;
                    }
                    int length = ("$"+strings[0]).length();
                    int lastIndex = newContent.lastIndexOf("$"+strings[0]);
                    newContent.replace(lastIndex,lastIndex+length,variable);
                }
                break;
            default:
                for(VariablePool variablePool : templatePool.getVariablePoolList()){
                    String[] strings = variablePool.getVariableContent().split(",");
                    String variable =String.valueOf(RandomNumFactory.RandomNumFactory(new int[]{Integer.valueOf(strings[1]),Integer.valueOf(strings[2])}));
                    int length = ("$"+strings[0]).length();
                    int lastIndex = newContent.lastIndexOf("$"+strings[0]);
                    newContent.replace(lastIndex,lastIndex+length,variable);
                }
                break;
        }
        System.out.println("新的内容"+newContent);
        jsonObject.put("name",name);
        jsonObject.put("value",newContent);
        jsonObject.put("answer","");
        return jsonObject;
    }

}
