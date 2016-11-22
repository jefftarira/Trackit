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
    int expreso = Integer.parseInt(jObj.getString("expreso"));

    String json = "";
    AlumnosBO alum = new AlumnosBO();
    json = alum.listaAlumnosExpreso(expreso);
    out.println(json);
%>