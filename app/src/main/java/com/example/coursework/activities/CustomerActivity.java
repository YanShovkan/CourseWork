package com.example.coursework.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursework.R;
import com.example.coursework.database.logics.CustomerLogic;
import com.example.coursework.database.models.CustomerModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomerActivity extends AppCompatActivity {

    Button button_create;
    Button button_cancel;
    EditText edit_text_name;
    DatePicker date_picker_birthday;
    CustomerLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        int userId = getIntent().getExtras().getInt("userId");
        int id = getIntent().getExtras().getInt("id");

        logic = new CustomerLogic(this);

        button_create = findViewById(R.id.button_create);
        button_cancel = findViewById(R.id.button_cancel);
        edit_text_name = findViewById(R.id.edit_text_name);
        date_picker_birthday = findViewById(R.id.date_picker_birthday);

        if (id != 0){
            logic.open();
            CustomerModel model = logic.getElement(id);
            logic.close();

            edit_text_name.setText(model.getName());
            Date date = new Date(model.getBirthday());
            int year = date.getYear() + 1900;
            int month = date.getMonth();
            int day = date.getDate();
            date_picker_birthday.init(year, month, day,null );
        }

        button_create.setOnClickListener(
                v -> {
                    Calendar date = new GregorianCalendar();
                    date.set( date_picker_birthday.getYear(), date_picker_birthday.getMonth(), date_picker_birthday.getDayOfMonth());
                    CustomerModel model = new CustomerModel(edit_text_name.getText().toString(),date.getTime().getTime(), userId);

                    logic.open();

                    if(id != 0){
                        model.setId(id);
                        logic.update(model);
                    } else {
                        logic.insert(model);
                    }

                    logic.close();
                    this.finish();
                }
        );

        button_cancel.setOnClickListener(
                v -> this.finish()
        );
    }
}