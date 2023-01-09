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
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-2 text-gray-800">Administratorzy</h1>
                    <a href='<c:url value="/admin/add"/>'
                       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-plus-circle fa-sm text-white-50"></i> Dodaj administratora</a>
                </div>
                <p class="mb-4">Lista aktywnych administratorów:</p>

                <!-- Begin - MAIN CONTENT -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Admins</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Email</th>
                                    <th>Enabled</th>
                                    <th>Roles</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td>${user.name}</td>
                                        <td>${user.surname}</td>
                                        <td>${user.email}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.enabled==1}">
                                                    YES
                                                </c:when>
                                                <c:when test="${user.enabled==0}">
                                                    NO
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:forEach varStatus="loopStatus" var="role" items="${user.roles}">
                                                ${role.name}<c:if test="${!loopStatus.last}">,</c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <a href='<c:url value="/admin/edit/${user.id}"/>' class="btn btn-secondary btn-icon-split btn-sm">
                                                <span class="icon text-gray-600"><i class="fas fa-arrow-right"></i></span>
                                                <span class="text">Edytuj</span>
                                            </a>
                                            <a href='<c:url value="/admin/delete/${user.id}"/>' class="btn btn-danger btn-icon-split btn-sm" onclick="return confirm('Czy na pewno usunąć Instytucję?')">
                                                <span class="icon text-white-50"><i class="fas fa-trash"></i></span>
                                                <span class="text">Usuń</span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
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
<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js"/>'></script>
<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js"/>'></script>
<!-- Page level custom scripts -->
<script src='<c:url value="/resources/js/demo/datatables-demo.js"/>'></script>

<!-- End of Scroll to Top Button / Logout Modal / scripts -->

</body>
</html>
