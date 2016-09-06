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
import com.backbox.util.VerticalLabelUI;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

/**
 *
 * @author Miguel Lemoz
 */
public class Menu extends javax.swing.JFrame {

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
     * Creates new form temp
     *
     * @param usuario
     */
    public Menu(String usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
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
                            JMenuItem menuItem = new JMenuItem();
                            menuItem = MenuRedireccionar.Atajos(object1.getAccion());
                            menuItem.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    MenuRedireccionar MenuF = new MenuRedireccionar(Menu.this, e.getActionCommand().toString(), List_Menu, usuario, codigoEmpresa);
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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand(), List_Menu, usuario, codigoEmpresa);
                    try {
                        MenuF.reDireccion();
                    } catch (IOException | URISyntaxException | ClassNotFoundException ex) {
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
                    + "                acciones.codacciones=detalleactividad.cod_accion and "
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
        this.feld01.setUI(new VerticalLabelUI(true));
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

        mainFrame = new javax.swing.JPanel();
        centro = new javax.swing.JPanel();
        superior = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inferior = new javax.swing.JPanel();
        cerrar = new javax.swing.JLabel();
        sense = new javax.swing.JLabel();
        izquierda = new javax.swing.JPanel();
        derecha = new javax.swing.JPanel();
        feld01 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú - BackBox");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(800, 600));

        mainFrame.setMinimumSize(new java.awt.Dimension(800, 400));
        mainFrame.setPreferredSize(new java.awt.Dimension(800, 400));
        mainFrame.setLayout(new java.awt.BorderLayout());

        centro.setBackground(java.awt.Color.white);
        centro.setLayout(new java.awt.BorderLayout());
        mainFrame.add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        superior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tip: Presiona (CTRL + V). Para hacer una venta");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        superior.add(jLabel3);

        mainFrame.add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(new java.awt.Color(196, 70, 38));
        inferior.setMinimumSize(new java.awt.Dimension(800, 100));
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 25, 7));

        cerrar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxhdpi/ic_archive_black_24dp.png"))); // NOI18N
        cerrar.setText("Salir");
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });
        inferior.add(cerrar);

        sense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xxhdpi/sense_24dp.png"))); // NOI18N
        sense.setText("Analitics");
        sense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sense.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sense.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inferior.add(sense);

        mainFrame.add(inferior, java.awt.BorderLayout.PAGE_END);

        izquierda.setBackground(java.awt.Color.white);
        izquierda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        mainFrame.add(izquierda, java.awt.BorderLayout.LINE_END);

        derecha.setBackground(java.awt.Color.white);

        feld01.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        feld01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feld01.setText("jLabel1");
        feld01.setPreferredSize(new java.awt.Dimension(20, 220));
        derecha.add(feld01);

        mainFrame.add(derecha, java.awt.BorderLayout.LINE_START);

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
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        this.dispose();
        new Acceder().setVisible(true);
    }//GEN-LAST:event_cerrarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centro;
    private javax.swing.JLabel cerrar;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JPanel derecha;
    private javax.swing.JLabel feld01;
    private javax.swing.JMenu file;
    private javax.swing.JPanel inferior;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JPanel izquierda;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel mainFrame;
    private javax.swing.JMenuItem salir;
    private javax.swing.JLabel sense;
    private javax.swing.JPanel superior;
    // End of variables declaration//GEN-END:variables
}
