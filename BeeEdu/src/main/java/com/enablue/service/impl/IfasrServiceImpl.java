package com.enablue.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.enablue.pojo.Word;
import com.enablue.service.IfasrService;
import com.enablue.util.TimpStampUtil;
import com.iflytek.msp.cpdb.lfasr.client.LfasrClientImp;
import com.iflytek.msp.cpdb.lfasr.exception.LfasrException;
import com.iflytek.msp.cpdb.lfasr.model.LfasrType;
import com.iflytek.msp.cpdb.lfasr.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 科大讯飞服务实现类
 * @author  王成
 *  2019.12.05 15.44
 */
@Service
public class IfasrServiceImpl implements IfasrService {
    /**
     * 创建语音转写任务
     * @param fileName 文件名
     * @return result 结果集
     */
    @Override
    public HashMap<String, Object> speechTask(String fileName) {
        HashMap<String, Object> result = new HashMap<>();
        //2.处理请求
        if (fileName != null) {
            // 获取上传任务ID
            String taskId;
            try {

                // 获得request对象,response对象
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                HttpServletResponse response = attributes.getResponse();
//                    //获取服务器中的路径
//                WebApplicationContext webApplicationContext = ContextLoader
//                        .getCurrentWebApplicationContext();
//                ServletContext servletContext = webApplicationContext
//                        .getServletContext();

                //设置文件路径
                String localFile =request.getServletContext().getRealPath("/upload")+File.separator + fileName;
                //判断文件是否存在
                File file = new File(localFile);
                if(!file.exists()){
                    result.put("flag", false);
                    return result;
                }
                //遍历请求中的cookie，如有存在cookie就直接返回结果
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie:cookies) {
                    if (fileName.equals( cookie.getName() )){
                        result.put("flag", true);
                        result.put("TaskId", cookie.getValue());
                        System.out.println("cookie_taskId= " + cookie.getValue());
                        return result;
                    }
                }

                System.out.println("localFile = " + localFile);
                //设置参数
                HashMap<String, String> params = new HashMap<>();
                params.put("has_participle", "false");
                // 初始化LFASRClient实例
                LfasrClientImp lc = LfasrClientImp.initLfasrClient();
                //上传文件
                taskId = lfasrUpload(lc, localFile, params);
                //打印过程
                System.out.println("taskId = " + taskId);
                if (taskId == null) {
                    result.put("flag", false);
                } else {
                    result.put("flag", true);
                    result.put("TaskId", taskId);
                    response.addCookie(new Cookie(fileName,taskId));
                }

            } catch (Exception e) {
                e.printStackTrace();
                result.put("flag", false);
                return result;
            }


        }
        return result;
    }

    /**
     * 文本查询转写结果
     * @param taskId 查询id
     * @return result 结果集
     */
    @Override
    public HashMap<String, Object> resultsQuery(String taskId) {
        HashMap<String, Object> result = new HashMap<>();
        try {
        //2.处理请求
        // 初始化LFASRClient实例

        LfasrClientImp lc = LfasrClientImp.initLfasrClient();
            //拿到查询结果
            Message message = IfaserGetResult(lc, taskId);
            System.out.println("message = " + message);

            if (message.getOk() == 0) {
                //创建文件
                //获取服务器中的路径
                WebApplicationContext webApplicationContext = ContextLoader
                        .getCurrentWebApplicationContext();
                ServletContext servletContext = webApplicationContext
                        .getServletContext();
                String realPath = servletContext.getRealPath("/result");
                List<Word> words = JSONArray.parseArray(message.getData(), Word.class);
                text_in_file(realPath,taskId,words,result);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" --------------- ");
            result.put("flag", false);
            result.put("msg", "转写异常");
            return result;
        }
        return result;
    }

    /**
     * 字幕文件结果查询
     * @param taskId 查询id
     * @return result 结果集
     */
    @Override
    public HashMap<String, Object> captionResultsQuery(String taskId) {
        //封装结果集
        HashMap<String, Object> result = new HashMap<>();
        //2.处理请求
        // 初始化LFASRClient实例
        try {
            LfasrClientImp lc = LfasrClientImp.initLfasrClient();
            //拿到结果内容
            Message message=IfaserGetResult(lc,taskId);
            System.out.println("message = " + message);
            if(message.getOk()==0){
                //创建文件
                //获取服务器中的路径
                WebApplicationContext webApplicationContext = ContextLoader
                        .getCurrentWebApplicationContext();
                ServletContext servletContext = webApplicationContext
                        .getServletContext();
                String realPath = servletContext.getRealPath("/result");
                List<Word> words = JSONArray.parseArray(message.getData(), Word.class);
                caption_in_file(realPath,taskId,words,result);

            }else {
                result.put("flag",false);
                result.put("msg",message.getFailed());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------------------" );
            result.put("flag",false);
            result.put("msg","转写失败");
        }

        return result;

    }

    /**
     * 字幕文件写入
     * @param realPath  写入路径
     * @param taskId  查询id
     * @param words 拿到的查询结果文本
     * @param result 封装结果
     */
   private void caption_in_file(String realPath,String taskId,List<Word> words,HashMap<String,Object> result){

       try {
           //写入文件
           FileWriter writer = new FileWriter(realPath+ File.separator+taskId+".srt",true);

           //控制变量
           String onebest="";
           int count=1;
           String temp="  ";
           Word tempWord=null;
           for (Word word:words) {

               if (tempWord!=null){
                   word.setBg(tempWord.getBg());
                   word.setOnebest(tempWord.getOnebest()+word.getOnebest());
               }
               //获取内容  过滤语气词
               onebest = word.getOnebest().replaceAll("嗯|啊|吧|嘛|啊|呢", "");
               //空句过滤
               if ("".equals(onebest)||onebest==null|| onebest.length() <= 1){
                   continue;
               }
               //判断整体内容是否小于7个字符串
               if(onebest.length()<7){
                   tempWord=word;
               }
               //
               //获取时间
               int beginTime = word.getBg();
               int time=(word.getEd()-beginTime)/onebest.length();
               //处理内容
               //按标点符号分割字符串
               String[] strings = onebest.split("，|。|！|？");
               //设置临时缓存变量
               String temp2="";
               for (String s: strings) {
                   if (s.length()<=5){
                       //短句放入临时缓存区域
                       temp2+=s;
                   }else if(s.length()<=20){
                       temp+=count+"\r\n";
                       count++;
                       temp2+=s;
                       String bg= TimpStampUtil.processingTimeStamp(beginTime);
                       String ed= TimpStampUtil.processingTimeStamp(beginTime+temp2.length()*time);
                       temp+=bg+" --> "+ed+"\r\n"+temp2+"\r\n\r\n";
                       //更新时间坐标
                       beginTime=beginTime+temp2.length()*time;
                       //清楚零时缓存
                       temp2="";
                   }else {
                       temp+=count+"\r\n";
                       count++;
                       temp2+=s;
                       //截取前20个字符作为一段字幕
                       String bg= TimpStampUtil.processingTimeStamp(beginTime);
                       String ed= TimpStampUtil.processingTimeStamp(beginTime+20*time);
                       temp+=bg+" --> "+ed+"\r\n"+temp2.substring(0,20)+"\r\n\r\n";
                       //更新时间坐标
                       beginTime=beginTime+temp2.length()*time;
                       //截取后面的字符作为一段字幕
                       temp+=count+"\r\n";
                       count++;
                       bg= TimpStampUtil.processingTimeStamp(beginTime);
                       ed= TimpStampUtil.processingTimeStamp(beginTime+temp2.substring(20).length()*time);
                       temp+=bg+" --> "+ed+"\r\n"+temp2.substring(20)+"\r\n\r\n";
                       //更新时间坐标
                       beginTime=beginTime+temp2.substring(20).length()*time;
                       //清楚零时缓存
                       temp2="";
                   }
               }
           }
           writer.write(temp);
           writer.flush();
           writer.close();

           result.put("flag",true);
           result.put("msg","生成成功");
           result.put("result",temp);
           result.put("fileName",taskId+".srt");
       } catch (IOException e) {
           e.printStackTrace();
           System.out.println(" ---------------- " );
           result.put("flag",false);
           result.put("msg","生成异常");
       }

   }
    /**
     * 写入文件
     * @param realPath  写入路径
     * @param taskId  查询id
     * @param words 拿到的查询结果文本
     * @param result 封装结果
     */
    private void text_in_file(String realPath,String taskId,List<Word> words,HashMap<String,Object> result){
        //写入文件
        FileWriter writer;
        try {
            writer = new FileWriter(realPath + File.separator + taskId + ".txt", true);
            //控制变量
            int count = 1;
            String temp = "\t";
            writer.write("   ");
            String onebest = "";

            for (Word word : words) {
                String temp2 = "";
                int number = onebest.length();
                //过滤语气词
                onebest = word.getOnebest().replaceAll("嗯|啊|吧|嘛|啊|呢", "");
                //内容为空则返回
                if ("".equals(onebest) || onebest.length() <= 2) {
                    continue;
                }
                //一句话只要不超过5个字就不算一句，只要不超过十个字就不算一整句
                String[] strings = onebest.split("，|。|！|？");
                for (String s : strings) {
                    if (s.length() <= 5) {
                        temp2 += s;
                    } else if (s.length() <= 10) {
                        temp2 += s + "，";
                    } else {
                        temp2 += s + "。";
                    }
                }

                //写入文件
                writer.write(temp2);
                //客户端显示内容
                temp += temp2;
                //检测结尾符号
                String tailed = temp2.substring(temp2.length() - 1);
                //有结尾标点才结束
                if (onebest.length() > 10 && (tailed.equals("！") || tailed.equals("。") || tailed.equals("？"))) {
                    count++;
                    //换行
                    if (count % 6 == 0) {
                        count = 1;
                        writer.write("\r\n   ");
                        temp += "\r\n\t";
                    }
                }

            }
            writer.flush();
            writer.close();
            result.put("flag", true);
            result.put("msg", "转写成功");
            result.put("result", temp);
            result.put("fileName", taskId + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" --------------- ");
            result.put("flag", false);
            result.put("msg", "转写异常");
        }
    }
    /**
     *  根据订单号查询结果
     * @param lc  处理
     * @param taskId 订单号
     * @return
     */
    private Message IfaserGetResult(LfasrClientImp lc, String taskId) {
        // 获取任务结果
        try {
            Message resultMsg = lc.lfasrGetResult(taskId);
            return resultMsg;
        } catch (LfasrException e) {
            // 获取结果异常处理，解析异常描述信息
            Message resultMsg = JSON.parseObject(e.getMessage(), Message.class);
            System.out.println("ecode=" + resultMsg.getErr_no());
            System.out.println("failed=" + resultMsg.getFailed());
            return resultMsg;
        }
    }


    /**
     * 上传音频
     * @param localFile  本地音频路径及文件
     * @param params      用户可配置参数
     * @return
     */
    public String lfasrUpload(LfasrClientImp lc , String localFile, HashMap<String, String> params){
        String taskId=null;
        try {
            // 上传音频文件
            Message uploadMsg =
                    lc.lfasrUpload(localFile, LfasrType.LFASR_STANDARD_RECORDED_AUDIO, params);

            // 判断返回值
            int ok = uploadMsg.getOk();
            if (ok == 0) {
                // 创建任务成功
                taskId = uploadMsg.getData();
                System.out.println("taskId=" + taskId);
                return taskId;
            } else {
                // 创建任务失败-服务端异常
                System.out.println("ecode=" + uploadMsg.getErr_no());
                System.out.println("failed=" + uploadMsg.getFailed());
                return null;
            }
        } catch (LfasrException e) {
            // 上传异常，解析异常描述信息
            Message uploadMsg = JSON.parseObject(e.getMessage(), Message.class);
            System.out.println("ecode=" + uploadMsg.getErr_no());
            System.out.println("failed=" + uploadMsg.getFailed());
            return null;
        }

    }
}