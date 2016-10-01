/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.*;
import Modelo.*;
import vw.main.Menu;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.model.*;

/**
 * Este Jframe se encarga de mostrar la informacion que se ve a cargar al
 * sistema, para los productos
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class CargaArchivo extends javax.swing.JFrame {

    /**
     * Metodo que se encarga iniciar el Jframe para cargar los productos.
     *
     * @param listaproductos lista de productos que se extrayeron del archivo
     * excel.
     * @param cod_usuario codigo de usuario que esta haciendo el carge
     * @param acciones lista de MenuItem del usuario
     */
    ArrayList listaProductos;
    String cod_usuario;
    String nom_usuario;
    ArrayList<Integer> ListAcciones = new ArrayList();
    ArrayList<List_Object> listIvas = new ArrayList();

    public CargaArchivo(ArrayList listaproductos, String cod_usuario, ArrayList acciones) throws ClassNotFoundException {
        try {
            initComponents();
            this.cod_usuario = cod_usuario;
            this.nom_usuario = "";
            this.ListAcciones = acciones;
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.listaProductos = listaproductos;
            ConfigurarInicio();
            iniciar();
            cargarUsuario();
            jTextField1.setText("" + listaproductos.size());
            URL url = getClass().getResource("/images/facelet/icon.png");
            ImageIcon img = new ImageIcon(url);
            setIconImage(img.getImage());
        } catch (SQLException ex) {
            Logger.getLogger(CargaArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que se encarga de recuperar el nombre del usuario
     */
    public void cargarUsuario() throws SQLException, ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from CargaUsuario(" + cod_usuario + ")");
        String nombre = "";
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
        }
        this.nom_usuario = nombre;
        Control.cerrarConexion();
    }

    /**
     * Metodo que se encarga de ajustar y determinar si el producto que esta
     * cargado ya se encuentra en el sistema.
     */
    public void ConfigurarInicio() throws SQLException {
        try {
            Control.conectar();
            Producto temp = null;
            int cantidad = 0;
            int iva = 0;
            int ivaF = 0;
            for (int i = 0; i < listaProductos.size(); i++) {
                temp = (Producto) listaProductos.get(i);
                Control.ejecuteQuery("select * from CargaProductoBuqueda('" + temp.getCodigo() + "')");
                if (Control.rs.next()) {
                    temp.setEsta("Existe");
                    temp.setCantBD(Control.rs.getInt(1));
                    temp.setCodigoProducto(Control.rs.getInt(2));
                } else {
                    temp.setEsta("-");
                    temp.setCantBD(0);
                }

                for (List_Object cate : listIvas) {
                    iva = Integer.parseInt(cate.getNom());
                    ivaF = cate.getCod();
                    if (temp.getIva() == iva) {
                        temp.setIvaCargeArchivo(ivaF);
                        break;
                    }
                }
            }

            listIvas.clear();
            Control.ejecuteQuery("select * from CargaIva()");
            while (Control.rs.next()) {
                listIvas.add(new List_Object(Control.rs.getInt(1), "" + Control.rs.getInt(2)));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
        }

    }

    /**
     * Metodo que se encarga de Configurar la table y cargar los productos a la
     * tabla
     */
    public void iniciar() throws SQLException {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[listaProductos.size()][6], new String[]{
                    "Codigo", "Nombre", "Costo", "Iva", "Precio", "Cantidad", "Estado"
                }
        ));
        TablaModel t = new TablaModel(listaProductos, 7, 1);
        t.ModelCargaArchivo();
        muevaLosDatosFre(t);

    }

    /**
     * Metodo que se encarga de asignar un valor a cada columna de la tabla
     */
    public void muevaLosDatosFre(TablaModel x) {
        jTable1.getDefaultEditor(null);
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable1.setValueAt(x.frecuencias[i][k], k, i);

            }
        }
    }

    /**
     * Metodo que se encarga de guardar los prodcutos en la base de datos
     */
    public void insertarLibro() throws ClassNotFoundException, SQLException {
        Date fecha = new Date();
        ArrayList<String> listaError = new ArrayList();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String fechafinal = format2.format(fecha);
        String mns = "Inventario : " + fechafinal;
        
        String tipo = "";
        Producto pr = null;
        int Noentro = 0;
        int Actualizo = 0;
        int Creo = 0;
        boolean f = false;
        int cantidad = 0;
        int cantFinal = 0;
        int cantSAlida = 0;
        int Condicion = 0;
        
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);

            for (int i = 0; i < listaProductos.size(); i++) {
                pr = (Producto) listaProductos.get(i);
                double precio = pr.getPrecio_venta();
                if (pr.getCantBD() >= 0) {
                    cantidad = pr.getCantidad() - pr.getCantBD();
                } else {
                    cantidad = pr.getCantBD() + pr.getCantidad();
                }
                if (pr.getEsta().equalsIgnoreCase("Existe") && cantidad != 0) {
                    Actualizo++;
                    if (cantidad > 0 && pr.getCantBD() < 0) {
                        tipo = "Entrada";
                        cantFinal = cantidad;
                        cantSAlida = pr.getCantidad();
                    } else if (cantidad > 0 && pr.getCantBD() > 0) {
                        tipo = "Entrada";
                        cantFinal = pr.getCantidad();
                        cantSAlida = cantidad;
                    } else if (cantidad < 0 && pr.getCantBD() < 0) {
                        tipo = "Entrada";
                        cantFinal = cantidad;
                        cantSAlida = pr.getCantidad();
                    } else if (cantidad < 0 && pr.getCantBD() > 0) {
                        tipo = "Salida";
                        cantFinal = pr.getCantidad();
                        cantSAlida = cantidad;
                    }
                } else if (pr.getEsta().equalsIgnoreCase("Existe") && cantidad == 0) {
                    f = true;
                    Noentro++;
//No ingresa datos
                } else {
                    Creo++;
                }
            }
            String op[] = new String[2];
            op[0] = "Si";
            op[1] = "No";
            Condicion = Entrada.menu("BackBox", "Detalle Cambios en el Sistema. \n "
                    + "Detalles. \n"
                    + "Nuevos Productos : " + Creo + "\n"
                    + "Actualizo Productos : " + Actualizo + "\n"
                    + "Sin Operacion : " + Noentro + "\n ¿Desea Realizar los combios ?", op);
            System.err.println("valor de condicion : " + Condicion);
            if (Condicion == 1) {
                for (int i = 0; i < listaProductos.size(); i++) {
                    pr = (Producto) listaProductos.get(i);
                    double precio = pr.getPrecio_venta();
                    if (pr.getCantBD() >= 0) {
                        cantidad = pr.getCantidad() - pr.getCantBD();
                    } else {
                        cantidad = pr.getCantBD() + pr.getCantidad();
                    }
                    if (pr.getEsta().equalsIgnoreCase("Existe") && cantidad != 0) {
                        Actualizo++;
                        if (cantidad > 0 && pr.getCantBD() < 0) {
                            tipo = "Entrada";
                            cantFinal = cantidad;
                            cantSAlida = pr.getCantidad();
                        } else if (cantidad > 0 && pr.getCantBD() > 0) {
                            tipo = "Entrada";
                            cantFinal = pr.getCantidad();
                            cantSAlida = cantidad;
                        } else if (cantidad < 0 && pr.getCantBD() < 0) {
                            tipo = "Entrada";
                            cantFinal = cantidad;
                            cantSAlida = pr.getCantidad();
                        } else if (cantidad < 0 && pr.getCantBD() > 0) {
                            tipo = "Salida";
                            cantFinal = pr.getCantidad();
                            cantSAlida = cantidad;
                        }
                        f = Control.ejecuteUpdate("insert into Salida_Entrada values(nextval('Sq_SalidaEntrada'),'" + tipo + "'," + Math.abs(cantSAlida) + ",'" + fecha + "','" + mns + "','" + nom_usuario + "'," + pr.getCodigoProducto() + ")");
                        System.out.println("paso 2");
                        f = Control.ejecuteUpdate("update producto set cantidad=" + cantFinal + " where"
                                + " cod_producto=" + pr.getCodigoProducto());

                    } else if (pr.getEsta().equalsIgnoreCase("Existe") && cantidad == 0) {
                        f = true;
                        Noentro++;
//No ingresa datos
                    } else {
                        Creo++;
                        f = Control.ejecuteUpdate("insert into producto values('" + pr.getNombre() + "',"
                                + pr.getCosto() + "," + pr.getIvaCargeArchivo() + "," + precio + "," + pr.getCategoria() + ","
                                + pr.getCantidad() + ",'A','n'," + pr.getDesc() + "," + precio + ",1,1,'" + pr.getCodigo() + "',nextval('Sq_producto'))");
                    }
                    if (f == false) {
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.toString());
        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        if (Condicion == 1) {
            if (f) {
                Entrada.muestreMensajeV("Carga Masiva Exitosa");
                Articulo ar;
                try {
                    ar = new Articulo(cod_usuario, ListAcciones, 1);
                    ar.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Entrada_Nueva.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
            } else {
                Entrada.muestreMensajeV("Error al Cargar Archivo  "
                        + javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        inferior = new javax.swing.JPanel();
        Nuevo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        derecha = new javax.swing.JPanel();
        izquierda = new javax.swing.JPanel();

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BackBox - Carga Masiva de datos");
        setExtendedState(6);
        setPreferredSize(new java.awt.Dimension(815, 560));

        centro.setBackground(new java.awt.Color(255, 255, 255));
        centro.setLayout(new javax.swing.BoxLayout(centro, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane2.setViewportView(jTable1);

        centro.add(jScrollPane2);

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        superior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Cantidad:");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 25));
        superior.add(jLabel2);

        jTextField1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setMinimumSize(new java.awt.Dimension(250, 20));
        jTextField1.setPreferredSize(new java.awt.Dimension(250, 20));
        superior.add(jTextField1);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        Nuevo.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_attach_file_black_24dp.png"))); // NOI18N
        Nuevo.setText("Cargar");
        Nuevo.setBorder(null);
        Nuevo.setBorderPainted(false);
        Nuevo.setContentAreaFilled(false);
        Nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevo.setMaximumSize(new java.awt.Dimension(55, 47));
        Nuevo.setMinimumSize(new java.awt.Dimension(55, 47));
        Nuevo.setPreferredSize(new java.awt.Dimension(55, 47));
        Nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        inferior.add(Nuevo);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setMaximumSize(new java.awt.Dimension(55, 47));
        jButton4.setMinimumSize(new java.awt.Dimension(55, 47));
        jButton4.setOpaque(false);
        jButton4.setPreferredSize(new java.awt.Dimension(55, 47));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        inferior.add(jButton4);

        getContentPane().add(inferior, java.awt.BorderLayout.PAGE_END);

        derecha.setBackground(java.awt.Color.white);
        getContentPane().add(derecha, java.awt.BorderLayout.LINE_END);

        izquierda.setBackground(java.awt.Color.white);
        getContentPane().add(izquierda, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        try {
            insertarLibro();
        } catch (Exception ex) {

        }
//        jLabel3.setVisible(false);
    }//GEN-LAST:event_NuevoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Menu(cod_usuario).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Nuevo;
    private javax.swing.JPanel centro;
    private javax.swing.JPanel derecha;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel izquierda;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel superior;
    // End of variables declaration//GEN-END:variables
}
