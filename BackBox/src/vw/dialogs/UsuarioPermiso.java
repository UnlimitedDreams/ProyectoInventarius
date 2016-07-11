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
import javax.swing.JMenu;
import vw.components.Entrada;
import vw.components.Sequence;

/**
 *
 * @author usuario
 */
public class UsuarioPermiso extends javax.swing.JDialog {

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
    public UsuarioPermiso(java.awt.Frame parent, boolean modal, String cod, ArrayList menu, String NomRol) {
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
                Bodega.setSelected(true);
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

    public void Insert() throws ClassNotFoundException, SQLException {
        boolean cerrar = false;
        String op[] = new String[2];
        op[0] = "Si";
        op[1] = "No";
        int Condicion = Entrada.menu("BackBox", "¿Esta Seguro que Desea Crear Los permisos? ", op);
        if (Condicion == 1) {

            int codigoAct = Sequence.seque("select max(cod_permisos) from permisos");
            try {
                Control.conectar();
                Control.con.setAutoCommit(false);

                Control.ejecuteUpdate("delete from permisos where cod_usuario=" + ced);

                if (BEntradaSalida.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','EntradaSalida'," + ced + ")");
                    codigoAct++;
                }
                if (BBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaBorrar'," + ced + ")");
                    codigoAct++;
                }
                if (BActualizar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaActualizar'," + ced + ")");
                    codigoAct++;
                }
                if (BReporte.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaReporte'," + ced + ")");
                    codigoAct++;
                }
                if (ACompra.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','NuevaCompra'," + ced + ")");
                    codigoAct++;
                }
                if (ACarga.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','ArtiuloCarga'," + ced + ")");
                    codigoAct++;
                }
                if (AReporte.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Articulo','ArticuloReporte'," + ced + ")");
                    codigoAct++;
                }
                if (UCrear.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuCrear'," + ced + ")");
                    codigoAct++;
                }
                if (UEditar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuEditar'," + ced + ")");
                    codigoAct++;
                }
                if (UBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuBorrar'," + ced + ")");
                    codigoAct++;
                }
                if (UPermiso.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuPermisos'," + ced + ")");
                    codigoAct++;
                }
                if (PCrear.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProCrear'," + ced + ")");
                    codigoAct++;
                }

                if (PEditar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProEditar'," + ced + ")");
                    codigoAct++;
                }

                if (PBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Proveedores','ProBorrar'," + ced + ")");
                    codigoAct++;
                }

                if (UPermiso.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Usuarios','UsuPermisos'," + ced + ")");
                    codigoAct++;
                }

                if (BBorrar.isSelected() && BActualizar.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",9)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",10)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;
                } else if (BBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",9)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;

                } else if (BActualizar.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",10)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;

                }

                if (BReporte.isSelected() && NewCate.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",11)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",12)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",1," + ced + ",3)");
                    codigoAct++;
                } else if (BReporte.isSelected()) {
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
                if (ACompra.isSelected() && ACarga.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",1)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",2)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                } else if (ACompra.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",1)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                } else if (ACarga.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",2)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                }

                if (jCheckBox17.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",20)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",21)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",22)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",3)");
                    codigoAct++;
                }
//                    if (VVentas.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",16)");
//                        codigoAct++;
//                    }

//                    if (VDevol.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",17)");
//                        codigoAct++;
//                    }
//
//                    if (VDiaria.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",18)");
//                        codigoAct++;
//                    }
                Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",2," + ced + ",3)");
                codigoAct++;

                if (UCrear.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",20)");
                    codigoAct++;
                }

                if (UEditar.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",21)");
                    codigoAct++;
                }

                if (UBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",22)");
                    codigoAct++;
                }
                Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",3," + ced + ",3)");
                codigoAct++;

                if (AReporte.isSelected() && NewPro.isSelected()) {
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",5)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",6)");
                    codigoAct++;
                    Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
                    codigoAct++;
                } else if (AReporte.isSelected()) {
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
//                    if (ListRol.isSelected() && UPermiso.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",7)");
//                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",8)");
//                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
//                        codigoAct++;
//                    } else if (ListRol.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",7)");
//                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
//                        codigoAct++;
//                    } else if (UPermiso.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",8)");
//                        codigoAct++;

//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
//                        codigoAct++;
//                    }
//                    if (ListClientes.isSelected()) {
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",30)");
//                        codigoAct++;
//                        Control.ejecuteUpdate("insert into detalleactividad values(" + codigoAct + ",18," + ced + ",3)");
//                        codigoAct++;
//                    }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Bodega = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        BReporte = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        BEntradaSalida = new javax.swing.JCheckBox();
        BActualizar = new javax.swing.JCheckBox();
        BBorrar = new javax.swing.JCheckBox();
        NewCate = new javax.swing.JCheckBox();
        ACompra = new javax.swing.JCheckBox();
        ACarga = new javax.swing.JCheckBox();
        AReporte = new javax.swing.JCheckBox();
        NewPro = new javax.swing.JCheckBox();
        UPermiso = new javax.swing.JCheckBox();
        UBorrar = new javax.swing.JCheckBox();
        UEditar = new javax.swing.JCheckBox();
        UCrear = new javax.swing.JCheckBox();
        PCrear = new javax.swing.JCheckBox();
        PEditar = new javax.swing.JCheckBox();
        PBorrar = new javax.swing.JCheckBox();
        RCrear = new javax.swing.JCheckBox();
        REditar = new javax.swing.JCheckBox();
        RBorrar = new javax.swing.JCheckBox();
        CCrear = new javax.swing.JCheckBox();
        CEditar = new javax.swing.JCheckBox();
        CBorrar = new javax.swing.JCheckBox();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Rol - BackBox");
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

        Bodega.setBackground(new java.awt.Color(255, 255, 255));
        Bodega.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        Bodega.setSelected(true);
        Bodega.setText("Bodega");
        Bodega.setOpaque(false);
        Bodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodegaActionPerformed(evt);
            }
        });
        jPanel1.add(Bodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jCheckBox13.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Articulos");
        jCheckBox13.setOpaque(false);
        jCheckBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox13ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        BReporte.setBackground(new java.awt.Color(255, 255, 255));
        BReporte.setSelected(true);
        BReporte.setText("Reporte Excel");
        BReporte.setOpaque(false);
        BReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BReporteActionPerformed(evt);
            }
        });
        jPanel1.add(BReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox14.setSelected(true);
        jCheckBox14.setText("Proveedores");
        jCheckBox14.setOpaque(false);
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Usuarios");
        jCheckBox17.setOpaque(false);
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Acciones Menu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        BEntradaSalida.setSelected(true);
        BEntradaSalida.setText("E / S");
        BEntradaSalida.setOpaque(false);
        BEntradaSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEntradaSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(BEntradaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        BActualizar.setSelected(true);
        BActualizar.setText("Actualizar Producto");
        BActualizar.setOpaque(false);
        BActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(BActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        BBorrar.setSelected(true);
        BBorrar.setText("Borrar Producto");
        BBorrar.setOpaque(false);
        jPanel1.add(BBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        NewCate.setSelected(true);
        NewCate.setText("Clientes");
        NewCate.setOpaque(false);
        NewCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewCateActionPerformed(evt);
            }
        });
        jPanel1.add(NewCate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        ACompra.setSelected(true);
        ACompra.setText("Nueva Compra");
        jPanel1.add(ACompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        ACarga.setSelected(true);
        ACarga.setText("Cargar Archivo");
        jPanel1.add(ACarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        AReporte.setSelected(true);
        AReporte.setText("Reporte Excell");
        jPanel1.add(AReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        NewPro.setSelected(true);
        NewPro.setText("Roles");
        NewPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewProActionPerformed(evt);
            }
        });
        jPanel1.add(NewPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, -1, -1));

        UPermiso.setSelected(true);
        UPermiso.setText("Permisos");
        jPanel1.add(UPermiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        UBorrar.setSelected(true);
        UBorrar.setText("Borrar");
        jPanel1.add(UBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        UEditar.setSelected(true);
        UEditar.setText("Editar");
        jPanel1.add(UEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        UCrear.setSelected(true);
        UCrear.setText("Crear");
        jPanel1.add(UCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        PCrear.setSelected(true);
        PCrear.setText("Crear");
        jPanel1.add(PCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, -1));

        PEditar.setSelected(true);
        PEditar.setText("Editar");
        jPanel1.add(PEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        PBorrar.setSelected(true);
        PBorrar.setText("Borrar");
        jPanel1.add(PBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, -1));

        RCrear.setSelected(true);
        RCrear.setText("Crear");
        jPanel1.add(RCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, -1));

        REditar.setSelected(true);
        REditar.setText("Editar");
        jPanel1.add(REditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, -1, -1));

        RBorrar.setSelected(true);
        RBorrar.setText("Borrar");
        jPanel1.add(RBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, -1, -1));

        CCrear.setSelected(true);
        CCrear.setText("Crear");
        jPanel1.add(CCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        CEditar.setSelected(true);
        CEditar.setText("Editar");
        jPanel1.add(CEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        CBorrar.setSelected(true);
        CBorrar.setText("Borrar");
        jPanel1.add(CBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

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
            Insert();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        if (jCheckBox13.isSelected()) {
            AReporte.setSelected(true);
            ACompra.setSelected(true);
            ACarga.setSelected(true);
        } else {
            AReporte.setSelected(false);
            ACompra.setSelected(false);
            ACarga.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBox13ActionPerformed

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BActualizarActionPerformed

    private void BodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodegaActionPerformed
        if (Bodega.isSelected()) {
            BEntradaSalida.setSelected(true);
            BBorrar.setSelected(true);
            BActualizar.setSelected(true);
            BReporte.setSelected(true);
        } else {
            BEntradaSalida.setSelected(false);
            BBorrar.setSelected(false);
            BActualizar.setSelected(false);
            BReporte.setSelected(false);
        }
    }//GEN-LAST:event_BodegaActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed
        if (jCheckBox17.isSelected()) {
            UEditar.setSelected(true);
            UBorrar.setSelected(true);
            UCrear.setSelected(true);
            UPermiso.setSelected(true);
        } else {
            UEditar.setSelected(false);
            UBorrar.setSelected(false);
            UCrear.setSelected(false);
            UPermiso.setSelected(false);
        }


    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
        if (jCheckBox14.isSelected()) {
            PCrear.setSelected(true);
            PEditar.setSelected(true);
            PBorrar.setSelected(true);
        } else {
            PCrear.setSelected(false);
            PEditar.setSelected(false);
            PBorrar.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBox14ActionPerformed

    private void BEntradaSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEntradaSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BEntradaSalidaActionPerformed

    private void BReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BReporteActionPerformed

    private void NewProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewProActionPerformed
        if (NewPro.isSelected()) {
            RCrear.setSelected(true);
            RBorrar.setSelected(true);
            REditar.setSelected(true);
        } else {
            RCrear.setSelected(false);
            RBorrar.setSelected(false);
            REditar.setSelected(false);
        }

    }//GEN-LAST:event_NewProActionPerformed

    private void NewCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewCateActionPerformed
        if (NewCate.isSelected()) {
            CBorrar.setSelected(true);
            CCrear.setSelected(true);
            CEditar.setSelected(true);
        } else {
            CBorrar.setSelected(false);
            CCrear.setSelected(false);
            CEditar.setSelected(false);
        }

    }//GEN-LAST:event_NewCateActionPerformed

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
    private javax.swing.JCheckBox ACarga;
    private javax.swing.JCheckBox ACompra;
    private javax.swing.JCheckBox AReporte;
    private javax.swing.JCheckBox BActualizar;
    private javax.swing.JCheckBox BBorrar;
    private javax.swing.JCheckBox BEntradaSalida;
    private javax.swing.JCheckBox BReporte;
    private javax.swing.JCheckBox Bodega;
    private javax.swing.JCheckBox CBorrar;
    private javax.swing.JCheckBox CCrear;
    private javax.swing.JCheckBox CEditar;
    private javax.swing.JCheckBox NewCate;
    private javax.swing.JCheckBox NewPro;
    private javax.swing.JCheckBox PBorrar;
    private javax.swing.JCheckBox PCrear;
    private javax.swing.JCheckBox PEditar;
    private javax.swing.JCheckBox RBorrar;
    private javax.swing.JCheckBox RCrear;
    private javax.swing.JCheckBox REditar;
    private javax.swing.JCheckBox UBorrar;
    private javax.swing.JCheckBox UCrear;
    private javax.swing.JCheckBox UEditar;
    private javax.swing.JCheckBox UPermiso;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
