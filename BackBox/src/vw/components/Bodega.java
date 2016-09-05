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
import vw.dialogs.KitUpdate2;
import vw.dialogs.ProductoUpdate2;
import vw.dialogs.SalidaEntrada;
import vw.main.Acceder;
import vw.main.Menu;

/**
 *
 * @author usuario
 */
public class Bodega extends javax.swing.JFrame {

    String usuario;
    String UsuNombre;
    int codEmpresa;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public Bodega(String usuario, ArrayList acciones, int codEmpresa) throws ClassNotFoundException, SQLException {
        initComponents();
        inicio();
        this.usuario = usuario;
        this.List_Menu = acciones;
        this.codEmpresa = codEmpresa;
        this.setLocationRelativeTo(null);
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
                        JMenuItem menuItem = new JMenuItem();
                        menuItem = MenuRedireccionar.Atajos(object1.getAccion());
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                MenuRedireccionar MenuF = new MenuRedireccionar(Bodega.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                    } else {
                                        Bodega.this.dispose();
                                    }
                                } catch (IOException | URISyntaxException ex) {
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

    public void inicio() throws ClassNotFoundException {

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
            Control.conectar();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        busca = new javax.swing.JTextField();
        inferior = new javax.swing.JPanel();
        inicio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        actualizar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        stock = new javax.swing.JButton();
        entrada = new javax.swing.JButton();
        salida = new javax.swing.JButton();
        derecha = new javax.swing.JPanel();
        izquierda = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio1 = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BackBox - Bodega");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBackground(java.awt.Color.white);
        centro.setLayout(new javax.swing.BoxLayout(centro, javax.swing.BoxLayout.LINE_AXIS));

        tablaProductos.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaProductos);

        centro.add(jScrollPane1);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        superior.setLayout(flowLayout1);

        busca.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        busca.setMinimumSize(new java.awt.Dimension(600, 40));
        busca.setPreferredSize(new java.awt.Dimension(760, 40));
        busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaActionPerformed(evt);
            }
        });
        busca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscaKeyReleased(evt);
            }
        });
        superior.add(busca);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_home_black_24dp.png"))); // NOI18N
        inicio.setBorder(null);
        inicio.setBorderPainted(false);
        inicio.setContentAreaFilled(false);
        inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inicio.setPreferredSize(new java.awt.Dimension(55, 47));
        inicio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        inicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });
        inferior.add(inicio);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(160, 40));
        jSeparator1.setRequestFocusEnabled(false);
        inferior.add(jSeparator1);

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
        inferior.add(actualizar);

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
        inferior.add(borrar);

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
        inferior.add(stock);

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
        inferior.add(entrada);

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
        inferior.add(salida);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBackground(java.awt.Color.white);
        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setBackground(java.awt.Color.white);
        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

        file.setText("Archivo");
        file.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        inicio1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        inicio1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        inicio1.setText("Inicio");
        inicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicio1ActionPerformed(evt);
            }
        });
        file.add(inicio1);

        cerrarSesion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        file.add(cerrarSesion);
        file.add(jSeparator2);

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

    private void buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaActionPerformed

    private void buscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaKeyPressed

    }//GEN-LAST:event_buscaKeyPressed

    private void buscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaKeyReleased
        try {
            Buscar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_buscaKeyReleased

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        try {
            update();
        } catch (Exception ex) {
            Entrada.muestreMensajeV("Error Al Actualizar Datos",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try {
            borrar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_borrarActionPerformed

    private void stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockActionPerformed
        try {
            Stock st = new Stock(usuario, List_Menu, codEmpresa);
            st.setVisible(true);
            this.dispose();
        } catch (Exception ex) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_stockActionPerformed

    private void entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaActionPerformed
        try {
            Entrar();
        } catch (Exception ex) {
            //nothing here
        }
    }//GEN-LAST:event_entradaActionPerformed

    private void salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaActionPerformed
        try {
            Sacar();
        } catch (Exception ex) {
            //nothing here
        }
    }//GEN-LAST:event_salidaActionPerformed

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        Menu m = new Menu(usuario);
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_inicioActionPerformed

    private void inicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicio1ActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inicio1ActionPerformed

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
        if (busca.getText().length() > 0) {
            String query = "";
            if (SoloNumeros(busca.getText())) {
                query = "select codigo \"Codigo\",nombre \"Nombre\",cate \"Categoria\",rcosto \"Costo\",riva \"Iva\",rprecio \"Precio\","
                        + "rdescuento \"Descuento\",rcantidad \"Cantidad\"  from BodegaInicioBuscar(1,'" + busca.getText() + "') ";
            } else {
                query = "select codigo \"Codigo\",nombre \"Nombre\",cate \"Categoria\",rcosto \"Costo\",riva \"Iva\",rprecio \"Precio\","
                        + "rdescuento \"Descuento\",rcantidad \"Cantidad\"  from BodegaInicioBuscar(2,'" + busca.getText() + "')";
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busca;
    private javax.swing.JPanel centro;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JPanel derecha;
    private javax.swing.JButton entrada;
    private javax.swing.JMenu file;
    private javax.swing.JPanel inferior;
    private javax.swing.JButton inicio;
    private javax.swing.JMenuItem inicio1;
    private javax.swing.JPanel izquierda;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton salida;
    private javax.swing.JMenuItem salir;
    private javax.swing.JButton stock;
    private javax.swing.JPanel superior;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
