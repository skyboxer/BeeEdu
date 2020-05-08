package com.enablue.test;

import com.enablue.common.PoiUtil;
import com.enablue.dto.TemplateDTO;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo {
    public static void main(String[] args) {
        PoiUtil poiUtil = new PoiUtil();
        String buffer = poiUtil.readWord("C:\\Users\\Administrator\\Desktop\\高二数学下学期期末复习题5(www.diyifanwen.com).doc");
        HashMap<String, Object> map = poiUtil.plateFormat(buffer);
        for (Map.Entry<String,Object> entry:map.entrySet()) {
            SubjectPool subjectPool = new SubjectPool();
            subjectPool.setSubjectId(1);
            subjectPool.setSubjectName("三年级数学");
            TypePool typePool = new TypePool();
            typePool.setPlateId(1);
            typePool.setPlateName("应用题");
            List<TemplateDTO> list = poiUtil.templateFormat(entry.getValue().toString(),subjectPool,typePool);
            
        }
        
    }
}
