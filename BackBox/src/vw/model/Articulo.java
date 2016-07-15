/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.model;

import Control.LeerExcel;
import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.exportar_excel;
import Modelo.seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import vw.components.CargaArchivo;
import vw.components.Datalles;
import Control.Entrada;
import vw.components.Entrada_Nueva;
import vw.main.Acceder;
import vw.main.Menu;
import Modelo.Producto;
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

/**
 *
 * @author Britany
 */
public class Articulo extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    String usuario;
    int codEmpresa;
    int regimen;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();
    ArrayList<Integer> listIvas = new ArrayList();
    ArrayList<Integer> listCategorias = new ArrayList();

    public Articulo(String nom, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        initComponents();
        inicio();
//        jButton6.setVisible(false);
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
            Control.ejecuteQuery("select codiva from maestro_iva");
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
            jButton1.setEnabled(false);
            jButton6.setEnabled(false);
            jButton5.setEnabled(false);
            String acci = "";
            for (String accione : acciones) {
                acci = (String) accione;
                if (acci.equalsIgnoreCase("NuevaCompra")) {
                    jButton1.setEnabled(true);
                } else if (acci.equalsIgnoreCase("ArtiuloCarga")) {
                    jButton6.setEnabled(true);
                } else if (acci.equalsIgnoreCase("ArticuloReporte")) {
                    jButton5.setEnabled(true);
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

        jButton3 = new javax.swing.JButton();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        jButton1.setText("Agregar");
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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton4.setToolTipText("Volver");
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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/export_to_excel_24dp.png"))); // NOI18N
        jButton5.setToolTipText("Exportar a Excel");
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

        jButton6.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_attach_file_black_24dp.png"))); // NOI18N
        jButton6.setText("Cargar");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTable1.getRowCount() >= 1) {
            getBto_exportar();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Datos Para Exportar");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        new Menu(usuario).setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton4ActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Entradas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
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
    private void getBto_exportar() {
        try {
            List<JTable> tb = new ArrayList<JTable>();
            tb.add(jTable1);
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

    public void pasar_datos(String ruta) throws ClassNotFoundException {
        System.out.println("Entro");
        String movimiento[] = null;
        String costo[] = null;
        String iva[] = null;
        String precio[] = null;
        String categoria[] = null;
        String cantidad[] = null;        
        String nombres[] = null;
        LeerExcel Hoja = new LeerExcel();
        Hoja.f_datos_1(ruta, 0);//codigo
        movimiento = Hoja.Carga();
        Hoja.f_datos_1(ruta, 1);//Nombre
        nombres = Hoja.Carga();
        Hoja.f_datos_1(ruta, 2);//costo
        costo = Hoja.Carga();
        Hoja.f_datos_1(ruta, 3);//iva
        iva = Hoja.Carga();
        Hoja.f_datos_1(ruta, 4);//precio
        precio = Hoja.Carga();
        Hoja.f_datos_1(ruta, 5);//categoria
        categoria = Hoja.Carga();
        Hoja.f_datos_1(ruta, 6);//cantidad
        cantidad = Hoja.Carga();
        ArrayList datos = null;
        if (ValidarDatosEnTabla(costo, 1)) {
            if (ValidarDatosEnTabla(iva, 2)) {
                if (ValidarDatosEnTabla(precio, 3)) {
                    if (ValidarDatosEnTabla(categoria, 4)) {
                        if (ValidarDatosEnTabla(cantidad, 5)) {
                            System.out.println("Paso todos los filtros");
                            datos = DatosPersona(movimiento, nombres, costo, iva, precio, categoria, cantidad);
                            System.out.println("vamos aqui");
                            System.out.println("Tamaño arreglo : " + datos.size());
                            CargaArchivo car = new CargaArchivo(datos, usuario, List_Menu);
                            this.dispose();
                            car.setVisible(true);
                        }
                    }
                }
            }
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

            System.out.println("Valor  : " + x+ " boolean : " + r);
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
        for (Object object : o) {
            valor = (String) object;
            System.out.println("::: " + valor);
            if (condi == 1 && isnumero(valor, 2) == false) {
                mnserror = "El valor del costo debe ser Numerico";
                r = false;
                break;
            } else if (condi == 1 && isnumero(valor, 2)) {
                System.out.println("entro por aqi");
                if (Double.parseDouble(valor) <= 0) {
                    mnserror = "El valor del costo debe ser mayor a 0 (Cero))";
                    r = false;
                    break;
                }
            } else if (condi == 2 && isnumero(valor, 2) == false) {
                mnserror = "El valor del iva debe ser Numerico";
                r = false;
                break;
            } else if (condi == 2 && isnumero(valor, 2)) {
                for (int Valoriva : listIvas) {
                    if (Double.parseDouble(valor) == Valoriva) {
                        iva = true;
                    }
                }
                if (iva != false) {
                    mnserror = "El valor del iva debe estar dentro de los parametros del iva permitido";
                    r = false;
                    break;
                }
            } else if (condi == 3 && isnumero(valor, 2) == false) {
                mnserror = "El valor del Precio debe ser Numerico";
                r = false;
                break;
            } else if (condi == 3 && isnumero(valor, 2)) {
                if (Double.parseDouble(valor) <= 0) {
                    mnserror = "El valor del Precio debe ser Numerico";
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
                if (Integer.parseInt(valor) <= 0) {
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
        String codi = "";
        ArrayList<Producto> pro = new ArrayList();

        for (int i = 0; i < cod.length; i++) {            
            Producto p = new Producto("" + codi, nom[i],Double.parseDouble(cos[i]),Integer.parseInt(iva[i]),
                    Double.parseDouble(precio[i]), Integer.parseInt(cant[i]), 0);            
            p.setCategoria(Integer.parseInt(cat[i]));
            System.out.println("-"+p.toString());
            pro.add(p);
        }
        return pro;
    }
    public boolean BuscarEstado(String cod) throws ClassNotFoundException {
        Control.conectar();
        boolean r = false;

        try {
            Control.ejecuteQuery("select * from producto where estado='A' "
                    + "and cod_producto='" + cod + "'");
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
            query = "select  distinct "
                    + "cod_producto \"Código\","
                    + "upper(nombre)\"Nombre\","
                    + "upper(categoria.descripcion) \"Categoría \","
                    + "costo \"Costo\","
                    + "iva \"IVA\","
                    + "precio_desc \"Precio\","
                    + "descu \"Descuento\","
                    + "cantidad \"Cantidad\"\n"
                    + " from producto,categoria where\n"
                    + "  producto.cod_categoria=categoria.cod_categoria and \n"
                    + "  \n"
                    + "  producto.cod_producto ILIKE ('%" + jTextField2.getText() + "')  and producto.estado='A'"
                    + " limit 40 ";
        } else {
            query = "select  distinct "
                    + "cod_producto \"Código\","
                    + "upper(nombre)\"Nombre\","
                    + "upper(categoria.descripcion) \"Categoría \","
                    + "costo \"Costo\","
                    + "iva \"IVA\","
                    + "precio_desc \"Precio\","
                    + "descu \"Descuento\","
                    + "cantidad \"Cantidad\"\n"
                    + " from producto,categoria where "
                    + "  producto.cod_categoria=categoria.cod_categoria and "
                    + "   (categoria.descripcion ILIKE ('%" + jTextField2.getText() + "%') or  "
                    + "producto.nombre ILIKE ('%" + jTextField2.getText() + "%') or "
                    + " producto.cod_producto ILIKE ('%" + jTextField2.getText() + "%') )  and producto.estado='A'";
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
                + "cod_producto \"Código\","
                + "upper(nombre) \"Nombre\","
                + "upper(categoria.descripcion) \"Categoría\","
                + "costo \"Costo\","
                + "iva \"IVA\","
                + "precio_desc \"Precio Venta\","
                + "descu \"Descuento\","
                + "cantidad \"Cantidad\""
                + "from producto,categoria\n"
                + "where\n"
                + "producto.cod_categoria=categoria.cod_categoria\n"
                + "and  producto.estado='A'"
                + " order by producto.cod_producto DESC";
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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
