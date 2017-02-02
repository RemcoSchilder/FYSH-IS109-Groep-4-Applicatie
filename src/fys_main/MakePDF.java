/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author freek
 */
public class MakePDF {

    public static void createRegistrationPDF(String date, String time, String airport,
            String name, String street, String city,
            String zipcode, String country, String telephone, String email,
            String labelNumber, String flightNumber, String destination,
            String type, String brand, String color, String characteristics) {

        try {
            OutputStream file = new FileOutputStream(new File("registrationPDF/" + labelNumber + ".pdf"));

            //create document
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, file);

            //create image
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("src/logo.jpg");
            image.scalePercent(20);
            image.setAlignment(Element.ALIGN_CENTER);

            //Create Paragraph
            Paragraph title = new Paragraph("Lost Bagage Registration Form", new Font(Font.FontFamily.TIMES_ROMAN, 16,
                    Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);

            //create empty line
            Paragraph emptyLine = new Paragraph(" ");

            //Create a place/time table
            PdfPTable placeTimeTable = new PdfPTable(new float[]{4, 6});
            placeTimeTable.setHeaderRows(1);

            //create cells and add values
            PdfPCell cell = new PdfPCell(new Phrase("Date:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            placeTimeTable.addCell(cell);
            placeTimeTable.addCell(date);

            cell = new PdfPCell(new Phrase("Time:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            placeTimeTable.addCell(cell);
            placeTimeTable.addCell(time);

            cell = new PdfPCell(new Phrase("Airport:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            placeTimeTable.addCell(cell);
            placeTimeTable.addCell(airport);

            //Create a Traveller Table
            PdfPTable travellerTable = new PdfPTable(new float[]{4, 6});
            travellerTable.setHeaderRows(1);

            //create title and add to table
            cell = new PdfPCell(new Phrase("Traveller Information:", new Font(Font.FontFamily.TIMES_ROMAN, 14,
                    Font.BOLDITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            travellerTable.addCell(cell).setBorder(0);

            //create cells and add values
            cell = new PdfPCell(new Phrase("Name:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(name);

            cell = new PdfPCell(new Phrase("Address:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(street);

            cell = new PdfPCell(new Phrase("City:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(city);

            cell = new PdfPCell(new Phrase("Zipcode:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(zipcode);

            cell = new PdfPCell(new Phrase("Country:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(country);

            cell = new PdfPCell(new Phrase("Telephone:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(telephone);

            cell = new PdfPCell(new Phrase("E-mail:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            travellerTable.addCell(cell);
            travellerTable.addCell(email);

            //Create a baggage label information Table
            PdfPTable labelInfoTable = new PdfPTable(new float[]{4, 6});
            labelInfoTable.setHeaderRows(1);

            //create title and add to table
            cell = new PdfPCell(new Phrase("Bagage Lable Information:", new Font(Font.FontFamily.TIMES_ROMAN, 14,
                    Font.BOLDITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            labelInfoTable.addCell(cell).setBorder(0);

            //create cells and add values
            cell = new PdfPCell(new Phrase("Label number:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            labelInfoTable.addCell(cell);
            labelInfoTable.addCell(labelNumber);

            cell = new PdfPCell(new Phrase("Flight number:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            labelInfoTable.addCell(cell);
            labelInfoTable.addCell(flightNumber);

            cell = new PdfPCell(new Phrase("Destination:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            labelInfoTable.addCell(cell);
            labelInfoTable.addCell(destination);

            //Create a baggage label information Table
            PdfPTable baggageInfoTable = new PdfPTable(new float[]{4, 6});
            baggageInfoTable.setHeaderRows(1);

            //create title and add to table
            cell = new PdfPCell(new Phrase("Baggage Information:", new Font(Font.FontFamily.TIMES_ROMAN, 14,
                    Font.BOLDITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            baggageInfoTable.addCell(cell).setBorder(0);

            //create cells and add values
            cell = new PdfPCell(new Phrase("Type:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            baggageInfoTable.addCell(cell);
            baggageInfoTable.addCell(type);

            cell = new PdfPCell(new Phrase("Brand:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            baggageInfoTable.addCell(cell);
            baggageInfoTable.addCell(brand);

            cell = new PdfPCell(new Phrase("Color:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            baggageInfoTable.addCell(cell);
            baggageInfoTable.addCell(color);

            cell = new PdfPCell(new Phrase("Characteristics:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            baggageInfoTable.addCell(cell);
            baggageInfoTable.addCell(characteristics);

            //Create a signatures Table
            PdfPTable signatureTable = new PdfPTable(new float[]{4, 6});
            signatureTable.setHeaderRows(1);
            signatureTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            //create cells and add values
            cell = new PdfPCell(new Phrase("Signature traveller:", new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLDITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setFixedHeight(40f);
            signatureTable.addCell(cell).setBorder(0);
            signatureTable.addCell("");

            cell = new PdfPCell(new Phrase("Signature customer service:", new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLDITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            signatureTable.addCell(cell).setBorder(0);
            signatureTable.addCell("");

            //add all to document
            document.open();

            document.add(image);
            document.add(title);
            document.add(emptyLine);

            document.add(placeTimeTable);
            document.add(emptyLine);

            document.add(travellerTable);
            document.add(emptyLine);

            document.add(labelInfoTable);
            document.add(emptyLine);

            document.add(baggageInfoTable);
            document.add(emptyLine);
            document.add(emptyLine);

            document.add(signatureTable);

            document.close();
            file.close();

            //auto opens file
            String pdfFile = "C:/Program Files (x86)/FYSH-IS109-Groep-4-Applicatie/registrationPDF/" + labelNumber + ".pdf";
            if (pdfFile.endsWith(".pdf")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
            } else {
                //For cross platform use

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void createShippingLabel(String name, String street, 
            String city, String country, String zipcode, 
            String labelNumber) {

        StringBuilder from = new StringBuilder();
        from.append("Corendon");
        from.append("\n");
        from.append("Singaporestraat 82");
        from.append("\n");
        from.append("1175 RA, Leinden, Netherlands");

        StringBuilder to = new StringBuilder();
        to.append(name);
        to.append("\n");
        to.append(street);
        to.append("\n");
        to.append(zipcode + ", " + city + ", " + country);

        try {
            OutputStream file = new FileOutputStream(new File("labelPDF/" + labelNumber + ".pdf"));

            //create document
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            
            Rectangle border = new Rectangle(415, 190);
            border.setBorder(Rectangle.BOX);
            border.setBorderWidth(2);
            border.setBorderColor(BaseColor.BLACK);
            document.setPageSize(border);
            document.setMargins(4, 4, 4, 4);

            //create image
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("src/logo.jpg");
            image.scalePercent(15);

            //Create Paragraph
            Paragraph sender = new Paragraph(from.toString(), new Font(Font.FontFamily.TIMES_ROMAN, 8));
            
            Paragraph emptyLine = new Paragraph(" ");

            Paragraph receiver = new Paragraph(to.toString(), new Font(Font.FontFamily.TIMES_ROMAN, 10));
            receiver.setAlignment(Element.ALIGN_CENTER);

            document.open();

            document.add(image);
            document.add(sender);
            document.add(emptyLine);
            document.add(receiver);

            document.close();

            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
