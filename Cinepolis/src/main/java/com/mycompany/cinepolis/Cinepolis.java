/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cinepolis;

import Negocio.CinepolisBO;
import Presentacion.Administrador.AdministrarCatalogos;
import Presentacion.Administrador.CatalogoClientes;
import Presentacion.LogIn;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;

/**
 *
 * @author diana
 */
public class Cinepolis {

    public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        
        AdministrarCatalogos administrarCatalogos=new AdministrarCatalogos(cinepolisBO);
        administrarCatalogos.show();
        
        LogIn l=new LogIn(cinepolisBO);
        l.show();
    }
}
