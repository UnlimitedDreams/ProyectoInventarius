/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Producto;
import Modelo.acciones;
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
public class TablaModel implements TableCellRenderer {

    ArrayList<Producto> pro = new ArrayList();
    ArrayList<acciones> ListAcciones = new ArrayList();
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
        frecuencias = new String[Columnas][x.size()];
        nrofreq = 0;
        if (condicion == 1) {
            this.pro = x;
        } else if (condicion == 2) {
            this.ListAcciones = x;
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
     * Método que se encargar de retorna una matriz para vista de Promociones y Kits,
     * acomodada de acuerdo a unas columnas especificas crear la matriz.
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

    public void nuevo() {
        pro.clear();
    }

    public int getNrofreq() {
        return nrofreq;
    }

    public void setNrofreq(int nrofreq) {
        this.nrofreq = nrofreq;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
