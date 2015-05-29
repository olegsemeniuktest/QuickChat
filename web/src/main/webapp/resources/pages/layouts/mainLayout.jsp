<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/resources/pages/common/jsp-header.jsp" %>

<html>
    <head>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
        <title>
            <%if (request.getParameter("title") != null) {%>${title}<%} else {%>Quick Chat<%}%>
        </title>
        <tiles:insertAttribute name="common_styles"/>
    </head>
    <body>
    <tiles:insertAttribute name="common_scripts"/>


    <tiles:insertAttribute name="header"/>


    <tiles:insertAttribute name="body"/>

    </body>
</html>