/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import DTOs.PeliculaDTO;
import entidades.Pelicula;
import excepciones.cinepolisException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class PeliculaDAO implements IPeliculaDAO{
    private IConexionBD conexionBD;
    
    public PeliculaDAO(){
    }
    public PeliculaDAO(IConexionBD conexionBD){
        this.conexionBD=conexionBD;
    }
    //INSERTAR PELICULAS
    @Override
    public Pelicula insertarPelicula(Pelicula pelicula) throws cinepolisException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
    
            String codigoSQL = "SELECT idPelicula, titulo FROM peliculas WHERE titulo = ? AND idGenero = ? AND idClasificacion = ?";
            PreparedStatement comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setString(1,pelicula.getTitulo());
            comandoSQL.setInt(2, pelicula.getGenero());
            comandoSQL.setInt(3, pelicula.getClasificacion());

            ResultSet resultado = comandoSQL.executeQuery();
            if (resultado.next()) {
                conexion.rollback();
                throw new cinepolisException("La película ya existe");
            }

            codigoSQL = "INSERT INTO peliculas (titulo, sinopsis, trailer, duracion, pais, idGenero, idClasificacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertCommand = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            insertCommand.setString(1, pelicula.getTitulo());
            insertCommand.setString(2, pelicula.getSinopsis());
            insertCommand.setString(3, pelicula.getTrailer());
            insertCommand.setDouble(4, pelicula.getDuracion());
            insertCommand.setString(5, pelicula.getPais());
            insertCommand.setInt(6, pelicula.getGenero());
            insertCommand.setInt(7, pelicula.getClasificacion());

            insertCommand.executeUpdate();

            ResultSet generatedKeys = insertCommand.getGeneratedKeys();
            if (generatedKeys.next()) {
                pelicula.setId(generatedKeys.getInt(1));
            }

            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }

            System.out.println(ex.getMessage());
            throw new cinepolisException("Hubo un error al registrar la película", ex);
        } finally {
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return pelicula;
    }

    //EDITAR PELICULAS
    @Override
    public Pelicula editarPelicula(Pelicula pelicula) throws cinepolisException {
        String sql = "UPDATE peliculas SET titulo = ?, sinopsis = ?, trailer = ?, duracion = ?, pais = ?, idGenero = ?, idClasificacion = ? WHERE idPelicula = ?";
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setString(2, pelicula.getSinopsis());
            preparedStatement.setString(3, pelicula.getTrailer());
            preparedStatement.setDouble(4, pelicula.getDuracion());
            preparedStatement.setString(5, pelicula.getPais());
            preparedStatement.setInt(6, pelicula.getGenero());
            preparedStatement.setInt(7, pelicula.getClasificacion());
            preparedStatement.setLong(8, pelicula.getId());

            System.out.println("Ejecutando SQL: " + sql);
            System.out.println("Con valores: ");
            System.out.println("titulo = " + pelicula.getTitulo());
            System.out.println("sinopsis = " + pelicula.getSinopsis());
            System.out.println("trailer = " + pelicula.getTrailer());
            System.out.println("duracion = " + pelicula.getDuracion());
            System.out.println("pais = " + pelicula.getPais());
            System.out.println("idGenero = " + pelicula.getGenero());
            System.out.println("idClasificacion = " + pelicula.getClasificacion());
            System.out.println("idPelicula = " + pelicula.getId());
            
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                conexion.rollback();
                throw new cinepolisException("No se encontró la película con el ID especificado");
            }

            conexion.commit();
            return pelicula;
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            throw new cinepolisException("Error al actualizar la película: " + ex.getMessage(), ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    //ELIMINAR PELICULAS
    @Override
    public Pelicula eliminarPeliculaPorID(Long idPelicula) throws cinepolisException {
        String sqlSelect = "SELECT * FROM peliculas WHERE idPelicula = ?";
        String sqlDelete = "DELETE FROM peliculas WHERE idPelicula = ?";
        Connection conexion = null;
        PreparedStatement selectStatement = null;
        PreparedStatement deleteStatement = null;
        Pelicula pelicula = null;

        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            selectStatement = conexion.prepareStatement(sqlSelect);
            selectStatement.setLong(1, idPelicula);
            ResultSet resultado = selectStatement.executeQuery();

            if (resultado.next()) {
                pelicula = new Pelicula();
                pelicula.setId(resultado.getInt("idPelicula"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setTrailer(resultado.getString("trailer"));
                pelicula.setDuracion(resultado.getDouble("duracion"));
                pelicula.setPais(resultado.getString("pais"));
                pelicula.setGenero(resultado.getInt("idGenero"));
                pelicula.setClasificacion(resultado.getInt("idClasificacion"));
            } else {
                conexion.rollback();
                throw new cinepolisException("No se encontró la película con el ID especificado");
            }

            deleteStatement = conexion.prepareStatement(sqlDelete);
            deleteStatement.setLong(1, idPelicula);
            deleteStatement.executeUpdate();

            conexion.commit();
            return pelicula;
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            throw new cinepolisException("Hubo un error al eliminar la película: " + ex.getMessage(), ex);
        } finally {
            if (selectStatement != null) {
                try {
                    selectStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el selectStatement: " + e.getMessage());
                }
            }
            if (deleteStatement != null) {
                try {
                    deleteStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el deleteStatement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    
    
    //OBTENER PELICULA POR ID
    @Override
    public PeliculaDTO obtenerPeliculaPorId(long id) throws cinepolisException {
        Connection conexion = null;
        PreparedStatement comandoSQL = null;
        PeliculaDTO pelicula = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idPelicula, titulo, sinopsis, trailer, duracion, pais, idGenero,idClasificacion FROM peliculas WHERE idPelicula = ?";
            comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setLong(1, id);
            ResultSet resultado = comandoSQL.executeQuery();

            if (resultado.next()) {
                pelicula = new PeliculaDTO();
                pelicula.setId(resultado.getLong("idPelicula"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setTrailer(resultado.getString("trailer"));
                pelicula.setDuracion(resultado.getDouble(("duracion")));
                pelicula.setPais(resultado.getString("pais"));
                
                long idGenero = resultado.getLong("idGenero");
                String tipoGenero = obtenerTipoGeneroPorID(idGenero);
                pelicula.setGenero(tipoGenero);

                long idClasificacion = resultado.getLong("idClasificacion");
                String tipoClasificacion = obtenerTipoClasificacionPorID(idClasificacion);
                pelicula.setClasificacion(tipoClasificacion);
            }

            return pelicula;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new cinepolisException("Ocurrió un error al buscar el cliente por ID", ex);
        } finally {
            if (comandoSQL != null) {
                try {
                    comandoSQL.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    
    //OBTENER IDS
    private String obtenerTipoGeneroPorID(long idGenero) throws cinepolisException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String tipoGenero = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String query = "SELECT tipo FROM genero WHERE idGenero = ?";
            statement = conexion.prepareStatement(query);
            statement.setLong(1, idGenero);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoGenero = resultSet.getString("tipo");
            }
        } catch (SQLException e) {
            throw new cinepolisException("Error al obtener el tipo de género por ID: " + e.getMessage(), e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }

        return tipoGenero;
    }
    private String obtenerTipoClasificacionPorID(long idClasificacion) throws cinepolisException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String tipoClasificacion = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String query = "SELECT tipo FROM clasificacion WHERE idClasificacion = ?";
            statement = conexion.prepareStatement(query);
            statement.setLong(1, idClasificacion);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoClasificacion = resultSet.getString("tipo");
            }
        } catch (SQLException e) {
            throw new cinepolisException("Error al obtener el tipo de clasificación por ID: " + e.getMessage(), e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }

        return tipoClasificacion;
    }
    
    @Override
    public List<PeliculaDTO> obtenerTodasLasPeliculas() throws cinepolisException {
        try {
            List<PeliculaDTO> peliculasLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idPelicula, titulo, sinopsis, trailer, duracion, pais, idGenero,idClasificacion FROM peliculas";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                PeliculaDTO pelicula = new PeliculaDTO();
                pelicula.setId(resultado.getLong("idPelicula"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setTrailer(resultado.getString("trailer"));
                pelicula.setDuracion(resultado.getDouble("duracion"));
                pelicula.setPais(resultado.getString("pais"));
                pelicula.setGenero(obtenerTipoGeneroPorID(resultado.getInt("idGenero")));
                pelicula.setClasificacion(obtenerTipoClasificacionPorID(resultado.getInt("idClasificacion")));
                peliculasLista.add(pelicula);
            }

            conexion.close();
            return peliculasLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new cinepolisException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste", ex);
        }
    }
    
    public List<Pelicula> buscarPeliculasTabla() throws cinepolisException {
        try{
            List<Pelicula> pelicuasLista=null;
            Connection conexion=this.conexionBD.crearConexion();
            String codigoSQL= "SELECT idPelicula, titulo, sinopsis, trailer, duracion, pais, idGenero,idClasificacion FROM peliculas";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado=comandoSQL.executeQuery(codigoSQL);
            while(resultado.next()){
                if(pelicuasLista==null){
                    pelicuasLista=new ArrayList<>();
                }
                Pelicula pelicula = new Pelicula();
                pelicula=pelicula.convertirAEntidad(resultado);
                pelicuasLista.add(pelicula);
            }
            conexion.close();
            return pelicuasLista;
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            throw new cinepolisException("Ocurrio un error al leer la base de datos, intentelo de nuevo y si el error persiste");
        }
    }
}
