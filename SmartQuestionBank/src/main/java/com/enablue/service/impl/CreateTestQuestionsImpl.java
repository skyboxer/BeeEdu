package com.enablue.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.*;
import com.enablue.pojo.*;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.service.UserService;
import com.enablue.util.RandomNumFactory;
import com.enablue.util.TemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cnxjk
 * 创建试卷
 */
@Service
public class CreateTestQuestionsImpl implements CreateTestQuestionsService {

    @Autowired
    private TemplatePoolMapper templatePoolMapper;
    @Autowired
    private VariablePoolMapper variablePoolMapper;
    @Autowired
    private TPAnswerMapper tpAnswerMapper;
    @Autowired
    private UserFileMapper userFileMapper;
    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private BaseController baseController;
    @Autowired
    private ModelMapper modelMapper;

    private TPAnswer tpAnswer;

    @Override
    public JSONArray createTestQuestion(List<TemplatePool> templateWhereList) {
        JSONArray testQuestionJSON = new JSONArray();
        //存放每个板块内容
        List<TemplatePool> typeTemplatePoolList;
        //存放答案
        TPAnswer tpAnswerPool;
        //存放变量
        List<VariablePool> tpVariableList ;
        //遍历每个板块里的所有困难等级
        for (TemplatePool templatePoolWhere : templateWhereList){
            if(templatePoolWhere.getTemplateNum() != null && templatePoolWhere.getTemplateNum()!=0 ){
                typeTemplatePoolList= templatePoolMapper.getTemplatePooList(templatePoolWhere);
                for(TemplatePool templatePool : typeTemplatePoolList){
                    tpVariableList = variablePoolMapper.getVariablePoolList(templatePool.getTemplateId());
                    templatePool.setVariablePoolList(tpVariableList);
                    tpAnswerPool = tpAnswerMapper.getTPAswer(templatePool.getAnswerId());
                    templatePool.setTpAnswer(tpAnswerPool);
                }
                testQuestionJSON.add(templatePoolFactory(typeTemplatePoolList,templatePoolWhere.getTemplateNum()));
            }
        }

        return testQuestionJSON;
    }

    public List<TemplatePool> templatePoolFactory(List<TemplatePool> typeTemplatePoolList,int sum){
        List<TemplatePool> templatePoolList = new ArrayList<>();
        if(typeTemplatePoolList.size()>0){
            for(int i =0;i<sum;i++){
                TemplatePool templatePool = new TemplatePool();
                //题目
                int templateIndex= RandomNumFactory.RandomNumFactory(new int[]{0,typeTemplatePoolList.size()});
                String templateContent = typeTemplatePoolList.get(templateIndex).getTemplateContent();
                System.out.println("题目1>>>>=============================>>>"+templateContent);
                StringBuffer newTemplateContent = new StringBuffer();
                char[] templateContentChar = templateContent.toCharArray();
                for (char string : templateContentChar) {
                    // 判断是否为数字
                    if ((string+"").matches("[0-9]")){
                        newTemplateContent.append(RandomNumFactory.RandomNumFactory(new int[]{0,10}));
                        continue;
                    }
                    newTemplateContent.append(string);
                }
                templatePool.setTemplateContent(String.valueOf(newTemplateContent));
                System.out.println("题目2"+templatePool.getTemplateContent());
                templatePoolList.add(templatePool);
            }

        }
        return templatePoolList;
    }

    /**
     * 应用题工厂函数
     * @param typeTemplatePoolList  id 集合
     * @param nameArray 占位符
     * @return
     */
    public List<JSONObject> templatePoolFactoryTwo(List<TemplatePool> typeTemplatePoolList,String[] nameArray){
        List<JSONObject> jsonObjects = new ArrayList<>();
        int index = 0;
        for (TemplatePool templatePoolWhere : typeTemplatePoolList){
                List<TemplatePool> templatePoolList = templatePoolMapper.getTemplatePooList(templatePoolWhere);
                for(TemplatePool templatePool : templatePoolList){
                    List<VariablePool> tpVariableList = variablePoolMapper.getVariablePoolList(templatePool.getTemplateId());
                    templatePool.setVariablePoolList(tpVariableList);
                   /* tpAnswerPool = tpAnswerMapper.getTPAswer(templatePool.getAnswerId());
                    templatePool.setTpAnswer(tpAnswerPool);*/
                }
            jsonObjects.add(TemplateFactory.templateJSONObjectFactory(templatePoolList.get(0),nameArray[index]));
            index ++;
        }
        return jsonObjects;
    }

    @Override
    public JSONObject saveTestQuestion(String fileName, String newFileName) {
        User user= baseController.getSessionUser();
        int userFileIndex = userFileMapper.addUserFile(new UserFile(user.getUserId(),fileName,newFileName));
        if(userFileIndex>0){
            return commonReturnValue.CommonReturnValue(0,"保存成功");
        }
        return commonReturnValue.CommonReturnValue(1005,"保存失败");
    }

    @Override
    public JSONObject getTestQuestionSaveLog() {
        User user= baseController.getSessionUser();
        List<UserFile> userFileList = userFileMapper.getUserFile(new UserFile(user.getUserId()));
        if(userFileList.size()>0){
            System.out.println(userFileList.size());
            return commonReturnValue.CommonReturnValue(0,"成功！",userFileList);
        }
        return commonReturnValue.CommonReturnValue(1005,"查询失败");
    }
    /**
     * 应用题工厂函数
     * @param templatePool  id 集合
     * @param nameArray 占位符
     * @return
     */
    public List<JSONObject> templatePoolFactoryFour(TemplatePool templatePool,String[] nameArray){
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<TemplatePool> templatePoolList = templatePoolMapper.getTemplatePooList(templatePool);
        JSONObject jsonObject;
        int [] indexList = RandomNumFactory.RandomNumIndex(templatePoolList.size(),nameArray.length);
        int nums = nameArray.length;
        if(templatePoolList.size()<nameArray.length){
            nums = templatePoolList.size();
        }
        for(int i = 0;i<nums;i++){
            jsonObject = new JSONObject();
            jsonObject.put("name",nameArray[i]);
            jsonObject.put("value",templatePoolList.get(indexList[i]).getTemplateContent());
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    @Override
    public JSONObject getModelList(Model model) {
        List<Model> modelList = modelMapper.getModelList(model);
        /*int count = modelMapper.getModelCount(model);*/
        if(modelList.size()>=0){
            return commonReturnValue.CommonReturnValue(0,modelList,0);
        }
        return commonReturnValue.CommonReturnValue(-1,"失败");
    }

}
