/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class phieugiamgiachitiet {
    Integer idGG;
    Integer maGiamGia;
    String tenGiamGia;
    BigDecimal giaTriGiam;
    BigDecimal hoaDonToiThieu;
    Integer idLG;
    String loaiGiam;
    String thoiGianKT;
    Integer soLuong;

    public phieugiamgiachitiet() {
    }

    public phieugiamgiachitiet(Integer idGG, Integer maGiamGia, String tenGiamGia, BigDecimal giaTriGiam, BigDecimal hoaDonToiThieu, Integer idLG, String loaiGiam, String thoiGianKT, Integer soLuong) {
        this.idGG = idGG;
        this.maGiamGia = maGiamGia;
        this.tenGiamGia = tenGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.hoaDonToiThieu = hoaDonToiThieu;
        this.idLG = idLG;
        this.loaiGiam = loaiGiam;
        this.thoiGianKT = thoiGianKT;
        this.soLuong = soLuong;
    }

    public Integer getIdGG() {
        return idGG;
    }

    public void setIdGG(Integer idGG) {
        this.idGG = idGG;
    }

    public Integer getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(Integer maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }

    public BigDecimal getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(BigDecimal giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public BigDecimal getHoaDonToiThieu() {
        return hoaDonToiThieu;
    }

    public void setHoaDonToiThieu(BigDecimal hoaDonToiThieu) {
        this.hoaDonToiThieu = hoaDonToiThieu;
    }

    public Integer getIdLG() {
        return idLG;
    }

    public void setIdLG(Integer idLG) {
        this.idLG = idLG;
    }

    public String getLoaiGiam() {
        return loaiGiam;
    }

    public void setLoaiGiam(String loaiGiam) {
        this.loaiGiam = loaiGiam;
    }

    public String getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(String thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    
    
}
