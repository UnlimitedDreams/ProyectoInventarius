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


public class Tabla2 implements TableCellRenderer {

    ArrayList<Producto> pro = new ArrayList();
    public String frecuencias[][];
    int nrofreq;

    public Tabla2(ArrayList x) {
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
                frecuencias[2][nrofreq] = ""+pro.get(i).getPrecio_venta();
                frecuencias[3][nrofreq] = "" + pro.get(i).getCantidad();
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
