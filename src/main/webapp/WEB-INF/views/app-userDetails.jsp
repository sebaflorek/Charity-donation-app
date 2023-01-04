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
    <title>User details</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Profil</h2>
    <div class="user-details">
        <p>Login: <b style="text-transform: capitalize">${userReadDto.username}</b></p>
        <p>Imię: <b style="text-transform: capitalize">${userReadDto.name}</b></p>
        <p>Nazwisko: <b style="text-transform: capitalize">${userReadDto.surname}</b></p>
        <p>Email: <b>${userReadDto.email}</b></p>
    </div>

    <div class="form-group form-group--buttons">
        <a href='<c:url value="/app/user/edit"/>' class="btn btn--without-border">Edytuj profil</a>
        <a href='<c:url value="/app/user/change-password"/>' class="btn btn--without-border">Zmień hasło</a>
    </div>
    <div class="form-group form-group--buttons">
        <a href='<c:url value="/app/user/delete"/>'
           onclick="return confirm('Czy na pewno chcesz trwale usunąć swoje konto oraz jego zawartość? Operacja jest nieodwracalna!')"
           class="btn btn--without-border">Usuń profil</a>
    </div>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
