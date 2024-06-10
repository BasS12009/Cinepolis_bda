/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


import entidades.Clasificacion;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IClasificacionDAO{
    
    Clasificacion buscarPorId(long id) throws cinepolisException ;
    List<Clasificacion> obtenerTodos() throws cinepolisException;
    boolean insert(Clasificacion clasificacion) throws cinepolisException;
    boolean update(Clasificacion clasificacion);
    boolean delete(long id);
    public Long obtenerIdPorNombre(String nombre) throws SQLException;
    
}
