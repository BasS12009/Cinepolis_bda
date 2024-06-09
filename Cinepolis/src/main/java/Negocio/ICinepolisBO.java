/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import entidades.Cliente;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 
 */
public interface ICinepolisBO {
    
    public ClienteDTO registro(ClienteDTO cliente);
    
    public ClienteDTO login(ClienteDTO cliente);
    
    public Cliente convertirAEntidad(ClienteDTO cliente) throws SQLException ;
    
    public ClienteDTO convertirAEntidad(Cliente cliente) throws SQLException;
    
    public List<ClienteDTO> buscarClientesTabla() throws cinepolisException;
    
    public ClienteDTO obtenerClientePorID(long id) throws cinepolisException;
    
    public List<ClienteDTO> obtenerTodosLosClientes() throws cinepolisException;
    
    public ClienteDTO editarCliente(ClienteDTO cliente) throws cinepolisException;
    
     public ClienteDTO eliminarCliente(long idCliente) throws cinepolisException ;
}
