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
}