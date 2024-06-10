/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.Boleto;
import excepciones.cinepolisException;

/**
 *
 * @author 
 */
public interface IBoletoDAO {
    
    public Boleto insertarBoletoComprado(Boleto boleto) throws cinepolisException;
    
    public Boleto editarBoleto(Boleto boleto) throws cinepolisException;
    
    public Boleto eliminarBoletoPorID(Long idBoleto) throws cinepolisException;
    
}
