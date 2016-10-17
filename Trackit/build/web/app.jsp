<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Usuarios u;
  u = (Usuarios) session.getAttribute("usuario");
  if (u == null) {
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%}%>

<!DOCTYPE html>
<html ng-app="alumnosApp" ng-controller="mainCtrl">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title ng-bind="config.aplicativo + config.iniciales"></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="dist/font-awesome-4.6.3/css/font-awesome.css">
    <link rel="stylesheet" href="dist/ionicons-2.0.1/css/ionicons.css">

    <!-- Theme style -->
    <link rel="stylesheet" href="plugins/select2/select2.min.css">
    <link rel="stylesheet" href="dist/css/AdminLTE.css">
    <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
    <!-- Estilos personalizados -->
    <link rel="stylesheet" href="dist/css/animate.css">
    <link rel="stylesheet" href="plugins/toastr/toastr.css">

    <!-- Importaciones de angular -->
    <script src="angular/lib/angular.min.js"></script>
    <script src="angular/lib/angular-route.min.js"></script>
    <script src="angular/lib/jcs-auto-validate.min.js"></script>

    <!-- Controladores -->

    <%
      if (u.getTipo().trim().equals("ADMINISTRADOR")) {%>
    <script src="angular/app.js"></script>
    <%} else {
    %>
    <script src="angular/appUser.js"></script>
    <%
      }
    %>

    <script src="angular/controladores/dashboardCtrl.js"></script>
    <script src="angular/controladores/usuariosCtrl.js"></script>
    <script src="angular/controladores/alumnosCtrl.js"></script>

    <!-- Servicios -->
    <script src="angular/servicios/configuracion_service.js"></script>
    <script src="angular/servicios/usuarios_service.js"></script>
    <script src="angular/servicios/alumnos_service.js"></script>

  </head>

  <body class="hold-transition skin-blue fixed sidebar-mini">
    <div class="wrapper">
      <header class="main-header">
        <a href="#/" class="logo hidden-xs">
          <span class="logo-mini"><b><i class="fa  fa-bullseye"></i></b></span>
          <span class="logo-lg">
            <i class="fa fa-bullseye"></i>
            <b ng-bind="config.aplicativo">
            </b>
            <span ng-bind="config.iniciales"></span>
          </span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account Menu -->
              <li class="dropdown user user-menu" ng-include="'template/cuenta.jsp'">

              </li>
              <!-- Control Sidebar Toggle Button -->
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->

      <aside class="main-sidebar" >
        <section class="sidebar">
          <ul class="sidebar-menu" ng-include=" 'template/menu.jsp'">
          </ul>
        </section>
      </aside>



      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <!-- <section class="content-header">

            <h1>
                <span ng-bind="titulo"></span>
                <small ng-bind="subtitulo"></small>
            </h1>
        </section> -->

        <!-- Main content -->
        <section class="content" ng-view>
          <!-- Your Page Content Here -->

        </section>
        <!-- /.content -->
      </div>
      <!-- /.content-wrapper -->

      <!-- Main Footer -->
      <!--<footer class="main-footer">
<div class="pull-right hidden-xs">
{{config.version}}
</div>
<strong>Copyright &copy; {{config.anio}} <a href="{{config.web}}" target="blank">{{config.empresa}}</a>.</strong> Todos los derechos reservados.
</footer>-->

      <!-- Control Sidebar -->

    </div>
    <!-- jQuery 2.2.3 -->
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>

    <script src="plugins/select2/select2.full.min.js"></script>

    <!-- DataTables -->
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- Sparkline -->
    <script src="plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- SlimScroll 1.3.0 -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
    <!-- Toastr -->
    <script src="plugins/toastr/toastr.js">< /toastr>"></script>
    <script>
                  $(function () {
                    //Initialize Select2 Elements
                    $(".select2").select2();
                  });
    </script>
  </body>


</html>
