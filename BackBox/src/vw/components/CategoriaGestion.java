/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.*;
import Modelo.*;
import java.awt.Toolkit;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vw.dialogs.CategoriasRegistrar;
import vw.main.Menu;

/**
 * Esta JFrame se encarga de mostrar las categorias que tiene el sistema 
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class CategoriaGestion extends javax.swing.JFrame {

    String usuario;
    ArrayList<seccion> listMenu = new ArrayList();
    ArrayList<acciones> listMenuItem = new ArrayList();
    ArrayList<ContenedorMenus> List_MenuItem = new ArrayList();

    /**
     * @param Usuario Usuario del sistema
     * @param listMenuItem Lista de Menus para el usuario
     */
    public CategoriaGestion(String Usuario, ArrayList listMenuItem) {
        initComponents();
        this.List_MenuItem = listMenuItem;
        this.usuario = Usuario;
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
        try {
            CargaCategorias();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriaLista = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        inferior = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        volver = new javax.swing.JButton();
        derecha = new javax.swing.JPanel();
        izquierda = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Categrorías - BackBox");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBackground(new java.awt.Color(255, 255, 255));
        centro.setLayout(new javax.swing.BoxLayout(centro, javax.swing.BoxLayout.LINE_AXIS));

        categoriaLista.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(categoriaLista);

        centro.add(jScrollPane1);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        nuevo.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.setBorder(null);
        nuevo.setBorderPainted(false);
        nuevo.setContentAreaFilled(false);
        nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevo.setPreferredSize(new java.awt.Dimension(55, 47));
        nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        inferior.add(nuevo);

        borrar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_remove_black_24dp.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setBorder(null);
        borrar.setBorderPainted(false);
        borrar.setContentAreaFilled(false);
        borrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setPreferredSize(new java.awt.Dimension(55, 47));
        borrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        inferior.add(borrar);

        actualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
        actualizar.setBorderPainted(false);
        actualizar.setContentAreaFilled(false);
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setPreferredSize(new java.awt.Dimension(55, 47));
        actualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        inferior.add(actualizar);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(160, 40));
        jSeparator1.setRequestFocusEnabled(false);
        inferior.add(jSeparator1);

        volver.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        volver.setBorder(null);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volver.setPreferredSize(new java.awt.Dimension(55, 47));
        volver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        volver.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        inferior.add(volver);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBackground(java.awt.Color.white);
        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setBackground(java.awt.Color.white);
        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        new CategoriasRegistrar(this, true).setVisible(true);
    }//GEN-LAST:event_nuevoActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try {
            borrar();
            CargaCategorias();
        } catch (Exception ex) {
            /*Nothing here*/
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        try {
            Update();
            CargaCategorias();
        } catch (ClassNotFoundException | SQLException ex) {
            /*Nothing Here*/
        }
    }//GEN-LAST:event_actualizarActionPerformed

    /* Custom Method*/
    /**
     * Cambiar estado a una Categoría
     *
     * @throws ClassNotFoundException No se encuentra la librería de conexión
     */
    public void borrar() throws ClassNotFoundException {
        Control.conectar();
        int i = categoriaLista.getSelectedRow();
        int j = categoriaLista.getSelectedColumn();
        if (i == -1) {
            Entrada.muestreMensajeV("Favor... seleccione una fila",
                    JOptionPane.WARNING_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar esta categoría?", "BackBox", JOptionPane.YES_NO_OPTION) == 0) {
            String cod = (String) categoriaLista.getValueAt(i, 0).toString();
            boolean r = Control.ejecuteUpdate("update categoria "
                    + "set estado='I' " /*Estado Inactivo*/
                    + "where cod_categoria=" + cod);
            if (r) {
                Entrada.muestreMensajeV("Categoría Borrada con éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                CargaCategorias();
            } else {
                Entrada.muestreMensajeV(":Error Fatal: \nNo se pudo borrar la categoría\n::Revise su conexión",
                        JOptionPane.ERROR_MESSAGE);
            }
            Control.cerrarConexion();
        }
    }

    /**
     * Actuaizar una categoría
     *
     * @throws ClassNotFoundException no encuentra la librería de conexion
     * DataBase.
     * @throws java.sql.SQLException Error al realizar la consulta.
     */
    public void Update() throws ClassNotFoundException, SQLException {
        boolean r = false;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            int i = categoriaLista.getSelectedRow();
            int j = categoriaLista.getSelectedColumn();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
            } else {
                String nom = Entrada.leaCadenaV("Digite Nombre Nuevo");
                String cod = (String) categoriaLista.getValueAt(i, 0).toString();
                if (!nom.isEmpty()) {
                    r = Control.ejecuteUpdate("update categoria set descripcion='" + nom + "' where cod_categoria=" + cod);
                }
            }
        } catch (SQLException ex) {
            r = false;
            System.out.println("Error SQl " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        if (r) {
            CargaCategorias();
        } else {
            Entrada.muestreMensajeV("Error "
                    + "\nCategoría no se pudo actualizar correctamente"
                    + "\nDebido a un error en la consulta",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Iniciar la tabla definida en el inicio.
     *
     * @throws ClassNotFoundException no encuentra la librería de conexion
     * DataBase
     */
    private void CargaCategorias() throws ClassNotFoundException {
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        this.categoriaLista.setModel(modeloEmpleado);
        categoriaLista.setRowHeight(30);
        this.categoriaLista.setModel(modeloEmpleado);
        try {
            Control.conectar();
            Control.ejecuteQuery("select    "
                    + "codigo \"Código\","
                    + "descripcion \"Descripción\" , ganancia "
                    + "from CategoriaBusqueda()");
            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i < numeroPreguntas; i++) {
                modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }

            while (Control.rs.next()) {
                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
            categoriaLista.getColumnModel().getColumn(0).setMaxWidth(80);
            categoriaLista.getColumnModel().getColumn(1).setMinWidth(200);
            categoriaLista.getColumnModel().getColumn(2).setMinWidth(80);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton borrar;
    private javax.swing.JTable categoriaLista;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel derecha;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel izquierda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton nuevo;
    private javax.swing.JPanel superior;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
