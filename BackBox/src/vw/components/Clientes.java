/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.*;
import Modelo.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import vw.dialogs.*;
import vw.main.*;

/**
 * Esta JFrame se encarga de mostrar los clientes que tiene el sistema 
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class Clientes extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    String usuario;
    int codEmpresa;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public Clientes(String nom, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        initComponents();
        this.List_Menu = acciones;
        this.codEmpresa = codEmpresa;
        inicio(1);
        this.usuario = nom;
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
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
                                MenuRedireccionar MenuF = new MenuRedireccionar(Clientes.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        Clientes.this.dispose();
                                    }
                                } catch (IOException | URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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

    private void Permisos() throws ClassNotFoundException {
        try {
            Control.conectar();
            ArrayList<String> acciones = new ArrayList();
            Control.ejecuteQuery("select * from Permisos (" + usuario + ",'Cliente')");
            while (Control.rs.next()) {
                acciones.add(Control.rs.getString(1));
            }
            agregarUsuario.setEnabled(false);
            borrar.setEnabled(false);
            actualizar.setEnabled(false);

            String acci = "";
            for (String accione : acciones) {
                acci = (String) accione;
                if (acci.equalsIgnoreCase("CliCrear")) {
                    agregarUsuario.setEnabled(true);
                } else if (acci.equalsIgnoreCase("CliEditar")) {
                    actualizar.setEnabled(true);
                } else if (acci.equalsIgnoreCase("CliBorrar")) {
                    borrar.setEnabled(true);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario, codEmpresa);
                    try {
                        MenuF.reDireccion();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        buscaUsu = new javax.swing.JTextField();
        inferior = new javax.swing.JPanel();
        agregarUsuario = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        volver = new javax.swing.JButton();
        derecha = new javax.swing.JPanel();
        izquierda = new javax.swing.JPanel();
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
        setTitle("Clientes - BackBox");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout centroLayout = new javax.swing.GroupLayout(centro);
        centro.setLayout(centroLayout);
        centroLayout.setHorizontalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
        );
        centroLayout.setVerticalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);

        buscaUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        buscaUsu.setMinimumSize(new java.awt.Dimension(760, 40));
        buscaUsu.setPreferredSize(new java.awt.Dimension(760, 40));
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
        superior.add(buscaUsu);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout1.setAlignOnBaseline(true);
        inferior.setLayout(flowLayout1);

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
        inferior.add(agregarUsuario);

        borrar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_delete_black_24dp.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setBorder(null);
        borrar.setBorderPainted(false);
        borrar.setContentAreaFilled(false);
        borrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setPreferredSize(new java.awt.Dimension(55, 47));
        borrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        inferior.add(borrar);

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
        inferior.add(actualizar);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(160, 40));
        jSeparator2.setRequestFocusEnabled(false);
        inferior.add(jSeparator2);

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
        inferior.add(volver);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBackground(java.awt.Color.white);
        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setBackground(java.awt.Color.white);
        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);;
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed

        try {
            borrar();
            inicio(1);
        } catch (Exception ex) {
            /*Nothing Here*/
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed

        try {
            Update();
            inicio(1);

        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
        }

    }//GEN-LAST:event_actualizarActionPerformed

    private void agregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarUsuarioActionPerformed
        try {
            RegistroCliente r = null;
            ArrayList<Producto> productos = new ArrayList();
            r = new RegistroCliente(this, true, productos, usuario, List_Menu, 2);
            r.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inicio(1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
    public void borrar() throws ClassNotFoundException, SQLException {
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            String op[] = new String[2];
            op[0] = "Si";
            op[1] = "No";
            int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Borrar el Cliente? ", op);
            if (Condicion == 1) {
                boolean r = false;
                try {
                    Control.conectar();
                    Control.con.setAutoCommit(false);
                    r = Control.ejecuteUpdate("delete from clientes where cedula='" + cod + "'");
                } catch (SQLException ex) {
                    System.out.println("Error");
                } finally {
                    Control.con.commit();
                    Control.con.setAutoCommit(true);
                    Control.cerrarConexion();
                }
                if (r) {
                    Entrada.muestreMensajeV("Cliente Borrado con Exito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    inicio(1);
                } else {
                    Entrada.muestreMensajeV("Error al Borrar Cliente");
                }
            }
        }
    }

    public void Update() throws ClassNotFoundException {
        int i = jTable1.getSelectedRow();
        int j = jTable1.getSelectedColumn();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String cod = (String) jTable1.getValueAt(i, 0).toString();
            try {
                new ClienteActualizar(this, true, cod).setVisible(true);
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
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

    private void inicio(int condicion) throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "";
        if (condicion == 1) {
            query = "select "
                    + "cedula \"Cédula\","
                    + "nombre \"Nombre\","
                    + "apellido \"Apellido\","
                    + "email \"Correo\","
                    + "telefono \"Teléfono\","
                    + "celular \"Celular\","
                    + "direccion \"Dirección\" from ClientesBuscar(1,'-')";
        } else if (condicion == 2) {
            if (SoloNumeros(buscaUsu.getText())) {
                query = "select  "
                    + "cedula \"Cédula\","
                    + "nombre \"Nombre\","
                    + "apellido \"Apellido\","
                    + "email \"Correo\","
                    + "telefono \"Teléfono\","
                    + "celular \"Celular\","
                    + "direccion \"Dirección\" from ClientesBuscar(2,'" + buscaUsu.getText() + "')";
            } else {
                query = "select  "
                    + "cedula \"Cédula\","
                    + "nombre \"Nombre\","
                    + "apellido \"Apellido\","
                    + "email \"Correo\","
                    + "telefono \"Teléfono\","
                    + "celular \"Celular\","
                    + "direccion \"Dirección\" from ClientesBuscar(3,'" + buscaUsu.getText() + "')";
            }
        }
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
                Object[] registroEmpleado = new Object[numeroPreguntas];
                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton agregarUsuario;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField buscaUsu;
    private javax.swing.JPanel centro;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JPanel derecha;
    private javax.swing.JMenu file;
    private javax.swing.JPanel inferior;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JPanel izquierda;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem salir;
    private javax.swing.JPanel superior;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
