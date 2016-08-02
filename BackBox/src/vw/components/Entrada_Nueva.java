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
import Control.Tabla3;
import Control.Control;
import com.alee.extended.date.WebCalendar;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import vw.model.Articulo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import vw.dialogs.ProductoRegistrar;
import vw.model.Venta;

/**
 *
 * @author Britany
 */
public class Entrada_Nueva extends javax.swing.JFrame {

    ArrayList<Producto> productos = new ArrayList();
    String nom;
    String nombreUsuario;
    boolean condicionfiltro;
    int codEmpresa;
    ArrayList<Integer> ListAcciones = new ArrayList();
    ArrayList<Integer> listIvas = new ArrayList();

    public Entrada_Nueva(ArrayList x, String nom, String fac, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        try {
            initComponents();
            URL url = getClass().getResource("/images/facelet/icon.png");
            ImageIcon img = new ImageIcon(url);
            setIconImage(img.getImage());
            c.setVisible(false);
            this.nom = nom;
            this.codEmpresa = codEmpresa;
            this.condicionfiltro = false;
            this.ListAcciones = acciones;
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            Categoria2();
            this.productos = x;
            iniciar();
            recuperar_fecha();
            jTextField1.setText(fac);
            jLabel4.setText("0.0");
            sumarTot();
            ConfigurarIva();
            cargarUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ConfigurarIva() throws SQLException {
        try {
            listIvas.clear();
            Control.conectar();
            Control.ejecuteQuery("select * from cargaiva()");
            while (Control.rs.next()) {
                listIvas.add(Control.rs.getInt(1));
            }
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperar_fecha() {
        Date fecha = new Date();
        fechaActual.setDate(fecha);

    }

    public void sumarTot() {
        Producto pro = null;
        double sum = 0;
        for (int i = 0; i < productos.size(); i++) {
            pro = (Producto) productos.get(i);
            sum = sum + (pro.getCosto());
        }
        jLabel4.setText("" + sum);
    }

    public void Categoria2() throws ClassNotFoundException {

        try {
            Control.conectar();
            Control.ejecuteQuery("select * from categoriabusqueda()");
            String cod = "", nom = "";
            ArrayList<List_Categoria> cater = new ArrayList();
            while (Control.rs.next()) {
                cater.add(new List_Categoria(Control.rs.getInt(1), Control.rs.getString(3)));
                proveedores.addItem(Control.rs.getInt(1) + "-" + Control.rs.getString(3));
            }
        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
    }

    public void iniciar() {
        Tabla3 t = new Tabla3(productos);
        t.calculeFrecuenciasV();
        muevaLosDatosFre(t);
    }

    public void muevaLosDatosFre(Tabla3 x) {
        jTable2.getDefaultEditor(null);
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                System.out.println("--- : " + x.frecuencias[i][k]);
                jTable2.setValueAt(x.frecuencias[i][k], k, i);

            }
        }
    }

    public void cargarUsuario() throws SQLException, ClassNotFoundException {
        Control.conectar();
        System.out.println("usuario : " + nom);
        Control.ejecuteQuery("select * from cargausuario(" + nom + ")");
        String nombre = "";
        int empresa = 0;
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
            empresa = Control.rs.getInt(3);
        }
        this.codEmpresa = empresa;
        this.nombreUsuario = nombre;
        Control.cerrarConexion();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        c = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        crearProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fechaActual = new com.alee.extended.date.WebDateField();
        comprass = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
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
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel1FocusLost(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTable1MouseDragged(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable1MouseExited(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        c.setViewportView(jTable1);

        jPanel1.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 620, 140));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, -1, -1));

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Costo", "Precio", "Iva", "Cantidad", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable2FocusLost(evt);
            }
        });
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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 166, 656, 380));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setText("Compra");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

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
        jPanel1.add(crearProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel2.setText("Fecha:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

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
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setText("Factura No.");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTextField1.setMinimumSize(new java.awt.Dimension(6, 22));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 170, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel3.setText("Total");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 102, 0));
        jLabel4.setText("Total");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 199, -1));

        fechaActual.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        fechaActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActualActionPerformed(evt);
            }
        });
        jPanel1.add(fechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 170, -1));

        comprass.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        comprass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Credito", "De Contado" }));
        comprass.setPreferredSize(new java.awt.Dimension(31, 20));
        jPanel1.add(comprass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 170, 25));

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jTextField2.setToolTipText("Busca un codigo de Producto");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 620, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setText("Proveedor:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        proveedores.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        proveedores.setPreferredSize(new java.awt.Dimension(31, 20));
        jPanel1.add(proveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 170, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (productos.size() == 0) {
            Entrada.muestreMensajeV("No hay Productos para Registrar");
        } else if (jTextField1.getText().isEmpty()) {
            Entrada.muestreMensajeV("No Registro Numero de Factura");
            jTextField1.requestFocus();
        } else if (ValidarDatosEnTabla()) {
            boolean Rfinal = false;
            if (ajustar_Datos_CalculoCosto()) {
                try {
                    Date date = fechaActual.getDate();
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
                    String fecha = format2.format(date);
                    Producto pro = null;
                    String v[] = proveedores.getSelectedItem().toString().split("-");
                    if (VerificarFactura() == false) {
                        boolean r = false;
                        boolean r1 = false;
                        int codigo_detalle = Sequence.seque("select max(cod_detalle) from detalle");
                        try {
                            Control.conectar();
                            Control.con.setAutoCommit(false);
                            for (int i = 0; i < productos.size(); i++) {
                                pro = (Producto) productos.get(i);
                                r = Control.ejecuteUpdate("insert into detalle values(" + codigo_detalle + ",'" + fecha + "',"
                                        + pro.getCantidad() + "," + pro.getCosto() + ","
                                        + v[0] + ",'" + jTextField1.getText() + "','" + comprass.getSelectedItem().toString() + "'"
                                        + "," + pro.getIva() + "," + pro.getPrecio_venta() + "," + codEmpresa + ",'" + nombreUsuario + "'," + pro.getCodigoProducto() + ")");
                                codigo_detalle++;
                                if (r) {
                                    r1 = Control.ejecuteUpdate("update producto set cantidad=cantidad+" + pro.getCantidad() + ","
                                            + "stock=" + pro.getStock() + ",bandera=1,nombre='" + pro.getNombre() + "',"
                                            + "precio_venta=" + pro.getPrecio_venta() + ",precio_desc=" + pro.getPrecio_venta()
                                            + " where cod_producto=" + pro.getCodigoProducto() + "");
                                    if (r1) {
                                        int cost = costo(pro.getCodigoProducto());
                                        boolean f = promedio_costo(cost, pro.getCosto(), pro.getCodigoProducto());
                                    }
                                }
                            }
                            if (r == true && r1 == true) {
//                                Connection con = Control.con;
//                            generarFactura(jTextField1.getText(), fecha, con, v[1]);
                                Rfinal = true;
                            } else {
                                Entrada.muestreMensajeV("Error al Registrar Producto Pongase en contacto con Soporte");
                            }

                        } catch (SQLException ex) {
                            System.out.println("Error SQL : " + ex.toString());
                        } finally {
                            try {
                                Control.con.commit();
                                Control.con.setAutoCommit(true);
                                Control.cerrarConexion();
                            } catch (SQLException ex) {
                                Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
                        Entrada.muestreMensajeV("La Factura ingresada ya se encuentra registrada para ese proveedor.");
                    }

                } catch (Exception ex) {
                    System.out.println("Error Proceso : " + ex.toString());
                }

                if (Rfinal) {
                    try {
                        Articulo a = new Articulo(nom, ListAcciones, codEmpresa);
                        this.dispose();
                        a.setVisible(true);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                Entrada.muestreMensajeV("El costo de los productos no puede ser $0 (cero)");
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public void generarFactura(String cod_factura, String fecha, Connection c, String provedor) throws JRException, ClassNotFoundException {
        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Cod_fac", cod_factura);
            parametros.put("fec_fac", fecha);
            parametros.put("provedor", provedor);
            System.out.println("fac: " + cod_factura + " fecha: " + fecha + " prove: " + provedor);
            //System.out.println("Coneccion : " +c.getSchema());
            JasperReport report = JasperCompileManager.compileReport("FacCompra.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, c);
            JasperPrintManager.printReport(jasperPrint, true);
            //JasperPrintManager.printReport(jasperPrint, true);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public boolean promedio_costo(int costo, double x, int cod) throws ClassNotFoundException {

        int valor = 0;
        valor = (costo + (int) x) / 2;
        boolean r = Control.ejecuteUpdate("update producto set costo=" + valor
                + " where cod_producto=" + cod);

        return r;
    }

    public int costo(int cod) throws ClassNotFoundException {
        int costo = 0;
        try {
            Control.ejecuteQuery("select costo from producto where "
                    + "cod_producto=" + cod);
            while (Control.rs.next()) {
                costo = Control.rs.getInt(1);
            }
        } catch (Exception ex) {
            Entrada.muestreMensajeV("Error Calculando Costo");
        }
        return costo;
    }

    public boolean SoloNumeros(String cadena, int condicion) {
        double valorD = 0;
        int valorI = 0;
        try {
            if (condicion == 1) {
                valorD = Double.parseDouble(cadena);
            } else if (condicion == 2) {
                valorI = Integer.parseInt(cadena);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ValidarDatosEnTabla() {
        Producto prod = null;
        String mnserror = "N";
        boolean r = true;
        boolean iva = false;
        int cant = 0;
        double costo = 0;
        double precio = 0;
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < productos.size(); k++) {
                prod = (Producto) productos.get(k);
                cant = 0;
                costo = 0;
                precio = 0;
                if (i == 2 && SoloNumeros((String) jTable2.getValueAt(k, i), 1) == false) {
                    mnserror = "El valor del costo debe ser Numerico";
                    r = false;
                    break;
                } else if (i == 2 && SoloNumeros((String) jTable2.getValueAt(k, i), 1)) {
                    costo = Double.parseDouble((String) jTable2.getValueAt(k, i));
                    if (costo <= 0) {
                        mnserror = "El valor del costo Debe ser mayor a cero (0)";
                        r = false;
                        break;
                    }
                } else if (i == 5 && SoloNumeros((String) jTable2.getValueAt(k, i), 2) == false) {
                    mnserror = "La cantidad debe ser Numerica";
                    r = false;
                    break;
                } else if (i == 5 && SoloNumeros((String) jTable2.getValueAt(k, i), 2)) {
                    cant = Integer.parseInt((String) jTable2.getValueAt(k, i));
                    if (cant <= 0) {
                        mnserror = "La cantidad debe ser mayor a cero (0)";
                        r = false;
                        break;
                    }
                } else if (i == 3 && SoloNumeros((String) jTable2.getValueAt(k, i), 1) == false) {
                    mnserror = "El valor del Precio debe ser Numerico";
                    r = false;
                    break;
                } else if (i == 3 && SoloNumeros((String) jTable2.getValueAt(k, i), 1)) {
                    precio = Double.parseDouble((String) jTable2.getValueAt(k, i));
                    if (precio <= 0) {
                        mnserror = "El valor del Precio Debe ser mayor a cero (0)";
                        r = false;
                        break;
                    }
                } else if (i == 6 && SoloNumeros((String) jTable2.getValueAt(k, i), 2) == false) {
                    mnserror = "La cantidad del Stock debe ser Numerico";
                    r = false;
                    break;

                } else if (i == 4 && SoloNumeros((String) jTable2.getValueAt(k, i), 1) == false) {
                    mnserror = "El valor del iva debe ser Numerico";
                    r = false;
                    break;
                } else if (i == 4 && SoloNumeros((String) jTable2.getValueAt(k, i), 1)) {
                    for (int Valoriva : listIvas) {
                        if (Integer.parseInt((String) jTable2.getValueAt(k, i)) == Valoriva) {
                            iva = true;
                        }
                    }
                    if (iva == false) {
                        mnserror = "El valor del iva debe estar dentro de los parametros del iva permitido";
                        r = false;
                        break;
                    }
                }
            }
        }
        if (mnserror != "N") {
            Entrada.muestreMensajeV(mnserror);
        }
        return r;
    }

    public boolean ajustar_Datos_CalculoCosto() {
        boolean costo = false;
        Producto prod = null;
        double valorCosto = 0;
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < productos.size(); k++) {
                prod = (Producto) productos.get(k);
                if (i == 1) {
                    prod.setNombre((String) jTable2.getValueAt(k, i));
                } else if (i == 2) {
                    prod.setCosto(Double.parseDouble((String) jTable2.getValueAt(k, i)));
                } else if (i == 3) {
                    prod.setPrecio_venta(Double.parseDouble((String) jTable2.getValueAt(k, i)));
                } else if (i == 4) {
                    prod.setIva(Integer.parseInt((String) jTable2.getValueAt(k, i)));
                } else if (i == 5) {
                    prod.setCantidad(Integer.parseInt((String) jTable2.getValueAt(k, i)));
                } else if (i == 6) {
                    prod.setStock(Integer.parseInt((String) jTable2.getValueAt(k, i)));
                }
            }
        }
        for (Producto producto : productos) {
            valorCosto = valorCosto + producto.getCosto();
        }
        if (valorCosto > 0) {
            costo = true;
        }
        return costo;
    }

    public void Restar_Todo() {
        iniciar();
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < productos.size(); k++) {
                jTable2.setValueAt("", k, i);
            }
        }
        productos.clear();

    }

    public boolean VerificarFactura() throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;
        try {
            String provedor[] = proveedores.getSelectedItem().toString().split("-");
            int a = Integer.parseInt(provedor[0]);
            Control.ejecuteQuery("select factura from detalle where factura='" + jTextField1.getText() + "' and provedor=" + a);
            while (Control.rs.next()) {
                r = true;
            }

        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
        return r;
    }
    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered

    }//GEN-LAST:event_jTable2MouseEntered

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed

    }//GEN-LAST:event_jTable2MousePressed
    public void borrar() {
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("Inventarius", "¿Esta Seguro que Desea Borrar el Producto? ", op);
        if (Condicion == 1) {
            int i = jTable2.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
            } else {                
                String cod = (String) jTable2.getValueAt(i, 0).toString();
                Producto p = null;

                for (int k = 0; k < productos.size(); k++) {
                    p = (Producto) productos.get(k);
                    if (p.getCodigo().equalsIgnoreCase(cod)) {                        
                        productos.remove(p);
                    }
                }                
                if (productos.size() == 0) {
                    Borrar2();
                } else {
                    Borrar();
                }
                iniciar();
            }
        }
    }
    private void crearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoActionPerformed
        String proveedor[] = proveedores.getSelectedItem().toString().split("-");
        String fac = jTextField1.getText();
        new ProductoRegistrar(this, true, productos).setVisible(true);
        //this.setVisible(false);


    }//GEN-LAST:event_crearProductoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Articulo ar;
        try {
            ar = new Articulo(nom, ListAcciones, codEmpresa);
            ar.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void fechaActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActualActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == 10 && condicionfiltro == false) {
            c.setVisible(false);
            this.condicionfiltro = true;
            Producto p = null;
            String cod = "";
            String can = "";
            cod = (String) jTextField2.getText();
            boolean Esta = false;
            for (int k = 0; k < productos.size(); k++) {
                p = (Producto) productos.get(k);
                if (p.getCodigo().equalsIgnoreCase(cod)) {
                    Esta = true;
                    p.setCantidad(p.getCantidad() + 1);
                }
            }
            if (Esta == false) {
                try {
                    Control.conectar();
                    Producto temp = null;                    
                    Control.ejecuteQuery("select * from ProductoBuqueda('"+cod+"',1)");
                    String nom = "";
                    double precio = 0;
                    double costo = 0;
                    int iva = 0, cod_producto = 0;
                    int ivaMaestro = 0;
                    int stock = 0;
                    boolean r = false;
                    while (Control.rs.next()) {
                        r = true;
                        nom = Control.rs.getString(1);
                        precio = Control.rs.getDouble(2);
                        iva = Control.rs.getInt(3);
                        costo = Control.rs.getDouble(4);
                        stock = Control.rs.getInt(5);
                        ivaMaestro = Control.rs.getInt(6);
                        cod_producto = Control.rs.getInt(7);
                    }
                    Control.cerrarConexion();
                    if (r) {
                        temp = new Producto(cod, nom, costo, iva, precio, 1, 0);
                        temp.setStock(stock);
                        temp.setCodigoIva(ivaMaestro);
                        temp.setCodigoProducto(cod_producto);
                        productos.add(temp);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
                    Control.cerrarConexion();
                }
            }
            c.setVisible(false);
            jTextField2.setText("");
            iniciar();
            jTextField2.requestFocus();
        } else {
            this.condicionfiltro = false;
            try {
                Buscar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged

    }//GEN-LAST:event_jTable1MouseDragged

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        System.out.println("Pierde focus");
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jTable1FocusLost
    public boolean SoloNumeros(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Borrar() {
        System.out.println("+++++");
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < productos.size() + 1; k++) {
                jTable2.setValueAt("", k, i);

            }
        }
        iniciar();
    }

    public void Borrar2() {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 1; k++) {
                jTable2.setValueAt("", k, i);
            }
        }
        iniciar();
    }

    public void Buscar() throws ClassNotFoundException {
        String query = "";
        if (SoloNumeros(jTextField2.getText())) {
            query = "select * from CompraVentaFiltro('"+jTextField2.getText()+"',1)";
        } else {
            query = "select * from CompraVentaFiltro('"+jTextField2.getText()+"',2)";
        }        
        Control.conectar();
        String cabeceras[] = {"Producto", "Nombre", "Precio"};
        DefaultTableModel modeloEmpleado = new DefaultTableModel(null, cabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        boolean r2 = false;

        this.jTable1.setModel(modeloEmpleado);
        try {
            boolean r = Control.ejecuteQuery(query);

            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i <= numeroPreguntas; i++) {
                //modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }
            if (!jTextField2.getText().equals("")) {                
                while (Control.rs.next()) {
                    r2 = true;
                    Object[] registroEmpleado = new Object[numeroPreguntas];
                    for (int i = 0; i < numeroPreguntas; i++) {
                        registroEmpleado[i] = Control.rs.getObject(i + 1);
                    }
                    modeloEmpleado.addRow(registroEmpleado);
                }
                c.setVisible(true);
            } else {
                c.setVisible(false);
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }

    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int i = jTable1.getSelectedRow();
            AgregarProductos(i);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited

    }//GEN-LAST:event_jTable1MouseExited
    public void AgregarProductos(int i) {
        Producto p = null;
        String cod = "";
        String can = "";
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fil");
        } else if (i >= 0) {
            cod = (String) jTable1.getValueAt(i, 0).toString();            
            boolean Esta = false;
            for (int k = 0; k < productos.size(); k++) {
                p = (Producto) productos.get(k);
                if (p.getCodigo().equalsIgnoreCase(cod)) {
                    Esta = true;
                    p.setCantidad(p.getCantidad() + 1);
                }
            }
            if (Esta == false) {               
                try {
                    Control.conectar();
                    Producto temp = null;
                    String query = "select * from ProductoBuqueda('"+cod+"',1)";
                    Control.ejecuteQuery(query);
                    String nom = "";
                    double precio = 0;
                    double costo = 0;
                    int iva = 0, cod_producto = 0;
                    int maestroIva = 0;
                    int stock = 0;
                    boolean r = false;
                    while (Control.rs.next()) {
                        r = true;
                        nom = Control.rs.getString(1);
                        precio = Control.rs.getDouble(2);
                        iva = Control.rs.getInt(3);
                        costo = Control.rs.getDouble(4);
                        stock = Control.rs.getInt(5);
                        maestroIva = Control.rs.getInt(6);
                        cod_producto = Control.rs.getInt(7);
                    }
                    Control.cerrarConexion();
                    if (r) {
                        temp = new Producto(cod, nom, costo, iva, precio, 1, 0);
                        temp.setStock(stock);
                        temp.setCodigoIva(maestroIva);
                        temp.setCodigoProducto(cod_producto);
                        productos.add(temp);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                jTable1.setValueAt("", i, 0);
            }
        }

        c.setVisible(false);
        jTextField2.setText("");
        iniciar();
        jTextField2.requestFocus();
    }
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        try {
            if (evt.getKeyCode() == 10) {
                int i = jTable1.getSelectedRow();
                AgregarProductos(i);
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        try {
            if (evt.getKeyCode() == 127) {
                borrar();
            }
        } catch (Exception ex) {
            Entrada.muestreMensajeV("Error al borrar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased

    }//GEN-LAST:event_jTable2KeyReleased

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusLost
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jTable2FocusLost

    private void jPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusLost
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jPanel1FocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane c;
    private javax.swing.JComboBox comprass;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox proveedores;
    // End of variables declaration//GEN-END:variables
}
