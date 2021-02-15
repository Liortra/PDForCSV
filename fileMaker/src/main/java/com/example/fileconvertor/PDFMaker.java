package com.example.fileconvertor;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PDFMaker {

    public static void createMyPDF(String fileName, List<String> list){
        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250,400,1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        int x = 10, y=25;
        Canvas canvas = myPage.getCanvas();
        for (String data:list) {
            canvas.drawText(data,x,y,myPaint);
            y+=myPaint.descent()-myPaint.ascent();
        }
        myPdfDocument.finishPage(myPage);

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MyFiles" + File.separator + "PDFs");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        File myFile = new File(folder.getPath() + File.separator + fileName + ".pdf");
        Log.d("TAG", "createMyPDF: " + myFile.toString());
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
            myPdfDocument.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("TAG", "createMyPDF: " + e.toString());
        }
        myPdfDocument.close();
    }
}
