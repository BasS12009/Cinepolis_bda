/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import entidades.Cliente;
import java.sql.SQLException;

/**
 *
 * @author 
 */
public interface ICinepolisBO {
    
    public ClienteDTO registro(ClienteDTO cliente);
    
    public ClienteDTO login(ClienteDTO cliente);
    
    public Cliente convertirAEntidad(ClienteDTO cliente) throws SQLException ;
    
    public ClienteDTO convertirAEntidad(Cliente cliente) throws SQLException;
}
