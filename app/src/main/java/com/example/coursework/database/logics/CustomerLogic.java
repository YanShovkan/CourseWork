package com.example.coursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.database.models.CustomerModel;
import com.example.coursework.database.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerLogic {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "customer";
    final String COLUMN_ID = "id";
    final String COLUMN_NAME = "name";
    final String COLUMN_BIRTHDAY = "birthday";
    final String COLUMN_USERID = "userid";

    public CustomerLogic(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public CustomerLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<CustomerModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<CustomerModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            CustomerModel obj = new CustomerModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setBirthday(cursor.getLong((int) cursor.getColumnIndex(COLUMN_BIRTHDAY)));
            obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<CustomerModel> getFilteredList(int userId) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_USERID + " = " + userId, null);
        List<CustomerModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            CustomerModel obj = new CustomerModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setBirthday(cursor.getLong((int) cursor.getColumnIndex(COLUMN_BIRTHDAY)));
            obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public CustomerModel getElement(int id) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + id, null);
        CustomerModel obj = new CustomerModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
        obj.setBirthday(cursor.getLong((int) cursor.getColumnIndex(COLUMN_BIRTHDAY)));
        obj.setUserid(cursor.getInt((int) cursor.getColumnIndex(COLUMN_USERID)));
        return obj;
    }

    public void insert(CustomerModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_BIRTHDAY,model.getBirthday());
        content.put(COLUMN_USERID,model.getUserid());
        db.insert(TABLE,null,content);
    }

    public void update(CustomerModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_BIRTHDAY,model.getBirthday());
        content.put(COLUMN_USERID,model.getUserid());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(int id) {
        String where = COLUMN_ID+" = "+id;
        db.delete(TABLE,where,null);
    }
}
