<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<div class="center-content">
    <h1 class="logo">JBlog</h1>
    <ul class="menu">
        <li><a href="">로그인</a></li>
        <li><a href="">회원가입</a></li>
        <li><a href="">로그아웃</a></li>
        <li><a href="">내블로그</a></li>
    </ul>
    <form class="login-form">
        <label>아이디</label> <input type="text" name="id">
        <label>패스워드</label> <input type="text" name="password">
        <input type="submit" value="로그인">
    </form>
</div>
</body>
</html>
