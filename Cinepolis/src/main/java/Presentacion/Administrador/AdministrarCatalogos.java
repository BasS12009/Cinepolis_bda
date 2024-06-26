/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Administrador;

import Presentacion.Administrador.AdministrarFunciones;
import Negocio.CinepolisBO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;

/**
 *
 * @author diana
 */
public class AdministrarCatalogos extends javax.swing.JFrame {
    
    CinepolisBO cinepolisBO;
    
    /**
     * Creates new form AdministrarCatalogos
     */
    public AdministrarCatalogos(CinepolisBO cinepolisBO) {
        initComponents();
        this.cinepolisBO=cinepolisBO;
         this.setLocationRelativeTo(this);
        this.setSize(690, 560);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCatalogoClientes = new javax.swing.JButton();
        btnReporteSucursales = new javax.swing.JButton();
        btnAdministrarFunciones = new javax.swing.JButton();
        btnCatalogoPeliculas1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(12, 33, 63));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 740, 40));

        jPanel2.setBackground(new java.awt.Color(12, 33, 63));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setText("Administrador de Catalogos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        btnCatalogoClientes.setBackground(new java.awt.Color(12, 33, 63));
        btnCatalogoClientes.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        btnCatalogoClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnCatalogoClientes.setText("Catalogo Clientes");
        btnCatalogoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnCatalogoClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 260, 50));

        btnReporteSucursales.setBackground(new java.awt.Color(12, 33, 63));
        btnReporteSucursales.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        btnReporteSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteSucursales.setText("Reporte Sucursales");
        btnReporteSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteSucursalesActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporteSucursales, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 260, 50));

        btnAdministrarFunciones.setBackground(new java.awt.Color(12, 33, 63));
        btnAdministrarFunciones.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        btnAdministrarFunciones.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministrarFunciones.setText("Administrar Funciones");
        btnAdministrarFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarFuncionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdministrarFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 260, 50));

        btnCatalogoPeliculas1.setBackground(new java.awt.Color(12, 33, 63));
        btnCatalogoPeliculas1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        btnCatalogoPeliculas1.setForeground(new java.awt.Color(255, 255, 255));
        btnCatalogoPeliculas1.setText("Catalogo Peliculas");
        btnCatalogoPeliculas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoPeliculas1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCatalogoPeliculas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 260, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSucursalesActionPerformed
        // TODO add your handling code here:
        
        ReportePorSucursal reportePorSucursal = new ReportePorSucursal();
        reportePorSucursal.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnReporteSucursalesActionPerformed

    private void btnCatalogoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoClientesActionPerformed

        CatalogoClientes catalogoClientes = new CatalogoClientes(cinepolisBO);
        catalogoClientes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCatalogoClientesActionPerformed

    private void btnAdministrarFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarFuncionesActionPerformed
        // TODO add your handling code here:
        AdministrarFunciones administrarFunciones = new AdministrarFunciones(cinepolisBO);
        administrarFunciones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministrarFuncionesActionPerformed

    private void btnCatalogoPeliculas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoPeliculas1ActionPerformed
        // TODO add your handling code here:
        CatalogoPeliculas p= new CatalogoPeliculas(cinepolisBO);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCatalogoPeliculas1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarCatalogos(cinepolisBO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministrarFunciones;
    private javax.swing.JButton btnCatalogoClientes;
    private javax.swing.JButton btnCatalogoPeliculas1;
    private javax.swing.JButton btnReporteSucursales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
