/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC Gamer
 */
public class ConexionBD implements IConexionBD {
    
    final String SERVER = "SG-Cinepolis-equipo-1-8696-mysql-master.servers.mongodirector.com"; // Servidor de la base de datos
    final String BASE_DATOS = "cinepolis"; // Nombre de la base de datos
    final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS; // URL de conexión a la base de datos
    final String USUARIO = "sgroot"; // Usuario de la base de datos
    final String CONTRASEÑA ="CZHEl@zTU5Bnpg5K" ;
    private Connection conexion;
    
    public ConexionBD() throws SQLException {
        this.conexion=crearConexion();
    }
    
    @Override
    public Connection crearConexion() throws SQLException {
            try {
                conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
            } catch (SQLException e) {
                // Log the exception or handle it according to your needs
                throw new SQLException("Error al crear la conexión a la base de datos", e);
        }
        return conexion;
    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            return crearConexion();
        }
        return conexion;
    }
}
