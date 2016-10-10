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
import Modelo.List_Object;
import Modelo.Producto;
import Modelo.acciones;
import Modelo.seccion;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vw.components.Kits;
import vw.components.MaestroItem;
import vw.components.Promociones;
import vw.model.Venta;

/**
 *
 * @author usuario
 */
public class MenuItemRegistro2 extends javax.swing.JDialog {

    /**
     * Creates new form RolActualizar
     *
     * @param parent
     * @param modal
     * @param cod
     */
    MaestroItem MenuItem = null;
    seccion temp;
    int numKit;
    ArrayList<List_Object> list_Menu = new ArrayList();
    ArrayList<seccion> list_MenuFinal = new ArrayList();
    boolean condicionfiltro;
    int posicion;
    int permiso;
    int validacion;

    ArrayList<acciones> list_Item = new ArrayList();
    ArrayList<acciones> list_Item2 = new ArrayList();

    public MenuItemRegistro2(java.awt.Frame parent, boolean modal, ArrayList x, int validacion) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        this.condicionfiltro = false;
        this.MenuItem = (MaestroItem) parent;
        nombre.requestFocus();
        this.list_Menu = x;
        posicion = 0;
        this.permiso = 0;
        this.validacion = validacion;
        cargarDatos();

    }

    public void cargarDatos() {
        try {
            System.out.println("Inicio");
            int posiciones = 0;
            for (List_Object list_Object : list_Menu) {
                list_MenuFinal.add(new seccion(list_Object.getCod(), list_Object.getNom(), "Inactivo", posiciones));
                listMenus.addItem(list_Object.getNom());
                posiciones++;
            }
            list_MenuFinal.get(0).setEstado("Activo");
            Control.conectar();
            Control.ejecuteQuery("select codacciones,nom_accion from acciones where estado='Activo'");
            while (Control.rs.next()) {
                System.out.println("----------------");
                list_Item.add(new acciones(Control.rs.getInt(1), Control.rs.getString(2)));
                listItem.addItem(Control.rs.getString(2));
            }
            this.permiso = 1;

        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
    }

    public void cargarDatosUp() {
        try {
            Control.conectar();
            Control.ejecuteQuery("select distinct a.cod_actividad,b.codacciones,b.nom_accion from det_menuitem a , acciones b where b.codacciones=a.cod_accion and ordenmenu="
                    + validacion);
            
           

            int posiciones = 0;
            for (List_Object list_Object : list_Menu) {

                list_MenuFinal.add(new seccion(list_Object.getCod(), list_Object.getNom(), "Inactivo", posiciones));
                listMenus.addItem(list_Object.getNom());
                posiciones++;
            }
            list_MenuFinal.get(0).setEstado("Activo");
            Control.conectar();
            Control.ejecuteQuery("select codacciones,nom_accion from acciones where estado='Activo'");
            while (Control.rs.next()) {
                System.out.println("----------------");
                list_Item.add(new acciones(Control.rs.getInt(1), Control.rs.getString(2)));
                listItem.addItem(Control.rs.getString(2));
            }
            this.permiso = 1;

        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
    }

    public void registrarMenuItem() throws SQLException, ClassNotFoundException {
        boolean r = false;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            int num = Sequence.Next("sq_MenuItem");
            Control.ejecuteUpdate("Insert into menuitem values (" + num + ",'prueba','" + new Date() + "')");
            seccion temp;
            acciones temp2;
            for (int i = 0; i < list_MenuFinal.size(); i++) {
                temp = (seccion) list_MenuFinal.get(i);
                if (temp.getList_acciones().size() > 0) {
                    for (int k = 0; k < list_MenuFinal.get(i).getList_acciones().size(); k++) {
                        temp2 = (acciones) list_MenuFinal.get(i).getList_acciones().get(k);
                        if (temp2.getCod_actividad() == temp.getPosicion()) {
                            Control.ejecuteUpdate("Insert into det_menuitem values (nextval('sq_Det_MenuItem')," + temp.getCod_seccion()
                                    + "," + temp2.getCod_accion() + "," + num + ")");
                        }
                    }
                }
            }
            System.out.println(" ok ");

        } catch (Exception ex) {
            r = false;
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
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

        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        listMenus = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        listItem = new javax.swing.JComboBox<>();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion Promocion - BackBox");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        jButton1.setText("Siguiente");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, -1, -1));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 310, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel8.setText("Titulo");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        listMenus.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        listMenus.setToolTipText("");
        listMenus.setPreferredSize(new java.awt.Dimension(130, 28));
        listMenus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listMenusActionPerformed(evt);
            }
        });
        jPanel1.add(listMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 340, 30));

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 50, -1));

        jButton4.setText("↓");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 50, -1));

        jButton5.setText("↑");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 50, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel9.setText("Item");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo", "Menu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 530, 190));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel10.setText("Menus");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        listItem.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        listItem.setToolTipText("");
        listItem.setPreferredSize(new java.awt.Dimension(130, 28));
        listItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listItemActionPerformed(evt);
            }
        });
        jPanel1.add(listItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 340, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            registrarMenuItem();
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemRegistro2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemRegistro2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    private void listMenusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listMenusActionPerformed
        if (this.permiso == 1) {
            for (seccion list_obj2 : list_MenuFinal) {
                System.out.println("secion : " + list_obj2.getDescripcion());
                System.out.println(" cant acciones : " + list_obj2.getList_acciones().size());
            }
            System.out.println("posicion : " + posicion);
            posicion = listMenus.getSelectedIndex();

            for (seccion list_obj2 : list_MenuFinal) {
                list_obj2.setEstado("Inactivo");
            }

            list_MenuFinal.get(posicion).setEstado("Activo");
            System.out.println("posicion : " + posicion);

            Borrar2();
            for (seccion list_obj2 : list_MenuFinal) {
                System.out.println("secion : " + list_obj2.getDescripcion());
                System.out.println(" cant acciones : " + list_obj2.getList_acciones().size());
            }
//        list_Item2.clear();
            for (seccion list_obj2 : list_MenuFinal) {
                System.out.println("secion : " + list_obj2.getDescripcion());
                System.out.println(" cant acciones : " + list_obj2.getList_acciones().size());
            }
            IniciarItem();
            for (seccion list_obj2 : list_MenuFinal) {
                System.out.println("secion : " + list_obj2.getDescripcion());
                System.out.println(" cant acciones : " + list_obj2.getList_acciones().size());
            }
        }
    }//GEN-LAST:event_listMenusActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CargarItem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        System.out.println("preciono clik");
    }//GEN-LAST:event_jTable1MouseClicked

    private void listItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listItemActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        try {
            if (evt.getKeyCode() == 127) {
                Borrar();
            }
        } catch (Exception ex) {
            System.err.println("Error al borrar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable1KeyPressed

    public void CargarItem() {
        acciones temp = null;
        String titulo = listItem.getSelectedItem().toString();
        int cod = 0;
        boolean r = false;

        for (acciones list_obj : list_Item) {
            if (list_obj.getAccion().equals(titulo)) {
                cod = list_obj.getCod_accion();
                break;
            }
        }
        for (acciones list_obj2 : list_MenuFinal.get(posicion).getList_acciones()) {
            if (cod == list_obj2.getCod_accion() && list_obj2.getCod_actividad() == posicion) {
                r = true;
            }
        }
        System.out.println("r : " + r);
        if (r == false) {
            for (acciones list_obj : list_Item) {
                if (list_obj.getCod_accion() == cod) {
                    System.out.println("registro : " + list_obj.getAccion() + " posicion : " + posicion);
//                    list_obj.setCod_actividad(posicion);
                    temp = new acciones(posicion, list_obj.getCod_accion(), list_obj.getAccion());
                    break;
                }
            }
            list_Item2.add(temp);
            list_MenuFinal.get(posicion).setList_acciones(list_Item2);
        }

        for (acciones list_obj2 : list_Item2) {
            System.out.println(".. " + list_obj2.getAccion() + " - " + list_obj2.getCod_actividad());
        }

        IniciarItem();
        r = false;
    }

    public void IniciarItem() {
        System.out.println("Tamañ de productos " + list_MenuFinal.size());
        for (seccion list_obj2 : list_MenuFinal) {
            System.out.println("----- : " + list_obj2.getDescripcion() + " -  " + list_obj2.getEstado());
        }

        TablaModel t = new TablaModel(list_MenuFinal, 2, 4);
        t.ModelMaestroItem2();
        MostrarItem(t);

    }

    public void Borrar() {
        int i = jTable1.getSelectedRow();
        String cod = (String) jTable1.getValueAt(i, 0).toString();
        System.out.println("cod : " + cod);
        for (acciones list_obj2 : list_Item2) {
            if (Integer.parseInt(cod) == list_obj2.getCod_accion() && posicion == list_obj2.getCod_actividad()) {
                System.out.println("encontro");
                list_Item2.remove(list_obj2);
                break;
            }
        }

        for (int a = 0; a < 2; a++) {
            for (int k = 0; k < list_Item2.size() + 1; k++) {
                jTable1.setValueAt("", k, a);

            }
        }
        IniciarItem();
    }

    public void MostrarItem(TablaModel x) {
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable1.setValueAt(x.frecuencias[i][k], k, i);
            }
        }
    }

    public void Borrar2() {
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < list_Item2.size(); k++) {
                jTable1.setValueAt("", k, i);
            }
        }
    }

    public boolean SoloNumeros(String cadena) {
        double valor = 0;
        try {
            valor = Double.parseDouble(cadena);
            return true;
        } catch (Exception ex) {
            return false;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> listItem;
    private javax.swing.JComboBox<String> listMenus;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
