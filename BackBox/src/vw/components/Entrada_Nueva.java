/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Tabla3;
import Control.Control;
import com.alee.extended.date.WebCalendar;
import java.net.URL;
import vw.model.Articulo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Britany
 */
public class Entrada_Nueva extends javax.swing.JFrame {

    ArrayList<Producto> pr = new ArrayList();
    String nom;
    ArrayList<Integer> ListAcciones = new ArrayList();

    public Entrada_Nueva(ArrayList x, String nom, String fac, ArrayList acciones) throws ClassNotFoundException {
        initComponents();
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        this.nom = nom;
        this.ListAcciones = acciones;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Categoria2();
        this.pr = x;
        iniciar();
        recuperar_fecha();
        jTextField1.setText(fac);
        jLabel4.setText("0.0");
        sumarTot();
    }

    public void recuperar_fecha() {
        Date fecha = new Date();
        fechaActual.setDate(fecha);

    }

    public void sumarTot() {
        Producto pro = null;
        double sum = 0;
        for (int i = 0; i < pr.size(); i++) {
            pro = (Producto) pr.get(i);
            sum = sum + (pro.getCosto() * pro.getCantidad());
        }
        jLabel4.setText("" + sum);
    }

    public void Categoria2() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from provedor where estado='A'");
        String cod = "", nom = "";
        ArrayList<List_Categoria> cater = new ArrayList();
        try {
            while (Control.rs.next()) {
                cater.add(new List_Categoria(Control.rs.getInt(1), Control.rs.getString(3)));
                proveedores.addItem(Control.rs.getInt(1) + "-" + Control.rs.getString(3));

            }
            Control.cerrarConexion();
        } catch (Exception ex) {

        }
    }

    public void iniciar() {
        Tabla3 t = new Tabla3(pr);
        t.calculeFrecuenciasV();
        muevaLosDatosFre(t);
    }

    public void muevaLosDatosFre(Tabla3 x) {
        jTable2.getDefaultEditor(null);
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable2.setValueAt(x.frecuencias[i][k], k, i);

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        agregarProductos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        crearProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fechaActual = new com.alee.extended.date.WebDateField();
        proveedores = new javax.swing.JComboBox();

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BackBox - Agregar Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(740, 620));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setToolTipText("Hacer registro de la entrada");
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

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Costo", "Cantidad", "Precio"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        agregarProductos.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        agregarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        agregarProductos.setText("Agregar");
        agregarProductos.setBorder(null);
        agregarProductos.setBorderPainted(false);
        agregarProductos.setContentAreaFilled(false);
        agregarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarProductos.setPreferredSize(new java.awt.Dimension(55, 47));
        agregarProductos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        agregarProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductosActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setText("Provedor:");

        crearProducto.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        crearProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_assignment_turned_in_black_24dp.png"))); // NOI18N
        crearProducto.setText("Nuevo");
        crearProducto.setToolTipText("rgreg");
        crearProducto.setBorder(null);
        crearProducto.setBorderPainted(false);
        crearProducto.setContentAreaFilled(false);
        crearProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        crearProducto.setPreferredSize(new java.awt.Dimension(55, 47));
        crearProducto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        crearProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        crearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel2.setText("Fecha:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setText("Número Factura de Compra:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel3.setText("Total");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 102, 0));
        jLabel4.setText("Total");
        jLabel4.setToolTipText("");

        fechaActual.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        fechaActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActualActionPerformed(evt);
            }
        });

        proveedores.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        proveedores.setPreferredSize(new java.awt.Dimension(31, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(agregarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(crearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(agregarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(crearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (pr.size() == 0) {
            Entrada.muestreMensajeV("No hay Productos para Registrar");
        } else {
            ajustar_Datos();
            iniciar();
            try {

                Date date = fechaActual.getDate();
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = format2.format(date);
                Producto pro = null;
                String v[] = proveedores.getSelectedItem().toString().split("-");
                if (jTextField1.getText().equalsIgnoreCase("")) {
                    Entrada.muestreMensajeV("No Numero de hay Factura");
                    jTextField1.requestFocus();
                } else {
                    boolean r = false;
                    int codigo_detalle = Sequence.seque("select max(cod_detalle) from detalle");
                    Control.conectar();
                    for (int i = 0; i < pr.size(); i++) {
                        pro = (Producto) pr.get(i);
                        r = Control.ejecuteUpdate("insert into detalle values(" + codigo_detalle + ",'" + fecha + "',"
                                + pro.getCantidad() + "," + pro.getCosto() + ","
                                + v[0] + ",'" + jTextField1.getText() + "','" + pro.getCodigo() + "')");
                        codigo_detalle++;
                        if (r) {
                            boolean r1 = Control.ejecuteUpdate("update producto set cantidad=cantidad+" + pro.getCantidad() + " where cod_producto='" + pro.getCodigo() + "'");
                            if (r1) {
                                int cost = costo(pro.getCodigo());

                                boolean f = promedio_costo(cost, pro.getCosto(), pro.getCodigo());

                            } else {

                            }

                        } else {
                            Entrada.muestreMensajeV("ERROR");

                        }
                    }
                    if (r) {
                        Entrada.muestreMensajeV("Registro Exitoso");
                        Articulo a = new Articulo(nom, ListAcciones);
                        this.dispose();
                        a.setVisible(true);
                    } else {
                        Entrada.muestreMensajeV("ERROR");

                    }
                }

                Control.cerrarConexion();
            } catch (Exception ex) {

            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean promedio_costo(int costo, double x, String cod) throws ClassNotFoundException {
        Control.conectar();
        int valor = 0;
        valor = (costo + (int) x) / 2;
        boolean r = Control.ejecuteUpdate("update producto set costo=" + valor
                + " where cod_producto='" + cod + "'");
        Control.cerrarConexion();
        return r;
    }

    public int costo(String cod) throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select costo from producto where "
                + "cod_producto='" + cod + "'");
        int costo = 0;
        try {
            while (Control.rs.next()) {
                costo = Control.rs.getInt(1);
            }
            Control.cerrarConexion();
        } catch (Exception ex) {

        }
        return costo;
    }

    public void ajustar_Datos() {
        Producto prod = null;
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < pr.size(); k++) {
                prod = (Producto) pr.get(k);
                if (i == 2) {
                    prod.setCosto(Double.parseDouble((String) jTable2.getValueAt(k, i)));
                } else if (i == 3) {
                    prod.setCantidad(Integer.parseInt((String) jTable2.getValueAt(k, i)));
                }
            }
        }

    }

    public void Restar_Todo() {
        iniciar();

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < pr.size(); k++) {
                jTable2.setValueAt("", k, i);

            }
        }
        pr.clear();

    }

    public void restar_Bodega() throws ClassNotFoundException {
        Control.conectar();
        Producto pro = null;
        int codigo_pro = 0;
        for (int i = 0; i < pr.size(); i++) {
            pro = (Producto) pr.get(i);
            Control.ejecuteUpdate("update producto set cantidad=cantidad-" + pro.getCantidad() + " where "
                    + "cod_producto='" + pro.getCodigo() + "'");
        }
        Control.cerrarConexion();

    }

    public String traer_cedula() throws ClassNotFoundException {
        Control.conectar();
        String v[] = proveedores.getSelectedItem().toString().split("-");
        String ced = "";
        Control.ejecuteQuery("select usuario.cod_usuario from usuario,persona where \n"
                + "usuario.cedula=persona.cedula and\n"
                + "persona.nombre='" + v[0] + "' and persona.apellido='" + v[1] + "'");

        try {
            while (Control.rs.next()) {
                ced = Control.rs.getString(1);
            }
            Control.cerrarConexion();

        } catch (Exception ex) {

        }
        return ced;
    }
    private void agregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductosActionPerformed
        try {
            String fac = jTextField1.getText();
            String v[] = proveedores.getSelectedItem().toString().split("-");
            new Carrito_new(v[0], pr, nom, fac, ListAcciones).setVisible(true);;
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_agregarProductosActionPerformed

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered

    }//GEN-LAST:event_jTable2MouseEntered

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed

    }//GEN-LAST:event_jTable2MousePressed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed

//        rellenar_datos();

    }//GEN-LAST:event_jTable2KeyPressed

    private void crearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoActionPerformed
        String proveedor[] = proveedores.getSelectedItem().toString().split("-");
        try {
            String fac = jTextField1.getText();
            new Registro_producto(Integer.parseInt(proveedor[0]), pr, nom, fac, ListAcciones).setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
        }
    }//GEN-LAST:event_crearProductoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Articulo ar;
        try {
            ar = new Articulo(nom, ListAcciones);
            ar.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased

        Producto p = null;
        int i = jTable2.getSelectedRow();
        ArrayList<Producto> produc = new ArrayList();
        String cod = "";
        String can = "";
        String costo = "";
        String cantidad = "";
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            cod = (String) jTable2.getValueAt(i, 0).toString();
            costo = (String) jTable2.getValueAt(i, 2).toString();
            cantidad = (String) jTable2.getValueAt(i, 3).toString();

        }
        for (int k = 0; k < pr.size(); k++) {
            p = (Producto) pr.get(k);
            if (p.getCodigo().equalsIgnoreCase(cod)) {
                p.setCosto(Double.parseDouble(costo));
                p.setCantidad(Integer.parseInt(cantidad));
            }
        }
        sumarTot();
    }//GEN-LAST:event_jTable2KeyReleased

    private void fechaActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActualActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProductos;
    private javax.swing.JButton crearProducto;
    private com.alee.extended.date.WebDateField fechaActual;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox proveedores;
    // End of variables declaration//GEN-END:variables
}
