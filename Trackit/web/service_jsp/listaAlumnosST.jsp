<%@page import="GENERAL.Usuarios"%>
<%@page import="BO.AlumnosBO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="application/json" %>
<% 
  Usuarios u;
  u = (Usuarios) session.getAttribute("usuario");
  int id = u.getId();
  String json="";
  AlumnosBO alum = new AlumnosBO();
  json = alum.listaAlumnosST(id);
  out.println(json);

%>