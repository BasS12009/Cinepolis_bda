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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @Override
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
    
    
    @Override
    public List<ClienteDTO> buscarClientesTabla() throws cinepolisException {
        try{
           List<Cliente> clientes=this.clienteDAO.buscarClientesTabla();
           return this.convertirClienteTablaDTO(clientes);
        } catch(cinepolisException ex){

            System.out.println(ex.getMessage());
            throw new cinepolisException(ex.getMessage());
        }
    }

     private List<ClienteDTO> convertirClienteTablaDTO(List<Cliente> clientes) throws cinepolisException {
        if (clientes == null) {
            throw new cinepolisException("No se pudieron obtener los alumnos");
        }

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        for (Cliente cliente : clientes) {
            ClienteDTO dto = new ClienteDTO();
            dto.setId(cliente.getId());
            dto.setNombre(cliente.getNombre());
            dto.setApellidoPaterno(cliente.getApellidoPaterno());
            dto.setApellidoMaterno(cliente.getApellidoMaterno());
            dto.setCorreo(cliente.getCorreo());
            dto.setContrasena(cliente.getCorreo());
            dto.setUbicacion(cliente.getUbicacion());
            dto.setFechaNacimiento(cliente.getFechaNacimiento());
            clientesDTO.add(dto);
        }
        return clientesDTO;
     }

    @Override
    public ClienteDTO obtenerClientePorID(long id) throws cinepolisException {
        try {

            ClienteDTO cliente = clienteDAO.obtenerClientePorID(id);

            if (cliente == null) {
                throw new cinepolisException("No se encontró ningún cliente con el ID proporcionado.");
            }

            return cliente;
        } catch (cinepolisException ex) {
            throw new cinepolisException("Error al obtener el cliente por ID.", ex);
        }
    }

    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() throws cinepolisException {
        try {

            List<ClienteDTO> clientes = clienteDAO.obtenerTodosLosClientes();


            if (clientes == null || clientes.isEmpty()) {
                throw new cinepolisException("No se encontraron clientes en la base de datos.");
            }


            return clientes;
        } catch (cinepolisException ex) {

            throw new cinepolisException("Error al obtener todos los clientes.", ex);
        }
    }

    @Override
    public ClienteDTO editarCliente(ClienteDTO cliente) throws cinepolisException {
        try {
            Cliente clienteEntidad = convertirAEntidad(cliente);

            Cliente clienteEditado = clienteDAO.editarCliente(clienteEntidad);

            return convertirAEntidad(clienteEditado);
        } catch (SQLException ex) {
            throw new cinepolisException("Error al editar el cliente en la base de datos.", ex);
        }
    }
    
    @Override
    public ClienteDTO eliminarCliente(long idCliente) throws cinepolisException {
        try {
            ClienteDTO cliente=new ClienteDTO();
            cliente=convertirAEntidad(clienteDAO.eliminarClientePorID(idCliente));
            return cliente;
        } catch (SQLException ex) {
            throw new cinepolisException("Error al eliminar el cliente en la base de datos.", ex);
        }
    }
}
