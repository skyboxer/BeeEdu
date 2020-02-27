package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.SessionCommon;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.WordTranslationService;
import com.enablue.util.PXmlFormat;
import com.enablue.util.WXmlFormat;
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
import java.util.regex.Pattern;

/**
 * 上传文件翻译
 */
@RestController
@RequestMapping(value = "uploadFileTranslate")
public class UploadFileTranslateController {
    @Autowired
    public WordTranslationService wordTranslationService;

    @Autowired
    private AppDetailMapper appDetailMapper;
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;
    private ApplicationDetailOperation applicationDetailOperation;
    @Autowired
    private SessionCommon sessionCommon;

    private  int pptTextCountTotal = 0;

    @RequestMapping(value = "wordUploadTranslate", method = RequestMethod.POST, produces = "application/json")
    public JSONObject wordUploadTranslate(String from, String to, String fileName, String engineType, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        //word文件路径
        String uploadFilePath = req.getServletContext().getRealPath("/upload/") + fileName;
        try {
            Boolean formatStatus =WXmlFormat.paragraphFormat(uploadFilePath);
            if(formatStatus==false){
                jsonObject.put("code", -1);
                jsonObject.put("msg", "格式化失败！");
                return jsonObject;
            }
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(uploadFilePath));//上传的 xml 对象
            List<Element> doc = document.getRootElement().elements();//xml内容的doc标签
            Element wDocument = null;
            for (Element part0 : doc) {
                wDocument = part0.element("xmlData").element("document");
                if (wDocument != null) {
                    break;
                }
            }
            List<Node> nodeList = wDocument.selectNodes("//w:body//w:t");
            String text;
            StringBuffer translateText;
            int textTotalNum;
            //获取用户ID
            List<AppDetail> appConfig;
            int nowEndServiceTotal;
            Account account = (Account) sessionCommon.getSession().getAttribute("account");
            switch (engineType) {
                case "Google":
                            textTotalNum = 0;
                            for (Node node : nodeList) {
                                text = node.getText();
                                if(text == null || text.isEmpty()){
                                    continue;
                                }
                                Thread.sleep(2000);
                                translateText = wordTranslationService.googleTreansl(from, to, text);
                                if (translateText != null) {
                                    node.setText(String.valueOf(translateText));
                                }
                                System.out.println("原文：===="+textTotalNum+"=====》》》"+text);
                                System.out.println("译文：=========》》》"+translateText);
                                textTotalNum +=text.length();
                            }
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(0, "google",
                            2,textTotalNum,textTotalNum,  account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                    break;
                case "百度":
                    textTotalNum = 0;
                            for (Node node : nodeList) {
                                text = node.getText();
                                if(text == null || text.isEmpty()){
                                    continue;
                                }
                                Thread.sleep(2000);
                                translateText = wordTranslationService.baiduTransl(from, to, text, engineType, req);
                                    if (translateText != null) {
                                        node.setText(String.valueOf(translateText));
                                }
                                System.out.println("原文：=========》》》"+text);
                                System.out.println("译文：=========》》》"+translateText);
                                textTotalNum +=text.length();
                            }
                    //获取用户ID
                   appConfig = appDetailMapper.queryAppDetailByType(2, textTotalNum,engineType);
                    // 统计调用量并记录
                    nowEndServiceTotal = appConfig.get(0).getResidualService() -textTotalNum;
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                            2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                    //修改操作剩余服务量
                    appConfig.get(0).setResidualService(nowEndServiceTotal);
                    appDetailMapper.updateAppDetail(appConfig.get(0));
                    break;
                case "讯飞":
                    textTotalNum = 0;
                            for (Node node : nodeList) {
                                text = node.getText();
                                if(text == null || text.isEmpty()){
                                    continue;
                                }
                                    translateText = wordTranslationService.xunfeiTransl(from, to, text, engineType, req);
                                Thread.sleep(2000);
                                    if (translateText != null) {
                                        node.setText(String.valueOf(translateText));
                                }
                                System.out.println("原文：=========》》》"+text);
                                System.out.println("译文：=========》》》"+translateText);
                                textTotalNum +=text.length();
                                }
                    //获取用户ID
                   appConfig = appDetailMapper.queryAppDetailByType(2, textTotalNum,engineType);
                    // 统计调用量并记录
                    nowEndServiceTotal = appConfig.get(0).getResidualService() -textTotalNum;
                    //获取用户ID
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                            2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                    //修改操作剩余服务量
                    appConfig.get(0).setResidualService(nowEndServiceTotal);
                    appDetailMapper.updateAppDetail(appConfig.get(0));
                    break;
            }

            FileOutputStream outputStream = new FileOutputStream(uploadFilePath);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(outputStream,format);
            writer.write(document);
            writer.close();
            System.out.println("翻译完成，写入完毕！");
            jsonObject.put("code", 0);
            jsonObject.put("data", uploadFilePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @RequestMapping(value = "pptUploadTranslate", method = RequestMethod.POST, produces = "application/json")
    public JSONObject pptUploadTranslate(String from, String to, String fileName, String engineType, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        //word文件路径
        String uploadFilePath = req.getServletContext().getRealPath("/upload/") + fileName;
        try {
            Boolean formatStatus = PXmlFormat.analyzePPTXml(uploadFilePath);
            if(formatStatus==false){
                jsonObject.put("code", -1);
                jsonObject.put("msg", "格式化失败！");
                return jsonObject;
            }
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(uploadFilePath));//上传的 xml 对象
            Element elementRoot = document.getRootElement();
            List<Element> elementPartList = elementRoot.elements();
            String elementName = null;
            String pattern = "^\\/ppt\\/slides\\/slide[1-9]\\d*.xml$";
            this.pptTextCountTotal = 0;
            for(Element elementPart : elementPartList) {
                elementName = elementPart.attributeValue("name");
                //每页ppt对象
                if(Pattern.matches(pattern,elementName)) {
                    Element spTree =  elementPart.element("xmlData").element("sld").element("cSld").element("spTree");
                    traversal(spTree,engineType,from,to,req);
                }
            }
            if(this.pptTextCountTotal !=0){
                Account account = (Account) sessionCommon.getSession().getAttribute("account");
                //获取用户ID
                if(engineType.equals("Google")){
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(0, "google",
                            2,this.pptTextCountTotal,this.pptTextCountTotal,  account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                }else{
                    List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, this.pptTextCountTotal,engineType);
                    // 统计调用量并记录
                    int nowEndServiceTotal = appConfig.get(0).getResidualService() -this.pptTextCountTotal;
                    //获取用户ID
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                        2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                    //修改操作剩余服务量
                    appConfig.get(0).setResidualService(nowEndServiceTotal);
                    appDetailMapper.updateAppDetail(appConfig.get(0));
                }
            }
            FileOutputStream outputStream = new FileOutputStream(uploadFilePath);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(outputStream,format);
            writer.write(document);
            writer.close();
            System.out.println("翻译完成，写入完毕！");
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

    private   void traversal(Element spTree,String engineType,String from,String to,HttpServletRequest req){
        List<Element> childList = spTree.elements("sp");
        List<Element> child1List = spTree.elements("grpSp");
        if(childList.size()>0){
           int textEacnSheetTotal = elementSpTraversal(childList, engineType, from, to, req);
           this.pptTextCountTotal+=textEacnSheetTotal;
        }
        if(child1List.size()>0){
            for(Element child1 : child1List){
                traversal(child1,engineType, from, to, req);
            }
        }
    }

    private   int elementSpTraversal(List<Element> elementSpList,String engineType,String from,String to,HttpServletRequest req) {
        List<Element> elementPList ;      //单行集合
        List<Element> elementRList;      //单行集合
        StringBuffer text = null;
        int textEachSheetTotal = 0;
        for (Element elementSP : elementSpList) {
            elementPList = elementSP.element("txBody").elements("p");
            for (Element elementP : elementPList) {
                elementRList = elementP.elements("r");
                if (elementRList.size() <= 0) {
                    continue;
                }
                for (Element elementR : elementRList) {
                    System.out.println(elementR.element("t").getText());
                    Boolean kongGe = Pattern.matches("^[\\s]*$", elementR.element("t").getText());
                    if (kongGe) {
                        continue;
                    }
                    text = new StringBuffer(elementR.element("t").getText());
                    StringBuffer translateText = null;
                    switch (engineType) {
                        case "Google":
                            translateText = wordTranslationService.googleTreansl(from, to, text.toString());
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            if (translateText != null) {
                                elementRList.get(0).element("t").setText(String.valueOf(translateText));
                            }
                            break;
                        case "百度":
                            translateText = wordTranslationService.baiduTransl(from, to, text.toString(), engineType, req);
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            if (translateText != null) {
                                elementRList.get(0).element("t").setText(String.valueOf(translateText));
                            }
                            break;
                        case "讯飞":
                            translateText = wordTranslationService.xunfeiTransl(from, to, text.toString(), engineType, req);
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            if (translateText != null) {
                                elementRList.get(0).element("t").setText(String.valueOf(translateText));
                            }
                            break;
                    }
                    textEachSheetTotal +=text.length();
                    System.out.println("原文 ====>>" + text);
                    System.out.println("译文 <<====" + translateText.toString());
                }
            }
        }
        return textEachSheetTotal;
    }
}