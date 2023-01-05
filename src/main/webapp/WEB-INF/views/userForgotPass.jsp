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
    <title>Forgot password</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Podaj adres email, na który zarejestrowałeś konto</h2>
    <c:if test="${hasErrors}">
        <section class="form--steps" style="width: 100%">
            <div class="form--steps-errorInstructions">
                <div class="form--steps-container" style="text-align: center">
                    <h3>Uwaga!</h3>
                    <p class="active">
                        Nie można zresetować hasła! Spróbuj ponownie.
                    </p>
                </div>
            </div>
        </section>
    </c:if>
    <form:form method="POST" modelAttribute="userForgotPassDto">
        <div class="form-group">
            <form:input path="email" placeholder="Email"/>
            <div class="errorDiv"><form:errors path="email" cssClass="errorMsg"/></div>
        </div>

        <div class="form-group form-group--buttons">
            <a href='<c:url value="/login"/>' class="btn btn--without-border">Wróć</a>
            <button class="btn" type="submit">Zresetuj hasło</button>
        </div>
    </form:form>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
