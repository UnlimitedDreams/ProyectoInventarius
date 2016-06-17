/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import static Control.Control.con;
import static Control.Control.connectString;
import static Control.Control.password;
import static Control.Control.stat;
import static Control.Control.user;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static Control.Control.driver;

/**
 *
 * @author Asus
 */
public class Ireport extends javax.swing.JFrame {

    /**
     * Creates new form Ireport
     */
    public Ireport() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jButton1)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(128, 128, 128))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            generarBarra();
        } catch (JRException ex) {
            Logger.getLogger(Ireport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ireport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public static Connection conectar() throws ClassNotFoundException {
        boolean r = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stat = con.createStatement();
            System.out.println("Conecto");
            r = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void generarBarra() throws JRException, ClassNotFoundException {
        try {
            conectar();
            String sql = "select cod_producto from producto where cod_producto in ('731') union all select cod_producto from producto where cod_producto in ('731')";
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("Sql", sql);
//        URL jasperUrl = this.getClass().getResource("/Reportes/Fac.jasper");
//      JasperReport jasperReport =
//                        JasperCompileManager.compileReport("/Reportes/Fac.jasper");
      //  File ruta=File("Reportes/Fac.jasper");
        JasperReport report = JasperCompileManager.compileReport("Fac.jrxml");
       JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
            JasperViewer.viewReport(jasperPrint);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

//        JRExporter exporter = new JRPdfExporter();
//
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//        exporter.exportReport();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ireport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ireport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}