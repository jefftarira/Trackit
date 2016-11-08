<%@page import="BO.HistorialBO"%>
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
  String lugar = jObj.getString("lugar").trim().toUpperCase();
  String dispositivo = jObj.getString("codigo").trim();
  
  HistorialBO his = new HistorialBO();
  String json = his.registrarUbicacion(dispositivo,lugar);
  out.println(json);
%>