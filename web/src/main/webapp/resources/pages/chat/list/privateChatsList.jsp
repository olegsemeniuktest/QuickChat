<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/resources/pages/common/jsp-header.jsp" %>

<div class="body_container container-fluid bordered">
    <div class="row " style=" padding-top: 40px; ">
        <div class=" col-sm-offset-2 col-sm-6  col-lg-offset-3 col-lg-4 bordered">
            <h2 style="color: #ffffff">Private chats</h2>
        </div>
        <div class=" col-sm-2  col-lg-2 bordered text-right" style="padding-top: 20px; padding-bottom: 5px;">
            <a href="google.com" class="btn btn-primary btn-sm">New chat</a>
        </div>
    </div>
    <%@ include file="chatsList.jsp" %>
</div>
