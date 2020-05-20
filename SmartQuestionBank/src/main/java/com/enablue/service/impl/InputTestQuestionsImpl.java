package com.enablue.service.impl;

import com.enablue.common.SpireUtil;
import com.enablue.dto.TemplateDTO;
import com.enablue.mapper.*;
import com.enablue.pojo.*;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
        if (tpAnswer!=null){
            int tpAnswerStatus = tpAnswerMapper.addTPAswer(tpAnswer);
            if(tpAnswerStatus<=0){
                return -1;
            }
            //设置答案id
            templatePool.setAnswerId(tpAnswer.getAnswerId());
        }

        //添加模板
        int tempStatus =templatePoolMapper.addTemplatePool(templatePool);
        if(tempStatus<=0){
            return -1;
        }
        int variableStatus = -1;
        if (variablePoolList!=null){
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
     * @param variableQuantity
     * @param tpAnswer 答案
     * @param file 图片文件
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> updataTemplate(TemplatePool templatePool, String variableQuantity, TPAnswer tpAnswer, MultipartFile file) {
        HashMap<String, Object> result = new HashMap<>();
        templatePool.setGetModified(new Date());
        //根据模板id查询数据主要目的是拿到答案id
        TemplatePool template = templatePoolMapper.queryTemplateById(templatePool.getTemplateId());
        //根据拿到的答案id查询答案
        TPAnswer aswer = tpAnswerMapper.getTPAswer(template.getAnswerId());

        //如果未查询到答案就新建答案
        if (aswer==null){
            aswer=new TPAnswer();
            aswer.setAnswerContent(tpAnswer.getAnswerContent());
            aswer.setGmtModified(new Date());
            aswer.setGmtCreate(new Date());
            int count = tpAnswerMapper.addTPAswer(aswer);
            if (count<1 ){
                result.put("code",-1);
                result.put("msg","答案修改失败");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            //创建答案成功修改模板的答案id
            templatePool.setAnswerId(aswer.getAnswerId());
        }else {
            //如果查询到答案就修改答案
            aswer.setAnswerContent(tpAnswer.getAnswerContent());
            int count = tpAnswerMapper.updateAnswer(aswer);
            if (count<1 ){
                result.put("code",-1);
                result.put("msg","答案修改失败");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            //修改完成设置好模板的答案id
            templatePool.setAnswerId(aswer.getAnswerId());
        }

        //删除原来的参数
        variablePoolMapper.deleteByTemplateId(templatePool.getTemplateId());
        //创建新的参数
        if(variableQuantity!=null){
            //分离出试题中的标识变量添加到变量表中
            String[] strings = variableQuantity.split("&");
            for (int i = 0; i < strings.length; i++) {
                VariablePool variablePool = new VariablePool();
                variablePool.setGmtCreate(new Date());
                variablePool.setGmtModified(new Date());
                variablePool.setVariableContent(strings[i]);
                variablePool.setTemplateId(templatePool.getTemplateId());
                variablePoolMapper.addVariablePool(variablePool);
            }

        }
        //修改模板
        int count=templatePoolMapper.updataTemplate(templatePool);
        if (count<1 ){
            result.put("code",-1);
            result.put("msg","模板修改失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        //修改图片
        if (file!=null&&file.getSize()>0){
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
                    result.put("msg","图片修改失败");
                    return result;
                }
            }catch (Exception e){
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }

        result.put("code",0);
        result.put("msg","模板修改成功");
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
            if (typePool!=null){
                //设置类型名
                templateDTO.setType(typePool.getPlateName());
            }

            //根据答案id查询到对应答案
            if (templatePool.getAnswerId()!=null){
                TPAnswer tpAswer = tpAnswerMapper.getTPAswer(templatePool.getAnswerId());
                templateDTO.setAnswer("");
                if(tpAswer!=null){
                    //设置答案
                    templateDTO.setAnswer(tpAswer.getAnswerContent());
                }
            }
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


    /**
     * 批量插入试题模板
     * @param templatePoolList
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> addListTemplate(List<TemplatePool> templatePoolList) {
        HashMap<String, Object> result = new HashMap<>();
        //遍历数据
        int i = templatePoolMapper.addListTemplate(templatePoolList);
        if (i<1){
            result.put("code",-1);
            result.put("msg","添加失败");
            return result;
        }
        result.put("code",1);
        result.put("msg","添加成功");
        return result;
    }

    /**
     * 读取文档
     *
     * @param subjectPool
     * @param file
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> readDocument(SubjectPool subjectPool, MultipartFile file) {
        //获取文件全名
        String fileName = file.getOriginalFilename();
        //获取文件格式
        String format = fileName.substring(fileName.lastIndexOf(".") + 1);
        //准备结果集
        HashMap<String, Object> result = new HashMap<>();
        //判断上传文件是否是word文档格式
        if ("doc".equals(format)||"docx".equals(format)){
            try {
                //获取服务器中的路径
                WebApplicationContext webApplicationContext = ContextLoader
                        .getCurrentWebApplicationContext();
                ServletContext servletContext = webApplicationContext
                        .getServletContext();
                String realPath = servletContext.getRealPath("/download");
                //上传文件到realPath目录
                file.transferTo(new File(realPath,file.getOriginalFilename()));
                //准备读取文档内容
                SpireUtil spireUtil = new SpireUtil();
                //读取文本内容
                String word = spireUtil.readDoc(realPath + File.separator + file.getOriginalFilename(),"C:\\Users\\Administrator\\Desktop\\images\\");
                System.out.println("word = " + word);
                //分离内容
                HashMap<String, Object> map = spireUtil.plateFormat(word);
                //准备容器
                List<TemplateDTO> list=new ArrayList<>();
                //遍历map集合
                for (Map.Entry<String,Object> entry: map.entrySet()){
                    //拿到题目类型
                     String key = entry.getKey();
                     //判断该类型是否存在
                    List<TypePool> typePools = typePoolMapper.queryByNameAndSubjectId(key,subjectPool.getSubjectId());
                    TypePool typePool;
                    //不存在则创建
                    if (typePools.size()<1){
                        typePool = new TypePool();
                        typePool.setSubjectId(subjectPool.getSubjectId());
                        typePool.setPlateName(key);
                        typePool.setGmtCreate(new Date());
                        typePool.setGmtModified(new Date());
                        typePool.setAmount(5);
                        int i = typePoolMapper.addTypePool(typePool);
                        if (i<1){
                            result.put("code",-1);
                            result.put("msg","创建类型失败");
                            return result;
                        }
                    }else {
                        //存在则获取到类型的数据
                        typePool=typePools.get(0);
                    }
                    //获取该类型下试题内容
                    String  value = (String) entry.getValue();
                    list.addAll(spireUtil.templateFormat(value, subjectPool, typePool));

                }
                result.put("code",1);
                result.put("msg","读取成功");
                result.put("data",list);
                return result;
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        result.put("code",-1);
        result.put("msg","读取失败");
        return result;
    }

}
