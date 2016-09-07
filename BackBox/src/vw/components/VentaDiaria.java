/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Entrada;
import Modelo.Producto;
import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.MenuRedireccionar;
import Modelo.acciones;
import Modelo.exportar_excel;
import Modelo.seccion;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import vw.dialogs.FacturaDetalle;
import vw.main.Acceder;
import vw.main.Menu;
import vw.model.Venta;

/**
 *
 * @author Microinformatica
 */
public class VentaDiaria extends javax.swing.JFrame {

    String usuario;
    String Fecha;
    int codEmpresa;
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    public VentaDiaria(String Usuario, ArrayList acciones, int codEmpresa) throws ClassNotFoundException {
        initComponents();
        this.List_Menu = acciones;
        this.codEmpresa = codEmpresa;
        Date fec = new Date();
        this.Fecha = new SimpleDateFormat("dd-MM-yyyy").format(fec);
        jLabel4.setText(Fecha);
        Total.setText("$ 0.0");
        inicio(new SimpleDateFormat("yyyy-MM-dd").format(fec), Usuario);
        this.usuario = Usuario;
        //this.usu = Usuario;
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
                                MenuRedireccionar MenuF = new MenuRedireccionar(VentaDiaria.this, e.getActionCommand(), List_Menu, usuario, codEmpresa);
                                try {
                                    MenuF.reDireccion();
                                    if (e.getActionCommand().equalsIgnoreCase("Crear Categoria ")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Usuario")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Proveedor")
                                            || e.getActionCommand().equalsIgnoreCase("Crear Rol")) {
                                        System.out.println("No cierra Ventana");
                                    } else {
                                        VentaDiaria.this.dispose();
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(VentaDiaria.class.getName()).log(Level.SEVERE, null, ex);
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
                    MenuRedireccionar MenuF = new MenuRedireccionar(this, e.getActionCommand().toString(), List_Menu, usuario, codEmpresa);
                    try {
                        MenuF.reDireccion();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(VentaDiaria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                menu.add(menuItem);
            }
        }
    }

    public void inicio(String fecha, String usuario) throws ClassNotFoundException {
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        Control.conectar();
        Producto temp = null;
        String query = "select to_char(fecha_venta, 'HH24:MI:SS')  \"Hora\","
                + " cod_factura \"Numero Factura\","
                + " total_venta \"Venta Total\","
                + " Upper(b.descripcion) \"Detalle\" "
                + "from venta a, tipo_venta b\n"
                + " where a.tipoventa=b.cod_tipo and \n"
                + " a.fecha_venta between '" + fecha.substring(0, 10).concat(" 00:00:00") + "' and '"
                + fecha.substring(0, 10).concat(" 23:59:59") + "' and cod_usuario=" + usuario + " order by fecha_venta desc";
        String codi = "", total = "", tipo = "", hora = "";
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
            double totalVenta = 0;
            while (Control.rs.next()) {
                hora = Control.rs.getString(1);
                codi = Control.rs.getString(2);
                total = Control.rs.getString(3);
                tipo = Control.rs.getString(4);
                totalVenta = totalVenta + Double.parseDouble(total);
                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }

            Total.setText("" + formateador.format(totalVenta));
            Control.cerrarConexion();

//            Control.rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inferior = new javax.swing.JPanel();
        infIzq = new javax.swing.JPanel();
        totalVlr = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        infDer = new javax.swing.JPanel();
        ventas = new javax.swing.JButton();
        detalle = new javax.swing.JButton();
        devoluciones = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas del Día - BackBox");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBackground(new java.awt.Color(255, 255, 255));
        centro.setLayout(new javax.swing.BoxLayout(centro, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        centro.add(jScrollPane1);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        superior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel2.setText("Fecha :");
        superior.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setText("jLabel4");
        superior.add(jLabel4);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.GridLayout(1, 2));

        infIzq.setBackground(java.awt.Color.white);
        infIzq.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        totalVlr.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        totalVlr.setText("Total :");
        infIzq.add(totalVlr);

        Total.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        Total.setForeground(new java.awt.Color(255, 102, 51));
        Total.setText("jLabel4");
        Total.setMaximumSize(new java.awt.Dimension(9999, 48));
        Total.setMinimumSize(new java.awt.Dimension(290, 48));
        Total.setPreferredSize(new java.awt.Dimension(290, 48));
        infIzq.add(Total);

        inferior.add(infIzq);

        infDer.setBackground(java.awt.Color.white);

        ventas.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        ventas.setText("Vender");
        ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasActionPerformed(evt);
            }
        });
        infDer.add(ventas);

        detalle.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        detalle.setText("Detalle");
        detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleActionPerformed(evt);
            }
        });
        infDer.add(detalle);

        devoluciones.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        devoluciones.setText("Devolucion");
        devoluciones.setToolTipText("");
        devoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devolucionesActionPerformed(evt);
            }
        });
        infDer.add(devoluciones);

        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        volver.setToolTipText("Volver a Bodega");
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
        infDer.add(volver);

        inferior.add(infDer);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

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

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);;
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasActionPerformed
        ArrayList<Producto> pro = new ArrayList();
        try {
            new Venta(pro, usuario, 1, List_Menu, "1", codEmpresa).setVisible(true);;
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VentaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ventasActionPerformed

    private void detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            Entrada.muestreMensajeV("Seleccione una de las facturas primero",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            String cod = (String) jTable1.getValueAt(i, 1).toString();
            FacturaDetalle v;
            try {
                v = new FacturaDetalle(this, true, usuario, cod);
                v.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_detalleActionPerformed

    private void devolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devolucionesActionPerformed
        ArrayList<Producto> pro = new ArrayList();
        try {
            new Venta(pro, usuario, 2, List_Menu, "1", codEmpresa).setVisible(true);;
            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VentaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_devolucionesActionPerformed

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
            /**
             * ****************AGREGO EL JTABLA A UN
             * ARRAYLIST**************************
             */
            List<JTable> tb = new ArrayList<JTable>();
            tb.add(jTable1);
            //-------------------
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
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Total;
    private javax.swing.JPanel centro;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JButton detalle;
    private javax.swing.JButton devoluciones;
    private javax.swing.JMenu file;
    private javax.swing.JPanel infDer;
    private javax.swing.JPanel infIzq;
    private javax.swing.JPanel inferior;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem salir;
    private javax.swing.JPanel superior;
    private javax.swing.JLabel totalVlr;
    private javax.swing.JButton ventas;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
