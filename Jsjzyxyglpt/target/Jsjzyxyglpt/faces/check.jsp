<%--
  Created by IntelliJ IDEA.
  User: cnxjk
  Date: 2020/3/12
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>施工合同验证</title>
</head>
<body>
<%
    String key = request.getParameter("Key");
    if ("533f095d-2508-44a5-b9d1-bdb02c930aa1".equals(key)) {
        request.getRequestDispatcher("/index1.jsp").forward(request, response);
    } else if ("533f095d-2508-44a5-b9d1-bdb02c930aa2".equals(key)) {
        request.getRequestDispatcher("/index2.jsp").forward(request, response);
    } else if ("b487a7dd-861c-4d49-89b8-7492456a9aa3".equals(key)){
        request.getRequestDispatcher("/index3.jsp").forward(request, response);
    } else if ("b487a7dd-861c-4d49-89b8-7492456a9aa4".equals(key)){
        request.getRequestDispatcher("/index4.jsp").forward(request, response);
    } else if ("b487a7dd-861c-4d49-89b8-7492456a9aa5".equals(key)){
        request.getRequestDispatcher("/index5.jsp").forward(request, response);
    }
%>
</body>
</html>
