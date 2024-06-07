/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author PC Gamer
 */
import entidades.Cliente;
import excepciones.cinepolisException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC Gamer
 */
public interface IClienteDAO {
    
    public Cliente insertarCliente(Cliente cliente) throws cinepolisException;
    
}
