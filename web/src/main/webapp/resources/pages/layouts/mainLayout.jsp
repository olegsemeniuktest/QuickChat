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

        <table class="mainTable" border="1px" width="1000" align="center">
            <tr style="height: 100px">
                <td colspan="3">
                    <tiles:insertAttribute name="header"/>
                </td>
            </tr>
            <tr>
                <td style="vertical-align: top; width:25%; padding: 10px">
                        <tiles:insertAttribute name="left_part"/>
                </td>
                <td style="vertical-align: top; width:55%; padding: 10px">
                    <tiles:insertAttribute name="body"/>
                </td>
                <td style="vertical-align: top; width:20%; padding: 10px">
                    <tiles:insertAttribute name="right_part"/>
                </td>
            </tr>
        </table>
    </body>
</html>