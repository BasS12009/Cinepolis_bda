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
    
    public Pelicula convertirAEntidadEd(PeliculaDTO peliculaDTO) throws SQLException, cinepolisException {

    long id=peliculaDTO.getId();
    String titulo = peliculaDTO.getTitulo();
    int idGenero = obtenerIdGenero(peliculaDTO.getGenero());
    String sinopsis = peliculaDTO.getSinopsis();
    int idClasificacion = obtenerIdClasificacion(peliculaDTO.getClasificacion());
    double duracion = peliculaDTO.getDuracion();
    String pais = peliculaDTO.getPais();
    String trailer = peliculaDTO.getTrailer();

    return new Pelicula( (int) id,titulo,  sinopsis,  trailer,  duracion,  pais,  idGenero,  idClasificacion);
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
    public PeliculaDTO convertirAEntidad(Pelicula pelicula) throws SQLException {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
            
            peliculaDTO.setTitulo(pelicula.getTitulo());
            peliculaDTO.setSinopsis(pelicula.getSinopsis());
            peliculaDTO.setTrailer(pelicula.getTrailer());
            peliculaDTO.setDuracion(pelicula.getDuracion());
            peliculaDTO.setPais(pelicula.getPais());

            try {
                String genero = obtenerTipoGeneroPorID(pelicula.getGenero());
                peliculaDTO.setGenero(genero);
            } catch (cinepolisException ex) {
                System.out.println("Error al obtener el tipo de género: " + ex.getMessage());
            }

            try {
                String clasificacion = obtenerTipoClasificacionPorID(pelicula.getClasificacion());
                peliculaDTO.setClasificacion(clasificacion);
            } catch (cinepolisException ex) {
                System.out.println("Error al obtener el tipo de clasificación: " + ex.getMessage());
            }

            return peliculaDTO;
            
    }
    
    
    public PeliculaDTO convertirAEntidadEd(Pelicula pelicula) throws SQLException {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setId((long) pelicula.getId());
            peliculaDTO.setId((long) pelicula.getId());
            peliculaDTO.setTitulo(pelicula.getTitulo());
            peliculaDTO.setSinopsis(pelicula.getSinopsis());
            peliculaDTO.setTrailer(pelicula.getTrailer());
            peliculaDTO.setDuracion(pelicula.getDuracion());
            peliculaDTO.setPais(pelicula.getPais());

            try {
                String genero = obtenerTipoGeneroPorID(pelicula.getGenero());
                peliculaDTO.setGenero(genero);
            } catch (cinepolisException ex) {
                System.out.println("Error al obtener el tipo de género: " + ex.getMessage());
            }

            try {
                String clasificacion = obtenerTipoClasificacionPorID(pelicula.getClasificacion());
                peliculaDTO.setClasificacion(clasificacion);
            } catch (cinepolisException ex) {
                System.out.println("Error al obtener el tipo de clasificación: " + ex.getMessage());
            }

            return peliculaDTO; 
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
    
    @Override
    public PeliculaDTO obtenerPeliculaPorID(long id) throws cinepolisException {
        try {

            PeliculaDTO pelicula = peliculaDAO.obtenerPeliculaPorId(id);

            if (pelicula == null) {
                throw new cinepolisException("No se encontró ningún cliente con el ID proporcionado.");
            }

            return pelicula;
        } catch (cinepolisException ex) {
            throw new cinepolisException("Error al obtener el cliente por ID.", ex);
        }
    }
    
    @Override
    public PeliculaDTO eliminarPelicula(long idPelicula) throws cinepolisException {
        PeliculaDTO pelicula=new PeliculaDTO();
        try {
            pelicula=convertirAEntidad(peliculaDAO.eliminarPeliculaPorID(idPelicula));
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new cinepolisException(ex.getMessage());
        }
        return pelicula;
    }
    
    public String obtenerTipoGeneroPorID(int id) throws cinepolisException{
        String tipo=generoDAO.buscarPorId(id).getNombre();
        return tipo;
    }
    
    public String obtenerTipoClasificacionPorID(int id) throws cinepolisException{
        String tipo=clasificacionDAO.buscarPorId(id).getNombre();
        return tipo;
    }
    
    @Override
    public List<PeliculaDTO> obtenerTodasLasPeliculasTablaDTO() throws cinepolisException {
        try {

            List<PeliculaDTO> peliculas = peliculaDAO.obtenerTodasLasPeliculas();


            if (peliculas == null || peliculas.isEmpty()) {
                throw new cinepolisException("No se encontraron peliculas en la base de datos.");
            }


            return peliculas;
        } catch (cinepolisException ex) {

            throw new cinepolisException("Error al obtener todas las peliculas.", ex);
        }
    }
    
    @Override
    public List<PeliculaDTO> buscarPeliculasTabla() throws cinepolisException {
        try{
           List<Pelicula> peliculas=this.peliculaDAO.buscarPeliculasTabla();
           return this.convertirPeliculaTablaDTO(peliculas);
        } catch(cinepolisException ex){

            System.out.println(ex.getMessage());
            throw new cinepolisException(ex.getMessage());
        }
    }
    
    private List<PeliculaDTO> convertirPeliculaTablaDTO(List<Pelicula> peliculas) throws cinepolisException {
        if (peliculas == null) {
            throw new cinepolisException("No se pudieron obtener los alumnos");
        }

        List<PeliculaDTO> PeliculasDTO = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setId((long) pelicula.getId());
            peliculaDTO.setTitulo(pelicula.getTitulo());
            peliculaDTO.setSinopsis(pelicula.getSinopsis());
            peliculaDTO.setTrailer(pelicula.getTrailer());
            peliculaDTO.setDuracion(pelicula.getDuracion());
            peliculaDTO.setPais(pelicula.getPais());
            peliculaDTO.setGenero(obtenerTipoGeneroPorID(pelicula.getGenero()));
            peliculaDTO.setClasificacion(obtenerTipoClasificacionPorID(pelicula.getClasificacion()));
            PeliculasDTO.add(peliculaDTO);
        }
        return PeliculasDTO;
     }
    
    private List<Pelicula> convertirPeliculaTabla(List<PeliculaDTO> peliculasDTO) throws cinepolisException {
        if (peliculasDTO == null) {
        throw new cinepolisException("No se pudieron obtener las películas");
        }

        List<Pelicula> peliculas = new ArrayList<>();
        for (PeliculaDTO peliculaDTO : peliculasDTO) {
            Pelicula pelicula = new Pelicula();
            pelicula.setId(peliculaDTO.getId().intValue());
            pelicula.setTitulo(peliculaDTO.getTitulo());
            pelicula.setSinopsis(peliculaDTO.getSinopsis());
            pelicula.setTrailer(peliculaDTO.getTrailer());
            pelicula.setDuracion(peliculaDTO.getDuracion());
            pelicula.setPais(peliculaDTO.getPais());
            try {
                int idGenero = obtenerIdGenero(peliculaDTO.getGenero());
                int idClasificacion = obtenerIdClasificacion(peliculaDTO.getClasificacion());
                pelicula.setGenero(idGenero);
                pelicula.setClasificacion(idClasificacion);
            } catch (SQLException ex) {
                throw new cinepolisException("Error al obtener el ID de género o clasificación.", ex);
            }
            peliculas.add(pelicula);
        }
        return peliculas;
    }
    
    @Override
    public PeliculaDTO editarPelicula(PeliculaDTO pelicula) throws cinepolisException {
        try {
            Pelicula peliculaEntidad = convertirAEntidadEd(pelicula);

            Pelicula peliculaEditado = peliculaDAO.editarPelicula(peliculaEntidad);

            return convertirAEntidad(peliculaEditado);
        } catch (SQLException ex) {
            throw new cinepolisException("Error al editar el cliente en la base de datos.", ex);
        }
    }
    
    public List<ClienteDTO> buscarClientes(String nombreFiltro, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws cinepolisException {
        try {
        List<Cliente> clientes = clienteDAO.buscarClientesConFiltros(nombreFiltro, fechaInicio, fechaFin);
        return convertirClienteTablaDTO(clientes);
        } catch (cinepolisException ex) {
            System.out.println(ex.getMessage());
            throw new cinepolisException(ex.getMessage());
        }
    }

    public List<PeliculaDTO> buscarPeliculas(String titulo, String genero, String clasificacion, String pais) throws cinepolisException, SQLException {
        try {
            System.out.println("CinepolisBO "+genero+" "+clasificacion);
            List<PeliculaDTO> peliculas = peliculaDAO.buscarPeliculasConFiltros(titulo, genero, clasificacion, pais);
            
            return peliculas;
        } catch (cinepolisException ex) {
            System.out.println(ex.getMessage());
            throw new cinepolisException(ex.getMessage());
        }
    }
}

