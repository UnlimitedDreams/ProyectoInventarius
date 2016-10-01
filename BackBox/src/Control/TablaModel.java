/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.List_Object;
import Modelo.Producto;
import Modelo.acciones;
import Modelo.seccion;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Esta clase se encarga de crear la matriz , para una tabla
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class TablaModel {

    ArrayList<Producto> pro = new ArrayList();
    ArrayList<acciones> ListAcciones = new ArrayList();
    ArrayList<List_Object> ListObject = new ArrayList();
    ArrayList<seccion> ListSeccion = new ArrayList();
    public String frecuencias[][];
    int nrofreq;

    /**
     * Método que se encargar de recibir el arrayList y la cantidad de columnas
     * para la matriz.
     *
     * @param x El parámetro x define los registros del arreglo producto.
     * @param Columnas El parámetro columna define la cantidad de columnas para
     * crear la matriz.
     */
    public TablaModel(ArrayList x, int Columnas, int condicion) {
        nuevo();
        if (condicion != 4) {
            frecuencias = new String[Columnas][x.size()];
            nrofreq = 0;
        }

        if (condicion == 1) {
            this.pro = x;
        } else if (condicion == 2) {
            this.ListAcciones = x;
        } else if (condicion == 3) {
            this.ListObject = x;
        } else if (condicion == 4) {
            this.ListSeccion = x;
            seccion temp;
            acciones temp2;
            for (int i = 0; i < ListSeccion.size(); i++) {
                temp = (seccion) ListSeccion.get(i);
                if (temp.getEstado().equalsIgnoreCase("Activo")) {
                    for (int k = 0; k < ListSeccion.get(i).getList_acciones().size(); k++) {
                        temp2 = (acciones) ListSeccion.get(i).getList_acciones().get(k);
                        if (temp2.getCod_actividad() == temp.getPosicion()) {
                            ListAcciones.add(temp2);
                        }
                    }
                }
            }
            System.out.println("tamañooooo : " + ListAcciones.size());
            frecuencias = new String[Columnas][ListAcciones.size()];
            nrofreq = 0;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Venta,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void modelVenta() {
        Producto temp = null;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getPrecio_venta();
            frecuencias[3][nrofreq] = "" + pro.get(i).getCantidad();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Acciones,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModeloAcciones() {
        acciones temp = null;
        for (int i = 0; i < ListAcciones.size(); i++) {
            temp = (acciones) ListAcciones.get(i);
            frecuencias[0][nrofreq] = "" + ListAcciones.get(i).getAccion();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Promociones y
     * Kits, acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelPromocionYkits() {
        Producto temp = null;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getPrecio_venta();
            frecuencias[3][nrofreq] = "" + pro.get(i).getDesc();
            frecuencias[4][nrofreq] = "" + pro.get(i).getCosto();
            frecuencias[5][nrofreq] = "" + pro.get(i).getPrecio_final();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelKit() {
        Producto temp = null;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getCantidad();
            frecuencias[3][nrofreq] = "" + pro.get(i).getCantidadKit();
            frecuencias[4][nrofreq] = "" + pro.get(i).getPrecio_venta();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelCargaArchivo() {
        Producto temp = null;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getCosto();
            frecuencias[3][nrofreq] = "" + pro.get(i).getIva();
            frecuencias[4][nrofreq] = "" + pro.get(i).getPrecio_venta();
            frecuencias[5][nrofreq] = "" + pro.get(i).getCantidad();
            frecuencias[6][nrofreq] = "" + pro.get(i).getEsta();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelMostrarCarga() {
        Producto temp = null;
        String iva = "";
        int ivaFinal = 0;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);

            if (pro.get(i).getIva() > 0) {
                iva = "" + pro.get(i).getIva();
            }
            if (iva.contains(".")) {
                ivaFinal = Integer.parseInt(iva.substring(0, (iva.length() - 2)));
            } else if (iva.equalsIgnoreCase("")) {
                ivaFinal = 0;
            } else {
                ivaFinal = Integer.parseInt(iva);
            }
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getCosto();
            frecuencias[3][nrofreq] = "" + pro.get(i).getPrecio_venta();
            frecuencias[4][nrofreq] = "" + ivaFinal;
            frecuencias[5][nrofreq] = "" + pro.get(i).getCantidad();
            frecuencias[6][nrofreq] = "" + pro.get(i).getStock();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelMaestroItem() {
        List_Object temp = null;
        String iva = "";
        int ivaFinal = 0;
        for (int i = 0; i < ListObject.size(); i++) {
            temp = (List_Object) ListObject.get(i);
            frecuencias[0][nrofreq] = "" + ListObject.get(i).getCod();
            frecuencias[1][nrofreq] = "" + ListObject.get(i).getNom();
            nrofreq++;
        }
    }

    /**
     * Método que se encargar de retorna una matriz para vista de Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
     */
    public void ModelMaestroItem2() {
        acciones temp = null;
        String iva = "";
        int ivaFinal = 0;
        for (int i = 0; i < ListAcciones.size(); i++) {
            temp = (acciones) ListAcciones.get(i);
            frecuencias[0][nrofreq] = "" + ListAcciones.get(i).getCod_accion();
            frecuencias[1][nrofreq] = "" + ListAcciones.get(i).getAccion();
            nrofreq++;
        }
    }

    public void nuevo() {
        pro.clear();
    }

    public int getNrofreq() {
        return nrofreq;
    }

    public void setNrofreq(int nrofreq) {
        this.nrofreq = nrofreq;
    }

}
