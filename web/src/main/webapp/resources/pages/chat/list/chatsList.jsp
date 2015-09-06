<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/resources/pages/common/jsp-header.jsp" %>

<div class="row ">
    <div class=" col-sm-offset-2 col-sm-8  col-lg-offset-3 col-lg-6 ">
        <c:if test="${not empty chatList}">
            <div class="chat_list_container">   <!-- chat_list_container -->
                <c:forEach items="${chatList}" var="chat">
                    <a href="#" class="undecorated_link">
                        <div class="chat_list_item">${chat.name}</div>
                    </a>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>