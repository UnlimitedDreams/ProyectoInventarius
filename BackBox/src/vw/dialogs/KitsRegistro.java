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
import Modelo.List_Categoria;
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
import vw.components.Promociones;
import vw.model.Venta;

/**
 *
 * @author usuario
 */
public class KitsRegistro extends javax.swing.JDialog {

    /**
     * Creates new form RolActualizar
     *
     * @param parent
     * @param modal
     * @param cod
     */
    Kits kit = null;
    int numKit;
    ArrayList<Producto> productos = new ArrayList();
    boolean condicionfiltro;

    public KitsRegistro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        this.condicionfiltro = false;
        c.setVisible(false);
        this.Costo.setText("0");
        this.precio.setText("0");
        this.Cantidad.setText("0");
        this.kit = (Kits) parent;
        Numerador();
        nombre.requestFocus();

    }

    public void Numerador() {
        try {
            Control.conectar();
            Control.ejecuteQuery("select secuencia from Numerador where TipoNumerador='Kits'");
            while (Control.rs.next()) {
                this.numKit = Integer.parseInt(Control.rs.getString(1));
                codigo.setText("Kit-" + Control.rs.getString(1));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error al conectar" + ex.toString());
        } catch (SQLException ex) {
            System.out.println("Sintaxis de la consulta mal hecha" + ex.toString());
        } finally {
            Control.cerrarConexion();
        }
    }

    public boolean ValidarCampos() {
        boolean r = true;
        if (nombre.getText().length() > 0) {
            nombre.requestFocus();
            r = false;
        }
        if (Cantidad.getText().length() > 0) {
            Cantidad.requestFocus();
            r = false;
        }

        return r;
    }

    public void registrarKit() throws SQLException, ClassNotFoundException {
        boolean r = false;
        try {
            int codKitDel = Sequence.seque("select max(codKitDet) from KitDetalle");
            Control.conectar();
            Control.con.setAutoCommit(false);
            int actu = 0;
            r = Control.ejecuteUpdate("insert into Kits values('" + codigo.getText().trim() + "',"
                    + Double.parseDouble(Costo.getText()) + "," + Double.parseDouble(precio.getText())
                    + "," + Integer.parseInt(Cantidad.getText()) + ",'A'," + numKit + ",'" + nombre.getText() + "'," + actu + ")");
            int canti = 0;
            for (Producto LiProducto : productos) {
                r = Control.ejecuteUpdate("insert into KitDetalle values(" + codKitDel + ",'" + codigo.getText().trim() + "',"
                        + LiProducto.getCodigoProducto() + "," + LiProducto.getPrecio_venta() + "," + LiProducto.getCantidadKit() + ")");
                codKitDel++;
                canti = LiProducto.getCantidad() - (Integer.parseInt(Cantidad.getText())*LiProducto.getCantidadKit());
                r = Control.ejecuteUpdate("update producto set cantidad=" + canti + " where cod_producto=" + LiProducto.getCodigoProducto());
                canti = 0;
            }
            r = Control.ejecuteUpdate("update Numerador set secuencia=" + (numKit + 1) + " where tiponumerador='Kits'");
        } catch (Exception ex) {
            r = false;
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        if (r) {
            Entrada.muestreMensajeV("Se registro Exitosamente el Kit");
            kit.inicio();
            this.dispose();
        } else {
            Entrada.muestreMensajeV("Error Al Crear Promocion");
        }
    }

    public void AgregarProductos(int i) {
        this.condicionfiltro = true;
        Producto p = null;
        String cod = "";
        String can = "";
        if (i == -1) {
            System.out.println("FAVORRRR");
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fil");
        } else if (i >= 0) {
            cod = (String) jTable2.getValueAt(i, 0).toString();
            System.out.println("Trajo producto :::: " + cod);
            boolean Esta = false;
            for (int k = 0; k < productos.size(); k++) {
                p = (Producto) productos.get(k);
                if (p.getCodigo().equalsIgnoreCase(cod)) {
                    System.out.println("Sumo cantidad");
                    Esta = true;
                }
            }
            if (Esta == false) {
                System.out.println("Consulto datos");
                try {
                    Control.conectar();
                    Producto temp = null;
                    String query = "select nombre,precio_venta,costo,cod_producto,cantidad from producto\n"
                            + "where\n"
                            + "producto.estado='A' and rtrim(ltrim(serie_producto))='" + cod.trim() + "'";
                    Control.ejecuteQuery(query);
                    String nom = "";
                    double precio = 0, costo = 0;
                    int cantidad = 0;
                    int cod_producto = 0;
                    boolean r = false;
                    while (Control.rs.next()) {
                        r = true;
                        nom = Control.rs.getString(1);
                        precio = Control.rs.getDouble(2);
                        costo = Control.rs.getInt(3);
                        cod_producto = Control.rs.getInt(4);
                        cantidad = Control.rs.getInt(5);
                    }
                    if (r) {
                        temp = new Producto(cod, cod_producto, nom, costo, precio);
                        temp.setCantidad(cantidad);
                        temp.setCantidadKit(1);
                        productos.add(temp);
                        jTextField2.setText("");
                        Costo.setText("" + (Double.parseDouble(Costo.getText()) + costo));
                        this.precio.setText("" + (Double.parseDouble(this.precio.getText()) + precio));
                    } else {
                        Entrada.muestreMensajeV("Codigo  de producto no valido");
                        jTextField2.setText("");

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Venta.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (SQLException ex) {
                    Logger.getLogger(Venta.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        c.setVisible(false);
        jTextField2.setText("");

        iniciar();

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
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        c = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        Costo = new javax.swing.JTextField();
        codigo = new javax.swing.JTextField();
        Cantidad = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion Promocion - BackBox");
        setPreferredSize(new java.awt.Dimension(697, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel7.setText("Codigo ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel6.setText("Costo ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
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
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 650, 30));

        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        c.setViewportView(jTable2);

        jPanel1.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 650, 130));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Codigo", "Nombre", "Stock", "Cant. Kit", "Precio Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 650, 160));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel4.setText("Cantidad");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, -1));

        Costo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Costo.setEnabled(false);
        jPanel1.add(Costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 160, -1));

        codigo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        codigo.setEnabled(false);
        jPanel1.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 160, -1));

        Cantidad.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CantidadFocusLost(evt);
            }
        });
        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });
        jPanel1.add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 160, -1));

        precio.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 160, -1));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 310, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel5.setText("Precio");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (nombre.getText().length() == 0) {
                Entrada.muestreMensajeV("EL nombre es obligatorio");
                nombre.requestFocus();
            } else if (Cantidad.getText().equalsIgnoreCase("0") || Cantidad.getText().length() == 0) {
                Entrada.muestreMensajeV("La cantidad debe ser mayor a cero");
                Cantidad.requestFocus();
            } else if (validarCantidad()) {
                registrarKit();
            } else {
                Entrada.muestreMensajeV("Uno de los productos no tiene la cantidad suficiente \n para crear el kit.");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean validarCantidad() {
        boolean r = true;
        int cant = Integer.parseInt(Cantidad.getText());
        for (Producto producto : productos) {
            if ((producto.getCantidadKit() * cant) > producto.getCantidad()) {
                r = false;
                break;
            }
        }
        return r;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            if (evt.getKeyCode() == 10 && condicionfiltro == false) {
                c.setVisible(false);
                this.condicionfiltro = true;
                c.setVisible(false);
                Producto p = null;
                ArrayList<Producto> produc = new ArrayList();
                String cod = "";
                String can = "";
                cod = (String) jTextField2.getText();
                System.out.println("Codigo ::: " + cod);
                boolean Esta = false;
                for (int k = 0; k < productos.size(); k++) {
                    p = (Producto) productos.get(k);
                    if (p.getCodigo().equalsIgnoreCase(cod)) {
                        Esta = true;
                    }
                }
                if (Esta == false) {
                    try {
                        Control.conectar();
                        Producto temp = null;
                        String query = "select nombre,precio_venta,costo,cod_producto,cantidad from producto\n"
                                + "where\n"
                                + "producto.estado='A' and rtrim(ltrim(serie_producto))='" + cod.trim() + "'";
                        Control.ejecuteQuery(query);
                        String nom = "";
                        double precio = 0, costo = 0;
                        int cantidad = 0;
                        int cod_producto = 0;
                        boolean r = false;
                        while (Control.rs.next()) {
                            r = true;
                            nom = Control.rs.getString(1);
                            precio = Control.rs.getDouble(2);
                            costo = Control.rs.getDouble(3);
                            cod_producto = Control.rs.getInt(4);
                            cantidad = Control.rs.getInt(5);
                        }
                        if (r) {
                            temp = new Producto(cod, cod_producto, nom, costo, precio);
                            temp.setCantidad(cantidad);
                            temp.setCantidadKit(1);
                            productos.add(temp);
                            jTextField2.setText("");
                            Costo.setText("" + (Double.parseDouble(Costo.getText()) + costo));
                            this.precio.setText("" + (Double.parseDouble(this.precio.getText()) + precio));
                        } else {
                            Entrada.muestreMensajeV("Codigo  de producto no valido");
                            jTextField2.setText("");

                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Venta.class
                                .getName()).log(Level.SEVERE, null, ex);

                    } catch (SQLException ex) {
                        Logger.getLogger(Venta.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                    //jTable2.requestFocus();
                    Control.cerrarConexion();
                } else {
                    jTextField2.setText("");
                }
                iniciar();

            } else {
                this.condicionfiltro = false;
                Buscar();
            }

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        try {
            if (evt.getKeyCode() == 10) {
                int i = jTable2.getSelectedRow();
                AgregarProductos(i);
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 2) {
            int i = jTable2.getSelectedRow();
            AgregarProductos(i);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        int Cant = Integer.parseInt(Cantidad.getText());
        if (productos.size() == 0 && Cant > 0) {
            Entrada.muestreMensajeV("Primero debe agregar los productos");
            Cantidad.setText("0");
        }
    }//GEN-LAST:event_CantidadActionPerformed

    private void CantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CantidadFocusLost
        int Cant = Integer.parseInt(Cantidad.getText());
        if (productos.size() == 0 && Cant > 0) {
            Entrada.muestreMensajeV("Primero debe agregar los productos");
            Cantidad.setText("0");
        }
    }//GEN-LAST:event_CantidadFocusLost

    public void borrar() {
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("Inventarius", "¿Esta Seguro que Desea Borrar el Producto? ", op);
        if (Condicion == 1) {
            int i = jTable1.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
            } else {
                System.out.println("---------");
                String cod = (String) jTable1.getValueAt(i, 0).toString();
                Producto p = null;

                for (int k = 0; k < productos.size(); k++) {
                    p = (Producto) productos.get(k);
                    if (p.getCodigo().equalsIgnoreCase(cod)) {
                        productos.remove(p);
                    }
                }
                Borrar();
            }

        }

    }

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        try {
            if (evt.getKeyCode() == 127) {
                borrar();
            } else if (evt.getKeyCode() == 10) {
                int i = jTable1.getSelectedRow();
                String cod = (String) jTable1.getValueAt(i, 0).toString();
                String cant = (String) jTable1.getValueAt(i, 3).toString();
                System.out.println("cod: " + cod);
                System.out.println("cant: " + cant);
                int c = 0;
                try {
                    c = Integer.parseInt(cant);
                    if (c > 0) {
                        for (Producto producto : productos) {
                            if (producto.getCodigo().equalsIgnoreCase(cod)) {
                                System.out.println("se registro");
                                producto.setCantidadKit(c);
                            }
                        }
                    }

                } catch (Exception ex) {
                    Entrada.muestreMensajeV("El tipo de dato debe ser numerico");
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al borrar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable1KeyReleased

    public void iniciar() {
        System.out.println("Tamañ de productos " + productos.size());
        TablaModel t = new TablaModel(productos, 5);
        t.calculeFrecuenciasKits();
        muevaLosDatosFre(t);

    }

    public void Borrar() {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < productos.size() + 1; k++) {
                jTable1.setValueAt("", k, i);
            }
        }
        iniciar();
    }

    public void muevaLosDatosFre(TablaModel x) {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable1.setValueAt(x.frecuencias[i][k], k, i);
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

    public void Buscar() throws ClassNotFoundException {
        String query = "";
        if (SoloNumeros(jTextField2.getText())) {
            query = " select distinct * from (select distinct  serie_producto \"Codigo\",nombre,cantidad,precio_venta \"Precio Venta\""
                    + " from producto,categoria where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  \n"
                    + "  producto.serie_producto ILIKE ('%" + jTextField2.getText() + "')  and producto.estado='A'"
                    + "  union all"
                    + " select distinct  serie_producto \"Codigo\",nombre,cantidad,precio_venta \"Precio Venta\""
                    + "  from producto,categoria where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  \n"
                    + "  producto.serie_producto ILIKE ('%" + jTextField2.getText() + "%')  and producto.estado='A')Y limit 10 ";
        } else {
            query = "select distinct  serie_producto \"Codigo\",nombre,cantidad,precio_venta \"Precio Venta\""
                    + "  from producto,categoria where "
                    + "  producto.cod_categoria=categoria.cod_categoria and "
                    + " (categoria.descripcion ILIKE ('%" + jTextField2.getText() + "%') or  "
                    + "producto.nombre ILIKE ('%" + jTextField2.getText() + "%') or "
                    + " producto.serie_producto ILIKE ('%" + jTextField2.getText() + "%') )  and producto.estado='A'";
        }
        Control.conectar();
        Producto temp = null;
        String cod = "", nom = "", valor = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
        String cabeceras[] = {"Producto", "Nombre", "Cantidad", "Precio"};
        DefaultTableModel modeloEmpleado = new DefaultTableModel(null, cabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        boolean r2 = false;

        this.jTable2.setModel(modeloEmpleado);
        try {
            boolean r = Control.ejecuteQuery(query);

            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i <= numeroPreguntas - 1; i++) {
                //modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }

            if (!jTextField2.getText().equals("")) {
                //System.out.println(":: " + Control.rs.next());
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
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Costo;
    private javax.swing.JScrollPane c;
    private javax.swing.JTextField codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    // End of variables declaration//GEN-END:variables
}
