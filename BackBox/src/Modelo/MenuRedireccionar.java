/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import vw.components.Bodega;
import vw.components.CategoriaGestion;
import vw.components.Clientes;
import vw.components.DatallesPorFecha;
import vw.components.Entrada_Nueva;
import vw.components.Kits;
import vw.components.MaestroIva;
import vw.components.Promociones;
import vw.components.Provedores;
import vw.components.Reporte_Entradas;
import vw.components.Reporte_Ventas;
import vw.components.Roles;
import vw.components.Usuarios;
import vw.components.VentaDiaria;
import vw.dialogs.AcercaDe;
import vw.dialogs.CategoriasRegistrar;
import vw.dialogs.ProveedoresRegistrar;
import vw.dialogs.RegistroCliente;
import vw.dialogs.RolRegistrarF;
import vw.dialogs.UsuariosRegistrar;
import vw.model.Articulo;
import vw.model.Venta;

/**
 *
 * @author Asus
 */
public class MenuRedireccionar {

    private String CadenaMenu;
    private String Usuario;
    private ArrayList<ContenedorMenus> listMenu = new ArrayList();
    private java.awt.Frame parent;
    private int codEmpresa;

    public MenuRedireccionar() {
    }

    public MenuRedireccionar(java.awt.Frame parent, String CadenaMenu, ArrayList acciones,
            String usuario, int codigoEmpresa

    
        ) {
        this.CadenaMenu = CadenaMenu;
        this.listMenu = acciones;
        this.Usuario = usuario;
        this.parent = parent;
        this.codEmpresa = codigoEmpresa;

    }

    public static JMenuItem Atajos(String accion) {
        System.out.println("Entro : " + accion);
        JMenuItem menuItem = new JMenuItem(accion.trim());
        if (accion.trim().equalsIgnoreCase("Bodega")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 10));
        }
        if (accion.trim().equalsIgnoreCase("Usuarios")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Usuario")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Rol")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Rol")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Proveedores")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Proveedor")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Articulos")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 10));
        }
        if (accion.trim().equalsIgnoreCase("Nueva Compra")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Categoria")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Categoria")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Realizar Venta")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Realizar Devolucion")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Venta Diaria")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 10));
        }
        if (accion.trim().equalsIgnoreCase("Reporte Venta")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Reporte Compras")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Reporte Entrada y Salidas")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Clientes")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Clientes")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Iva")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, 10));
        }
        if (accion.trim().equalsIgnoreCase("Promocion")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Promocion")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
        }
        if (accion.trim().equalsIgnoreCase("Kits")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, 10));
        }
        if (accion.trim().equalsIgnoreCase("Crear Kits")) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        }

        return menuItem;
    }

    public void reDireccion() throws IOException, URISyntaxException, ClassNotFoundException, ClassNotFoundException, ClassNotFoundException {
        try {
            if (CadenaMenu.equalsIgnoreCase("Bodega")) {
                new Bodega(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Usuarios")) {
                new Usuarios(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Usuario")) {
                new UsuariosRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Rol")) {
                new Roles(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Rol")) {
                new RolRegistrarF(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Proveedores")) {
                new Provedores(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Proveedor")) {
                new ProveedoresRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Articulos")) {
                new Articulo(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Nueva Compra")) {
                ArrayList<Producto> pr = new ArrayList();
                String fac = "";
                new Entrada_Nueva(pr, Usuario, fac, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Categoria")) {
                new CategoriaGestion(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Categoria")) {
                new CategoriasRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Realizar Venta")) {
                ArrayList<Producto> productos = new ArrayList();
                new Venta(productos, Usuario, 1, listMenu, "1", this.codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Realizar Devolucion")) {
                ArrayList<Producto> productos = new ArrayList();
                new Venta(productos, Usuario, 2, listMenu, "1", this.codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ver Venta Diaria")) {
                new VentaDiaria(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Reporte Venta")) {
                new Reporte_Ventas(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Reporte Compras")) {
                new Reporte_Entradas(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Reporte Entrada y Salidas")) {//Pendiente
                new DatallesPorFecha(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ayuda en Linea")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("BackBox")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("Atajos")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("Acerca De")) {
                new AcercaDe(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Clientes")) {
                new Clientes(Usuario, listMenu, codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Cliente")) {
                RegistroCliente r = null;
                ArrayList<Producto> productos = new ArrayList();
                r = new RegistroCliente(this.parent, true, productos, Usuario, listMenu, 2);
                r.setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Iva")) {
                new MaestroIva(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Promocion")) {
                new Promociones(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Kits")) {
                new Kits(Usuario, listMenu).setVisible(true);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuRedireccionar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCadenaMenu() {
        return CadenaMenu;
    }

    public void setCadenaMenu(String CadenaMenu) {
        this.CadenaMenu = CadenaMenu;
    }

}
