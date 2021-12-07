package com.example.coursework.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.coursework.R;
import com.example.coursework.database.logics.CustomerLogic;
import com.example.coursework.database.models.CustomerModel;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class CustomersActivity extends AppCompatActivity {

    TableRow selectedRow;

    Button button_create;
    Button button_update;
    Button button_delete;
    CustomerLogic logic;
    int userId;

    @Override
    public void onResume() {
        super.onResume();
        logic.open();
        fillTable(Arrays.asList("Имя", "Дата рождения"), logic.getFilteredList(userId));
        logic.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        button_create = findViewById(R.id.button_create);
        button_update = findViewById(R.id.button_update);
        button_delete = findViewById(R.id.button_delete);

        logic = new CustomerLogic(this);

        userId = getIntent().getExtras().getInt("userId");

        logic.open();
        fillTable(Arrays.asList("Имя", "Дата рождения"), logic.getFilteredList(userId));
        logic.close();

        button_create.setOnClickListener(
                v -> {
                    Intent intent = new Intent(CustomersActivity.this, CustomerActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("id", 0);
                    startActivity(intent);
                }
        );

        button_update.setOnClickListener(
                v -> {
                    Intent intent = new Intent(CustomersActivity.this, CustomerActivity.class);
                    intent.putExtra("userId", userId);
                    TextView textView = (TextView) selectedRow.getChildAt(2);
                    intent.putExtra("id", Integer.valueOf(textView.getText().toString()));
                    startActivity(intent);
                }
        );

        button_delete.setOnClickListener(
                v -> {
                    logic.open();
                    TextView textView = (TextView) selectedRow.getChildAt(2);
                    logic.delete(Integer.parseInt(textView.getText().toString()));
                    fillTable(Arrays.asList("Имя", "Дата рождения"), logic.getFilteredList(userId));
                    logic.close();
                });
        }

    void fillTable(List<String> titles, List<CustomerModel> customers) {

        TableLayout tableLayoutCustomers = findViewById(R.id.tableLayoutCustomers);

        tableLayoutCustomers.removeAllViews();

        TableRow tableRowTitles = new TableRow(this);

        for (String title : titles) {
            TextView textView = new TextView(this);

            textView.setTextSize(16);
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth( (int)(getWindowManager().getDefaultDisplay().getWidth() / 2.2));
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutCustomers.addView(tableRowTitles);


        for (CustomerModel customer : customers) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(customer.getName());
            textViewName.setHeight(100);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewBirthday = new TextView(this);
            textViewName.setHeight(100);
            textViewBirthday.setTextSize(16);
            textViewBirthday.setText(String.valueOf(new Date(customer.getBirthday())));
            textViewBirthday.setTextColor(Color.WHITE);
            textViewBirthday.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(customer.getId()));

            tableRow.addView(textViewName);
            tableRow.addView(textViewBirthday);
            tableRow.addView(textViewId);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(v -> {

                selectedRow = tableRow;

                for(int i = 0; i < tableLayoutCustomers.getChildCount(); i++){
                    View view = tableLayoutCustomers.getChildAt(i);
                    if (view instanceof TableRow){
                        view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    }
                }

                tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            });

            tableLayoutCustomers.addView(tableRow);
        }
    }
}