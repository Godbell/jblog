<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--@elvariable id="BLOG" type="jblog.vo.BlogVo"--%>

<ul class="admin-menu">
    <c:choose>
        <c:when test='${param.currentPage.equals("BASIC")}'>
            <li class="selected">기본설정</li>
        </c:when>
        <c:otherwise>
            <li><a href="${contextPath}/${BLOG.blogId}/admin/basic">기본설정</a></li>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test='${param.currentPage.equals("CATEGORY")}'>
            <li class="selected">카테고리</li>
        </c:when>
        <c:otherwise>
            <li><a href="${contextPath}/${BLOG.blogId}/admin/category">카테고리</a></li>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test='${param.currentPage.equals("WRITE")}'>
            <li class="selected">글작성</li>
        </c:when>
        <c:otherwise>
            <li><a href="${contextPath}/${BLOG.blogId}/admin/write">글작성</a></li>
        </c:otherwise>
    </c:choose>
</ul>