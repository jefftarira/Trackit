<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Usuarios u;
  u = (Usuarios) session.getAttribute("usuario");
  if (u != null) {
%>
    <jsp:forward page="app.jsp"></jsp:forward>
<%}%>

<!DOCTYPE html>
<html ng-app="appLogin" ng-controller="loginCtrl">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title ng-bind="config.aplicativo + config.iniciales"></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="bootstrap/fonts/ionicons.min.css">
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- Importaciones de angular -->
    <script src="angular/lib/angular.min.js"></script>
    <script src="angular/lib/jcs-auto-validate.min.js"></script>

    <!-- Controladores -->
    <script src="angular/appLogin.js"></script>

    <!-- Servicios -->
    <script src="angular/servicios/configuracion_service.js"></script>
    <script src="angular/servicios/login_service.js"></script>

  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href=""><b>{{config.aplicativo}} </b> {{config.iniciales}}</a>
      </div>
      <div class="login-box-body">
        <p class="login-box-msg">Inicia sesión para ingresar al sistema</p>

        <form name="frmLogin" novalidate="novalidate" ng-submit="ingresar(datos)">
          <div class="form-group has-feedback">
            <input type="text"
                   class="form-control"
                   name="cedula"
                   placeholder="Cédula de edentidad"
                   required="required"
                   ng-model="datos.cedula">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password"
                   class="form-control"
                   name="clave"
                   placeholder="Contraseña"
                   required="required"
                   ng-model="datos.clave">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <button type="submit" class="btn btn-primary btn-block btn-flat" ng-disabled="frmLogin.$invalid || cargando" ng-bind="btnIngresar"></button>
            </div>
          </div>
          <br>
          <div class="row" ng-show="invalido">
            <div class="col-md-12">
              <div class="alert alert-danger">
                <strong ng-bind="mensaje"></strong>
              </div>
            </div>
          </div>
        </form>



      </div>
    </div>
    <!-- jQuery 2.2.3 -->
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>