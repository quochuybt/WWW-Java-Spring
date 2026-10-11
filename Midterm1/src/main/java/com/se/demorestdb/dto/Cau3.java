package com.se.demorestdb.dto;

public class Cau3 {
    private int maDm;
    private String tenDm;
    private int soluong;

    public Cau3(int maDm, String tenDm, int soluong) {
        this.maDm = maDm;
        this.tenDm = tenDm;
        this.soluong = soluong;
    }

    public Cau3() {
    }

    public int getMaDm() {
        return maDm;
    }

    public void setMaDm(int maDm) {
        this.maDm = maDm;
    }

    public String getTenDm() {
        return tenDm;
    }

    public void setTenDm(String tenDm) {
        this.tenDm = tenDm;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
