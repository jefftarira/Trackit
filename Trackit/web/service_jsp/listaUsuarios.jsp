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
//  Thread.sleep(2000);
  JSONObject jObj = new JSONObject(sb.toString());

  int pagina = Integer.parseInt(jObj.getString("pagina"));
  int maxreg = Integer.parseInt(jObj.getString("max_reg"));
//  int pagina = 1;
  String json="";
  UsuariosBO cli = new UsuariosBO();
  json = cli.listaUsuarios(pagina,maxreg);
  out.println(json);

%>