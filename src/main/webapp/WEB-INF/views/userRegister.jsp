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
    <title>Home page</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Załóż konto</h2>
    <c:if test="${hasErrors}">
        <section class="form--steps" style="width: 100%">
            <div class="form--steps-errorInstructions">
                <div class="form--steps-container" style="text-align: center">
                    <h3>Uwaga!</h3>
                    <p class="active">
                        Nie można zarejestrować użytkownika! Uzupełnij poprawnie wymagane pola.
                    </p>
                </div>
            </div>
        </section>
    </c:if>
    <form:form method="POST" modelAttribute="userCreateDto">
        <div class="form-group">
            <form:input path="username" placeholder="Login*"/>
            <div class="errorDiv"><form:errors path="username" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email*"/>
            <div class="errorDiv"><form:errors path="email" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <form:input path="name" placeholder="Imię"/>
        </div>
        <div class="form-group">
            <form:input path="surname" placeholder="Nazwisko"/>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło*"/>
            <div class="errorDiv"><form:errors path="password" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <form:password path="matchingPassword" placeholder="Powtórz hasło*"/>
            <div class="errorDiv"><form:errors path="matchingPassword" cssClass="errorMsg"/></div>
        </div>
        <div class="form-group">
            <p style="font-size: 1.4em">* - Pola obowiązkowe</p>
        </div>


        <div class="form-group form-group--buttons">
            <a href='<c:url value="/login"/>' class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
