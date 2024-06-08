/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import DTOs.BoletoDTO;
import entidades.Boleto;
import excepciones.cinepolisException;
import java.awt.Menu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 
 */
public class BoletoDAO {
    
    private IConexionBD conexionBD;
    private Menu menu;
    
    public BoletoDAO(){
        this.conexionBD = new ConexionBD();
    }
    
    public BoletoDAO(IConexionBD conexionBD){
        this.conexionBD=conexionBD;
    }

    public Boleto insertarBoletoComprado(Boleto boleto) throws cinepolisException{
        Connection conexion = null;
        try{
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            
            String codigoSQL = "INSERT INTO boletos (costo, estado, fechaCompra, idFuncion, idCliente) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertCommand = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            insertCommand.setDouble(1, boleto.getCosto());
            insertCommand.setBoolean(2, boleto.isEstado());
            insertCommand.setDate(3, (Date) boleto.getFechaCompra());
            insertCommand.setLong(4, boleto.getFuncion().getId());
            insertCommand.setLong(5, boleto.getCliente().getId());
            
            insertCommand.executeUpdate();

            ResultSet generatedKeys = insertCommand.getGeneratedKeys();
            if (generatedKeys.next()) {
                boleto.setId(generatedKeys.getLong(1));
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
                throw new cinepolisException("Hubo un error al registrar el boleto",ex);
                
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
            return boleto;
    }
    
}
