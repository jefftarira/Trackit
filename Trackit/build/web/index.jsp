<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   Usuarios u;
   u=(Usuarios)session.getAttribute("usuario");
   if(u!=null){ %>
   <jsp:forward page="app.jsp"></jsp:forward>
<% } else{%>
<jsp:forward page="login.jsp"></jsp:forward>
<% } %>