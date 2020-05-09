package com.enablue.test;

import com.enablue.common.PoiUtil;
import com.enablue.dto.TemplateDTO;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;
import com.latextoword.Latex_Word;
import com.spire.doc.Document;
import com.spire.doc.HtmlExportOptions;
import com.spire.doc.Section;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.interfaces.ICompositeObject;
import com.spire.doc.interfaces.IDocumentObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.*;


public class Demo {
    public static void main(String[] args) {
        /*PoiUtil poiUtil = new PoiUtil();
        String buffer = poiUtil.readWord("C:\\Users\\Administrator\\Documents\\WeChat Files\\wc15250138779\\FileStorage\\File\\2020-04\\57kcpi5ucklbrvh715585209630.doc");
        HashMap<String, Object> map = poiUtil.plateFormat(buffer);
        for (Map.Entry<String,Object> entry:map.entrySet()) {
            SubjectPool subjectPool = new SubjectPool();
            subjectPool.setSubjectId(1);
            subjectPool.setSubjectName("三年级数学");
            TypePool typePool = new TypePool();
            typePool.setPlateId(1);
            typePool.setPlateName("应用题");
            List<TemplateDTO> list = poiUtil.templateFormat(entry.getValue().toString(),subjectPool,typePool);
            
        }*/
        //加载Word文档
        /*Document doc = new Document("E:\\b.docx");
        SectionCollection section = doc.getSections();
        OfficeMath officeMath = new OfficeMath(doc);
        paragraph.Items.Add(officeMath);
        officeMath.FromLatexMathCode("x^{2}+\\sqrt{x^{2}+1}=2");
        doc.SaveToFile("latexToDoc.docx", FileFormat.Docx);*/

        /*late转换word mate*/
        /*String latexStr="\\(\\sqrt[3]{2+x}\\)";
        String bu= Latex_Word.latexToWord(latexStr);
        System.out.println(bu);*/


        //加载Word文档
        /*Document document = new Document();
        document.loadFromFile("C:\\Users\\cn_xj\\Desktop\\公式测试.docx");

        //获取文档中的文本保存为String
        String text=document.getText();
        System.out.println(text);
        System.out.println(document.getHTMLCustomComment());
        List list = document.getHTMLIdentifierPunctuations();
        for(Object a : list){
            System.out.println(a.toString());
        }
*/
    }
}
