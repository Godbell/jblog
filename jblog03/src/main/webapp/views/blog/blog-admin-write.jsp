<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<c:import url="/views/blog/blog-head.jsp"/>
<body>
<div id="container">
    <c:import url="/views/blog/blog-header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/views/blog/blog-admin-menu.jsp"/>
            <form action="" method="post">
                <table class="admin-cat-write">
                    <tr>
                        <td class="t">제목</td>
                        <td>
                            <input type="text" size="60" name="title">
                            <select name="category">
                                <option>미분류</option>
                                <option>자바</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="t">내용</td>
                        <td><textarea name="content"></textarea></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td class="s"><input type="submit" value="포스트하기"></td>
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