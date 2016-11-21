package GENERAL;

import java.sql.Date;
import java.sql.Timestamp;

public class Alumnos {

    private int id;
    private String dispositivo;
    private Timestamp fecha_ingreso;
    private String institucion;
    private String apellidos;
    private String nombres;
    private String direccion;
    private String conductor;
    private int expreso;
    private String encargado;
    private String estado;
    private String ubicacion;

    private int id_usuario;
    private String u_cedula;
    private String u_nombre;
    private String u_apellido;

    public Alumnos(int id, String nombres, String apellidos, String direccion,
            String u_cedula, String u_nombre, String u_apellido,
            Timestamp fecha_ingreso, String estado) {
        this.setId(id);
        this.setNombres(nombres);
        this.setApellidos(apellidos);
        this.setDireccion(direccion);
        this.setU_cedula(u_cedula);
        this.setU_nombre(u_nombre);
        this.setU_apellido(u_apellido);
        this.setFecha_ingreso(fecha_ingreso);
        this.setEstado(estado);
    }

    public Alumnos(int id, String nombres, String apellidos, String direccion,
            String u_cedula, String u_nombre, String u_apellido,
            Timestamp fecha_ingreso, String estado, String ubicacion) {
        this.setId(id);
        this.setNombres(nombres);
        this.setApellidos(apellidos);
        this.setDireccion(direccion);
        this.setU_cedula(u_cedula);
        this.setU_nombre(u_nombre);
        this.setU_apellido(u_apellido);
        this.setFecha_ingreso(fecha_ingreso);
        this.setEstado(estado);
        this.setUbicacion(ubicacion);
    }

    public Alumnos(int id, String dispositivo, Timestamp fecha_ingreso,
            String institucion, String apellidos, String nombres,
            String direccion, String conductor, int expreso, String encargado,
            String estado, int id_usuario, String u_cedula, String u_nombre,
            String u_apellido) {
        this.setId(id);
        this.setDispositivo(dispositivo);
        this.setFecha_ingreso(fecha_ingreso);
        this.setInstitucion(institucion);
        this.setApellidos(apellidos);
        this.setNombres(nombres);
        this.setDireccion(direccion);
        this.setConductor(conductor);
        this.setExpreso(expreso);
        this.setEncargado(encargado);
        this.setEstado(estado);

        this.setId_usuario(id_usuario);
        this.setU_cedula(u_cedula);
        this.setU_nombre(u_nombre);
        this.setU_apellido(u_apellido);
    }

    public Alumnos(int id, String dispositivo, String institucion,
            String apellidos, String nombres, String direccion, String conductor,
            int expreso, String encargado, String estado, int id_usuario) {
        this.setId(id);
        this.setDispositivo(dispositivo);
        this.setInstitucion(institucion);
        this.setApellidos(apellidos);
        this.setNombres(nombres);
        this.setDireccion(direccion);
        this.setConductor(conductor);
        this.setExpreso(expreso);
        this.setEncargado(encargado);
        this.setEstado(estado);
        this.setId_usuario(id_usuario);
    }

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the fecha_ingreso
     */
    public Timestamp getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Timestamp fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the institucion
     */
    public String getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the conductor
     */
    public String getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    /**
     * @return the expreso
     */
    public int getExpreso() {
        return expreso;
    }

    /**
     * @param expreso the expreso to set
     */
    public void setExpreso(int expreso) {
        this.expreso = expreso;
    }

    /**
     * @return the encargado
     */
    public String getEncargado() {
        return encargado;
    }

    /**
     * @param encargado the encargado to set
     */
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cedula
     */
    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getU_Cedula() {
        return u_cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setU_Cedula(String u_cedula) {
        this.u_cedula = u_cedula;
    }

    /**
     * @return the u_cedula
     */
    public String getU_cedula() {
        return u_cedula;
    }

    /**
     * @param u_cedula the u_cedula to set
     */
    public void setU_cedula(String u_cedula) {
        this.u_cedula = u_cedula;
    }

    public String getU_nombre() {
        return u_nombre;
    }

    public void setU_nombre(String u_nombre) {
        this.u_nombre = u_nombre;
    }

    public String getU_apellido() {
        return u_apellido;
    }

    public void setU_apellido(String u_apellido) {
        this.u_apellido = u_apellido;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

}
