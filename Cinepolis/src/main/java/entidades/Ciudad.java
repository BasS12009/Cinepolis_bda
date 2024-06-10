/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author 
 */
public class Ciudad {
    
    private Long id;
    private String nombre;
    private String locaclizacion;

    public Ciudad() {
    }

    public Ciudad(String nombre, String locaclizacion) {
        this.nombre = nombre;
        this.locaclizacion = locaclizacion;
    }

    public Ciudad(Long id, String nombre, String locaclizacion) {
        this.id = id;
        this.nombre = nombre;
        this.locaclizacion = locaclizacion;
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

    public String getLocaclizacion() {
        return locaclizacion;
    }

    public void setLocaclizacion(String locaclizacion) {
        this.locaclizacion = locaclizacion;
    }
    
    
    
}
