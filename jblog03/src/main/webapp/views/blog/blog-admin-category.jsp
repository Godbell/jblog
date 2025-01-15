<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<c:import url="/views/blog/blog-head.jsp"/>
<body>
<div id="container">
    <c:import url="/views/blog/blog-header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/views/blog/blog-admin-menu.jsp">
                <c:param name="currentPage" value="CATEGORY"/>
            </c:import>
            <table class="admin-cat">
                <tr>
                    <th>번호</th>
                    <th>카테고리명</th>
                    <th>포스트 수</th>
                    <th>설명</th>
                    <th>삭제</th>
                </tr>
                <tr>
                    <td>3</td>
                    <td>미분류</td>
                    <td>10</td>
                    <td>카테고리를 지정하지 않은 경우</td>
                    <td><img src=${pageContext.request.contextPath}"/assets/images/delete.jpg"></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>스프링 스터디</td>
                    <td>20</td>
                    <td>어쩌구 저쩌구</td>
                    <td><img src=${pageContext.request.contextPath}"/assets/images/delete.jpg"></td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>스프링 프로젝트</td>
                    <td>15</td>
                    <td>어쩌구 저쩌구</td>
                    <td><img src=${pageContext.request.contextPath}"/assets/images/delete.jpg"></td>
                </tr>
            </table>

            <h4 class="n-c">새로운 카테고리 추가</h4>
            <table id="admin-cat-add">
                <tr>
                    <td class="t">카테고리명</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td class="t">설명</td>
                    <td><input type="text" name="desc"></td>
                </tr>
                <tr>
                    <td class="s">&nbsp;</td>
                    <td><input type="submit" value="카테고리 추가"></td>
                </tr>
            </table>
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