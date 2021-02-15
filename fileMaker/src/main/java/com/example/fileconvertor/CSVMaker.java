package com.example.fileconvertor;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVMaker {
    public static void writeToCsv(String fileName, List<String> data, String HEADER) {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MyFiles" + File.separator + "CSVs");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }

        File file = new File(folder.getPath() + File.separator + fileName + ".csv");
        Log.d("TAG", "writeToCsv: " + file.toString());
        PrintWriter writer = null;
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            // https://en.wikipedia.org/wiki/Byte_order_mark
            // https://stackoverflow.com/a/4192897/7147289
            os.write(239);
            os.write(187);
            os.write(191);

            writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
//            String HEADER = "Key,Workplace,StartTime,EndTime,Salary,Comments";
            writer.print(HEADER);

            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.setLength(0);
            for (String info : data) {
                stringBuilder.append("\n");
                stringBuilder.append(info);
            }
            writer.print(stringBuilder.toString());

            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
