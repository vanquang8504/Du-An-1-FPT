package model;

import java.math.BigDecimal;

public class Giay_ChiTiet {

    private int idGiayChiTiet, idMauSac, idKichThuoc, kichThuoc, idHinhAnh, soLuong;
    private String tenGiayChiTiet, mauSac, hinhAnh, trangThai;
    private BigDecimal giaBan;
    private int idGiay;
    private String tenGiay;

    public Giay_ChiTiet() {
    }

    public Giay_ChiTiet(int idGiayChiTiet, int idMauSac, int idKichThuoc, int kichThuoc, int idHinhAnh, int soLuong, String tenGiayChiTiet, String mauSac, String hinhAnh, BigDecimal giaBan, String trangThai, int idGiay, String tenGiay) {
        this.idGiayChiTiet = idGiayChiTiet;
        this.idMauSac = idMauSac;
        this.idKichThuoc = idKichThuoc;
        this.kichThuoc = kichThuoc;
        this.idHinhAnh = idHinhAnh;
        this.soLuong = soLuong;
        this.tenGiayChiTiet = tenGiayChiTiet;
        this.mauSac = mauSac;
        this.hinhAnh = hinhAnh;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.idGiay = idGiay;
        this.tenGiay = tenGiay;
    }

    public int getIdGiayChiTiet() {
        return idGiayChiTiet;
    }

    public void setIdGiayChiTiet(int idGiayChiTiet) {
        this.idGiayChiTiet = idGiayChiTiet;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(int idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public int getIdHinhAnh() {
        return idHinhAnh;
    }

    public void setIdHinhAnh(int idHinhAnh) {
        this.idHinhAnh = idHinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenGiayChiTiet() {
        return tenGiayChiTiet;
    }

    public void setTenGiayChiTiet(String tenGiayChiTiet) {
        this.tenGiayChiTiet = tenGiayChiTiet;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

}
