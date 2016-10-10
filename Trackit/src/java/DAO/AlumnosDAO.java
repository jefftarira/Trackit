package DAO;

import GENERAL.Alumnos;
import GENERAL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;

public class AlumnosDAO {

  private Conexion con;
  private String sTotal = "SELECT count(*) as cuantos FROM alumnos";
  private String sAlumnos = "select a.id,a.nombres,a.apellidos,a.direccion,u.cedula,a.fecha_ingreso,a.estado from alumnos a,usuarios u where   a.id_usuario=u.id order by a.fecha_ingreso desc limit ?,? ";
  private String sAlumno = "select a.id,u.id id_usuario,u.cedula u_cedula,u.nombres u_nombre,u.apellidos u_apellido,a.fecha_ingreso,a.institucion,a.nombres,a.apellidos,a.direccion,a.conductor,a.expreso,a.encargado,a.estado from alumnos a,usuarios u where a.id_usuario=u.id and a.id = ?";

  private String uAlumno = " UPDATE alumnos SET id_usuario = ?, institucion = ?, apellidos = ?, nombres = ?, direccion = ?, conductor = ?, expreso = ?, encargado = ?, estado = ? WHERE id = ? LIMIT 1 ";

  private String iAlumno = "INSERT INTO alumnos \n"
          + "(id_usuario,fecha_ingreso,institucion,apellidos,nombres,direccion,conductor,expreso,encargado,estado)\n"
          + "VALUES(?,sysdate(),?,?,?,?,?,?,?,?)";

  public AlumnosDAO() throws ClassNotFoundException, SQLException {
    con = new Conexion();
  }

  public int ingresarAlumno(Alumnos a) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;

    con.conectar();
    con.autoCommit(false);
    PreparedStatement ps;
    ps = con.prepareStatement(iAlumno);
    ps.setInt(1, a.getId_usuario());
    ps.setString(2, a.getInstitucion());
    ps.setString(3, a.getApellidos());
    ps.setString(4, a.getNombres());
    ps.setString(5, a.getDireccion());
    ps.setString(6, a.getConductor());
    ps.setInt(7, a.getExpreso());
    ps.setString(8, a.getEncargado());
    ps.setString(9, a.getEstado());
    rAfectados = ps.executeUpdate();
    con.Commit();
    con.cerrar();
    return rAfectados;
  }

  public int actualizarAlumno(Alumnos a) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;

    con.conectar();
    con.autoCommit(false);
    PreparedStatement ps;
    ps = con.prepareStatement(uAlumno);
    ps.setInt(1, a.getId_usuario());
    ps.setString(2, a.getInstitucion());
    ps.setString(3, a.getApellidos());
    ps.setString(4, a.getNombres());
    ps.setString(5, a.getDireccion());
    ps.setString(6, a.getConductor());
    ps.setInt(7, a.getExpreso());
    ps.setString(8, a.getEncargado());
    ps.setString(9, a.getEstado());
    ps.setInt(10, a.getId());

    rAfectados = ps.executeUpdate();

    con.Commit();
    con.cerrar();

    return rAfectados;
  }

  public int totalAlumnos() throws SQLException, ClassNotFoundException {
    con.conectar();
    int total = 0;
    PreparedStatement ps;
    ResultSet rs;
    ps = con.prepareStatement(sTotal);
    rs = ps.executeQuery();
    rs.next();
    total = rs.getInt("cuantos");
    rs.close();
    con.cerrar();
    return total;
  }

  public ArrayList getAlumnos(int desde, int reg_pagina) throws SQLException, ClassNotFoundException {
    ArrayList<Alumnos> aAlumnos = new ArrayList<Alumnos>();
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sAlumnos);
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
