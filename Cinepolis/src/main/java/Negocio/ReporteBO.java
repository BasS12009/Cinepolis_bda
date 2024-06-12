/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import entidades.Reporte;
import persistencia.IReporteDAO;
import persistencia.ReporteDAO;

/**
 *
 * @author diana
 */
public class ReporteBO implements IReporteDAO{
    
    ReporteDAO reporteDAO;
    
     public ReporteBO() {
    }
    

    public ReporteBO(ReporteDAO reporteDAO) {
        this.reporteDAO = reporteDAO;
    }

    @Override
    public void generarReporte(Reporte reportes, int opcion) {
        
        reporteDAO.generarReporte(reportes, opcion);
        
    }

   
    
}
