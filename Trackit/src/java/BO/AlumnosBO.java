package BO;

import DAO.AlumnosDAO;
import DAO.UsuariosDAO;
import GENERAL.Alumnos;
import GENERAL.Usuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

public class AlumnosBO {

  private AlumnosDAO db;

  public AlumnosBO() throws SQLException, ClassNotFoundException {
    db = new AlumnosDAO();
  }

  public String listaAlumnos(int pagina, int maxreg, String vBuscar) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Alumnos> aAlumnos = null;
    Alumnos a = null;

    int desde = 0;
    int reg_pagina = maxreg;
    int pag_siguiente = 0;
    int pag_anterior = 0;
    int totalregistros = 0;
    int totalpaginas = 0;
    double paginasTot = 0;

    totalregistros = db.totalAlumnos(vBuscar);
    paginasTot = ((double) totalregistros) / ((double) reg_pagina);
    totalpaginas = Math.round(totalregistros / reg_pagina);
    
    if(paginasTot>totalpaginas) totalpaginas++;

    if (totalpaginas < 1) {totalpaginas = 1;}

    if (pagina < 1) {pagina = 1;}

    if (pagina > totalpaginas) {pagina = totalpaginas;}

    pagina -= 1;
    desde = pagina * reg_pagina;

    if (pagina >= totalpaginas - 1) {
      pag_siguiente = 1;
    } else {
      pag_siguiente = pagina + 2;
    }

    if (pagina < 1) {
      pag_anterior = totalpaginas;
    } else {
      pag_anterior = pagina;
    }

    aAlumnos = db.getAlumnos(desde, reg_pagina, vBuscar);

    JSONObject obj = new JSONObject();
    obj.put("err", false);
    obj.put("conteo", totalregistros);
    obj.put("reg_pagina", reg_pagina);
    obj.put("pag_actual", pagina + 1);
    obj.put("pag_siguiente", pag_siguiente);
    obj.put("pag_anterior", pag_anterior);
    obj.put("total_paginas", totalpaginas);

    JSONArray aPag = new JSONArray();
    for (int i = 0; i < totalpaginas; i++) {
      JSONObject jsonD = new JSONObject();
      jsonD.put("num", i + 1);
      aPag.add(jsonD);
    }
    obj.put("paginas", aPag);

    String status = "";
    JSONArray aC = new JSONArray();
    for (int i = 0; i < aAlumnos.size(); i++) {
      JSONObject jsonD = new JSONObject();
      a = aAlumnos.get(i);
      jsonD.put("id", a.getId());
      jsonD.put("nombres", a.getNombres());
      jsonD.put("apellidos", a.getApellidos());
      jsonD.put("direccion", a.getDireccion());
      jsonD.put("cedula", a.getU_cedula());
      jsonD.put("u_nombre", a.getU_nombre());
      jsonD.put("u_apellido", a.getU_apellido());
      jsonD.put("registro", a.getFecha_ingreso().toInstant());

      if (a.getEstado().trim().equals("A")) {
        status = "ACTIVO";
      } else {
        status = "INACTIVO";
      }
      jsonD.put("estado", status);
      jsonD.put("ubicacion", (a.getUbicacion() == null) ? "" : a.getUbicacion());      
      aC.add(jsonD);
    }
    obj.put("alumnos", aC);

    return obj.toString().trim();
  }

  public String listaAlumnosST(int id) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Alumnos> aAlumnos = null;
    Alumnos a = null;

    aAlumnos = db.getAlumnosST(id);

    JSONObject obj = new JSONObject();
    obj.put("err", false);

    String status = "";
    JSONArray aC = new JSONArray();
    for (int i = 0; i < aAlumnos.size(); i++) {
      JSONObject jsonD = new JSONObject();
      a = aAlumnos.get(i);
      jsonD.put("id", a.getId());
      jsonD.put("nombres", a.getNombres());
      jsonD.put("apellidos", a.getApellidos());
      jsonD.put("direccion", a.getDireccion());
      jsonD.put("cedula", a.getU_cedula());
      jsonD.put("u_nombre", a.getU_nombre());
      jsonD.put("u_apellido", a.getU_apellido());
      jsonD.put("registro", a.getFecha_ingreso().toInstant());

      if (a.getEstado().trim().equals("A")) {
        status = "ACTIVO";
      } else {
        status = "INACTIVO";
      }
      jsonD.put("estado", status);
      jsonD.put("ubicacion", (a.getUbicacion() == null) ? "" : a.getUbicacion());
      aC.add(jsonD);
    }
    obj.put("alumnos", aC);

    return obj.toString().trim();
  }
  
  public String listaAlumnosExpreso(int expreso) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Alumnos> aAlumnos = null;
    Alumnos a = null;
    
    aAlumnos = db.getAlumnosExpreso(expreso);

    JSONObject obj = new JSONObject();
    
    JSONArray aC = new JSONArray();
    for (int i = 0; i < aAlumnos.size(); i++) {
      JSONObject jsonD = new JSONObject();
      a = aAlumnos.get(i);
      jsonD.put("id", a.getId());
      jsonD.put("nombres", a.getNombres());
      jsonD.put("apellidos", a.getApellidos());
      jsonD.put("direccion", a.getDireccion());
      jsonD.put("dispositivo",a.getDispositivo());
      aC.add(jsonD);
    }
    obj.put("alumnos", aC);
    
    System.out.println(obj);

    return obj.toString().trim();
  }

  public String cargaAlumno(int id) throws ClassNotFoundException, SQLException, JSONException {

    Alumnos a = null;
    Usuarios u = null;
    ArrayList<Usuarios> aUsuarios = null;
    UsuariosDAO uDao;
    uDao = new UsuariosDAO();
    aUsuarios = uDao.listaUsuarios();

    if (id == 0) {

      JSONObject obj = new JSONObject();
      obj.put("err", false);

      JSONObject jsonD = new JSONObject();
      jsonD.put("id", id);
      jsonD.put("dispositivo", "");
      jsonD.put("fecha_ingreso", "");
      jsonD.put("institucion", "");
      jsonD.put("apellidos", "");
      jsonD.put("nombres", "");
      jsonD.put("direccion", "");
      jsonD.put("conductor", "");
      jsonD.put("expreso", "");
      jsonD.put("encargado", "");
      jsonD.put("estado", true);
      jsonD.put("id_usuario", "");
      obj.put("alumno", jsonD);

      JSONArray aC = new JSONArray();
      for (int i = 0; i < aUsuarios.size(); i++) {
        JSONObject jsonU = new JSONObject();
        u = aUsuarios.get(i);
        jsonU.put("id", u.getId());
        jsonU.put("nombres", u.getNombres());
        jsonU.put("apellidos", u.getApellidos());
        jsonU.put("cedula", u.getCedula());
        aC.add(jsonU);
      }
      obj.put("usuarios", aC);

      return obj.toString();

    } else {
      a = db.getAlumno(id);
      JSONObject obj = new JSONObject();
      obj.put("err", false);

      JSONObject jsonD = new JSONObject();
      jsonD.put("id", a.getId());
      jsonD.put("dispositivo", (a.getDispositivo() == null) ? "" : a.getDispositivo());
      jsonD.put("fecha_ingreso", a.getFecha_ingreso().toInstant());
      jsonD.put("institucion", a.getInstitucion());
      jsonD.put("apellidos", a.getApellidos());
      jsonD.put("nombres", a.getNombres());
      jsonD.put("direccion", a.getDireccion());
      jsonD.put("conductor", a.getConductor());
      jsonD.put("expreso", a.getExpreso());
      jsonD.put("encargado", a.getEncargado());

      boolean status = false;
      if (a.getEstado().trim().equals("A")) {
        status = true;
      } else {
        status = false;
      }

      jsonD.put("estado", status);
      jsonD.put("id_usuario", a.getId_usuario());
      jsonD.put("u_cedula", a.getU_cedula().trim());
      jsonD.put("u_nombre", a.getU_nombre());
      jsonD.put("u_apellido", a.getU_apellido());

      obj.put("alumno", jsonD);

      JSONArray aC = new JSONArray();
      for (int i = 0; i < aUsuarios.size(); i++) {
        JSONObject jsonU = new JSONObject();
        u = aUsuarios.get(i);
        jsonU.put("id", u.getId());
        jsonU.put("nombres", u.getNombres());
        jsonU.put("apellidos", u.getApellidos());
        jsonU.put("cedula", u.getCedula());
        aC.add(jsonU);
      }
      obj.put("usuarios", aC);
      return obj.toString();
    }
  }

  public String guardarAlumno(JSONObject jObj) throws ClassNotFoundException, SQLException, JSONException {
    int n = 0;

    String estado = "";
    if (jObj.getBoolean("estado")) {
      estado = "A";
    } else {
      estado = "I";
    }

    Alumnos a = null;
    a = new Alumnos(Integer.parseInt(jObj.getString("id")),
            jObj.getString("dispositivo").trim(),
            jObj.getString("institucion").toUpperCase().trim(),
            jObj.getString("apellidos").toUpperCase().trim(),
            jObj.getString("nombres").toUpperCase().trim(),
            jObj.getString("direccion").toUpperCase().trim(),
            jObj.getString("conductor").toUpperCase().trim(),
            Integer.parseInt(jObj.getString("expreso")),
            jObj.getString("encargado").toUpperCase().trim(),
            estado,
            Integer.parseInt(jObj.getString("id_usuario")));

    if (a.getId() != 0) {
      n = db.actualizarAlumno(a);
    } else {
      n = db.ingresarAlumno(a);
    }

    JSONObject obj = new JSONObject();

    if (n < 0) {
      obj.put("err", true);
      obj.put("mensaje", "Error al guardar");
    } else {
      obj.put("err", false);
      obj.put("mensaje", "Se guardo correctamente");
    }

    return obj.toString();
  }

//    public void encriptar() throws SQLException {
//      System.out.println("Ingreso a encryptar");
//      db.encriptarClave();
//    }    
}
