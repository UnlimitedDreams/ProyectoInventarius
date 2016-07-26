/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.model;

import Control.Control;
import Control.Entrada;
import Control.LeerExcel;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.Producto;
import Modelo.acciones;
import Modelo.exportar_excel;
import Modelo.seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import vw.components.Bodega;
import vw.components.CargaArchivo;
import vw.components.Datalles;
import vw.components.Entrada_Nueva;
import vw.help.Ayudas;
import vw.main.Acceder;
import vw.main.Menu;

/**
 *
 * @author Britany
 */
public class Articulo extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Articulo
     */
    String usuario;
    int codEmpresa;
    int regimen;
    Thread Hilo1;
    Thread Hilo2;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();
    ArrayList<Integer> listIvas = new ArrayList();
    ArrayList<Integer> listCategorias = new ArrayList();
    Ayudas miAyuda;

    public Articulo() {
        initComponents();
    }

    public Articulo(String nom, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        initComponents();
        inicio();
        jProgressBar1.setVisible(false);
        this.List_Menu = acciones;
        this.usuario = nom;
        this.regimen = 0;
        this.codEmpresa = codEmpresa;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
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
                        if (object1.getAccion().equalsIgnoreCase("Nueva Compra")) {
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
                                MenuRedireccionar MenuF = new MenuRedireccionar(Articulo.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        Articulo.this.dispose();
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        menu.add(menuItem);
                    }
                }
            }
        }
        MenuAyuda();
        Permisos();

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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario, codEmpresa);
                    try {
                        MenuF.reDireccion();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menu.add(menuItem);
            }
        }
    }

    public void ConfigurarIva() throws SQLException {
        try {
            listIvas.clear();
            Control.conectar();
            Control.ejecuteQuery("select porcentaje from maestro_iva");
            while (Control.rs.next()) {
                listIvas.add(Control.rs.getInt(1));
            }
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListaCategoria() throws SQLException {
        try {
            listCategorias.clear();
            Control.conectar();
            Control.ejecuteQuery("select cod_categoria from categoria where estado='A'");
            while (Control.rs.next()) {
                listCategorias.add(Control.rs.getInt(1));
            }
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Permisos() throws ClassNotFoundException {
        Control.conectar();
        try {
            ArrayList<String> acciones = new ArrayList();
            Control.ejecuteQuery("select c.accion from usuario a, persona b , permisos c\n"
                    + "where\n"
                    + "a.cedula=b.cedula and \n"
                    + "a.cod_usuario=c.cod_usuario\n"
                    + "and c.panel='Articulo'\n"
                    + "and a.cod_usuario=" + usuario);
            while (Control.rs.next()) {
                acciones.add(Control.rs.getString(1));
            }
            agregar.setEnabled(false);
            cargarExcel.setEnabled(false);
            bajarExcel.setEnabled(false);
            String acci = "";
            for (String accione : acciones) {
                acci = (String) accione;
                if (acci.equalsIgnoreCase("NuevaCompra")) {
                    agregar.setEnabled(true);
                } else if (acci.equalsIgnoreCase("ArtiuloCarga")) {
                    cargarExcel.setEnabled(true);
                } else if (acci.equalsIgnoreCase("ArticuloReporte")) {
                    bajarExcel.setEnabled(true);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        bajarExcel = new javax.swing.JButton();
        cargarExcel = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        ayuda = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BackBox - Artículos");
        setPreferredSize(new java.awt.Dimension(815, 565));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField2.setSelectionColor(new java.awt.Color(51, 0, 255));
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
        });

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        agregar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        agregar.setText("Agregar");
        agregar.setBorder(null);
        agregar.setBorderPainted(false);
        agregar.setContentAreaFilled(false);
        agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar.setPreferredSize(new java.awt.Dimension(55, 47));
        agregar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        volver.setToolTipText("Volver");
        volver.setBorder(null);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volver.setPreferredSize(new java.awt.Dimension(55, 47));
        volver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        volver.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        bajarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/export_to_excel_24dp.png"))); // NOI18N
        bajarExcel.setToolTipText("Exportar a Excel");
        bajarExcel.setBorder(null);
        bajarExcel.setBorderPainted(false);
        bajarExcel.setContentAreaFilled(false);
        bajarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bajarExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bajarExcel.setPreferredSize(new java.awt.Dimension(55, 47));
        bajarExcel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bajarExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bajarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajarExcelActionPerformed(evt);
            }
        });

        cargarExcel.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        cargarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_attach_file_black_24dp.png"))); // NOI18N
        cargarExcel.setText("Cargar");
        cargarExcel.setBorder(null);
        cargarExcel.setBorderPainted(false);
        cargarExcel.setContentAreaFilled(false);
        cargarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargarExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cargarExcel.setPreferredSize(new java.awt.Dimension(55, 47));
        cargarExcel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cargarExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cargarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarExcelActionPerformed(evt);
            }
        });

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setForeground(new java.awt.Color(0, 0, 0));

        ayuda.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_help_outline_black_24dp.png"))); // NOI18N
        ayuda.setToolTipText("Ayuda");
        ayuda.setAlignmentY(0.0F);
        ayuda.setBorder(null);
        ayuda.setBorderPainted(false);
        ayuda.setContentAreaFilled(false);
        ayuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ayuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ayuda.setMargin(new java.awt.Insets(3, 3, 3, 3));
        ayuda.setPreferredSize(new java.awt.Dimension(33, 30));
        ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cargarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bajarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(volver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cargarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bajarExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarExcelActionPerformed
        Hilo1 = new Thread(this);
        Hilo1.start();

//Thread hiloA = new Thread(new Articulo(), "hiloA");
        //  Thread hiloB = new Thread(new MiHilo(), "hiloB");

    }//GEN-LAST:event_cargarExcelActionPerformed
    public void cargarFile() {
        try {

            JFileChooser dlg = new JFileChooser();
            int option = dlg.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                String file = dlg.getSelectedFile().getPath();
                ListaCategoria();
                ConfigurarIva();
                pasar_datos(file);
            }
        } catch (Exception ex) {
            System.err.println("Error : " + ex.toString());
            Entrada.muestreMensajeV("Documento no valido");
            jProgressBar1.setVisible(false);

        }
    }
    private void bajarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajarExcelActionPerformed
        if (jTable1.getRowCount() >= 1) {
            getBto_exportar();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Datos Para Exportar");
        }
    }//GEN-LAST:event_bajarExcelActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);
        miAyuda.dispose();
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            Buscar();
        } catch (Exception ex) {
            System.out.println("error " + ex.toString());
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        try {
            Entradas();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Articulo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_agregarActionPerformed
    public void Entradas() throws ClassNotFoundException {
        String fac = "";
        ArrayList<Producto> pr = new ArrayList();
        Entrada_Nueva rp = new Entrada_Nueva(pr, usuario, fac, List_Menu, codEmpresa);
        this.setVisible(false);
        rp.setVisible(true);

    }
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

    private void ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaActionPerformed
        miAyuda = new Ayudas(1);
        miAyuda.setVisible(true);
    }//GEN-LAST:event_ayudaActionPerformed
    private void getBto_exportar() {
        try {
            List<JTable> tb = new ArrayList<JTable>();
            JTable table = new javax.swing.JTable();
            table = CargaInventario();
            tb.add(table);
            exportar_excel excelExporter = new exportar_excel(tb, new File("DATOS_EXPORTADOS.xls"));
            if (excelExporter.export()) {
                JOptionPane.showMessageDialog(null, "DATOS EXPORTADOS CON EXITO!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        llama_excel();

    }

    public void llama_excel() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "DATOS_EXPORTADOS.xls");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void detalles_pro() throws ClassNotFoundException {
        int i = jTable1.getSelectedRow();
        int j = jTable1.getSelectedColumn();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            Datalles p = new Datalles(cod, usuario, List_Menu);
            p.setVisible(true);
            this.setVisible(false);
        }
    }

    public void pasar_datos(String ruta) throws ClassNotFoundException, InterruptedException {
        //jProgressBar1.setForeground(Color.ORANGE);
        jProgressBar1.setValue(0);
        jProgressBar1.setStringPainted(true);
        String movimiento[] = null;
        String costo[] = null;
        String iva[] = null;
        String precio[] = null;
        String categoria[] = null;
        String cantidad[] = null;
        String nombres[] = null;
        String stock[] = null;
        LeerExcel Hoja = new LeerExcel();
        Hoja.f_datos_1(ruta, 0);//codigo
        jProgressBar1.setValue(10);
        jProgressBar1.setStringPainted(true);
        movimiento = Hoja.Carga();
        Hoja.f_datos_1(ruta, 1);//Nombre
        jProgressBar1.setValue(20);
        jProgressBar1.setStringPainted(true);
        nombres = Hoja.Carga();
        Hoja.f_datos_1(ruta, 2);//categoria
        jProgressBar1.setValue(30);
        jProgressBar1.setStringPainted(true);
        categoria = Hoja.Carga();
        Hoja.f_datos_1(ruta, 3);//costo
        jProgressBar1.setValue(40);
        jProgressBar1.setStringPainted(true);
        costo = Hoja.Carga();
        Hoja.f_datos_1(ruta, 4);//iva
        jProgressBar1.setValue(50);
        jProgressBar1.setStringPainted(true);
        iva = Hoja.Carga();
        Hoja.f_datos_1(ruta, 5);//precio
        jProgressBar1.setValue(60);
        jProgressBar1.setStringPainted(true);
        precio = Hoja.Carga();
        Hoja.f_datos_1(ruta, 6);//stock
        jProgressBar1.setValue(70);
        jProgressBar1.setStringPainted(true);
        precio = Hoja.Carga();
        Hoja.f_datos_1(ruta, 7);//cantidad
        jProgressBar1.setValue(80);
        jProgressBar1.setStringPainted(true);
        cantidad = Hoja.Carga();
        ArrayList datos = null;
        if ((movimiento.length == costo.length) && (iva.length == precio.length) && (categoria.length == cantidad.length
                && nombres.length == movimiento.length)) {
            if (ValidarDatosEnTabla(costo, 1)) {
                if (ValidarDatosEnTabla(iva, 2)) {
                    if (ValidarDatosEnTabla(precio, 3)) {
                        if (ValidarDatosEnTabla(categoria, 4)) {
                            if (ValidarDatosEnTabla(cantidad, 5)) {
                                jProgressBar1.setValue(90);
                                jProgressBar1.setStringPainted(true);
                                datos = DatosPersona(movimiento, nombres, costo, iva, precio, categoria, cantidad);
                                CargaArchivo car = new CargaArchivo(datos, usuario, List_Menu);
                                this.dispose();
                                car.setVisible(true);
                            }
                        }
                    }
                }
            }
        } else {
            Entrada.muestreMensajeV("Verifique la cantidad de filas del archivo deben ser iguales");
        }

    }

    public String[] compilarLetras(String x[]) {
        String n[] = new String[x.length - 1];
        for (int i = 0; i < n.length; i++) {
            n[i] = x[i + 1];
        }
        return n;
    }

    public boolean isnumero(String x, int condicion) {
        int a = 0;
        double b = 0;
        boolean r = false;
        try {
            if (condicion == 1) {
                a = Integer.parseInt(x);
                r = true;
            } else if (condicion == 2) {
                b = Double.parseDouble(x);
                r = true;
            }

            return r;
        } catch (Exception ex) {
            return r;
        }
    }

    public boolean ValidarDatosEnTabla(String[] o, int condi) {
        String mnserror = "N";
        boolean r = true;
        boolean iva = false;
        String valor = "";
        //listIvas
        System.out.println("Size : " + o.length);
        for (Object object : o) {
            valor = (String) object;
            System.out.println(" Valor " + valor + " Condicion : " + condi);
            if (condi == 1 && isnumero(valor, 2) == false) {
                mnserror = "El valor del costo debe ser Numerico";
                r = false;
                break;
            } else if (condi == 1 && isnumero(valor, 2)) {
                if (Double.parseDouble(valor) <= 0) {
                    mnserror = "El valor del costo debe ser mayor a 0 (Cero))";
                    r = false;
                    break;
                }
            } else if (condi == 2 && isnumero(valor, 1) == false) {
                mnserror = "El valor del iva debe ser Numerico";
                r = false;
                break;
            } else if (condi == 2 && isnumero(valor, 1)) {
                for (int Valoriva : listIvas) {
                    System.out.println("iva  : " + Valoriva + " valor : " + valor);
                    if (Integer.parseInt(valor) == Valoriva) {

                        iva = true;
                    }
                }
                if (iva == false) {
                    mnserror = "El valor del iva debe estar dentro de los parametros del iva permitido";
                    r = false;
                    break;
                }
            } else if (condi == 3 && isnumero(valor, 1) == false) {
                System.out.println("Entro valor del precio numerico");
                mnserror = "El valor del Precio debe ser Numerico";
                r = false;
                break;
            } else if (condi == 3 && isnumero(valor, 2)) {
                if (Double.parseDouble(valor) <= 0) {
                    mnserror = "El valor del Precio debe ser mayor a cero";
                    r = false;
                    break;
                }
            } else if (condi == 4 && isnumero(valor, 1) == false) {
                mnserror = "El codigo de la categoria debe ser Numerico";
                r = false;
                break;
            } else if (condi == 4 && isnumero(valor, 1)) {

                for (Integer cate : listCategorias) {
                    if (Integer.parseInt(valor) == cate) {
                        iva = true;
                    }
                }
                if (iva == false) {
                    mnserror = "El codigo de la categoria No pertenece a una categoria del sistema";
                    r = false;
                    break;
                }
            } else if (condi == 5 && isnumero(valor, 1) == false) {
                mnserror = "La cantidad del producto debe ser numerica";
                r = false;
                break;
            } else if (condi == 5 && isnumero(valor, 1)) {
                if (Integer.parseInt(valor) < 0) {
                    System.out.println(":::::::::::::::::::::: " + valor);
                    mnserror = "La cantidad del producto debe ser mayor a Cero (0)";
                    r = false;
                    break;
                }
            }
        }

//      
        if (mnserror != "N") {
            Entrada.muestreMensajeV(mnserror);
        }
        return r;
    }

    public ArrayList DatosPersona(String cod[], String nom[], String cos[], String iva[], String precio[], String cat[], String cant[]) {
        ArrayList<Producto> pro = new ArrayList();

        for (int i = 0; i < cod.length; i++) {
            Producto p = new Producto("" + cod[i], nom[i], Double.parseDouble(cos[i]), Integer.parseInt(iva[i]),
                    Double.parseDouble(precio[i]), Integer.parseInt(cant[i]), 0);
            p.setCategoria(Integer.parseInt(cat[i]));
            System.out.println("-" + p.toString());
            pro.add(p);
        }
        return pro;
    }

    public boolean SoloNumeros(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Buscar() throws ClassNotFoundException {
        String query = "";
        if (SoloNumeros(jTextField2.getText())) {
            query = "select distinct * from (\n"
                    + "select  distinct serie_producto \"Código\",upper(nombre)\"Nombre\",upper(categoria.descripcion) \"Categoría \",costo \"Costo\",maestro_iva.porcentaje \"IVA\",precio_desc \"Precio\",stock \"stock\",cantidad \"Cantidad\"\n"
                    + " from producto,categoria,maestro_iva where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  producto.iva=maestro_iva.codiva and \n"
                    + "  producto.serie_producto ILIKE ('%" + jTextField2.getText() + "')  and producto.estado='A'\n"
                    + "union all\n"
                    + "select  distinct serie_producto \"Código\",upper(nombre)\"Nombre\",upper(categoria.descripcion) \"Categoría \",costo \"Costo\",maestro_iva.porcentaje \"IVA\",precio_desc \"Precio\",stock \"stock\",cantidad \"Cantidad\"\n"
                    + " from producto,categoria,maestro_iva where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  producto.iva=maestro_iva.codiva and \n"
                    + "  producto.serie_producto ILIKE ('%" + jTextField2.getText() + "%')  and producto.estado='A')Y\n"
                    + "   limit 40";
        } else {
            query = "select  distinct "
                    + "serie_producto \"Código\","
                    + "upper(nombre)\"Nombre\","
                    + "upper(categoria.descripcion) \"Categoría \","
                    + "costo \"Costo\","
                    + "iva \"IVA\","
                    + "precio_desc \"Precio\","
                    + "stock \"stock\","
                    + "cantidad \"Cantidad\"\n"
                    + " from producto,categoria where "
                    + "  producto.cod_categoria=categoria.cod_categoria and "
                    + "   (categoria.descripcion ILIKE ('%" + jTextField2.getText() + "%') or  "
                    + "producto.nombre ILIKE ('%" + jTextField2.getText() + "%') or "
                    + " producto.serie_producto ILIKE ('%" + jTextField2.getText() + "%') )  and producto.estado='A'";
        }
        Control.conectar();
        Producto temp = null;
        String cod = "", nom = "", valor = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        boolean r2 = false;
        this.jTable1.setModel(modeloEmpleado);
        try {
            boolean r = Control.ejecuteQuery(query);

            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i < numeroPreguntas; i++) {
                modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }

            while (Control.rs.next()) {
                cod = Control.rs.getString(1);
                nom = Control.rs.getString(2);
                costo = Control.rs.getString(3);
                iva = Control.rs.getString(4);
                precio = Control.rs.getString(5);
                cant = Control.rs.getString(6);
                cate = Control.rs.getString(7);
                r2 = true;
                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
            Control.cerrarConexion();
//            Control.rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    public void inicio() throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "select "
                + "serie_producto \"Código\","
                + "upper(nombre) \"Nombre\","
                + "upper(categoria.descripcion) \"Categoría\","
                + "costo \"Costo\","
                + "maestro_iva.porcentaje \"IVA\","
                + "precio_desc \"Precio Venta\","
                + "stock \"stock\","
                + "cantidad \"Cantidad\""
                + "from producto,categoria,maestro_iva\n"
                + " where\n"
                + " producto.cod_categoria=categoria.cod_categoria"
                + " and producto.iva=maestro_iva.codiva\n"
                + " and  producto.estado='A'"
                + " order by producto.serie_producto DESC";

        String cod = "", nom = "", valor = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        this.jTable1.setModel(modeloEmpleado);
        try {
            boolean r = Control.ejecuteQuery(query);

            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i < numeroPreguntas; i++) {
                modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }

            while (Control.rs.next()) {
                cod = Control.rs.getString(1);
                nom = Control.rs.getString(2);
                costo = Control.rs.getString(3);
                iva = Control.rs.getString(4);
                precio = Control.rs.getString(5);
                cant = Control.rs.getString(6);
                cate = Control.rs.getString(7);

                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
            Control.cerrarConexion();
//            Control.rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    public JTable CargaInventario() throws ClassNotFoundException {
        Control.conectar();
        JTable table = new javax.swing.JTable();
        Producto temp = null;
        String query = "select "
                + "serie_producto \"Código\","
                + "upper(nombre) \"Nombre\","
                + "categoria.cod_categoria \"Categoría\","
                + "costo \"Costo\","
                + "maestro_iva.porcentaje \"IVA\","
                + "precio_desc \"Precio Venta\","
                + "stock \"stock\","
                + "cantidad \"Cantidad\""
                + "from producto,categoria,maestro_iva\n"
                + " where\n"
                + " producto.cod_categoria=categoria.cod_categoria"
                + " and producto.iva=maestro_iva.codiva\n"
                + " and  producto.estado='A'"
                + " order by cantidad ";
        System.out.println(query);
        String cod = "", nom = "", valor = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        table.setModel(modeloEmpleado);
        try {
            boolean r = Control.ejecuteQuery(query);

            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i < numeroPreguntas; i++) {
                modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }

            while (Control.rs.next()) {
                cod = Control.rs.getString(1);
                nom = Control.rs.getString(2);
                costo = Control.rs.getString(3);
                iva = Control.rs.getString(4);
                precio = Control.rs.getString(5);
                cant = Control.rs.getString(6);
                cate = Control.rs.getString(7);

                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
            Control.cerrarConexion();
//            Control.rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
        return table;
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton ayuda;
    private javax.swing.JButton bajarExcel;
    private javax.swing.JButton cargarExcel;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JMenuItem salir;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();

        while (ct == Hilo1) {
            jProgressBar1.setVisible(true);
            cargarFile();
            Hilo1.stop();
            //corredor1();            
        }

    }
}
