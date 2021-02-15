package com.example.fileconvertor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;

public class EmailSender {

    public static void sendCsvToEmail(Context activityContext, String fileName, String textToMail,String email, String subject) {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MyFiles" + File.separator + "CSVs");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        File file = new File(folder.getPath() + File.separator + fileName + ".csv");

        Uri csvUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            csvUri = FileProvider.getUriForFile(activityContext, activityContext.getPackageName() + ".provider", file);
        } else {
            //doesn't work in API 24+
            csvUri  =   Uri.fromFile(file);
        }


        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, csvUri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT   , textToMail);
        emailIntent.putExtra(Intent.EXTRA_CC, new String[] {""});
        emailIntent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
        emailIntent.setType("text/html");
        activityContext.startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    public static void sendPdfToEmail(Context activityContext, String fileName, String textToMail,String email, String subject) {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MyFiles" + File.separator + "PDFs");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        File myFile = new File(folder.getPath() + File.separator + fileName + ".pdf");

        Uri csvUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            csvUri = FileProvider.getUriForFile(activityContext, activityContext.getPackageName() + ".provider", myFile);
        } else {
            //doesn't work in API 24+
            csvUri  =   Uri.fromFile(myFile);
        }

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, csvUri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT   , textToMail);
        emailIntent.putExtra(Intent.EXTRA_CC, new String[] {""});
        emailIntent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
        emailIntent.setType("text/html");
        activityContext.startActivity(Intent.createChooser(emailIntent, "Send email"));
    }
}
