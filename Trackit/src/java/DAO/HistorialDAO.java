package DAO;

import GENERAL.Historial;
import GENERAL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialDAO {

  private Conexion con;
  private String sHistorial = " select h.id_alumno,h.id_estado,e.descripcion,h.fecha "
          + " from historial h,estados e "
          + " where h.id_alumno=? "
          + "   and h.id_estado = e.id "
          + "  and h.estado='A' "
          + " order by h.fecha desc ";

  private String iHistorial = " insert into historial (id_alumno,id_estado,fecha) "
          + " values((select a.id from alumnos a where a.dispositivo=? limit 1), "
          + " (select e.id from estados e where e.descripcion = ? limit 1), "
          + " sysdate()) ";

  public HistorialDAO() throws ClassNotFoundException, SQLException {
    con = new Conexion();
  }

  public int setHistorial(String dispositivo, String lugar) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;

    try {
      con.conectar();
      con.autoCommit(false);
      PreparedStatement ps;
      ps = con.prepareStatement(iHistorial);
      ps.setString(1, dispositivo.trim());
      ps.setString(2, lugar.toUpperCase().trim());
      rAfectados = ps.executeUpdate();
      con.Commit();
      con.cerrar();
      return rAfectados;

    } catch (SQLException ex) {
      con.Rollback();
      con.cerrar();
      Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
      return -1;
    }
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
