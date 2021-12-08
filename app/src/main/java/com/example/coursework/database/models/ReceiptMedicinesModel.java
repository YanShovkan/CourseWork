package com.example.coursework.database.models;

public class ReceiptMedicinesModel {
    private int id;
    private int receiptid;
    private int medicineid;
    private int count;

    public ReceiptMedicinesModel(){

    }

    public ReceiptMedicinesModel(int receiptid, int medicineid, int count){
        this.receiptid = receiptid;
        this.medicineid = medicineid;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(int receiptid) {
        this.receiptid = receiptid;
    }

    public int getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(int medicineid) {
        this.medicineid = medicineid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
