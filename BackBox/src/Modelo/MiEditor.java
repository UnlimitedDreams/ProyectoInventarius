package Modelo;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


/**
* @author gatsu
*/
public class MiEditor extends AbstractCellEditor implements TableCellEditor {


   private Boolean currentValue;

   @Override
   public Object getCellEditorValue()
   {
       return currentValue;
   }

   //El editor usara el propio componente. Para que funcione la celda en el modelo debe ser editable.
   @Override
   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
   {


       return (JComponent) value;// la tabla solo debe tener componentes graficos

   }
}
