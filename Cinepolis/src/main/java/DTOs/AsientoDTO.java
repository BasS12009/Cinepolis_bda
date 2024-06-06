/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author diana
 */
public class AsientoDTO {
    //Atributos 
    private Long id;
    private boolean estatus;
    private String numeracion;
    private List<FuncionDTO> funciones;

    //Constructor vacio
    public AsientoDTO() {
    }

    //Constructor con atributos
    public AsientoDTO(Long id, boolean estatus, String numeracion, List<FuncionDTO> funciones) {
        this.id = id;
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

    public List<FuncionDTO> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<FuncionDTO> funciones) {
        this.funciones = funciones;
    }
    
    
    
}
