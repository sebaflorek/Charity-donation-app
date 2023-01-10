<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Admin panel</title>
    <!-- Custom fonts for this template-->
    <link href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>' rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href='<c:url value="/resources/css/sb-admin-2.min.css"/>' rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.css"/>' rel="stylesheet">
    <style>
        .errorMsg {
            display: block;
            margin: 1px auto;
            padding-left: 20px;
            background-repeat: no-repeat;
            background-position: left;
            color: #D8000C;
            background-image: url('https://i.imgur.com/GnyDvKN.png');
            background-size: 16px;
            border-bottom: 10px;
        }

        .errorDiv {
            font-size: 1rem;
            font-weight: 300;
            font-style: normal;
        }
    </style>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <%@include file="fragments/admin-sidebar.jsp" %>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <%@include file="fragments/admin-topbar.jsp" %>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-2 text-gray-800">Użytkownicy</h1>
                    <a href='<c:url value="/admin/user/list"/>'
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-arrow-alt-circle-left fa-sm text-white-50"></i> Wróć</a>
                </div>
                <p class="mb-4">Uzupełnij poniższe pola:</p>

                <!-- Begin - MAIN CONTENT -->
                <form:form class="user" method="post" modelAttribute="userEditDto">
                    <div class="form-group">
                        <form:input class="form-control" path="username" placeholder="Podaj login"/>
                        <div class="errorDiv"><form:errors path="username" cssClass="errorMsg"/></div>
                    </div>

                    <div class="form-group">
                        <form:input class="form-control" path="name" placeholder="Podaj imię"/>
                        <div class="errorDiv"><form:errors path="name" cssClass="errorMsg"/></div>
                    </div>

                    <div class="form-group">
                        <form:input class="form-control" path="surname" placeholder="Podaj nazwisko"/>
                        <div class="errorDiv"><form:errors path="surname" cssClass="errorMsg"/></div>
                    </div>

                    <div class="form-group">
                        <form:input class="form-control" path="email" placeholder="Podaj email"/>
                        <div class="errorDiv"><form:errors path="email" cssClass="errorMsg"/></div>
                    </div>

                    <form:hidden path="id"/>

                    <button type="submit" class="btn btn-primary">
                        Zapisz
                    </button>
                    <a href='<c:url value="/admin/user/list"/>' class="btn btn-google">
                        Anuluj
                    </a>
                </form:form>
                <!-- End - MAIN CONTENT -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <%@include file="fragments/admin-footer.jsp" %>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button / Logout Modal / scripts -->
<%@include file="fragments/admin-ending.jsp" %>

<!-- Page level plugins -->

<!-- Page level custom scripts -->

<!-- End of Scroll to Top Button / Logout Modal / scripts -->

</body>
</html>
