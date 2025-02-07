
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import static com.itextpdf.kernel.pdf.PdfName.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

public class PrintPdfExample {


 /*   public static void main(String[] args) throws Exception {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter("hello-pdf.pdf"));
             Document document = new Document(pdfDoc)) {
            document.add(new Paragraph("Hello PDF!"));
        }
    }*/


    public void createTable(String[] args) {
        String destiny = "table.pdf"; // Destination file path

        try {
            PdfWriter writer = new PdfWriter(destiny);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Create a table with 3 columns
            Table table = new Table(3);
            table.addCell(new Cell().add(new Paragraph("Header 1")));
            table.addCell(new Cell().add(new Paragraph("Header 2")));
            table.addCell(new Cell().add(new Paragraph("Header 3")));

            // Add some data
            for (int i = 1; i <= 5; i++) {
                table.addCell(new Cell().add( new Paragraph("Row " + i + ", Col 1")));
                table.addCell(new Cell().add(new Paragraph("Row " + i + ", Col 2")));
                table.addCell(new Cell().add(new Paragraph("Row " + i + ", Col 3")));
            }

            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();
            System.out.println("PDF with table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




/* Cuando haya que escribir
            String dest = "/Users/sixto/Downloads/sample.pdf";
            PdfWriter writer = new PdfWriter(dest);


            document.close();
            pdfDoc.addNewPage();

            // Creating an Area Break
            AreaBreak aB = new AreaBreak();

            // Adding area break to the PDF
            document.add(aB);


             String para1 = "Tutorials Point originated from the idea that there exists
      a class of readers who respond better to online content and prefer to learn
      new skills at their own pace from the comforts of their drawing rooms.";

      String para2 = "The journey commenced with a single tutorial on HTML in 2006
      and elated by the response it generated, we worked our way to adding fresh
      tutorials to our repository which now proudly flaunts a wealth of tutorials
      and allied articles on topics ranging from programming languages to web designing
      to academics and much more.";

      // Creating Paragraphs
      Paragraph paragraph1 = new Paragraph(para1);
      Paragraph paragraph2 = new Paragraph(para2);

      // Adding paragraphs to document
      document.add(paragraph1);
      document.add(paragraph2);

       // Creating a table
      float [] pointColumnWidths = {150F, 150F, 150F};
      Table table = new Table(pointColumnWidths);

      // Adding cells to the table
      table.addCell(new Cell().add("Name"));
      table.addCell(new Cell().add("Raju"));
      table.addCell(new Cell().add("Id"));
      table.addCell(new Cell().add("1001"));
      table.addCell(new Cell().add("Designation"));
      table.addCell(new Cell().add("Programmer"));

      // Adding Table to document
      doc.add(table);
      */

//https://www.tutorialspoint.com/itext/itext_creating_pdf_document.htm
//https://www.tutorialspoint.com/itext/itext_formatting_cell_contents.htm



/*
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PrintPdfExample {
    public static void main(String[] args) {
        try {
            // Specify the PDF file to print
            String filePath = "example.pdf";
            FileInputStream fis = new FileInputStream(filePath);

            // Create a Doc object
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);

            // Find a print service
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            if (printService != null) {
                // Create a print job
                DocPrintJob printJob = printService.createPrintJob();
                // Print the document
                printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
                System.out.println("Printing...");
            } else {
                System.out.println("No printer found.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
}*/
