/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Pelicula;
import excepciones.cinepolisException;

/**
 *
 * @author PC Gamer
 */
public interface IPeliculaDAO {
    

    public Pelicula insertarCliente(Pelicula cliente) throws cinepolisException;
}
