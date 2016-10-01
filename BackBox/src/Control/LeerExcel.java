package Control;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Esta clase se Encarga un archivo Excel , con unas columnas determinadas, para la
 * carga de productos.
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class LeerExcel {

    public int columna = 0, IndiceInicio_fila = 0;
    public DecimalFormat formateador = new DecimalFormat("####.####");
    public ArrayList<String> value = new ArrayList();

    /**
     * Método que se encargar de inicializar el arreglo de la columna
     */
    public LeerExcel() {
        value = new java.util.ArrayList<String>();
    }

    /**
     * Método que se encargar de cargar, una columna del archivo.
     *
     * @param archivo El parámetro archivo define el archivo del excel.
     * @param columna El parámetro columna define el numero de la columna.
     */

    public void Archivo(String archivo, int columna) {
        System.out.println("--*-****---");
        System.out.println(""+archivo);
        System.out.println(""+columna);
        File file = new File(archivo);
        int fecha = 0, ve = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                System.out.println("1---------------");
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();

                while (iterator.hasNext()) {
                    System.out.println("entonces");
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                for (int j = 0; j < cellTempList.size(); j++) {
                    System.out.println("entro aqui");
                    XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                    String v2 = hssfCell.toString();
                    //Se pone diferente de cero para evitar, las cabeceras del excel
                    if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        System.out.println("cargo");
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));;
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex() != 0) {
                        value.add("" + cellTempList.get(j));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Método que se encargar de limpiar el arreglo
     */
    public void restetvalue() {
        value.clear();
    }

    /**
     * Método que se encargar de revizar los valores decimales, y re
     *
     * @return El número de ítems (números aleatorios) de que consta la serie
     */
    public String[] Carga() {
        String mov[] = new String[value.size()];
        String valor = "";
        for (int i = 0; i < value.size(); i++) {
            valor = value.get(i);
            if (valor.isEmpty() == false || valor != null) {
                if (valor.contains(".")) {
                    mov[i] = valor.substring(0, (valor.length() - 2));
                } else {
                    mov[i] = value.get(i);
                }
            }
        }
        restetvalue();
        return mov;
    }
}
