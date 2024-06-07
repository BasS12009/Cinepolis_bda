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

    @Override
    public Pelicula insertarPelicula(Pelicula pelicula) throws cinepolisException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            // Consulta para verificar si la película ya existe
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

            // Insertar la nueva película
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
    
    
}
