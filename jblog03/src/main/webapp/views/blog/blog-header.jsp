<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userAttributeName" value="<%=jblog.config.constant.JBlogAttribute.SIGNED_USER.name()%>"/>
<%--@elvariable id="BLOG" type="jblog.vo.BlogVo"--%>

<div id="header">
    <h1>${BLOG.title}</h1>
    <ul>
        <c:choose>
            <c:when test="${empty sessionScope[userAttributeName]}">
                <li><a href="${contextPath}/user/signin">로그인</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${contextPath}/user/signout">로그아웃</a></li>
                <c:if test="${BLOG.blogId.equals(sessionScope[userAttributeName].id)}">
                    <li><a href="${contextPath}/${BLOG.blogId}/admin">블로그 관리</a></li>
                </c:if>
            </c:otherwise>
        </c:choose>
    </ul>
</div>