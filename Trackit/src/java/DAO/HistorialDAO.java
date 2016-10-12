package DAO;

import GENERAL.Historial;
import GENERAL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistorialDAO {

  private Conexion con;
  private String sHistorial = " select h.id_alumno,h.id_estado,e.descripcion,h.fecha "
          + " from historial h,estados e "
          + " where h.id_alumno=? "
          + "   and h.id_estado = e.id "
          + "  and h.estado='A' "
          + " order by h.fecha desc ";

  public HistorialDAO() throws ClassNotFoundException, SQLException {
    con = new Conexion();
  }

  public ArrayList getHistorial(int idAlumno) throws SQLException, ClassNotFoundException {
    ArrayList<Historial> aHistorial = new ArrayList<Historial>();
    Historial h;
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sHistorial);
    ps.setInt(1, idAlumno);
    ResultSet rs;
    rs = ps.executeQuery();
    
    while (rs.next()) {
      h = new Historial(rs.getInt("id_alumno"),
              rs.getInt("id_estado"),
              rs.getString("descripcion"),
              rs.getTimestamp("fecha"));
      aHistorial.add(h);
    }
    rs.close();
    con.cerrar();
    return aHistorial;
  }
  
}
