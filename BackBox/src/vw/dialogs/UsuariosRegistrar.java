/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vw.components.Entrada;
import vw.components.List_Categoria;
import vw.components.Sequence;

/**
 *
 * @author Miguel Lemoz
 */
public class UsuariosRegistrar extends javax.swing.JDialog {

    /**
     * Creates new form UsuariosRegistrar
     */
    int SecuenciaCC;

    /**
     *
     * @param parent
     * @param modal
     */
    public UsuariosRegistrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());

        try {
            cargarRoles();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarRoles() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from rol");
        String cod = "", nom = "";
        ArrayList<List_Categoria> cater = new ArrayList();
        rol.addItem("--");
        try {
            while (Control.rs.next()) {
                cater.add(new List_Categoria(Control.rs.getInt(1), Control.rs.getString(2)));
                rol.addItem(Control.rs.getString(2));
            }
            System.out.println("Tamaño de roles");
            System.out.println("-- " + cater.size());
            Control.cerrarConexion();

        } catch (Exception ex) {

        }
    }

    public boolean SoloNumeros(String cadena) {
        long num = 0;
        try {
            num = Long.parseLong(cadena);

            SecuenciaCC = cadena.length();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean VerificarDatos() {
        boolean r = true;
        if (cedula.getText().length() == 0) {
            r = false;
        } else if (nombre.getText().length() == 0) {
            r = false;
        } else if (apellido.getText().length() == 0) {
            r = false;
        } else if (usuario.getText().length() == 0) {
            r = false;
        } else if (clave.getText().length() == 0) {
            r = false;
        }
        return r;
    }

    public boolean buscar_cod() throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery("select * from persona where "
                + "cedula=" + cedula.getText() + "");
        boolean r = false;
        try {
            while (Control.rs.next()) {
                r = true;
            }
            Control.cerrarConexion();
        } catch (Exception ex) {
        }
        return r;
    }

    public void registrar() throws ClassNotFoundException {
        if (VerificarDatos()) {
            if (buscar_cod() == false) {
                int cod_rol = traerCod();
                int S = sexo.getSelectedIndex();
                Control.conectar();
                String sexo = "";
                if (S == 1) {
                    sexo = "M";
                } else if (S == 2) {
                    sexo = "F";
                }
                boolean r = Control.ejecuteUpdate("insert into persona values("
                        + cedula.getText() + ",'"
                        + nombre.getText() + "','"
                        + apellido.getText() + "','"
                        + sexo + "',"
                        + "'A')");
                if (r) {
                    int usu = Sequence.seque("select max(cod_usuario) from usuario  ");
                    boolean f = Control.ejecuteUpdate("insert into usuario"
                            + " values(" + usu + ",'" + usuario.getText() + "','"
                            + clave.getText() + "'," + cod_rol + "," + cedula.getText() + ")");
                    if (f) {
                        Entrada.muestreMensajeV("REGISTRO EXITOSO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        Entrada.muestreMensajeV("ERROR ", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Entrada.muestreMensajeV("ERROR ", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                Control.cerrarConexion();

            } else {
                Entrada.muestreMensajeV("La Cedula  ya existe", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } else {
            Entrada.muestreMensajeV("Debe llenar Todos los Datos", javax.swing.JOptionPane.WARNING_MESSAGE);
        }

    }

    public int traerCod() throws ClassNotFoundException {
        Control.conectar();
        String nom = rol.getSelectedItem().toString();
        Control.ejecuteQuery("select cod_rol from rol where descripcion='" + nom + "'");
        int cod = 0;
        try {
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
            Control.cerrarConexion();

        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
        return cod;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        rol = new javax.swing.JComboBox();
        registrar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        sexo = new javax.swing.JComboBox();
        clave = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Usuario - BackBox");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel2.setText("Sexo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel3.setText("Cedula:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel5.setText("Apellido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel6.setText("Usuario:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel7.setText("Rol:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel8.setText("Clave:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        cedula.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });
        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaKeyReleased(evt);
            }
        });
        jPanel1.add(cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 380, -1));

        nombre.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 380, -1));

        apellido.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 380, -1));

        usuario.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 380, -1));

        rol.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 380, -1));

        registrar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setBorder(null);
        registrar.setBorderPainted(false);
        registrar.setContentAreaFilled(false);
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.setPreferredSize(new java.awt.Dimension(55, 47));
        registrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        registrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, -1, -1));

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
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        sexo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "Hombre", "Mujer" }));
        jPanel1.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 380, -1));

        clave.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });
        jPanel1.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 380, -1));

        jPanel2.setBackground(new java.awt.Color(196, 70, 38));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_cedulaActionPerformed

    private void cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyPressed

    }//GEN-LAST:event_cedulaKeyPressed

    private void cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyReleased
        boolean v = SoloNumeros(cedula.getText());
        if (v == false) {
            cedula.setText(cedula.getText().substring(0, SecuenciaCC));
        }
    }//GEN-LAST:event_cedulaKeyReleased

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_apellidoActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (this.sexo.getSelectedIndex() == 0
                || this.rol.getSelectedIndex() == 0
                || this.nombre.getText().isEmpty()
                || this.cedula.getText().isEmpty()
                || this.apellido.getText().isEmpty()
                || this.usuario.getText().isEmpty()
                || this.clave.getText().isEmpty()) {
            Entrada.muestreMensajeV("No se permiten Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                registrar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuariosRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_nombreActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_usuarioActionPerformed

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
        registrarActionPerformed(evt);
    }//GEN-LAST:event_claveActionPerformed

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
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UsuariosRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                UsuariosRegistrar dialog = new UsuariosRegistrar(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField cedula;
    private javax.swing.JPasswordField clave;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton registrar;
    private javax.swing.JComboBox rol;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTextField usuario;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
