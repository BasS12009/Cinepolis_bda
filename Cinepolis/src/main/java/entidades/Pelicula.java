/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author 
 */
public class Pelicula {
    
    private Long id;
    private String titulo;
    private String genero;
    private String sinopsis;
    private String trailer;
    private Double duracion;
    private String pais;
    private String clasificacion;

    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, String genero, String sinopsis, String trailer, Double duracion, String pais, String clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
        this.duracion = duracion;
        this.pais = pais;
        this.clasificacion = clasificacion;
    }

    public Pelicula(String titulo, String genero, String sinopsis, String trailer, Double duracion, String pais, String clasificacion) {
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
        this.duracion = duracion;
        this.pais = pais;
        this.clasificacion = clasificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    
    
}
