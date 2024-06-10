/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Genero;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IGeneroDAO {
    Genero buscarPorId(long id) throws cinepolisException ;
    List<Genero> obtenerTodos() throws cinepolisException;
    boolean insert(Genero genero) throws cinepolisException;
    boolean update(Genero genero);
    boolean delete(long id);
    
    public Long obtenerIdPorNombre(String nombre) throws cinepolisException;
}
