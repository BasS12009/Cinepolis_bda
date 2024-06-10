/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import com.itextpdf.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author stae
 */
public class Cliente {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasena;
    private String ubicacion;
    private Date fechaNacimiento;
    private Point2D.Double coordenadas;
    
    public Cliente() {

    }
    public Cliente(Point2D.Double coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Point2D.Double getCoordenadas() {
        return coordenadas;
    }

    public Cliente(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena, String ubicacion, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasena = contrasena;
        this.ubicacion = ubicacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena, String ubicacion, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasena = contrasena;
        this.ubicacion = ubicacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente convertirAEntidad(ResultSet resultado) throws SQLException {

        Long id = resultado.getLong("idCliente");
        String nombre = resultado.getString("nombre");
        String paterno = resultado.getString("apellidoPaterno");
        String materno = resultado.getString("apellidoMaterno");
        String correo = resultado.getString("correo");
        String contrasena = resultado.getString("contrasena");
        String ubicacion = resultado.getString("ubicacion");
        Date fechaN = resultado.getDate("fechaNacimiento");

        return new Cliente(id, nombre, paterno, materno, correo, contrasena, ubicacion, fechaN);
    }

    
    
}
