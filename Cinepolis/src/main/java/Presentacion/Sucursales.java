/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.SucursalDTO;
import Negocio.CinepolisBO;
import com.itextpdf.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;

/**
 *
 * @author diana
 */
public class Sucursales extends javax.swing.JFrame {
    CinepolisBO cine;
    /**
     * Creates new form Sucursales
     */
    
    public Sucursales() { 
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(725, 560);
        this.cine= new CinepolisBO();
    }

    public Sucursales(CinepolisBO cine) throws SQLException {
       
        
        llenarComboBoxNombreSucursal();
    }

    private void llenarComboBoxNombreSucursal() throws SQLException {
        // Obtener la lista de sucursales
            Point2D.Double coordenadasCliente = cine.obtenerCoordenadasCliente(cine.getId());

        // Obtener la lista de sucursales
        List<SucursalDTO> sucursales = cine.obtenerSucursales();

        // Crear un mapa para almacenar las distancias entre el cliente y cada sucursal
        Map<String, Double> distancias = new HashMap<>();

        // Calcular la distancia entre el cliente y cada sucursal y almacenarla en el mapa
        for (SucursalDTO sucursal : sucursales) {
            Point2D.Double coordenadasSucursal = sucursal.getCoordenadas();
            double distancia = calcularDistancia(coordenadasCliente, coordenadasSucursal);
            distancias.put(sucursal.getNombre(), distancia);
        }

        // Ordenar las sucursales por distancia
        List<Map.Entry<String, Double>> listaDistanciasOrdenadas = new ArrayList<>(distancias.entrySet());
        listaDistanciasOrdenadas.sort(Map.Entry.comparingByValue());

        // Limpiar el combo box antes de agregar los nuevos elementos
        comboBoxNombreSucursal.removeAllItems();

        // Agregar los nombres de las sucursales más cercanas al combo box
        for (Map.Entry<String, Double> entry : listaDistanciasOrdenadas) {
            comboBoxNombreSucursal.addItem(entry.getKey());
        }
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
        jPanel2 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxNombreSucursal = new javax.swing.JComboBox<>();
        btnConfirmarSucursal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(12, 33, 63));

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("←");
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnRegresar)
                .addGap(0, 527, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

        jPanel3.setBackground(new java.awt.Color(12, 33, 63));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 660, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setText("Sucursales mas cercanas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel3.setText("Nombre Sucursal:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        comboBoxNombreSucursal.setBackground(new java.awt.Color(12, 33, 63));
        comboBoxNombreSucursal.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        comboBoxNombreSucursal.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxNombreSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cinepolis Bella Vista", "Cinepolis Sendero", "Cinepolis Navojoa ", "Cinepolis Patio Hermosillo" }));
        jPanel1.add(comboBoxNombreSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 370, 30));

        btnConfirmarSucursal.setBackground(new java.awt.Color(12, 33, 63));
        btnConfirmarSucursal.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnConfirmarSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarSucursal.setText("Confirmar Sucursal");
        btnConfirmarSucursal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnConfirmarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarSucursalActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfirmarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
      Cartelera cartelera = new Cartelera(comboBoxNombreSucursal.getItemAt(0));
        cartelera.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnConfirmarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarSucursalActionPerformed
//        try {
//            Point2D.Double coordenadasCliente = obtenerCoordenadasCliente();
//            
//            String nombreSucursalSeleccionada = comboBoxNombreSucursal.getSelectedItem().toString();
//            
//            List<Point2D.Double> coordenadasSucursales = obtenerCoordenadasSucursales();
//            
//            List<SucursalDTO> sucursalesCercanas = encontrarSucursalesCercanas(coordenadasCliente, coordenadasSucursales);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

    }//GEN-LAST:event_btnConfirmarSucursalActionPerformed

        private Point2D.Double obtenerCoordenadasCliente() {
            int idCliente = cine.getId();
            
            return cine.obtenerCoordenadasCliente(idCliente);
     }

    private List<SucursalDTO> obtenerCoordenadasSucursales() throws SQLException {
    return cine.obtenerCoordenadasSucursales();
    }

    private List<SucursalDTO> encontrarSucursalesCercanas(Point2D.Double coordenadasCliente, List<Point2D.Double> coordenadasSucursales) {
        List<SucursalDTO> sucursalesCercanas = new ArrayList<>();
        double distanciaMinima = Double.MAX_VALUE;
        for (Point2D.Double coordenadas : coordenadasSucursales) {
            double distancia = calcularDistancia(coordenadasCliente, coordenadas);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                sucursalesCercanas.clear();
                SucursalDTO sucursal = obtenerSucursalPorCoordenadas(coordenadas);
                sucursalesCercanas.add(sucursal);
            } else if (distancia == distanciaMinima) {
                SucursalDTO sucursal = obtenerSucursalPorCoordenadas(coordenadas);
                sucursalesCercanas.add(sucursal);
            }
        }
        return sucursalesCercanas;
    }
    
    private double calcularDistancia(Point2D.Double coordenadasCliente, Point2D.Double coordenadasSucursal) {
        double dx = coordenadasCliente.getX() - coordenadasSucursal.getX();
        double dy = coordenadasCliente.getY() - coordenadasSucursal.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private SucursalDTO obtenerSucursalPorCoordenadas(Point2D.Double coordenadas) {
        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setCoordenadas(coordenadas);
        return sucursal;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Sucursales(cinepolisBO).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Sucursales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarSucursal;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxNombreSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
