package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.service.WordTranslationService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 上传文件翻译
 */
@RestController
@RequestMapping(value = "uploadFileTranslate")
public class UploadFileTranslateController {
    @Autowired
    public WordTranslationService wordTranslationService;

    @RequestMapping(value = "wordUploadTranslate", method = RequestMethod.POST, produces = "application/json")
    public JSONObject wordUploadTranslate(String from, String to, String fileName, String engineType, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        //word文件路径
        String uploadFilePath = req.getServletContext().getRealPath("/upload/") + fileName;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(uploadFilePath));//上传的 xml 对象
            List<Element> doc = document.getRootElement().elements();//xml内容的doc标签
            Element wDocument = null;
            for (Element part0 : doc) {
                wDocument = part0.element("xmlData").element("document");
                if (wDocument != null) {
                    wDocument = part0.element("xmlData").element("document");
                    break;
                }
            }
            List<Node> nodeList = wDocument.selectNodes("//w:body//w:t");
            String text;
            StringBuffer translateText;
            switch (engineType) {
                case "Google":
                            for (Node node : nodeList) {
                                text = node.getText();
                                if(text.length()>2){
                                    translateText = wordTranslationService.googleTreansl(from, to, text);
                                    if (translateText != null) {
                                        node.setText(String.valueOf(translateText));
                                    }
                                }
                            }
                    break;
                case "百度":
                            for (Node node : nodeList) {
                                System.out.println(node.getStringValue());
                                text = node.getText();
                                if (text.length() > 2) {
                                    translateText = wordTranslationService.baiduTransl(from, to, text, engineType, req);
                                    if (translateText != null) {
                                        node.setText(String.valueOf(translateText));
                                    }
                                }
                            }
                    break;
                case "讯飞":
                            for (Node node : nodeList) {
                                System.out.println(node.getStringValue());
                                text = node.getText();
                                if(text.length()>2){
                                    translateText = wordTranslationService.xunfeiTransl(from, to, text, engineType, req);
                                    if (translateText != null) {
                                        node.setText(String.valueOf(translateText));
                                    }
                                }
                            }
                    break;
            }
            FileOutputStream outputStream = new FileOutputStream(uploadFilePath);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(outputStream,format);
            writer.write(document);
            writer.close();
            jsonObject.put("code", 0);
            jsonObject.put("data", uploadFilePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}