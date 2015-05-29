<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/resources/pages/common/jsp-header.jsp" %>

<div class="header_container container-fluid">
    <div class="row header">
        <div class="col-lg-3 col-lg-offset-1 col-md-2  text-center bordered header_part" style=" padding: 8px">
            <img src='<c:url value="/images/logo.png"/>' class="img-rounded" style="height: 85%; margin-top: 10px;">
        </div>

        <div class="col-lg-5 col-lg-offset-0 col-md-6 col-md-offset-1 text-center header_part bordered">
            <div class="nav navbar-nav header_menu_item">
                <a href='<c:url value="/chats/public"/>' class="header_menu_link">
                    <div>Public chats</div>
                </a>
            </div>
            <div class="nav navbar-nav header_menu_item">
                <a href='<c:url value="/chats/private"/>' class="header_menu_link">
                    <div>Private chats</div>
                </a>
            </div>
            <div class="nav navbar-nav header_menu_item">
                <a href="" class="header_menu_link">
                    <div>Options</div>
                </a>
            </div>
        </div>

        <div class="col-lg-3 col-md-2  header_part bordered">
            <div class="nav navbar-nav header_menu_item">
                <security:authorize access="isAnonymous()">
                    <a href='<c:url value="/signIn"/>' class="header_menu_link">
                        <div>Sign in</div>
                    </a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a href='<c:url value="/logout"/>' class="header_menu_link">
                        <div>Logout</div>
                    </a>
                </security:authorize>
            </div>
            <div class="nav navbar-nav" style="padding-top: 26px;">
                En
            </div>
        </div>

    </div>
</div>
