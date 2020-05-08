package com.enablue.test;

import com.enablue.common.PoiUtil;
import com.enablue.dto.TemplateDTO;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;
import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.Paragraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    }
}
