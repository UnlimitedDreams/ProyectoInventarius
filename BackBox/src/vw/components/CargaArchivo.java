/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Entrada;
import Modelo.Producto;
import vw.main.Menu;
import Control.Tabla;
import Control.Control;
import Control.Sequence;
import Modelo.List_Categoria;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.model.Articulo;
import vw.model.Venta;

/**
 *
 * @author Britany
 */
public class CargaArchivo extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    ArrayList p;
    String usuario;
    String nombre;
    ArrayList<Integer> ListAcciones = new ArrayList();
    ArrayList<List_Categoria> listIvas = new ArrayList();

    public CargaArchivo(ArrayList p, String nom, ArrayList acciones) throws ClassNotFoundException {
        try {
            initComponents();
            this.usuario = nom;
            this.nombre = "";
            this.ListAcciones = acciones;
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.p = p;
            ConfigurarIva();
            iniciar();
            cargarUsuario();
            jTextField1.setText("" + p.size());
            URL url = getClass().getResource("/images/facelet/icon.png");
            ImageIcon img = new ImageIcon(url);
            setIconImage(img.getImage());
        } catch (SQLException ex) {
            Logger.getLogger(CargaArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarUsuario() throws SQLException, ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from CargaUsuario(" + usuario + ")");
        String nombre = "";
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
        }
        this.nombre = nombre;
        Control.cerrarConexion();
    }

    public void ConfigurarIva() throws SQLException {
        try {
            listIvas.clear();
            Control.conectar();
            Control.ejecuteQuery("select * from CargaIva()");
            while (Control.rs.next()) {
                listIvas.add(new List_Categoria(Control.rs.getInt(1), "" + Control.rs.getInt(2)));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            Control.cerrarConexion();
        }

    }

    public void iniciar() throws SQLException {
        try {
            String r = "";
            Control.conectar();
            Control.con.setAutoCommit(false);
            Producto temp = null;
            int cantidad = 0;
            int iva = 0;
            int ivaF = 0;
            for (int i = 0; i < p.size(); i++) {
                temp = (Producto) p.get(i);
                Control.ejecuteQuery("select * from CargaProductoBuqueda('"+temp.getCodigo()+"')");
                if (Control.rs.next()) {
                    temp.setEsta("Existe");
                    temp.setCantBD(Control.rs.getInt(1));
                    temp.setCodigoProducto(Control.rs.getInt(2));
                } else {
                    temp.setEsta("-");
                    temp.setCantBD(0);
                }

                for (List_Categoria cate : listIvas) {
                    iva = Integer.parseInt(cate.getNom());
                    ivaF = cate.getCod();
                    if (temp.getIva() == iva) {
                        temp.setIvaCargeArchivo(ivaF);
                        break;
                    }
                }
            }

        } catch (Exception ex) {

        } finally {
            Control.con.commit();
            Control.con.setAutoCommit(true);
            Control.cerrarConexion();
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[p.size()][6], new String[]{
                    "Codigo", "Nombre", "Costo", "Iva", "Precio", "Cantidad", "Estado"
                }
        ));
        Tabla t = new Tabla(p);
        t.calculeFrecuenciasV();
        muevaLosDatosFre(t);
    }

    public void muevaLosDatosFre(Tabla x) {
        jTable1.getDefaultEditor(null);
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < x.getNrofreq(); k++) {
                jTable1.setValueAt(x.frecuencias[i][k], k, i);

            }
        }
    }

    public void insertarLibro() throws ClassNotFoundException, SQLException {
        Producto pr = null;
        Date fecha = new Date();
        int Noentro = 0;
        int Actualizo = 0;
        int Creo = 0;
        ArrayList<String> listaError = new ArrayList();
        boolean f = false;
        boolean r1 = false;
        int codProducto = Sequence.seque("select max(cod_producto) from producto");
        int codigo_sal = Sequence.seque("select max(cod_entra) from Salida_Entrada");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String fechafinal = format2.format(fecha);
        String mns = "Inventario : " + fechafinal;
        String tipo = "";
        int cantidad = 0;
        int cantFinal = 0;
        int cantSAlida = 0;
        int Condicion = 0;
        try {
            Control.conectar();
            Control.con.setAutoCommit(false);

            for (int i = 0; i < p.size(); i++) {
                pr = (Producto) p.get(i);
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
                for (int i = 0; i < p.size(); i++) {
                    pr = (Producto) p.get(i);
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
                        f = Control.ejecuteUpdate("insert into Salida_Entrada values("
                                + codigo_sal + ",'" + tipo + "'," + Math.abs(cantSAlida) + ",'" + fecha + "','" + mns + "','" + nombre + "'," + pr.getCodigoProducto() + ")");
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
                                + pr.getCantidad() + ",'A','n'," + pr.getDesc() + "," + precio + ",1,1,'" + pr.getCodigo() + "',"
                                + codProducto + ")");
                    }
                    if (f == false) {
                        break;
                    }
                    codigo_sal++;
                    codProducto++;
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
                    ar = new Articulo(usuario, ListAcciones, 1);
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
        jPanel1 = new javax.swing.JPanel();
        Nuevo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(815, 560));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_add_black_24dp.png"))); // NOI18N
        Nuevo.setText("CARGAR");
        Nuevo.setBorder(null);
        Nuevo.setBorderPainted(false);
        Nuevo.setContentAreaFilled(false);
        Nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        jPanel1.add(Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 82, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, -1, -1));

        jTable1.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 65, 770, 370));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Cantidad");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jTextField1.setEnabled(false);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 40, 91, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CARGA MASIVA DE DATOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );

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
        new Menu(usuario).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Nuevo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
