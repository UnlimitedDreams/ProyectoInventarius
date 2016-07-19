/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Sequence;
import Control.Entrada;
import Modelo.Producto;
import Modelo.List_Categoria;
import Control.Control;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Microinformatica
 */
public class Registro_producto extends javax.swing.JFrame {

    private int cod;
    ArrayList<Producto> pr = new ArrayList();
    String nom;
    String fac;
    int codEmpresa;
    ArrayList<Integer> ListAcciones = new ArrayList();

    public Registro_producto(int cod, ArrayList x, String nom, String fac, ArrayList acciones,int codEmpresa) throws ClassNotFoundException {
        initComponents();
        Categoria();
        this.nom = nom;
        this.fac = fac;
        this.codEmpresa=codEmpresa;
        this.ListAcciones = acciones;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        this.pr = x;
        this.cod = cod;

        jTextField2.setText("0");
        jTextField3.setText("");
        jTextField4.setText("0");
        stock.setText("0");
        jTextField6.setText("0");
        jTextField7.setText("0");
    }

    public void Categoria() throws ClassNotFoundException {
        Control.conectar();
        try {
            Control.ejecuteQuery("select * from categoria");
            String cod = "", nom = "";
            ArrayList<List_Categoria> cater = new ArrayList();

            while (Control.rs.next()) {
                cater.add(new List_Categoria(Control.rs.getInt(1), Control.rs.getString(2)));
                categoria.addItem(Control.rs.getString(2) + "-" + Control.rs.getString(4));

            }
            Control.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("Fallo Consulta: " + ex.toString());
        } finally {
            Control.cerrarConexion();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        stock = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        categoria = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BackBox - Nuevo producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 380, 32));

        jTextField3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 380, 32));

        jTextField4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 380, 32));

        stock.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 380, 32));

        jTextField6.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 380, 32));

        jTextField7.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 380, 32));

        categoria.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel6.setText("Stock");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel7.setText("Precio Venta");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel8.setText("Cantidad");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel9.setText("% IVA");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel5.setText("Costo");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel2.setText("Categoría");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel3.setText("Código");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel4.setText("Descripcion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(26, 26, 26)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(113, 113, 113))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 510));

        jTextField8.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 380, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            registrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registro_producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registro_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed
    public void GenerarCodigo() {

    }

    public boolean buscar_cod() throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;
        try {
            Control.ejecuteQuery("select count(*) from producto a, venta_pro p , venta v where a.cod_producto=p.cod_prodcuto and p.cod_factura=v.cod_factura and \n"
                    + "cod_producto='" + jTextField2.getText() + "'");

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

    public boolean registro_detalle() throws ClassNotFoundException, SQLException {
        Date fecha = new Date();
        boolean r = false;
        int codigo_detalle = Sequence.seque("select max(cod_detalle) from detalle");
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            r = Control.ejecuteUpdate("insert into detalle values(" + codigo_detalle + ",'" + fecha + "',"
                    + jTextField7.getText() + "," + jTextField4.getText() + ",'" + jTextField2.getText() + "',"
                    + cod + ")");

            if (r = false) {
                Entrada.muestreMensajeV("Error Registro Producto");
            }
            Control.cerrarConexion();
        } catch (Exception ex) {

        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        return r;
    }

    public void registrar() throws ClassNotFoundException, SQLException {
        if (buscar_cod() == false) {
            int categoria = traerCod();
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);
                double costo = Double.parseDouble(jTextField4.getText());
                double precio = Double.parseDouble(jTextField6.getText());
                String desc = "0";
                double des = 0;
                String iva = "0";
                double ivas = 0;
                double precioSinDEs = Double.parseDouble(jTextField6.getText());

                if (jTextField8.getText().equalsIgnoreCase("0")) {

                } else {
                    iva = "0." + jTextField8.getText();
                    ivas = costo * Double.parseDouble(iva);
                    precio = precio + ivas;
                    precioSinDEs = precioSinDEs + ivas;
                }

                boolean r = Control.ejecuteUpdate("insert into producto values('" + jTextField2.getText() + "','" + jTextField3.getText() + "',"
                        + jTextField4.getText() + "," + jTextField8.getText() + "," + precioSinDEs + ","
                        + categoria + "," + 0 + ",'A','n',0," + precio + "," + Integer.parseInt(stock.getText()) + ")");
                if (r) {
                    Entrada.muestreMensajeV("SE AGREGGO PRODUCTO A LA COMPRA");
                    pr.add(new Producto(jTextField2.getText(),
                            jTextField3.getText(), Double.parseDouble(jTextField4.getText()), precio, Integer.parseInt(jTextField7.getText()),1));
                    Entrada_Nueva ar = new Entrada_Nueva(pr, nom, fac, ListAcciones,codEmpresa);
                    this.setVisible(false);
                    ar.setVisible(true);
                } else {
                    Entrada.muestreMensajeV("Error al Agregar Producto");
                }
                Control.cerrarConexion();

            } catch (NumberFormatException ex) {
            } finally {
                Control.con.commit();
                Control.con.setAutoCommit(true);
                Control.cerrarConexion();
            }

        } else {
            Entrada.muestreMensajeV("El producto que esta tratando de registrar ya existe con una Transacccion");
        }

    }

    public int traerCod() throws ClassNotFoundException {
        Control.conectar();
        String nom[] = categoria.getSelectedItem().toString().split("-");
        try {
            Control.ejecuteQuery("select cod_categoria from categoria where descripcion='" + nom[0] + "'");
            int cod = 0;

            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
            Control.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        } finally {
            Control.cerrarConexion();
        }
        return cod;
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables

    private void setText(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
