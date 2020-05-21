package com.enablue.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.dto.TemplateDTO;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;
import com.enablue.util.IfOs;
import com.enablue.util.baidu.AuthService;
import com.enablue.util.baidu.Base64Util;
import com.enablue.util.baidu.FileUtil;
import com.enablue.util.baidu.HttpUtil;
import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.Section;
import com.spire.doc.collections.DocumentObjectCollection;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 冰蓝doc工具类
 */
public class SpireUtil {


    /**
     * 读取word文件内容
     *
     * @param path
     * @return buffer
     */
    public String readWord(String path) {
        String buffer = "";
        if (path.endsWith(".doc")||path.endsWith(".docx")) {
            Document document = new Document(path);
            buffer = document.getText();
        } else {
            System.out.println("此文件不是word文件！");
        }
        return buffer;
    }

    /**
     * 把读取的文本格式化城类型和试题内容
     * @param buffer
     * @return
     */
    public HashMap<String,Object> plateFormat(String buffer){
        String[] plate = buffer.split("[一二三四五六七八九十](\\、|\\.)");
        HashMap<String,Object> map = new HashMap<>();
        for (int i = 1; i < plate.length ; i++) {
            //截取出题目类型
            int index = plate[i].indexOf("\r\n");
            String substring = plate[i].substring(0, index);
            substring = substring.replaceAll("[^\u4E00-\u9FA5]", "");
            //截取类型下所有题目
            String context = plate[i].substring(index + 2);
            //封装到map
            map.put(substring,context);
        }
        return map;
    }
    /**
     * 把读取的文本格式化成试题
     * @param text
     * @return
     */
    public List<TemplateDTO> templateFormat(String text, SubjectPool subject, TypePool type){
        TemplateDTO templateDTO;

        String[] split = text.split("[1-99]\\)\\、");
        List<TemplateDTO> list=new ArrayList<>();
        for (int i=0;i<split.length;i++) {
            if (!"".equals(split[i])) {
                System.out.println("split = " + split[i]);
                templateDTO = new TemplateDTO();
                //显示在页面上的内容
                templateDTO.setSubject(subject.getSubjectName());
                templateDTO.setType(type.getPlateName());
                //要携带的数据
                templateDTO.setSubjectId(subject.getSubjectId());
                templateDTO.setTypeId(type.getPlateId());
                templateDTO.setDifficultyGrade(0);
                templateDTO.setTemplateContent(split[i]);
                list.add(templateDTO);
            }
        }
        return list;
    }

    /**
     *
     * @param file 读取的word路径
     * @return
     * @throws IOException
     */
    //读取doc
    public String readDoc(String file) throws IOException {
        //初始化一个Document实例并加载Word文档
        Document doc = new Document();
        doc.loadFromFile(file);
        //拿到全局文本
        StringBuffer docText = new StringBuffer(doc.getText());
        //拿到文档的各个节点之后遍历
        SectionCollection sections = doc.getSections();
        for (Iterator it1=sections.iterator();it1.hasNext();){
            Section next = (Section) it1.next();
            //从节点中拿到段落遍历
            ParagraphCollection paragraphs = next.getParagraphs();
            for (Iterator it2=paragraphs.iterator();it2.hasNext();){
                
                Paragraph next1 = (Paragraph) it2.next();
                //段落文本处理
                String paragraphText = next1.getText().replaceAll("\u000B","\r\n");
                //空段落返回
                if (paragraphText.equals("")){
                    continue;
                }
                DocumentObjectCollection childObjects = next1.getChildObjects();
                //如果是空字符串就不保存图片
                boolean  flag=true;
                //如果图片连续读取则跳过
                boolean  flag1=true;
                //获取替换公式后文本
                StringBuffer temp = new StringBuffer();
                //遍历子对象
                for (Iterator it3=childObjects.iterator();it3.hasNext();){
                    //拿到子对象
                    DocumentObject next2 = (DocumentObject) it3.next();

                    if (next2.getDocumentObjectType() == DocumentObjectType.Text_Range){
                        TextRange textRange= (TextRange) next2;
                        //拿到子对象文本去空格
                        String textRangeText = textRange.getText().trim();

                        temp.append(textRangeText);
                        flag=true;
                        flag1=true;
                        if ("".equals(textRangeText)){
                            temp.append(" ");
                            flag=false;
                            continue;
                        }
                    }
                    //拿到子对象图片
                    if (next2.getDocumentObjectType() == DocumentObjectType.Picture){
                        if (flag&&flag1){
                            DocPicture picture= (DocPicture) next2;
                            String storageDirectory=IfOs.ifOsResourceValue("exploit.download.path","server.download.path","config/global");
                            //设置图片路径和格式
                            String imageName=System.currentTimeMillis()+".jpg";
                            String filePath=storageDirectory+File.separator+imageName;
                            ImageIO.write(picture.getImage(),"JPG",new File(filePath));
                            //获取图片识别后字符串
                            String recognition = recognition(filePath);
                            temp.append(recognition);
                        }
                        flag1=false;
                    }

                }
                //用转换后的段落替换原来的段落
                int i = docText.indexOf(paragraphText);
                docText.insert(i,temp);
                docText.delete(i+temp.length(),i+temp.length()+paragraphText.length());
            }
        }
        //返回
        return docText.toString();
    }

    /**
     * 识别公式
     * @param filePath
     * @return
     */
    public String  recognition(String filePath){
        try{
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post("https://aip.baidubce.com/rest/2.0/ocr/v1/formula", accessToken, param);
            //调用休眠0.1秒
            Thread.sleep(500);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("words_result");
            String words="";
            for (Object object:jsonArray){
                 JSONObject jsonObject1= (JSONObject) object;
                 words = jsonObject1.getString("words");
            }
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
