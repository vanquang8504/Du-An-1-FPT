
package model;

public class nhanvien11 {
    private int IDNV;
    private String tenNhanVien;
    private String ngaySinh;
    private String diaChi;
    private String SDT;
    private String Email;
    private int gioiTinh;
    private String hinhAnh;
    private String chucVu;

    public nhanvien11() {
    }

    public nhanvien11(int IDNV, String tenNhanVien, String chucVu) {
        this.IDNV = IDNV;
        this.tenNhanVien = tenNhanVien;
        this.chucVu = chucVu;
    }
    
    public nhanvien11(int IDNV, String tenNhanVien, String ngaySinh, String diaChi, String SDT, String Email, int gioiTinh, String hinhAnh) {
        this.IDNV = IDNV;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.gioiTinh = gioiTinh;
        this.hinhAnh = hinhAnh;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    
}