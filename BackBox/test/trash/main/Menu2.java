/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trash.main;

import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
import vw.main.Acceder;
import vw.main.Menu;

/**
 *
 * @author Microinformatica
 */
public class Menu2 extends javax.swing.JFrame implements Runnable {

    Thread Hilo1;
    String usuario;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();
    int VerBodega;
    int VerArticulos;
    int VerVenta;
    int VerUsuario;
    int codigoEmpresa;

    /**
     * Creates new form Bodega
     */
    public Menu2(String usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        VerBodega = 0;
        VerArticulos = 0;
        VerVenta = 0;
        VerUsuario = 0;
        this.codigoEmpresa = 0;
        List_Menu.clear();
        try {
            cargarUsuario(usuario);
            System.out.println("1");
            cargarSecciones();
            System.out.println("2");
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
                                    MenuRedireccionar MenuF = new MenuRedireccionar(Menu2.this, e.getActionCommand().toString(), List_Menu, usuario, codigoEmpresa);
                                    try {
                                        MenuF.reDireccion();
                                        if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                                || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                            System.out.println("No cierra Ventana");
                                        } else {
                                            Menu2.this.dispose();
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
            System.out.println("3");
            MenuAyuda();
            EliminarBandera();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void MenuAyuda() {
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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario, codigoEmpresa);
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

    private void cargarSecciones() throws SQLException {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
        }
    }

    private void cargarAccion() throws SQLException {
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

    private void cargarUsuario(String cod) throws SQLException, ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select nombre,apellido,codempresa from usuario,persona where \n"
                + "usuario.cedula=persona.cedula and  cod_usuario=" + cod);
        String nombre = "";
        int empresa = 0;
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
            empresa = Control.rs.getInt(3);
        }
        this.codigoEmpresa = empresa;
        this.feld01.setText("Bienvenido " + nombre);
        Control.cerrarConexion();

    }

    private void EliminarBandera() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteUpdate("delete from detalle a where a.cod_producto in (select cod_producto from producto where cod_producto=a.cod_producto and bandera=0)");
        Control.ejecuteUpdate("delete from producto where bandera=0");
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
        bntAnalitics = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        feld01 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
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

        bntAnalitics.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        bntAnalitics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/sense_24dp.png"))); // NOI18N
        bntAnalitics.setText("BackBox - Analitics");
        bntAnalitics.setBorder(null);
        bntAnalitics.setBorderPainted(false);
        bntAnalitics.setContentAreaFilled(false);
        bntAnalitics.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntAnalitics.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntAnalitics.setPreferredSize(new java.awt.Dimension(99, 117));
        bntAnalitics.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bntAnalitics.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntAnalitics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnaliticsActionPerformed(evt);
            }
        });
        jPanel1.add(bntAnalitics, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, 120, 120));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/img001.png"))); // NOI18N

        feld01.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        feld01.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(feld01)
                .addContainerGap(294, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tip: Presiona (CTRL + V). Para hacer una venta");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 400, 30));

        btnSalir.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_archive_black_24dp.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(null);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(99, 117));
        btnSalir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxxhdpi/ic_archive_gray_24dp.png"))); // NOI18N
        btnSalir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 120, 120));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntAnaliticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAnaliticsActionPerformed

    }//GEN-LAST:event_bntAnaliticsActionPerformed

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

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarSesionActionPerformed(evt);
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAnalitics;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JLabel feld01;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();

        while (ct == Hilo1) {
            System.out.println("---");

        }
    }
}
