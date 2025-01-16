<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="BLOG" type="jblog.vo.CategoryVo"--%>

<!doctype html>
<html>
<c:import url="/views/blog/blog-head.jsp"/>
<body>
<div id="container">
    <c:import url="/views/blog/blog-header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/views/blog/blog-admin-menu.jsp">
                <c:param name="currentPage" value="WRITE"/>
            </c:import>
            <%--@elvariable id="postCreateDto" type="jblog.dto.PostCreateDto"--%>
            <form:form modelAttribute="postCreateDto"
                       action="${contextPath}/${BLOG.blogId}/admin/write"
                       method="post">
                <table class="admin-cat-write">
                    <tr>
                        <td class="t">제목</td>
                        <td>
                            <form:input path="title" size="60" name="title" value=""/>
                            <form:select path="categoryId" id="category" name="category"></form:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="t">내용</td>
                        <td>
                            <form:textarea path="content" name="content"></form:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td class="s"><input type="submit" value="포스트하기"></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
    <c:import url="/views/blog/blog-footer.jsp"/>
</div>
</body>
<script src="${contextPath}/~assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    $.ajax({
        url: '${contextPath}/api/${BLOG.blogId}/category/all',
        method: 'GET',
        dataType: 'json',
        success: (res) => {
            console.log(res);
            const $category = $('#category');

            res.forEach(elem => {
                console.log(elem);

                const option = document.createElement('option');
                option.textContent = elem.name;
                option.value = elem.id;

                $category.append(option);
            })
        }
    })
</script>
</html>