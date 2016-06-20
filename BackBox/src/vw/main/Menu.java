/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.main;

import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import vw.model.Articulo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import vw.components.Bodega;
import vw.components.CategoriaGestion;
import vw.components.Entrada_Nueva;
import vw.components.Producto;
import vw.components.Provedores;
import vw.components.Reporte_Entradas;
import vw.components.Reporte_Ventas;
import vw.components.Roles;
import vw.components.Usuarios;
import vw.components.VentaDiaria;
import vw.dialogs.AcercaDe;
import vw.dialogs.CategoriasRegistrar;
import vw.dialogs.ProveedoresRegistrar;
import vw.dialogs.RolRegistrar;
import vw.dialogs.UsuariosRegistrar;
import vw.model.Venta;

/**
 *
 * @author Microinformatica
 */
public class Menu extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form Bodega
     */
    String usuario;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();
    int VerBodega;
    int VerArticulos;
    int VerVenta;
    int VerUsuario;

    public Menu(String usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        addKeyListener(this);
        VerBodega = 0;
        VerArticulos = 0;
        VerVenta = 0;
        VerUsuario = 0;
        List_Menu.clear();
        try {
            cargarUsuario(usuario);
            cargarSecciones();
            cargarAccion();
            ContenedorMenus con_menu = new ContenedorMenus();
            con_menu.setListaAcciones(listaaccion);
            con_menu.setListaSeccion(listaSeccion);
            List_Menu.add(con_menu);
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
                                    MenuRedireccionar MenuF = new MenuRedireccionar(Menu.this, e.getActionCommand().toString(), List_Menu, usuario);
                                    try {
                                        MenuF.reDireccion();
                                        if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                            System.out.println("No cierra Ventana");
                                        } else {
                                            Menu.this.dispose();
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (URISyntaxException ex) {
                                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
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
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menu.add(menuItem);
            }
        }
    }

    public void cargarSecciones() throws SQLException {
        try {
            Control.conectar();
            listaSeccion.clear();
            Control.ejecuteQuery("select distinct codigoact,nombre,posicion from rol,detalleactividad,usuario,actividades where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                actividades.codigoact=detalleactividad.cod_actividad and "
                    + "                usuario.cod_rol=rol.cod_rol  \n"
                    + "                and usuario.cod_usuario=" + usuario + " order by posicion");

            while (Control.rs.next()) {
                listaSeccion.add(new seccion(Control.rs.getInt(1), Control.rs.getString(2)));
            }
            System.out.println("seccion : " + listaSeccion.size());
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            Control.cerrarConexion();
        }
    }

    public void cargarAccion() throws SQLException {
        try {
            Control.conectar();
            listaaccion.clear();
            Control.ejecuteQuery("select detalleactividad.cod_actividad,acciones.nom_accion,detalleactividad.cod_detalleac from rol,detalleactividad,usuario,acciones where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                 acciones.codacciones=detalleactividad.cod_accion and "
                    + "                usuario.cod_rol=rol.cod_rol\n"
                    + "                and usuario.cod_usuario=" + usuario + " order by cod_detalleac");
            while (Control.rs.next()) {
                listaaccion.add(new acciones(Control.rs.getInt(1), Control.rs.getString(2)));
            }
            System.out.println("lista Acciones : " + listaaccion);
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarUsuario(String cod) throws SQLException, ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select nombre,apellido from usuario,persona where \n"
                + "usuario.cedula=persona.cedula and  cod_usuario=" + cod);
        String nombre = "";
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
        }
        this.feld01.setText("Bienvenido " + nombre);
        Control.cerrarConexion();

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
        C_bodega = new javax.swing.JButton();
        C_Articulo = new javax.swing.JButton();
        C_venta = new javax.swing.JButton();
        C_usuario = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        feld01 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - BackBox ");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        C_bodega.setBackground(new java.awt.Color(255, 255, 255));
        C_bodega.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        C_bodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_archive_gray_24dp.png"))); // NOI18N
        C_bodega.setText("Ver Bodega");
        C_bodega.setBorder(null);
        C_bodega.setBorderPainted(false);
        C_bodega.setContentAreaFilled(false);
        C_bodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        C_bodega.setDefaultCapable(false);
        C_bodega.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        C_bodega.setPreferredSize(new java.awt.Dimension(99, 117));
        C_bodega.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_archive_black_24dp.png"))); // NOI18N
        C_bodega.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C_bodega.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C_bodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_bodegaActionPerformed(evt);
            }
        });
        jPanel1.add(C_bodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 110, -1));

        C_Articulo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        C_Articulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_card_giftcard_gray_24dp.png"))); // NOI18N
        C_Articulo.setText("Ver Artículos");
        C_Articulo.setBorder(null);
        C_Articulo.setBorderPainted(false);
        C_Articulo.setContentAreaFilled(false);
        C_Articulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        C_Articulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        C_Articulo.setPreferredSize(new java.awt.Dimension(99, 117));
        C_Articulo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_card_giftcard_black_24dp.png"))); // NOI18N
        C_Articulo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C_Articulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_ArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(C_Articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, -1));

        C_venta.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        C_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_shopping_cart_gray_24dp.png"))); // NOI18N
        C_venta.setText("Realizar Venta");
        C_venta.setBorder(null);
        C_venta.setBorderPainted(false);
        C_venta.setContentAreaFilled(false);
        C_venta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        C_venta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        C_venta.setPreferredSize(new java.awt.Dimension(99, 117));
        C_venta.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_shopping_cart_black_24dp.png"))); // NOI18N
        C_venta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C_venta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_ventaActionPerformed(evt);
            }
        });
        jPanel1.add(C_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, -1, -1));

        C_usuario.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        C_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_account_box_gray_24dp.png"))); // NOI18N
        C_usuario.setText("Control de Usuarios");
        C_usuario.setBorder(null);
        C_usuario.setBorderPainted(false);
        C_usuario.setContentAreaFilled(false);
        C_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        C_usuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        C_usuario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_account_box_black_24dp.png"))); // NOI18N
        C_usuario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C_usuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_usuarioActionPerformed(evt);
            }
        });
        jPanel1.add(C_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, -1, -1));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/img001.png"))); // NOI18N

        feld01.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        feld01.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(feld01)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(feld01)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 540));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void C_bodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_bodegaActionPerformed
        try {

            new Bodega(usuario, List_Menu).setVisible(true);
            this.dispose();

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_C_bodegaActionPerformed

    private void C_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_ArticuloActionPerformed
        try {
            new Articulo(usuario, List_Menu).setVisible(true);
            this.dispose();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_C_ArticuloActionPerformed

    private void C_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_ventaActionPerformed
        try {
            ArrayList<Producto> productos = new ArrayList();
            new Venta(productos, usuario, 1, List_Menu, "1").setVisible(true);
            this.dispose();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_C_ventaActionPerformed

    private void C_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_usuarioActionPerformed
        try {
            new Usuarios(usuario, List_Menu).setVisible(true);
            this.dispose();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_C_usuarioActionPerformed

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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C_Articulo;
    private javax.swing.JButton C_bodega;
    private javax.swing.JButton C_usuario;
    private javax.swing.JButton C_venta;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JLabel feld01;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(":: " + e.getKeyChar());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("::-- " + e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("--.- " + e.getKeyChar());
    }

}
