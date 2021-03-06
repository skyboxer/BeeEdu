package com.enablue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iflytek.msp.cpdb.lfasr.client.LfasrClientImp;
import com.iflytek.msp.cpdb.lfasr.exception.LfasrException;
import com.iflytek.msp.cpdb.lfasr.model.LfasrType;
import com.iflytek.msp.cpdb.lfasr.model.Message;
import com.iflytek.msp.cpdb.lfasr.model.ProgressStatus;
import com.enablue.pojo.Word;
import com.enablue.util.TimpStampUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 科大讯飞ASR
 */
@WebServlet("/ifasrServlet")
public class IfasrServlet extends BaseServlet {

    /**
     * 创建语音识别任务
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void speechTask(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1.接受请求
        //处理post请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
        //获取文件名
        String fileName = request.getParameter("fileName");
        //2.处理请求
        if(fileName!=null){
            // 获取上传任务ID
            String task_id = null;
            HashMap<String, Object> result = new HashMap<>();
            //设置文件路径
            String local_file = request.getServletContext().getRealPath("/upload")+File.separator+fileName;
            System.out.println("local_file = " + local_file);
            //设置参数
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("has_participle", "false");
            // 初始化LFASRClient实例
            LfasrClientImp lc = LfasrClientImp.initLfasrClient();
            //上传文件
            task_id = lfasrUpload(lc,local_file, params);
            //打印过程
            System.out.println("task_id = " + task_id);
            if(task_id==null){
                result.put("flag",false);
            }else {
                result.put("flag",true);
                result.put("TaskId",task_id);
            }

            //3.响应数据
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
        }


    }

    /**
     * 查询订单状态
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void queryOrderStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, LfasrException {

        //1.接受请求
        //处理post请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
        String task_id = request.getParameter("taskid");
        HashMap<String, Object> result = new HashMap<>();

        //2.处理请求
        // 初始化LFASRClient实例
        LfasrClientImp lc = LfasrClientImp.initLfasrClient();
        //获取
        result.put("status",lfasrGetProgress(lc,task_id));

        //3.响应数据
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");

        response.getWriter().println(JSON.toJSONString(result));


    }

    /**
     * 文本结果查询
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws LfasrException
     */
    public void resultsQuery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, LfasrException {

        //1.接受请求
        //处理post请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
        String task_id = request.getParameter("taskid");
        HashMap<String, Object> result = new HashMap<>();

        //2.处理请求
        // 初始化LFASRClient实例
        LfasrClientImp lc = LfasrClientImp.initLfasrClient();
        Message message=IfaserGetResult(lc,task_id);
        System.out.println("message = " + message);
        if(message.getOk()==0){
            //创建文件
            String realPath = getServletContext().getRealPath("/result");
            List<Word> words = JSONArray.parseArray(message.getData(), Word.class);
            //写入文件
            FileWriter writer;
            try {
                writer = new FileWriter(realPath+ File.separator+task_id+".txt",true);
                //控制变量
                int count=1;
                String temp="\t";
                writer.write("   ");
                String onebest="";

                for (Word word:words) {
                    String temp2="";
                    int number=onebest.length();
                    //过滤语气词
                     onebest = word.getOnebest().replaceAll("嗯|啊|吧|嘛|啊|呢", "");
                     //内容为空则返回
                    if ("".equals(onebest)||onebest==null || onebest.length()<=2){
                        continue;
                    }
                    //一句话只要不超过5个字就不算一句，只要不超过十个字就不算一整句
                    String[] strings = onebest.split("，|。|！|？");
                    for (String s: strings) {
                        if (s.length()<=5){
                            temp2+=s;
                        }else if(s.length()<=10){
                            temp2+=s+"，";
                        }else {
                            temp2+=s+"。";
                        }
                    }

                    //写入文件
                    writer.write(temp2);
                    //客户端显示内容
                    temp+=temp2;
                    //检测结尾符号
                    String tailed = temp2.substring(temp2.length()-1);
                    //有结尾标点才结束
                    if(onebest.length()> 10 && (tailed.equals("！")||tailed.equals("。")||tailed.equals("？"))){
                        count++;
                        //换行
                        if(count%6==0){
                            count=1;
                            writer.write("\r\n   ");
                            temp+="\r\n\t";
                        }
                    }

                }
                writer.flush();
                writer.close();

                result.put("flag",true);
                result.put("msg","转写成功");
                result.put("result",temp);
                result.put("fileName",task_id+".txt");
            } catch (IOException e) {
                e.printStackTrace();
                result.put("flag",false);
                result.put("msg","转写异常");
            }

            //3.响应数据
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().println(JSON.toJSONString(result));

        }else {
            //3.响应数据
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");
            result.put("flag",false);
            result.put("msg",message.getFailed());
            response.getWriter().println(JSON.toJSONString(result));
        }

    }
    /**
     * 字幕结果查询
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws LfasrException
     */
    public void captionResultsQuery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, LfasrException {

        //1.接受请求
        //处理post请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
        String task_id = request.getParameter("taskid");
        HashMap<String, Object> result = new HashMap<>();

        //2.处理请求
        // 初始化LFASRClient实例
        LfasrClientImp lc = LfasrClientImp.initLfasrClient();
        Message message=IfaserGetResult(lc,task_id);
        System.out.println("message = " + message);
        if(message.getOk()==0){
            //创建文件
            String realPath = getServletContext().getRealPath("/result");
            List<Word> words = JSONArray.parseArray(message.getData(), Word.class);
            //写入文件
            FileWriter writer;
            try {
                writer = new FileWriter(realPath+ File.separator+task_id+".srt",true);

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
//                    if ("。".equals(temp.substring(temp.length()-1))){
//                        temp+="\r\n";
//                        temp+=hour+":"+minute+":"+sec+"     "+temp2;
//                    }else {
//                        temp+=temp2;
//                    }
                }
                writer.write(temp);
                writer.flush();
                writer.close();

                result.put("flag",true);
                result.put("msg","生成成功");
                result.put("result",temp);
                result.put("fileName",task_id+".srt");
            } catch (IOException e) {
                e.printStackTrace();
                result.put("flag",false);
                result.put("msg","生成异常");
            }

            //3.响应数据
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().println(JSON.toJSONString(result));

        }else {
            //3.响应数据
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");
            result.put("flag",false);
            result.put("msg",message.getFailed());
            response.getWriter().println(JSON.toJSONString(result));
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
     * 查询转写状态
     * @param task_id
     */
    public int lfasrGetProgress(LfasrClientImp lc, String task_id){
        try {
            // 获取处理进度
            Message progressMsg = lc.lfasrGetProgress(task_id);

            // 如果返回状态不等于0，则任务失败
            if (progressMsg.getOk() != 0) {
                System.out.println("task was fail. task_id:" + task_id);
                System.out.println("ecode=" + progressMsg.getErr_no());
                System.out.println("failed=" + progressMsg.getFailed());
                return -1;

            } else {
                ProgressStatus progressStatus = JSON.parseObject(progressMsg.getData(), ProgressStatus.class);
                if (progressStatus.getStatus() == 9) {
                    return 2;
                } else {
                    return  1;
                }
            }
        } catch (LfasrException e) {
            // 获取进度异常处理，根据返回信息排查问题后，再次进行获取
            Message progressMsg = JSON.parseObject(e.getMessage(), Message.class);
            System.out.println("ecode=" + progressMsg.getErr_no());
            System.out.println("failed=" + progressMsg.getFailed());
            return 3;

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

