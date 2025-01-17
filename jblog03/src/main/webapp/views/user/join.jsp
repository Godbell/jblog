<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>
<c:import url="/views/includes/jblog-head.jsp"/>
<body>
<div class="center-content">
    <c:import url="/views/includes/jblog-logo.jsp"/>
    <c:import url="/views/includes/menu.jsp"/>
    <%--@elvariable id="userJoinRequestDto" type="jblog.dto.UserJoinRequestDto"--%>
    <form:form modelAttribute="userJoinRequestDto"
               class="join-form"
               id="join-form"
               method="post"
               action="${contextPath}/user/join"
               onsubmit="onSubmit()">
        <label class="block-label" for="name">이름</label>
        <form:input path="name" id="name" type="text" value=""/>
        <br>
        <form:errors path="name"/>

        <label class="block-label" for="blogInfo-id">아이디</label>
        <form:input path="id" id="blogInfo-id" name="id" type="text"/>
        <input id="btn-checkemail" type="button" value="id 중복체크">
        <img id="img-checkemail" style="display: none;"
             src="${contextPath}/~assets/images/check.png">
        <br>
        <form:errors path="id"/>

        <label class="block-label" for="password">패스워드</label>
        <form:input path="password" id="password" type="password"/>
        <br>
        <form:errors path="password"/>

        <fieldset>
            <legend>약관동의</legend>
            <form:checkbox path="agreedToTerm" id="agree-prov" name="agreeProv"/>
            <label class="l-float">서비스 약관에 동의합니다.</label>
            <br>
            <form:errors path="agreedToTerm"/>
        </fieldset>

        <input type="submit" value="가입하기">
    </form:form>
</div>
</body>
<script src="${contextPath}/~assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    const formStatus = {
        isDupChecked: false,
    }

    function onSubmit() {
        const termCheckBox = document.getElementById('agree-prov');
        return termCheckBox.checked && formStatus.isDupChecked;
    }

    $(function () {
        const $emailCheckButton = $("#btn-checkemail");

        $emailCheckButton.click(function () {
            const $id = $('#blogInfo-id');

            if ($id.val().length === 0) {
                return;
            }

            $.ajax({
                url: `${contextPath}/api/user/id/availability?id=\${$id.val()}`,
                type: 'GET',
                dataType: 'json',
                /**
                 * @param res {{
                 *     error?: string;
                 *     message?: string;
                 * } | {
                 *     availability: boolean;
                 * }}
                 */
                success: function (res) {
                    console.log(res);

                    if (res.error) {
                        console.error(res.message);
                        return;
                    }

                    if (!res.availability) {
                        alert('이미 사용중인 이메일입니다.');
                        $id.val("");
                        $id.focus();
                        return;
                    }

                    formStatus.isDupChecked = true;
                    $('#btn-checkemail').css('display', 'none');
                    $('#img-checkemail').css('display', 'inline');
                },
            });
        })
    });
</script>
</html>
