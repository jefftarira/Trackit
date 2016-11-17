package BO;

import DAO.UsuariosDAO;
import GENERAL.Usuarios;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

public class UsuariosBO {

  private UsuariosDAO db;

  public UsuariosBO() throws SQLException, ClassNotFoundException {
    db = new UsuariosDAO();
  }

  public Usuarios autenticar(String cedula, String clave) throws SQLException, ClassNotFoundException {
    return db.autenticar(cedula, clave);
  }

  public String listaUsuarios(int pagina, int maxreg, String vBuscar) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Usuarios> aUsuarios = null;
    Usuarios u = null;

    int desde = 0;
    int reg_pagina = maxreg;
    int pag_siguiente = 0;
    int pag_anterior = 0;
    int totalpaginas = 0;
    double paginasTot = 0;

    int totalregistros = db.totalUsuarios(vBuscar);
    paginasTot = ((double) totalregistros) / ((double) reg_pagina);
    totalpaginas = Math.round(totalregistros / reg_pagina);

    if (paginasTot > totalpaginas) {
      totalpaginas++;
    }

    if (totalpaginas < 1) {
      totalpaginas = 1;
    }

    if (pagina < 1) {
      pagina = 1;
    }

    if (pagina > totalpaginas) {
      pagina = totalpaginas;
    }

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

    aUsuarios = db.getUsuarios(desde, reg_pagina, vBuscar);

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
    for (int i = 0; i < aUsuarios.size(); i++) {
      JSONObject jsonD = new JSONObject();
      u = aUsuarios.get(i);
      jsonD.put("id", u.getId());
      jsonD.put("tipo", u.getTipo());
      jsonD.put("nombres", u.getNombres());
      jsonD.put("apellidos", u.getApellidos());
      jsonD.put("cedula", u.getCedula());
      jsonD.put("registro", u.getFechaRegistro().toInstant());

      if (u.getEstado().trim().equals("A")) {
        status = "ACTIVO";
      } else {
        status = "INACTIVO";
      }

      jsonD.put("estado", status);
      aC.add(jsonD);
    }
    obj.put("usuarios", aC);
    return obj.toString();

  }

  public String cargaUsuario(int id) throws ClassNotFoundException, SQLException, JSONException {

    Usuarios u = null;

    if (id == 0) {
      System.out.println("Nuevo Usuario");

      JSONObject obj = new JSONObject();
      obj.put("err", false);

      JSONObject jsonD = new JSONObject();
      jsonD.put("id", 0);
      jsonD.put("tipo", "");
      jsonD.put("nombres", "");
      jsonD.put("apellidos", "");
      jsonD.put("cedula", "");
      jsonD.put("clave", "");
      jsonD.put("claveRep", "");
      jsonD.put("registro", "");
      jsonD.put("estado", true);
      obj.put("usuario", jsonD);
      return obj.toString();

    } else {
      System.out.println("Edicion de usuario");
      u = db.getUsuario(id);

      JSONObject obj = new JSONObject();

      obj.put("err", false);

      JSONObject jsonD = new JSONObject();
      boolean status = false;
      if (u.getEstado().trim().equals("A")) {
        status = true;
      } else {
        status = false;
      }
      jsonD.put("id", u.getId());
      jsonD.put("tipo", u.getTipo());
      jsonD.put("nombres", u.getNombres());
      jsonD.put("apellidos", u.getApellidos());
      jsonD.put("cedula", u.getCedula());
      jsonD.put("clave", u.getClave());
      jsonD.put("claveRep", u.getClave());
      jsonD.put("registro", u.getFechaRegistro().toInstant());
      jsonD.put("estado", status);
      obj.put("usuario", jsonD);
      return obj.toString();
    }
  }

  public String guardarUsuario(JSONObject jObj) throws ClassNotFoundException, SQLException, JSONException {
    int n = 0;
    JSONObject obj = new JSONObject();
    String estado = "";
    if (jObj.getBoolean("estado")) {
      estado = "A";
    } else {
      estado = "I";
    }

    if (jObj.getString("clave").trim().equals(jObj.getString("claveRep").trim())) {
      Usuarios u = null;
      u = new Usuarios(Integer.parseInt(jObj.getString("id")),
              jObj.getString("tipo").toUpperCase().trim(),
              jObj.getString("cedula").toUpperCase().trim(),
              jObj.getString("apellidos").toUpperCase().trim(),
              jObj.getString("nombres").toUpperCase().trim(),
              jObj.getString("clave").trim(),
              estado);

      if (u.getId() != 0) {
        n = db.actualizarUsuario(u);
      } else {
        n = db.ingresarUsuario(u);
      }

      if (n < 0) {
        obj.put("err", true);
        obj.put("mensaje", "Error al guardar");
      } else {
        obj.put("err", false);
        obj.put("mensaje", "Se guardo correctamente");
      }
    } else {
      obj.put("err", true);
      obj.put("mensaje", "Las claves no coinciden");
    }

    return obj.toString();
  }

//    public void encriptar() throws SQLException {
//      System.out.println("Ingreso a encryptar");
//      db.encriptarClave();
//    }    
}
