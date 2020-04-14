package com.enablue.service.impl;

import com.enablue.dto.TemplateDTO;
import com.enablue.mapper.*;
import com.enablue.pojo.*;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author cnxjk
 * 模板持久层接口
 */
@Service
public class InputTestQuestionsImpl implements ImpotTestQuestionsService {

    @Autowired
    private TemplatePoolMapper templatePoolMapper;
    @Autowired
    private SubjectPoolMapper subjectPoolMapper;
    @Autowired
    private VariablePoolMapper variablePoolMapper;
    @Autowired
    private TPAnswerMapper tpAnswerMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private TypePoolMapper typePoolMapper;

    @Override
    @Transactional
    public int  addTestQuestions(TemplatePool templatePool, List<VariablePool> variablePoolList, TPAnswer tpAnswer, MultipartFile file){
        //返回的是答案id
        int tpAnswerStatus = tpAnswerMapper.addTPAswer(tpAnswer);
        //设置答案id
        templatePool.setAnswerId(tpAnswer.getAnswerId());
        //添加模板
        int tempStatus =templatePoolMapper.addTemplatePool(templatePool);
        if(tempStatus<=0 || tpAnswerStatus<=0){
            return -1;
        }
        int variableStatus = 0;
        //添加变量
        for (VariablePool variablePool : variablePoolList) {
            //设置标识变量的模板id
            variablePool.setTemplateId(templatePool.getTemplateId());
            //添加标识变量
            variableStatus = variablePoolMapper.addVariablePool(variablePool);
            if(variableStatus<=0){
                //添加失败手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        //添加图片
        if (file!=null){
            if (file.getSize()/1024>100){
                //添加失败手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
            try {
            //设置数据
            Image image = new Image();
            image.setCreatTime(new Date());
            image.setTemplateId(templatePool.getTemplateId());
            image.setImageData(file.getBytes());
            image.setImageName(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            image.setImageFormat(fileName.substring(fileName.lastIndexOf(".") + 1));
            //添加图片
            int imageCount = imageMapper.addImage(image);
            if (imageCount<1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
            }catch (Exception e){
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return 1;
    }


    /**
     * 修改试题模板
     * @param templatePool 试题模板
     * 王成
     * @param file 图片文件
     * @return
     */
    @Override
    public HashMap<String, Object> updataTemplate(TemplatePool templatePool, MultipartFile file) {
        HashMap<String, Object> result = new HashMap<>();
        templatePool.setGetModified(new Date());
        int count=templatePoolMapper.updataTemplate(templatePool);
        if (count<1){
            result.put("code",-1);
            result.put("msg","修改失败");
            return result;
        }
        //修改图片
        if (file!=null){
            if (file.getSize()/1024>100){
                result.put("code",-1);
                result.put("msg","文件过大");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            try {
                //设置数据
                Image image = new Image();
                image.setUpdateTime(new Date());
                image.setTemplateId(templatePool.getTemplateId());
                image.setImageData(file.getBytes());
                image.setImageName(file.getOriginalFilename());
                String fileName = file.getOriginalFilename();
                image.setImageFormat(fileName.substring(fileName.lastIndexOf(".") + 1));
                //修改图片
                int imageCount = imageMapper.updateImg(image);
                if (imageCount<1){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    result.put("code",-1);
                    result.put("msg","修改失败");
                    return result;
                }
            }catch (Exception e){
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }

        result.put("code",0);
        result.put("msg","修改成功");
        return result;
    }

    /**
     * 分页查询试题模板
     * 王成
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> queryPageTemplatePool(Long page,Long limit) {
        if (page==null || page<1){
            page=1L;
        }
        if (limit==null){
            limit=10L;
        }
        //分页设置
        page=(page-1)*limit;
        //结果集封装
        HashMap<String, Object> result=new HashMap<>();
        //查询模板数据
        List<TemplatePool> templatePools = templatePoolMapper.queryAllTemplate();
        List<TemplatePool> templatePoolList=templatePoolMapper.queryPageTemplatePool(page,limit);
        //未查询到结果就返回
        if (templatePools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        //封装前端需要的数据
        TemplateDTO templateDTO;
        List<TemplateDTO> templateDTOList = new ArrayList<>();
        for (TemplatePool templatePool:templatePoolList) {
            templateDTO=new TemplateDTO();
            //根据模板的数据查询到对应的科目数据
            SubjectPool subjectPool = subjectPoolMapper.querySubjectById(templatePool.getSubjectId());
            //设置科目名
            templateDTO.setSubject(subjectPool.getSubjectName());
            //根据模板数据查询到对应的类型数据
            TypePool typePool = typePoolMapper.queryTypeById(templatePool.getTypeId());
            //设置类型名
            templateDTO.setType(typePool.getPlateName());
            //根据答案id查询到对应答案
            TPAnswer tpAswer = tpAnswerMapper.getTPAswer(templatePool.getAnswerId());
            //设置答案
            templateDTO.setAnswer(tpAswer.getAnswerContent());
            //设置其他数据
            templateDTO.setDifficultyGrade(templatePool.getDifficultyGrade());
            templateDTO.setTemplateContent(templatePool.getTemplateContent());
            templateDTO.setTemplateId(templatePool.getTemplateId());
            templateDTO.setTemplateNum(typePool.getAmount());
            templateDTOList.add(templateDTO);

        }
        //封装结果集
        result.put("code", 0);
        result.put("data", templateDTOList);
        //总记录数
        result.put("count",templatePools.size());
        result.put("msg","查询成功");
        return result;
    }

    /**
     * 删除模板
     * 王成
     * @param id
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> deleteTemplatePool(int id) {
        HashMap<String, Object>  result = new HashMap<>();
        int count = templatePoolMapper.deleteTemplatePool(id);
        if (count<1){
            result.put("code",-1);
            result.put("msg","删除失败");
            return result;
        }
        result.put("code",0);
        result.put("msg","删除成功");
        return result;
    }

}
