/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class KhoHang {
    private int ID;
    private String Ma;
    private String DC;
    private String TenKho;
    private String ntao;
    private String SDT;
    private int SL_Hang_TK;
    private String Note;

    public KhoHang() {
    }

    public KhoHang(int ID, String Ma, String DC, String TenKho, String ntao, String SDT, int SL_Hang_TK, String Note) {
        this.ID = ID;
        this.Ma = Ma;
        this.DC = DC;
        this.TenKho = TenKho;
        this.ntao = ntao;
        this.SDT = SDT;
        this.SL_Hang_TK = SL_Hang_TK;
        this.Note = Note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getDC() {
        return DC;
    }

    public void setDC(String DC) {
        this.DC = DC;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String TenKho) {
        this.TenKho = TenKho;
    }

    public String getNtao() {
        return ntao;
    }

    public void setNtao(String ntao) {
        this.ntao = ntao;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getSL_Hang_TK() {
        return SL_Hang_TK;
    }

    public void setSL_Hang_TK(int SL_Hang_TK) {
        this.SL_Hang_TK = SL_Hang_TK;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    
   
}
