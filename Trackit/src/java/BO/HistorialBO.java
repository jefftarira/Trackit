package BO;

import DAO.HistorialDAO;
import GENERAL.Historial;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

public class HistorialBO {

  private HistorialDAO db;

  public HistorialBO() throws SQLException, ClassNotFoundException {
    db = new HistorialDAO();
  }

  public String listaHistorial(int idAlumno) throws SQLException, ClassNotFoundException, JSONException {
    String json = "";

    ArrayList<Historial> aHistorial = null;
    Historial h = null;

    aHistorial = db.getHistorial(idAlumno);

    JSONObject obj = new JSONObject();
    obj.put("err", false);

    JSONArray aC = new JSONArray();
    for (int i = 0; i < aHistorial.size(); i++) {
      JSONObject jsonD = new JSONObject();
      h = aHistorial.get(i);
      jsonD.put("id_alumno", h.getId_alumno());
      jsonD.put("id_estado", h.getId_estado());
      jsonD.put("descripcion", h.getDescripcion());
      jsonD.put("fecha", h.getFecha().toInstant());

      if (h.getId_estado() == 1) {
        jsonD.put("estiloicono", "fa-home");
        jsonD.put("estilocolor", "bg-red");
      }
      
      if (h.getId_estado() == 2) {
        jsonD.put("estiloicono", "fa-bus");
        jsonD.put("estilocolor", "bg-blue");
      }
      
      if (h.getId_estado() == 3) {
        jsonD.put("estiloicono", "fa-graduation-cap");
        jsonD.put("estilocolor", "bg-green");
      }

      aC.add(jsonD);
    }
    obj.put("historial", aC);

    System.out.println(obj);
    return obj.toString().trim();
  }
}
