/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import Control.Entrada;
import Control.Sequence;

/**
 *
 * @author Miguel Lemoz
 */
public class ProveedoresRegistrar extends javax.swing.JDialog {

    String usuario;
    ArrayList<Integer> ListAcciones = new ArrayList();

    public ProveedoresRegistrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    public boolean buscar_cod() throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;
        try {
            Control.ejecuteQuery("select * from persona where "
                    + "cedula=" + nit.getText() + "");
            while (Control.rs.next()) {
                r = true;
            }
            Control.cerrarConexion();
        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
        return r;
    }

    public void registrar() throws ClassNotFoundException, SQLException {
        if (buscar_cod() == false) {
            int secUsuario = Sequence.seque("select max(cod_provedor) from provedor");
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);
                boolean ejecutoUpdate = Control.ejecuteUpdate("insert into provedor"
                        + " values(" + secUsuario + ",'"
                        + nit.getText() + "','"
                        + nombre.getText() + "','"
                        + telefono.getText() + "','"
                        + direccion.getText() + "',"
                        + "'A')");
                if (ejecutoUpdate) {
                    Entrada.muestreMensajeV("REGISTRO EXITOSO",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    Entrada.muestreMensajeV("ERROR",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error:" + ex.toString());
            } finally {
                Control.con.commit();
                Control.con.setAutoCommit(true);
                Control.cerrarConexion();
            }
        } else {
            Entrada.muestreMensajeV("La cedula  ya existe",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
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
        nit = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        direccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Proveedor - BackBox");
        setPreferredSize(new java.awt.Dimension(535, 290));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nit.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nitActionPerformed(evt);
            }
        });
        jPanel1.add(nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 370, 30));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 370, 30));

        telefono.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 370, 30));

        registrar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setBorder(null);
        registrar.setBorderPainted(false);
        registrar.setContentAreaFilled(false);
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.setPreferredSize(new java.awt.Dimension(55, 47));
        registrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        registrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

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
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        direccion.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 370, 30));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel3.setText("NIT:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Empresa:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel5.setText("Teléfono:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel6.setText("Dirección:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 124, 265));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (!this.nit.getText().isEmpty()
                || !this.nombre.getText().isEmpty()
                || !this.direccion.getText().isEmpty()
                || !this.telefono.getText().isEmpty()) {
            try {
                registrar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProveedoresRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Entrada.muestreMensajeV("Error Al Registrar Proveedor");
            }
        } else {
            Entrada.muestreMensajeV("No puede haber campos vacíos",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_direccionActionPerformed

    private void nitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nitActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_nitActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_nombreActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_telefonoActionPerformed

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
//            java.util.logging.Logger.getLogger(ProveedoresRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ProveedoresRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ProveedoresRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ProveedoresRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ProveedoresRegistrar dialog = new ProveedoresRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField direccion;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nit;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField telefono;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
