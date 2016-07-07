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
import vw.components.Bodega;
import vw.components.CategoriaGestion;
import vw.components.Clientes;
import vw.components.DatallesPorFecha;
import vw.components.Entrada_Nueva;
import vw.components.Producto;
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
import vw.dialogs.RolRegistrar;
import vw.dialogs.UsuariosRegistrar;
import vw.main.Acceder;
import vw.main.Menu;
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

    public MenuRedireccionar(java.awt.Frame parent, String CadenaMenu, ArrayList acciones, 
            String usuario,int codigoEmpresa) {
        this.CadenaMenu = CadenaMenu;
        this.listMenu = acciones;
        this.Usuario = usuario;
        this.parent = parent;
        this.codEmpresa=codigoEmpresa;

    }

    public void reDireccion() throws IOException, URISyntaxException, ClassNotFoundException, ClassNotFoundException, ClassNotFoundException {
        try {
            if (CadenaMenu.equalsIgnoreCase("Lista Bodega")) {
                new Bodega(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Lista Usuarios")) {
                new Usuarios(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Usuario")) {
                new UsuariosRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Lista Rol")) {
                new Roles(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Rol")) {
                new RolRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Lista Proveedores")) {
                new Provedores(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Proveedor")) {
                new ProveedoresRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ver Articulos")) {
                new Articulo(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Nueva Compra")) {
                ArrayList<Producto> pr = new ArrayList();
                String fac = "";
                new Entrada_Nueva(pr, Usuario, fac, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ver Categoria")) {
                new CategoriaGestion(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Categoria ")) {
                new CategoriasRegistrar(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Realizar Venta")) {
                ArrayList<Producto> productos = new ArrayList();
                new Venta(productos, Usuario, 1, listMenu, "1",this.codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Realizar Devolucion")) {
                ArrayList<Producto> productos = new ArrayList();
                new Venta(productos, Usuario, 2, listMenu, "1",this.codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ver Venta Diaria")) {
                new VentaDiaria(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Venta")) {
                new Reporte_Ventas(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Compras")) {
                new Reporte_Entradas(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Entrada y Salidas")) {//Pendiente
                new DatallesPorFecha(Usuario, listMenu).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Ayuda en Linea")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("BackBox")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("Atajos")) {
                Desktop.getDesktop().browse(new URI("http://www.qmanager.com.co"));
            } else if (CadenaMenu.equalsIgnoreCase("Acerca De")) {
                new AcercaDe(this.parent, true).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Lista Clientes")) {
                new Clientes(Usuario, listMenu,codEmpresa).setVisible(true);
            } else if (CadenaMenu.equalsIgnoreCase("Crear Cliente")) {
                RegistroCliente r = null;
                ArrayList<Producto> productos = new ArrayList();
                r = new RegistroCliente(this.parent, true, productos, Usuario, listMenu,2);
                r.setVisible(true);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuRedireccionar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
