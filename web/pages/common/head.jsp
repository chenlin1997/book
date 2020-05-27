<%--
  Created by IntelliJ IDEA.
  User: chenl
  Date: 2020/5/26
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() /*协议：http*/
                    +"://"+
                    request.getServerName() /*服务器ip:localhost*/
                    +":"
                    +request.getServerPort()/*端口号 8080*/
                    +request.getContextPath()/*工程路径*/
                    +"/";

    pageContext.setAttribute("basePath",basePath);
%>
<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery.js"></script>