package com.cris.pdf.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFGeneratorService {

	public void export(HttpServletResponse response) throws IOException, IOException {
		Document document = new Document(PageSize.A4.rotate());
		
		
		try {
			PdfWriter.getInstance(document, response.getOutputStream());

 
            document.open();
            Paragraph p1 = new Paragraph(new Chunk("OvniVinos. \n Barrio Centenario manzana C casa 13 \n Tel. 310-211-1965 \n Armenia-Quindio \n OvniVinos@gmail.com",FontFactory.getFont(FontFactory.HELVETICA, 8)));
            p1.add(new Phrase("                              Ovni_Vinos",FontFactory.getFont(FontFactory.TIMES_BOLD, 32)));
            

            p1.setSpacingAfter((float) 80.0);
            
            document.add(p1);
            /**
            Paragraph p2 = new Paragraph("",FontFactory.getFont(FontFactory.HELVETICA, 12));
            p2.setSpacingBefore((float) 25.0);
            p2.setAlignment(Paragraph.ALIGN_MIDDLE);
            p2.add("                                                     Cliente: NameCliente                                                       Fecha: 2022-08-10\n");
            p2.add("                                                     Direccion: DireccionCliente                                                Hora: 14:44      \n");
            p2.add("                                                     Telefono: TelefonoCliente                                                                   \n");
            document.add(p2);
            
            Paragraph p3 = new Paragraph("",FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
            p3.setSpacingBefore((float) 25.0);
            p3.setAlignment(Paragraph.ALIGN_MIDDLE);
            p3.add("                                                     ITEM |      Nombre     | Cantidad | Total \n");
            int contador = 1;
            //FOR EACH DE ITEMS VENTA
            p3.add("                                                     "+contador + " Crema de whisky  2    46.000 \n");
            p3.add("                                                     "+contador + " Vino Abocado     1    13.000 \n");
            p3.add("                                                     "+contador + " Vino Tinto       1    13.000 \n");
            document.add(p3);
            
            Paragraph p4 = new Paragraph("",FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
            p4.setSpacingBefore((float) 20.0);
            p4.setAlignment(Paragraph.ALIGN_CENTER);
            p4.add("                                                          Total Bruto");
            p4.add("                                                          $Valor...");
            
             */
            
            String[] bogusData = { "M0065920", "SL", "FR86000P", "PCGOLD"};
            int NumColumns = 4;

            PdfPTable datatable = new PdfPTable(NumColumns);
            int[] headerwidths = {15, 45, 15, 25}; // percentage
            datatable.setWidths(headerwidths);
            datatable.setWidthPercentage(100); // percentage
            datatable.getDefaultCell().setPadding(3);
            datatable.getDefaultCell().setBorderWidth(2);
            datatable.getDefaultCell().setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            datatable.addCell("ITEM #");
            datatable.addCell("PRODUCTO");
            datatable.addCell("CANTIDAD");
            datatable.addCell("TOTAL");

            datatable.setHeaderRows(1); // this is the end of the table header

            datatable.getDefaultCell().setBorderWidth(1);
            int contador = 1;
            for (int i = 1; i < 4; i++) {
                if (i % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(0.9f);
                }
                for (int x = 0; x < NumColumns; x++) {
                    
                    if(bogusData[x].equals("M0065920")) {
                    	datatable.addCell(contador + "");
                    	contador = contador +1;
                    }else {
                    	datatable.addCell(bogusData[x]);
                    }
                    
                }
                if (i % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(1);
                }
            }
            document.add(datatable);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        // step 5: we close the document
        document.close();
	}
}
