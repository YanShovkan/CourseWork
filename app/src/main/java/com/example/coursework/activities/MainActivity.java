package com.example.coursework.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursework.R;
import com.example.coursework.Report;
import com.example.coursework.database.logics.UserLogic;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    Button button_customers;
    Button button_medicines;
    Button button_receipts;
    Button button_report;
    Button button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UserLogic userLogic = new UserLogic(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLogic.open();

        button_receipts = findViewById(R.id.button_receipts);
        button_medicines = findViewById(R.id.button_medicines);
        button_customers = findViewById(R.id.button_customers);
        button_report = findViewById(R.id.button_report);
        button_exit = findViewById(R.id.button_exit);

        int userId = getIntent().getExtras().getInt("userId");

        button_receipts.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, ReceiptsActivity.class);
                    startActivity(intent);
                }
        );

        button_medicines.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, MedicinesActivity.class);
                    startActivity(intent);
                }
        );

        button_customers.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, CustomersActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
        );

        button_exit.setOnClickListener(
                v -> {
                    finish();
                    Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                    startActivity(intent);
                }
        );

        button_report.setOnClickListener(
                v -> {
                    Report report = new Report();
                    try {
                        report.generatePdf();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}