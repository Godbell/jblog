<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<%--@elvariable id="errors" type="java.lang.Object"--%>
<c:if test="${not empty errors}">
    <script>
        alert('로그인에 실패했습니다. 아이디와 패스워드를 확인해 주세요.');
    </script>
</c:if>
<div class="center-content">
    <h1 class="logo">JBlog</h1>
    <c:import url="/views/includes/menu.jsp"/>
    <%--@elvariable id="signInDto" type="jblog.dto.SignInDto"--%>
    <form:form class="login-form" modelAttribute="signInDto" method="post"
               action="${contextPath}/user/signin">
        <label>아이디</label> <form:input name="id" path="id"/>
        <label>패스워드</label> <form:password name="password" path="password"/>
        <input type="submit" value="로그인">
    </form:form>
</div>
</body>
</html>
