package com.enablue.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.enablue.controller.IfasrServlet;
import com.enablue.pojo.Word;
import com.enablue.service.IfasrService;
import com.iflytek.msp.cpdb.lfasr.client.LfasrClientImp;
import com.iflytek.msp.cpdb.lfasr.exception.LfasrException;
import com.iflytek.msp.cpdb.lfasr.model.LfasrType;
import com.iflytek.msp.cpdb.lfasr.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 科大讯飞服务实现类
 *   王成
 *  2019.12.05 15.44
 */
@Service
public class IfasrServiceImpl implements IfasrService {
    /**
     * 创建语音转写任务
     * @param fileName 文件名
     * @return
     */
    @Override
    public HashMap<String, Object> speechTask(String fileName) {
        HashMap<String, Object> result = new HashMap<>();
        //2.处理请求
        if (fileName != null) {
            // 获取上传任务ID
            String task_id = null;
            //获取服务器中的路径
            WebApplicationContext webApplicationContext = ContextLoader
                    .getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext
                    .getServletContext();

            //设置文件路径
            String local_file = servletContext.getRealPath("/upload")+File.separator + fileName;
            System.out.println("local_file = " + local_file);
            //设置参数
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("has_participle", "false");
            // 初始化LFASRClient实例

            LfasrClientImp lc = null;
            try {
                lc = LfasrClientImp.initLfasrClient();
            } catch (LfasrException e) {
                e.printStackTrace();
                result.put("flag", false);
                return result;
            }
            //上传文件
            task_id = lfasrUpload(lc, local_file, params);
            //打印过程
            System.out.println("task_id = " + task_id);
            if (task_id == null) {
                result.put("flag", false);
            } else {
                result.put("flag", true);
                result.put("TaskId", task_id);
            }
        }
        return result;
    }

    /**
     * 查询转写结果
     * @param taskid 查询id
     * @return
     */
    @Override
    public HashMap<String, Object> resultsQuery(String taskid) {
        HashMap<String, Object> result = new HashMap<>();

        //2.处理请求
        // 初始化LFASRClient实例

        LfasrClientImp lc = null;
        try {
            lc = LfasrClientImp.initLfasrClient();
        } catch (LfasrException e) {
            e.printStackTrace();
            System.out.println(" 初始化失败 " );
            result.put("flag", false);
            result.put("msg", "转写异常");
            return result;
        }

        //拿到查询结果
        Message message = IfaserGetResult(lc, taskid);
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
            in_file(realPath,taskid,words,result);

        }
        return result;
    }

    /**
     * 写入文件
     * @param realPath  写入路径
     * @param taskid  查询id
     * @param words 拿到的查询结果文本
     * @param result 封装结果
     */
    private void in_file(String realPath,String taskid,List<Word> words,HashMap<String,Object> result){
        //写入文件
        FileWriter writer;
        try {
            writer = new FileWriter(realPath + File.separator + taskid + ".txt", true);
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
                if ("".equals(onebest) || onebest == null || onebest.length() <= 2) {
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
            result.put("fileName", taskid + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" 写入异常 ");
            result.put("flag", false);
            result.put("msg", "转写异常");
        }
    }
    /**
     *  根据订单号查询结果
     * @param lc
     * @param task_id
     * @return
     */
    private Message IfaserGetResult(LfasrClientImp lc, String task_id) {
        // 获取任务结果
        try {
            Message resultMsg = lc.lfasrGetResult(task_id);
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
     * @param local_file  本地音频路径及文件
     * @param params      用户可配置参数
     * @return
     */
    public String lfasrUpload(LfasrClientImp lc , String local_file, HashMap<String, String> params){
        String task_id=null;
        try {
            // 上传音频文件
            Message uploadMsg =
                    lc.lfasrUpload(local_file, LfasrType.LFASR_STANDARD_RECORDED_AUDIO, params);

            // 判断返回值
            int ok = uploadMsg.getOk();
            if (ok == 0) {
                // 创建任务成功
                task_id = uploadMsg.getData();
                System.out.println("task_id=" + task_id);
                return task_id;
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
