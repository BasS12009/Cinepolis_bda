/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOs.GeneroDTO;
import DTOs.PeliculaDTO;
import Negocio.CinepolisBO;
import excepciones.cinepolisException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class cartelera extends javax.swing.JFrame {
       private int pagina = 1;
       private final int LIMITE = 6;  // Assume we show 6 movies per page
       private List<String> peliculas;
       private CinepolisBO negocio;
       private String sucursalSeleccionada;
       private String sucursal;
       private int totalPaginas;
       List<PeliculaDTO>datosPeliculas;
       
    /**
     * Creates new form catalogoPeliculas
     */
    public cartelera(String sucursal, CinepolisBO negocio) {
        this.negocio = negocio;
        this.sucursalSeleccionada = sucursal;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(955, 580);
        setVisible(true);
        
        llenarComboBoxGenero();
        ComboBoxGenero.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarBotonesPeliculas(pagina);
            }
        });
    }



    // Method to obtain movie titles by genre and branch
   

    // Fill the ComboBox with genres
    private void llenarComboBoxGenero() {
        ComboBoxGenero.addItem(""); // First option blank
        List<GeneroDTO> generos = negocio.obtenerTodosLosGeneros();
        
        for (GeneroDTO genero : generos) {
            ComboBoxGenero.addItem(genero.getTipo());
        }
    }
    
    private int calcularTotalPaginas() {
        return (int) Math.ceil((double) peliculas.size() / 7); 
    }

    private void llenarBotonesPeliculas(int pagina) {
        this.peliculas = negocio.obtenerTitulosPeliculasPorGeneroYSucursal((String) ComboBoxGenero.getSelectedItem(), sucursalSeleccionada);
        this.totalPaginas = calcularTotalPaginas();
        this.datosPeliculas = negocio.obtenerDatosPeliculasPorGeneroYSucursal((String) ComboBoxGenero.getSelectedItem(), sucursalSeleccionada);
        int inicio = (pagina - 1) * 7;
        int fin = Math.min(inicio + 7, peliculas.size());

        try {
            for (int i = 0; i < 7; i++) {
                int index = inicio + i;
                if (index < fin) {
                    switch (i) {
                        case 0:
                            botonPelicula1.setText(peliculas.get(index));
                            break;
                        case 1:
                            botonPelicula2.setText(peliculas.get(index));
                            break;
                        case 2:
                            botonPelicula3.setText(peliculas.get(index));
                            break;
                        case 3:
                            botonPelicula4.setText(peliculas.get(index));
                            break;
                        case 4:
                            botonPelicula5.setText(peliculas.get(index));
                            break;
                        case 5:
                            botonPelicula6.setText(peliculas.get(index));
                            break;
                        case 6:
                            botonPelicula7.setText(peliculas.get(index));
                            break;
                    }
                } else {
                    // Si no hay más películas para mostrar, limpiar el texto del botón
                    switch (i) {
                        case 0:
                            botonPelicula1.setText("");
                            break;
                        case 1:
                            botonPelicula2.setText("");
                            break;
                        case 2:
                            botonPelicula3.setText("");
                            break;
                        case 3:
                            botonPelicula4.setText("");
                            break;
                        case 4:
                            botonPelicula5.setText("");
                            break;
                        case 5:
                            botonPelicula6.setText("");
                            break;
                        case 6:
                            botonPelicula7.setText("");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ComboBoxGenero = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        botonPelicula2 = new javax.swing.JButton();
        botonPelicula3 = new javax.swing.JButton();
        botonPelicula7 = new javax.swing.JButton();
        botonPelicula1 = new javax.swing.JButton();
        botonPelicula4 = new javax.swing.JButton();
        botonPelicula5 = new javax.swing.JButton();
        botonPelicula6 = new javax.swing.JButton();
        btnMenu = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(12, 33, 63));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(12, 33, 63));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 570));

        jPanel3.setBackground(new java.awt.Color(12, 33, 63));

        ComboBoxGenero.setBackground(new java.awt.Color(12, 33, 63));
        ComboBoxGenero.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        ComboBoxGenero.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(860, Short.MAX_VALUE)
                .addComponent(ComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(ComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 910, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setText("Cartelera ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        btnAtras.setBackground(new java.awt.Color(12, 33, 63));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAtras.setText("←");
        btnAtras.setContentAreaFilled(false);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, -1));

        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSiguiente.setText("→");
        btnSiguiente.setContentAreaFilled(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 530, -1, -1));

        botonPelicula2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula2ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 150, 190));

        botonPelicula3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula3ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 150, 190));

        botonPelicula7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula7ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, 150, 190));

        botonPelicula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula1ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 150, 190));

        botonPelicula4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula4ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 150, 190));

        botonPelicula5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula5ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 150, 190));

        botonPelicula6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelicula6ActionPerformed(evt);
            }
        });
        jPanel1.add(botonPelicula6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 150, 190));

        jMenu2.setText("Sucursales");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("Ver Sucursales");
        jMenu2.add(jRadioButtonMenuItem3);

        btnMenu.add(jMenu2);

        jMenu3.setText("Funciones");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Ver Funciones");
        jMenu3.add(jRadioButtonMenuItem2);

        btnMenu.add(jMenu3);

        jMenu1.setText("Salas");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Ver Salas");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        btnMenu.add(jMenu1);

        setJMenuBar(btnMenu);

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

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (pagina < totalPaginas) {
            pagina++;
            llenarBotonesPeliculas(pagina);
        } else {
            JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (pagina > 1) {
            pagina--;
            llenarBotonesPeliculas(pagina);
        } else {
            JOptionPane.showMessageDialog(this, "Ya estás en la primera página", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    
    
    
    private void botonPelicula2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula2ActionPerformed
        JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
        String tituloPelicula = boton.getText(); // Obtener el texto del botón
        confirmarCompra(tituloPelicula, datosPeliculas);
    }//GEN-LAST:event_botonPelicula2ActionPerformed

    private void botonPelicula3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula3ActionPerformed
        JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas); 
    }//GEN-LAST:event_botonPelicula3ActionPerformed

    private void botonPelicula7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula7ActionPerformed
     JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas); 
    }//GEN-LAST:event_botonPelicula7ActionPerformed

    private void botonPelicula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula1ActionPerformed
    JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas);     }//GEN-LAST:event_botonPelicula1ActionPerformed

    private void botonPelicula4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula4ActionPerformed
       JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas); 
    }//GEN-LAST:event_botonPelicula4ActionPerformed

    private void botonPelicula5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula5ActionPerformed
     JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas); 
    }//GEN-LAST:event_botonPelicula5ActionPerformed

    private void botonPelicula6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelicula6ActionPerformed
        JButton boton = (JButton) evt.getSource(); // Obtener el botón que activó el evento
    String tituloPelicula = boton.getText(); // Obtener el texto del botón
    confirmarCompra(tituloPelicula, datosPeliculas); 
    }//GEN-LAST:event_botonPelicula6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxGenero;
    private javax.swing.JButton botonPelicula1;
    private javax.swing.JButton botonPelicula2;
    private javax.swing.JButton botonPelicula3;
    private javax.swing.JButton botonPelicula4;
    private javax.swing.JButton botonPelicula5;
    private javax.swing.JButton botonPelicula6;
    private javax.swing.JButton botonPelicula7;
    private javax.swing.JButton btnAtras;
    private javax.swing.JMenuBar btnMenu;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    // End of variables declaration//GEN-END:variables

    private void confirmarCompra(String tituloPelicula, List<PeliculaDTO> datosPeliculas) {
        // Mostrar los datos de la película seleccionada
        PeliculaDTO peliculaSeleccionada = null;
        for (PeliculaDTO pelicula : datosPeliculas) {
            if (pelicula.getTitulo().equals(tituloPelicula)) {
                peliculaSeleccionada = pelicula;
                break;
            }
        }

        if (peliculaSeleccionada != null) {
            System.out.println("Título: " + peliculaSeleccionada.getTitulo());
            System.out.println("Sinopsis: " + peliculaSeleccionada.getSinopsis());
            System.out.println("Duración: " + peliculaSeleccionada.getDuracion());

           
            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea confirmar la compra de entradas para esta película?", "Confirmar Compra", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
               CompraBoleto b=new CompraBoleto(sucursal, peliculaSeleccionada, negocio);
               b.setVisible(true);
               this.dispose();
            } else {
                System.out.println("Compra cancelada.");
            }
        } else {
            System.out.println("La película seleccionada no se encuentra en la lista de datos.");
        }
        }


}
