/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.dialogs;

import Control.Control;
import Modelo.ContenedorMenus;
import Modelo.acciones;
import Modelo.seccion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import Control.Entrada;
import Control.Sequence;
import Modelo.Producto;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class UsuarioPermiso extends javax.swing.JDialog {

    String ced = "";
    ArrayList<seccion> listaSeccion = new ArrayList();
    ArrayList<acciones> listaaccion = new ArrayList();
    ArrayList<ContenedorMenus> List_Menu = new ArrayList();

    /**
     * Creates new form RolActualizar
     *
     * @param parent
     * @param modal
     * @param cod
     */
    public UsuarioPermiso(java.awt.Frame parent, boolean modal, String cod, ArrayList menu) {
        super(parent, modal);
        initComponents();
        this.List_Menu = menu;
        ContenedorMenus con_menu = new ContenedorMenus();
        con_menu = (ContenedorMenus) List_Menu.get(0);
        listaSeccion = con_menu.getListaSeccion();
        listaaccion = con_menu.getListaAcciones();
        this.ced = cod;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        try {
            RecuperarCodigoUsu();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuarioo : " + ced);
    }

    /**
     *
     * @throws ClassNotFoundException No encuentra OJDBC
     */
    public void RecuperarCodigoUsu() throws ClassNotFoundException {

        try {
            Control.conectar();
            System.out.println("select distinct D.nom_accion,A.cod_usuario from usuario A , rol B ,detalleactividad C , acciones D where A.cod_rol=B.cod_rol  and\n"
                    + " A.cod_rol=C.cod_rol and C.cod_accion=D.codacciones  and D.codacciones not in (3) and  A.cedula=" + ced);
            Control.ejecuteQuery("select distinct D.nom_accion,A.cod_usuario from usuario A , rol B ,detalleactividad C , acciones D where A.cod_rol=B.cod_rol  and\n"
                    + " A.cod_rol=C.cod_rol and C.cod_accion=D.codacciones  and D.codacciones not in (3) and  A.cedula=" + ced);
            int cod_usu = 0;
            Tipo.addItem("Seleccione");
            while (Control.rs.next()) {
                Tipo.addItem(Control.rs.getString(1));
                cod_usu = Control.rs.getInt(2);
            }
            this.ced = "" + cod_usu;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Control.cerrarConexion();
        }
    }

    public void Insert() throws ClassNotFoundException, SQLException {
        boolean cerrar = false;
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Crear Los permisos? ", op);
        if (Condicion == 1) {

            int codigoAct = Sequence.seque("select max(cod_permiso) from permisos");
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);

                Control.ejecuteUpdate("delete from permisos where cod_usuario=" + ced);

//                if (BEntradaSalida.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','EntradaSalida'," + ced + ")");
//                    codigoAct++;
//                }
//                if (BBorrar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaBorrar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (BActualizar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaActualizar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (BReporte.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaStock'," + ced + ")");
//                    codigoAct++;
//                }
//                if (ACompra.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','NuevaCompra'," + ced + ")");
//                    codigoAct++;
//                }
//                if (ACarga.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','ArtiuloCarga'," + ced + ")");
//                    codigoAct++;
//                }
//                if (AReporte.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','ArticuloReporte'," + ced + ")");
//                    codigoAct++;
//                }
//                if (UCrear.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuCrear'," + ced + ")");
//                    codigoAct++;
//                }
//                if (UEditar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuEditar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (UBorrar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuBorrar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (UPermiso.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuPermisos'," + ced + ")");
//                    codigoAct++;
//                }
//                if (PCrear.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProCrear'," + ced + ")");
//                    codigoAct++;
//                }
//
//                if (PEditar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProEditar'," + ced + ")");
//                    codigoAct++;
//                }
//
//                if (PBorrar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProBorrar'," + ced + ")");
//                    codigoAct++;
//                }
//
//                if (RCrear.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','RolCrear'," + ced + ")");
//                    codigoAct++;
//                }
//                if (REditar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','RolEditar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (RBorrar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','Rolborrar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (CCrear.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliCrear'," + ced + ")");
//                    codigoAct++;
//                }
//                if (CEditar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliEditar'," + ced + ")");
//                    codigoAct++;
//                }
//                if (CBorrar.isSelected()) {
//                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliBorrar'," + ced + ")");
//                    codigoAct++;
//                }
                cerrar = true;
            } catch (ClassNotFoundException | SQLException ex) {
                cerrar = false;
            } finally {
                Control.con.commit();
                Control.con.setAutoCommit(true);
                Control.cerrarConexion();
            }
        }
        if (cerrar) {
            this.dispose();
        } else {
            Entrada.muestreMensajeV("Error Comuniquese con soporte");
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

        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        Tipo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustar Permisos de usuario - BackBox");
        setPreferredSize(new java.awt.Dimension(500, 430));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tipo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Tipo.setMaximumRowCount(10);
        Tipo.setAutoscrolls(true);
        Tipo.setMinimumSize(new java.awt.Dimension(31, 22));
        Tipo.setPreferredSize(new java.awt.Dimension(31, 25));
        Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoActionPerformed(evt);
            }
        });
        jPanel2.add(Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 370, -1));

        jLabel1.setText("Control de Permisos");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 41, -1, -1));

        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 116, 372, 231));

        jButton1.setText("Agregar");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void cargarTable() {
        try {
            if (Tipo.getSelectedItem().equals("Seleccione")) {

            } else {
                Control.conectar();
                System.out.println("select cod_permiso,panel from permisos where rtrim(ltrim(panel))='" + Tipo.getSelectedItem().toString().trim() + "' and cod_usuario=" + ced);
                Control.ejecuteQuery("select cod_permiso,panel from permisos where rtrim(ltrim(panel))='" + Tipo.getSelectedItem().toString().trim() + "' and cod_usuario=" + ced);
                String cabeceras[] = {"Codigo", "Accion"};
                DefaultTableModel modeloEmpleado = new DefaultTableModel(null, cabeceras) {
                    @Override
                    public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                        return false;
                    }
                };
                int numeroPreguntas;
                ResultSetMetaData rsetMetaData;
                this.jTable1.setModel(modeloEmpleado);

                rsetMetaData = Control.rs.getMetaData();
                numeroPreguntas = rsetMetaData.getColumnCount();
                while (Control.rs.next()) {

                    Object[] registroEmpleado = new Object[numeroPreguntas];
                    for (int i = 0; i < numeroPreguntas; i++) {
                        registroEmpleado[i] = Control.rs.getObject(i + 1);
                    }
                    modeloEmpleado.addRow(registroEmpleado);
                }
                Control.cerrarConexion();
            }

        } catch (Exception ex) {

        } finally {

        }
    }
    private void TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoActionPerformed
        cargarTable();
    }//GEN-LAST:event_TipoActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == 127) {
            try {
                borrar();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    public void borrar() throws SQLException {
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("Inventarius", "¿Esta Seguro que Desea Borrar el Producto? ", op);
        if (Condicion == 1) {
            int i = jTable1.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
            } else {
                boolean r = false;
                try {
                    String cod = (String) jTable1.getValueAt(i, 0).toString();
                    Control.conectar();
                    Control.con.setAutoCommit(false);
                    r = Control.ejecuteUpdate("delete from permisos where cod_permiso=" + cod);
                } catch (Exception ex) {

                } finally {
                    Control.con.commit();
                    Control.con.setAutoCommit(false);
                    Control.cerrarConexion();
                }

                if (r) {
                    System.out.println("Borrado con exito");
                    cargarTable();
                } else {
                    System.out.println("Borrado Mal");
                }
            }

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
