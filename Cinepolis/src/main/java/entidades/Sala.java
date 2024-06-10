/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
                      

/**
 *
 * @author stae
 */
public class Sala {
    
    private Long id;
    private int numero;
    private Funcion funcion ;

    public Sala() {
    }

    public Sala(Long id, int numero, Funcion funcion) {
        this.id = id;
        this.numero = numero;
        this.funcion = funcion;
    }

    public Sala(int numero, Funcion funcion) {
        this.numero = numero;
        this.funcion = funcion;
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

    public Funcion getFunciones() {
        return funcion;
    }

    public void setFunciones(Funcion funcion) {
        this.funcion = funcion;
    }
    
    public Sala convertirAEntidad(ResultSet resultado) throws SQLException {

        Long id = resultado.getLong("idSalas");
        int numero = resultado.getInt("numero");
        Funcion funcion = new Funcion().convertirAEntidad(resultado);
        

        return new Sala(id, numero, funcion);
    }
    

    
}
