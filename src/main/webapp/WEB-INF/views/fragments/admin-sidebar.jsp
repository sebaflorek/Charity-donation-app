<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href='<c:url value="/admin/panel"/>'>
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href='<c:url value="/admin/panel"/>'>
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Oddam w dobre ręce
    </div>

    <!-- Nav Item - Institutions -->
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/admin/institution/list"/>'>
            <i class="fas fa-fw fa-building"></i>
            <span>Instytucje</span></a>
    </li>

    <!-- Nav Item - Donations -->
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/admin/donation/list"/>'>
            <i class="fas fa-fw fa-gifts"></i>
            <span>Datki</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Users
    </div>

    <!-- Nav Item - Users -->
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/admin/user/list"/>'>
            <i class="fas fa-fw fa-user"></i>
            <span>Użytkownicy</span></a>
    </li>

    <!-- Nav Item - Admins -->
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/admin/list"/>'>
            <i class="fas fa-fw fa-user-shield"></i>
            <span>Administratorzy</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->