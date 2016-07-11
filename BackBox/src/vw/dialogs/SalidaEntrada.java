/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.components.Bodega;
import Control.Entrada;
import Control.Sequence;

/**
 *
 * @author usuario
 */
public class SalidaEntrada extends javax.swing.JDialog {

    String codigo;
    String tipo;
    String nombre;
    String cant;
    String usuario;
    int codEmpresa;
    ArrayList<Integer> ListAcciones = new ArrayList();

    /**
     * Creates new form SalidaEntrada
     *
     * @param parent Swing padre con caracteristicas del mismo
     * @param modal Modo modal
     * @param codigo Codigo del producto
     * @param tipo Tipo de Producto
     * @param Nombre Nombre del producto
     * @param cant Cantidad del Producto
     */
    public SalidaEntrada(java.awt.Frame parent, boolean modal,
            String codigo, String tipo, String Nombre, String cant, ArrayList acciones,int codEmpresa) {
        super(parent, modal);
        initComponents();
        this.codigo = codigo;
        this.tipo = tipo;
        this.usuario = Nombre;
        this.cant = cant;
        this.ListAcciones = acciones;
        this.codEmpresa=codEmpresa;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        try {
            cargarUsuario(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(SalidaEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalidaEntrada.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        actualizar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        cantidad = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BackBox - Salidas/Entradas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Cantidad");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setText("Comentario");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 86, -1, 110));

        actualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
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
        jPanel1.add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

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
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        cantidad.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        cantidad.setToolTipText("Ajuste del movimiento");
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
        });
        jPanel1.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 210, -1));

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

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed

        try {
            actualizarCantidad();
        } catch (Exception ex) {
            System.err.println("Error : " + ex.toString());
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();

    }//GEN-LAST:event_volverActionPerformed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased

    }//GEN-LAST:event_cantidadKeyReleased
    public void cargarUsuario(String cod) throws SQLException, ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select nombre,apellido from usuario,persona where \n"
                + "usuario.cedula=persona.cedula and  cod_usuario=" + cod);
        String nombre = "";
        while (Control.rs.next()) {
            nombre = Control.rs.getString(1) + " " + Control.rs.getString(2);
        }
        this.nombre = nombre;
        Control.cerrarConexion();

    }

    /**
     * Actualizar las cantidades de los productos seleccionados.
     *
     * @throws ClassNotFoundException
     */
    public void actualizarCantidad() throws ClassNotFoundException, SQLException {
        int cant2 = (int) cantidad.getValue();
        if (cant2 <= Integer.parseInt(cant) || tipo.equalsIgnoreCase("Entrada")) {
            int codigo_sal = Sequence.seque("select max(cod_entra) from Salida_Entrada");
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);
                Date fecha = new Date();
                if (jTextArea1.getText().equalsIgnoreCase("")) {
                    Entrada.muestreMensajeV("Debe escribir una descripcion en comentarios",
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean r1 = Control.ejecuteUpdate("insert into Salida_Entrada values("
                            + codigo_sal + ",'" + tipo + "'," + cantidad.getValue() + ",'" + fecha + "','"
                            + codigo + "','" + jTextArea1.getText() + "','" + nombre + "')");
                    if (r1) {
                        boolean r = false;
                        if (tipo.equalsIgnoreCase("Salida")) {
                            r = Control.ejecuteUpdate("update producto set cantidad=cantidad-" + cantidad.getValue() + " where cod_producto='" + codigo + "'");
                        } else {
                            System.out.println("Update producto");
                            r = Control.ejecuteUpdate("update producto set cantidad=cantidad+" + cantidad.getValue() + " where cod_producto='" + codigo + "'");

                        }
                        if (r) {
                            Control.con.commit();
                            Control.con.setAutoCommit(true);
                            Control.cerrarConexion();
                            Bodega b = new Bodega(usuario, ListAcciones,codEmpresa);
                            this.dispose();

                        } else {
                            Entrada.muestreMensajeV("Error Haciendo Cambios a La Bodega",
                                    javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Entrada.muestreMensajeV("Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {

            } finally {
                System.out.println("cerro");
                Control.con.commit();
                Control.con.setAutoCommit(true);
                Control.cerrarConexion();
            }

        } else {
            Entrada.muestreMensajeV("No dispone cantidad en stock, para hacer salida de este producto.",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

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
    private javax.swing.JButton actualizar;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
