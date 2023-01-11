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
    <title>Donation Details</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="fragments/header.jsp" %>
</header>
<%--CONTENT-START--%>
<section class="login-page">
    <h2>Szczegóły datku</h2>
    <div class="app-table-container">
        <table class="donation-details">
            <tr>
                <td>Oddawane rzeczy:</td>
                <td>
                    <c:forEach varStatus="loopStatus" var="category" items="${donation.categories}">
                        ${category.name}<c:if test="${!loopStatus.last}">,</c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Ilość przekazywanych worków:</td>
                <td>${donation.quantity} szt.</td>
            </tr>
            <tr>
                <td>Dla fundacji:</td>
                <td>"${donation.institution.name}"</td>
            </tr>
            <tr>
                <td>Cel fundacji:</td>
                <td>${donation.institution.description}</td>
            </tr>
            <th colspan="2">ADRES ODBIORU</th>
            <tr>
                <td>Ulica:</td>
                <td>${donation.street}</td>
            </tr>
            <tr>
                <td>Miasto:</td>
                <td>${donation.city}</td>
            </tr>
            <tr>
                <td>Kod pocztowy:</td>
                <td>${donation.zipCode}</td>
            </tr>
            <tr>
                <td>Numer telefonu:</td>
                <td>${donation.phoneNumber}</td>
            </tr>
            <th colspan="2">TERMIN ODBIORU</th>
            <tr>
                <td>Miasto:</td>
                <td>${donation.city}</td>
            </tr>
            <tr>
                <td>Data:</td>
                <td>${donation.pickUpDate}</td>
            </tr>
            <tr>
                <td>Godzina:</td>
                <td>${donation.pickUpTime}</td>
            </tr>
            <tr>
                <td>Uwagi:</td>
                <td>${donation.pickUpComment}</td>
            </tr>
            <th colspan="2">STATUS DATKU</th>
            <tr>
                <td>Data zgłoszenia:</td>
                <td>${donation.created}</td>
            </tr>
            <tr>
                <td>Data przekazania:</td>
                <td><c:out value="${donation.pickedUpDateTime}" default="Oczekuje na odbiór"/></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>
                    <c:choose>
                        <c:when test="${donation.status==0}">
                            <b style="color: darkred">Nieodebrany</b>
                        </c:when>
                        <c:when test="${donation.status==1}">
                            <b style="color: green">Odebrany</b>
                        </c:when>
                    </c:choose>
                </td>
            </tr>

        </table>
    </div>
    <c:if test="${donation.status==0}">
        <div class="form-group form-group--buttons">
            <a href='<c:url value="/app/donation/donated/${donation.id}"/>' class="btn">Oznacz jako odebrany</a>
        </div>
    </c:if>
    <br>
    <div class="form-group form-group--buttons">
        <a href='<c:url value="/app/donation/my-list"/>' class="btn btn--without-border">Wróć</a>
        <a href='<c:url value="/app/donation/edit/${donation.id}"/>' class="btn btn--without-border">Edytuj</a>
        <a href='<c:url value="/app/donation/delete/${donation.id}"/>' class="btn btn--without-border">Usuń</a>
    </div>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
