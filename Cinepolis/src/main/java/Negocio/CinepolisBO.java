/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.ClasificacionDTO;
import DTOs.ClienteDTO;
import DTOs.GeneroDTO;
import DTOs.PeliculaDTO;
import entidades.Clasificacion;
import entidades.Cliente;
import entidades.Genero;
import entidades.Pelicula;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ClasificacionDAO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.GeneroDAO;
import persistencia.PeliculaDAO;

/**
 *
 * @author 
 */
public class CinepolisBO implements ICinepolisBO{
    
    ClienteDAO clienteDAO;
    PeliculaDAO peliculaDAO;
    ClasificacionDAO clasificacionDAO;
    GeneroDAO generoDAO;
            
    public CinepolisBO(ClienteDAO clienteDAO){
        this.clienteDAO=clienteDAO;
        this.peliculaDAO=new PeliculaDAO(clienteDAO.getConexion());
        this.clasificacionDAO=new ClasificacionDAO(clienteDAO.getConexion());
        this.generoDAO=new GeneroDAO(clienteDAO.getConexion());
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
    
    public Pelicula convertirAEntidad(PeliculaDTO peliculaDTO) throws SQLException, cinepolisException {

    String titulo = peliculaDTO.getTitulo();
    int idGenero = obtenerIdGenero(peliculaDTO.getGenero());
    String sinopsis = peliculaDTO.getSinopsis();
    int idClasificacion = obtenerIdClasificacion(peliculaDTO.getClasificacion());
    double duracion = peliculaDTO.getDuracion();
    String pais = peliculaDTO.getPais();
    String trailer = peliculaDTO.getTrailer();

    return new Pelicula( titulo,  sinopsis,  trailer,  duracion,  pais,  idGenero,  idClasificacion);
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
    
    
    public void agregarPelicula(PeliculaDTO pelicula) throws SQLException, cinepolisException {
        try{
            peliculaDAO.insertarPelicula(convertirAEntidad(pelicula));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public int obtenerIdGenero(String generoSeleccionado) throws SQLException, cinepolisException {
        
        long id=generoDAO.obtenerIdPorNombre(generoSeleccionado);
        return (int) id;
        
    }

    public int obtenerIdClasificacion(String clasificacionSeleccionada) throws SQLException, cinepolisException {
        
        long id=clasificacionDAO.obtenerIdPorNombre(clasificacionSeleccionada);
        return (int) id;
    }

    @Override
    public void agregarCliente(ClienteDTO cliente) throws SQLException, cinepolisException {
        try{
            clienteDAO.insertarCliente(this.convertirAEntidad(cliente));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    
    
    @Override
    public List<ClasificacionDTO> obtenerTodasLasClasificaciones(){
        
    List<ClasificacionDTO> clasificacionesDTO = new ArrayList<>();
    List<Clasificacion> clasificaciones = clasificacionDAO.obtenerTodos();
    for (Clasificacion clasificacion : clasificaciones) {
        ClasificacionDTO clasificacionDTO = new ClasificacionDTO();
        clasificacionDTO.setId(clasificacion.getId());
        clasificacionDTO.setTipo(clasificacion.getNombre());
        clasificacionesDTO.add(clasificacionDTO);
    }
        return clasificacionesDTO;
    }

    @Override
    public List<GeneroDTO> obtenerTodosLosGeneros(){
        
        try {
            List<GeneroDTO> generosDTO = new ArrayList<>();
            List<Genero> generos = generoDAO.obtenerTodos();
            for (Genero genero : generos) {
                GeneroDTO generoDTO = new GeneroDTO();
                generoDTO.setId(genero.getId());
                generoDTO.setTipo(genero.getNombre());
                generosDTO.add(generoDTO);
            }
            return generosDTO;
        } catch (cinepolisException ex) {
            System.out.println(ex.getMessage());
           return null;
        }
    }
    
}

