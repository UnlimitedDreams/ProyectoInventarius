/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Control;
import Control.Entrada;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.Producto;
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import vw.dialogs.RolActualizar;
import vw.dialogs.RolRegistrarF;
import vw.dialogs.RolRegistrarPrueba;
import vw.dialogs.UsuarioPermiso;
import vw.dialogs.UsuariosRegistrar;
import vw.main.Acceder;
import vw.main.Menu;
import vw.model.Articulo;
import vw.model.Venta;

/**
 *
 * @author Britany
 */
public class Roles extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    String usuario;
    int codEmpresa;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public Roles(String usuario, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        initComponents();
        inicio(1);
        this.usuario = usuario;
        this.List_Menu = acciones;
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
                      JMenuItem menuItem = new JMenuItem();
                        menuItem = MenuRedireccionar.Atajos(object1.getAccion());
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                MenuRedireccionar MenuF = new MenuRedireccionar(Roles.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        Roles.this.dispose();
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
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

    public void Permisos() throws ClassNotFoundException {
        Control.conectar();
        try {
            ArrayList<String> acciones = new ArrayList();
            Control.ejecuteQuery("select c.accion from usuario a, persona b , permisos c\n"
                    + "where\n"
                    + "a.cedula=b.cedula and \n"
                    + "a.cod_usuario=c.cod_usuario\n"
                    + "and c.panel='Roles'\n"
                    + "and a.cod_usuario=" + usuario);
            while (Control.rs.next()) {
                acciones.add(Control.rs.getString(1));
            }
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            actualizar.setEnabled(false);

            String acci = "";
            for (String accione : acciones) {
                acci = (String) accione;
                if (acci.equalsIgnoreCase("RolCrear")) {
                    jButton1.setEnabled(true);
                } else if (acci.equalsIgnoreCase("RolEditar")) {
                    actualizar.setEnabled(true);
                } else if (acci.equalsIgnoreCase("Rolborrar")) {
                    jButton2.setEnabled(true);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario, codEmpresa);
                    try {
                        MenuF.reDireccion();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
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
        buscaUsu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
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
        setTitle("Roles - BackBox");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buscaUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        buscaUsu.setSelectionColor(new java.awt.Color(51, 0, 255));
        buscaUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaUsuActionPerformed(evt);
            }
        });
        buscaUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaUsuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscaUsuKeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

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

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        jButton1.setText("Nuevo");
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

        actualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
        actualizar.setBorderPainted(false);
        actualizar.setContentAreaFilled(false);
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setPreferredSize(new java.awt.Dimension(55, 47));
        actualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(buscaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(buscaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Menu m = new Menu(usuario);
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            borrar();
            inicio(1);
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        String cod = "";
        String Nom = "";
        try {
            int i = jTable1.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
            } else {
                cod = (String) jTable1.getValueAt(i, 0).toString();
                Nom = (String) jTable1.getValueAt(i, 1).toString();
                new RolActualizar(this, true, cod, List_Menu, Nom).setVisible(true);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            try {
                inicio(1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //new RolRegistrarF(this, true).setVisible(true);
            new RolRegistrarPrueba().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                inicio(1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void buscaUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaUsuActionPerformed

    private void buscaUsuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaUsuKeyPressed

    }//GEN-LAST:event_buscaUsuKeyPressed

    private void buscaUsuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaUsuKeyReleased
        try {
            inicio(2);
        } catch (Exception ex) {
            System.out.println("error " + ex.toString());
        }
    }//GEN-LAST:event_buscaUsuKeyReleased
    public void borrar() throws ClassNotFoundException {
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            String op[] = new String[2];
            op[0] = "Si";
            op[1] = "No";
            int Condicion = Entrada.menu("Inventarius", "¿Esta Seguro que Desea Borrar el Rol? ", op);
            if (Condicion == 1) {
                Control.conectar();
                boolean r = Control.ejecuteUpdate("update rol set estado='I' where cod_rol=" + cod);
                if (r) {

                    Entrada.muestreMensajeV("Rol Borrado con Exito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    inicio(1);
                } else {
                    Entrada.muestreMensajeV("Error al Borrar Rol", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                Control.cerrarConexion();
            }
        }
    }

    public boolean SoloNumeros(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void inicio(int condicion) throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "";

        if (condicion == 1) {
            query = "select cod_rol \"Codigo\",descripcion \"Descripcion\" from rol"
                    + " where estado='A' order by descripcion";
        } else if (condicion == 2) {
            if (SoloNumeros(buscaUsu.getText())) {
                query = "select distinct  * from "
                        + "(select cod_rol \"Codigo\",descripcion \"Descripcion\" from rol"
                        + " where estado='A' and cast(cod_rol as varchar(10)) like ('%" + buscaUsu.getText() + "')"
                        + "union all "
                        + "select cod_rol \"Codigo\",descripcion \"Descripcion\" from rol"
                        + " where estado='A' and cast(cod_rol as varchar(10)) like ('%" + buscaUsu.getText() + "%'))Y";
            } else {
                query = "select cod_rol \"Codigo\",descripcion \"Descripcion\" from rol"
                        + " where estado='A' and descripcion ILIKE ('%"+buscaUsu.getText()+"%') order by descripcion";

            }
        }
        System.out.println(query);
            String cod = "", nom = "";
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
    private javax.swing.JButton actualizar;
    private javax.swing.JTextField buscaUsu;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
