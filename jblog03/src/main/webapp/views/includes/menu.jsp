<%@ page import="jblog.config.constant.JBlogAttribute" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userAttributeName" value="<%=JBlogAttribute.SIGNED_USER.name()%>"/>

<ul class="menu">
    <c:choose>
        <c:when test="${empty sessionScope[userAttributeName]}">
            <li><a href="${contextPath}/user/signin">로그인</a></li>
            <li><a href="${contextPath}/user/join">회원가입</a></li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${contextPath}/${sessionScope[userAttributeName].id}">
                    내블로그
                </a>
            </li>
            <li><a href="${contextPath}/user/signout">로그아웃</a></li>
        </c:otherwise>
    </c:choose>
</ul>
