<%@page import="BO.UsuariosBO"%>
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
  String vBuscar = jObj.getString("buscar");
  String json = "";
  UsuariosBO usr = new UsuariosBO();
  json = usr.listaUsuarios(pagina, maxreg,vBuscar);
  out.println(json);

%>