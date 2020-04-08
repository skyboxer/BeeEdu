<%@ page import="pojo.Lable" %>
<%@ page pageEncoding="utf-8" isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <meta name=Generator content="Microsoft Word 11 (filtered)">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no"/>

    <title>施工合同验证</title>
    <link type="text/css" href="/Jsjzyxyglpt/css.css" rel="stylesheet"/>
</head>

<body lang=ZH-CN style='text-justify-trim:punctuation' background='/Jsjzyxyglpt/faces/htba/shuiyin.png'>
<form id="form" action="">
    <input id="form:action" type="hidden" name="form:action"/>

    <div class=Section1 style='layout-grid:15.6pt'>
        <c:if test="${empty requestScope.data}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:red'>合同内容已发生过修改，非最新合同! </span></b></p>
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>人员未变更</span></b></p>
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>无分包信息</span></b></p>
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>未见施工许可证</span></b></p>
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>三、竣工情况:</span></b></p>
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>在线工程交工申报已确认，确认日期:2017-08-10</span></b></p>
        </c:if>


        <c:if test="${not empty requestScope.data}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>一、合同基本信息</span></b></p>
        <c:if test="${not empty requestScope.data.getEmployer()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>发包人：<u>${requestScope.data.getEmployer()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getContractor()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>承包人：<u>${requestScope.data.getContractor()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getProjectName()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>工程名称：<u>${requestScope.data.getProjectName()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getContractPrice()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
        font-family:仿宋;color:black'>合同价：<u>${requestScope.data.getContractPrice()}</u>元</span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getPlannedCommencementDate()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>计划开工日期：<u>${requestScope.data.getPlannedCommencementDate()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getPlannedCompletionDate()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>计划竣工日期：<u>${requestScope.data.getPlannedCompletionDate()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getContractFilingCode()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
        font-family:仿宋;color:black'>合同备案码：<u>${requestScope.data.getContractFilingCode()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getProjectEncoding()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
        font-family:仿宋;color:black'>项目编码：<u>${requestScope.data.getProjectEncoding()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getContractCode()}">
            <p class=MsoNormal><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>合同验证码:<br>&nbsp;&nbsp;&nbsp;&nbsp;<u>${requestScope.data.getContractCode()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getProjectManager()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>项目经理:<u>${requestScope.data.getProjectManager()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getProjectSupervisor()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>项目主管:<u>${requestScope.data.getProjectSupervisor()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getTechnicalDirector()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>技术负责人:<u>${requestScope.data.getTechnicalDirector()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getSecurityOfficer()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>安全员:<u>${requestScope.data.getSecurityOfficer()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getQualityWorker()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>质量员:<u>${requestScope.data.getQualityWorker()}</u></span></b></p>
        </c:if>
        <c:if test="${not empty requestScope.data.getConstructors()}">
            <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
        font-family:仿宋;color:black'>施工员:<u>${requestScope.data.getConstructors()}</u></span></b></p>
        </c:if>
        <table style='background-image:url(/Jsjzyxyglpt/faces/htba/photoLibrary/select.jpg);background-repeat:no-repeat;'
               border=0>
            <tr>
                <td align=right>
                    <p class=MsoNormal style='margin-left:45.0pt;line-height:30.0pt'><b><span
                            style='font-size:13.0pt;font-family:仿宋;color:black'>合同验证通过</span></b></p>
                </td>
            </tr>
        </table>
    </div>
    <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>岗位人员身份证信息:</span></b></p>
    <c:if test="${not empty requestScope.data.getProjectManager()}">
        <p class=MsoNormal style='line-height:10.0pt;'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>项目经理:<u>${requestScope.data.getProjectManager()}
        <c:if test="${not empty requestScope.data.getProjectManagerCode()}">
            ，${requestScope.data.getProjectManagerCode()}
        </c:if>
        </u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getTechnicalDirector()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>技术负责人:<u>${requestScope.data.getTechnicalDirector()}</u><br/><img onerror="this.style.display='none';" src=''></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getSecurityOfficer()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>安全员:<u>${requestScope.data.getSecurityOfficer()}</u><br/><img onerror="this.style.display='none';" src=''></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getPersonnel()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>${requestScope.data.getPersonnel()}</span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getLicence()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;font-family:仿宋;color:black'>${requestScope.data.getLicence()}</span></b></p>
    </c:if>
        <%--<c:if test="${not empty requestScope.data.getProjectName()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>工程名称:<u>${requestScope.data.getProjectName()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getConstructionUnit()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>建设单位:<u>${requestScope.data.getConstructionUnit()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getMediumBidName()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>中标人名称:<u>${requestScope.data.getMediumBidName()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getMediumBidPrice()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>中标价:<u>${requestScope.data.getMediumBidPrice()}元</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getWinningBidPeriod()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>中标工期:<u>${requestScope.data.getWinningBidPeriod()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getBidWinnQualityStandard()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>中标质量标准:<u>${requestScope.data.getBidWinnQualityStandard()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getProjectManager()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>中标项目负责人:<u>${requestScope.data.getProjectManager()}</u></span></b></p>
    </c:if>--%>
    <c:if test="${not empty requestScope.data.getWebsite()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>网址:<u>${requestScope.data.getWebsite()}</u></span></b></p>
    </c:if>
    <c:if test="${not empty requestScope.data.getCompletionStatus()}">
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt;
font-family:仿宋;color:black'>三、竣工情况:</span></b></p>
        <p class=MsoNormal style='line-height:10.0pt'><b><span style='font-size:13.0pt; font-family:仿宋;color:black'>
        ${requestScope.data.getCompletionStatus()}
        <c:if test="${not empty requestScope.data.getConfirmationDate()}">
            ，确认日期:<u>${requestScope.data.getConfirmationDate()}
        </c:if>
            </u></span></b></p>
    </c:if>
    </c:if>
    <input type="hidden" name="com.sun.faces.VIEW"
           value="rO0ABXNyACBjb20uc3VuLmZhY2VzLnV0aWwuVHJlZVN0cnVjdHVyZRRmG0QclWAgAgAETAAIY2hpbGRyZW50ABVMamF2YS91dGlsL0FycmF5TGlzdDtMAAljbGFzc05hbWV0ABJMamF2YS9sYW5nL1N0cmluZztMAAZmYWNldHN0ABNMamF2YS91dGlsL0hhc2hNYXA7TAACaWRxAH4AAnhwc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAF3BAAAAAFzcQB+AABzcQB+AAUAAAACdwQAAAACc3EAfgAAcHQAKmphdmF4LmZhY2VzLmNvbXBvbmVudC5odG1sLkh0bWxJbnB1dEhpZGRlbnB0AAZhY3Rpb25zcQB+AABwdAApamF2YXguZmFjZXMuY29tcG9uZW50Lmh0bWwuSHRtbE91dHB1dFRleHRwdAAEX2lkMHh0ACNqYXZheC5mYWNlcy5jb21wb25lbnQuaHRtbC5IdG1sRm9ybXB0AARmb3JteHQAKG9yZy5hamF4NGpzZi5mcmFtZXdvcmsuYWpheC5BamF4Vmlld1Jvb3RwdAAJX3ZpZXdSb290dXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAAAnVxAH4AEwAAAAJ1cQB+ABMAAAAEdXEAfgATAAAACHNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAkamF2YXguZmFjZXMud2ViYXBwLkNVUlJFTlRfVklFV19ST09UcQB+ABp4cHBxAH4AEnNyABFqYXZhLmxhbmcuQm9vbGVhbs0gcoDVnPruAgABWgAFdmFsdWV4cAFzcQB+ABsAcHB0AApIVE1MX0JBU0lDdAAKL2NoZWNrLmpzcHNyABBqYXZhLnV0aWwuTG9jYWxlfvgRYJww+ewDAAZJAAhoYXNoY29kZUwAB2NvdW50cnlxAH4AAkwACmV4dGVuc2lvbnNxAH4AAkwACGxhbmd1YWdlcQB+AAJMAAZzY3JpcHRxAH4AAkwAB3ZhcmlhbnRxAH4AAnhw/////3QAAkNOdAAAdAACemhxAH4AI3EAfgAjeHVxAH4AEwAAAAVxAH4AHXEAfgAdcHEAfgAdcQB+AB11cQB+ABMAAAABdXEAfgATAAAAAnVxAH4AEwAAABZ1cQB+ABMAAAAIc3EAfgAYP0AAAAAAAAx3CAAAABAAAAABdAAgamF2YXguZmFjZXMud2ViYXBwLkNPTVBPTkVOVF9JRFNzcQB+AAUAAAACdwQAAAACcQB+AAtxAH4ADnh4cHQABGZvcm1xAH4AEHEAfgAccQB+AB10ABBqYXZheC5mYWNlcy5Gb3JtcHBwcHQAIWFwcGxpY2F0aW9uL3gtd3d3LWZvcm0tdXJsZW5jb2RlZHBwcHBwcHBwcHBwcHBwcHBwdXEAfgATAAAAAnVxAH4AEwAAAAJ1cQB+ABMAAAABdXEAfgATAAAACnVxAH4AEwAAAAN1cQB+ABMAAAAIc3EAfgAYP0AAAAAAAAx3CAAAABAAAAAAeHVxAH4AEwAAAAJ1cgATW0xqYXZhLmxhbmcuU3RyaW5nO63SVufpHXtHAgAAeHAAAAABdAAFdmFsdWV1cQB+ABMAAAABc3IAJmphdmF4LmZhY2VzLmNvbXBvbmVudC5TdGF0ZUhvbGRlclNhdmVyWcqzPZOczU0CAAJMAAljbGFzc05hbWVxAH4AAkwACnNhdmVkU3RhdGV0ABJMamF2YS9sYW5nL09iamVjdDt4cHQAIWNvbS5zdW4uZmFjZXMuZWwuVmFsdWVCaW5kaW5nSW1wbHQAFkh0YmFCZWFuLnNjcmlwdC5hY3Rpb250AAtmb3JtOmFjdGlvbnEAfgALcQB+ABxxAH4AHXQAEmphdmF4LmZhY2VzLkhpZGRlbnBwcHEAfgAdcQB+AB1xAH4AHXEAfgAccQB+AB1xAH4AHXBwcHVxAH4AEwAAAAB1cQB+ABMAAAACdXEAfgATAAAABnVxAH4AEwAAAAN1cQB+ABMAAAAIc3EAfgAYP0AAAAAAAAx3CAAAABAAAAAAeHVxAH4AEwAAAAJ1cQB+ADgAAAABcQB+ADp1cQB+ABMAAAABc3EAfgA8cQB+AD90ABVIdGJhQmVhbi5lZGl0QmVhbi5odGp0AAlmb3JtOl9pZDBxAH4ADnEAfgAccQB+AB10ABBqYXZheC5mYWNlcy5UZXh0cHNxAH4APHQAI2phdmF4LmZhY2VzLmNvbnZlcnQuTnVtYmVyQ29udmVydGVydXEAfgATAAAAD3BwcQB+ABxxAH4AHXNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAAAcQB+AB1zcQB+AFMAAAAAcQB+AB1zcQB+AFMAAAAAcQB+AB1zcQB+AFMAAAAAcQB+AB1wdAAIIywjIzAuMDB0AAZudW1iZXJwcQB+ABxxAH4AHXBwcHVxAH4AEwAAAAA="/>
    <input type="hidden" name="form" value="form"/></form>
</body>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

</html>