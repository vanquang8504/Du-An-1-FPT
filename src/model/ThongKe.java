package model;

public class ThongKe {

    private int idGiay, soLuong, giaBan, doanhThu;
    private String tenGiay;

    public ThongKe() {
    }

    public ThongKe(int idGiay, int soLuong, int giaBan, int doanhThu, String tenGiay) {
        this.idGiay = idGiay;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.doanhThu = doanhThu;
        this.tenGiay = tenGiay;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

}
