package com.qlct.mymoney.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDitures {
    @PrimaryKey(autoGenerate =  true)
    private int id;
    private String username;
    private int wallet;
    private String password;

    public UserDitures(String username, int wallet, String password) {
        this.username = username;
        this.wallet = wallet;
        this.password = password;
    }

    public UserDitures() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
