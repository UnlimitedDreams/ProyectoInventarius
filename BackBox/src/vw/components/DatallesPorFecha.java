/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Control;
import Modelo.exportar_excel;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vw.main.Menu;

/**
 *
 * @author Microinformatica
 */
public class DatallesPorFecha extends javax.swing.JFrame {

    String nom;
    ArrayList<Integer> ListAcciones = new ArrayList();

    public DatallesPorFecha(String nom, ArrayList acciones) throws ClassNotFoundException {
        initComponents();
        this.nom = nom;
        this.ListAcciones = acciones;
        this.setLocationRelativeTo(null);
        setTitle("REPORTE ENTRADA Y SALIDA DE PRODUCTOS EN BODEGA");
        this.setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
        Date fecha = new Date();
        jDateChooser1.setDate(fecha);
        jDateChooser2.setDate(fecha);

    }

    public void inicio() throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "";
        Date date = jDateChooser1.getDate();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format2.format(date);

        Date date2 = jDateChooser2.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha2 = format2.format(date2);

        query = "select to_char(fecha, 'dd/mm/yyyy') fecha,to_char(fecha, 'HH24:MI:SS') Hora,producto.nombre \"Nombre\""
                + ",salida_entrada.nombre \"Tipo\","
                + "salida_entrada.cant \"Cantidad\",salida_entrada.comentario \"Descripcion\",responsable"
                + " from salida_entrada,producto where\n"
                + "salida_entrada.cod_producto=producto.cod_producto and \n"
                + "  fecha  between '" + fecha.substring(0, 10).concat(" 00:00:00") + "' and '"
                    + fecha2.substring(0, 10).concat(" 23:59:59") + "' \n"
                + " order by 1,2 DESC";

        System.out.println(query);
        String codi = "", nom = "", valor = "", cant = "", costo = "";
        String descrip = "", respon = "";
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
                codi = Control.rs.getString(1);
                nom = Control.rs.getString(2);
                costo = Control.rs.getString(3);
                valor = Control.rs.getString(4);
                descrip = Control.rs.getString(5);
                respon = Control.rs.getString(6);

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        volver = new javax.swing.JButton();
        exportar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.alee.extended.date.WebDateField();
        jDateChooser2 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 561));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 700, 350));

        volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        volver.setBorder(null);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        volver.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, -1, -1));

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/export_to_excel_24dp.png"))); // NOI18N
        exportar.setBorder(null);
        exportar.setBorderPainted(false);
        exportar.setContentAreaFilled(false);
        exportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        exportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        jPanel1.add(exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel2.setText("Fecha Final");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 17, 170, -1));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 17, 170, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_find_in_page_black_24dp.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 10, 50, 50));

        jLabel6.setText("Fecha Inicial");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed

        new Menu(nom).setVisible(true);
        this.dispose();

    }//GEN-LAST:event_volverActionPerformed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed

        if (jTable1.getRowCount() >= 1) {
            getBto_exportar();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Datos Para Exportar");
        }
    }//GEN-LAST:event_exportarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            inicio();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed
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
    private javax.swing.JButton exportar;
    private javax.swing.JButton jButton1;
    private com.alee.extended.date.WebDateField jDateChooser1;
    private com.alee.extended.date.WebDateField jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
