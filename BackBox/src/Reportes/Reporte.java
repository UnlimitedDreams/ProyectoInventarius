/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

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
import com.lowagie.text.pdf.PdfCell;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Reporte {

    public static void main(String[] juan) throws DocumentException, IOException {
        Reporte.ReciboCajaRegistroTiquete();
    }

    public static Document ReciboCajaRegistroTiquete() throws DocumentException, IOException {
        SimpleDateFormat format2 = new SimpleDateFormat("dd/M/yyyy H:m:s");
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
        Image img;
        Date fecha = new Date();

        Rectangle r = new Rectangle(224, 990);//Tamaño de la hoja
        Document document = new Document(r);

        PdfWriter pdfw = PdfWriter.getInstance(document, new FileOutputStream("prueba.pdf"));
        document.setMargins(10, 10, 10, 10);
        document.setMarginMirroring(true);
        // step 3
        document.open();
        Font titulo = new Font(Font.FontFamily.TIMES_ROMAN, 13.0f, Font.NORMAL);
        Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.NORMAL);

        Paragraph P1 = new Paragraph("RECIBO DE CAJA", titulo);
        P1.setAlignment(Element.ALIGN_CENTER);

        Paragraph P2 = new Paragraph("Afiliacion Palmira S.A", f2);
        P2.setAlignment(Element.ALIGN_CENTER);

        Paragraph P3 = new Paragraph("Factura : 1010", f2);
        P2.setAlignment(Element.ALIGN_LEFT);

        Paragraph P4 = new Paragraph("Fecha : " + format2.format(fecha), f2);
        P2.setAlignment(Element.ALIGN_LEFT);

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
        float[] columnWidths = {2f, 1f, 1f, 1f, 1f};

        table.setWidths(columnWidths);
//        table.c
//        table.addCell(new Paragraph("#    Nombre                     ", f2));
////        table.addCell("Nombre");
//        table.addCell("Precio");        
        table.addCell(new Paragraph("# Nombre", f2));
        table.addCell(new Paragraph("Valor", f2));
        table.addCell(new Paragraph("Desc", f2));
        table.addCell(new Paragraph("Cant", f2));
        table.addCell(new Paragraph("Total", f2));
        document.add(table);
        document.add(ls);

        PdfPTable table2 = new PdfPTable(5);
        table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table2.setWidthPercentage(100);

        table2.setWidths(columnWidths);
        table2.addCell(new Paragraph("100 ", f2));
        table2.addCell(new Paragraph("Valor", f2));
        table2.addCell(new Paragraph("Desc", f2));
        table2.addCell(new Paragraph("Cant", f2));
        table2.addCell(new Paragraph("Total", f2));

//
        document.add(table2);
        document.add(new Paragraph("\t"));
        document.add(new Paragraph("\t"));
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode("9781935182610");
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
