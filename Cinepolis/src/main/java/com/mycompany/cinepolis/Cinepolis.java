/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cinepolis;

import Negocio.CinepolisBO;
import Presentacion.Administrador.AdministrarCatalogos;
import Presentacion.LogIn;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;

/**
 *
 * @author diana
 */
public class Cinepolis {

    public static void main(String[] args) {
        try {
            ConexionBD conexion = new ConexionBD();
            ClienteDAO clienteDAO= new ClienteDAO(conexion);
            CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
            LogIn i=new LogIn(cinepolisBO);
            i.show();
//            AdministrarCatalogos a=new AdministrarCatalogos(cinepolisBO);
//            a.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }
}
