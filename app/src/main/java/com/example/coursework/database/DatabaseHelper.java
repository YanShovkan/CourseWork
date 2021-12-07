package com.example.coursework.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "budteBolny.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS user (\n" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    login character(100) NOT NULL,\n" +
                "    password character(100) NOT NULL);\n");

        db.execSQL("CREATE TABLE customer (\n" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    name character(100) NOT NULL,\n" +
                "    birthday long NOT NULL,\n" +
                "    userid integer NOT NULL,\n" +
                "    CONSTRAINT userfk FOREIGN KEY (userid)\n" +
                "    REFERENCES user(id) ON DELETE CASCADE);");

        db.execSQL("CREATE TABLE receipt (" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    receiving_date long NOT NULL,\n" +
                "    discharge_date long NOT NULL,\n" +
                "    customerid integer NOT NULL,\n" +
                "    CONSTRAINT customerfk FOREIGN KEY (customerid)\n" +
                "    REFERENCES customer(id) ON DELETE CASCADE);");

        db.execSQL("CREATE TABLE medicine (" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    name character(100) NOT NULL,\n" +
                "    type character(100) NOT NULL,\n" +
                "    price_per_pakage real NOT NULL);");

        db.execSQL("CREATE TABLE receipt_medicines (" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    receiptid integer NOT NULL,\n" +
                "    medicineid integer NOT NULL,\n" +
                "    count integer NOT NULL,\n" +
                "    CONSTRAINT receiptfk FOREIGN KEY (receiptid)\n" +
                "    REFERENCES receipt(id) ON DELETE CASCADE,\n"  +
                "    CONSTRAINT medicinefk FOREIGN KEY (medicineid)\n" +
                "    REFERENCES medicine(id) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
