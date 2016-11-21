package DAO;

import GENERAL.Alumnos;
import GENERAL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class AlumnosDAO {

  private Conexion con;
  private String sTotal = " SELECT count(*) as cuantos FROM alumnos ";
  private String sAlumnos = " select a.id,a.nombres,a.apellidos,a.direccion,u.cedula, "
          + " u.nombres u_nombre,u.apellidos u_apellido,a.fecha_ingreso,a.estado "
          + " from alumnos a,usuarios u "
          + " where u.id=? "
          + " and a.id_usuario=u.id "
          + " order by a.fecha_ingreso desc ";
  private String sAlumno = "select a.id,a.dispositivo,u.id id_usuario,u.cedula u_cedula, "
          + "u.nombres u_nombre,u.apellidos u_apellido,a.fecha_ingreso,a.institucion, "
          + " a.nombres,a.apellidos,a.direccion,a.conductor,a.expreso,a.encargado,a.estado "
          + " from alumnos a,usuarios u where a.id_usuario=u.id and a.id = ?";

  private String uAlumno = " UPDATE alumnos SET dispositivo=?, id_usuario = ?, " 
          + " institucion = ?, apellidos = ?, nombres = ?, direccion = ?, " 
          + "conductor = ?, expreso = ?, encargado = ?, estado = ?, buscar= ? " 
          + " WHERE id = ? LIMIT 1 ";

  private String iAlumno = "INSERT INTO alumnos "
          + " (dispositivo,id_usuario,fecha_ingreso,institucion,apellidos,nombres,direccion,conductor,expreso,encargado,estado,buscar) "
          + " VALUES(?,?,sysdate(),?,?,?,?,?,?,?,?,?)";

  public AlumnosDAO() throws ClassNotFoundException, SQLException {
    con = new Conexion();
  }

  public int ingresarAlumno(Alumnos a) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;

    try {
      con.conectar();
      con.autoCommit(false);
      PreparedStatement ps;
      ps = con.prepareStatement(iAlumno);
      ps.setString(1, (a.getDispositivo().trim().isEmpty()) ? null : a.getDispositivo().trim());
      ps.setInt(2, a.getId_usuario());
      ps.setString(3, a.getInstitucion());
      ps.setString(4, a.getApellidos());
      ps.setString(5, a.getNombres());
      ps.setString(6, a.getDireccion());
      ps.setString(7, a.getConductor());
      ps.setInt(8, a.getExpreso());
      ps.setString(9, a.getEncargado());
      ps.setString(10, a.getEstado());
      ps.setString(11, a.getNombres() + " " + a.getApellidos() + " " + a.getDireccion());
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

  public int actualizarAlumno(Alumnos a) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;
    try {
      con.conectar();
      con.autoCommit(false);
      PreparedStatement ps;
      ps = con.prepareStatement(uAlumno);
      ps.setString(1, (a.getDispositivo().trim().isEmpty()) ? null : a.getDispositivo().trim());
      ps.setInt(2, a.getId_usuario());
      ps.setString(3, a.getInstitucion());
      ps.setString(4, a.getApellidos());
      ps.setString(5, a.getNombres());
      ps.setString(6, a.getDireccion());
      ps.setString(7, a.getConductor());
      ps.setInt(8, a.getExpreso());
      ps.setString(9, a.getEncargado());
      ps.setString(10, a.getEstado());
      ps.setString(11, a.getNombres() + " " + a.getApellidos() + " " + a.getDireccion());
      ps.setInt(12, a.getId());

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

  public int totalAlumnos(String vBuscar) throws SQLException, ClassNotFoundException {
    vBuscar = vBuscar.toUpperCase().trim();
    StringTokenizer tokens = new StringTokenizer(vBuscar);
    String select = "";
    select = " SELECT count(*) as cuantos "
            + "  FROM alumnos a "
            + " WHERE  ( a.buscar like concat('%','" + vBuscar + "','%') ";

    if (tokens.countTokens() > 0) {
      select += "   OR ( a.buscar like concat('%','" + tokens.nextToken() + "','%') ";
      while (tokens.hasMoreTokens()) {
        select += " and a.buscar like concat('%','" + tokens.nextToken() + "','%') ";
      }
      select += " ) ";
    }

    select += " ) ";

    con.conectar();
    int total = 0;
    PreparedStatement ps;
    ResultSet rs;
    ps = con.prepareStatement(select);
    rs = ps.executeQuery();
    rs.next();
    total = rs.getInt("cuantos");
    rs.close();
    con.cerrar();
    return total;
  }

  public ArrayList getAlumnos(int desde, int reg_pagina, String vBuscar) throws SQLException, ClassNotFoundException {
    vBuscar = vBuscar.toUpperCase().trim();
    StringTokenizer tokens = new StringTokenizer(vBuscar);
    String select = "";

    select = " select a.id,a.nombres,a.apellidos,a.direccion,u.cedula, "
            + " u.nombres u_nombre,u.apellidos u_apellido,a.fecha_ingreso,a.estado, "
            + " (select e.descripcion from historial h,estados e "
            + " where h.id_estado = e.id and a.id = h.id_alumno and h.estado='A' "
            + " order by h.fecha desc " 
            + " limit 1) ubicacion "
            + " from alumnos a,usuarios u "
            + " where a.id_usuario=u.id ";
    select += " AND ( a.buscar like concat('%','" + vBuscar + "','%') ";

    if (tokens.countTokens() > 0) {
      select += "   OR ( a.buscar like concat('%','" + tokens.nextToken() + "','%') ";
      while (tokens.hasMoreTokens()) {
        select += " and a.buscar like concat('%','" + tokens.nextToken() + "','%') ";
      }
      select += " ) ";
    }

    select += " ) order by a.fecha_ingreso desc limit ?,?  ";
    System.out.println(select);
    ArrayList<Alumnos> aAlumnos = new ArrayList<Alumnos>();
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(select);
    ps.setInt(1, desde);
    ps.setInt(2, reg_pagina);
    ResultSet rs;
    rs = ps.executeQuery();
    Alumnos a;
    while (rs.next()) {
      a = new Alumnos(rs.getInt("id"),
              rs.getString("nombres"),
              rs.getString("apellidos"),
              rs.getString("direccion"),
              rs.getString("cedula"),
              rs.getString("u_nombre"),
              rs.getString("u_apellido"),
              rs.getTimestamp("fecha_ingreso"),
              rs.getString("estado"),
              rs.getString("ubicacion"));
      aAlumnos.add(a);
    }
    rs.close();
    con.cerrar();
    return aAlumnos;
  }

  public ArrayList getAlumnosST(int id) throws SQLException, ClassNotFoundException {

    ArrayList<Alumnos> aAlumnos = new ArrayList<Alumnos>();
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sAlumnos);
    ps.setInt(1, id);
    ResultSet rs;
    rs = ps.executeQuery();
    Alumnos a;
    while (rs.next()) {
      a = new Alumnos(rs.getInt("id"),
              rs.getString("nombres"),
              rs.getString("apellidos"),
              rs.getString("direccion"),
              rs.getString("cedula"),
              rs.getString("u_nombre"),
              rs.getString("u_apellido"),
              rs.getTimestamp("fecha_ingreso"),
              rs.getString("estado"));
      aAlumnos.add(a);
    }
    rs.close();
    con.cerrar();
    return aAlumnos;
  }

  public Alumnos getAlumno(int id) throws ClassNotFoundException, SQLException {
    Alumnos a = null;
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sAlumno);
    ps.setInt(1, id);
    ResultSet rs;
    rs = ps.executeQuery();

    rs.next();
    a = new Alumnos(rs.getInt("id"),
            rs.getString("dispositivo"),
            rs.getTimestamp("fecha_ingreso"),
            rs.getString("institucion"),
            rs.getString("apellidos"),
            rs.getString("nombres"),
            rs.getString("direccion"),
            rs.getString("conductor"),
            rs.getInt("expreso"),
            rs.getString("encargado"),
            rs.getString("estado"),
            rs.getInt("id_usuario"),
            rs.getString("u_cedula"),
            rs.getString("u_nombre"),
            rs.getString("u_apellido"));

    return a;
  }

//    public int insertUsuario(Usuarios u) throws SQLException {
//        PreparedStatement ps;
//        con.autoCommit(false);
//        ps = con.prepareStatement(insert);
//        ps.setString(1, u.getUsername());        
//        ps.setString(2, u.getEmail());
//        ps.setString(3, DigestUtils.md5Hex(u.getClave()));
//        ps.setString(4, u.getNombre());        
//        int n = 0;
//        n = ps.executeUpdate();
//        con.Commit();
//        con.cerrar();
//        return n;
//    }
//    public int validaUsuario(String userName) throws SQLException {
//        PreparedStatement ps;
//        ResultSet rs;
//        int n=0;
//        ps = con.prepareStatement(selectbyUsername);
//        ps.setString(1, userName);
//        rs = ps.executeQuery();
//        while(rs.next())
//            n++;
//        
//        rs.close();
//        return n;
//    }  
}
