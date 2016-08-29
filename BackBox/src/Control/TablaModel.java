/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Component;
import Modelo.Producto;
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
    public TablaModel(ArrayList x, int Columnas) {
        nuevo();
        frecuencias = new String[Columnas][x.size()];
        nrofreq = 0;
        this.pro = x;
//        Producto temp = null;
//        for (int i = 0; i < x.size(); i++) {
//            temp = (Producto) x.get(i);
//            pro.add(temp);
//        }
    }

    public void calculeFrecuenciasV() {
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

    public void calculeFrecuenciasPromocion() {
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

    public void calculeFrecuenciasKits() {
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
     * Método que se encargar de cargar la matriz en cada posicion. de acuerdo a
     * la ventana CargaArchivo
     *
     */
    public void DatosCargaArchivo() {
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
     * Método que se encargar de cargar la matriz en cada posicion. de acuerdo a
     * la ventana Entrada Nueva
     *
     */
    public void DatosEntradaNueva() {
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
