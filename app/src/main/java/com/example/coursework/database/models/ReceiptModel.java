package com.example.coursework.database.models;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ReceiptModel {
    private int id;
    private long receiving_date;
    private long discharge_date;
    private int customerId;
    private Map<Integer, Integer> receiptMedicines = new HashMap<Integer, Integer>();

    ReceiptModel(){

    }

    ReceiptModel(long receiving_date, long discharge_date, int customerId, Map<Integer, Integer> receiptMedicines){
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

    public Map<Integer, Integer> getReceiptMedicines() {
        return receiptMedicines;
    }

    public void setReceiptMedicines(Map<Integer, Integer> receiptMedicines) {
        this.receiptMedicines = receiptMedicines;
    }
}
