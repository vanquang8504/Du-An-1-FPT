package model;

public class GioHang {

    private String tenSanPham;
    private int idSanPham, soLuong, donGia;

    public GioHang() {
    }

    public GioHang( String tenSanPham, int idSanPham, int soLuong, int donGia) {
        this.tenSanPham = tenSanPham;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
    
    public int getThanhTien(){
        return donGia*soLuong;
    }

}
