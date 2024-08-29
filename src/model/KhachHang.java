package model;

public class KhachHang {

    private int maKhachHang;
    private String tenKhachHang;
    private boolean gioiTinh;
    private String sdt;

    private String diaChi;
    private boolean hangKhach;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, boolean gioiTinh, String sdt, String diaChi, boolean hangKhach) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.hangKhach = hangKhach;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isHangKhach() {
        return hangKhach;
    }

    public void setHangKhach(boolean hangKhach) {
        this.hangKhach = hangKhach;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", gioiTinh=" + sdt + ", diaChi=" + diaChi + ", hangKhach=" + hangKhach + '}';
    }

}
