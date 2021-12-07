package com.example.coursework.database.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerModel {
    private int id;
    private String name;
    private long birthday;
    private int userid;

    public CustomerModel(String name, long birthday, int userid){
        this.name = name;
        this.birthday = birthday;
        this.userid = userid;
    }

    public CustomerModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
