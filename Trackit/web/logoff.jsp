
<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   Usuarios u;
   u=(Usuarios)session.getAttribute("usuario"); 
   if(u!=null){
       session.removeAttribute("usuario");       
   }
   response.sendRedirect("index.jsp");
%>