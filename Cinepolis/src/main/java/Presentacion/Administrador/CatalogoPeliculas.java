/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Administrador;

import DTOs.PeliculaDTO;
import Presentacion.Administrador.AgregarPeliculas;
import Presentacion.Administrador.AdministrarCatalogos;
import Negocio.CinepolisBO;
import excepciones.cinepolisException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author diana
 */
public class CatalogoPeliculas extends javax.swing.JFrame {
    CinepolisBO cinepolisBO;
    private int pagina=1;
    private int LIMITE=1;
    
    /**
     * Creates new form CatalalogoPeliculas
     */
    public CatalogoPeliculas(CinepolisBO cinepolisBO) {
        initComponents();
         this.setLocationRelativeTo(this);
        this.setSize(955, 600);
        this.cinepolisBO=cinepolisBO;
        this.cargarMetodosIniciales();
        NumeroDePagina.setEditable(false);
    }
    
    private long getIdSeleccionadoTablaPelicula() throws cinepolisException {
         int selectedRow = tblPeliculas.getSelectedRow();
        if (selectedRow >= 0) {
            return (long) tblPeliculas.getValueAt(selectedRow, 0);
        } else {
            throw new excepciones.cinepolisException("No se ha seleccionado ninguna película");
        }
    }
        
    private void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarPeliculasEnTabla();
    }
    
        private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        };
        int indiceColumnaEditar = 4;
        TableColumnModel modeloColumnas = this.tblPeliculas.getColumnModel();
        Color color = new Color(178, 218, 250);
        modeloColumnas.getColumn(indiceColumnaEditar).setCellRenderer(new JButtonRenderer("Editar",color));
        modeloColumnas.getColumn(indiceColumnaEditar).setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        };
        int indiceColumnaEliminar = 5;
        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar",color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
        }
        
            private void editar() {
            try {
            long id = this.getIdSeleccionadoTablaPelicula();
            System.out.println("ID de la película seleccionada: " + id);
            EditarPelicula editar = new EditarPelicula(this.cinepolisBO, id);
            this.setVisible(false);
            editar.show();
        } catch (excepciones.cinepolisException e) {
            System.out.println("Error: " + e.getMessage());
        }
        }

        private void eliminar() {
            try {
            long id = this.getIdSeleccionadoTablaPelicula();
            PeliculaDTO pelicula = cinepolisBO.obtenerPeliculaPorID(id);
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                        "¿Está seguro que desea eliminar la película?\n" +
                        "ID: " + pelicula.getId() + "\n" +
                        "Título: " + pelicula.getTitulo() + "\n" +
                        "Sinopsis: " + pelicula.getGenero(),
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                cinepolisBO.eliminarPelicula(id);
                JOptionPane.showMessageDialog(this, "Película eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPeliculasEnTabla();
            }
            } catch (cinepolisException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        private void llenarTablaPeliculas(List<PeliculaDTO> clienteLista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();

        modeloTabla.setRowCount(0);

        if (clienteLista != null) {
            clienteLista.forEach(row -> {
                Object[] fila = new Object[6];
                fila[0] = row.getId();
                fila[1] = row.getTitulo();
                fila[2] = row.getGenero();
                fila[3] = row.getClasificacion();
                fila[4] = "Eliminar";
                fila[5] = "Editar"; 
                modeloTabla.addRow(fila);
            });
        }
        }
        
        private void cargarPeliculasEnTabla() {
    try {
        int indiceInicio = (pagina - 1) * LIMITE;
        List<PeliculaDTO> todasLasPeliculas = cinepolisBO.obtenerTodasLasPeliculasTablaDTO();
        int indiceFin = Math.min(indiceInicio + LIMITE, todasLasPeliculas.size());

        List<PeliculaDTO> peliculasPagina = obtenerPeliculasPagina(indiceInicio, indiceFin);

        llenarTablaPeliculas(peliculasPagina);

        actualizarNumeroDePagina();
    } catch (cinepolisException ex) {
        ex.printStackTrace();
    }
    }
        
        private List<PeliculaDTO> obtenerPeliculasPagina(int indiceInicio, int indiceFin) throws cinepolisException {
            List<PeliculaDTO> todasLasPeliculas = cinepolisBO.buscarPeliculasTabla();
            List<PeliculaDTO> peliculasPaginas = new ArrayList<>();
            indiceFin = Math.min(indiceFin, todasLasPeliculas.size());
            for (int i = indiceInicio; i < indiceFin; i++) {
                peliculasPaginas.add(todasLasPeliculas.get(i));
            }
            return peliculasPaginas;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnNuevaPelicula = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(12, 33, 63));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 910, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setText("Catalogo Peliculas ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Descripcion", "Genero", "Eliminar", "Editar"
            }
        ));
        jScrollPane1.setViewportView(tblPeliculas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 790, 260));

        jPanel6.setBackground(new java.awt.Color(12, 33, 63));

        jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Filtro de Busqueda");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Inicio:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha Fin:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Peliculas:");

        btnBuscar.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(322, 322, 322))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 770, 80));

        btnNuevaPelicula.setBackground(new java.awt.Color(12, 33, 63));
        btnNuevaPelicula.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        btnNuevaPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaPelicula.setText("+Nueva Pelicula");
        btnNuevaPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPeliculaActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevaPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        btnSiguiente.setBackground(new java.awt.Color(12, 33, 63));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 510, -1, -1));

        btnAtras.setBackground(new java.awt.Color(12, 33, 63));
        btnAtras.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, -1, -1));

        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, 20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        AdministrarCatalogos administrarCatalogos = new AdministrarCatalogos(cinepolisBO);
        administrarCatalogos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevaPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPeliculaActionPerformed
        // TODO add your handling code here:
        AgregarPeliculas agregarPeliculas = new AgregarPeliculas(cinepolisBO);
        agregarPeliculas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevaPeliculaActionPerformed

    
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (pagina > 1) {
        pagina--;
        cargarPeliculasEnTabla();
        actualizarNumeroDePagina();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed
   
    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
         
        try {
        List<PeliculaDTO> todasLasPeliculas = cinepolisBO.buscarPeliculasTabla();

        int totalPaginas = (int) Math.ceil((double) todasLasPeliculas.size() / LIMITE);

        int nuevaPagina = Integer.parseInt(NumeroDePagina.getText());

        if (nuevaPagina >= 1 && nuevaPagina <= totalPaginas) {
            pagina = nuevaPagina;

            cargarPeliculasEnTabla();

            actualizarNumeroDePagina();
        } else {
            JOptionPane.showMessageDialog(this, "Número de página inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para la página", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (cinepolisException ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_NumeroDePaginaActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
        List<PeliculaDTO> todasLasPeliculas = cinepolisBO.buscarPeliculasTabla();

        int totalPaginas = (int) Math.ceil((double) todasLasPeliculas.size() / LIMITE);

        if (pagina < totalPaginas) {
            pagina++;
            cargarPeliculasEnTabla();
            actualizarNumeroDePagina();
        } else {

            JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (cinepolisException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void actualizarNumeroDePagina() {
    NumeroDePagina.setText("Página " + pagina);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalogoPeliculas(cinepolisBO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevaPelicula;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblPeliculas;
    // End of variables declaration//GEN-END:variables
}
