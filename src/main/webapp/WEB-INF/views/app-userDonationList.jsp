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
    <title>Donation List</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Moje zbiórki</h2>
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

    <table class="sortable">
        <thead>
        <tr>
            <th>Id</th>
            <th>Kategorie</th>
            <th>Torby</th>
            <th>Instytucja</th>
            <th>Utworzono</th>
            <th>Status</th>
            <th>Data odbioru</th>
            <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="donation" items="${donationList}">
            <tr>
                <td>${donation.id}</td>
                <td>
                    <c:forEach varStatus="loopStatus" var="category" items="${donation.categories}">
                        ${category.name}<c:if test="${!loopStatus.last}">,</c:if>
                    </c:forEach>
                </td>
                <td>${donation.quantity}</td>
                <td>${donation.institution.name}</td>
                <td>${donation.created}</td>
                <td>
                    <c:choose>
                        <c:when test="${donation.status==1}">
                            Odebrane
                        </c:when>
                        <c:when test="${donation.status==0}">
                            Nieodebrane
                        </c:when>
                    </c:choose>
                </td>
                <td>${donation.pickedUpDateTime}</td>
                <td>
                    <div class="form-group form-group--buttons">
                        <a href='<c:url value="/app/donation/details/${donation.id}"/>' class="btn btn--small">Szczegóły</a>
                        <a href='<c:url value="/app/donation/edit/${donation.id}"/>' class="btn btn--small">Edytuj</a>
                        <a href='<c:url value="/app/donation/delete/${donation.id}"/>' class="btn btn--small" onclick="return confirm('Czy na pewno chcesz usunąć Swój Datek?')">Usuń</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
