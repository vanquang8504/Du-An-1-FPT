package model;

public class HoaDonCho {

    private int IdHoaDon = 1, idNhanVien = 1, idKhachHang = 1, idMaGG = 0, idDotGG = 0, soTienGGVC = 0, soTienGGDG = 0,idLoaiGG;

    private String tenNhanVien, tenKhachHang,ngayTao,soDt;

    public HoaDonCho() {
    }

    public HoaDonCho(int IdHoaDon, int idNhanVien, int idKhachHang, int idMaGG, int idDotGG, String tenNhanVien, String tenKhachHang, String ngayTao, int soTienGGVC, int soTienGGDG,String soDT) {
        this.IdHoaDon = IdHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idMaGG = idMaGG;
        this.idDotGG = idDotGG;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.ngayTao = ngayTao;
        this.soTienGGVC = soTienGGVC;
        this.soTienGGDG = soTienGGDG;
        this.soDt=soDT;
    }

    public int getIdLoaiGG() {
        return idLoaiGG;
    }

    public void setIdLoaiGG(int idLoaiGG) {
        this.idLoaiGG = idLoaiGG;
    }

    
    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public int getSoTienGGVC() {
        return soTienGGVC;
    }

    public void setSoTienGGVC(int soTienGGVC) {
        this.soTienGGVC = soTienGGVC;
    }

    public int getSoTienGGDG() {
        return soTienGGDG;
    }

    public void setSoTienGGDG(int soTienGGDG) {
        this.soTienGGDG = soTienGGDG;
    }

    public int getIdMaGG() {
        return idMaGG;
    }

    public void setIdMaGG(int idMaGG) {
        this.idMaGG = idMaGG;
    }

    public int getIdDotGG() {
        return idDotGG;
    }

    public void setIdDotGG(int idDotGG) {
        this.idDotGG = idDotGG;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.IdHoaDon = idHoaDon;
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

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

}
