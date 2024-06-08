/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Funcion;
import entidades.Pelicula;
import excepciones.cinepolisException;
import java.awt.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC Gamer
 */
public class FuncionDAO implements IFuncionDAO{
    private IConexionBD conexionBD;
    private Menu menu;
    
    public FuncionDAO(){
         this.conexionBD = new ConexionBD();
    }
    
    //INSERTAR FUNCION
    @Override
    public Funcion insertarFuncion(Funcion funcion) throws cinepolisException {
        String sql = "INSERT INTO funciones (fecha, horaInicio, idpeliculas) VALUES (?, ?, ?)";
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(funcion.getFecha().getTime()));
            preparedStatement.setDouble(2, funcion.getHoraInicio());
            preparedStatement.setInt(3, funcion.getPeliculas().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                funcion.setId(generatedKeys.getInt(1));
            }

            conexion.commit();
            return funcion;
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            throw new cinepolisException("Hubo un error al registrar la función: " + ex.getMessage(), ex);
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

    //EDITAR FUNCION
    @Override
    public Funcion editarFuncion(Funcion funcion) throws cinepolisException {
        String sql = "UPDATE funciones SET fecha = ?, horaInicio = ?, idpeliculas = ? WHERE idFuncion = ?";
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(funcion.getFecha().getTime()));
            preparedStatement.setDouble(2, funcion.getHoraInicio());
            preparedStatement.setInt(3, funcion.getPeliculas().getId());
            preparedStatement.setInt(4, funcion.getId());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                conexion.rollback();
                throw new cinepolisException("No se encontró la función con el ID especificado");
            }

            conexion.commit();
            return funcion;
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            throw new cinepolisException("Hubo un error al actualizar la función: " + ex.getMessage(), ex);
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

    //ELIMINAR FUNCION
    @Override
    public Funcion eliminarFuncionPorId(int idFuncion) throws cinepolisException {
        String sqlSelect = "SELECT * FROM funciones WHERE idFuncion = ?";
        String sqlDelete = "DELETE FROM funciones WHERE idFuncion = ?";
        Connection conexion = null;
        PreparedStatement selectStatement = null;
        PreparedStatement deleteStatement = null;
        Funcion funcion = null;

        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            selectStatement = conexion.prepareStatement(sqlSelect);
            selectStatement.setInt(1, idFuncion);
            ResultSet resultado = selectStatement.executeQuery();

            if (resultado.next()) {
                funcion = new Funcion();
                funcion.setId(resultado.getInt("idFuncion"));
                funcion.setFecha(resultado.getDate("fecha"));
                funcion.setHoraInicio(resultado.getDouble("horaInicio"));
                funcion.setPeliculas(new Pelicula(resultado.getInt("idpeliculas")));
            } else {
                conexion.rollback();
                throw new cinepolisException("No se encontró la función con el ID especificado");
            }
            
            deleteStatement = conexion.prepareStatement(sqlDelete);
            deleteStatement.setInt(1, idFuncion);
            deleteStatement.executeUpdate();

            conexion.commit(); 
            return funcion;
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
                }
            }
            throw new cinepolisException("Hubo un error al eliminar la función: " + ex.getMessage(), ex);
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
