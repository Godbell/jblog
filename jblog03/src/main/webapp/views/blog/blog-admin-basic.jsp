<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--@elvariable id="BLOG" type="jblog.vo.BlogVo"--%>

<!doctype html>
<html>
<c:import url="/views/blog/blog-head.jsp"/>
<body>
<div id="container">
    <c:import url="/views/blog/blog-header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/views/blog/blog-admin-menu.jsp">
                <c:param name="currentPage" value="BASIC"/>
            </c:import>
            <form action="${contextPath}/${BLOG.blogId}/admin/write"
                  method="post"
                  enctype="multipart/form-data">
                <table class="admin-config">
                    <tr>
                        <td class="t">블로그 제목</td>
                        <td><input type="text" size="40" name="title"></td>
                    </tr>
                    <tr>
                        <td class="t">로고이미지</td>
                    </tr>
                    <tr>
                        <td class="t">&nbsp;</td>
                        <td><img src="${contextPath}/${BLOG.profile}"></td>
                    </tr>
                    <tr>
                        <td class="t">&nbsp;</td>
                        <td><input type="file" name="logo-file"></td>
                    </tr>
                    <tr>
                        <td class="s"><input type="submit" value="기본설정 변경"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="footer">
        <p>
            <strong>Spring 이야기</strong> is powered by JBlog (c)2016
        </p>
    </div>
</div>
</body>
</html>