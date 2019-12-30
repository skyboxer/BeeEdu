package com.enablue.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class POIUtil {

    /**
     * @throws Exception
     * @Description: POI 读取  word
     * @create: 2019-07-27 9:48
     * @update logs
     */
    public static List<Character> readWord(String filePath) throws Exception {

        List<String> linList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        String buffer = "";
        try {
            String suffix = filePath.substring(filePath.lastIndexOf(".")+1);
            switch (suffix){
                case "doc":
                    InputStream is = new FileInputStream(new File(filePath));
                    WordExtractor ex = new WordExtractor(is);
                    buffer = ex.getText();
                    //ex.close();
                    if (buffer.length() > 0) {
                        //使用回车换行符分割字符串
                        String[] arry = buffer.split("\\r\\n");
                        for (String string : arry) {
                            linList.add(string.trim());
                            for(Character character:string.trim().toCharArray()){
                                charList.add(character);
                            }
                        }
                    }
                    break;
                case "docx":
                    OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                    POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                    buffer = extractor.getText();
                    // extractor.close();

                    if (buffer.length() > 0) {
                        //使用换行符分割字符串
                        String[] arry = buffer.split("\\n");
                        for (String string : arry) {
                            linList.add(string.trim());
                            for(Character character:string.trim().toCharArray()){
                                charList.add(character);
                            }
                        }
                    }
                    break;
                case "txt":
                    File file = new File(filePath);
                    Reader fileRead = null;
                    try {
                        fileRead = new FileReader(file);//创建读取字符流对象
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    int temp;
                    try {//获取读取内容
                        while ((temp = fileRead.read()) != -1) {
                            charList.add((char)temp);
                        }
                        fileRead.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    return  null;
            }
            return charList;
        } catch (Exception e) {
            System.out.print("error---->" + filePath);
            e.printStackTrace();
            return null;
        }
    }

}
