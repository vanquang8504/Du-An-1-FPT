/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author phamt
 */
public class PhieuDoi {
    private int ID_Phieu;
    private String Ma_Ct;
    private int ID_K_Hang;
    private String K_Hang;
    private int ID_N_Vien;
    private String N_vien;
    private int ID_S_Pham;
    private String S_Pham;
    private int ID_H_Don;
    private int SL_Mua;
    private int SL_Doi;
    private float G_Ban;
    private int LyDo;
    private int T_Thai;
    private int Kho_luu;
    private String N_Tao;
    private String Note;
    
    private String Ma_Dai;

    public PhieuDoi() {
    }

    public PhieuDoi(String Ma_Dai) {
        this.Ma_Dai = Ma_Dai;
    }

    public String getMa_Dai() {
        return Ma_Dai;
    }

    public void setMa_Dai(String Ma_Dai) {
        this.Ma_Dai = Ma_Dai;
    }
    
    

    public PhieuDoi(int ID_Phieu, int ID_K_Hang, String K_Hang, int ID_N_Vien, String N_vien, int ID_H_Don, String N_Tao) {
        this.ID_Phieu = ID_Phieu;
        this.ID_K_Hang = ID_K_Hang;
        this.K_Hang = K_Hang;
        this.ID_N_Vien = ID_N_Vien;
        this.N_vien = N_vien;
        this.ID_H_Don = ID_H_Don;
        this.N_Tao = N_Tao;
    }

    public PhieuDoi(int ID_Phieu, String Ma_Ct, int ID_S_Pham, String S_Pham, int SL_Mua, int SL_Doi, float G_Ban, int LyDo, int T_Thai, int Kho_luu, String Note) {
        this.ID_Phieu = ID_Phieu;
        this.Ma_Ct = Ma_Ct;
        this.ID_S_Pham = ID_S_Pham;
        this.S_Pham = S_Pham;
        this.SL_Mua = SL_Mua;
        this.SL_Doi = SL_Doi;
        this.G_Ban = G_Ban;
        this.LyDo = LyDo;
        this.T_Thai = T_Thai;
        this.Kho_luu = Kho_luu;
        this.Note = Note;
    }
    
    

    public PhieuDoi(int ID_Phieu, String Ma_Ct, int ID_K_Hang, String K_Hang,
            int ID_N_Vien, String N_vien, int ID_S_Pham, String S_Pham,
            int ID_H_Don, int SL_Mua, int SL_Doi, float G_Ban,
            int LyDo, int T_Thai, int Kho_luu, String N_Tao, String Note) {
        this.ID_Phieu = ID_Phieu;
        this.Ma_Ct = Ma_Ct;
        this.ID_K_Hang = ID_K_Hang;
        this.K_Hang = K_Hang;
        this.ID_N_Vien = ID_N_Vien;
        this.N_vien = N_vien;
        this.ID_S_Pham = ID_S_Pham;
        this.S_Pham = S_Pham;
        this.ID_H_Don = ID_H_Don;
        this.SL_Mua = SL_Mua;
        this.SL_Doi = SL_Doi;
        this.G_Ban = G_Ban;
        this.LyDo = LyDo;
        this.T_Thai = T_Thai;
        this.Kho_luu = Kho_luu;
        this.N_Tao = N_Tao;
        this.Note = Note;
    }

    public int getID_Phieu() {
        return ID_Phieu;
    }

    public void setID_Phieu(int ID_Phieu) {
        this.ID_Phieu = ID_Phieu;
    }

    public String getMa_Ct() {
        return Ma_Ct;
    }

    public void setMa_Ct(String Ma_Ct) {
        this.Ma_Ct = Ma_Ct;
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

    public String getN_vien() {
        return N_vien;
    }

    public void setN_vien(String N_vien) {
        this.N_vien = N_vien;
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

    public int getID_H_Don() {
        return ID_H_Don;
    }

    public void setID_H_Don(int ID_H_Don) {
        this.ID_H_Don = ID_H_Don;
    }

    public int getSL_Mua() {
        return SL_Mua;
    }

    public void setSL_Mua(int SL_Mua) {
        this.SL_Mua = SL_Mua;
    }
    
    public int getSL_Doi() {
        return SL_Doi;
    }

    public void setSL_Doi(int SL_Doi) {
        this.SL_Doi = SL_Doi;
    }

    public float getG_Ban() {
        return G_Ban;
    }

    public void setG_Ban(float G_Ban) {
        this.G_Ban = G_Ban;
    }

    public int getLyDo() {
        return LyDo;
    }

    public void setLyDo(int LyDo) {
        this.LyDo = LyDo;
    }

    public int getT_Thai() {
        return T_Thai;
    }

    public void setT_Thai(int T_Thai) {
        this.T_Thai = T_Thai;
    }

    public int getKho_luu() {
        return Kho_luu;
    }

    public void setKho_luu(int Kho_luu) {
        this.Kho_luu = Kho_luu;
    }

    public String getN_Tao() {
        return N_Tao;
    }

    public void setN_Tao(String N_Tao) {
        this.N_Tao = N_Tao;
    }
    
    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    

   
    
    
}
