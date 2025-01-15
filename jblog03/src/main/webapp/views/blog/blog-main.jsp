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
<c:import url="/views/blog/blog-head.jsp"/>
<body>
<div id="container">
    <c:import url="/views/blog/blog-header.jsp"/>
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
                    <ul id="blog-list" class="blog-list">
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div id="extra">
        <div class="blog-logo">
            <img src="${contextPath}/${BLOG.profile}">
        </div>
    </div>

    <div id="navigation">
        <h2>카테고리</h2>
        <ul id="category-list">
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

    $(() => {
        $('#post-title').text(post.title);
        $('#post-content').text(post.content);
    })
    </c:if>

    $(() => {
        $.ajax({
            url: '${contextPath}/api/${BLOG.blogId}/${POST.categoryId}',
            method: 'GET',
            dataType: 'json',
            success: (res) => {
                console.log(res);
                res.forEach(elem => {
                    console.log(elem);
                    const listElement = document.createElement('li');

                    const link = document.createElement('a');
                    link.href = '${contextPath}/${BLOG.blogId}/${POST.categoryId}/' + elem.id
                    link.textContent = elem.title;
                    listElement.appendChild(link);

                    const regDate = document.createElement('span');
                    regDate.textContent = elem.regDate;
                    listElement.appendChild(regDate);

                    $('#blog-list').append(listElement);
                })
            }
        })

        $.ajax({
            url: '${contextPath}/api/${BLOG.blogId}/category/all',
            method: 'GET',
            dataType: 'json',
            success: (res) => {
                console.log(res);

                res.forEach(elem => {
                    console.log(elem);
                    const listElement = document.createElement('li');

                    const link = document.createElement('a');
                    link.href = '${contextPath}/${BLOG.blogId}/' + elem.id
                    link.textContent = elem.name;
                    listElement.appendChild(link);

                    $('#category-list').append(listElement);
                })
            }
        })
    })
</script>
</html>