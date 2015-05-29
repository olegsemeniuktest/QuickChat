<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/resources/pages/common/jsp-header.jsp" %>

<div class="body_container container-fluid bordered">
    <div class="row text-center" style=" vertical-align: middle; padding-top: 40px;">
        <div id="slogan_bloc" class="text-center bordered">
            <img id="slogan_image" src='<c:url value="images/slogan_background.png"/>' class="img-rounded"
                 style="width: 800px;">

            <p id="slogan_text" class="text-center bordered">
                <em>Quick entrance, quick communication, quick information!</em>
            </p>
        </div>
    </div>
    <div class="row text-center" style="padding-top: 20px;">
        <div class="bordered">
            <form action='<c:url value="signIn"/>' method="post" class="form_signin text-left" role="form">
                <h2 style="color: #ffffff">Please sign in</h2>
                <input type="text" class="form-control" placeholder="Nickname" name="nickname" required autofocus>
                <input type="password" class="form-control" placeholder="Password" name="password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
    </div>
</div>
