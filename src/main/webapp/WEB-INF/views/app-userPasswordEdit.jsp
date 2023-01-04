<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Change password</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Zmień hasło</h2>
    <c:if test="${hasErrors}">
        <section class="form--steps" style="width: 100%">
            <div class="form--steps-errorInstructions">
                <div class="form--steps-container" style="text-align: center">
                    <h3>Uwaga!</h3>
                    <p class="active">
                        Nie można zmienić hasła! Spróbuj ponownie.
                    </p>
                </div>
            </div>
        </section>
    </c:if>
    <form:form method="POST" modelAttribute="userChangePassDto">
        <div class="form-group">
            <form:password path="oldPassword" placeholder="Stare hasło"/>
            <div class="errorDiv"><form:errors path="oldPassword" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <form:password path="newPassword" placeholder="Nowe hasło"/>
            <div class="errorDiv"><form:errors path="newPassword" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <form:password path="matchingNewPassword" placeholder="Powtórz nowe hasło"/>
            <div class="errorDiv"><form:errors path="matchingNewPassword" cssClass="errorMsg"/></div>
        </div>
        <form:hidden path="id"/>

        <div class="form-group form-group--buttons">
            <a href='<c:url value="/app/user/profile"/>' class="btn btn--without-border">Odrzuć zmiany</a>
            <button class="btn" type="submit">Zapisz zmiany</button>
        </div>
    </form:form>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
