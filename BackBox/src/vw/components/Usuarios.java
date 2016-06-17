/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import vw.main.Menu;
import vw.main.Acceder;
import vw.model.Venta;
import Control.Control;
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
import vw.model.Articulo;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import vw.dialogs.AcercaDe;
import vw.dialogs.CategoriasRegistrar;
import vw.dialogs.ProveedoresRegistrar;
import vw.dialogs.RolRegistrar;
import vw.dialogs.UsuarioActualizar;
import vw.dialogs.UsuariosRegistrar;

/**
 *
 * @author Britany
 */
public class Usuarios extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    String usuario;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public Usuarios(String nom, ArrayList acciones) throws ClassNotFoundException {
        initComponents();
        this.List_Menu = acciones;

        inicio();
        this.usuario = nom;
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
                                MenuRedireccionar MenuF = new MenuRedireccionar(Usuarios.this, e.getActionCommand(), List_Menu, usuario);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        Usuarios.this.dispose();
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
                    }
                });
                menu.add(menuItem);
            }
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        volver = new javax.swing.JButton();
        agregarUsuario = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Usuarios - BackBox");
        setPreferredSize(new java.awt.Dimension(806, 555));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
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

        agregarUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        agregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        agregarUsuario.setText("Nuevo");
        agregarUsuario.setBorder(null);
        agregarUsuario.setBorderPainted(false);
        agregarUsuario.setContentAreaFilled(false);
        agregarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarUsuario.setPreferredSize(new java.awt.Dimension(55, 47));
        agregarUsuario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        agregarUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarUsuarioActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_delete_black_24dp.png"))); // NOI18N
        jButton2.setText("Borrar");
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

        jButton5.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        jButton5.setText("Actualizar");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);;
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            borrar();
            inicio();
        } catch (Exception ex) {
            /*Nothing Here*/
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
            Update();
            inicio();

        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
        } finally {
            try {
                inicio();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void agregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarUsuarioActionPerformed
        try {
            new UsuariosRegistrar(this, true).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inicio();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_agregarUsuarioActionPerformed

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
    public void borrar() throws ClassNotFoundException {
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            String op[] = new String[2];
            op[0] = "Si";
            op[1] = "No";
            int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Borrar el Usuario? ", op);
            if (Condicion == 1) {
                Control.conectar();
                boolean r = Control.ejecuteUpdate("update persona set estado='I' where cedula=" + cod);
                if (r) {
                    Entrada.muestreMensajeV("Usuario Borrado con Exito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    inicio();
                } else {
                    Entrada.muestreMensajeV("Error al Borrar Usuario");
                }
                Control.cerrarConexion();
            }
        }
    }

    public void Update() throws ClassNotFoundException {
        Control.conectar();
        int i = jTable1.getSelectedRow();
        int j = jTable1.getSelectedColumn();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            try {
                new UsuarioActualizar(this, true, cod).setVisible(true);
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            } finally {
                inicio();
            }

        }
    }

    public void inicio() throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "select "
                + "persona.cedula \"Cédula\","
                + "upper(nombre) \"Nombre\","
                + "upper(apellido) \"Apellido\","
                + "usuario.usuario \"Usuario\""
                + "from usuario,persona\n"
                + "where\n"
                + "usuario.cedula=persona.cedula\n"
                + "and persona.estado='A'";
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
                valor = Control.rs.getString(4);

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
    private javax.swing.JButton agregarUsuario;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem salir;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
