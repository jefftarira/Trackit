<%@page import="BO.AlumnosBO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="application/json" %>
<%
  
  
  StringBuilder sb = new StringBuilder();
  BufferedReader br = request.getReader();

  String str = null;
  while ((str = br.readLine()) != null) {
    sb.append(str);
  }
  
  JSONObject jObj = new JSONObject(sb.toString());

  int pagina = Integer.parseInt(jObj.getString("pagina"));
  int maxreg = Integer.parseInt(jObj.getString("max_reg"));
  String json="";
  AlumnosBO alum = new AlumnosBO();
  json = alum.listaAlumnos(pagina,maxreg);
  out.println(json);

%>