/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Date;
import java.util.List;

/**
 *
 * @author diana
 */
public class BoletoDTO {
    
    //Atributos
    private Long id;
    private double costo;
    private boolean estado;
    private Date fechaCompra;
    private List<ClienteDTO> clientes;
    
    //Constructor vacio
    public BoletoDTO() {
    }
    
    //Constructor con atributos inicializados

    public BoletoDTO(Long id, double costo, boolean estado, Date fechaCompra, List<ClienteDTO> clientes) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.clientes = clientes;
    }
    
    
}
