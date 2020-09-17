package com.qlct.mymoney.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expenditures {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String money;
    private String note;
    private int image;
    private  String nameGroup;
    private  int month;
    private  int day;
    private int year;
    public Expenditures(String money, String note, int image, String nameGroup, int month, int day, int year) {
        this.money = money;
        this.note = note;
       this.image = image;
        this.nameGroup = nameGroup;
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
