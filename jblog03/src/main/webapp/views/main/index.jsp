<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div class="center-content">
    <h1 class="logo">JBlog</h1>
    <c:import url="/views/includes/menu.jsp"/>
    <form class="search-form">
        <fieldset>
            <input type="text" name="keyword"/>
            <input type="submit" value="검색"/>
        </fieldset>
        <fieldset>
            <input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
            <input type="radio" name="which" value="tag"> <label>태그</label>
            <input type="radio" name="which" value="blog-user"> <label>블로거</label>
        </fieldset>
    </form>
</div>
</body>
</html>