/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Administrador;

import DTOs.FuncionDTO;
import DTOs.PeliculaDTO;
import Negocio.CinepolisBO;
import excepciones.cinepolisException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;

/**
 *
 * @author diana
 */
public class NuevaFuncion extends javax.swing.JFrame {
 CinepolisBO cinepolisBO;
    /**
     * Creates new form NuevaFuncion
     * @param cinepolisBO
     */
    public NuevaFuncion(CinepolisBO cinepolisBO) {
        initComponents();
         this.cinepolisBO= cinepolisBO;
         
         try {
            List<PeliculaDTO> peliculas = cinepolisBO.buscarPeliculasTabla();
            for (PeliculaDTO pelicula : peliculas) {
                comboBoxPelicula.addItem(pelicula.getTitulo());
            }
        } catch (cinepolisException e) {
             System.out.println(e.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        textoHoraInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAgregarFuncion = new javax.swing.JButton();
        comboBoxPelicula = new javax.swing.JComboBox<>();

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
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 760, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setText("Nueva Funcion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel2.setText("Fecha:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 250, 30));

        jLabel3.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel3.setText("Hora Inicio:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, 20));
        jPanel1.add(textoHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 250, 30));

        jLabel4.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel4.setText("Pelicula:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, -1, -1));

        btnAgregarFuncion.setBackground(new java.awt.Color(12, 33, 63));
        btnAgregarFuncion.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnAgregarFuncion.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarFuncion.setText("Agregar Funcion");
        btnAgregarFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFuncionActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarFuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, -1, 30));

        comboBoxPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPeliculaActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
       AdministrarFunciones administrarFunciones = new  AdministrarFunciones(cinepolisBO);
        administrarFunciones.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAgregarFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFuncionActionPerformed
            try {                                                  
            Date fecha = jDateChooser1.getDate();
            String horaInicioStr = textoHoraInicio.getText();
            String peliculaSeleccionada = (String) comboBoxPelicula.getSelectedItem();

            if (fecha == null || horaInicioStr.isEmpty() || peliculaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double horaInicio;
            try {
                String[] partesHora = horaInicioStr.split(":");
                int horas = Integer.parseInt(partesHora[0]);
                int minutos = Integer.parseInt(partesHora[1]);

                horaInicio = horas + minutos / 60.0;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "Formato de hora incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            System.out.println(fecha + " " + horaInicioStr + " " + peliculaSeleccionada);

            // Obtener datos de la UI y crear un objeto Funcion
            FuncionDTO nuevaFuncion = new FuncionDTO();
            nuevaFuncion.setFecha(fecha);
            nuevaFuncion.setHoraInicio(horaInicio);

            PeliculaDTO pelicula = cinepolisBO.buscarPeliculaPorTitulo(peliculaSeleccionada);
            if (pelicula == null) {
                JOptionPane.showMessageDialog(this, "La película seleccionada no existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            nuevaFuncion.setPeliculaDTO(pelicula);

            try {
                System.out.println(pelicula.getId());
                cinepolisBO.agregarFuncion(nuevaFuncion);
                JOptionPane.showMessageDialog(this, "Función agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                jDateChooser1.setDate(null);
                textoHoraInicio.setText("");
                comboBoxPelicula.setSelectedIndex(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar función: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (cinepolisException ex) {
            System.out.println(ex.getMessage());
        }   
    }//GEN-LAST:event_btnAgregarFuncionActionPerformed

    private void comboBoxPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPeliculaActionPerformed

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
            java.util.logging.Logger.getLogger(NuevaFuncion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaFuncion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaFuncion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaFuncion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NuevaFuncion(cinepolisBO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarFuncion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxPelicula;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField textoHoraInicio;
    // End of variables declaration//GEN-END:variables
}
