package model;

public class ThuocTinh {

    private String tenThuocTinh;
    private int idThuocTinh;

    public ThuocTinh() {
    }

    public ThuocTinh(String tenThuocTinh, int idThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
        this.idThuocTinh = idThuocTinh;
    }

    public int getIdThuocTinh() {
        return idThuocTinh;
    }

    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setIdThuocTinh(int idThuocTinh) {
        this.idThuocTinh = idThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }

}
