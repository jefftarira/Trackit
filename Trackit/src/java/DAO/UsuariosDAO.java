package DAO;

import GENERAL.Conexion;
import GENERAL.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuariosDAO {

  private Conexion con;
  private String sTotal = "SELECT count(*) as cuantos FROM usuarios";
  private String sUsuarios = "select id,tipo,cedula,apellidos,nombres,fecha_registro,estado "
          + " from usuarios order by fecha_registro desc limit ?,? ";
  private String sUsuario = " select id,tipo,cedula,apellidos,nombres,fecha_registro,estado "
          + "   from usuarios "
          + "  where id=? ";
  private String sCuentas = "select id,tipo,cedula,apellidos,nombres,fecha_registro,estado from usuarios where estado='A'";
  private String selectbyUsername = "select username from usuarios where username=?";
  private String iUsuario = " insert into usuarios (tipo,cedula,clave,apellidos,nombres,fecha_registro,buscar) "
          + " values(?,?,?,?,?,sysdate(),?) ";
  private String uUsuario = " update usuarios set tipo=? , cedula = ? , clave = ? , apellidos = ? , nombres = ? , estado = ? , buscar = ? "
          + " where id = ? limit 1 ";
  private String uUsuarioC = " update usuarios set tipo=? , cedula = ? , apellidos = ? , nombres = ? , estado = ? , buscar = ? "
          + " where id = ? limit 1 ";
  private String selectAutenticar = "select id,cedula,tipo,apellidos,nombres,fecha_registro,estado "
          + "from usuarios where cedula=? and clave=md5(?) and estado='A' ";

  public UsuariosDAO() throws ClassNotFoundException, SQLException {
    con = new Conexion();
  }

  public int ingresarUsuario(Usuarios u) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;

    try {
      con.conectar();
      con.autoCommit(false);
      PreparedStatement ps;
      ps = con.prepareStatement(iUsuario);
      ps.setString(1, u.getTipo());
      ps.setString(2, u.getCedula());
      ps.setString(3, DigestUtils.md5Hex(u.getClave()));
      ps.setString(4, u.getApellidos());
      ps.setString(5, u.getNombres());
      ps.setString(6, u.getCedula() + " " + u.getNombres() + " " + u.getApellidos());
      rAfectados = ps.executeUpdate();
      con.Commit();
      con.cerrar();
      return rAfectados;

    } catch (SQLException ex) {
      con.Rollback();
      con.cerrar();
      Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
      return -1;
    }
  }

  public int actualizarUsuario(Usuarios u) throws ClassNotFoundException, SQLException {
    int rAfectados = 0;
    try {
      con.conectar();
      con.autoCommit(false);
      PreparedStatement ps;

      if (u.getClave().trim().equals("********")) {
        ps = con.prepareStatement(uUsuarioC);
        ps.setString(1, u.getTipo());
        ps.setString(2, u.getCedula());
        ps.setString(3, u.getApellidos());
        ps.setString(4, u.getNombres());
        ps.setString(5, u.getEstado());
        ps.setString(6, u.getCedula() + " " + u.getNombres() + " " + u.getApellidos());
        ps.setInt(7, u.getId());
      } else {
        ps = con.prepareStatement(uUsuario);
        ps.setString(1, u.getTipo());
        ps.setString(2, u.getCedula());
        ps.setString(3, DigestUtils.md5Hex(u.getClave()));
        ps.setString(4, u.getApellidos());
        ps.setString(5, u.getNombres());
        ps.setString(6, u.getEstado());
        ps.setString(7, u.getCedula() + " " + u.getNombres() + " " + u.getApellidos());
        ps.setInt(8, u.getId());
      }
      rAfectados = ps.executeUpdate();
      con.Commit();
      con.cerrar();

      return rAfectados;
    } catch (SQLException ex) {
      con.Rollback();
      con.cerrar();
      Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
      return -1;
    }
  }

  public int totalUsuarios(String vBuscar) throws SQLException, ClassNotFoundException {
    vBuscar = vBuscar.toUpperCase().trim();
    StringTokenizer tokens = new StringTokenizer(vBuscar);
    String select;
    select = " SELECT count(*) as cuantos "
            + "  FROM usuarios u "
            + " WHERE  ( u.buscar like concat('%','" + vBuscar + "','%') ";

    if (tokens.countTokens() > 0) {
      select += "   OR ( u.buscar like concat('%','" + tokens.nextToken() + "','%') ";
      while (tokens.hasMoreTokens()) {
        select += " and u.buscar like concat('%','" + tokens.nextToken() + "','%') ";
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

  public Usuarios getUsuario(int id) throws SQLException, ClassNotFoundException {
    Usuarios u = null;
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sUsuario);
    ps.setInt(1, id);
    ResultSet rs;
    rs = ps.executeQuery();

    rs.next();
    u = new Usuarios(rs.getInt("id"),
            rs.getString("tipo"),
            rs.getString("cedula"),
            "********",
            rs.getString("apellidos"),
            rs.getString("nombres"),
            rs.getTimestamp("fecha_registro"),
            rs.getString("estado"));

    return u;
  }

  public ArrayList getUsuarios(int desde, int reg_pagina, String vBuscar) throws SQLException, ClassNotFoundException {
    vBuscar = vBuscar.toUpperCase().trim();
    StringTokenizer tokens = new StringTokenizer(vBuscar);
    String select = "";

    select = " select id,tipo,cedula,apellidos,nombres,fecha_registro,estado "
            + "  from usuarios "
            + " where ( buscar like concat('%','" + vBuscar + "','%') ";

    if (tokens.countTokens() > 0) {
      select += "   OR ( buscar like concat('%','" + tokens.nextToken() + "','%') ";
      while (tokens.hasMoreTokens()) {
        select += " and buscar like concat('%','" + tokens.nextToken() + "','%') ";
      }
      select += " ) ";
    }

    select += " ) order by fecha_registro desc limit ?,?  ";

    ArrayList<Usuarios> aUsuarios = new ArrayList<Usuarios>();
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(select);
    ps.setInt(1, desde);
    ps.setInt(2, reg_pagina);
    ResultSet rs;
    rs = ps.executeQuery();
    Usuarios u;
    while (rs.next()) {
      u = new Usuarios(rs.getInt("id"),
              rs.getString("tipo"),
              rs.getString("cedula"),
              rs.getString("apellidos"),
              rs.getString("nombres"),
              rs.getTimestamp("fecha_registro"),
              rs.getString("estado"));
      aUsuarios.add(u);
    }
    rs.close();
    con.cerrar();
    return aUsuarios;
  }

  public ArrayList listaUsuarios() throws SQLException, ClassNotFoundException {
    ArrayList<Usuarios> aUsuarios = new ArrayList<Usuarios>();
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(sCuentas);
    ResultSet rs;
    rs = ps.executeQuery();
    Usuarios u;
    while (rs.next()) {
      u = new Usuarios(rs.getInt("id"),
              rs.getString("tipo"),
              rs.getString("cedula"),
              rs.getString("apellidos"),
              rs.getString("nombres"),
              rs.getTimestamp("fecha_registro"),
              rs.getString("estado"));
      aUsuarios.add(u);
    }
    rs.close();
    con.cerrar();
    return aUsuarios;
  }

//    public int insertUsuario(Alumnos u) throws SQLException {
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
  public Usuarios autenticar(String cedula, String clave) throws SQLException, ClassNotFoundException {
    Usuarios u = null;
    PreparedStatement ps;
    con.conectar();
    ps = con.prepareStatement(selectAutenticar);
    System.out.println("Se autentico");
    ps.setString(1, cedula);
//    ps.setString(2, DigestUtils.md5Hex(clave.trim()));
    ps.setString(2, clave.trim());
    ResultSet rs;
    rs = ps.executeQuery();
    if (rs.next()) {
      u = new Usuarios(rs.getInt("id"),
              rs.getString("tipo"),
              rs.getString("cedula"),
              rs.getString("apellidos"),
              rs.getString("nombres"),
              rs.getTimestamp("fecha_registro"),
              rs.getString("estado"));
    }
    rs.close();
    con.cerrar();
    return u;
  }

//  public void encriptarClave() throws SQLException {
//    System.out.println("Ingreso a encryptar");
//    con.autoCommit(false);
//    PreparedStatement ps;
//    ps = con.prepareStatement("update usuarios set clave=? where id=1");
//    ps.setString(1, DigestUtils.md5Hex("reload"));
//    int n = 0;
//    n = ps.executeUpdate();
//    con.Commit();
//    con.cerrar();
//    System.out.println(n);
//  }
//    public Alumnos getUsuariobyId(int idUsuarios) throws SQLException{
//        PreparedStatement ps;
//        Alumnos u=null;
//        ps=con.prepareStatement(selectbyId);
//        ps.setInt(1, idUsuarios);
//        ResultSet rs;
//        rs=ps.executeQuery();
//        if(rs.next()){
//            u = new Alumnos(idUsuarios,
//                     rs.getString("username"),
//                     rs.getString("clave"),
//                     rs.getString("nombre"),
//                     rs.getString("estado"));
//            rs.close();
//        }
//        return u;
//    }    
}
