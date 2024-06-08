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
    
    private Long id;
    private Date fecha;
    private double horaInicio;
    private List<Pelicula> peliculas;

    public Funcion() {
    }

    public Funcion(Long id, Date fecha, double horaInicio, List<Pelicula> peliculas) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.peliculas = peliculas;
    }

    public Funcion(Date fecha, double horaInicio, List<Pelicula> peliculas) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.peliculas = peliculas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    public Funcion convertirAEntidad(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("idPelicula");
        String titulo = resultado.getString("titulo");
        String sinopsis = resultado.getString("sinopsis");
        String trailer = resultado.getString("trailer");
        Double duracion = resultado.getDouble("duracion");
        String pais = resultado.getString("pais");
        int idGenero = resultado.getInt("idGenero");
        int idClasificacion = resultado.getInt("idClasificacion");
        

        return new Pelicula(id, titulo, sinopsis, trailer, duracion, pais, idGenero, idClasificacion);
    }
    

}
