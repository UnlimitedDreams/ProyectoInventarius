/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import Modelo.acciones;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Angel Lemos
 */
public class Acciones extends javax.swing.JDialog {

    /**
     * Creates new form AcercaDe
     *
     * @param parent
     * @param modal
     */
    ArrayList<acciones> listAcciones = new ArrayList();
    ArrayList<acciones> listOrden = new ArrayList();
    RolRegistrar rol;
    RolUpdate rolU;
    int condicion;

    public Acciones(java.awt.Frame parent, boolean modal, int condicion) {
        super(parent, modal);
        initComponents();
        this.condicion = condicion;
        if (condicion == 1) {
            rol = (RolRegistrar) parent;
        } else if (condicion == 2) {
            rolU = (RolUpdate) parent;
        }

        this.setLocationRelativeTo(null);
        try {
            inicio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de BackBox");
        setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 310, 270));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        añadirDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void añadirDatos() {

        int rowIndexStart = jTable1.getSelectedRow();
        int rowIndexEnd = jTable1.getSelectionModel().getMaxSelectionIndex();
        int colIndexStart = jTable1.getSelectedColumn();
        int colIndexEnd = jTable1.getColumnModel().getSelectionModel().getMaxSelectionIndex();

// Check each cell in the range
        for (int r = rowIndexStart; r <= rowIndexEnd; r++) {
            for (int c = colIndexStart; c <= colIndexEnd; c++) {
                if (jTable1.isCellSelected(r, c)) {
                    acciones a = new acciones(Integer.parseInt((String) jTable1.getValueAt(r, 0).toString().trim()),
                            (String) jTable1.getValueAt(r, 1).toString());

                    listAcciones.add(a);

// cell is selected
                }
            }
        }

        for (acciones object : listAcciones) {
            for (acciones object1 : listOrden) {

                if (object1.getCod_seccion() == object.getCod_seccion()) {
                    object.setOrden(object1.getOrden());
                    System.out.println("agrego");
                }
            }
        }
        boolean r = false;
        if (condicion == 1) {
            for (acciones object : listAcciones) {
                r = false;
                for (acciones object1 : rol.listaaccion) {
                    if (object.getCod_seccion() == object1.getCod_seccion()) {
                        r = true;
                    }
                }
                if (r == false) {
                    rol.listaaccion.add(object);
                }
            }
            rol.iniciar();
        } else if (condicion == 2) {
            for (acciones object : listAcciones) {
                r = false;
                for (acciones object1 : rolU.listaaccion) {
                    if (object.getCod_seccion() == object1.getCod_seccion()) {
                        r = true;
                    }
                }
                if (r == false) {
                    rolU.listaaccion.add(object);
                }
            }
            rolU.iniciar();

        }

        this.dispose();
    }

    private void inicio() throws ClassNotFoundException {

        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        String cabeceras[] = {"Cod", "Accion"};
        modeloEmpleado = new DefaultTableModel(null, cabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        this.jTable1.setModel(modeloEmpleado);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(500);

        jTable1.setRowHeight(15);
        this.jTable1.setModel(modeloEmpleado);

        try {
            Control.conectar();
            Control.ejecuteQuery("select * from acciones where codacciones not in (3,17,18) order by orden");
            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            while (Control.rs.next()) {
                listOrden.add(new acciones(Control.rs.getInt(1), Control.rs.getInt(3)));
                Object[] registroEmpleado = new Object[numeroPreguntas];
                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Cargar Bodega " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }
//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(AcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AcercaDe dialog = new AcercaDe(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
