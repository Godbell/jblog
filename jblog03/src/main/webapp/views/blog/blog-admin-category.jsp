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
            </table>

            <h4 class="n-c">새로운 카테고리 추가</h4>
            <table id="admin-cat-add">
                <tr>
                    <td class="t">카테고리명</td>
                    <td><input id="category-add-name" type="text" name="name"></td>
                </tr>
                <tr>
                    <td class="t">설명</td>
                    <td><input id="category-add-desc" type="text" name="desc"></td>
                </tr>
                <tr>
                    <td class="s"></td>
                    <td><input type="submit" value="카테고리 추가" onclick="onAdd()"></td>
                </tr>
            </table>
        </div>
    </div>
    <c:import url="/views/blog/blog-footer.jsp"/>
</div>
</body>
<script src="${contextPath}/~assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    function onAdd() {
        const categoryName = $('#category-add-name').val();
        const categoryDescription = $('#category-add-desc').val();

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            url: '${contextPath}/api/${BLOG.blogId}/category',
            method: 'POST',
            data: JSON.stringify({
                name: categoryName,
                description: categoryDescription,
            }),
            success: (res) => {
                console.log('success triggered')
                window.location.replace(window.location.href);
            }
        })
    }

    function onDelete() {
    }

    $.ajax({
        url: '${contextPath}/api/${BLOG.blogId}/category/all',
        method: 'GET',
        dataType: 'json',
        success: (res) => {
            console.log(res);
            const $category = $('.admin-cat');

            res.forEach(elem => {
                console.log(elem);

                const category = document.createElement('tr');

                const indexColumn = document.createElement('td');
                indexColumn.textContent = elem.index;
                category.appendChild(indexColumn);

                const nameColumn = document.createElement('td');
                nameColumn.textContent = elem.name;
                category.appendChild(nameColumn);

                const postCountColumn = document.createElement('td');
                postCountColumn.textContent = elem.postCount;
                category.appendChild(postCountColumn);

                const descriptonColumn = document.createElement('td');
                descriptonColumn.textContent = elem.descripton;
                category.appendChild(descriptonColumn);

                const deleteImageColumn = document.createElement('td');

                if (elem.index !== 1) {
                    const deleteImage = document.createElement('img');
                    deleteImage.src = '${contextPath}/~assets/images/delete.jpg';
                    deleteImageColumn.appendChild(deleteImage);
                }

                category.appendChild(deleteImageColumn);
                $category.append(category);
            })
        }
    })
</script>
</html>