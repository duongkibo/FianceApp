package com.qlct.mymoney.model;

public class Positions {
    double x;
    double y;

    public Positions() {
    }

    public Positions(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Positions(double x) {
        this.x = x;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
