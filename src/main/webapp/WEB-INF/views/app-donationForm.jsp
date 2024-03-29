<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Donation form</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="fragments/header.jsp" %>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<%--CONTENT-START--%>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3 id="important">Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>
    <c:if test="${hasErrors}">
    <div class="form--steps-errorInstructions">
        <div class="form--steps-container">
            <h3>Uwaga!</h3>
            <p class="active">
                Formularz niewysłany! Popraw wskazane pozycje i wyślij ponownie!
            </p>
        </div>
    </div>
    </c:if>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="post" modelAttribute="donation">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach var="category" items="${categoryList}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox path="categories" value="${category}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--inline">
                    <label>
                        <form:errors path="categories" cssClass="errorMsg"/>
                    </label>
                </div>

                    <%--                <div>--%>
                    <%--                    <label>--%>
                    <%--                        <form:checkboxes class="test" path="categories" items="${categoryList}" itemValue="id"--%>
                    <%--                                         itemLabel="name"/>--%>
                    <%--                        <form:errors path="categories" cssClass="errorMsg"/>--%>
                    <%--                    </label>--%>
                    <%--                </div>--%>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantity" type="number" min="1" step="1"/>
                        <form:errors path="quantity" cssClass="errorMsg"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizację, której chcesz pomóc:</h3>

                <c:forEach var="institution" items="${institutionList}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${institution}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                      <div class="title">Fundacja "${institution.name}"</div>
                                      <div class="subtitle">Cel i misja: ${institution.description}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--inline">
                    <label>
                        <form:errors path="institution" cssClass="errorMsg"/>
                    </label>
                </div>

                    <%--                <div>--%>
                    <%--                    <label>--%>
                    <%--                        <form:radiobuttons path="institution" items="${institutionList}" itemValue="id"--%>
                    <%--                                           itemLabel="name"/>--%>
                    <%--                        <form:errors path="institution" cssClass="errorMsg"/>--%>
                    <%--                    </label>--%>
                    <%--                </div>--%>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzeczy przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label>
                                Ulica
                                <form:input path="street"/>
                            </label>
                            <div class="errorDiv"><form:errors path="street" cssClass="errorMsg"/></div>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Miasto
                                <form:input path="city"/>
                            </label>
                            <div class="errorDiv"><form:errors path="city" cssClass="errorMsg"/></div>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy
                                <form:input path="zipCode" placeholder="xx-xxx"/>
                            </label>
                            <div class="errorDiv"><form:errors path="zipCode" cssClass="errorMsg"/></div>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu
                                <form:input path="phoneNumber"/>
                            </label>
                            <div class="errorDiv"><form:errors path="phoneNumber" cssClass="errorMsg"/></div>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label>
                                Data
                                <form:input path="pickUpDate" type="date"/>
                            </label>
                            <div class="errorDiv"><form:errors path="pickUpDate" cssClass="errorMsg"/></div>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Godzina
                                <form:input path="pickUpTime" type="time"/>
                            </label>
                            <div class="errorDiv"><form:errors path="pickUpTime" cssClass="errorMsg"/></div>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" placeholder="Max 600 znaków"/>
                            </label>
                            <div class="errorDiv"><form:errors path="pickUpComment" cssClass="errorMsg"/></div>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="bagsSummary">Nic nie oddajesz</span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="institutionSummary">Nie wybrano fundacji</span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="street">Prosta 51</li>
                                <li id="city">Warszawa</li>
                                <li id="zipCode">99-098</li>
                                <li id="phone">123 456 789</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="date">13/12/2018</li>
                                <li id="time">15:40</li>
                                <li id="comment">Brak uwag</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <form:hidden path="user" value="${currentUser.user.id}"/>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>
<%--CONTENT-STOP--%>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
