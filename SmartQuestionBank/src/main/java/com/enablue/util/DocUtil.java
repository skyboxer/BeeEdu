package com.enablue.util;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.RecursiveEquation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocUtil {
    public static void main(String[] args) {
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        jsonObject.put("name","${title}");
        jsonObject.put("value","三年级算法测试");
        list.add(jsonObject);
        //创建竖式运算对象
        RecursiveEquation recursiveEquation = new RecursiveEquation();
        //第一题
        for(int i=0; i<nameArray1.length; i++){
            jsonObject=new JSONObject();
            HashMap<String, Object> result = recursiveEquation.generativeExpression();
            jsonObject.put("name",nameArray1[i]);
            jsonObject.put("value",result.get("expression"));//这里是自动生成
            jsonObject.put("answer",result.get("answer"));//这里是自动生成
            list.add(jsonObject);
        }
        Algorithm algorithm = new Algorithm();
        List<JSONObject> list2 = algorithm.recursiveComputation(nameArray2);

        String path = "src/main/webapp/TemplateDoc/title.doc";
        try {
            File file = new File(path);
            System.out.println(file.getCanonicalPath());
            String filePath = file.getCanonicalPath();
            InputStream is = new FileInputStream(filePath);
            OutputStream outputStream = null;
            HWPFDocument doc = null;
            doc= new HWPFDocument(is);
            Range range = doc.getRange();
            for(JSONObject jsonObject1 : list){
                range.replaceText(jsonObject1.getString("name"),jsonObject1.getString("value"));
            }
            for (JSONObject jsonObject2 :list2){
                range.replaceText(jsonObject2.getString("name"),jsonObject2.getString("value"));
            }
            //  /home/cnxjk/下载/new.doc
            outputStream = new FileOutputStream(new File("D:\\BeeEdu\\SmartQuestionBank\\src\\main\\webapp\\OutputTemplate\\new.doc"));
            doc.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}