<%@page import="BO.UsuariosBO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="GENERAL.Alumnos"%>
<%@ page language="java" contentType="application/json" %>
<%
  StringBuilder sb = new StringBuilder();
  BufferedReader br = request.getReader();
 
  String str = null;
  while ((str = br.readLine()) != null) {
    sb.append(str);
  }
  
  JSONObject jObj = new JSONObject(sb.toString());
  System.out.println(jObj);
  String json="";
  UsuariosBO usr = new UsuariosBO();
  json = usr.guardarUsuario(jObj);
  out.println(json);

%>