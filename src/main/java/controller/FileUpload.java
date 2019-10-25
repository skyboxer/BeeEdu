package controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */

@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传配置
    private static final int MEMORY_THRESHOLD  = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE   = 1024 * 1024 * 1024; // 1024MB
    private static final int MAX_REQUEST_SIZE  = 1024 * 1024 * 1024; // 1024MB

    public FileUpload() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> mapData = new HashMap<>();
        String jsonObject;
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            //out.println("Error: 表单必须包含 enctype=multipart/form-data");
            mapData.put("status",1);
            mapData.put("message","Error: 表单必须包含 enctype=multipart/form-data");
            jsonObject = JSONObject.toJSONString(mapData);
            out.print(jsonObject);
            out.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");


        String uploadPath = request.getServletContext().getRealPath("/upload");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            //TODO 去除上传文件不重复

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        long timeStamp = System.currentTimeMillis();
                        String filePath = uploadPath + File.separator + timeStamp+fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
//            System.out.println(filePath);
                        // 保存文件到硬盘
                        if(storeFile.exists()) {
                            mapData.put("status",1);
                            mapData.put("message","上传失败，文件已存在！");
                            //out.println("上传失败，文件已存在！");
                        }else {
                            item.write(storeFile);
//                            response.sendRedirect("success.html?fileName="+fileName);
                          //  out.println("上传成功！");
                            mapData.put("status",0);
                            mapData.put("message","成功");
                            mapData.put("fileName",timeStamp+fileName);
                        }
                    }
                }
            }else{
                mapData.put("status",1);
                mapData.put("message","上传失败，文件为空！");
            }
        } catch (Exception ex) {
            mapData.put("status",1);
            mapData.put("message","上传失败");
            //response.getWriter().println("文件上传失败!");
        }
        jsonObject = JSONObject.toJSONString(mapData);
        out.print(jsonObject);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}