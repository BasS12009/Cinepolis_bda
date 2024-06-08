/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 
 */
public class Boleto {
    
    private Long id;
    private double costo;
    private boolean estado;
    private Date fechaCompra;
    private Funcion funcion;
    private Cliente cliente;

    public Boleto() {
    }

    public Boleto(Long id, double costo, boolean estado, Date fechaCompra, Funcion funcion, Cliente cliente) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boleto(double costo, boolean estado, Date fechaCompra,  List<Cliente> clientes) {
        this.costo = costo;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    
    
    
    
}
