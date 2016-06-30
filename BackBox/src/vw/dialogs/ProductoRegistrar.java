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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.components.Entrada;
import vw.components.List_Categoria;
import vw.components.Producto;
import vw.components.Sequence;

/**
 *
 * @author usuario
 */
public class ProductoRegistrar extends javax.swing.JDialog {

    private int cod;
    ArrayList<Producto> pr = new ArrayList();
    String nom;
    String fac;
    ArrayList<Integer> ListAcciones = new ArrayList();

    /**
     * Creates new form ProductoRegistrar
     *
     * @param parent
     * @param modal
     */
    public ProductoRegistrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        Categoria();
        codigo.setText("" + 0);
        costoF.setText("" + 0);
        ivaF.setText("" + 0);
        cantidad.setText("" + 0);
        precioVenta.setText("" + 0);
        stock.setText("" + 0);
    }

    public void Categoria() {
        ArrayList<List_Categoria> categorias = new ArrayList();
        try {
            Control.conectar();
            Control.ejecuteQuery("select * from categoria");
            while (Control.rs.next()) {
                categorias.add(new List_Categoria(Control.rs.getInt(1), Control.rs.getString(2)));
                categoria.addItem(Control.rs.getString(2) + "-" + Control.rs.getString(4));
            }
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al conectar" + ex.toString());
        } catch (SQLException ex) {
            System.out.println("Sintaxis de la consulta mal hecha" + ex.toString());
        }
    }

    public int buscar_cod() throws ClassNotFoundException {
        int cant = 0;
        try {
            Control.ejecuteQuery("select count(*) from producto a, venta_pro p , venta v where a.cod_producto=p.cod_prodcuto and p.cod_factura=v.cod_factura and \n"
                    + "cod_producto='" + codigo.getText() + "'");

            while (Control.rs.next()) {
                cant = Control.rs.getInt(1);
            }

        } catch (Exception ex) {

        }
        System.out.println("Devuelve : " + cant);
        return cant;
    }

    public void registrar() throws ClassNotFoundException, SQLException {
        boolean proceso = false;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            if (buscar_cod() == 0) {
                int categoria = traerCod();

                double costo = Double.parseDouble(costoF.getText());
                double precio = Double.parseDouble(precioVenta.getText());
                String desc = "0";
                double des = 0;
                String iva = "0";
                double ivas = 0;
                double precioSinDEs = Double.parseDouble(precioVenta.getText());

                if (ivaF.getText().equalsIgnoreCase("0")) {

                } else {
                    iva = "0." + ivaF.getText();
                    ivas = costo * Double.parseDouble(iva);
                    precio = precio + ivas;
                    precioSinDEs = precioSinDEs + ivas;
                }
                System.out.println("::::::::::::::: Cantidad de bonos " + categoria);
                boolean r = Control.ejecuteUpdate("insert into producto values('" + codigo.getText() + "','" + nombre.getText() + "',"
                        + costoF.getText() + "," + ivaF.getText() + "," + precioSinDEs + ","
                        + categoria + "," + 0 + ",'A','n',0," + precio + "," + Integer.parseInt(stock.getText()) + ",0)");
                if (r) {
                    Entrada.muestreMensajeV("SE AGREGGO PRODUCTO A LA COMPRA");
                    pr.add(new Producto(codigo.getText(), nombre.getText(), Double.parseDouble(costoF.getText()), precio,
                            Integer.parseInt(cantidad.getText())));
                    System.out.println("Se registro todo bien");
                    proceso = true;

                } else {
                    Entrada.muestreMensajeV("Error al Agregar Producto");
                }

            } else {
                Entrada.muestreMensajeV("El producto que esta tratando de registrar ya existe con una Transacccion");
            }

        } catch (NumberFormatException ex) {
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (proceso) {
            System.out.println("Entro a nueva ventana");
            //Entrada_Nueva ar = new Entrada_Nueva(pr, nom, fac, ListAcciones);
            this.setVisible(false);
            //ar.setVisible(true);
        }

    }

    public int traerCod() throws ClassNotFoundException {
        String nom[] = categoria.getSelectedItem().toString().split("-");
        int cod = 0;
        try {
            System.out.println("select cod_categoria from categoria where descripcion='" + nom[0] + "'");
            Control.ejecuteQuery("select cod_categoria from categoria where descripcion='" + nom[0] + "'");

            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
        return cod;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        costoF = new javax.swing.JTextField();
        ivaF = new javax.swing.JTextField();
        precioVenta = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        categoria = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel2.setText("Categoría");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel3.setText("Código");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 70, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 70, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel5.setText("Costo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 60, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel6.setText("% IVA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 80, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel8.setText("Cantidad");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 80, 30));

        codigo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 380, 32));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 380, 32));

        costoF.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(costoF, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 380, 32));

        ivaF.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(ivaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 380, 32));

        precioVenta.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(precioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 380, 32));

        cantidad.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 380, 32));

        categoria.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 380, 32));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        jButton1.setText("Registrar");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel7.setText("Precio Venta");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel12.setText("Stock");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(180, 180, 180))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 510));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel9.setText("Stock");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        stock.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 380, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel10.setText("Precio Venta");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel11.setText("Precio Venta");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            registrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
//            java.util.logging.Logger.getLogger(ProductoRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ProductoRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ProductoRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ProductoRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ProductoRegistrar dialog = new ProductoRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField cantidad;
    private javax.swing.JComboBox categoria;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField costoF;
    private javax.swing.JTextField ivaF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precioVenta;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables
}
