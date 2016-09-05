/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Modelo.Producto;
import Control.Control;
import Modelo.exportar_excel;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/facelet/icon.png")));
        Date fecha = new Date();
        fechaInicial.setDate(fecha);
        fechaFinal.setDate(fecha);

    }

    public void inicio() throws ClassNotFoundException {
        Control.conectar();
        Producto temp = null;
        String query = "";
        Date date = fechaInicial.getDate();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format2.format(date);

        Date date2 = fechaFinal.getDate();
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

        centro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        superior = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fechaInicial = new com.alee.extended.date.WebDateField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        fechaFinal = new com.alee.extended.date.WebDateField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        inferior = new javax.swing.JPanel();
        exportar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        volver = new javax.swing.JButton();
        derecha = new javax.swing.JPanel();
        izquierda = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reportes - BackBox");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        centro.setBackground(new java.awt.Color(255, 255, 255));
        centro.setPreferredSize(new java.awt.Dimension(750, 561));

        jTable1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout centroLayout = new javax.swing.GroupLayout(centro);
        centro.setLayout(centroLayout);
        centroLayout.setHorizontalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                .addContainerGap())
        );
        centroLayout.setVerticalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(centro, java.awt.BorderLayout.CENTER);

        superior.setBackground(java.awt.Color.white);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10);
        flowLayout1.setAlignOnBaseline(true);
        superior.setLayout(flowLayout1);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel6.setText("Fecha Inicial");
        superior.add(jLabel6);

        fechaInicial.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        fechaInicial.setMinimumSize(new java.awt.Dimension(130, 30));
        fechaInicial.setPreferredSize(new java.awt.Dimension(130, 30));
        superior.add(fechaInicial);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator4.setPreferredSize(new java.awt.Dimension(30, 30));
        jSeparator4.setRequestFocusEnabled(false);
        superior.add(jSeparator4);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Fecha Final");
        superior.add(jLabel2);

        fechaFinal.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        fechaFinal.setMinimumSize(new java.awt.Dimension(130, 30));
        fechaFinal.setPreferredSize(new java.awt.Dimension(130, 30));
        superior.add(fechaFinal);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(160, 30));
        jSeparator3.setRequestFocusEnabled(false);
        superior.add(jSeparator3);

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_find_in_page_black_24dp.png"))); // NOI18N
        jButton1.setToolTipText("Buscar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        superior.add(jButton1);

        getContentPane().add(superior, java.awt.BorderLayout.PAGE_START);

        inferior.setBackground(java.awt.Color.white);
        inferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/export_to_excel_24dp.png"))); // NOI18N
        exportar.setBorder(null);
        exportar.setBorderPainted(false);
        exportar.setContentAreaFilled(false);
        exportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportar.setPreferredSize(new java.awt.Dimension(55, 47));
        exportar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        exportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });
        inferior.add(exportar);

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
    private javax.swing.JPanel centro;
    private javax.swing.JPanel derecha;
    private javax.swing.JButton exportar;
    private com.alee.extended.date.WebDateField fechaFinal;
    private com.alee.extended.date.WebDateField fechaInicial;
    private javax.swing.JPanel inferior;
    private javax.swing.JPanel izquierda;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel superior;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
