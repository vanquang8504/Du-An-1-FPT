/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyenkhanhdang
 */
public class DotGiamGia {
    public int maDGG;
    public String tenDGG;
    public String ngayBD;
    public String ngayKT;
    public double giam;
    public boolean trangThai;
    public int PTTT;
    public String moTa;
    public String hinhAnh;
    
    public DotGiamGia() {
    }

    public DotGiamGia(int maDGG, String tenDGG, String ngayBD, String ngayKT, double giam, boolean trangThai, int PTTT, String moTa, String hinhAnh) {
        this.maDGG = maDGG;
        this.tenDGG = tenDGG;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.giam = giam;
        this.trangThai = trangThai;
        this.PTTT = PTTT;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public int getMaDGG() {
        return maDGG;
    }

    public void setMaDGG(int maDGG) {
        this.maDGG = maDGG;
    }

    public String getTenDGG() {
        return tenDGG;
    }

    public void setTenDGG(String tenDGG) {
        this.tenDGG = tenDGG;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public double getGiam() {
        return giam;
    }

    public void setGiam(double giam) {
        this.giam = giam;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getPTTT() {
        return PTTT;
    }

    public void setPTTT(int PTTT) {
        this.PTTT = PTTT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "DotGiamGia{" + "maDGG=" + maDGG + ", tenDGG=" + tenDGG + ", ngayBD=" + ngayBD + ", ngayKT=" + ngayKT + ", giam=" + giam + ", trangThai=" + trangThai + ", PTTT=" + PTTT + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + '}';
    }


    
    
    

    


    
    
}
