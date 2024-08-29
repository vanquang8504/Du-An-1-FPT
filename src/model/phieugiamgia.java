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
public class phieugiamgia {
    Integer maGiamGia;
    String tenGiamGia;
    BigDecimal giaTriGiam;
    BigDecimal donHangToiThieu;
    String ngayTao;
    String ngayBatDau;
    String ngayKetThuc;
    String nhanVienTao;
    String loaiGiamGia;
    Integer maNV;
    Integer idLoaiGG;
    Integer idPGG;
    public String toString(){
        return loaiGiamGia;
    }

    public phieugiamgia() {
    }

    public phieugiamgia(Integer maGiamGia, String tenGiamGia, BigDecimal giaTriGiam, BigDecimal donHangToiThieu, String ngayTao, String ngayBatDau, String ngayKetThuc, String nhanVienTao, String loaiGiamGia, Integer maNV, Integer idLoaiGG, Integer idPGG) {
        this.maGiamGia = maGiamGia;
        this.tenGiamGia = tenGiamGia;
        this.giaTriGiam = giaTriGiam;
        this.donHangToiThieu = donHangToiThieu;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.nhanVienTao = nhanVienTao;
        this.loaiGiamGia = loaiGiamGia;
        this.maNV = maNV;
        this.idLoaiGG = idLoaiGG;
        this.idPGG = idPGG;
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

    public BigDecimal getDonHangToiThieu() {
        return donHangToiThieu;
    }

    public void setDonHangToiThieu(BigDecimal donHangToiThieu) {
        this.donHangToiThieu = donHangToiThieu;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getNhanVienTao() {
        return nhanVienTao;
    }

    public void setNhanVienTao(String nhanVienTao) {
        this.nhanVienTao = nhanVienTao;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Integer getIdLoaiGG() {
        return idLoaiGG;
    }

    public void setIdLoaiGG(Integer idLoaiGG) {
        this.idLoaiGG = idLoaiGG;
    }

    public Integer getIdPGG() {
        return idPGG;
    }

    public void setIdPGG(Integer idPGG) {
        this.idPGG = idPGG;
    }

    
    
    
    
}
