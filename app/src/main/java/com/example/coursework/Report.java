package com.example.coursework;

import android.os.Environment;
import android.util.DisplayMetrics;


import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Report {

    public void generatePdf() throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/report.pdf";

        PdfWriter writer = new PdfWriter(pdfPath);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        document.close();
    }

}
