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
import Control.Entrada;
import Modelo.List_Categoria;
import Modelo.Producto;
import Control.Sequence;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import vw.components.Bodega;
import vw.components.Entrada_Nueva;
import vw.model.Venta;

/**
 *
 * @author usuario
 */
public class ProductoUpdate2 extends javax.swing.JDialog {

    private int cod;
    Producto pr = new Producto();
    String nom;
    String fac;
    int Secuencia;
    int codProducto;
    Border Linea;
    int codEmpresa;
    Bodega v = null;
    ArrayList<Integer> ListAcciones = new ArrayList();
    ArrayList<List_Categoria> listIvas = new ArrayList();

    /**
     * Creates new form ProductoRegistrar
     *
     * @param parent
     * @param modal
     */
    public ProductoUpdate2(java.awt.Frame parent, boolean modal, Producto pro, int empresa) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        mns.setVisible(false);
        codigo.setText("" + 0);
        costoF.setText("" + 0);
        cantidad.setText("" + 0);
        precioVenta.setText("" + 0);
        stock.setText("" + 0);
        this.v = (Bodega) parent;
        this.pr = pro;
        this.codEmpresa=empresa;
        Linea = BorderFactory.createLineBorder(Color.orange, 2);
        
        try {
            ConfigurarInicio();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoUpdate2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ConfigurarInicio() throws SQLException {
        try {
            codigo.setText("" + pr.getCodigo());
            nombre.setText("" + pr.getNombre());
            costoF.setText("" + pr.getCosto());
            cantidad.setText("" + pr.getCantidad());
            precioVenta.setText("" + pr.getPrecio_venta());
            listIvas.clear();            
            System.out.println("--------------------------------------");
            Control.conectar();
            Control.ejecuteQuery("select codiva,porcentaje from maestro_iva a, producto b where\n"
                    + "a.codiva=b.iva and b.serie_producto='" + pr.getCodigo() + "' "
                    + "union all\n"
                    + "select codiva,porcentaje from maestro_iva a where codiva not in (select iva from producto where iva=a.codiva)");
            while (Control.rs.next()) {
                iva.addItem(Control.rs.getString(2) + " %");
                listIvas.add(new List_Categoria(Control.rs.getInt(1), "" + Control.rs.getInt(2)));
            }

            Control.ejecuteQuery("select * from (select producto.cod_categoria,rtrim(ltrim(descripcion)) descrip from categoria , producto where categoria.cod_categoria=producto.cod_categoria\n"
                    + "and serie_producto='" + pr.getCodigo() + "'\n"
                    + "union\n"
                    + "select cod_categoria,rtrim(ltrim(descripcion)) descrip from categoria )Y order by Y.descrip");
            while (Control.rs.next()) {
                categoria.addItem(Control.rs.getInt(1) + "-" + Control.rs.getString(2));
            }

            Control.ejecuteQuery("select cod_producto,stock from producto where serie_producto='" + pr.getCodigo() + "'");
            while (Control.rs.next()) {
                this.codProducto = Control.rs.getInt(1);
                this.stock.setText(""+Control.rs.getInt(2));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
        }

    }

    public boolean ValidarCampos() {
        boolean r = false;

        if (stock.getText().length() == 0 || stock.getText().equalsIgnoreCase("0")) {
            stock.requestFocus();
            stock.setBorder(Linea);
            r = true;
        }

        if (precioVenta.getText().length() == 0 || precioVenta.getText().equalsIgnoreCase("0")) {
            precioVenta.requestFocus();
            precioVenta.setBorder(Linea);
            r = true;
        }

        if (cantidad.getText().length() == 0 || cantidad.getText().equalsIgnoreCase("0")) {
            cantidad.requestFocus();
            cantidad.setBorder(Linea);
            r = true;
        }

        if (costoF.getText().length() == 0 || costoF.getText().equalsIgnoreCase("0")) {
            costoF.requestFocus();
            costoF.setBorder(Linea);
            r = true;
        }
        if (nombre.getText().length() == 0) {
            nombre.requestFocus();
            nombre.setBorder(Linea);
            r = true;
        }
        if (codigo.getText().length() == 0) {
            codigo.requestFocus();
            codigo.setBorder(Linea);
            r = true;
        }

        if (r) {
            Entrada.muestreMensajeV("Debe llenar todos los campos");
        }

        return r;
    }

    public void update() throws ClassNotFoundException, SQLException {
        boolean r = false;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            String nomcategoria = iva.getSelectedItem().toString();
            String val[] = nomcategoria.trim().split("%");
            int codIva = 0;
            for (List_Categoria ivas : listIvas) {
                if (ivas.getNom().equalsIgnoreCase(val[0].trim())) {
                    codIva = ivas.getCod();
                }
            }
            r = Control.ejecuteUpdate("update producto set nombre='" + nombre.getText() + "',"
                    + "costo=" + Double.parseDouble(costoF.getText()) + ",cantidad=" + Integer.parseInt(cantidad.getText()) + ","
                    + "precio_venta=" + (Double.parseDouble(precioVenta.getText())) + ","
                    + "precio_desc=" + (Double.parseDouble(precioVenta.getText())) + ","
                    + " iva=" + codIva + ",stock=" + Integer.parseInt(stock.getText()) + " where cod_producto=" + this.codProducto);
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
            this.v.inicio();
            this.dispose();
        } else {
            Entrada.muestreMensajeV("Error Actualizando");
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
        codigo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        costoF = new javax.swing.JTextField();
        precioVenta = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        iva = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox();
        mns = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                codigoFocusLost(evt);
            }
        });
        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoKeyReleased(evt);
            }
        });
        jPanel1.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 380, 32));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFocusLost(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 380, 32));

        costoF.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        costoF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                costoFFocusLost(evt);
            }
        });
        costoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoFActionPerformed(evt);
            }
        });
        costoF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costoFKeyReleased(evt);
            }
        });
        jPanel1.add(costoF, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 380, 32));

        precioVenta.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        precioVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                precioVentaFocusLost(evt);
            }
        });
        precioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioVentaKeyReleased(evt);
            }
        });
        jPanel1.add(precioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 380, 32));

        cantidad.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cantidadFocusLost(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
        });
        jPanel1.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 380, 32));

        iva.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 380, 32));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel7.setText("Precio Venta");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel12.setText("Stock");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel8.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel6.setText("% IVA");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel5.setText("Costo");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel3.setText("Código");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel2.setText("Categoría");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(157, 157, 157))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 510));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel9.setText("Stock");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        stock.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        stock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stockFocusLost(evt);
            }
        });
        stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockActionPerformed(evt);
            }
        });
        stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stockKeyReleased(evt);
            }
        });
        jPanel1.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 380, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel10.setText("Precio Venta");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel11.setText("Precio Venta");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, -1));

        categoria.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 380, 32));

        mns.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        mns.setForeground(new java.awt.Color(255, 102, 0));
        mns.setText("Código");
        jPanel1.add(mns, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 380, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            if (ValidarCampos() == false) {
                update();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoUpdate2.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ProductoUpdate2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void costoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoFActionPerformed

    }//GEN-LAST:event_costoFActionPerformed

    private void costoFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costoFKeyReleased
        char caracter = evt.getKeyChar();
        if (SoloNumeros(caracter)) {
            costoF.setText(costoF.getText().substring(0, (costoF.getText().length() - 1)));
        }

    }//GEN-LAST:event_costoFKeyReleased

    private void precioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioVentaKeyReleased
        char caracter = evt.getKeyChar();
        if (SoloNumeros(caracter)) {
            precioVenta.setText(precioVenta.getText().substring(0, (precioVenta.getText().length() - 1)));
        }
    }//GEN-LAST:event_precioVentaKeyReleased

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        char caracter = evt.getKeyChar();
        if (SoloNumeros(caracter)) {
            cantidad.setText(cantidad.getText().substring(0, (cantidad.getText().length() - 1)));
        }

    }//GEN-LAST:event_cantidadKeyReleased

    private void stockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockKeyReleased
        char caracter = evt.getKeyChar();
        if (SoloNumeros(caracter)) {
            stock.setText(stock.getText().substring(0, (stock.getText().length() - 1)));
        }


    }//GEN-LAST:event_stockKeyReleased

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        try {
            if (buscar_cod()) {
                codigo.setBorder(Linea);
                mns.setText("El codigo ya Existe");
                mns.setVisible(true);

            } else {
                mns.setVisible(false);
                codigo.setBorder(null);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_codigoActionPerformed

    private void codigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyReleased

    }//GEN-LAST:event_codigoKeyReleased

    private void codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codigoFocusLost
        if (codigo.getText().length() == 0) {
            codigo.setBorder(Linea);
        } else {
            codigo.setBorder(null);
        }
    }//GEN-LAST:event_codigoFocusLost

    private void nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFocusLost

        if (nombre.getText().length() == 0) {
            nombre.setBorder(Linea);
        } else {
            nombre.setBorder(null);
        }
    }//GEN-LAST:event_nombreFocusLost

    private void costoFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_costoFFocusLost
        if (costoF.getText().length() == 0 || costoF.getText().equalsIgnoreCase("0")) {
            costoF.setBorder(Linea);
        } else {
            costoF.setBorder(null);
        }
    }//GEN-LAST:event_costoFFocusLost

    private void cantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusLost
        if (cantidad.getText().length() == 0 || cantidad.getText().equalsIgnoreCase("0")) {
            cantidad.setBorder(Linea);
        } else {
            cantidad.setBorder(null);
        }
    }//GEN-LAST:event_cantidadFocusLost

    private void precioVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_precioVentaFocusLost
        if (precioVenta.getText().length() == 0 || precioVenta.getText().equalsIgnoreCase("0")) {
            precioVenta.setBorder(Linea);
        } else {
            precioVenta.setBorder(null);
        }
    }//GEN-LAST:event_precioVentaFocusLost

    private void stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockActionPerformed

    private void stockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stockFocusLost
        if (stock.getText().length() == 0 || stock.getText().equalsIgnoreCase("0")) {
            stock.setBorder(Linea);
        } else {
            stock.setBorder(null);
        }
    }//GEN-LAST:event_stockFocusLost
    public boolean SoloNumeros(char e) {
        long num = 0;
        double valor = 0;
        try {
            if (((e < '0')
                    || (e > '9'))
                    && (e != '\b' && e != '.')) {
                System.out.println("carateer mal");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean buscar_cod() throws ClassNotFoundException {
        boolean r = false;
        try {
            if (codigo.getText().length() > 0) {
                Control.conectar();
                int c = 0;
                Control.ejecuteQuery("select count(*) cuenta from producto  where serie_producto='" + codigo.getText() + "'");
                while (Control.rs.next()) {
                    c = Control.rs.getInt(1);
                }
                if (c > 0) {
                    r = true;
                }
            }
        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
        return r;
    }

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
    private javax.swing.JComboBox iva;
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
    private javax.swing.JLabel mns;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precioVenta;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables
}
