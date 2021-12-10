package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.CustomerModel;
import com.example.coursework.database.models.ReceiptMedicinesModel;
import com.example.coursework.database.models.ReceiptModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiptLogic {
    Context context;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "receipt";
    final String COLUMN_ID = "id";
    final String COLUMN_RECEIVING_DATE = "receiving_date";
    final String COLUMN_DISHARGE_DATE = "discharge_date";
    final String COLUMN_CUSTOMER_ID = "customerid";

    public ReceiptLogic(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        this.context = context;
    }

    public ReceiptLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<ReceiptModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ReceiptModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ReceiptModel obj = new ReceiptModel();
            int id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.setId(id);
            obj.setReceiving_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_RECEIVING_DATE)));
            obj.setDischarge_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DISHARGE_DATE)));
            obj.setCustomerId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));

            ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
            receiptMedicinesLogic.open();
            obj.setReceiptMedicines(receiptMedicinesLogic.getFilteredList(id));
            receiptMedicinesLogic.close();
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<ReceiptModel> getFilteredList(long dateFrom, long dateTo) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_RECEIVING_DATE + " > " + dateFrom + " and " + COLUMN_RECEIVING_DATE + " < " + dateTo, null);
        List<ReceiptModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ReceiptModel obj = new ReceiptModel();
            int id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.setId(id);
            obj.setReceiving_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_RECEIVING_DATE)));
            obj.setDischarge_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DISHARGE_DATE)));
            obj.setCustomerId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));

            ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
            receiptMedicinesLogic.open();
            obj.setReceiptMedicines(receiptMedicinesLogic.getFilteredList(id));
            receiptMedicinesLogic.close();
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public ReceiptModel getElement(int id) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + id, null);
        ReceiptModel obj = new ReceiptModel();
        if (!cursor.moveToFirst()) {
            return null;
        }

        obj.setId(id);
        obj.setReceiving_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_RECEIVING_DATE)));
        obj.setDischarge_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DISHARGE_DATE)));
        obj.setCustomerId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_CUSTOMER_ID)));

        ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
        receiptMedicinesLogic.open();
        obj.setReceiptMedicines(receiptMedicinesLogic.getFilteredList(id));
        receiptMedicinesLogic.close();

        return obj;
    }

    public void insert(ReceiptModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_RECEIVING_DATE,model.getReceiving_date());
        content.put(COLUMN_DISHARGE_DATE,model.getDischarge_date());
        content.put(COLUMN_CUSTOMER_ID,model.getCustomerId());

        db.insert(TABLE,null,content);

        ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
        receiptMedicinesLogic.open();
        for(ReceiptMedicinesModel receiptMedicinesModel : model.getReceiptMedicines()){
            receiptMedicinesLogic.insert(receiptMedicinesModel);
        }
        receiptMedicinesLogic.close();
    }

    public void update(ReceiptModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_RECEIVING_DATE,model.getReceiving_date());
        content.put(COLUMN_DISHARGE_DATE,model.getDischarge_date());
        content.put(COLUMN_CUSTOMER_ID,model.getCustomerId());
        String where = COLUMN_ID + " = " + model.getId();

        db.update(TABLE,content,where,null);

        ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
        receiptMedicinesLogic.open();
        receiptMedicinesLogic.deleteByReceiptId(model.getId());
        for(ReceiptMedicinesModel receiptMedicinesModel : model.getReceiptMedicines()){
            receiptMedicinesLogic.insert(receiptMedicinesModel);
        }
        receiptMedicinesLogic.close();
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
        ReceiptMedicinesLogic receiptMedicinesLogic = new ReceiptMedicinesLogic(context);
        receiptMedicinesLogic.deleteByReceiptId(id);
    }

    public void deleteByCustomerId(int customerId) {
        String where = COLUMN_CUSTOMER_ID+" = "+customerId;
        db.delete(TABLE,where,null);
    }
}
