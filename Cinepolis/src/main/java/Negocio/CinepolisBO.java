/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.ClienteDTO;
import entidades.Cliente;
import excepciones.cinepolisException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ClienteDAO;

/**
 *
 * @author 
 */
public class CinepolisBO implements ICinepolisBO{
    
    ClienteDAO clienteDAO;

    public CinepolisBO(ClienteDAO clienteDAO){
        this.clienteDAO=clienteDAO;
    }
    
    @Override
    public ClienteDTO registro(ClienteDTO cliente) {
        Cliente clienteAuxiliar = null;
        try {
            clienteAuxiliar = convertirAEntidad(cliente);
            
            return convertirAEntidad(clienteDAO.insertarCliente(clienteAuxiliar));
            
        } catch (SQLException ex) {
            Logger.getLogger(CinepolisBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (cinepolisException ex) {
            Logger.getLogger(CinepolisBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return null;
    }

    @Override
    public ClienteDTO login(ClienteDTO cliente) {

        Cliente clienteAuxiliar = null;
        try {
            clienteAuxiliar = convertirAEntidad(cliente);
            
            return convertirAEntidad(clienteDAO.login(clienteAuxiliar));
            
        } catch (SQLException ex) {
            Logger.getLogger(CinepolisBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (cinepolisException ex) {
            Logger.getLogger(CinepolisBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return null;
        

    }
    
    public Cliente convertirAEntidad(ClienteDTO cliente) throws SQLException {

        Long id = cliente.getId();
        String nombre = cliente.getNombre();
        String paterno = cliente.getApellidoPaterno();
        String materno = cliente.getApellidoMaterno();
        String correo = cliente.getCorreo();
        String contrasena = cliente.getContrasena();
        String ubicacion = cliente.getUbicacion();
        Date fechaN = cliente.getFechaNacimiento();

        return new Cliente(id, nombre, paterno, materno, correo, contrasena, ubicacion, fechaN);
    }
    
    public ClienteDTO convertirAEntidad(Cliente cliente) throws SQLException {

        Long id = cliente.getId();
        String nombre = cliente.getNombre();
        String paterno = cliente.getApellidoPaterno();
        String materno = cliente.getApellidoMaterno();
        String correo = cliente.getCorreo();
        String contrasena = cliente.getContrasena();
        String ubicacion = cliente.getUbicacion();
        Date fechaN = cliente.getFechaNacimiento();

        return new ClienteDTO(id, nombre, paterno, materno, correo, contrasena, ubicacion, fechaN);
    }
    
    

    
}
