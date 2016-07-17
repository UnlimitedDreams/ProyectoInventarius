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
import javax.swing.ImageIcon;
import Control.Entrada;
import Control.Sequence;

/**
 *
 * @author usuario
 */
public class RolActualizar extends javax.swing.JDialog {

    String ced = "";
    String nomRol = "";
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
    public RolActualizar(java.awt.Frame parent, boolean modal, String cod, ArrayList menu, String NomRol) {
        super(parent, modal);
        initComponents();
        this.List_Menu = menu;
        this.nomRol = NomRol;
        jTextField3.setText(nomRol);
        ContenedorMenus con_menu = new ContenedorMenus();
        con_menu = (ContenedorMenus) List_Menu.get(0);
        listaSeccion = con_menu.getListaSeccion();
        listaaccion = con_menu.getListaAcciones();
        for (seccion object : listaSeccion) {
            if (object.getCod_seccion() == 1) {
                jCheckBox3.setSelected(true);
            } else if (object.getCod_seccion() == 2) {
                jCheckBox14.setSelected(true);
            }
        }
        this.ced = cod;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/facelet/icon.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }

    public void update() throws ClassNotFoundException, SQLException {
        boolean cerrar = false;
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Actualizar el Rol? ", op);
        if (Condicion == 1) {

            int codigoAct = Sequence.seque("select max(cod_detalleAc) from detalleactividad");
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);
                boolean r = Control.ejecuteUpdate("update rol set "
                        + "descripcion='" + jTextField3.getText() + "' where "
                        + "cod_rol=" + ced);
                if (r) {

                    Control.ejecuteUpdate("delete from detalleactividad where cod_rol=" + ced);
                    Control.ejecuteUpdate("update rol set descripcion='" + jTextField3.getText() + "' where cod_rol=" + ced);

                    if (LisBodega.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",4)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;
                    }
                    if (ListArti.isSelected() && NewCompra.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",9)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",10)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;
                    } else if (ListArti.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",9)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;

                    } else if (NewCompra.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",10)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;

                    }

                    if (ListCate.isSelected() && NewCate.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",11)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",12)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;
                    } else if (ListCate.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",11)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;
                    } else if (NewCate.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",12)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                        codigoAct++;
                    }
                    if (ListUsu.isSelected() && NewUsu.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",1)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",2)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (ListUsu.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",1)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (NewUsu.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",2)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    }

                   
                    if (VVentas.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",16)");
                        codigoAct++;
                    }

                    if (VDevol.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",17)");
                        codigoAct++;
                    }

                    if (VDiaria.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",18)");
                        codigoAct++;
                    }
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",3)");
                    codigoAct++;
                    
                    
                    if (RVentas.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",20)");
                        codigoAct++;
                    }

                    if (rcompras.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",21)");
                        codigoAct++;
                    }

                    if (Rentradas.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",22)");
                        codigoAct++;
                    }
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",3)");
                    codigoAct++;
                    
                    
                    if (ListPro.isSelected() && NewPro.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",5)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",6)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (ListPro.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",5)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (NewPro.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",6)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    }
                    if (ListRol.isSelected() && NewRol.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",7)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",8)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (ListRol.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",7)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    } else if (NewRol.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",8)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    }
                    if (ListClientes.isSelected()) {
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",30)");
                        codigoAct++;
                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                        codigoAct++;
                    }
                }
                cerrar = true;
            } catch (Exception ex) {
                cerrar = false;
            } finally {
                Control.con.commit();
                Control.con.setAutoCommit(true);
                Control.cerrarConexion();
            }
        }
        if (cerrar) {
            this.dispose();
        }else{
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
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        ListCate = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        LisBodega = new javax.swing.JCheckBox();
        NewCompra = new javax.swing.JCheckBox();
        ListArti = new javax.swing.JCheckBox();
        NewCate = new javax.swing.JCheckBox();
        ListUsu = new javax.swing.JCheckBox();
        NewUsu = new javax.swing.JCheckBox();
        ListPro = new javax.swing.JCheckBox();
        NewPro = new javax.swing.JCheckBox();
        ListRol = new javax.swing.JCheckBox();
        NewRol = new javax.swing.JCheckBox();
        ListClientes = new javax.swing.JCheckBox();
        Rentradas = new javax.swing.JCheckBox();
        VDiaria = new javax.swing.JCheckBox();
        rcompras = new javax.swing.JCheckBox();
        RVentas = new javax.swing.JCheckBox();
        VVentas = new javax.swing.JCheckBox();
        VDevol = new javax.swing.JCheckBox();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Rol - BackBox");
        setPreferredSize(new java.awt.Dimension(700, 430));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 23)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jTextField3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 330, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_update_black_24dp.png"))); // NOI18N
        jButton1.setText("Actualizar");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, -1));

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Bodega");
        jCheckBox3.setOpaque(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jCheckBox13.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Stakeholders");
        jCheckBox13.setOpaque(false);
        jCheckBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox13ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        ListCate.setBackground(new java.awt.Color(255, 255, 255));
        ListCate.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        ListCate.setSelected(true);
        ListCate.setText("Lista Categoria");
        ListCate.setOpaque(false);
        jPanel1.add(ListCate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox14.setSelected(true);
        jCheckBox14.setText("Venta");
        jCheckBox14.setOpaque(false);
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Reportes");
        jCheckBox17.setOpaque(false);
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Acciones Menu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        LisBodega.setSelected(true);
        LisBodega.setText("Lista Bodega");
        LisBodega.setOpaque(false);
        jPanel1.add(LisBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        NewCompra.setSelected(true);
        NewCompra.setText("Nueva Compra");
        NewCompra.setOpaque(false);
        NewCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewCompraActionPerformed(evt);
            }
        });
        jPanel1.add(NewCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        ListArti.setSelected(true);
        ListArti.setText("Lista Articulos");
        ListArti.setOpaque(false);
        jPanel1.add(ListArti, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        NewCate.setSelected(true);
        NewCate.setText("Crear Categoria");
        NewCate.setOpaque(false);
        jPanel1.add(NewCate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        ListUsu.setSelected(true);
        ListUsu.setText("Lista Usuarios");
        jPanel1.add(ListUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        NewUsu.setSelected(true);
        NewUsu.setText("Crear Usuarios");
        jPanel1.add(NewUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        ListPro.setSelected(true);
        ListPro.setText("Lista Proveedores");
        jPanel1.add(ListPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        NewPro.setSelected(true);
        NewPro.setText("Crear Proveedor");
        jPanel1.add(NewPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, -1, -1));

        ListRol.setSelected(true);
        ListRol.setText("Lista Roles");
        jPanel1.add(ListRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));

        NewRol.setSelected(true);
        NewRol.setText("Crear Rol");
        jPanel1.add(NewRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));

        ListClientes.setSelected(true);
        ListClientes.setText("Lista Clientes");
        jPanel1.add(ListClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, -1, -1));

        Rentradas.setSelected(true);
        Rentradas.setText("E/S");
        jPanel1.add(Rentradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, -1, -1));

        VDiaria.setSelected(true);
        VDiaria.setText("Venta Diaria");
        jPanel1.add(VDiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        rcompras.setSelected(true);
        rcompras.setText("Compras");
        jPanel1.add(rcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        RVentas.setSelected(true);
        RVentas.setText("Ventas");
        jPanel1.add(RVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        VVentas.setSelected(true);
        VVentas.setText("Realizar Venta");
        jPanel1.add(VVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        VDevol.setSelected(true);
        VDevol.setText("Realizar Devolucion");
        VDevol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VDevolActionPerformed(evt);
            }
        });
        jPanel1.add(VDevol, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            update();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        if (jCheckBox13.isSelected()) {
            ListClientes.setSelected(true);
            ListPro.setSelected(true);
            ListRol.setSelected(true);
            ListUsu.setSelected(true);
            NewUsu.setSelected(true);
            NewPro.setSelected(true);
            NewRol.setSelected(true);
        } else {
            ListClientes.setSelected(false);
            ListPro.setSelected(false);
            ListRol.setSelected(false);
            ListUsu.setSelected(false);
            NewUsu.setSelected(false);
            NewPro.setSelected(false);
            NewRol.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBox13ActionPerformed

    private void NewCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewCompraActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if (jCheckBox3.isSelected()) {
            LisBodega.setSelected(true);
            ListArti.setSelected(true);
            NewCompra.setSelected(true);
            ListCate.setSelected(true);
            NewCate.setSelected(true);
        } else {
            LisBodega.setSelected(false);
            ListArti.setSelected(false);
            NewCompra.setSelected(false);
            ListCate.setSelected(false);
            NewCate.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed
        if (jCheckBox17.isSelected()) {
            rcompras.setSelected(true);
            Rentradas.setSelected(true);
            RVentas.setSelected(true);
        } else {
            rcompras.setSelected(false);
            Rentradas.setSelected(false);
            RVentas.setSelected(false);
        }


    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void VDevolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VDevolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VDevolActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
        if (jCheckBox14.isSelected()) {
            VDiaria.setSelected(true);
            VDevol.setSelected(true);
            VVentas.setSelected(true);
        } else {
            VDiaria.setSelected(false);
            VDevol.setSelected(false);
            VVentas.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBox14ActionPerformed

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
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RolActualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RolActualizar dialog = new RolActualizar(new javax.swing.JFrame(), true, "1");
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
    private javax.swing.JCheckBox LisBodega;
    private javax.swing.JCheckBox ListArti;
    private javax.swing.JCheckBox ListCate;
    private javax.swing.JCheckBox ListClientes;
    private javax.swing.JCheckBox ListPro;
    private javax.swing.JCheckBox ListRol;
    private javax.swing.JCheckBox ListUsu;
    private javax.swing.JCheckBox NewCate;
    private javax.swing.JCheckBox NewCompra;
    private javax.swing.JCheckBox NewPro;
    private javax.swing.JCheckBox NewRol;
    private javax.swing.JCheckBox NewUsu;
    private javax.swing.JCheckBox RVentas;
    private javax.swing.JCheckBox Rentradas;
    private javax.swing.JCheckBox VDevol;
    private javax.swing.JCheckBox VDiaria;
    private javax.swing.JCheckBox VVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JCheckBox rcompras;
    // End of variables declaration//GEN-END:variables
}
