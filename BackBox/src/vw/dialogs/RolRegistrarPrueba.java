/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import Control.Entrada;
import Control.Sequence;
import Control.TablaModel;
import Modelo.ContenedorMenus;
import Modelo.acciones;
import Modelo.seccion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import vw.dialogs.Acciones;

/**
 *
 * @author usuario
 */
public class RolRegistrarPrueba extends javax.swing.JFrame {

    String nomRol = "";
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();
    ArrayList<acciones> listAcciones = new ArrayList();

    /**
     * Creates new form RolActualizar
     *
     * @param parent
     * @param modal
     * @param cod
     */
    public RolRegistrarPrueba() {
        initComponents();

//        ContenedorMenus con_menu = new ContenedorMenus();
//        con_menu = (ContenedorMenus) List_Menu.get(0);
//        listaSeccion = con_menu.getListaSeccion();
//        listaaccion = con_menu.getListaAcciones();
//        for (seccion object : listaSeccion) {
//            if (object.getCod_seccion() == 1) {
//                jCheckBox3.setSelected(true);
//            } else if (object.getCod_seccion() == 2) {
//                jCheckBox14.setSelected(true);
//            }
//        }
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    public void iniciar() {
        System.out.println("Tamañ de productos " + listaaccion.size());
        TablaModel t = new TablaModel(listaaccion, 1, 2);
        t.RegistroRolTable();
        muevaLosDatosFre(t);

    }

    public void Borrar() {
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < listaaccion.size() + 1; k++) {
                jTable1.setValueAt("", k, i);
            }
        }
        iniciar();
    }

    public void muevaLosDatosFre(TablaModel x) {
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable1.setValueAt(x.frecuencias[i][k], k, i);
            }
        }
    }

    public void acomodarDatos(ArrayList<acciones> x) {
        Collections.sort(x, new Comparator<acciones>() {
            public int compare(acciones o1, acciones o2) {
                return new Integer(o1.getOrden()).compareTo(new Integer(o1.getOrden()));
            }
        });
    }

    public void Insertar() throws ClassNotFoundException, SQLException {
        acomodarDatos(listaaccion);
        boolean cerrar = false;
        try {
            int codigo = Sequence.seque("select max(cod_rol) from rol");
            int codigoAct = Sequence.seque("select max(cod_detalleAc) from detalleactividad");
            Control.conectar();
            Control.con.setAutoCommit(false);
            int a = 0;
            boolean validacion = false;
            boolean r = Control.ejecuteUpdate("insert into rol values(" + codigo + ",'" + jTextField3.getText() + "','A')");
            if (r) {
                System.out.println("insert rol");
                for (acciones object : listaaccion) {
                    System.out.println(object.toString());
                    System.out.println("-- registrar - " + object.getAccion());
                    validacion = false;
                    if (object.getAccion().equalsIgnoreCase("Bodega")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Articulos")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
//                        codigoAct++;

                    } else if (object.getAccion().equalsIgnoreCase("Nueva Compra")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
//                        codigoAct++;

                    }
                    System.out.println("- " + a++);
                    if (object.getAccion().equalsIgnoreCase("Categoria")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
//                        codigoAct++;
                    }
                    if (object.getAccion().trim().equalsIgnoreCase("Crear Categoria")) {
                        System.out.println("entro crear cate");
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Usuarios")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    } else if (object.getAccion().equalsIgnoreCase("Crear Usuario")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                    System.out.println("- " + a++);
                    if (object.getAccion().equalsIgnoreCase("Realizar Venta")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + "," + 16 + ")");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + "," + 17 + ")");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + "," + 18 + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Reporte Venta")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
                        validacion = true;
                    }

                    if (object.getAccion().equalsIgnoreCase("Reporte Compras")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
                        validacion = true;
                    }

                    if (object.getAccion().equalsIgnoreCase("Reporte Entrada y Salidas")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
                        validacion = true;
                    }
//                    if (validacion) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + codigo + ",3)");
//                        codigoAct++;
//                    }

                    System.out.println("- " + a++);
                    if (object.getAccion().equalsIgnoreCase("Proveedores")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    } else if (object.getAccion().equalsIgnoreCase("Crear Proveedor")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                    System.out.println("- " + a++);
                    if (object.getAccion().equalsIgnoreCase("Rol")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    } else if (object.getAccion().equalsIgnoreCase("Crear Rol")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                    if (object.getAccion().equalsIgnoreCase("Clientes")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                    if (object.getAccion().equalsIgnoreCase("Nuevo Clientes")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                    if (object.getAccion().equalsIgnoreCase("Iva")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",19," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Promocion")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",19," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Crear Promocion")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",19," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Kits")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",19," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }

                    if (object.getAccion().equalsIgnoreCase("Crear Kits")) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",19," + codigo + "," + object.getCod_seccion() + ")");
                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + codigo + ",3)");
//                        codigoAct++;
                    }
                }

            }

            cerrar = true;
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.toString());
            cerrar = false;
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (cerrar) {
            this.dispose();
        } else {
            Entrada.muestreMensajeV("Error Comuniquese con soporte");
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

        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Rol - BackBox");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jTextField3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 330, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Funciones");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 460, 200));

        jButton3.setText("Agregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Insertar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Acciones(this, true).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introducodigo in Java SE 6) is not available, stay with the default look and feel.
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
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
