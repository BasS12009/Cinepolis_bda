/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidades.Reporte;
import excepciones.cinepolisException;
import java.util.List;

/**
 *
 * @author diana
 */
public interface IReporteDAO {
    
    
    public void generarReporte(Reporte reportes, int opcion);
    
    
}
