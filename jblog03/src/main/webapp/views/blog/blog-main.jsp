<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% pageContext.setAttribute("newline", "\n"); %>
<%--@elvariable id="BLOG" type="jblog.vo.BlogVo"--%>
<%--@elvariable id="POST" type="jblog.dto.PostResponseDto"--%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${contextPath}/~assets/css/jblog.css">
</head>
<body>
<div id="container">
    <div id="header">
        <h1>Spring 이야기</h1>
        <ul>
            <li><a href="${contextPath}/user/signin">로그인</a></li>
            <li><a href="${contextPath}/user/signout">로그아웃</a></li>
            <li><a href="${contextPath}/${BLOG.blogId}/admin">블로그 관리</a></li>
        </ul>
    </div>
    <div id="wrapper">
        <c:choose>
            <c:when test="${empty POST}">
                <div id="content">
                    글이 없습니다.
                    <a href="${contextPath}/${BLOG.blogId}/write">
                        <button>글쓰기</button>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div id="content">
                    <div class="blog-content">
                        <h4 id="post-title"></h4>
                        <p id="post-content">
                        <p>
                    </div>
                    <ul class="blog-list">
                        <li><a href="">Spring Camp 2016 참여기</a> <span>2015/05/02</span></li>
                        <li><a href="">Spring Boot 사용법 정리</a> <span>2015/05/02</span></li>
                        <li><a href="">Spring Security 설정법</a> <span>2015/05/02</span></li>
                        <li><a href="">JPA + Hinernate</a> <span>2015/05/02</span></li>
                        <li><a href="">AOP 활용하기 - DAO 실행시간 측정하기</a> <span>2015/05/02</span></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div id="extra">
        <div class="blog-logo">
            <img src=${contextPath}/~assets/images/spring-logo.jpg">
        </div>
    </div>

    <div id="navigation">
        <h2>카테고리</h2>
        <ul>
            <li><a href="">닥치고 스프링</a></li>
            <li><a href="">스프링 스터디</a></li>
            <li><a href="">스프링 프로젝트</a></li>
            <li><a href="">기타</a></li>
        </ul>
    </div>

    <div id="footer">
        <p>
            <strong>Spring 이야기</strong> is powered by JBlog (c)2016
        </p>
    </div>
</div>
</body>
<script src="${contextPath}/~assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    <c:if test="${not empty POST}">
    const post = {
        title: `${fn:replace(POST.title, "`", "\\`")}`,
        content: `${fn:replace(POST.content, "`", "\\`")}`
    }

    $(function () {
        $('#post-title').text(post.title);
        $('#post-content').text(post.content);
    })
    </c:if>
</script>
</html>