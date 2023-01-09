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
                <div class="border-bottom-warning d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-2 text-gray-800">Uwaga <i class="fas fa-exclamation-triangle fa text-gray-300"></i></h1>
                </div>
                <p class="mb-4 text-lg">${resultMsg}</p>

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
<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js"/>'></script>
<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js"/>'></script>
<!-- Page level custom scripts -->
<script src='<c:url value="/resources/js/demo/datatables-demo.js"/>'></script>

<!-- End of Scroll to Top Button / Logout Modal / scripts -->

</body>
</html>
