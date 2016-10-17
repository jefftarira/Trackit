<%@page import="java.util.StringTokenizer"%>
<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Usuarios u;
u = (Usuarios) session.getAttribute("usuario");
%>
<a href="" class="dropdown-toggle" data-toggle="dropdown">
  <!-- The user image in the navbar-->
  <img src="dist/img/avatar5.png" class="user-image" alt="User Image">
  <!-- hidden-xs hides the username on small devices so only the image appears. -->
  <span class="hidden-xs">
    <%
    String cuenta = u.getNombres().trim() +" "+ u.getApellidos().trim();
    StringTokenizer st = new StringTokenizer(cuenta);
    String vUsuario = "";
    while (st.hasMoreTokens()){
    String texto = st.nextToken();
    vUsuario +=texto.substring(0, 1).toUpperCase()+texto.substring(1).toLowerCase()+" ";
  }
  out.print(vUsuario);
  %>
</span>
</a>
<ul class="dropdown-menu">
  <!-- The user image in the menu -->
  <li class="user-header">
    <img src="dist/img/avatar5.png" class="img-circle" alt="User Image">

    <p>
      <%out.print(vUsuario);%>
      <small>Registrado  -   <% out.print(u.getFechaRegistro().toGMTString()); %> </small>
    </p>
  </li>

  <!-- Menu Footer-->
  <li class="user-footer">
    <!-- <div class="pull-left">
          <a href="" class="btn btn-default btn-flat">Perfil</a>
        </div>
      -->
      <div class="pull-right">
      <a href="" class="btn btn-default btn-flat" ng-click="cerrarSesion()">Cerrar sesión</a>
      </div>
    </li>
  </ul>
