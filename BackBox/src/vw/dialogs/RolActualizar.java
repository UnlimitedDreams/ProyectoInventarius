/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.acciones;
import Modelo.seccion;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import vw.components.Entrada;
import vw.components.Sequence;

/**
 *
 * @author usuario
 */
public class RolActualizar extends javax.swing.JDialog {

    String ced = "";
    String nomRol="";
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    /**
     * Creates new form RolActualizar
     *
     * @param parent
     * @param modal
     * @param cod
     */
    public RolActualizar(java.awt.Frame parent, boolean modal, String cod, ArrayList menu,String NomRol) {
        super(parent, modal);
        initComponents();
        this.List_Menu = menu;
        this.nomRol=NomRol;
        jTextField3.setText(nomRol);
        ContenedorMenus con_menu = new ContenedorMenus();
        con_menu = (ContenedorMenus) List_Menu.get(0);
        listaSeccion = con_menu.getListaSeccion();
        listaaccion = con_menu.getListaAcciones();
        for (seccion object : listaSeccion) {
            if(object.getCod_seccion()==1){
                jCheckBox3.setSelected(true);
            }else  if(object.getCod_seccion()==2){
                jCheckBox14.setSelected(true);
            }
        }
        this.ced = cod;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    public void update() throws ClassNotFoundException {
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Actualizar el Rol? ", op);
        if (Condicion == 1) {
            int codigoAct = Sequence.seque("select max(cod_detalleAc) from detalleactividad");
            Control.conectar();
            boolean r = Control.ejecuteUpdate("update rol set "
                    + "descripcion='" + jTextField3.getText() + "' where "
                    + "cod_rol=" + ced);
            if (r) {

                Control.ejecuteUpdate("delete from detalleactividad where cod_rol=" + ced);
                Control.ejecuteUpdate("update rol set descripcion='"+jTextField3.getText()+"' where cod_rol=" + ced);

                if (jCheckBox3.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",4)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox13.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",9)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",10)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox12.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",11)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",12)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox23.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",1)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",2)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox17.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",20)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",21)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",22)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox14.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",16)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",17)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",18)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",3)");
                    codigoAct++;
                }
                if (jCheckBox25.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",5)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",6)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                }
                if (role.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",7)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",8)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                }
                 if (jCheckBox15.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",30)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",31)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                }
                
                this.dispose();
            }
            Control.cerrarConexion();
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
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        role = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox25 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Rol - BackBox");
        setPreferredSize(new java.awt.Dimension(500, 370));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTextField3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 330, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        jButton1.setText("Actualizar");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, -1));

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
        jPanel1.add(jCheckBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Bodega");
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jCheckBox13.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Articulos");
        jCheckBox13.setOpaque(false);
        jPanel1.add(jCheckBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jCheckBox12.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox12.setSelected(true);
        jCheckBox12.setText("Categoria");
        jCheckBox12.setOpaque(false);
        jPanel1.add(jCheckBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox14.setSelected(true);
        jCheckBox14.setText("Venta");
        jCheckBox14.setOpaque(false);
        jPanel1.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        role.setBackground(new java.awt.Color(255, 255, 255));
        role.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        role.setSelected(true);
        role.setText("Roles");
        role.setOpaque(false);
        jPanel1.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        jCheckBox23.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox23.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox23.setSelected(true);
        jCheckBox23.setText("Usuario");
        jCheckBox23.setOpaque(false);
        jPanel1.add(jCheckBox23, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Reporte");
        jCheckBox17.setOpaque(false);
        jPanel1.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Acciones");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/triangulo.gif"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 0, -1, -1));

        jCheckBox25.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox25.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox25.setSelected(true);
        jCheckBox25.setText("Provedores");
        jCheckBox25.setOpaque(false);
        jPanel1.add(jCheckBox25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            update();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RolActualizar dialog = new RolActualizar(new javax.swing.JFrame(), true, "1");
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
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JCheckBox role;
    // End of variables declaration//GEN-END:variables
}