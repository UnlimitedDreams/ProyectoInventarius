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
        for (seccion object : listaSeccion) {
            if (object.getCod_seccion() == 1) {
                bodega.setSelected(true);
            } else if (object.getCod_seccion() == 2) {
                proveedores.setSelected(true);
            }
        }
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
            Control.ejecuteQuery("select cod_usuario from usuario where cedula=" + ced);
            int cod_usu = 0;
            while (Control.rs.next()) {
                cod_usu = Control.rs.getInt(1);
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
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Bodega','BodegaStock'," + ced + ")");
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

                if (RCrear.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','RolCrear'," + ced + ")");
                    codigoAct++;
                }
                if (REditar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','RolEditar'," + ced + ")");
                    codigoAct++;
                }
                if (RBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Roles','Rolborrar'," + ced + ")");
                    codigoAct++;
                }
                if (CCrear.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliCrear'," + ced + ")");
                    codigoAct++;
                }
                if (CEditar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliEditar'," + ced + ")");
                    codigoAct++;
                }
                if (CBorrar.isSelected()) {
                    Control.ejecuteUpdate("insert into permisos values(" + codigoAct + ",'Cliente','CliBorrar'," + ced + ")");
                    codigoAct++;
                }

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
        jPanel1 = new javax.swing.JPanel();
        guardar = new javax.swing.JButton();
        vovler = new javax.swing.JButton();
        articulos = new javax.swing.JCheckBox();
        proveedores = new javax.swing.JCheckBox();
        Usuarios = new javax.swing.JCheckBox();
        roles = new javax.swing.JCheckBox();
        bodega = new javax.swing.JCheckBox();
        panelBodega = new javax.swing.JPanel();
        BReporte = new javax.swing.JCheckBox();
        BActualizar = new javax.swing.JCheckBox();
        BBorrar = new javax.swing.JCheckBox();
        BEntradaSalida = new javax.swing.JCheckBox();
        panelArticulos = new javax.swing.JPanel();
        AReporte = new javax.swing.JCheckBox();
        ACarga = new javax.swing.JCheckBox();
        ACompra = new javax.swing.JCheckBox();
        panelRoles = new javax.swing.JPanel();
        RBorrar = new javax.swing.JCheckBox();
        REditar = new javax.swing.JCheckBox();
        RCrear = new javax.swing.JCheckBox();
        panelProveedores = new javax.swing.JPanel();
        PCrear = new javax.swing.JCheckBox();
        PEditar = new javax.swing.JCheckBox();
        PBorrar = new javax.swing.JCheckBox();
        panelClientes = new javax.swing.JPanel();
        CBorrar = new javax.swing.JCheckBox();
        CEditar = new javax.swing.JCheckBox();
        CCrear = new javax.swing.JCheckBox();
        panelUsuarios = new javax.swing.JPanel();
        UPermiso = new javax.swing.JCheckBox();
        UBorrar = new javax.swing.JCheckBox();
        UEditar = new javax.swing.JCheckBox();
        UCrear = new javax.swing.JCheckBox();
        Clientes = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustar Permisos de usuario - BackBox");
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setForeground(java.awt.SystemColor.controlHighlight);

        guardar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-mdpi/ic_save_black_24dp.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setBorder(null);
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setPreferredSize(new java.awt.Dimension(55, 47));
        guardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        vovler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drawable-xhdpi/ic_arrow_back_black_24dp.png"))); // NOI18N
        vovler.setBorder(null);
        vovler.setBorderPainted(false);
        vovler.setContentAreaFilled(false);
        vovler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vovler.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vovler.setPreferredSize(new java.awt.Dimension(55, 47));
        vovler.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        vovler.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vovler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vovlerActionPerformed(evt);
            }
        });

        articulos.setBackground(new java.awt.Color(255, 255, 255));
        articulos.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        articulos.setSelected(true);
        articulos.setText("Articulos");
        articulos.setOpaque(false);
        articulos.setPreferredSize(new java.awt.Dimension(170, 29));
        articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                articulosActionPerformed(evt);
            }
        });

        proveedores.setBackground(new java.awt.Color(255, 255, 255));
        proveedores.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        proveedores.setSelected(true);
        proveedores.setText("Proveedores");
        proveedores.setOpaque(false);
        proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresActionPerformed(evt);
            }
        });

        Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        Usuarios.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        Usuarios.setSelected(true);
        Usuarios.setText("Usuarios");
        Usuarios.setOpaque(false);
        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });

        roles.setBackground(java.awt.Color.white);
        roles.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        roles.setSelected(true);
        roles.setText("Roles");
        roles.setOpaque(false);
        roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolesActionPerformed(evt);
            }
        });

        bodega.setBackground(new java.awt.Color(255, 255, 255));
        bodega.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        bodega.setSelected(true);
        bodega.setText("Bodega");
        bodega.setOpaque(false);
        bodega.setPreferredSize(new java.awt.Dimension(170, 29));
        bodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bodegaActionPerformed(evt);
            }
        });

        panelBodega.setBackground(java.awt.Color.white);

        BReporte.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        BReporte.setSelected(true);
        BReporte.setText("Stock");
        BReporte.setOpaque(false);

        BActualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        BActualizar.setSelected(true);
        BActualizar.setText("Actualizar Producto");

        BBorrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        BBorrar.setSelected(true);
        BBorrar.setText("Borrar Producto");

        BEntradaSalida.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        BEntradaSalida.setSelected(true);
        BEntradaSalida.setText("E / S");

        javax.swing.GroupLayout panelBodegaLayout = new javax.swing.GroupLayout(panelBodega);
        panelBodega.setLayout(panelBodegaLayout);
        panelBodegaLayout.setHorizontalGroup(
            panelBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BEntradaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBodegaLayout.setVerticalGroup(
            panelBodegaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBodegaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BEntradaSalida)
                .addGap(18, 18, 18)
                .addComponent(BBorrar)
                .addGap(18, 18, 18)
                .addComponent(BActualizar)
                .addGap(18, 18, 18)
                .addComponent(BReporte)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panelArticulos.setBackground(java.awt.Color.white);

        AReporte.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        AReporte.setSelected(true);
        AReporte.setText("Reporte MS Excel");

        ACarga.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        ACarga.setSelected(true);
        ACarga.setText("Cargar Archivo");

        ACompra.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        ACompra.setSelected(true);
        ACompra.setText("Nueva Compra");

        javax.swing.GroupLayout panelArticulosLayout = new javax.swing.GroupLayout(panelArticulos);
        panelArticulos.setLayout(panelArticulosLayout);
        panelArticulosLayout.setHorizontalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ACompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ACarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelArticulosLayout.setVerticalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ACompra)
                .addGap(18, 18, 18)
                .addComponent(ACarga)
                .addGap(18, 18, 18)
                .addComponent(AReporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRoles.setBackground(java.awt.Color.white);

        RBorrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        RBorrar.setSelected(true);
        RBorrar.setText("Borrar");

        REditar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        REditar.setSelected(true);
        REditar.setText("Editar");

        RCrear.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        RCrear.setSelected(true);
        RCrear.setText("Crear");

        javax.swing.GroupLayout panelRolesLayout = new javax.swing.GroupLayout(panelRoles);
        panelRoles.setLayout(panelRolesLayout);
        panelRolesLayout.setHorizontalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(REditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRolesLayout.setVerticalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRolesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(RCrear)
                .addGap(19, 19, 19)
                .addComponent(REditar)
                .addGap(18, 18, 18)
                .addComponent(RBorrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelProveedores.setBackground(java.awt.Color.white);

        PCrear.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        PCrear.setSelected(true);
        PCrear.setText("Crear");

        PEditar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        PEditar.setSelected(true);
        PEditar.setText("Editar");

        PBorrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        PBorrar.setSelected(true);
        PBorrar.setText("Borrar");

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PCrear)
                .addGap(18, 18, 18)
                .addComponent(PEditar)
                .addGap(18, 18, 18)
                .addComponent(PBorrar)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        panelClientes.setBackground(java.awt.Color.white);
        panelClientes.setPreferredSize(new java.awt.Dimension(63, 180));

        CBorrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        CBorrar.setSelected(true);
        CBorrar.setText("Borrar");

        CEditar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        CEditar.setSelected(true);
        CEditar.setText("Editar");

        CCrear.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        CCrear.setSelected(true);
        CCrear.setText("Crear");

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(CCrear)
                .addGap(19, 19, 19)
                .addComponent(CEditar)
                .addGap(18, 18, 18)
                .addComponent(CBorrar)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelUsuarios.setBackground(java.awt.Color.white);

        UPermiso.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        UPermiso.setSelected(true);
        UPermiso.setText("Permisos");

        UBorrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        UBorrar.setSelected(true);
        UBorrar.setText("Borrar");

        UEditar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        UEditar.setSelected(true);
        UEditar.setText("Editar");

        UCrear.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        UCrear.setSelected(true);
        UCrear.setText("Crear");

        javax.swing.GroupLayout panelUsuariosLayout = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(panelUsuariosLayout);
        panelUsuariosLayout.setHorizontalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UPermiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelUsuariosLayout.setVerticalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UCrear)
                .addGap(18, 18, 18)
                .addComponent(UEditar)
                .addGap(18, 18, 18)
                .addComponent(UBorrar)
                .addGap(14, 14, 14)
                .addComponent(UPermiso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Clientes.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        Clientes.setSelected(true);
        Clientes.setText("Clientes");
        Clientes.setOpaque(false);
        Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(panelBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bodega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(Clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelArticulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(articulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelUsuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(Usuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roles, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vovler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roles))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRoles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelArticulos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBodega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Clientes)
                    .addComponent(Usuarios)
                    .addComponent(proveedores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vovler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            Insert();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void vovlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vovlerActionPerformed
        this.dispose();
    }//GEN-LAST:event_vovlerActionPerformed

    private void articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articulosActionPerformed
        if (articulos.isSelected()) {
            panelArticulos.setVisible(true);
            AReporte.setSelected(true);
            ACompra.setSelected(true);
            ACarga.setSelected(true);
        } else {
            panelArticulos.setVisible(false);
            AReporte.setSelected(false);
            ACompra.setSelected(false);
            ACarga.setSelected(false);
        }

    }//GEN-LAST:event_articulosActionPerformed

    private void bodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bodegaActionPerformed
        if (bodega.isSelected()) {
            panelBodega.setVisible(true);
            BEntradaSalida.setSelected(true);
            BBorrar.setSelected(true);
            BActualizar.setSelected(true);
            BReporte.setSelected(true);
        } else {
            panelBodega.setVisible(false);
            BEntradaSalida.setSelected(false);
            BBorrar.setSelected(false);
            BActualizar.setSelected(false);
            BReporte.setSelected(false);
        }
    }//GEN-LAST:event_bodegaActionPerformed

    private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
        if (Usuarios.isSelected()) {
            panelUsuarios.setVisible(true);
            UEditar.setSelected(true);
            UBorrar.setSelected(true);
            UCrear.setSelected(true);
            UPermiso.setSelected(true);
        } else {
            panelUsuarios.setVisible(false);
            UEditar.setSelected(false);
            UBorrar.setSelected(false);
            UCrear.setSelected(false);
            UPermiso.setSelected(false);
        }


    }//GEN-LAST:event_UsuariosActionPerformed

    private void proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresActionPerformed
        if (proveedores.isSelected()) {
            panelProveedores.setVisible(true);
            PCrear.setSelected(true);
            PEditar.setSelected(true);
            PBorrar.setSelected(true);
        } else {
            panelProveedores.setVisible(false);
            PCrear.setSelected(false);
            PEditar.setSelected(false);
            PBorrar.setSelected(false);
        }

    }//GEN-LAST:event_proveedoresActionPerformed

    private void rolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolesActionPerformed
        if (roles.isSelected()) {
            panelRoles.setVisible(true);
            RCrear.setSelected(true);
            RBorrar.setSelected(true);
            REditar.setSelected(true);
        } else {
            panelRoles.setVisible(false);
            RCrear.setSelected(false);
            RBorrar.setSelected(false);
            REditar.setSelected(false);
        }

    }//GEN-LAST:event_rolesActionPerformed

    private void ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesActionPerformed
        if (Clientes.isSelected()) {
            panelClientes.setVisible(true);
            CBorrar.setSelected(true);
            CCrear.setSelected(true);
            CEditar.setSelected(true);
        } else {
            panelClientes.setVisible(false);
            CBorrar.setSelected(false);
            CCrear.setSelected(false);
            CEditar.setSelected(false);
        }

    }//GEN-LAST:event_ClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ACarga;
    private javax.swing.JCheckBox ACompra;
    private javax.swing.JCheckBox AReporte;
    private javax.swing.JCheckBox BActualizar;
    private javax.swing.JCheckBox BBorrar;
    private javax.swing.JCheckBox BEntradaSalida;
    private javax.swing.JCheckBox BReporte;
    private javax.swing.JCheckBox CBorrar;
    private javax.swing.JCheckBox CCrear;
    private javax.swing.JCheckBox CEditar;
    private javax.swing.JCheckBox Clientes;
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
    private javax.swing.JCheckBox Usuarios;
    private javax.swing.JCheckBox articulos;
    private javax.swing.JCheckBox bodega;
    private javax.swing.JButton guardar;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelArticulos;
    private javax.swing.JPanel panelBodega;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelRoles;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JCheckBox proveedores;
    private javax.swing.JCheckBox roles;
    private javax.swing.JButton vovler;
    // End of variables declaration//GEN-END:variables
}
