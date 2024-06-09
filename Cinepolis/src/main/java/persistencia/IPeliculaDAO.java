/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import DTOs.PeliculaDTO;
import entidades.Pelicula;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IPeliculaDAO {
    

    public Pelicula insertarPelicula(Pelicula pelicula) throws cinepolisException;
    
    public Pelicula editarPelicula(Pelicula pelicula) throws cinepolisException;
    
    public Pelicula eliminarPeliculaPorID(Long idPelicula) throws cinepolisException;
    
    public PeliculaDTO obtenerPeliculaPorId(long id) throws cinepolisException;
    
    public List<PeliculaDTO> obtenerTodasLasPeliculas() throws cinepolisException;
    
    public List<PeliculaDTO> buscarPeliculasConFiltros(String titulo, String genero, String clasificacion, String pais) throws SQLException, cinepolisException;
    
}
