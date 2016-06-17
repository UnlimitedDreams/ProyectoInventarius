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

    public boolean buscar_cod() throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;
        try {
            Control.ejecuteQuery("select * from producto where "
                    + "cod_producto='" + codigo.getText() + "'");

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

    public boolean registro_detalle() throws ClassNotFoundException {
        Control.conectar();
        Date fecha = new Date();
        int codigo_detalle = Sequence.seque("select max(cod_detalle) from detalle");
        boolean r = Control.ejecuteUpdate("insert into detalle values(" + codigo_detalle + ",'" + fecha + "',"
                + cantidad.getText() + "," + costo.getText() + ",'" + codigo.getText() + "',"
                + cod + ")");

        if (r) {

        } else {
            Entrada.muestreMensajeV("ERROR 3");

        }
        Control.cerrarConexion();

        return r;
    }

    public void registrar() throws ClassNotFoundException {
        if (buscar_cod() == false) {

            int categoria = traerCod();
            Control.conectar();
            double costo = Double.parseDouble(this.costo.getText());
            double precio = Double.parseDouble(precioVenta.getText());
            String desc = "0";
            double des = 0;
            String iva = "0";
            double ivas = 0;
            double precioSinDEs = Double.parseDouble(precioVenta.getText());

            if (this.iva.getText().equalsIgnoreCase("0")) {

            } else {
                iva = "0." + this.iva.getText();
                ivas = costo * Double.parseDouble(iva);
                precio = precio + ivas;
                precioSinDEs = precioSinDEs + ivas;
            }

            boolean r = Control.ejecuteUpdate("insert into producto values('"
                    + codigo.getText() + "','"
                    + nombre.getText() + "',"
                    + this.costo.getText() + ","
                    + this.iva.getText() + ","
                    + precioSinDEs + ","
                    + categoria + ","
                    + 0
                    + ",'A','n',0,"
                    + precio + ")");
            if (r) {

                Entrada.muestreMensajeV("REGISTRO EXITOSO");
                pr.add(new Producto(codigo.getText(),
                        nombre.getText(),
                        Double.parseDouble(this.costo.getText()),
                        precio,
                        Integer.parseInt(cantidad.getText())));

                this.dispose();

            } else {
                Entrada.muestreMensajeV("ERROR 2");
            }
            Control.cerrarConexion();

        } else {
            Entrada.muestreMensajeV("El codigo del producto ya existe");
        }

    }

    public void borrarTodo() throws ClassNotFoundException {
        int cod = Sequence.seque("select max(cod_producto) from producto");
        codigo.setText("" + cod);
        nombre.setText("");
        costo.setText("");
        iva.setText("");
        precioVenta.setText("");
        cantidad.setText("");
    }

    public int traerCod() throws ClassNotFoundException {
        Control.conectar();
        int cod = 0;
        String nom[] = categoria.getSelectedItem().toString().split("-");
        try {
            Control.ejecuteQuery("select cod_categoria from categoria where descripcion='" + nom[0] + "'");
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
            Control.cerrarConexion();

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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        costo = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        precioVenta = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        categoria = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel2.setText("Categoría");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel3.setText("Código");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 70, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel5.setText("Costo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 60, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel6.setText("% IVA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 80, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel7.setText("Precio Venta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel8.setText("Cantidad");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 80, 30));

        codigo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 380, 28));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 380, 32));

        costo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 380, 32));

        iva.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 380, 31));

        precioVenta.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(precioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 380, 31));

        cantidad.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 380, 32));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            registrar();
        } catch (ClassNotFoundException ex) {
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
    private javax.swing.JTextField costo;
    private javax.swing.JTextField iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precioVenta;
    // End of variables declaration//GEN-END:variables
}
