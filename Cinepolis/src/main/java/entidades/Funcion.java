/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 
 */
public class Funcion {
    
    private int id;
    private Date fecha;
    private double horaInicio;
    private Pelicula pelicula;

    public Funcion() {
    }

    public Funcion(int id, Date fecha, double horaInicio, Pelicula pelicula) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.pelicula = pelicula;
    }

    public Funcion(Date fecha, double horaInicio, Pelicula pelicula) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.pelicula = pelicula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(double horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Pelicula getPeliculas() {
        return pelicula;
    }

    public void setPeliculas(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    public Funcion convertirAEntidad(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("idPelicula");
        Date fecha = resultado.getDate("fecha");
        double horaInicio = resultado.getDouble("horaiInicio");
        Pelicula pelicula = new Pelicula().convertirAEntidad(resultado);
  

        return new Funcion(id, fecha, horaInicio, pelicula);
    }
    

}
