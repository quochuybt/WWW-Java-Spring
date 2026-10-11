package com.se.demorestdb.dto;

public class Cau2 {
    private int matt;
    private String tieude;
    private String noidungtt;
    private String tendm;

    public Cau2(int matt, String tieude, String noidungtt, String tendm) {
        this.matt = matt;
        this.tieude = tieude;
        this.noidungtt = noidungtt;
        this.tendm = tendm;
    }

    public Cau2() {
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidungtt() {
        return noidungtt;
    }

    public void setNoidungtt(String noidungtt) {
        this.noidungtt = noidungtt;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }
}
