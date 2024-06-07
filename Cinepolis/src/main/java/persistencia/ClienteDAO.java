/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.IClienteDAO;
import Presentacion.Menu;
import entidades.Cliente;
import excepciones.cinepolisException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import persistencia.IConexionBD;

/**
 *
 * @author PC Gamer
 */
public class ClienteDAO implements IClienteDAO{
    private IConexionBD conexionBD;
    private Menu menu;
    
    public ClienteDAO(IConexionBD conexionBD){
        this.conexionBD=conexionBD;
    }

    @Override
    public Cliente insertarCliente(Cliente cliente) throws cinepolisException {
        Connection conexion = null;
        try{
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            
            String codigoSQL = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, correo, contrasena, ubicacion, fechaNacimiento FROM clientes "
                    + "WHERE nombre = ? AND apellidoPaterno = ? AND apellidoMaterno = ? AND correo = ? AND contrasena = ? AND ubicacion = ? AND fechaNacimiento = ?";
            PreparedStatement comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setString(1, cliente.getNombre());
            comandoSQL.setString(2, cliente.getApellidoPaterno());
            comandoSQL.setString(3, cliente.getApellidoMaterno());
            comandoSQL.setString(4, cliente.getCorreo());
            comandoSQL.setString(5, cliente.getContrasena());
            comandoSQL.setString(6, cliente.getUbicacion());
            comandoSQL.setDate(7, new Date(cliente.getFechaNacimiento().getTime()));
            

            ResultSet resultado = comandoSQL.executeQuery();
            if (resultado.next()) {
                conexion.rollback();
                throw new cinepolisException("La película ya existe");
            }
            
            codigoSQL = "INSERT INTO clientes (nombre, apellidoPaterno, apellidoMaterno, correo, contrasena, ubicacion, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertCommand = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            insertCommand.setString(1, cliente.getNombre());
            insertCommand.setString(2, cliente.getApellidoPaterno());
            insertCommand.setString(3, cliente.getApellidoMaterno());
            insertCommand.setString(4, cliente.getCorreo());
            insertCommand.setString(5, cliente.getContrasena());
            insertCommand.setString(6, cliente.getUbicacion());
            insertCommand.setDate(7, new Date(cliente.getFechaNacimiento().getTime()));
            
            insertCommand.executeUpdate();

            ResultSet generatedKeys = insertCommand.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1));
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
                throw new cinepolisException("Hubo un error al registrar el usuario",ex);
                
            }finally {
        
                if (conexion != null) {
                    try {
                        conexion.setAutoCommit(true);
                        conexion.close();
                    } catch (SQLException e) {
                        System.out.println("Error al cerrar la conexión: " + e.getMessage());
                        }
                    }
                 }
            return cliente;
            }
    
    
}