package com.example.coursework.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.coursework.R;
import com.example.coursework.Report;
import com.example.coursework.database.firebase.CustomerFirebaseLogic;
import com.example.coursework.database.firebase.MedicineFirebaseLogic;
import com.example.coursework.database.firebase.ReceiptFirebaseLogic;
import com.example.coursework.database.firebase.ReceiptMedicinesFirebaseLogic;
import com.example.coursework.database.firebase.UserFirebaseLogic;
import com.example.coursework.database.logics.CustomerLogic;
import com.example.coursework.database.logics.ReceiptLogic;
import com.example.coursework.database.logics.UserLogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button_customers;
    Button button_medicines;
    Button button_receipts;
    Button button_report;
    Button button_exit;

    Calendar dateFrom;
    Calendar dateTo;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UserLogic userLogic = new UserLogic(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLogic.open();

        context = this;

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
                    Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        UserFirebaseLogic userFirebaseLogic = new UserFirebaseLogic();
        userFirebaseLogic.syncUsers(this);

        CustomerFirebaseLogic customerFirebaseLogic = new CustomerFirebaseLogic();
        customerFirebaseLogic.syncCustomers(this);

        MedicineFirebaseLogic medicineFirebaseLogic = new MedicineFirebaseLogic();
        medicineFirebaseLogic.syncMedicines(this);

        ReceiptFirebaseLogic receiptFirebaseLogic = new ReceiptFirebaseLogic();
        receiptFirebaseLogic.syncReceipt(this);

        ReceiptMedicinesFirebaseLogic receiptMedicinesFirebaseLogic = new ReceiptMedicinesFirebaseLogic();
        receiptMedicinesFirebaseLogic.syncReceiptMedicines(this);

    }

}