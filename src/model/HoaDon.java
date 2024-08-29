/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author phamt
 */
public class HoaDon {
    
    private int ID_HoaDon;
    private int ID_K_Hang;
    private String K_Hang;
    private int ID_N_Vien;
    private String N_Vien;
    private int ID_S_Pham;
    private String S_Pham;
    private int S_Luong;
    private float G_Ban;
    private float T_Tien;
    private Date N_Tao;

    public HoaDon() {
    }

    public HoaDon(int ID_HoaDon, int ID_K_Hang, String K_Hang, int ID_N_Vien, String N_Vien, float T_Tien, Date N_Tao) {
        this.ID_HoaDon = ID_HoaDon;
        this.ID_K_Hang = ID_K_Hang;
        this.K_Hang = K_Hang;
        this.ID_N_Vien = ID_N_Vien;
        this.N_Vien = N_Vien;
        this.T_Tien = T_Tien;
        this.N_Tao = N_Tao;
    }
    

    public HoaDon(int ID_HoaDon, int ID_K_Hang, String K_Hang, int ID_N_Vien, String N_Vien, int ID_S_Pham, String S_Pham, int S_Luong, float G_Ban, float T_Tien, Date N_Tao) {
        this.ID_HoaDon = ID_HoaDon;
        this.ID_K_Hang = ID_K_Hang;
        this.K_Hang = K_Hang;
        this.ID_N_Vien = ID_N_Vien;
        this.N_Vien = N_Vien;
        this.ID_S_Pham = ID_S_Pham;
        this.S_Pham = S_Pham;
        this.S_Luong = S_Luong;
        this.G_Ban = G_Ban;
        this.T_Tien = T_Tien;
        this.N_Tao = N_Tao;
    }

    public int getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(int ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
    }

    public int getID_K_Hang() {
        return ID_K_Hang;
    }

    public void setID_K_Hang(int ID_K_Hang) {
        this.ID_K_Hang = ID_K_Hang;
    }

    public String getK_Hang() {
        return K_Hang;
    }

    public void setK_Hang(String K_Hang) {
        this.K_Hang = K_Hang;
    }

    public int getID_N_Vien() {
        return ID_N_Vien;
    }

    public void setID_N_Vien(int ID_N_Vien) {
        this.ID_N_Vien = ID_N_Vien;
    }

    public String getN_Vien() {
        return N_Vien;
    }

    public void setN_Vien(String N_Vien) {
        this.N_Vien = N_Vien;
    }

    public int getID_S_Pham() {
        return ID_S_Pham;
    }

    public void setID_S_Pham(int ID_S_Pham) {
        this.ID_S_Pham = ID_S_Pham;
    }

    public String getS_Pham() {
        return S_Pham;
    }

    public void setS_Pham(String S_Pham) {
        this.S_Pham = S_Pham;
    }

    public int getS_Luong() {
        return S_Luong;
    }

    public void setS_Luong(int S_Luong) {
        this.S_Luong = S_Luong;
    }

    public float getG_Ban() {
        return G_Ban;
    }

    public void setG_Ban(float G_Ban) {
        this.G_Ban = G_Ban;
    }

    public float getT_Tien() {
        return T_Tien;
    }

    public void setT_Tien(float T_Tien) {
        this.T_Tien = T_Tien;
    }

    public Date getN_Tao() {
        return N_Tao;
    }

    public void setN_Tao(Date N_Tao) {
        this.N_Tao = N_Tao;
    }

    
}
