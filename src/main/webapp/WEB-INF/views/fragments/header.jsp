<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<head>--%>
<%--    <meta charset="UTF-8"/>--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>--%>
<%--    <title>Header</title>--%>
<%--    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>--%>
<%--</head>--%>
<%--<header class="header--main-page">--%>
<nav class="container container--70">
    <ul class="nav--actions">
        <sec:authorize access="!isAuthenticated()">
            <li><a href='<c:url value="/login"/>' class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href='<c:url value="/"/>' class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="logged-user">
                Witaj <b style="text-transform: capitalize"><sec:authentication property="principal.username"/></b>
                <ul class="dropdown">
                    <li><a href='<c:url value="/"/>'>Profil</a></li>
                    <li><a href='<c:url value="/"/>'>Moje zbiórki</a></li>
                    <li><a href='<c:url value="/logout"/>'>Wyloguj</a></li>
                </ul>
            </li>
        </sec:authorize>
    </ul>

    <ul>
        <li><a href='<c:url value="/"/>' class="btn btn--without-border active">Start</a></li>
        <li><a href='<c:url value="/#steps"/>' class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href='<c:url value="/#about-us"/>' class="btn btn--without-border">O nas</a></li>
        <li><a href='<c:url value="/#help"/>' class="btn btn--without-border">Fundacje i organizacje</a></li>
        <sec:authorize access="isAuthenticated()">
            <li><a href='<c:url value="/app/donation/form"/>' class="btn btn--without-border">Przekaż dary</a></li>
        </sec:authorize>
        <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>

<%--    <div class="slogan container container--90">--%>
<%--        <div class="slogan--item">--%>
<%--            <h1>--%>
<%--                Zacznij pomagać!<br/>--%>
<%--                Oddaj niechciane rzeczy w zaufane ręce--%>
<%--            </h1>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</header>--%>
