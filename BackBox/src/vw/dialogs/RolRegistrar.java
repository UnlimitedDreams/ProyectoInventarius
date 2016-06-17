/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.components.Entrada;
import vw.components.Sequence;

/**
 *
 * @author Miguel Lemoz
 */
public class RolRegistrar extends javax.swing.JDialog {

    /**
     * Creates new form RolRegistrar
     *
     * @param parent
     * @param modal
     */
    public RolRegistrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    public void registrar() {

        try {
            int codigo = Sequence.seque("select max(cod_rol) from rol");
            int codigoAct = Sequence.seque("select max(cod_detalleAc) from detalleactividad");
            Control.conectar();
            boolean r = Control.ejecuteUpdate("insert into rol values(" + codigo + ",'" + nombreRol.getText() + "','A')");
            if (r) {

                if (jCheckBox3.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",4)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox13.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",9)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",10)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox12.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",11)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",12)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox23.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",1)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",2)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox17.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + ",20)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + ",21)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + ",22)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + ",3)");
                }
                if (jCheckBox14.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + ",16)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + ",17)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + ",18)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox26.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",5)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",6)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
                    codigoAct++;
                }
                if (role.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",7)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",8)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
                    codigoAct++;
                }
                if (jCheckBox15.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",30)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",31)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
                    codigoAct++;
                }
                

                Control.cerrarConexion();
                this.dispose();
            } else {
                Entrada.muestreMensajeV("ERROR");

            }

            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RolRegistrar.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel4 = new javax.swing.JLabel();
        nombreRol = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        role = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Rol - BackBox");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre Rol: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        nombreRol.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(nombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 260, -1));

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setBorder(null);
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setPreferredSize(new java.awt.Dimension(55, 47));
        guardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Acciones:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Bodega");
        jCheckBox3.setOpaque(false);
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        jCheckBox13.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Articulos");
        jCheckBox13.setOpaque(false);
        jPanel1.add(jCheckBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        jCheckBox12.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox12.setSelected(true);
        jCheckBox12.setText("Categoria");
        jCheckBox12.setOpaque(false);
        jPanel1.add(jCheckBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox14.setSelected(true);
        jCheckBox14.setText("Venta");
        jCheckBox14.setOpaque(false);
        jPanel1.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Reporte");
        jCheckBox17.setOpaque(false);
        jPanel1.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        jCheckBox23.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox23.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox23.setSelected(true);
        jCheckBox23.setText("Usuario");
        jCheckBox23.setOpaque(false);
        jPanel1.add(jCheckBox23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        role.setBackground(new java.awt.Color(255, 255, 255));
        role.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        role.setSelected(true);
        role.setText("Roles");
        role.setOpaque(false);
        jPanel1.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/triangulo.gif"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-270, -10, -1, -1));

        jCheckBox26.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox26.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox26.setSelected(true);
        jCheckBox26.setText("Provedores");
        jCheckBox26.setOpaque(false);
        jPanel1.add(jCheckBox26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        jCheckBox15.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox15.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox15.setSelected(true);
        jCheckBox15.setText("Clientes");
        jCheckBox15.setOpaque(false);
        jCheckBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox15ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        registrar();
    }//GEN-LAST:event_guardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox15ActionPerformed

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
//            java.util.logging.Logger.getLogger(RolRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RolRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RolRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RolRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RolRegistrar dialog = new RolRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreRol;
    private javax.swing.JCheckBox role;
    // End of variables declaration//GEN-END:variables
}
