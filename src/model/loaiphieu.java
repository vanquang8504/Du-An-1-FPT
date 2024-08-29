/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class loaiphieu {
    Integer id;
    String tenLoaiPhieu;

    public loaiphieu() {
    }

    public loaiphieu(Integer id, String tenLoaiPhieu) {
        this.id = id;
        this.tenLoaiPhieu = tenLoaiPhieu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLoaiPhieu() {
        return tenLoaiPhieu;
    }

    public void setTenLoaiPhieu(String tenLoaiPhieu) {
        this.tenLoaiPhieu = tenLoaiPhieu;
    }
    
    
}
