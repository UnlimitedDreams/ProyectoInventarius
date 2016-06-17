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
import vw.components.Entrada;
import vw.components.List_Categoria;
import vw.components.Producto;
import vw.components.Sequence;
import vw.model.Venta;

/**
 *
 * @author Miguel Lemoz
 */
public class RegistroCliente extends javax.swing.JDialog {

    /**
     * Creates new form UsuariosRegistrar
     */
    int SecuenciaCC;

    /**
     *
     * @param parent
     * @param modal
     */
    ArrayList<Producto> productos = new ArrayList();
    String usuario = "";
    Venta v = null;
    int condicion = 0;
    ArrayList<Integer> ListAcciones = new ArrayList();

    public RegistroCliente(java.awt.Frame parent, boolean modal, ArrayList p, String usu, ArrayList acciones, int condicion) {
        super(parent, modal);
        initComponents();
        this.usuario = usu;
        this.ListAcciones = acciones;
        this.condicion = condicion;
        if (condicion == 1) {
            this.v = (Venta) parent;
        }

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.productos = p;
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());

    }

    public boolean SoloNumeros(String cadena) {
        long num = 0;
        try {
            num = Long.parseLong(cadena);

            SecuenciaCC = cadena.length();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void registrar() throws ClassNotFoundException, SQLException {
        int cod_cliente = Sequence.seque("select max(cod_cliente) from clientes");
        Control.conectar();
        boolean r = Control.ejecuteUpdate("insert into clientes values(" + cod_cliente + ",'"
                + cedula.getText() + "','"
                + nombre.getText() + "','"
                + apellido.getText() + "','"
                + email.getText() + "'," + telefono.getText() + "," + cedular.getText() + ",'"
                + Dir.getText() + "')");
        if (r) {
            Control.cerrarConexion();
            if (condicion == 1) {
                v.Cliente.setText(cedula.getText());
            }

            this.dispose();

        } else {
            Entrada.muestreMensajeV("Error al grabar cliente ", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        Control.cerrarConexion();
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
        cedula = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        Dir = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cedular = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Clientes - BackBox");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cedula.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });
        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaKeyReleased(evt);
            }
        });
        jPanel1.add(cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 380, -1));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 380, -1));

        apellido.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 380, -1));

        Dir.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DirActionPerformed(evt);
            }
        });
        jPanel1.add(Dir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 380, -1));

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
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

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
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, -1, -1));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel5.setText("Apellido:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel6.setText("Direccion:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel9.setText("Telefono");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel10.setText("Celular");

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel11.setText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel3.setText("Cedula:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(11, 11, 11)
                .addComponent(jLabel6)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 520));

        cedular.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        cedular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedularActionPerformed(evt);
            }
        });
        jPanel1.add(cedular, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 380, -1));

        email.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 380, -1));

        telefono.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 380, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_cedulaActionPerformed

    private void cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyPressed

    }//GEN-LAST:event_cedulaKeyPressed

    private void cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyReleased
        boolean v = SoloNumeros(cedula.getText());
        if (v == false) {
            cedula.setText(cedula.getText().substring(0, SecuenciaCC));
        }
    }//GEN-LAST:event_cedulaKeyReleased

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_apellidoActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (this.nombre.getText().isEmpty()
                || this.cedula.getText().isEmpty()
                || this.apellido.getText().isEmpty()
                || this.Dir.getText().isEmpty()) {
            Entrada.muestreMensajeV("No se permiten Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                registrar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_nombreActionPerformed

    private void DirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DirActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_DirActionPerformed

    private void cedularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedularActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
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
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                UsuariosRegistrar dialog = new UsuariosRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField Dir;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField cedular;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField telefono;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}