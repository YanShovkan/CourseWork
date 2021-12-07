package com.example.coursework.database.models;

public class MedicineModel {
    private int id;
    private String name;
    private String type;
    private float price_per_pakage;

    public MedicineModel(String name, String type, float price_per_pakage){
        this.name = name;
        this.type = type;
        this.price_per_pakage = price_per_pakage;
    }

    public MedicineModel(){

    }

    public float getPrice_per_pakage() {
        return price_per_pakage;
    }

    public void setPrice_per_pakage(float price_per_pakage) {
        this.price_per_pakage = price_per_pakage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
