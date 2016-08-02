/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import Control.Entrada;
import Control.Sequence;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author usuario
 */
public class IvaCrear extends javax.swing.JDialog {

    /**
     * Creates new form CategoriasRegistrar
     */

    public IvaCrear(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        for (int i = 0; i < 100; i++) {            
                porcen.addItem("" + i + " %");            
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
        descrip = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        porcen = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Categoria");

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descrip.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jPanel1.add(descrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 380, 32));

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        salir.setBorder(null);
        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setPreferredSize(new java.awt.Dimension(55, 47));
        salir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel1.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel4.setText("Porcentaje");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel7.setText("Nombre:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(78, 78, 78))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 210));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel5.setText("Serie:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel6.setText("Serie:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        porcen.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        porcen.setToolTipText("Método de Pago del Cliente");
        porcen.setPreferredSize(new java.awt.Dimension(130, 28));
        porcen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcenActionPerformed(evt);
            }
        });
        jPanel1.add(porcen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 380, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Insert();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IvaCrear.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IvaCrear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void porcenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcenActionPerformed

    }//GEN-LAST:event_porcenActionPerformed

    public void Insert() throws ClassNotFoundException, SQLException {
        boolean r = false;
        int codIva=Sequence.seque("select max(codiva) from maestro_iva");
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);            
            String valorIva[] = porcen.getSelectedItem().toString().split("%");
            r = Control.ejecuteUpdate("insert into  maestro_iva values(" + codIva + ",'"+descrip.getText()+"',"+valorIva[0]+",'A')");                    
            if (r) {                
                Entrada.muestreMensajeV("Exito a Crear",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                Entrada.muestreMensajeV("Erro al Crear Iva");
            }
        } catch (SQLException ex) {
            System.out.println("Error haciendo el Update " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        if (r) {
            this.dispose();
        }
    }

    public boolean buscarSerie(String serie) throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;

        try {
            Control.ejecuteQuery("select *  from categoria where serie='" + serie + "'");
            r = false;

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
//            java.util.logging.Logger.getLogger(CategoriasRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CategoriasRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CategoriasRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CategoriasRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CategoriasRegistrar dialog = new CategoriasRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField descrip;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> porcen;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
