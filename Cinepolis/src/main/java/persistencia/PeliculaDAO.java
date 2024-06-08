/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Pelicula;
import excepciones.cinepolisException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC Gamer
 */
public class PeliculaDAO implements IPeliculaDAO{
    private IConexionBD conexionBD;
    
    public PeliculaDAO(){
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
            preparedStatement.setInt(8, pelicula.getId());

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
    public Pelicula eliminarPeliculaPorID(int idPelicula) throws cinepolisException {
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
            selectStatement.setInt(1, idPelicula);
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
            deleteStatement.setInt(1, idPelicula);
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
    
    
}
