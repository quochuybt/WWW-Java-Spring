package com.se.demorestdb.dto;

public class Cau1 {
    private int maTT;
    private String tieuDe;
    private String tenDM;

    public Cau1(int maTT, String tieuDe, String tenDM) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.tenDM = tenDM;
    }

    public Cau1() {
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
}
