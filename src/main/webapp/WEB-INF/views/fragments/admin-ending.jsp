<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Scroll to Top Button -->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Czy chcesz się wylogować?</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">Wybierz "Wyloguj" by zakończyć aktualną sesję.</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
        <a class="btn btn-primary" href='<c:url value="/admin/logout"/>'>Wyloguj</a>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src='<c:url value="/resources/vendor/jquery/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>

<!-- Core plugin JavaScript -->
<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>'></script>

<!-- Custom scripts for all pages -->
<script src='<c:url value="/resources/js/sb-admin-2.min.js"/>'></script>