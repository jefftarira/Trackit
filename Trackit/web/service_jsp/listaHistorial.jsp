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

  int id = Integer.parseInt(jObj.getString("id"));
  String json="";
  HistorialBO his = new HistorialBO();
  json = his.listaHistorial(id);
  out.println(json);

%>