package com.raven.form;

public class PGG {

    int idPGG,loaiGiam, giamGia, toiThieu;

    public int getToiThieu() {
        return toiThieu;
    }

    public void setToiThieu(int toiThieu) {
        this.toiThieu = toiThieu;
    }

    public PGG() {
    }

    public PGG(int idPGG, int loaiGiam, int giamGia, int toiThieu) {
        this.idPGG = idPGG;
        this.loaiGiam = loaiGiam;
        this.giamGia = giamGia;
        this.toiThieu = toiThieu;
    }

    public int getLoaiGiam() {
        return loaiGiam;
    }

    public void setLoaiGiam(int loaiGiam) {
        this.loaiGiam = loaiGiam;
    }

    

    public int getIdPGG() {
        return idPGG;
    }

    public void setIdPGG(int idPGG) {
        this.idPGG = idPGG;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

}
