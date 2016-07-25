package Control;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextField;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerExcel {

    public int columna = 0, IndiceInicio_fila = 0;
    public DecimalFormat formateador = new DecimalFormat("####.####");
    public ArrayList<String> value = new ArrayList();

    public LeerExcel() {
        value = new java.util.ArrayList<String>();
    }

    public boolean isnumero(String x) {
        System.out.println("entro Numero : " + x);
        int a = 0;
        boolean r = false;
        try {
            a = Integer.parseInt(x);
            r = true;
            System.out.println("Valor r : " + r);
            return r;
        } catch (Exception ex) {
            return r;
        }
    }

    public boolean f_datos_1(String archivo, int columna) {        
        File file = new File(archivo);        
        int fecha = 0, ve = 0;
        boolean r = false;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {

                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();

                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }                
                for (int j = 0; j < cellTempList.size(); j++) {
                    System.out.println("--- : " + cellTempList.get(j));

                    try {
                        XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                        String v2 = hssfCell.toString();
                       // System.out.println("ColumnH : " +hssfCell.getColumnIndex());
                        
                        if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {                            
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {                           
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {                            
                            value.add("" + cellTempList.get(j));;
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {
                            System.out.println("" + 4);
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {
                            System.out.println("" + 5);
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {
                            System.out.println("" + 6);
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {
                            System.out.println("" + 7);
                            value.add("" + cellTempList.get(j));
                        } else if (hssfCell.getColumnIndex() == columna && hssfCell.getRowIndex()!=0) {
                            System.out.println("" + 8);
                            value.add("" + cellTempList.get(j));
                        }

                    } catch (Exception e) {
                    }
                }
            }
//            System.out.println(identificaciones+"\n");
//            System.out.println(Valor+"\n");
//             System.out.println("fechas = " +fecha);

        } catch (Exception e) {
//           Entrada.muestreMensajeV("Docuem");
        }

        return r;
    }

    public void restetvalue() {
//        System.out.println("resteeeeeeeeeeeeeeeeeeeeeeeeeet");
        value.clear();
    }

    public String[] Carga() {
//        value.remove(0);
        String mov[] = new String[value.size()];
        String valor = "";
        for (int i = 0; i < value.size(); i++) {
            valor = value.get(i);
            if(valor.isEmpty()==false || valor!=null){
            if (valor.contains(".")) {
                mov[i] = valor.substring(0, (valor.length()-2));
            } else {
                mov[i] = value.get(i);
            }
            }

        }
        restetvalue();
        return mov;
    }

    public static void main(String[] args) throws SQLException {
        String fileName = "C:\\Users\\ASUS_01\\Desktop\\DATOS_EXPORTADOS.xlsx";
//        System.out.println(fileName);
        String movimiento[] = null;
        int costo[] = null;
        String movimiento2[] = null;

        LeerExcel Hoja = new LeerExcel();
//        Hoja.f_datos_1(fileName, 0);
//        movimiento2 = Hoja.Carga();
        Hoja.f_datos_1(fileName, 7);
        movimiento = Hoja.Carga();

        System.out.println(" Mov " + movimiento.length);
        for (String string : movimiento) {
            if(Integer.parseInt(string)<=0){
            System.out.println("- :" + string);
            }
        }

//        System.out.println(" COS " + movimiento2.length);
//
//        for (String string : movimiento2) {
//            System.out.println("- :" + string);
//        }
    }

//    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\admin\\Desktop\\libro1.xlsx");
//        System.out.println(file);
//        FileInputStream fileInputStream = new FileInputStream(file);
//        System.out.println("1");
//        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//
//        System.out.println("2");
//        XSSFSheet sheet = workbook.getSheetAt(0);
//
//        Iterator<Row> rowIterator = sheet.iterator();
//
//        
//        
//        
//        Row row;
//        while (rowIterator.hasNext()) {
//            row = rowIterator.next();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            Cell celda;
//            while (cellIterator.hasNext()) {
//
//                celda = cellIterator.next();
//                switch (celda.getCellType()) {
//                    case Cell.CELL_TYPE_NUMERIC:
//                        if (DateUtil.isCellDateFormatted(celda)) {
//                            System.out.println(celda.getDateCellValue());
//                        } else {
//                            System.out.println(celda.getNumericCellValue());
//                        }
//                        break;
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.println(celda.getStringCellValue());
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        System.out.println(celda.getBooleanCellValue());
//                        break;
//                }
//            }
//        }
//
//    }
}
