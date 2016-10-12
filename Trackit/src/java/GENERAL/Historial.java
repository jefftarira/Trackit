package GENERAL;

import java.sql.Date;
import java.sql.Timestamp;

public class Historial {

  private int id;
  private int id_alumno;
  private int id_estado;
  private String descripcion;
  private Timestamp fecha;
  
  public Historial(int id_alumno, int id_estado, String descripcion, Timestamp fecha) {
    this.setId_alumno(id_alumno);
    this.setId_estado(id_estado);
    this.setDescripcion(descripcion);
    this.setFecha(fecha);    
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId_alumno() {
    return id_alumno;
  }

  public void setId_alumno(int id_alumno) {
    this.id_alumno = id_alumno;
  }

  public int getId_estado() {
    return id_estado;
  }

  public void setId_estado(int id_estado) {
    this.id_estado = id_estado;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Timestamp getFecha() {
    return fecha;
  }

  public void setFecha(Timestamp fecha) {
    this.fecha = fecha;
  }
}
