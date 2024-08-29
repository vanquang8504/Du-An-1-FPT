package model;

public class HoaDonChiTiet {

    private int idHoaDonCT, idHoaDon, idGiayCT, soLuong, giaBan;
    private String tenGiay;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int idHoaDonCT, int idHoaDon, int idGiayCT, int soLuong, String tenGiay,int giaBan) {
        this.idHoaDonCT = idHoaDonCT;
        this.idHoaDon = idHoaDon;
        this.idGiayCT = idGiayCT;
        this.soLuong = soLuong;
        this.tenGiay = tenGiay;
        this.giaBan=giaBan;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }


    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public int getIdHoaDonCT() {
        return idHoaDonCT;
    }

    public void setIdHoaDonCT(int idHoaDonCT) {
        this.idHoaDonCT = idHoaDonCT;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdGiayCT() {
        return idGiayCT;
    }

    public void setIdGiayCT(int idGiayCT) {
        this.idGiayCT = idGiayCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien(){
        return soLuong*giaBan;
    }
}
