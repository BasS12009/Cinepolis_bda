/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;

/**
 *
 * @author 
 */
public class Sucursal {
    
    private Long id;
    private String nombre;
    private String ubicacion;
    List<Sala> salas;

    public Sucursal() {
    }

    public Sucursal(Long id, String nombre, String ubicacion, List<Sala> salas) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.salas = salas;
    }

    public Sucursal(String nombre, String ubicacion, List<Sala> salas) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.salas = salas;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
    
    
    
}
