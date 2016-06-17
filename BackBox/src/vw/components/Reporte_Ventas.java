/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Control;
import Modelo.exportar_excel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import vw.dialogs.ReporteDetalleVenta;
import vw.main.Menu;

/**
 *
 * @author Britany
 */
public class Reporte_Ventas extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    String usuario;

    ArrayList<Integer> ListAcciones = new ArrayList();

    public Reporte_Ventas(String nom, ArrayList acciones) throws ClassNotFoundException {
        initComponents();
        this.usuario = nom;
        this.ListAcciones = acciones;
        this.setLocationRelativeTo(null);
        cargarPersonal();
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        Date fecha = new Date();
        jDateChooser1.setDate(fecha);
        jDateChooser2.setDate(fecha);
        jLabel5.setText("0");
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
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        rol = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reporte de Ventas - BackBox");
        setPreferredSize(new java.awt.Dimension(849, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(849, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoscrolls(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 829, 330));

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
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Trabajador");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 25, -1, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 24, 133, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_find_in_page_black_24dp.png"))); // NOI18N
        jButton1.setText("Buscar");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 31, -1, 51));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_assignment_black_24dp.png"))); // NOI18N
        jButton2.setText("Detalle");
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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 450, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel3.setText("Total:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 0));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 444, 348, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/export_to_excel_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel6.setText("Fecha Final");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 57, -1, -1));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 56, 133, -1));

        rol.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        rol.setMaximumRowCount(10);
        rol.setAutoscrolls(true);
        rol.setMinimumSize(new java.awt.Dimension(31, 22));
        rol.setPreferredSize(new java.awt.Dimension(31, 25));
        jPanel1.add(rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 21, 134, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel7.setText("Fecha Inicial");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 25, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facelet/pdf.jpg"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        new Menu(usuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            inicio();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public void cargarPersonal() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select distinct usuario from usuario U, persona P where U.cedula=P.cedula and P.estado='A'");
        String nom = "";
        rol.addItem("Todos");
        try {
            while (Control.rs.next()) {
                rol.addItem(Control.rs.getString(1));
            }
            Control.cerrarConexion();

        } catch (Exception ex) {

        }
    }

    public void generarVentaPDF() throws JRException, ClassNotFoundException {

        try {
            Date date = jDateChooser1.getDate();
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = format2.format(date);

            Date date2 = jDateChooser2.getDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fechaF = format.format(date2);
            String query = "";

            System.out.println("::: " + rol.getSelectedIndex());
            if (rol.getSelectedIndex() == 0) {
                query = "select distinct to_char(fecha_venta, 'dd-mm-yyyy'),persona.nombre,\n"
                        + " persona.apellido,sum(venta.total_venta)Venta,sum((producto.precio_desc-producto.costo)*venta_pro.cantidad)"
                        + " ganancia  from venta,usuario,persona,venta_pro,producto\n"
                        + "  where\n"
                        + " venta.cod_usuario=usuario.cod_usuario\n"
                        + " and usuario.cedula=persona.cedula\n"
                        + " and venta.cod_factura=venta_pro.cod_factura\n"
                        + " and venta_pro.cod_prodcuto=producto.cod_producto\n"
                        + " and venta.fecha_venta between '" + fecha.substring(0, 10).concat(" 00:00:00'") + " and '"
                        + fechaF.substring(0, 10).concat(" 23:59:59'")
                        + " group by\n"
                        + " fecha_venta,persona.nombre,persona.apellido\n"
                        + "order by 1,3";
            } else {
                query = "select distinct to_char(fecha_venta, 'dd-mm-yyyy'),persona.nombre,\n"
                        + " persona.apellido,sum(venta.total_venta)Venta,sum((producto.precio_desc-producto.costo)*venta_pro.cantidad)"
                        + " ganancia  from venta,usuario,persona,venta_pro,producto\n"
                        + "  where\n"
                        + " venta.cod_usuario=usuario.cod_usuario\n"
                        + " and usuario.cedula=persona.cedula\n"
                        + " and venta.cod_factura=venta_pro.cod_factura\n"
                        + " and venta_pro.cod_prodcuto=producto.cod_producto\n"
                        + " and venta.fecha_venta between '" + fecha.substring(0, 10).concat(" 00:00:00'") + " and '"
                        + fechaF.substring(0, 10).concat(" 23:59:59'")
                        + " and usuario.usuario in ('" + rol.getSelectedItem().toString() + "') "
                        + " group by\n"
                        + " fecha_venta,persona.nombre,persona.apellido\n"
                        + "order by 1,3";
            }

            Control.conectar();
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechaI", fecha);
            parametros.put("FechaF", fechaF);
            parametros.put("query", query);
            JasperReport report = JasperCompileManager.compileReport("Reportes/Rep_Ventas.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, Control.con);
            JasperViewer.viewReport(jasperPrint, false);
            //JasperPrintManager.printReport(jasperPrint, true);
            Control.cerrarConexion();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public String cedula(String nom, String ape) throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from persona where nombre='" + nom + "' and "
                + "apellido='" + ape + "'");
        String ced = "";
        try {
            while (Control.rs.next()) {
                ced = Control.rs.getString(1);
            }
            Control.cerrarConexion();

        } catch (Exception ex) {

        }
        return ced;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int i = jTable1.getSelectedRow();
        int j = jTable1.getSelectedColumn();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else {
            String fec = (String) jTable1.getValueAt(i, 0).toString();
            String nom = (String) jTable1.getValueAt(i, 1).toString();
            String ape = (String) jTable1.getValueAt(i, 2).toString();
            String vent = (String) jTable1.getValueAt(i, 3).toString();

            ReporteDetalleVenta de;
            try {
                String ced = cedula(nom, ape);
                de = new ReporteDetalleVenta(this, true, fec, ced, vent, nom, ListAcciones);
                de.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reporte_Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed
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
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (jTable1.getRowCount() >= 1) {
            getBto_exportar();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Datos Para Exportar");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            generarVentaPDF();
        } catch (JRException ex) {
            Logger.getLogger(Reporte_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reporte_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void inicio() throws ClassNotFoundException {
        Control.conectar();
        DecimalFormat formateador = new DecimalFormat("###,###.##");

        Date date = jDateChooser1.getDate();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format2.format(date);

        Date date2 = jDateChooser2.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaF = format.format(date2);

        Producto temp = null;
        String query = "";
        if (rol.getSelectedIndex() == 0) {
            query = "select distinct to_char(fecha_venta, 'dd-mm-yyyy'),nombre \"Trabajador Nombre\","
                    + "apellido \"Trabajador Apellido\",sum(venta.total_venta)\"Venta\" from venta,usuario,persona\n"
                    + "where\n"
                    + "venta.cod_usuario=usuario.cod_usuario\n"
                    + "and usuario.cedula=persona.cedula\n"
                    + "and venta.fecha_venta between '" + fecha.substring(0, 10).concat(" 00:00:00") + "' and '"
                    + fechaF.substring(0, 10).concat(" 23:59:59") + "' "
                    + "group by\n"
                    + "to_char(fecha_venta, 'dd-mm-yyyy'),persona.nombre,apellido";
        } else {
            query = "select distinct to_char(fecha_venta, 'dd-mm-yyyy'),nombre \"Trabajador Nombre\","
                    + "apellido \"Trabajador Apellido\",sum(venta.total_venta)\"Venta\" from venta,usuario,persona\n"
                    + "where\n"
                    + "venta.cod_usuario=usuario.cod_usuario\n"
                    + "and usuario.cedula=persona.cedula\n"
                    + "and venta.fecha_venta between '" + fecha.substring(0, 10).concat(" 00:00:00") + "' and '"
                    + fechaF.substring(0, 10).concat(" 23:59:59") + "'and "
                    + "usuario.usuario in ('" + rol.getSelectedItem().toString() + "') "
                    + "group by\n"
                    + "to_char(fecha_venta, 'dd-mm-yyyy'),persona.nombre,apellido";
        }

        System.out.println(query);
        String cod = "", nom = "", cant = "", costo = "", iva = "", precio = "";
        String cate = "";
        String valor = "";
        DefaultTableModel modeloEmpleado = new DefaultTableModel();
        int numeroPreguntas;
        ResultSetMetaData rsetMetaData;
        this.jTable1.setModel(modeloEmpleado);
        boolean r = Control.ejecuteQuery(query);
        try {
            rsetMetaData = Control.rs.getMetaData();
            numeroPreguntas = rsetMetaData.getColumnCount();
            //Establece los nombres de las columnas de las tablas
            for (int i = 0; i < numeroPreguntas; i++) {
                modeloEmpleado.addColumn(rsetMetaData.getColumnLabel(i + 1));
            }
            double valor2 = 0;
            while (Control.rs.next()) {
                precio = Control.rs.getString(1);
                cant = Control.rs.getString(2);
                cod = Control.rs.getString(3);
                valor = Control.rs.getString(4);
                valor2 = valor2 + Double.parseDouble(valor);
                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }
            jLabel5.setText("" + formateador.format(valor2));
            Control.cerrarConexion();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
            try {
            } catch (Exception e) {;
            }
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox rol;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
