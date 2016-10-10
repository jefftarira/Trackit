<%@page import="GENERAL.Usuarios"%>
<%@page import="BO.UsuariosBO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="application/json" %>
<%  
  boolean frw = false;
  StringBuilder sb = new StringBuilder();
  BufferedReader br = request.getReader();
  String str = null;
  while ((str = br.readLine()) != null) {
    sb.append(str);
  }
  JSONObject jObj = new JSONObject(sb.toString());

  String cedula = jObj.getString("cedula");
  String clave = jObj.getString("clave");

  String mensajeError = "";
  boolean bError = false;

  if (cedula != null && clave != null) {
    UsuariosBO user = new UsuariosBO();
    Usuarios u = null;
    u = user.autenticar(cedula, clave);

    if (u != null) {      
      session.setAttribute("usuario", u);
      session.setMaxInactiveInterval(-1);
      frw = true;
    } else {
      bError = true;
      mensajeError = mensajeError + " Usuario o clave incorrecto. ";
    }

  } else {
    bError = true;
    mensajeError = mensajeError + " Ocurrio un error, vuelva a intentar mas tarde. ";
  }

  JSONObject obj = new JSONObject();
  obj.put("err", bError);
  obj.put("mensaje", mensajeError);
  out.println(obj);
%>


