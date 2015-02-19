<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ page import="java.util.Properties" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="currentLanguage" value="${pageContext.response.locale.language}"/>