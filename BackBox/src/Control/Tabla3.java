/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import vw.components.Producto;
import java.util.ArrayList;

public class Tabla3 {

    ArrayList<Producto> pro = new ArrayList();
    public String frecuencias[][];
    int nrofreq;

    public Tabla3(ArrayList x) {
        nuevo();
        frecuencias = new String[7][x.size()];
        nrofreq = 0;
        Producto temp = null;
        for (int i = 0; i < x.size(); i++) {
            temp = (Producto) x.get(i);
            pro.add(temp);
        }
    }

    public void calculeFrecuenciasV() {
        Producto temp = null;
        for (int i = 0; i < pro.size(); i++) {
            temp = (Producto) pro.get(i);
            frecuencias[0][nrofreq] = "" + pro.get(i).getCodigo();
            frecuencias[1][nrofreq] = "" + pro.get(i).getNombre();
            frecuencias[2][nrofreq] = "" + pro.get(i).getCosto();
            frecuencias[3][nrofreq] = "" + pro.get(i).getCantidad();
            frecuencias[4][nrofreq] = "" + pro.get(i).getPrecio_venta();
            frecuencias[5][nrofreq] = ""+pro.get(i).getStock();

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
