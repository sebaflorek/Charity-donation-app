<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Register page</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="fragments/header.jsp" %>
    <div class="slogan container container--90" style="justify-content: center">
        <h2 class="white-shadow">
            Witaj <b style="text-transform: capitalize; color: #317f51">${registeredUser}</b> w aplikacji "Oddam w dobre ręce".<br>
            <p>Na podany adres email wysłaliśmy link aktywacyjny. Aktywuj konto, by dokończyć rejestrację.</p>
        </h2>
    </div>
</header>
<%--CONTENT-START--%>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
