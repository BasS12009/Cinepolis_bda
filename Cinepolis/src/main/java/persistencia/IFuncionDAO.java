/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Funcion;
import excepciones.cinepolisException;

/**
 *
 * @author PC Gamer
 */
public interface IFuncionDAO {
    
    public Funcion insertarFuncion(Funcion funcion) throws cinepolisException;
    
    public Funcion editarFuncion(Funcion funcion) throws cinepolisException;
    
    public Funcion eliminarFuncionPorId(int idFuncion) throws cinepolisException;
}
