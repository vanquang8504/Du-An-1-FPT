package model;

import java.util.Date;

public class HoaDon_1 {

    private int idHoaDon, idNhanVien, idKhachHang, idPhieuGG, idDotGG, idPhuongThucTT, tongTien, soTienGGVC, soTienGGCT, thanhToan, tienKhachDua, tienThua;
    private String tenNhanVien, tenKhachHang, phieuGG, dotGG, phuongThucTT, ngayLap, soDt, trangThai;
    private String ghiChi;

    public HoaDon_1() {
    }

    public HoaDon_1(int idHoaDon, int idNhanVien, int idKhachHang, int idPhieuGG, int idDotGG, int idPhuongThucTT, int tongTien, int soTienGGVC, int soTienGGCT, int thanhToan, int tienKhachDua, int tienThua, String tenNhanVien, String tenKhachHang, String phieuGG, String dotGG, String phuongThucTT, String ngayLap, String trangThai, String ghiChi, String soDT) {
        this.idHoaDon = idHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idPhieuGG = idPhieuGG;
        this.idDotGG = idDotGG;
        this.idPhuongThucTT = idPhuongThucTT;
        this.tongTien = tongTien;
        this.soTienGGVC = soTienGGVC;
        this.soTienGGCT = soTienGGCT;
        this.thanhToan = thanhToan;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.phieuGG = phieuGG;
        this.dotGG = dotGG;
        this.phuongThucTT = phuongThucTT;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
        this.ghiChi = ghiChi;
        this.soDt = soDT;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getPhieuGG() {
        return phieuGG;
    }

    public void setPhieuGG(String phieuGG) {
        this.phieuGG = phieuGG;
    }

    public String getDotGG() {
        return dotGG;
    }

    public void setDotGG(String dotGG) {
        this.dotGG = dotGG;
    }

    public String getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(String phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdPhieuGG() {
        return idPhieuGG;
    }

    public void setIdPhieuGG(int idPhieuGG) {
        this.idPhieuGG = idPhieuGG;
    }

    public int getIdDotGG() {
        return idDotGG;
    }

    public void setIdDotGG(int idDotGG) {
        this.idDotGG = idDotGG;
    }

    public int getIdPhuongThucTT() {
        return idPhuongThucTT;
    }

    public void setIdPhuongThucTT(int idPhuongThucTT) {
        this.idPhuongThucTT = idPhuongThucTT;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoTienGGVC() {
        return soTienGGVC;
    }

    public void setSoTienGGVC(int soTienGGVC) {
        this.soTienGGVC = soTienGGVC;
    }

    public int getSoTienGGCT() {
        return soTienGGCT;
    }

    public void setSoTienGGCT(int soTienGGCT) {
        this.soTienGGCT = soTienGGCT;
    }

    public int getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(int tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public int getTienThua() {
        return tienThua;
    }

    public void setTienThua(int tienThua) {
        this.tienThua = tienThua;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChi() {
        return ghiChi;
    }

    public void setGhiChi(String ghiChi) {
        this.ghiChi = ghiChi;
    }

}
