package com.example.pdforcsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fileconvertor.CSVMaker;
import com.example.fileconvertor.EmailSender;
import com.example.fileconvertor.PDFMaker;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button main_BTN_CSV,main_BTN_CSVemail,main_BTN_PDF,main_BTN_PDFemail;
    private TextView main_TXT_headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        permission();
        listeners();
    }

    private void init() {
        main_TXT_headline = findViewById(R.id.main_TXT_headline);
        main_BTN_CSV = findViewById(R.id.main_BTN_CSV);
        main_BTN_CSVemail = findViewById(R.id.main_BTN_CSVemail);
        main_BTN_PDF = findViewById(R.id.main_BTN_PDF);
        main_BTN_PDFemail = findViewById(R.id.main_BTN_PDFemail);
    }

    private void permission() {
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
    }

    private void listeners() {
        main_BTN_CSV.setOnClickListener(v -> createCSV());
        main_BTN_PDF.setOnClickListener(v -> createPDF());
        main_BTN_CSVemail.setOnClickListener(v -> sendCSV());
        main_BTN_PDFemail.setOnClickListener(v -> sendPDF());
    }

    private void sendPDF() {
        String fileName = "test";
        String textToMail = "Hello here you PDf in your mail";
        String email = "liortra@gmail.com";
        String subject = "PDForCSV == PDF";
        EmailSender.sendPdfToEmail(this,fileName,textToMail,email,subject);
    }

    private void sendCSV() {
        String fileName = "test";
        String textToMail = "Hello here you CSV in your mail";
        String email = "liortra@gmail.com";
        String subject = "PDForCSV == CSV";
        EmailSender.sendCsvToEmail(this,fileName,textToMail,email,subject);
    }

    private void createPDF() {
        String fileName = "test";
        List<String> list = Arrays.asList("data1","data2","data3","data4");
        PDFMaker.createMyPDF(fileName,list);
    }

    private void createCSV() {
        String fileName = "test";
        String HEADER = "Info";
        List<String> list = Arrays.asList("data1","data2","data3","data4");
        CSVMaker.writeToCsv(fileName,list,HEADER);
    }
}