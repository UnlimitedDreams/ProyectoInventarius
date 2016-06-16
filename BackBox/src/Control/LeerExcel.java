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

    public int columna = 0, fila = 0, IndiceInicio_fila = 0;
    public DecimalFormat formateador = new DecimalFormat("####.####");
    public ArrayList<Integer> identificaciones = new ArrayList();
    public ArrayList<Double> Valor = new ArrayList();
    public ArrayList<String> letras = new ArrayList();

    public LeerExcel() {
        identificaciones = new java.util.ArrayList<Integer>();
        Valor = new java.util.ArrayList<Double>();
        letras = new java.util.ArrayList<String>();
    }

    public boolean f_datos_1(String archivo, String mns) {

        File file = new File(archivo);
        System.out.println("entro al metodo");
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

                    try {
                        XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                        String v2 = hssfCell.toString();
                        if (mns.equals("codigo")) {
                            if (v2.equalsIgnoreCase("codigo")) {
                                columna = j;
                                System.out.println("entre codigo");
                            }
                            {
                                String p = "";
                                if (j == columna) {
                                    letras.add(""+hssfCell.getStringCellValue());
                                    p = "";
                                    r = true;
                                }
                            }
                        } else if (mns.equals("nombre")) {

                            if (v2.equalsIgnoreCase("nombre")) {
                                System.out.println("entre nombre");

                                columna = j;
                            }
                            {
                                System.out.println(": " + j + " colum = " + columna );
                                if (j==1 ) {
                                    //System.out.println("entre nombre : " +hssfCell.getStringCellValue());

                                    letras.add(hssfCell.getStringCellValue());
                                    r = true;
                                }
                            }
                        } else if (mns.equals("costo")) {
                            if (v2.equalsIgnoreCase("costo")) {
                                columna = j;
                                System.out.println("entre costo");

                            }
                            {
                                if (j == columna) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }
                        } else if (mns.equals("iva")) {
                            if (v2.equalsIgnoreCase("iva")) {
                                columna = j;
                                System.out.println("entre iva");

                            }
                            {
                                if (j == columna) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }
                        } else if (mns.equals("precio")) {
                            if (v2.equalsIgnoreCase("precio")) {
                                columna = j;
                                System.out.println("entre precio");

                            }
                            {
                                if (j == 4) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }

                        } else if (mns.equals("categoria")) {
                            if (v2.equalsIgnoreCase("categoria")) {
                                columna = j;
                            }
                            {
                                if (j == 5) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }
                        } else if (mns.equals("cantidad")) {
                            if (v2.equalsIgnoreCase("cantidad")) {
                                columna = j;
                            }
                            {
                                if (j == 6) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }
                        } else if (mns.equals("descuento")) {
                            if (v2.equalsIgnoreCase("descuento")) {
                                columna = j;
                            }
                            {
                                if (j == columna) {
                                    identificaciones.add(Integer.parseInt(formateador.format(hssfCell.getNumericCellValue())));
                                    r = true;
                                }
                            }
                        } else if (mns.equals("Valor Total")) {
                            if (v2.equalsIgnoreCase("Valor Total")) {
                                columna = j;
                            }
                            {
                                String p = "";
                                if (columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        } else if (mns.equals("documento")) {
                            if (v2.equalsIgnoreCase("documento")) {
                                columna = j;
                            }
                            {
                                String p = "";
                                if (columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        } else if (mns.equals("documento2")) {
                            if (v2.equalsIgnoreCase("documento")) {
                                columna = j;
                            }
                            {
                                if (columna == j) {
                                    letras.add(hssfCell.getStringCellValue());
                                    r = true;

                                }
                            }
                        } else if (mns.equals("Origen")) {
                            if (v2.equalsIgnoreCase("Origen")) {
                                columna = j;
                            }
                            {
                                if (j >= 4 && columna == j) {
                                    letras.add(hssfCell.getStringCellValue());
                                    r = true;

                                }
                            }
                        } else if (mns.equals("Valor Efectivo")) {
                            if (v2.equalsIgnoreCase("Valor Efectivo")) {
                                columna = j;
                            }
                            {
                                String p = "";
                                if (columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        } else if (mns.equals("D- Debito C- Credito")) {
                            if (v2.equalsIgnoreCase("D- Debito C- Credito")) {
                                columna = j;
                            }
                            String m = "";
                            {
                                if (columna == j) {
                                    System.out.println("VALOR DE C-D " + hssfCell.getStringCellValue());
                                    letras.add(hssfCell.getStringCellValue());
                                    r = true;

                                }
                            }
                        } else if (mns.equals("NÚMERO CHEQUE")) {
                            if (v2.equalsIgnoreCase("NÚMERO CHEQUE")) {
                                columna = j;
                            }
                            {
                                String p = "";
                                if (j > 1 && columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        } else if (mns.equals("Nombre Transaccion")) {

                            if (v2.equalsIgnoreCase("Nombre Transaccion")) {

                                columna = j;
                            }
                            {
                                if (j >= 2 && columna == j) {
                                    letras.add(hssfCell.getStringCellValue());
                                    r = true;
                                }
                            }
                        } else if (mns.equals("Debito")) {

                            if (v2.equalsIgnoreCase("Debito")) {

                                columna = j;
                            }
                            {
                                String p = "";
                                if (columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        } else if (mns.equals("Credito")) {

                            if (v2.equalsIgnoreCase("Credito")) {

                                columna = j;
                            }
                            {
                                String p = "";
                                if (columna == j) {
                                    p = "" + (formateador.format(hssfCell.getNumericCellValue()));
                                    letras.add(p);
                                    p = "";
                                    r = true;

                                }
                            }
                        }

                    } catch (Exception e) {
                    }
                }
                fila++;
            }
//            System.out.println(identificaciones+"\n");
//            System.out.println(Valor+"\n");
//             System.out.println("fechas = " +fecha);

        } catch (Exception e) {
//           Entrada.muestreMensajeV("Docuem");
        }

        return r;
    }

    public int[] haber() {
        int num[] = new int[identificaciones.size()];

        for (int i = 0; i < identificaciones.size(); i++) {
            num[i] = identificaciones.get(i);
        }
        restet();
        return num;
    }

    public int[] Num_moviento() {
        int num[] = new int[identificaciones.size()];
        for (int i = 0; i < identificaciones.size(); i++) {
            num[i] = identificaciones.get(i);
        }
        restet();
        return num;
    }

    public int[] fechas() {
        int num[] = new int[identificaciones.size()];
        for (int i = 0; i < identificaciones.size(); i++) {
            num[i] = identificaciones.get(i);
        }
        restet();
        return num;

    }

    public void restet() {
//        System.out.println("resteeeeeeeeeeeeeeeeeeeeeeeeeet");
        identificaciones.clear();
    }

    public void restetletras() {
//        System.out.println("resteeeeeeeeeeeeeeeeeeeeeeeeeet");
        letras.clear();
    }

    public void resetvalor() {
//        System.out.println("resteeeeeeeeeeeeeeeeeeeeeeeeeet");
        Valor.clear();
    }

    public String[] tipo_movimiento() {
        String mov[] = new String[letras.size()];
        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
        }
        restetletras();
        return mov;
    }

    public String[] Estado() {
        String mov[] = new String[letras.size()];

        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
        }
        restetletras();
        return mov;
    }

    public String[] Nombres() {
        String mov[] = new String[letras.size()];
        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
        }
        restetletras();
        return mov;
    }

    public double[] valor() {
        double val[] = new double[Valor.size()];

        for (int i = 0; i < Valor.size(); i++) {
            val[i] = Valor.get(i);
//            if (i == 788) {
////                System.out.println("el valor es  " + Valor.get(i));
//            }
        }
        resetvalor();
        return val;
    }

    public String[] saldo() {
        String mov[] = new String[letras.size()];

        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
        }
        restetletras();
        return mov;
    }

    public String[] debe() {
        String mov[] = new String[letras.size()];

        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
            if (i >= 789 && i <= 793) {
                System.out.println("el numero es " + mov[i]);
            }
        }
        restetletras();
        return mov;
    }

    public String[] contabi() {
        String mov[] = new String[letras.size()];

        for (int i = 0; i < letras.size(); i++) {
            mov[i] = letras.get(i);
        }
        restetletras();
        return mov;
    }

    public String invertir(String texto) {
        String texto2 = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            char aux = texto.charAt(i);
            texto2 += String.valueOf(aux);

        }
        return texto2;

    }

    public String imprimirNum(int x[]) {
        String r = "";
//        System.out.println("tamaño " + x.length);

        for (int i = 0; i < x.length; i++) {
            r = r + x[i] + "\n";
//            System.out.println(x[i]);
        }
//        System.out.println("------------------------------------------------");
        return r;
    }

    public String imprimirLetras(String x[]) {
        String r = "";
//        System.out.println("tamaño " + x.length);
        for (int i = 0; i < x.length; i++) {
            r = r + x[i] + "\n";
//            System.out.println(x[i]);
        }
//        System.out.println("------------------------------------------------");

        return r;
    }

    public String imprimir2(double x[]) {
        String r = "";
        System.out.println("tamaño " + x.length);

        for (int i = 0; i < x.length; i++) {
            r = r + x[i] + "\n";
        }
        System.out.println("------------------------------------------------");

        return r;
    }

    public String imprimirprueba() {
        String r = "";
        System.out.println("tamaño " + letras.size());
        try {
            for (int i = 0; i < letras.size(); i++) {
                r = r + letras.get(i) + "\n";
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
        System.out.println("------------------------------------------------");

        return r;
    }

    public boolean validarBancoAv(String x) {
        String fileName = x;
        boolean r = false;
        String a = "NÚMERO MOVIMIENTO";
        r = f_datos_1(x, a);
        System.out.println(r);
        return r;
    }

    public static void main(String[] args) throws SQLException {
        String fileName = "C:\\Users\\Family\\Desktop\\libro.xlsx";
//        System.out.println(fileName);
        String movimiento[] = null;;
        int costo[] = null;
        int iva[] = null;
        int precio[] = null;
        int categoria[] = null;
        int cantidad[] = null;
        int descuento[] = null;
        String nombres[] = null;
//        String estado[] = null;
//        String conta[] = null;
//        String saldo[] = null;
//        String haber[] = null;
//        String Cheque[] = null;
//        String prueba[] = null;
        LeerExcel Hoja = new LeerExcel();
        Hoja.f_datos_1(fileName, "codigo");
        movimiento = Hoja.Nombres();
        Hoja.f_datos_1(fileName, "nombre");
        nombres = Hoja.Nombres();
        Hoja.f_datos_1(fileName, "costo");
        costo = Hoja.Num_moviento();
        Hoja.f_datos_1(fileName, "iva");
        iva = Hoja.Num_moviento();
        Hoja.f_datos_1(fileName, "precio");
        precio = Hoja.Num_moviento();
        Hoja.f_datos_1(fileName, "categoria");
        categoria = Hoja.Num_moviento();
        Hoja.f_datos_1(fileName, "cantidad");
        cantidad = Hoja.Num_moviento();
        Hoja.f_datos_1(fileName, "descuento");
        descuento = Hoja.Num_moviento();
        System.out.println(" Mov " + movimiento.length);
        System.out.println(" NOM " + nombres.length);
        System.out.println(" COS " + costo.length);
        System.out.println(" IVA " + iva.length);
        System.out.println(" PRE " + precio.length);
        System.out.println(" CAT " + categoria.length);
        System.out.println(" CAN " + cantidad.length);
        System.out.println(" DES " + descuento.length);

//        System.out.println(" fech "+fechas.length);
//        System.out.println(" hab "+haber.length);
//        System.out.println("Nom "+nombres.length);
//        System.out.println("Est "+estado.length);
//        System.out.println("Con "+conta.length);
//        System.out.println("deb "+debe.length);
//        System.out.println("sal "+saldo.length);
//        System.out.println("tipos "+ tipos.length);
//        System.out.println(" che "+Cheque.length);
        System.out.println(Hoja.imprimirLetras(movimiento));
        System.out.println(Hoja.imprimirNum(costo));
//        System.out.println(Hoja.imprimirLetras(haber));
        System.out.println(Hoja.imprimirLetras(nombres));
//        System.out.println(Hoja.imprimirLetras(estado));
//        System.out.println(Hoja.imprimirLetras(conta));
//        System.out.println(Hoja.imprimirLetras(debe));
//        System.out.println(Hoja.imprimirLetras(saldo));
//        System.out.println(Hoja.imprimirLetras(tipos));
//        System.out.println(fileName);
//        ArrayList nue = null;
//        ReadExcel1 Hoja = new ReadExcel1();
//        int fechas2[] = null;
//        String debe2[] = null;
//        String tipos2[] = null;
//        String docu[] = null;
//        String ori[] = null;
//        String Credi[] = null;
//        String c[] = null;
//        Hoja.f_datos_1(fileName, "FECHA MOVIMIENTO");
//        fechas2 = Hoja.fechas();
//        Hoja.f_datos_1(fileName, "Valor Efectivo");
//        debe2 = Hoja.debe();
//        Hoja.f_datos_1(fileName, "Nombre Transaccion");
//        tipos2 = Hoja.tipo_movimiento();
//        Hoja.f_datos_1(fileName, "documento");
//        docu = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "Debito");
//        ori = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "Credito");
//        Credi = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "NÚMERO CHEQUE");
//        c = Hoja.Nombres();
//        System.out.println("docu " + docu.length);
//        System.out.println("fecha" + fechas2.length);
//        System.out.println("tipo " + tipos2.length);
//        System.out.println("debe " + debe2.length);
//        System.out.println("che " + c.length);
////        Compilar C = new Compilar();
//////       prueba=C.compilarLetras3(debe);
//////       C.imprimir(prueba);
//        if(Cheque.length<=movimiento.length){
//                  System.out.println("mayor");
//            for(int i=0;i<movimiento.length;i++){
//                Cheque[i]=0;
////                System.out.println(Cheque[i]);
//            }
//        }
//        C.llenar3(movimiento, tipos, nombres, estado, conta, fechas, debe, haber, saldo, Cheque);
//        ReadExcel Hoja2 = new ReadExcel();
//        System.out.println("Entro");
//        int fechas2[] = null;
//        String debe2[] = null;
//        String tipos2[] = null;
//        String docu[] = null;
//        String ori[] = null;
//        String Credi[] = null;
//        String c[] = null;
//        Hoja.f_datos_1(fileName, "FECHA MOVIMIENTO");
//        fechas2 = Hoja.fechas();
//        Hoja.f_datos_1(fileName, "Valor Efectivo");
//        debe2 = Hoja.debe();
//        Hoja.f_datos_1(fileName, "Nombre Transaccion");
//        tipos2 = Hoja.tipo_movimiento();
//        Hoja.f_datos_1(fileName, "documento");
//        docu = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "Debito");
//        ori = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "Credito");
//        Credi = Hoja.Nombres();
//        Hoja.f_datos_1(fileName, "NÚMERO CHEQUE");
//        c = Hoja.Nombres();
//        System.out.println("docu " + docu.length);
//        System.out.println("fecha" + fechas2.length);
//        System.out.println("tipo " + tipos2.length);
//        System.out.println("debe " + debe2.length);
//        System.out.println("debito " + ori.length);
//        System.out.println("credito" + Credi.length);
//        System.out.println("che " + c.length);
//        C.llenar(fechas2, debe2, tipos2, docu, ori, Credi, c);
//        System.out.println(Hoja.imprimirLetras(docu));
//        System.out.println(Hoja.imprimirNum(fechas2));
//        System.out.println(Hoja.imprimirLetras(tipos2));
//        System.out.println(Hoja.imprimirLetras(c));
//        C.llena(fechas2, debe2, tipos2, docu, ori, c);
    }
}
