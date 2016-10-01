/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Modelo.Producto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Reporte {

    public static void main(String[] juan) throws DocumentException, IOException {
        ArrayList<Producto> listPro = new ArrayList();
        listPro.add(new Producto("100", "Pedal de algojjjjjjjjjjjjjjjjjjj", 5000, 2, 2000, (int) 0.0));
        listPro.add(new Producto("76373736363", "Pedal de kkk", 5000, 2, 2000, (int) 0.0));
        Reporte.ReciboCajaRegistroTiquete("1234567785142", listPro, 10000, 11500, "1200", 0);
    }

    public static Document ReciboCajaRegistroTiquete(String factura, ArrayList<Producto> x, double ValorNeto, double ValorTotal, String ValorIva, double desc) throws DocumentException, IOException {
        SimpleDateFormat format2 = new SimpleDateFormat("dd/M/yyyy H:m:s");
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        String codFacturaFinal = "";
        String ceros = "";
        if (factura.length() < 13) {
            for (int i = 0; i < (13 - (int) factura.length()); i++) {
                ceros = ceros + '0';
            }
        }
        codFacturaFinal=ceros+""+factura;
        System.out.println("- " + ceros);

        Image img;
        Date fecha = new Date();

        Rectangle r = new Rectangle(240, 990);//Tamaño de la hoja
        Document document = new Document(r);

        PdfWriter pdfw = PdfWriter.getInstance(document, new FileOutputStream("prueba.pdf"));
        document.setMargins(1, 1, 1, 1);
        document.setMarginMirroring(true);
        // step 3
        document.open();
        Font titulo = new Font(Font.FontFamily.TIMES_ROMAN, 15.0f, Font.NORMAL);
        Font Subtitulo = new Font(Font.FontFamily.TIMES_ROMAN, 12.0f, Font.NORMAL);
        Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.NORMAL);
        Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 11.0f, Font.NORMAL);
        Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD);

        Paragraph P1 = new Paragraph("RECIBO DE CAJA", titulo);
        P1.setAlignment(Element.ALIGN_CENTER);

        Paragraph P2 = new Paragraph("Afiliacion Palmira S.A", Subtitulo);
        P2.setAlignment(Element.ALIGN_CENTER);

        Paragraph P3 = new Paragraph("Factura : " + factura, f2);
        P3.setAlignment(Element.ALIGN_LEFT);

        Paragraph P4 = new Paragraph("Fecha : " + format2.format(fecha), f2);
        P4.setAlignment(Element.ALIGN_LEFT);

        document.add(P1);
        document.add(P2);
        document.add(new Paragraph("\t"));
        document.add(P3);
        document.add(P4);
        document.add(new Paragraph("\t"));

        LineSeparator ls = new LineSeparator();

        PdfPTable table = new PdfPTable(5);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        float[] columnWidths = {3f, 1.2f, 0.7f, 1f,1.5f};

        table.setWidths(columnWidths);
//        table.c
//        table.addCell(new Paragraph("#    Nombre                     ", f2));
////        table.addCell("Nombre");
//        table.addCell("Precio");        
        table.addCell(new Paragraph("# Nombre", f2));
        table.addCell(new Paragraph("Valor", f2));
        table.addCell(new Paragraph("(%)", f2));
        table.addCell(new Paragraph("Cant", f2));
        table.addCell(new Paragraph("Total", f2));
        document.add(table);
        document.add(ls);

        //--------------------------
        PdfPTable table2 = new PdfPTable(5);
        table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table2.setWidthPercentage(100);
        table2.setWidths(columnWidths);

        for (Producto x1 : x) {
            table2.addCell(new Paragraph(x1.getCodigo() + "\n" + x1.getNombre(), f1));
            table2.addCell(new Paragraph("" + formateador.format(x1.getPrecio_final()), f1));
            table2.addCell(new Paragraph("" + x1.getDesc(), f1));
            table2.addCell(new Paragraph("" + x1.getCantidad(), f1));
            table2.addCell(new Paragraph("" + formateador.format((int) (x1.getCantidad() * x1.getPrecio_final())), f1));
        }

//
        document.add(table2);
        document.add(new Paragraph("\t"));

        PdfPTable table3 = new PdfPTable(2);
        table3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table3.setWidthPercentage(50);

        table3.addCell(new Paragraph("Valor Neto :", f2));
        table3.addCell(new Paragraph("" + formateador.format(ValorNeto), f2));

        table3.addCell(new Paragraph("Descuento :", f2));
        table3.addCell(new Paragraph("" + formateador.format(desc), f2));

        table3.addCell(new Paragraph("Iva :", f2));
        table3.addCell(new Paragraph("" + ValorIva, f2));

        table3.addCell(new Paragraph("TOTAL :", f3));
        table3.addCell(new Paragraph("" + formateador.format(ValorTotal), f3));

        document.add(table3);

        document.add(new Paragraph("\t"));
        document.add(new Paragraph("\t"));
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode(codFacturaFinal);
        img = codeEAN.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);

        document.add(new Phrase(new Chunk(img, 60, 0)));
//        document.add(img);

        document.close();

        System.out.println("creo bien");
        File path = new File("prueba.pdf");
        Desktop.getDesktop().open(path);
//
//        Desktop.getDesktop().print(path);
        return document;
    }

}
