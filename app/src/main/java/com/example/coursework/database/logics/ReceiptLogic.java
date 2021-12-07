package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.CustomerModel;
import com.example.coursework.database.models.ReceiptModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiptLogic {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "receipt";
    final String COLUMN_ID = "id";
    final String COLUMN_RECEIVINGDATE = "receiving_date";
    final String COLUMN_DISHARGEDATE = "discharge_date";
    final String COLUMN_CUSTOMERID = "customerid";

    public ReceiptLogic(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
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

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setReceiving_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_RECEIVINGDATE)));
            obj.setDischarge_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DISHARGEDATE)));
            obj.setCustomerId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_CUSTOMERID)));

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
        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setReceiving_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_RECEIVINGDATE)));
        obj.setDischarge_date(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DISHARGEDATE)));
        obj.setCustomerId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_CUSTOMERID)));
        return obj;
    }

    public void insert(ReceiptModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_RECEIVINGDATE,model.getReceiving_date());
        content.put(COLUMN_DISHARGEDATE,model.getDischarge_date());
        content.put(COLUMN_CUSTOMERID,model.getCustomerId());
        db.insert(TABLE,null,content);
    }

    public void update(ReceiptModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_RECEIVINGDATE,model.getReceiving_date());
        content.put(COLUMN_DISHARGEDATE,model.getDischarge_date());
        content.put(COLUMN_CUSTOMERID,model.getCustomerId());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
    }
}
