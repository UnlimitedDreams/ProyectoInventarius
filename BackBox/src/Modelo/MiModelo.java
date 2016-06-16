package Modelo;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gatsu
 */
public class MiModelo extends DefaultTableModel {

    /**
     * Este metodo indica que clase esta en que celda. Es importantisimo.
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return JButton.class;
        } else {
            // si no tienes el jar del JDateChooser solo regresa el JButton.class
            return null;
        }
    }

    @Override
    public int getColumnCount() {
        // la tabla solo tiene 2 columnas
        return 5;
    }

    @Override
    public int getRowCount() {
        // la tabla solo tiene una fila
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 4) {
            // en la primera columna el elemento es un boton
            return new JButton("Borrar");
        }
        // en la otra columna el elemento es un calendario (si no esta el jar solo hay que poner el return del boton sin ningun if).
        return null;

    }

}
