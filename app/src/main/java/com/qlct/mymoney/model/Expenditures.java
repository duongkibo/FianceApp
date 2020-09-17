package com.qlct.mymoney.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Expenditures extends RealmObject {
    @PrimaryKey
    private int id;
    private long money;
    private String note;
    private  String Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
