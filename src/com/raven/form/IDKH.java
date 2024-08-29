package com.raven.form;

public class IDKH {

    int idKH;
    String tenKH,soDt;

    public IDKH() {
    }

    public IDKH(int idKH, String tenKH, String soDt) {
        this.idKH = idKH;
        this.tenKH = tenKH;
        this.soDt = soDt;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }
    

}
