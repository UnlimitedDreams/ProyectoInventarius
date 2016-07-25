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
import vw.components.Bodega;
import Control.Entrada;
import Modelo.List_Categoria;
import Modelo.Producto;
import vw.model.Venta;

/**
 *
 * @author Microinformatica
 */
public class ProductoUpdate extends javax.swing.JDialog {

    private int cod;
    Producto p;
    String nom;
    int SecuenciaCosto;
    int SecuenciaVenta;
    int SecuenciaDesc;
    int SecuenciaCant;
    int secuenciaIva;
    int codEmpresa;
    ArrayList<Integer> ListAcciones = new ArrayList();
    ArrayList<List_Categoria> listIvas = new ArrayList();

    public ProductoUpdate(java.awt.Frame parent, boolean modal, Producto p, String nom, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        super(parent, modal);
        initComponents();
        this.p = p;
        this.nom = nom;
        this.ListAcciones = acciones;
        this.codEmpresa = codEmpresa;
        recuperar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        try {
            ConfigurarIva();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperar() {
        jTextField2.setText("" + p.getCodigo());
        jTextField3.setText("" + p.getNombre());
        jTextField4.setText("" + p.getCosto());
        jTextField8.setText("" + p.getCantidad());
        jTextField6.setText("" + p.getPrecio_venta());
        jTextField9.setText("" + p.getDesc());

    }

    public void ConfigurarIva() throws SQLException, ClassNotFoundException {
        try {
            listIvas.clear();
            Control.conectar();
            Control.con.setAutoCommit(false);
            Control.ejecuteQuery("select codiva,porcentaje from maestro_iva a, producto b where\n"
                    + "a.codiva=b.iva\n"
                    + "union all\n"
                    + "select codiva,porcentaje from maestro_iva a where codiva not in (select iva from producto where iva=a.codiva)");
            while (Control.rs.next()) {
                iva.addItem(Control.rs.getString(2) + " %");
                listIvas.add(new List_Categoria(Control.rs.getInt(1), "" + Control.rs.getInt(2)));
            }
            Control.ejecuteQuery("select stock from producto where serie_producto='" + p.getCodigo() + "'");

            while (Control.rs.next()) {
                jTextField12.setText("" + Control.rs.getInt(1));
            }
            if (jTextField12.getText().equalsIgnoreCase("")) {
                jTextField12.setText("" + 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
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
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        iva = new javax.swing.JComboBox<String>();

        setTitle("Actualizar Producto - Inventarius");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(510, 510));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField2.setEnabled(false);
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 400, 40));

        jTextField3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 400, 40));

        jTextField4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 400, 40));

        jTextField6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 400, 40));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton2.setToolTipText("Volver");
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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, -1));

        jTextField8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 400, 40));

        jTextField9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 400, 40));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel3.setText("Código");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel5.setText("Costo");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel7.setText("Precio Venta");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel10.setText("Descuento(%)");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel9.setText("Cantidad");

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel11.setText("Stock");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel12.setText("Iva(%)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 540));

        jTextField12.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 400, 40));

        iva.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        iva.setToolTipText("");
        iva.setPreferredSize(new java.awt.Dimension(130, 28));
        iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaActionPerformed(evt);
            }
        });
        jPanel1.add(iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 400, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Bodega b;
        try {
            try {
                b = new Bodega(nom, ListAcciones, codEmpresa);
                b.setVisible(true);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            try {
                update();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField4KeyTyped
    public boolean SoloNumeros(String cadena, int condicion) {
        long num = 0;
        double valor = 0;
        try {
            if (condicion == 1) {
                valor = Double.parseDouble(cadena);
                SecuenciaCosto = cadena.length();
            } else if (condicion == 2) {
                valor = Double.parseDouble(cadena);
                SecuenciaVenta = cadena.length();
            } else if (condicion == 3) {
                num = Long.parseLong(cadena);
                SecuenciaDesc = cadena.length();
            } else if (condicion == 4) {
                num = Long.parseLong(cadena);
                SecuenciaCant = cadena.length();
            } else if (condicion == 5) {
                num = Long.parseLong(cadena);
                secuenciaIva = cadena.length();
            } else {
                num = Long.parseLong(cadena);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        try {
            boolean v = SoloNumeros(jTextField4.getText(), 1);
            if (v == false) {
                jTextField4.setText(jTextField4.getText().substring(0, SecuenciaCosto));
            }
        } catch (Exception ex) {
            System.err.println("Error : " + ex.toString());
        }

    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        boolean v = SoloNumeros(jTextField6.getText(), 2);
        if (v == false) {
            jTextField6.setText(jTextField6.getText().substring(0, SecuenciaVenta));
        }
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        boolean v = SoloNumeros(jTextField9.getText(), 3);
        if (v == false) {
            jTextField9.setText(jTextField9.getText().substring(0, SecuenciaDesc));
        }
        if (jTextField9.getText().length() > 1) {
            jTextField9.setText(jTextField9.getText().substring(0, 2));
        }

    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        boolean v = SoloNumeros(jTextField8.getText(), 4);
        if (v == false) {
            jTextField8.setText(jTextField8.getText().substring(0, SecuenciaCant));
        }

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        boolean v = SoloNumeros(jTextField12.getText(), 5);
        if (v == false) {
            jTextField12.setText("");
        }
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaActionPerformed

    }//GEN-LAST:event_ivaActionPerformed
    public int detectarNumero(String a) {
        int valor = 0;
        try {
            valor = Integer.parseInt(a);
        } catch (Exception ex) {
            valor = 0;
            System.out.println("Error sistema");
        }
        return valor;
    }

    public void update() throws ClassNotFoundException, SQLException {
        boolean r = false;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            String nomcategoria = iva.getSelectedItem().toString();
            String val[] = nomcategoria.split("%");
            r = Control.ejecuteUpdate("update producto set nombre='" + jTextField3.getText() + "',"
                    + "costo=" + jTextField4.getText() + ",cantidad=" + jTextField8.getText() + ","
                    + "precio_venta=" + jTextField6.getText() + ","
                    + "descu=" + jTextField9.getText() + ","
                    + "precio_desc=" + ((Double.parseDouble(jTextField6.getText()))
                    - ((Double.parseDouble(jTextField6.getText())) * (Double.parseDouble(jTextField9.getText()) / 100))) + ","
                    + " iva=" + val[0] + ",stock=" + Integer.parseInt(jTextField12.getText()) + " where cod_producto='" + jTextField2.getText()
                    + "'");
        } catch (SQLException ex) {
            System.out.println("Error SQL : " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (r) {
            Entrada.muestreMensajeV("Actualizacion Exitosa",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            Bodega b = new Bodega(nom, ListAcciones, codEmpresa);
            this.setVisible(false);
            b.setVisible(true);
        } else {
            Entrada.muestreMensajeV("Error Actualizando");
        }

    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    private void setText(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
