/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class TaiKhoan {

    private int IDTK;
    private String tenTK;
    private String matKhau;
    private int IDCV;
    private int IDNV;
    private String trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(int IDTK,  String tenTK, String matKhau, int IDCV, int IDNV, String trangThai) {
        this.IDTK = IDTK;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.IDCV = IDCV;
        this.IDNV = IDNV;
        this.trangThai = trangThai;
    }


    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getIDCV() {
        return IDCV;
    }

    public void setIDCV(int IDCV) {
        this.IDCV = IDCV;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
