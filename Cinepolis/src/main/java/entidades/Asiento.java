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
public class Asiento {
    
    private Long id;
    private boolean estatus;
    private String numeracion;
    private List<Funcion> funciones;

    public Asiento() {
    }

    public Asiento(Long id, boolean estatus, String numeracion, List<Funcion> funciones) {
        this.id = id;
        this.estatus = estatus;
        this.numeracion = numeracion;
        this.funciones = funciones;
    }

    public Asiento(boolean estatus, String numeracion, List<Funcion> funciones) {
        this.estatus = estatus;
        this.numeracion = numeracion;
        this.funciones = funciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
    
    
    
}
