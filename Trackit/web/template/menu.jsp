<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Usuarios u;
  u = (Usuarios) session.getAttribute("usuario");
%>
<li class="header">OPCIONES</li>

<%
  if (u.getTipo().trim().equals("ESTANDAR")) {
%>
<li ng-class="mDashboard"><a href="#/"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
  <%
  } else {
  %>
<li ng-class="mAlumnos"><a href="#/alumnos"><i class="fa fa-odnoklassniki"></i> <span>Alumnos</span></a></li>
<li ng-class="mUsuarios"><a href="#/usuarios"><i class="fa fa-users"></i> <span>Cuentas</span></a></li>
  <%
    }
  %>



