<%@page import="BO.UsuariosBO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="application/json" %>
<%
  
  int id = 0;
  StringBuilder sb = new StringBuilder();
  BufferedReader br = request.getReader();
 
  String str = null;
  while ((str = br.readLine()) != null) {
    sb.append(str);
  }
  
  JSONObject jObj = new JSONObject(sb.toString());  
  if(jObj.length() !=0)
    id = Integer.parseInt(jObj.getString("id"));

  String json="";
  UsuariosBO usr = new UsuariosBO();
  json = usr.cargaUsuario(id);
  out.println(json);

%>