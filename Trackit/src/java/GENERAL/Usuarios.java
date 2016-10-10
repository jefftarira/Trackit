package GENERAL;

import java.sql.Date;
import java.sql.Timestamp;

public class Usuarios {

  private int id;
  private String tipo;
  private String cedula;
  private String clave;
  private String apellidos;
  private String nombres;
  private Timestamp fechaRegistro;
  private String estado;

  public Usuarios(int id, String tipo, String cedula, String clave, String apellidos, String nombres, Timestamp fechaRegistro, String estado) {
    this.setId(id);
    this.setTipo(tipo);
    this.setCedula(cedula);
    this.setClave(clave);
    this.setApellidos(apellidos);
    this.setNombres(nombres);
    this.setFechaRegistro(fechaRegistro);
    this.setEstado(estado);
  }

  public Usuarios(int id, String tipo, String cedula, String apellidos, String nombres, Timestamp fechaRegistro, String estado) {
    this.setId(id);
    this.setTipo(tipo);
    this.setCedula(cedula);
    this.setApellidos(apellidos);
    this.setNombres(nombres);
    this.setFechaRegistro(fechaRegistro);
    this.setEstado(estado);
  }

  public Usuarios(String cedula, String clave) {
    this.setCedula(cedula);
    this.setClave(clave);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave.trim();
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos.toUpperCase().trim();
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres.toUpperCase().trim();
  }

  public Timestamp getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(Timestamp fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

}
