/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Control;
import Control.Entrada;
import Modelo.ContenedorMenus;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.Toolkit;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vw.dialogs.IvaCrear;
import vw.dialogs.IvaModificar;
import vw.main.Menu;

/**
 *
 * @author usuario
 */
public class MaestroMenu extends javax.swing.JFrame {

    String usuario;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    /**
     * Creates new form categoriaGestion
     *
     * @param Usuario
     * @param Acciones
     */
    //, ArrayList Acciones
    public MaestroMenu(String Usuario) {
        initComponents();
        // this.List_Menu = Acciones;
        this.usuario = Usuario;
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
        try {
            inicio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaestroMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CrearMenu() throws SQLException, ClassNotFoundException {
        boolean r = false;
        try {
            String titulo = Entrada.leaCadenaV("Ingrese Titulo de Menu");
            Control.conectar();
            Control.con.setAutoCommit(false);
            r = Control.ejecuteUpdate("insert into actividades values(nextval('sq_actividades'),'" + titulo + "','Activo')");
        } catch (SQLException ex) {
            System.out.println("Error de crear " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (r) {
            Entrada.muestreMensajeV("Menu Creado Con Exito");
            inicio();
        } else {
            Entrada.muestreMensajeV("Error al crear Menu");
        }
    }

    public void ActualizarMenu() throws SQLException, ClassNotFoundException {
        boolean r = false;
        try {
            int i = categoriaLista.getSelectedRow();
            if (i == -1) {
                Entrada.muestreMensajeV("Favor... seleccione una fila",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String codigoMenu = (String) categoriaLista.getValueAt(i, 0).toString();
                String nombreMenu = (String) categoriaLista.getValueAt(i, 1).toString();
                String titulo = Entrada.leaCadenaV(nombreMenu);
                Control.conectar();
                Control.con.setAutoCommit(false);
                r = Control.ejecuteUpdate("update actividades set nombre='" + titulo + "' where codigoact=" + Integer.parseInt(codigoMenu));
            }

        } catch (SQLException ex) {
            /*Nothing Here*/
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (r) {
            Entrada.muestreMensajeV("Actualizacion Exitosa");
            inicio();
        } else {
            Entrada.muestreMensajeV("Error al hacer Actualizacion");
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

        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriaLista = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        inferior = new javax.swing.JPanel();
        infIzq = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        infDer = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Iva - Inventarius");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(800, 600));

        centro.setBackground(new java.awt.Color(255, 255, 255));
        centro.setLayout(new javax.swing.BoxLayout(centro, javax.swing.BoxLayout.LINE_AXIS));

        categoriaLista.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(categoriaLista);

        centro.add(jScrollPane1);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.GridLayout(1, 0));

        infIzq.setBackground(java.awt.Color.white);
        infIzq.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

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
        infIzq.add(nuevo);

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
        infIzq.add(borrar);

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
        infIzq.add(actualizar);

        inferior.add(infIzq);

        infDer.setBackground(java.awt.Color.white);
        infDer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(160, 40));
        jSeparator1.setRequestFocusEnabled(false);
        infDer.add(jSeparator1);

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
        infDer.add(volver);

        inferior.add(infDer);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        try {
            CrearMenu();
        } catch (SQLException ex) {
            Logger.getLogger(MaestroMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaestroMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nuevoActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try {
            borrar();
            inicio();
        } catch (Exception ex) {
            /*Nothing here*/
        }

    }//GEN-LAST:event_borrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        try {
            ActualizarMenu();
        } catch (SQLException ex) {
            Logger.getLogger(MaestroMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaestroMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_actualizarActionPerformed

    public void borrar() throws ClassNotFoundException, SQLException {
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);

            boolean r = false;
            int i = categoriaLista.getSelectedRow();
            if (i == -1) {
                Entrada.muestreMensajeV("Favor... seleccione una fila",
                        JOptionPane.WARNING_MESSAGE);
            } else if (JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar el Menu?", "Inventarius", JOptionPane.YES_NO_OPTION) == 0) {
                String cod = (String) categoriaLista.getValueAt(i, 0).toString();
                r = Control.ejecuteUpdate("update actividades "
                        + "set estado='Inactivo' " /*Estado Inactivo*/
                        + "where codigoact=" + Integer.parseInt(cod));

                if (r) {
                    Entrada.muestreMensajeV("Menu Borrado con éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Entrada.muestreMensajeV(":Error Fatal: \nNo se pudo borrar el Menu\n::Revise su conexión",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error : " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        inicio();
    }

    /**
     * Actuaizar una categoría
     *
     * @throws ClassNotFoundException no encuentra la librería de conexion
     * DataBase
     */
    /**
     * Iniciar la tabla definida en el inicio.
     *
     * @throws ClassNotFoundException no encuentra la librería de conexion
     * DataBase
     */
    public void inicio() throws ClassNotFoundException {
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        this.categoriaLista.setModel(modeloEmpleado);
        try {
            Control.conectar();
            Control.ejecuteQuery("select codigoact,nombre from actividades where estado='Activo' order by nombre");
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CategoriaGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CategoriaGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CategoriaGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CategoriaGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CategoriaGestion().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton borrar;
    private javax.swing.JTable categoriaLista;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel infDer;
    private javax.swing.JPanel infIzq;
    private javax.swing.JPanel inferior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton nuevo;
    private javax.swing.JPanel superior;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
