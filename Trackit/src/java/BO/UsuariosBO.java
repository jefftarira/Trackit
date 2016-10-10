package BO;

import DAO.UsuariosDAO;
import GENERAL.Usuarios;
import java.sql.SQLException;
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

  public String listaUsuarios(int pagina, int maxreg) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Usuarios> aUsuarios = null;
    Usuarios u = null;

    int desde = 0;
    int reg_pagina = 20;
    int pag_siguiente = 0;
    int pag_anterior = 0;
    int totalregistros = 0;
    int totalpaginas = 0;

    totalregistros = db.totalUsuarios();
    totalpaginas = Math.round(totalregistros / 20);
    System.out.println("total Paginas " + totalpaginas);

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

    aUsuarios = db.getUsuarios(desde, reg_pagina);

    JSONObject obj = new JSONObject();
    obj.put("err", false);
    obj.put("conteo", totalregistros);
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

//    public void encriptar() throws SQLException {
//      System.out.println("Ingreso a encryptar");
//      db.encriptarClave();
//    }    
}
