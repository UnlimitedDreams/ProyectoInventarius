/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.model;

import Control.Control;
import Control.Tabla2;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import vw.components.Bodega;
import vw.components.CategoriaGestion;
import vw.components.Entrada;
import vw.components.Entrada_Nueva;
import vw.components.Producto;
import vw.components.Provedores;
import vw.components.Reporte_Entradas;
import vw.components.Reporte_Ventas;
import vw.components.Roles;
import vw.components.Sequence;
import vw.components.Usuarios;
import vw.components.VentaDiaria;
import vw.dialogs.AcercaDe;
import vw.dialogs.CategoriasRegistrar;
import vw.dialogs.ProveedoresRegistrar;
import vw.dialogs.RegistroCliente;
import vw.dialogs.ReporteDetalleVenta;
import vw.dialogs.RolRegistrar;
import vw.dialogs.UsuariosRegistrar;
import vw.main.Acceder;
import vw.main.Menu;

/**
 *
 * @author Britany
 */
public class Venta extends javax.swing.JFrame {

    ArrayList<Producto> productos = new ArrayList();
    Connection cone;
    String usuario;
    int tipoVenta;
    int SecuenciaDesc;
    double ValorPago;
    boolean condicionfiltro;
    String cedulaCliente;
    int codigo_cliente;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    /**
     *
     * @param Acciones
     * @param usuario
     * @param TipoVenta
     * @param acciones
     * @throws ClassNotFoundException
     */
    public Venta() {

    }

    public Venta(ArrayList Acciones,
            String usuario,
            int TipoVenta,
            ArrayList acciones, String cliente)
            throws ClassNotFoundException {
        initComponents();
        c.setVisible(false);
        this.condicionfiltro = false;
        this.ValorPago = 0;
        this.List_Menu = acciones;
        this.usuario = usuario;
        this.tipoVenta = TipoVenta;
        this.cedulaCliente = cliente;
        this.codigo_cliente = 0;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        this.productos = Acciones;
        iniciar();
        descIva.setText("" + 0);
        porcentajeDescuento.setText("" + 0);
        Date fechaMostrar = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha2 = format.format(fechaMostrar);
        fecha.setText("" + fecha2);
        Cliente.setText(cliente);
        rellenar_datos();
        if (tipoVenta == 1) {
            this.setTitle("Realizar Venta - BackBox");
        } else if (TipoVenta == 2) {
            this.setTitle("Realizar Devolución - BackBox");
            descIva.setEditable(false);
        }
        this.SecuenciaDesc = 0;
        ContenedorMenus con_menu = new ContenedorMenus();
        con_menu = (ContenedorMenus) List_Menu.get(0);
        listaSeccion = con_menu.getListaSeccion();
        listaaccion = con_menu.getListaAcciones();
        for (seccion object : listaSeccion) {
            JMenu menu = new JMenu(object.getDescripcion());
            jMenuBar1.add(menu);
            for (acciones object1 : listaaccion) {
                if (object1.getCod_seccion() == object.getCod_seccion()) {
                    if (object1.getAccion().equalsIgnoreCase("Linea")) {
                        menu.add(new JSeparator());
                    } else {
                        //10 = Control + Alt
                        JMenuItem menuItem = new JMenuItem(object1.getAccion());
                        if (object1.getAccion().equalsIgnoreCase("Lista Bodega")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Lista Usuarios")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Crear Usuario")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Lista Rol")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Crear Rol")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Lista Proveedores")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Crear Proveedor")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Ver Articulos")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Nuevo Articulo")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Ver Categoria")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Crear Categoria ")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Realizar Venta")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Realizar Devolucion")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Ver Venta Diaria")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Venta")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Compras")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Entrada y Salidas")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Venta")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Lista Clientes")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 10));
                        }
                        if (object1.getAccion().equalsIgnoreCase("Crear Cliente")) {
                            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
                        }
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                MenuRedireccionar MenuF = new MenuRedireccionar(Venta.this, e.getActionCommand(), List_Menu, usuario);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        Venta.this.dispose();
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        menu.add(menuItem);
                    }
                }
            }
        }
        MenuAyuda();
        try {
            RecuperarNombreCliente(Cliente.getText());
        } catch (Exception ex) {

        }

        jTextField2.requestFocus();

        //jTable2.changeSelection(0, 0, false, false);
    }

    public void MenuAyuda() {
        ArrayList<String> Ayuda = new ArrayList();
        Ayuda.add("Ayuda en Linea");
        Ayuda.add("Linea");
        Ayuda.add("Back Box");
        Ayuda.add("Atajos");
        Ayuda.add("Acerca de");
        JMenu menu = new JMenu("Ayuda");
        jMenuBar1.add(menu);
        for (String object1 : Ayuda) {

            if (object1.equalsIgnoreCase("Linea")) {
                menu.add(new JSeparator());
            } else {
                JMenuItem menuItem = new JMenuItem(object1);
                if (object1.equalsIgnoreCase("Lista Bodega")) {
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, ActionEvent.ALT_MASK));
                }
                if (object1.equalsIgnoreCase("Lista Usuarios")) {
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
                }
                if (object1.equalsIgnoreCase("Crear Usuario")) {
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
                }
                menuItem.addActionListener((ActionEvent e) -> {
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario);
                    try {
                        MenuF.reDireccion();

                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menu.add(menuItem);
            }
        }
    }

    public void RecuperarNombreCliente(String cedulacliente) throws SQLException {

        try {
            Control.conectar();
            Control.ejecuteQuery("select concat(nombre,' ',apellido),cod_cliente from clientes where cedula='" + cedulacliente + "'");
            while (Control.rs.next()) {
                NomCliente.setText("Cliente : " + Control.rs.getString(1));
                codigo_cliente = Control.rs.getInt(2);
                Cliente.setText(cedulacliente);
            }
            Control.cerrarConexion();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iniciar() {
        Tabla2 t = new Tabla2(productos);
        t.calculeFrecuenciasV();
        muevaLosDatosFre(t);

    }

    public void muevaLosDatosFre(Tabla2 x) {
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable2.setValueAt(x.frecuencias[i][k], k, i);
            }
        }
    }

    public void rellenar_datos() {
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        Producto pro = null;
        double valor = 0;
        int cant = 0;
        int iva = 0;
        for (int i = 0; i < productos.size(); i++) {
            pro = (Producto) productos.get(i);
            cant = pro.getCantidad();
            valor = valor + (pro.getPrecio_venta() * cant);
            iva = iva + (int) ((pro.getPrecio_venta() * cant) * (pro.getIva() / 100));
        }
        subtotal.setText("" + formateador.format(valor));
        String d = "";
        int desc = 0;
        if (descIva.getText() == null) {
            d = "0";
        } else {
            desc = Integer.parseInt(descIva.getText());

        }
        if (descIva.getText().length() == 1) {
            d = "0.0" + desc;

        } else if (descIva.getText().length() == 2) {
            d = "0." + desc;

        }
        double de = Double.parseDouble(d);
        double valor_f = 0;
        if (tipoVenta == 1) {
            valor_f = valor - (valor * de);
            valor_f = valor_f + iva;
            valorDescuento.setText("" + formateador.format(valor * de));
            porcentajeIVA.setText("" + formateador.format(iva));

        } else {
            valor_f = -1 * (valor);
            valorDescuento.setText("" + 0);
        }
        ValorPago = valor_f;
        jLTotal.setText("" + formateador.format(valor_f));

//        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jMenu7 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        c = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        descuento = new javax.swing.JLabel();
        descIva = new javax.swing.JTextField();
        medioDePago = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        labelCambio = new javax.swing.JLabel();
        jLTotal = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        IVA = new javax.swing.JLabel();
        valorDescuento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        medioPago = new javax.swing.JComboBox<>();
        Descuento = new javax.swing.JLabel();
        porcentajeIVA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        labelPago = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Cliente = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        IVA1 = new javax.swing.JLabel();
        porcentajeDescuento = new javax.swing.JTextField();
        medioDePago1 = new javax.swing.JLabel();
        montoPago = new javax.swing.JTextField();
        NomCliente = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel1FocusLost(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, -1, -1));

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

        jPanel1.add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 620, 140));

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyPressed(evt);
            }
        });

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Valor", "Cantidad"
            }
        ));
        jTable2.setAutoscrolls(false);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setEditingColumn(0);
        jTable2.setEditingRow(0);
        jTable2.setGridColor(new java.awt.Color(102, 102, 102));
        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable2FocusLost(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 700, 220));

        descuento.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        descuento.setText("Descuento ($):");
        jPanel1.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        descIva.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        descIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descIvaActionPerformed(evt);
            }
        });
        descIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descIvaKeyReleased(evt);
            }
        });
        jPanel1.add(descIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 445, 135, 28));

        medioDePago.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        medioDePago.setText("CC. Cliente :");
        jPanel1.add(medioDePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        subtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        subtotal.setEnabled(false);
        subtotal.setPreferredSize(new java.awt.Dimension(137, 26));
        jPanel1.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 135, 28));

        labelCambio.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        labelCambio.setText("Cambio:");
        jPanel1.add(labelCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 110, -1));

        jLTotal.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLTotal.setForeground(new java.awt.Color(255, 102, 0));
        jLTotal.setText("Total");
        jLTotal.setToolTipText("");
        jPanel1.add(jLTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 300, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 510, -1, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/Gente_opt.png"))); // NOI18N
        jButton5.setText("Cliente");
        jButton5.setToolTipText("");
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
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, -1, 70));

        IVA.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        IVA.setText("IVA (%):");
        jPanel1.add(IVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 445, 120, -1));

        valorDescuento.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        valorDescuento.setEnabled(false);
        valorDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorDescuentoActionPerformed(evt);
            }
        });
        jPanel1.add(valorDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 135, 28));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel7.setText("Sub Total:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        medioPago.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        medioPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        medioPago.setToolTipText("Método de Pago del Cliente");
        medioPago.setPreferredSize(new java.awt.Dimension(130, 28));
        medioPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medioPagoActionPerformed(evt);
            }
        });
        jPanel1.add(medioPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 375, 135, -1));

        Descuento.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        Descuento.setText("Descuento (%):");
        jPanel1.add(Descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 140, -1));

        porcentajeIVA.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        porcentajeIVA.setEnabled(false);
        porcentajeIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeIVAActionPerformed(evt);
            }
        });
        jPanel1.add(porcentajeIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 515, 135, 28));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel5.setText("Total:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 110, -1));

        labelPago.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        labelPago.setText("Pagó:");
        jPanel1.add(labelPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 375, 110, -1));

        cambio.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        cambio.setForeground(new java.awt.Color(255, 102, 0));
        cambio.setText("0");
        cambio.setToolTipText("");
        jPanel1.add(cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 300, -1));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, -1, 30));

        Cliente.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Cliente.setToolTipText("Monto en dinero del Pago del Cliente");
        Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteActionPerformed(evt);
            }
        });
        Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClienteKeyReleased(evt);
            }
        });
        jPanel1.add(Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 135, 28));

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
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
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 38, 620, 33));

        IVA1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        IVA1.setText("IVA ($):");
        jPanel1.add(IVA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 515, -1, -1));

        porcentajeDescuento.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        porcentajeDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeDescuentoActionPerformed(evt);
            }
        });
        porcentajeDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                porcentajeDescuentoKeyReleased(evt);
            }
        });
        jPanel1.add(porcentajeDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 135, 28));

        medioDePago1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        medioDePago1.setText("Tipo de Pago:");
        jPanel1.add(medioDePago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 375, -1, -1));

        montoPago.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        montoPago.setToolTipText("Monto en dinero del Pago del Cliente");
        montoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montoPagoActionPerformed(evt);
            }
        });
        montoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                montoPagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montoPagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montoPagoKeyTyped(evt);
            }
        });
        jPanel1.add(montoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 375, 135, 28));

        NomCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NomCliente.setText("jLabel1");
        jPanel1.add(NomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 490, 30));

        fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha.setText("jLabel1");
        jPanel1.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 170, 30));

        file.setText("Archivo");
        file.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        inicio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        inicio.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        inicio.setText("Inicio");
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });
        file.add(inicio);

        cerrarSesion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        file.add(cerrarSesion);
        file.add(jSeparator1);

        salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        salir.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        file.add(salir);

        jMenuBar1.add(file);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (productos.size() == 0) {
                Entrada.muestreMensajeV("No hay Productos para vender");
            } else {
                boolean r = false;
                Producto p = null;
                for (int k = 0; k < productos.size(); k++) {
                    p = (Producto) productos.get(k);
                    if (p.getPrecio_venta() == 0) {
                        r = true;
                    }
                }
                if (r) {
                    Entrada.muestreMensajeV("Los Productos no pueden Tener Precio en Cero");
                } else {
                    Vender();
                }

            }
        } catch (Exception ex) {
            System.err.println("error : " + ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public void generarFactura(int cod_factura, Date fecha, Connection c) throws JRException, ClassNotFoundException {

        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Cod_fac", cod_factura);
            parametros.put("fec_fac", fecha);
            parametros.put("desc", Integer.parseInt(descIva.getText()));
            parametros.put("Iva", Double.parseDouble(porcentajeIVA.getText()));
            parametros.put("ValorDesc", Double.parseDouble(valorDescuento.getText()));
            parametros.put("Total", ValorPago);
            JasperReport report = JasperCompileManager.compileReport("Reportes/Fac.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, c);
            //Mostrar en pdf   // JasperViewer.viewReport(jasperPrint);   
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public void Vender() throws ClassNotFoundException, JRException, SQLException {
        int codigo_venta = Sequence.seque("select max(cod_factura) from venta");
        int codigo_pro = (Sequence.seque("select max(cod_venta) from venta_pro"));

        Control.conectar();
        cone = Control.con;
        int cod_pago = 0;
        if (medioPago.getSelectedIndex() == 0) {
            cod_pago = 1;
        } else if (medioPago.getSelectedIndex() == 1) {
            cod_pago = 2;
        }
        Date fecha = new Date();
        double valorIva = Double.parseDouble(porcentajeIVA.getText());
        boolean r = Control.ejecuteUpdate("insert into venta values(" + codigo_venta + ",'" + fecha + "'," + ValorPago
                + "," + usuario + "," + tipoVenta + "," + cod_pago + "," + valorIva + ""
                + "," + Integer.parseInt(descIva.getText()) + "," + Double.parseDouble(valorDescuento.getText()) + "," + codigo_cliente + ")");
        if (r) {
            Producto pro = null;
            for (int i = 0; i < productos.size(); i++) {
                pro = (Producto) productos.get(i);
                Control.ejecuteUpdate("insert into venta_pro values(" + codigo_pro + ",'" + pro.getCodigo() + "',"
                        + codigo_venta + "," + pro.getCantidad() + ")");
                codigo_pro++;
            }
            restar_Bodega();
            generarFactura(codigo_venta, fecha, cone);
            Control.cerrarConexion();
            Restar_Todo();

        } else {
            System.out.println("Error en venta");
        }
    }

    public void Restar_Todo() {
        descIva.setText("0");
        subtotal.setText("0");
        jLTotal.setText("0");

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < productos.size(); k++) {
                jTable2.setValueAt("", k, i);
            }
        }
        productos.clear();
        iniciar();
    }

    public void Borrar() {
        System.out.println("+++++");
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < productos.size() + 1; k++) {
                jTable2.setValueAt("", k, i);

            }
        }
        iniciar();
    }

    public void Borrar2() {
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 1; k++) {
                jTable2.setValueAt("", k, i);
            }
        }
        iniciar();
    }

    public void restar_Bodega() throws ClassNotFoundException {
        //control.conectar();
        Producto pro = null;
        int codigo_pro = 0;
        for (int i = 0; i < productos.size(); i++) {
            pro = (Producto) productos.get(i);
            if (tipoVenta == 1) {
                Control.ejecuteUpdate("update producto set cantidad=cantidad-" + pro.getCantidad() + " where "
                        + "cod_producto='" + pro.getCodigo() + "'");
            } else if (tipoVenta == 2) {
                Control.ejecuteUpdate("update producto set cantidad=cantidad+" + pro.getCantidad() + " where "
                        + "cod_producto='" + pro.getCodigo() + "'");
            }

        }
        //control.cerrarConexion();

    }


    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered

    }//GEN-LAST:event_jTable2MouseEntered

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed

    }//GEN-LAST:event_jTable2MousePressed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        try {
            if (evt.getKeyCode() == 127) {
                borrar();
            }
        } catch (Exception ex) {
            System.err.println("Error al borrar producto :" + ex.toString());
        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void descIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descIvaActionPerformed
        rellenar_datos();
    }//GEN-LAST:event_descIvaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Menu(usuario).setVisible(true);;
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        RegistroCliente r = null;
        r = new RegistroCliente(this, true, productos, usuario, List_Menu, 1);
        r.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        //System.out.println("entro por aqui");
        //jTable2.requestFocus();


    }//GEN-LAST:event_jTable2KeyReleased

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyTyped

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        // TODO add your handling code here:
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusLost
        c.setVisible(false);
        jTextField2.setText("");

    }//GEN-LAST:event_jTable2FocusLost

    private void valorDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorDescuentoActionPerformed

    private void porcentajeIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeIVAActionPerformed

    private void descIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descIvaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_descIvaKeyReleased

    private void medioPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medioPagoActionPerformed
        if (medioPago.getSelectedIndex() == 0) {
            Cliente.setVisible(true);
            cambio.setVisible(true);
            labelCambio.setVisible(true);
            labelPago.setVisible(true);
        } else {
            Cliente.setVisible(false);
            cambio.setVisible(false);
            labelCambio.setVisible(false);
            labelPago.setVisible(false);
        }
    }//GEN-LAST:event_medioPagoActionPerformed

    private void ClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClienteKeyReleased


    }//GEN-LAST:event_ClienteKeyReleased

    private void ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteActionPerformed

    }//GEN-LAST:event_ClienteActionPerformed

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicioActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed

        this.dispose();
        new Acceder().setVisible(true);

    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed
    public boolean SoloNumerosVenta(String cadena) {
        long num = 0;
        try {
            Long.parseLong(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            System.out.println("******************* INICIO *************************");
            System.out.println("::: " + condicionfiltro);
            if (evt.getKeyCode() == 10 && condicionfiltro == false) {
                c.setVisible(false);
                if (SoloNumerosVenta(jTextField2.getText())) {
                    this.condicionfiltro = true;
                    c.setVisible(false);
                    Producto p = null;
                    ArrayList<Producto> produc = new ArrayList();
                    String cod = "";
                    String can = "";
                    System.out.println("+++++++++++++ :" + jTextField2.getText());
                    cod = (String) jTextField2.getText();
                    System.out.println("Codigo ::: " + cod);
                    boolean Esta = false;
                    for (int k = 0; k < productos.size(); k++) {
                        p = (Producto) productos.get(k);
                        if (p.getCodigo().equalsIgnoreCase(cod)) {
                            System.out.println("Agrego cant");
                            Esta = true;
                            p.setCantidad(p.getCantidad() + 1);
                        }
                    }
                    if (Esta == false) {
                        try {
                            Control.conectar();
                            Producto temp = null;
                            String query = "select nombre,precio_desc,iva "
                                    + "from producto\n"
                                    + "where\n"
                                    + "producto.estado='A' and cod_producto='" + cod + "'";
                            Control.ejecuteQuery(query);
                            String nom = "";
                            double precio = 0;
                            double iva = 0;
                            boolean r = false;
                            while (Control.rs.next()) {
                                r = true;
                                nom = Control.rs.getString(1);
                                precio = Control.rs.getDouble(2);
                                iva = Control.rs.getDouble(3);
                            }
                            if (r) {
                                temp = new Producto(cod, nom, precio, 1, iva);
                                productos.add(temp);
                                jTextField2.setText("");
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
                    } else {
                        jTextField2.setText("");
                    }
                    iniciar();
                    rellenar_datos();
                } else {
                    Entrada.muestreMensajeV("Codigo  de producto no valido");
                }

            } else {
                this.condicionfiltro = false;
                Buscar();
            }

        } catch (Exception ex) {

        }
        System.out.println(":::------------------ " + condicionfiltro);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost


    }//GEN-LAST:event_jTextField2FocusLost

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited

    }//GEN-LAST:event_jTable1MouseExited

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged

    }//GEN-LAST:event_jTable1MouseDragged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        System.out.println("click : " + evt.getClickCount());
        if (evt.getClickCount() == 2) {
            System.out.println("agregarProducto");
            int i = jTable1.getSelectedRow();
            AgregarProductos(i);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        c.setVisible(false);
        jTextField2.setText("");
    }//GEN-LAST:event_jTable1FocusLost

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2MouseClicked

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained


    }//GEN-LAST:event_jPanel1FocusGained

    private void jPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusLost

    }//GEN-LAST:event_jPanel1FocusLost

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        System.out.println("Pierde focus");
    }//GEN-LAST:event_jTable1FocusGained
    public void AgregarProductos(int i) {
        this.condicionfiltro = true;
        Producto p = null;
        String cod = "";
        String can = "";
        if (i == -1) {
            System.out.println("FAVORRRR");
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fil");
        } else if (i >= 0) {
            cod = (String) jTable1.getValueAt(i, 0).toString();
            System.out.println("Trajo producto :::: " + cod);
            boolean Esta = false;
            for (int k = 0; k < productos.size(); k++) {
                p = (Producto) productos.get(k);
                if (p.getCodigo().equalsIgnoreCase(cod)) {
                    System.out.println("Sumo cantidad");
                    Esta = true;
                    p.setCantidad(p.getCantidad() + 1);
                }
            }
            if (Esta == false) {
                System.out.println("Consulto datos");
                try {
                    Control.conectar();
                    Producto temp = null;
                    String query = "select nombre,precio_desc,iva "
                            + "from producto\n"
                            + "where\n"
                            + "producto.estado='A' and cod_producto='" + cod + "'";
                    Control.ejecuteQuery(query);
                    String nom = "";
                    double precio = 0;
                    double iva = 0;
                    boolean r = false;
                    while (Control.rs.next()) {
                        r = true;
                        nom = Control.rs.getString(1);
                        precio = Control.rs.getDouble(2);
                        iva = Control.rs.getDouble(3);
                    }
                    Control.cerrarConexion();
                    if (r) {
                        System.out.println("Agrego producto : " + cod);
                        temp = new Producto(cod, nom, precio, 1, iva);
                        productos.add(temp);
                    } else {
                        jTable1.setValueAt("", i, 0);

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
        rellenar_datos();
        // jTable2.transferFocus();
        // jTable2.updateUI();
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

    private void jScrollPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2KeyPressed

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusGained

    private void porcentajeDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeDescuentoActionPerformed

    private void porcentajeDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeDescuentoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeDescuentoKeyReleased
    public boolean SoloNumeros(String cadena) {
        double valor = 0;
        try {
            valor = Double.parseDouble(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private void ClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClienteKeyPressed
        try {
            System.out.println("entroo al cliente enter " + evt.getKeyCode());
            if (evt.getKeyCode() == 10) {
                Control.conectar();
                boolean r = false;
                Control.ejecuteQuery("select concat(nombre,' ',apellido) from clientes where cedula='" + Cliente.getText() + "'");
                while (Control.rs.next()) {
                    System.out.println("encontro datos");
                    r = true;
                    NomCliente.setText("Cliente : " + Control.rs.getString(1));
                }
                System.out.println("--- " + r);
                if (r == false) {
                    Entrada.muestreMensajeV("La cedula ingresada no esta registrada como cliente");
                    Cliente.setText(this.cedulaCliente);
                }
                Control.cerrarConexion();
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar producto :" + ex.toString());
        }
    }//GEN-LAST:event_ClienteKeyPressed

    private void montoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoPagoActionPerformed

    private void montoPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoPagoKeyPressed

    }//GEN-LAST:event_montoPagoKeyPressed

    private void montoPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoPagoKeyReleased
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        System.out.println("entro");
        if (montoPago.getText().length() > 0) {
            double valor = 0;
            if (!SoloNumeros(montoPago.getText())) {
                try {
                    montoPago.setText(montoPago.getText().substring(0, (montoPago.getText().length() - 1)));
                } catch (StringIndexOutOfBoundsException e) {
                    /*Error Normal*/
                }
            } else {
                cambio.setText("$ " + formateador.format((Double.parseDouble(montoPago.getText())) - ValorPago));
            }
        } else {
            cambio.setText("$ 0");
        }
    }//GEN-LAST:event_montoPagoKeyReleased

    private void montoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoPagoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_montoPagoKeyTyped
    public void Buscar() throws ClassNotFoundException {
        String query = "";
        if (SoloNumeros(jTextField2.getText())) {
            query = "select distinct  cod_producto \"Codigo\",nombre,precio_desc \"Precio Venta\""
                    + " from producto,categoria where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  \n"
                    + "  producto.cod_producto ILIKE ('%" + jTextField2.getText() + "')  and producto.estado='A'"
                    + "  union "
                    + " select '0' \"Codigo\" ,'nada' \"nombre\" ,0 \"Precio Venta\" limit 10 ";
        } else {
            query = "select distinct  cod_producto \"Codigo\",nombre,precio_desc \"Precio Venta\""
                    + " from producto,categoria where "
                    + "  producto.cod_categoria=categoria.cod_categoria and "
                    + "  producto.nombre ILIKE ('%" + jTextField2.getText() + "%')  and producto.estado='A'";
        }

        System.out.println(query);
        Control.conectar();
        Producto temp = null;
        String cod = "", nom = "", valor = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
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

        boolean r = Control.ejecuteQuery(query);

        try {
            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i <= numeroPreguntas; i++) {
                //modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }
            System.out.println("--- " + Control.rs.getRow());
            System.out.println("---- " + !jTextField2.getText().equals(""));
            if (Control.rs.next() && !jTextField2.getText().equals("")) {
                //System.out.println(":: " + Control.rs.next());
                while (Control.rs.next()) {
                    System.out.println("Trajo datos");
                    cod = Control.rs.getString(1);
                    nom = Control.rs.getString(2);
                    costo = Control.rs.getString(3);
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
            Control.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            try {
            } catch (Exception e) {;
            }
        }
    }

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
                System.out.println("---------");
                String cod = (String) jTable2.getValueAt(i, 0).toString();
                Producto p = null;

                for (int k = 0; k < productos.size(); k++) {
                    p = (Producto) productos.get(k);
                    if (p.getCodigo().equalsIgnoreCase(cod)) {
                        System.out.println("borrar");
                        productos.remove(p);
                    }
                }
                System.out.println("size " + productos.size());
                if (productos.size() == 0) {
                    Borrar2();
                } else {
                    Borrar();

                }
                rellenar_datos();

            }

        }

    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Cliente;
    private javax.swing.JLabel Descuento;
    private javax.swing.JLabel IVA;
    private javax.swing.JLabel IVA1;
    private javax.swing.JLabel NomCliente;
    private javax.swing.JScrollPane c;
    private javax.swing.JLabel cambio;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JTextField descIva;
    private javax.swing.JLabel descuento;
    private javax.swing.JLabel fecha;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel labelCambio;
    private javax.swing.JLabel labelPago;
    private javax.swing.JLabel medioDePago;
    private javax.swing.JLabel medioDePago1;
    private javax.swing.JComboBox<String> medioPago;
    private javax.swing.JTextField montoPago;
    private javax.swing.JTextField porcentajeDescuento;
    private javax.swing.JTextField porcentajeIVA;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTextField valorDescuento;
    // End of variables declaration//GEN-END:variables
}