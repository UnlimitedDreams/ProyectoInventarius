/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trash.components;

import Control.Control;
import Control.Entrada;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.Producto;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
import vw.components.Bodega;
import vw.components.Stock;
import vw.dialogs.KitUpdate2;
import vw.dialogs.ProductoUpdate2;
import vw.dialogs.SalidaEntrada;
import vw.main.Acceder;
import vw.main.Menu;

/**
 *
 * @author Microinformatica
 */
public class BodegaAlt extends javax.swing.JFrame {

    String usuario;
    String UsuNombre;
    int codEmpresa;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public BodegaAlt(String usuario, ArrayList acciones, int codEmpresa) throws ClassNotFoundException, SQLException {
        initComponents();
        inicio();
        this.usuario = usuario;
        System.out.println("USUARIO : " + usuario);
        this.List_Menu = acciones;
        this.codEmpresa = codEmpresa;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Stock();
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
                                MenuRedireccionar MenuF = new MenuRedireccionar(BodegaAlt.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                    } else {
                                        BodegaAlt.this.dispose();
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menu.add(menuItem);
            }
        }
    }

    private void Permisos() throws ClassNotFoundException {
        Control.conectar();
        try {
            ArrayList<String> acciones = new ArrayList();
            Control.ejecuteQuery("select * from Permisos(" + usuario + ",'Bodega')");
            while (Control.rs.next()) {
                acciones.add(Control.rs.getString(1));
            }
            salida.setEnabled(false);
            entrada.setEnabled(false);
            borrar.setEnabled(false);
            actualizar.setEnabled(false);
            stock.setEnabled(false);
            String acci = "";
            for (String accione : acciones) {
                acci = (String) accione;
                if (acci.equalsIgnoreCase("EntradaSalida")) {
                    salida.setEnabled(true);
                    entrada.setEnabled(true);
                } else if (acci.equalsIgnoreCase("BodegaBorrar")) {
                    borrar.setEnabled(true);
                } else if (acci.equalsIgnoreCase("BodegaActualizar")) {
                    actualizar.setEnabled(true);
                } else if (acci.equalsIgnoreCase("BodegaStock")) {
                    stock.setEnabled(true);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Bodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
        }
    }

    private void Stock() throws ClassNotFoundException {
        Control.conectar();
        try {
            Control.ejecuteQuery("select * from Reporte_Stock()");
            int count = 0;

            while (Control.rs.next()) {
                count++;
            }
            if (count > 0) {
                Entrada.muestreMensajeV("Hay " + count + " productos en cero cantidad",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            Control.cerrarConexion();
        } catch (Exception ex) {
            Entrada.muestreMensajeV("Error al Cargar Stock de Productos " + ex.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    private void inicio() throws ClassNotFoundException {
        Control.conectar();
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        String cabeceras[] = {"Codigo", "Nombre", "Categoria", "Costo", "Iva", "Precio", "(%)Desc.", "Cantidad"};
        modeloEmpleado = new DefaultTableModel(null, cabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        this.tablaProductos.setModel(modeloEmpleado);
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(500);
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(65);
        tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(45);
        tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(65);
        tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(90);
        tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
        //tablaProductos.getColumnModel().getColumn(1).setMaxWidth(1);
        tablaProductos.setRowHeight(30);
        this.tablaProductos.setModel(modeloEmpleado);

        try {
            Control.ejecuteQuery("select codigo \"Codigo\",nombre \"Nombre\",categoria \"Categoria\",costo \"Costo\",iva \"Iva\",precio \"Precio\""
                    + ",descuento \"Descuento\",cantidad \"Cantidad\" from BodegaInicio() limit 500");
            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            while (Control.rs.next()) {
                Object[] registroEmpleado = new Object[numeroPreguntas];
                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Cargar Bodega " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        entrada = new javax.swing.JButton();
        salida = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        stock = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventarius - Bodega");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 567));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, 910, 410));

        entrada.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        entrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_vertical_align_top_black_24dp.png"))); // NOI18N
        entrada.setText("Entrada");
        entrada.setBorder(null);
        entrada.setBorderPainted(false);
        entrada.setContentAreaFilled(false);
        entrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        entrada.setPreferredSize(new java.awt.Dimension(55, 47));
        entrada.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        entrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaActionPerformed(evt);
            }
        });
        jPanel1.add(entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 520, -1, -1));

        salida.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        salida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_vertical_align_bottom_black_24dp.png"))); // NOI18N
        salida.setText("Salida");
        salida.setBorder(null);
        salida.setBorderPainted(false);
        salida.setContentAreaFilled(false);
        salida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salida.setPreferredSize(new java.awt.Dimension(55, 47));
        salida.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        salida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaActionPerformed(evt);
            }
        });
        jPanel1.add(salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        actualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setToolTipText("");
        actualizar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
        jPanel1.add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));

        borrar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_remove_black_24dp.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        borrar.setBorderPainted(false);
        borrar.setContentAreaFilled(false);
        borrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setPreferredSize(new java.awt.Dimension(55, 45));
        borrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jPanel1.add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, -1, -1));

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
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
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 590, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_home_black_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 520, -1, -1));

        stock.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_assignment_black_24dp.png"))); // NOI18N
        stock.setText("Stock");
        stock.setBorder(null);
        stock.setBorderPainted(false);
        stock.setContentAreaFilled(false);
        stock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stock.setPreferredSize(new java.awt.Dimension(55, 47));
        stock.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        stock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockActionPerformed(evt);
            }
        });
        jPanel1.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, -1, -1));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            Buscar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try {
            borrar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_borrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        try {
            update();
        } catch (Exception ex) {
            Entrada.muestreMensajeV("Error Al Actualizar Datos",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_actualizarActionPerformed

    private void salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaActionPerformed
        try {
            Sacar();
        } catch (Exception ex) {
            //nothing here
        }

    }//GEN-LAST:event_salidaActionPerformed

    private void entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaActionPerformed
        try {
            Entrar();
        } catch (Exception ex) {
            //nothing here
        }
    }//GEN-LAST:event_entradaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Menu m = new Menu(usuario);
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockActionPerformed
        try {
            Stock st = new Stock(usuario, List_Menu, codEmpresa);
            st.setVisible(true);
            this.dispose();
        } catch (Exception ex) {

        }
// TODO add your handling code here:
    }//GEN-LAST:event_stockActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
    public boolean SoloNumeros(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void Buscar() throws ClassNotFoundException {
        if (jTextField2.getText().length() > 0) {
            String query = "";
            if (SoloNumeros(jTextField2.getText())) {
                query = "select codigo \"Codigo\",nombre \"Nombre\",cate \"Categoria\",rcosto \"Costo\",riva \"Iva\",rprecio \"Precio\","
                        + "rdescuento \"Descuento\",rcantidad \"Cantidad\"  from BodegaInicioBuscar(1,'" + jTextField2.getText() + "') ";
            } else {
                query = "select codigo \"Codigo\",nombre \"Nombre\",cate \"Categoria\",rcosto \"Costo\",riva \"Iva\",rprecio \"Precio\","
                        + "rdescuento \"Descuento\",rcantidad \"Cantidad\"  from BodegaInicioBuscar(2,'" + jTextField2.getText() + "')";
            }
            Control.conectar();
            DefaultTableModel modeloEmpleado = new DefaultTableModel();
            int numeroPreguntas;
            ResultSetMetaData rsetMetaData;
            String cabeceras[] = {"Codigo", "Nombre", "Categoria", "Costo", "Iva", "Precio", "(%)Desc.", "Cantidad"};
            modeloEmpleado = new DefaultTableModel(null, cabeceras) {
                @Override
                public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                    return false;
                }
            };
            this.tablaProductos.setModel(modeloEmpleado);
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(500);
            tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(65);
            tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(45);
            tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(65);
            tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(90);
            tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
            //tablaProductos.getColumnModel().getColumn(1).setMaxWidth(1);
            tablaProductos.setRowHeight(30);
            this.tablaProductos.setModel(modeloEmpleado);
            try {
                Control.ejecuteQuery(query);
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
                JOptionPane.showMessageDialog(null, "Error al Realizar Filtro de Datos " + e.getMessage());
            } finally {
                Control.cerrarConexion();
            }
        } else {
            inicio();
        }
    }

    public void update() throws ClassNotFoundException {
        Producto producto = new Producto();
        int i = tablaProductos.getSelectedRow();
        if (i == -1) {
            Entrada.muestreMensajeV("Seleccione un producto primero",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            String cod = (String) tablaProductos.getValueAt(i, 0).toString();
            String nombre = (String) tablaProductos.getValueAt(i, 1).toString();
            String categoria = (String) tablaProductos.getValueAt(i, 2).toString();
            String Costo = (String) tablaProductos.getValueAt(i, 3).toString();
            String iva = (String) tablaProductos.getValueAt(i, 4).toString();
            String Precio = (String) tablaProductos.getValueAt(i, 5).toString();
            String cant = (String) tablaProductos.getValueAt(i, 7).toString();
            String Desc = (String) tablaProductos.getValueAt(i, 6).toString();
            producto.setCodigo(cod);
            producto.setNombre(nombre);
            producto.setCosto(Double.parseDouble(Costo));
            producto.setIva(Integer.parseInt(iva));
            producto.setDesc(Integer.parseInt(Desc));
            producto.setPrecio_venta(Double.parseDouble(Precio));
            producto.setCantidad(Integer.parseInt(cant));
            if (categoria.equalsIgnoreCase("Kits")) {
                new KitUpdate2(this, true, cod, 2).setVisible(true);
            } else {
                ProductoUpdate2 p = new ProductoUpdate2(this, true, producto, codEmpresa);
                p.setVisible(true);
            }
        }
    }

    public void borrar() throws ClassNotFoundException, SQLException {
        boolean r = false;
        int Condicion = 0;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);
            int i = tablaProductos.getSelectedRow();
            int j = tablaProductos.getSelectedColumn();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... Seleccione una Fila");
            } else {
                String cod = (String) tablaProductos.getValueAt(i, 0).toString();
                String op[] = new String[2];
                op[0] = "Si";
                op[1] = "No";
                Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Borrar el Producto? \n Recuerde que "
                        + "despues de borrar el producto,SI el producto tiene transacciones (Ventas),\n no podra volver a "
                        + "usar el mismo codigo para otro producto, De lo contrario Si", op);
                if (Condicion == 1) {
                    r = Control.ejecuteUpdate("update producto set estado='I' where serie_producto='" + cod + "'");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al Borrar producto : " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }

        if (r && Condicion == 1) {
            Entrada.muestreMensajeV("Producto Borrado con Exito");
            inicio();
        } else if (r == false && Condicion == 1) {
            Entrada.muestreMensajeV("Error Borrando el Producto");
        }

    }

    public void Sacar() throws ClassNotFoundException {
        int i = tablaProductos.getSelectedRow();
        if (i == -1) {
            Entrada.muestreMensajeV("Seleccione un Producto por favor",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            String cod = (String) tablaProductos.getValueAt(i, 0).toString();
            String c = (String) tablaProductos.getValueAt(i, 7).toString();
            SalidaEntrada newSalida = new SalidaEntrada(this, true,
                    cod, "Salida", usuario, c, List_Menu, codEmpresa);
            newSalida.setVisible(true);

        }
    }

    public ArrayList<seccion> getListaSeccion() {
        return listaSeccion;
    }

    public void setListaSeccion(ArrayList<seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }

    public ArrayList<acciones> getListaaccion() {
        return listaaccion;
    }

    public void setListaaccion(ArrayList<acciones> listaaccion) {
        this.listaaccion = listaaccion;
    }

    public ArrayList<ContenedorMenus> getList_Menu() {
        return List_Menu;
    }

    public void setList_Menu(ArrayList<ContenedorMenus> List_Menu) {
        this.List_Menu = List_Menu;
    }

    public void Entrar() throws ClassNotFoundException {
        int i = tablaProductos.getSelectedRow();
        int j = tablaProductos.getSelectedColumn();
        if (i == -1) {
            Entrada.muestreMensajeV("Seleccione un Producto por favor",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            String cod = (String) tablaProductos.getValueAt(i, 0).toString();
            String c = (String) tablaProductos.getValueAt(i, 6).toString();

            SalidaEntrada newEntrada = new SalidaEntrada(this, true,
                    cod, "Entrada", usuario, c, List_Menu, codEmpresa);
            newEntrada.setVisible(true);

        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton borrar;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JButton entrada;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton salida;
    private javax.swing.JMenuItem salir;
    private javax.swing.JButton stock;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}