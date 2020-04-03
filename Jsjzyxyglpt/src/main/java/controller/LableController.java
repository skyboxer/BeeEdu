package controller;

import com.alibaba.fastjson.JSONObject;
import doc.LableDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Lable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lableController")
public class LableController {

    @Autowired
    private LableDoc lableDoc;

    @RequestMapping("/getLable")
    public JSONObject getLable() {
        JSONObject jsonObject = new JSONObject();
        List<Lable> lableList = lableDoc.getLable(new Lable());
        if (lableList.size() > 0) {
            jsonObject.put("code", 0);
            jsonObject.put("data", lableList);
            jsonObject.put("msg", "查询出" + lableList.size() + "条记录");
            return jsonObject;
        }
        jsonObject.put("code", 2001);
        jsonObject.put("msg", "查询出" + lableList.size() + "条记录");
        return jsonObject;
    }

    @RequestMapping("/getPage")
    public void getPage(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String key = request.getParameter("Key");
        String primaryKey =request.getParameter("primaryKey");
        List<Lable> lableList = lableDoc.getLable(new Lable(key,primaryKey));
        if(lableList.size()>0){
            lableList.get(0).toString();
            request.setAttribute("data",lableList.get(0));
        }else {
            request.setAttribute("data","");
        }
        try {
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addLable")
    public JSONObject addLable(String keyName,
                               String primaryKey, String employer, String contractor, String projectName, String contractPrice,
                               String plannedCommencementDate, String plannedCompletionDate, String contractFilingCode,
                               String projectEncoding, String contractCode, String projectManager,String projectManagerCode,String projectSupervisor, String technicalDirector,
                               String securityOfficer, String qualityWorker, String constructors, String constructionUnit,
                               String mediumBidName, String mediumBidPrice, String winningBidPeriod, String bidWinnQualityStandard,
                               String website, String completionStatus, String confirmationDate,String personnel, String licence) {
        JSONObject resultJSON = new JSONObject();
        Lable lable = new Lable(keyName,
                primaryKey, employer, contractor, projectName, contractPrice,
                plannedCommencementDate, plannedCompletionDate, contractFilingCode,
                projectEncoding, contractCode, projectManager,projectManagerCode,projectSupervisor, technicalDirector,
                securityOfficer, qualityWorker, constructors, constructionUnit,
                mediumBidName, mediumBidPrice, winningBidPeriod, bidWinnQualityStandard,
                website, completionStatus, confirmationDate,personnel,
                licence);
        int size = lableDoc.addLable(lable);
        if (size > 0) {
            resultJSON.put("code", 0);
            resultJSON.put("msg", "添加成功！");
            return resultJSON;
        }
        resultJSON.put("code", 2002);
        resultJSON.put("msg", "添加失败");
        return resultJSON;
    }

    @RequestMapping("/delLable")
    public JSONObject delLable(int lableId){
        JSONObject resultJSON = new JSONObject();
        Lable lable = new Lable(lableId);
        int index = lableDoc.delLable(lable);
        if(index > 0) {
            resultJSON.put("code", 0);
            resultJSON.put("msg", "删除成功！");
            return resultJSON;
        }
        resultJSON.put("code", 2005);
        resultJSON.put("msg", "删除失败");
        return resultJSON;
    }

    @RequestMapping("/updateLable")
    public JSONObject updateLable(Integer lableId,String keyName,
                                  String primaryKey, String employer, String contractor, String projectName, String contractPrice,
                                  String plannedCommencementDate, String plannedCompletionDate, String contractFilingCode,
                                  String projectEncoding, String contractCode, String projectManager,String projectManagerCode,String projectSupervisor, String technicalDirector,
                                  String securityOfficer, String qualityWorker, String constructors, String constructionUnit,
                                  String mediumBidName, String mediumBidPrice, String winningBidPeriod, String bidWinnQualityStandard,
                                  String website, String completionStatus, String confirmationDate,String personnel, String licence){
        JSONObject resultJSON = new JSONObject();
        Lable lable = new Lable(lableId,keyName,
                primaryKey, employer, contractor, projectName, contractPrice,
                plannedCommencementDate, plannedCompletionDate, contractFilingCode,
                projectEncoding, contractCode, projectManager,projectManagerCode,projectSupervisor, technicalDirector,
                securityOfficer, qualityWorker, constructors, constructionUnit,
                mediumBidName, mediumBidPrice, winningBidPeriod, bidWinnQualityStandard,
                website, completionStatus, confirmationDate,personnel,
                licence);
        int index = lableDoc.updateLable(lable);
        if(index > 0) {
            resultJSON.put("code", 0);
            resultJSON.put("msg", "修改成功！");
            return resultJSON;
        }
        resultJSON.put("code", 2005);
        resultJSON.put("msg", "修改失败");
        return resultJSON;
    }

}
