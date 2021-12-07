package com.example.coursework.database.models;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReceiptModel {
    private int id;
    private long receiving_date;
    private long discharge_date;
    private int customerId;
    private List<ReceiptMedicinesModel> receiptMedicines = new LinkedList<>();

    public ReceiptModel(){

    }

    public ReceiptModel(long receiving_date, long discharge_date, int customerId, List<ReceiptMedicinesModel> receiptMedicines){
        this.receiving_date = receiving_date;
        this.discharge_date = discharge_date;
        this.customerId = customerId;
        this.receiptMedicines = receiptMedicines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(long receiving_date) {
        this.receiving_date = receiving_date;
    }

    public long getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(long discharge_date) {
        this.discharge_date = discharge_date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<ReceiptMedicinesModel> getReceiptMedicines() {
        return receiptMedicines;
    }

    public void setReceiptMedicines(List<ReceiptMedicinesModel> receiptMedicines) {
        this.receiptMedicines = receiptMedicines;
    }
}
