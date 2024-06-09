/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author PC Gamer
 */
import DTOs.ClienteDTO;
import entidades.Cliente;
import excepciones.cinepolisException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IClienteDAO {
    
    public Cliente insertarCliente(Cliente cliente) throws cinepolisException;
    
    public Cliente login(Cliente cliente) throws cinepolisException;
    
    public Cliente editarCliente(Cliente cliente) throws cinepolisException;
    
    public Cliente eliminarClientePorID(int idCliente) throws cinepolisException;
    
    public List<Cliente> buscarClientesTabla() throws cinepolisException;
    
    public List<ClienteDTO> obtenerTodosLosClientes() throws cinepolisException;
     
    public ClienteDTO obtenerClientePorID(long id) throws cinepolisException;
}
