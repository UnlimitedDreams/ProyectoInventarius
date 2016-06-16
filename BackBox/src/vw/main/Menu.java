/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.main;

import Control.Control;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import vw.dialogs.AcercaDe;
import vw.dialogs.CategoriasRegistrar;
import vw.dialogs.ProveedoresRegistrar;
import vw.dialogs.RolRegistrar;
import vw.dialogs.UsuariosRegistrar;

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

        try {
            cargarUsuario(usuario);
            cargarSecciones();
            System.out.println(":: " + listaSeccion.size());
            cargarAccion();
            System.out.println(":: " + listaaccion.size());
            for (seccion object : listaSeccion) {
                JMenu menu = new JMenu(object.getDescripcion());
                jMenuBar1.add(menu);
                for (acciones object1 : listaaccion) {
                    if (object1.getCod_seccion() == object.getCod_seccion()) {
                        if (object1.getAccion().equalsIgnoreCase("Linea")) {
                            menu.add(new JSeparator());
                        } else {
                            JMenuItem menuItem = new JMenuItem(object1.getAccion());
                            if(object1.getAccion().equalsIgnoreCase("Lista Bodega")){
                                menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, ActionEvent.ALT_MASK));
                            }
                            
                            menuItem.addActionListener((ActionEvent e) -> {
                                MenuRedireccionar MenuF = new MenuRedireccionar(e.getActionCommand().toString());
                                this.dispose();
                                MenuF.reDireccion();
                            });
                            menu.add(menuItem);

                        }

                    }
                }

//            jMenuBar2.add(new JMenu(object.getDescripcion()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MenuDinamico() throws ClassNotFoundException, SQLException {

//        VerVenta = 0;
//        VerUsuario = 0;
//        //Bloque de Acciones
//        stok.setVisible(false);
//        bill.setVisible(false);
//        //Menu Principal
//        C_bodega.setVisible(false);
//        C_usuario.setVisible(false);
//        C_Articulo.setVisible(false);
//        C_venta.setVisible(false);
//        //
//        jMenuItem6.setVisible(false);
//        jMenuItem7.setVisible(false);
//        jMenuItem8.setVisible(false);
//        jMenuItem9.setVisible(false);
//        jMenuItem10.setVisible(false);
//        jMenuItem7.setVisible(false);
//        jMenuItem8.setVisible(false);
//        jMenuItem9.setVisible(false);
//        jMenuItem10.setVisible(false);
//        jMenuItem2.setVisible(false);
//        jMenuItem1.setVisible(false);
//        jMenuItem1.setVisible(false);
//        listaProveerdores.setVisible(false);
//        crearProveedor.setVisible(false);
//        jMenu5.setVisible(false);
//        user.setVisible(false);
//        jMenuItem3.setVisible(false);
//        crearUsuario.setVisible(false);
//        listaRoles.setVisible(false);
//        crearRol.setVisible(false);
//        for (Integer ListAccione : ListAcciones) {
//            System.err.println("--- : " + ListAccione);
//            if (ListAccione == 1) {//Bodega
//                C_bodega.setVisible(true);
//                stok.setVisible(true);
//                jMenuItem6.setVisible(true);
//            } else if (ListAccione == 6) {//Articulos
//                C_Articulo.setVisible(true);
//                stok.setVisible(true);
//                jMenuItem7.setVisible(true);
//                jMenuItem8.setVisible(true);
//            } else if (ListAccione == 9) {
//                stok.setVisible(true);
//                jMenuItem9.setVisible(true);
//                jMenuItem10.setVisible(true);
//            } else if (ListAccione == 13) {//Venta
//                C_venta.setVisible(true);
//                bill.setVisible(true);
//                jMenuItem2.setVisible(true);
//                jMenuItem1.setVisible(true);
//                jMenuItem1.setVisible(true);
//            } else if (ListAccione == 14) {
//                bill.setVisible(true);
//                jMenu5.setVisible(true);
//            } else if (ListAccione == 14) {
//                bill.setVisible(true);
//                jMenu5.setVisible(true);
//            } else if (ListAccione == 18) {//Usuarios
//                C_usuario.setVisible(true);
//                user.setVisible(true);
//                jMenuItem3.setVisible(true);
//                crearUsuario.setVisible(true);
//                listaRoles.setVisible(true);
//                crearRol.setVisible(true);
//            } else if (ListAccione == 22) {
//                user.setVisible(true);
//                listaProveerdores.setVisible(true);
//                crearProveedor.setVisible(true);
//            }
//        }
    }

    public void cargarSecciones() throws SQLException {
        try {
            Control.conectar();
            System.out.println("select codigoact,nombre from rol,detalleactividad,usuario,actividades where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                 actividades.codigoact=detalleactividad.cod_actividad and "
                    + "                usuario.cod_rol=rol.cod_rol\n"
                    + "                and usuario.cod_usuario=" + usuario);
            System.out.println("-----------");
            Control.ejecuteQuery("select distinct codigoact,nombre from rol,detalleactividad,usuario,actividades where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                 actividades.codigoact=detalleactividad.cod_actividad and "
                    + "                usuario.cod_rol=rol.cod_rol\n"
                    + "                and usuario.cod_usuario=" + usuario);

            while (Control.rs.next()) {
                listaSeccion.add(new seccion(Control.rs.getInt(1), Control.rs.getString(2)));
            }
            System.out.println("seccion : " + listaSeccion.size());
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarAccion() throws SQLException {
        try {
            Control.conectar();
            System.out.println("select detalleactividad.cod_actividad,acciones.nom_accion from rol,detalleactividad,usuario,acciones where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                 acciones.codacciones=detalleactividad.cod_accion and "
                    + "                usuario.cod_rol=rol.cod_rol\n"
                    + "                and usuario.cod_usuario=" + usuario);
            Control.ejecuteQuery("select detalleactividad.cod_actividad,acciones.nom_accion from rol,detalleactividad,usuario,acciones where \n"
                    + "                rol.cod_rol=detalleactividad.cod_rol and \n"
                    + "                 acciones.codacciones=detalleactividad.cod_accion and "
                    + "                usuario.cod_rol=rol.cod_rol\n"
                    + "                and usuario.cod_usuario=" + usuario);
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
        user = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        crearUsuario = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        listaProveerdores = new javax.swing.JMenuItem();
        crearProveedor = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        listaRoles = new javax.swing.JMenuItem();
        crearRol = new javax.swing.JMenuItem();
        stok = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        bill = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        realizarVenta = new javax.swing.JMenuItem();
        realizarDevolucion = new javax.swing.JMenuItem();
        verVentaDiaria = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        reporteVentas = new javax.swing.JMenuItem();
        ReporteCompras = new javax.swing.JMenuItem();
        reporteEYS = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        ayudaLinea = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        paginaWeb = new javax.swing.JMenuItem();
        atajos = new javax.swing.JMenuItem();
        aboutUs = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - BackBox ");
        setPreferredSize(new java.awt.Dimension(770, 590));
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

        user.setText("Gestionar Cuentas");
        user.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem3.setText("Lista Usuarios");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        user.add(jMenuItem3);

        crearUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        crearUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        crearUsuario.setText("Crear Usuario");
        crearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearUsuarioActionPerformed(evt);
            }
        });
        user.add(crearUsuario);
        user.add(jSeparator2);

        listaProveerdores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        listaProveerdores.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        listaProveerdores.setText("Lista Proveedores");
        listaProveerdores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaProveerdoresActionPerformed(evt);
            }
        });
        user.add(listaProveerdores);

        crearProveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        crearProveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        crearProveedor.setText("Crear Proveedor");
        crearProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProveedorActionPerformed(evt);
            }
        });
        user.add(crearProveedor);
        user.add(jSeparator5);

        listaRoles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        listaRoles.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        listaRoles.setText("Lista Roles");
        listaRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaRolesActionPerformed(evt);
            }
        });
        user.add(listaRoles);

        crearRol.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        crearRol.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        crearRol.setText("Crear Rol");
        crearRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearRolActionPerformed(evt);
            }
        });
        user.add(crearRol);

        jMenuBar1.add(user);

        stok.setText("Bodega");
        stok.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem6.setText("Lista Bodega");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        stok.add(jMenuItem6);
        stok.add(jSeparator3);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem7.setText("Ver Artículos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        stok.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem8.setText("Nuevo artículo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        stok.add(jMenuItem8);
        stok.add(jSeparator4);

        jMenuItem9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem9.setText("Ver Categorías");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        stok.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem10.setText("Crear Categoría");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        stok.add(jMenuItem10);

        jMenuBar1.add(stok);

        bill.setText("Transacciones");
        bill.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem2.setText("Entradas y Salidas");
        bill.add(jMenuItem2);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jMenuItem1.setText("Realizar Compra");
        bill.add(jMenuItem1);

        jMenu6.setText("Ventas y Devoluciones");
        jMenu6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        realizarVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        realizarVenta.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        realizarVenta.setText("Realizar Venta");
        realizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarVentaActionPerformed(evt);
            }
        });
        jMenu6.add(realizarVenta);

        realizarDevolucion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        realizarDevolucion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        realizarDevolucion.setText("Realizar Devolución");
        realizarDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarDevolucionActionPerformed(evt);
            }
        });
        jMenu6.add(realizarDevolucion);

        verVentaDiaria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        verVentaDiaria.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        verVentaDiaria.setText("Ver Ventas del Día");
        verVentaDiaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verVentaDiariaActionPerformed(evt);
            }
        });
        jMenu6.add(verVentaDiaria);

        bill.add(jMenu6);
        bill.add(jSeparator7);

        jMenu5.setText("Reportes");
        jMenu5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        reporteVentas.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        reporteVentas.setText("Ventas");
        reporteVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteVentasActionPerformed(evt);
            }
        });
        jMenu5.add(reporteVentas);

        ReporteCompras.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        ReporteCompras.setText("Compras");
        ReporteCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteComprasActionPerformed(evt);
            }
        });
        jMenu5.add(ReporteCompras);

        reporteEYS.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        reporteEYS.setText("Entradas y Salidas");
        reporteEYS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteEYSActionPerformed(evt);
            }
        });
        jMenu5.add(reporteEYS);

        bill.add(jMenu5);

        jMenuBar1.add(bill);

        help.setText("Ayuda");
        help.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        ayudaLinea.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        ayudaLinea.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        ayudaLinea.setText(" Ayuda en línea");
        ayudaLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaLineaActionPerformed(evt);
            }
        });
        help.add(ayudaLinea);
        help.add(jSeparator6);

        paginaWeb.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        paginaWeb.setText("BackBox");
        paginaWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paginaWebActionPerformed(evt);
            }
        });
        help.add(paginaWeb);

        atajos.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        atajos.setText("Atajos");
        atajos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atajosActionPerformed(evt);
            }
        });
        help.add(atajos);

        aboutUs.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        aboutUs.setText("Acerca de...");
        aboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutUsActionPerformed(evt);
            }
        });
        help.add(aboutUs);

        jMenuBar1.add(help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void C_bodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_bodegaActionPerformed
//        Bodega b;
//        try {
//
//            b = new Bodega(usuario, ListAcciones);
//            this.setVisible(false);
//            b.setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_C_bodegaActionPerformed

    private void C_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_ArticuloActionPerformed
//        try {
//            new Articulo(usuario, ListAcciones).setVisible(true);
//            this.dispose();
//        } catch (Exception ex) {
//
//        }
    }//GEN-LAST:event_C_ArticuloActionPerformed

    private void C_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_ventaActionPerformed
//        ArrayList<Producto> productos = new ArrayList();
//        try {
//            new Venta(productos, usuario, 1, ListAcciones).setVisible(true);;
//            this.setVisible(false);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_C_ventaActionPerformed

    private void C_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_usuarioActionPerformed
//        try {
//            new Usuarios(usuario, ListAcciones).setVisible(true);;
//            this.dispose();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_C_usuarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        try {
//            new Usuarios(usuario, ListAcciones).setVisible(true);;
//            this.dispose();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void crearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearUsuarioActionPerformed
        new UsuariosRegistrar(this, true).setVisible(true);
    }//GEN-LAST:event_crearUsuarioActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed

        this.dispose();
        new Acceder().setVisible(true);

    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void listaProveerdoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaProveerdoresActionPerformed
//        try {
//            new Provedores(usuario, ListAcciones).setVisible(true);;
//            this.dispose();;
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_listaProveerdoresActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
//        try {
//            // TODO add your handling code here:
//            Bodega b = new Bodega(usuario, ListAcciones);
//            this.dispose();
//            b.setVisible(true);
//        } catch (Exception ex) {
//
//        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
//        Articulo ar;
//        try {
//            ar = new Articulo(usuario, ListAcciones);
//            this.dispose();
//            ar.setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
//        Entrada_Nueva rp;
//        try {
//            ArrayList<Producto> pr = new ArrayList();
//            String fac = "";
//            rp = new Entrada_Nueva(pr, usuario, fac, ListAcciones);
//            rp.setVisible(true);
//            this.dispose();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
//        try {
//            new CategoriaGestion(usuario, ListAcciones).setVisible(true);
//            this.dispose();
//        } catch (Exception x) {
//            /*Nothig Here*/
//        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        new CategoriasRegistrar(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void reporteVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteVentasActionPerformed
//        try {
//            new Reporte_Ventas(usuario, ListAcciones).setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.dispose();

    }//GEN-LAST:event_reporteVentasActionPerformed

    private void realizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarVentaActionPerformed
//        ArrayList<Producto> productos = new ArrayList();
//        try {
//            new Venta(productos, usuario, 1, ListAcciones).setVisible(true);;
//            this.setVisible(false);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_realizarVentaActionPerformed

    private void aboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutUsActionPerformed
        new AcercaDe(this, true).setVisible(true);
    }//GEN-LAST:event_aboutUsActionPerformed

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicioActionPerformed

    private void paginaWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paginaWebActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_paginaWebActionPerformed

    private void ayudaLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaLineaActionPerformed
//        if (Desktop.isDesktopSupported()) {
//            try {
//                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
//            } catch (URISyntaxException | IOException ex) {
//                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_ayudaLineaActionPerformed

    private void atajosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atajosActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_atajosActionPerformed

    private void reporteEYSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteEYSActionPerformed
        /*Esperando metodo*/
    }//GEN-LAST:event_reporteEYSActionPerformed

    private void ReporteComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteComprasActionPerformed
//        try {
//            new Reporte_Entradas(usuario, ListAcciones).setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.dispose();
    }//GEN-LAST:event_ReporteComprasActionPerformed

    private void verVentaDiariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verVentaDiariaActionPerformed

//        try {
//            new VentaDiaria(usuario, ListAcciones).setVisible(true);;
//            this.dispose();
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_verVentaDiariaActionPerformed

    private void realizarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarDevolucionActionPerformed
//        ArrayList<Producto> pro = new ArrayList();
//        try {
//            new Venta(pro, usuario, 2, ListAcciones).setVisible(true);;
//            this.dispose();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_realizarDevolucionActionPerformed

    private void crearProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProveedorActionPerformed
        new ProveedoresRegistrar(this, true).setVisible(true);
    }//GEN-LAST:event_crearProveedorActionPerformed

    private void listaRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaRolesActionPerformed
//        try {
//            new Roles(usuario, ListAcciones).setVisible(true);;
//            this.dispose();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_listaRolesActionPerformed

    private void crearRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearRolActionPerformed
        new RolRegistrar(this, true).setVisible(true);
    }//GEN-LAST:event_crearRolActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C_Articulo;
    private javax.swing.JButton C_bodega;
    private javax.swing.JButton C_usuario;
    private javax.swing.JButton C_venta;
    private javax.swing.JMenuItem ReporteCompras;
    private javax.swing.JMenuItem aboutUs;
    private javax.swing.JMenuItem atajos;
    private javax.swing.JMenuItem ayudaLinea;
    private javax.swing.JMenu bill;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenuItem crearProveedor;
    private javax.swing.JMenuItem crearRol;
    private javax.swing.JMenuItem crearUsuario;
    private javax.swing.JLabel feld01;
    private javax.swing.JMenu file;
    private javax.swing.JMenu help;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JMenuItem listaProveerdores;
    private javax.swing.JMenuItem listaRoles;
    private javax.swing.JMenuItem paginaWeb;
    private javax.swing.JMenuItem realizarDevolucion;
    private javax.swing.JMenuItem realizarVenta;
    private javax.swing.JMenuItem reporteEYS;
    private javax.swing.JMenuItem reporteVentas;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenu stok;
    private javax.swing.JMenu user;
    private javax.swing.JMenuItem verVentaDiaria;
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
