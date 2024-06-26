/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Administrador;

import Presentacion.Administrador.AgregarCliente;
import Presentacion.Administrador.AdministrarCatalogos;
import DTOs.ClienteDTO;
import Negocio.CinepolisBO;
import excepciones.cinepolisException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class CatalogoClientes extends javax.swing.JFrame {
    private int pagina=1;
    private int LIMITE=1;
    CinepolisBO cinepolisBO;
    boolean conFiltro;
    
    /**
     * Creates new form CatalogoClientes
     */
    public CatalogoClientes(CinepolisBO cinepolisBO) {
        initComponents();
        this.cinepolisBO=cinepolisBO;
         this.setLocationRelativeTo(this);
        this.setSize(955, 600);
        this.cargarMetodosIniciales();
        NumeroDePagina.setEditable(false);
        conFiltro=false;
    }
    
    
    private long getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            long indiceColumnaId = 0;
            long idSocioSeleccionado = (long) modelo.getValueAt(indiceFilaSeleccionada,
                   (int) indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }
    
    private void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarClientesEnTabla();
    }
    
    private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un alumno
                editar();
            }
        };
        int indiceColumnaEditar = 4;
        TableColumnModel modeloColumnas = this.tblClientes.getColumnModel();
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
        long id = this.getIdSeleccionadoTablaClientes();
        EditarCliente editar = new EditarCliente(this.cinepolisBO, id);
        this.setVisible(false);
        editar.show();
    } catch (excepciones.cinepolisException e) {
        System.out.println("Error: " + e.getMessage());
    }
    }
    
    private void eliminar() {
        try {
        long id = this.getIdSeleccionadoTablaClientes();
        ClienteDTO cliente = cinepolisBO.obtenerClientePorID(id);
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                            "¿Está seguro que desea eliminar al cliente?\n" +
                            "ID: " + cliente.getId() + "\n" +
                            "Nombre: " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno() + "\n" +
                            "Correo: " + cliente.getCorreo(),
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            cinepolisBO.eliminarCliente(id);
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesEnTabla();
        }
        } catch (cinepolisException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void llenarTablaClientes(List<ClienteDTO> clienteLista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();

    // Clear existing rows
    modeloTabla.setRowCount(0);

    if (clienteLista != null) {
        clienteLista.forEach(row -> {
            Object[] fila = new Object[6]; // Adjust the array size to match the number of columns
            fila[0] = row.getId();
            fila[1] = row.getNombre() + " " + row.getApellidoPaterno() + " " + row.getApellidoMaterno();
            fila[2] = row.getCorreo();
            fila[3] = row.getContrasena();
            fila[4] = "Eliminar";
            fila[5] = "Editar"; 
            modeloTabla.addRow(fila); // Add row data to the table model
        });
    }
    }
     
    private void cargarClientesEnTabla() {
    try {
        int indiceInicio = (pagina - 1) * LIMITE;
        List<ClienteDTO> todosLosClientes = cinepolisBO.buscarClientesTabla();
        int indiceFin = Math.min(indiceInicio + LIMITE, todosLosClientes.size());

        List<ClienteDTO> clientesPagina = obtenerClientesPagina(indiceInicio, indiceFin);

        llenarTablaClientes(clientesPagina);

        actualizarNumeroDePagina();
    } catch (cinepolisException ex) {
        ex.printStackTrace();
    }
    }
    
    
    private List<ClienteDTO> obtenerClientesPagina(int indiceInicio, int indiceFin) {
            try {
        List<ClienteDTO> todosLosClientes = cinepolisBO.buscarClientesTabla();
        List<ClienteDTO> clientesPaginas = new ArrayList<>();

        indiceFin = Math.min(indiceFin, todosLosClientes.size());

        for (int i = indiceInicio; i < indiceFin; i++) {
            clientesPaginas.add(todosLosClientes.get(i));
        }
        
        return clientesPaginas;
            } catch (cinepolisException ex) {
 
                ex.printStackTrace();
                return Collections.emptyList();
            }
    }
    
    
    
    
//a
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
        tblClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        textoFiltroNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        botonRestaurar = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();
        CambiarLimite = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

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
                .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnRegresar)
                .addGap(0, 527, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

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
        jLabel1.setText("Catalogos Clientes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo", "Contraseña", "Eliminar", "Editar"
            }
        ));
        jScrollPane1.setViewportView(tblClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 790, 260));

        jPanel4.setBackground(new java.awt.Color(12, 33, 63));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Filtro de Busqueda");

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Inicio:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Fin:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clientes:");

        btnBuscar.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        botonRestaurar.setText("Restaurar");
        botonRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRestaurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(216, 216, 216)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(textoFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(211, 211, 211)
                        .addComponent(botonRestaurar)))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textoFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 770, 80));

        btnNuevoCliente.setBackground(new java.awt.Color(12, 33, 63));
        btnNuevoCliente.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        btnNuevoCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoCliente.setText("+Nuevo Cliente");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

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

        NumeroDePagina.setBackground(new java.awt.Color(200, 200, 200));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, 20, -1));

        CambiarLimite.setText("1");
        CambiarLimite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarLimiteActionPerformed(evt);
            }
        });
        jPanel1.add(CambiarLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, 20, 40));

        jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel7.setText("Numero de Resultados");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, -1, -1));

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
        AdministrarCatalogos administrarCatalogos = new AdministrarCatalogos(cinepolisBO);
        administrarCatalogos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        AgregarCliente agregarCliente = new AgregarCliente(cinepolisBO);
        agregarCliente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (pagina > 1) {
        pagina--;
        cargarClientesEnTabla();
        actualizarNumeroDePagina();
    }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:
        try {
        List<ClienteDTO> todosLosclientes = cinepolisBO.buscarClientesTabla();

        int totalPaginas = (int) Math.ceil((double) todosLosclientes.size() / LIMITE);

        int nuevaPagina = Integer.parseInt(NumeroDePagina.getText());

        if (nuevaPagina >= 1 && nuevaPagina <= totalPaginas) {
            pagina = nuevaPagina;

            cargarClientesEnTabla();

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
        // TODO add your handling code here:
        try {
        List<ClienteDTO> todosLosClientes = cinepolisBO.buscarClientesTabla();

        int totalPaginas = (int) Math.ceil((double) todosLosClientes.size() / LIMITE);

        if (pagina < totalPaginas) {
            pagina++;
            cargarClientesEnTabla();
            actualizarNumeroDePagina();
        } else {

            JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (cinepolisException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void CambiarLimiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarLimiteActionPerformed
            try {
                if(conFiltro=false){

            int nuevoLimite = Integer.parseInt(CambiarLimite.getText());
            this.LIMITE = nuevoLimite;
            cargarClientesEnTabla(); 
            actualizarNumeroDePagina();
                }else{
                    int nuevoLimite = Integer.parseInt(CambiarLimite.getText());
                    this.LIMITE = nuevoLimite;
                   String nombreFiltro = textoFiltroNombre.getText();
                    java.sql.Date fechaInicio = null;
                    if (jDateChooser1.getDate() != null) {
                        fechaInicio = new java.sql.Date(jDateChooser1.getDate().getTime());
                    }
                    java.sql.Date fechaFin = null;
                    if (jDateChooser2.getDate() != null) {
                        fechaFin = new java.sql.Date(jDateChooser2.getDate().getTime());
                    }
                    cargarClientesEnTablaActualizada(nombreFiltro, fechaInicio, fechaFin);
                    actualizarNumeroDePagina();
                    conFiltro=true;
                }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el límite", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CambiarLimiteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String nombreFiltro = textoFiltroNombre.getText();
        // Verificar si jDateChooser1 tiene una fecha seleccionada
        java.sql.Date fechaInicio = null;
        if (jDateChooser1.getDate() != null) {
            fechaInicio = new java.sql.Date(jDateChooser1.getDate().getTime());
        }
        // Verificar si jDateChooser2 tiene una fecha seleccionada
        java.sql.Date fechaFin = null;
        if (jDateChooser2.getDate() != null) {
            fechaFin = new java.sql.Date(jDateChooser2.getDate().getTime());
        }
        cargarClientesEnTablaActualizada(nombreFiltro, fechaInicio, fechaFin);
        actualizarNumeroDePagina();
        conFiltro=true;
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void botonRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRestaurarActionPerformed
        // TODO add your handling code here:
        conFiltro=false;
        textoFiltroNombre.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        this.cargarMetodosIniciales();
    }//GEN-LAST:event_botonRestaurarActionPerformed
    
    private List<ClienteDTO> obtenerClientesPaginaActualizado(int indiceInicio, int indiceFin,String nombreFiltro, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
            try {
        List<ClienteDTO> todosLosClientes = cinepolisBO.buscarClientes(nombreFiltro, fechaInicio, fechaFin);
        List<ClienteDTO> clientesPaginas = new ArrayList<>();

        indiceFin = Math.min(indiceFin, todosLosClientes.size());

        for (int i = indiceInicio; i < indiceFin; i++) {
            clientesPaginas.add(todosLosClientes.get(i));
        }
        
        return clientesPaginas;
            } catch (cinepolisException ex) {
 
                ex.printStackTrace();
                return Collections.emptyList();
            }
    }
    
    
    private void cargarClientesEnTabla(List<ClienteDTO> clientesEncontrados) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblClientes.getModel();

        // Limpiar filas existentes
        modeloTabla.setRowCount(0);

        if (clientesEncontrados != null) {
            clientesEncontrados.forEach(row -> {
                Object[] fila = new Object[6];
                fila[0] = row.getId();
                fila[1] = row.getNombre() + " " + row.getApellidoPaterno() + " " + row.getApellidoMaterno();
                fila[2] = row.getCorreo();
                fila[3] = row.getContrasena();
                fila[4] = "Eliminar";
                fila[5] = "Editar"; 
                modeloTabla.addRow(fila);
            });
        }
        
        
    }
    
    
    private void cargarClientesEnTablaActualizada(String nombreFiltro, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
    try {
        int indiceInicio = (pagina - 1) * LIMITE;
        List<ClienteDTO> todosLosClientes = cinepolisBO.buscarClientes(nombreFiltro, fechaInicio, fechaFin);
        int indiceFin = Math.min(indiceInicio + LIMITE, todosLosClientes.size());

        List<ClienteDTO> clientesPagina = obtenerClientesPaginaActualizado(indiceInicio, indiceFin, nombreFiltro,fechaInicio, fechaFin);

        cargarClientesEnTabla(clientesPagina);

        actualizarNumeroDePagina();
    } catch (cinepolisException ex) {
        ex.printStackTrace();
    }
    }
    
    private void actualizarNumeroDePagina() {
    NumeroDePagina.setText(""+pagina);
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
            java.util.logging.Logger.getLogger(CatalogoClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalogoClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalogoClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogoClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        ConexionBD conexion = new ConexionBD();
        ClienteDAO clienteDAO= new ClienteDAO (conexion);
        CinepolisBO cinepolisBO=new CinepolisBO(clienteDAO);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalogoClientes(cinepolisBO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CambiarLimite;
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JButton botonRestaurar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField textoFiltroNombre;
    // End of variables declaration//GEN-END:variables
}
