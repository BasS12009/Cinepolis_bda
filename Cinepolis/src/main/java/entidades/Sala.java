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
public class Sala {
    
    private Long id;
    private int numero;
    private List<Funcion> funciones;

    public Sala() {
    }

    public Sala(Long id, int numero, List<Funcion> funciones) {
        this.id = id;
        this.numero = numero;
        this.funciones = funciones;
    }

    public Sala(int numero, List<Funcion> funciones) {
        this.numero = numero;
        this.funciones = funciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
    
    
}
