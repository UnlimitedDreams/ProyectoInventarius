/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import Modelo.Producto;

/**
 *
 * @author usuario
 */
public class FacturaDetalle extends javax.swing.JDialog {

    String factura;
    String usuario;
    Date fecha;
    Connection cone;
    double iva, valorDesc;
    int desc;
    double totalVEnta;

    /**
     * Creates new form SalidaEntrada
     *
     * @param parent Swing padre con caracteristicas del mismo
     * @param modal Modo modal
     * @param usuario Usuario actual
     * @param factura Codigo de la factura
     * @throws java.lang.ClassNotFoundException
     *
     */
    public FacturaDetalle(java.awt.Frame parent, boolean modal, String usuario, String factura) throws ClassNotFoundException {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        this.factura = factura;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        inicio();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventarius - Detalle de la Factura");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        volver.setBorder(null);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volver.setPreferredSize(new java.awt.Dimension(55, 47));
        volver.setVerifyInputWhenFocusTarget(false);
        volver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        volver.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jTable1.setToolTipText("Detalle de los productos ");
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 430, 190));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel1.setText("Total:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 60, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 240, 40));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();

    }//GEN-LAST:event_volverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Control.conectar();
            cone = Control.con;
            Control.ejecuteQuery("select "
                    + "fecha_venta,"
                    + "valoriva,"
                    + "porcentajedesc,"
                    + "valordesc,"
                    + "total_venta "
                    + "from venta where cod_factura=" + factura);
            while (Control.rs.next()) {
                fecha = Control.rs.getDate(1);
                iva = Control.rs.getDouble(2);
                desc = Control.rs.getInt(3);
                valorDesc = Control.rs.getDouble(4);
                totalVEnta = Control.rs.getDouble(5);

            }
            generarFactura(Integer.parseInt(factura), fecha, cone);
            Control.cerrarConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(FacturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public void generarFactura(int cod_factura, Date fecha, Connection c) throws JRException, ClassNotFoundException {
        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Cod_fac", cod_factura);
            parametros.put("fec_fac", fecha);
            parametros.put("desc", desc);
            parametros.put("Iva", iva);
            parametros.put("ValorDesc", valorDesc);
            parametros.put("Total", totalVEnta);
            JasperReport report = JasperCompileManager.compileReport("Fac.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, c);
            //Mostrar en pdf   // JasperViewer.viewReport(jasperPrint);   
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public void inicio() throws ClassNotFoundException {
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        Control.conectar();
        Producto temp = null;
        String query = "	select * from (\n"
                + "                select \n"
                + "                producto.serie_producto Producto,\n"
                + "                nombre Nombre,\n"
                + "                (venta_pro.valor_total+venta_pro.valoriva) Precio,\n"
                + "                venta_pro.cantidad Cantidad,((venta_pro.valor_total+venta_pro.valoriva) *venta_pro.cantidad) Total\n"
                + "                from venta,venta_pro,producto\n"
                + "                where\n"
                + "                venta.cod_factura=venta_pro.cod_factura\n"
                + "                and venta_pro.cod_producto=producto.cod_producto\n"
                + "                and venta.cod_factura=" + factura + " and producto.cod_producto<>0\n"
                + "                union \n"
                + "                 select \n"
                + "                A.cod_kit Producto,\n"
                + "                A.nombre Nombre,\n"
                + "                (venta_pro.valor_total+venta_pro.valoriva) Precio,\n"
                + "                venta_pro.cantidad Cantidad,((venta_pro.valor_total+venta_pro.valoriva) *venta_pro.cantidad) Total\n"
                + "                from venta,venta_pro,Kits A\n"
                + "                where\n"
                + "                venta.cod_factura=venta_pro.cod_factura\n"
                + "                and venta_pro.cod_kit=A.cod_kit		\n"
                + "                and venta.cod_factura=" + factura + " \n"                
                + "                )Y";

        String codi = "", total = "", cant = "", nombre = "", totalValor = "";
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
                codi = Control.rs.getString(1);
                nombre = Control.rs.getString(2);
                total = Control.rs.getString(3);
                cant = Control.rs.getString(4);
                totalValor = Control.rs.getString(5);
                totalVenta = totalVenta + (Double.parseDouble(total) * Integer.parseInt(cant));

                Object[] registroEmpleado = new Object[numeroPreguntas];

                for (int i = 0; i < numeroPreguntas; i++) {
                    registroEmpleado[i] = Control.rs.getObject(i + 1);
                }
                modeloEmpleado.addRow(registroEmpleado);
            }

            jLabel3.setText("" + formateador.format(totalVenta));
            Control.cerrarConexion();

//            Control.rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        } finally {
            Control.cerrarConexion();
        }
    }
    /**
     * Actualizar las cantidades de los productos seleccionados.
     *
     * @throws ClassNotFoundException
     */

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SalidaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SalidaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SalidaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SalidaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                SalidaEntrada dialog = new SalidaEntrada(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
