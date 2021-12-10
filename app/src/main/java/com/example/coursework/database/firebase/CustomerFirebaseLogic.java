package com.example.coursework.database.firebase;

import android.content.Context;

import com.example.coursework.database.logics.CustomerLogic;
import com.example.coursework.database.logics.MedicineLogic;
import com.example.coursework.database.models.CustomerModel;
import com.example.coursework.database.models.MedicineModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CustomerFirebaseLogic {
    private final String TABLE = "customer";

    private DatabaseReference database;

    public CustomerFirebaseLogic(){
        database = FirebaseDatabase.getInstance().getReference(TABLE);
    }

    public void syncCustomers(Context context) {
        CustomerLogic logic = new CustomerLogic(context);

        logic.open();
        List<CustomerModel> models = logic.getFullList();
        logic.close();

        database.removeValue();

        for (CustomerModel model: models) {
            database.push().setValue(model);
        }
    }
}
