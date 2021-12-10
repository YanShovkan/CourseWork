package com.example.coursework;

import android.content.Context;
import android.os.Environment;
import com.example.coursework.database.models.CustomerModel;
import com.example.coursework.database.models.ReceiptModel;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {

    public void generatePdf(List<ReceiptModel> receipts, Date dateFrom, Date dateTo) throws IOException {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
        File file = new File(pdfPath, "report.pdf");
        PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph("Report on the number of prescribed receipts for the period from " + dateFrom.getDate() + " / " + dateFrom.getMonth() + " / " + (dateFrom.getYear()+ 1900) + " to " + dateTo.getDate() + " / " + dateTo.getMonth() + " / " + (dateTo.getYear()+ 1900));

        document.add(paragraph);

        float columnWidth[] = { 200, 200 };
        Table table = new Table(columnWidth);

        Map<Integer, Integer> customerReceipts = new HashMap<>();

        for(ReceiptModel receipt : receipts){
            if(customerReceipts.containsKey(receipt.getCustomerId())){
                customerReceipts.put(receipt.getCustomerId(), customerReceipts.get(receipt.getCustomerId()) + 1);
            }
            else{
                customerReceipts.put(receipt.getCustomerId(), 1);
            }
        }

        for(Integer customerId : customerReceipts.keySet()) {
            table.addCell("Customer № " + customerId);
            table.addCell(String.valueOf(customerReceipts.get(customerId)));
        }

        document.add(table);
        document.close();
    }

}
