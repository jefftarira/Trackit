<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="BO.AlumnosBO"%>
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
  String json="";
  AlumnosBO alu = new AlumnosBO();
  json = alu.guardarAlumno(jObj);
  out.println(json);

%>