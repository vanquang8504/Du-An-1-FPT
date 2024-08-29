/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamt
 */
public class PD_CTiet {
    private int ID_PD;
    private int ID_Giay;
    private String Ma_PD;
    private int ID_CTPD;
    private int ID_TThai_P;
    private String TThai;
    private int ID_LDo;
    private String LDo;
    private int ID_Kho;
    private String Kho;
    private int ID_SP_HD;
    private String Ten_SP_HD;
    private double Gia_HD;
    private int ID_SPD;
    private String Ten_SPD;
    private double Gia_DOi;
    private int SL_IN_HD;
    private int SLD;
    private String Note;

    public PD_CTiet() {
    }

    public PD_CTiet(int ID_PD, int ID_Giay, String Ma_PD, int ID_CTPD, int ID_TThai_P, String TThai, int ID_LDo, String LDo, int ID_Kho, String Kho, int ID_SP_HD, String Ten_SP_HD, double Gia_HD, int ID_SPD, String Ten_SPD, double Gia_DOi, int SL_IN_HD, int SLD, String Note) {
        this.ID_PD = ID_PD;
        this.ID_Giay = ID_Giay;
        this.Ma_PD = Ma_PD;
        this.ID_CTPD = ID_CTPD;
        this.ID_TThai_P = ID_TThai_P;
        this.TThai = TThai;
        this.ID_LDo = ID_LDo;
        this.LDo = LDo;
        this.ID_Kho = ID_Kho;
        this.Kho = Kho;
        this.ID_SP_HD = ID_SP_HD;
        this.Ten_SP_HD = Ten_SP_HD;
        this.Gia_HD = Gia_HD;
        this.ID_SPD = ID_SPD;
        this.Ten_SPD = Ten_SPD;
        this.Gia_DOi = Gia_DOi;
        this.SL_IN_HD = SL_IN_HD;
        this.SLD = SLD;
        this.Note = Note;
    }

    
    public int getID_PD() {
        return ID_PD;
    }

    public void setID_PD(int ID_PD) {
        this.ID_PD = ID_PD;
    }

    public int getID_Giay() {
        return ID_Giay;
    }

    public void setID_Giay(int ID_Giay) {
        this.ID_Giay = ID_Giay;
    }
    
    public String getMa_PD() {
        return Ma_PD;
    }

    public void setMa_PD(String Ma_PD) {
        this.Ma_PD = Ma_PD;
    }

    public int getID_CTPD() {
        return ID_CTPD;
    }

    public void setID_CTPD(int ID_CTPD) {
        this.ID_CTPD = ID_CTPD;
    }

    public int getID_TThai_P() {
        return ID_TThai_P;
    }

    public void setID_TThai_P(int ID_TThai_P) {
        this.ID_TThai_P = ID_TThai_P;
    }

    public String getTThai() {
        return TThai;
    }

    public void setTThai(String TThai) {
        this.TThai = TThai;
    }

    public int getID_LDo() {
        return ID_LDo;
    }

    public void setID_LDo(int ID_LDo) {
        this.ID_LDo = ID_LDo;
    }

    public String getLDo() {
        return LDo;
    }

    public void setLDo(String LDo) {
        this.LDo = LDo;
    }

    public int getID_Kho() {
        return ID_Kho;
    }

    public void setID_Kho(int ID_Kho) {
        this.ID_Kho = ID_Kho;
    }

    public String getKho() {
        return Kho;
    }

    public void setKho(String Kho) {
        this.Kho = Kho;
    }

    public int getID_SP_HD() {
        return ID_SP_HD;
    }

    public void setID_SP_HD(int ID_SP_HD) {
        this.ID_SP_HD = ID_SP_HD;
    }

    public String getTen_SP_HD() {
        return Ten_SP_HD;
    }

    public void setTen_SP_HD(String Ten_SP_HD) {
        this.Ten_SP_HD = Ten_SP_HD;
    }

    public double getGia_HD() {
        return Gia_HD;
    }

    public void setGia_HD(double Gia_HD) {
        this.Gia_HD = Gia_HD;
    }

    public int getID_SPD() {
        return ID_SPD;
    }

    public void setID_SPD(int ID_SPD) {
        this.ID_SPD = ID_SPD;
    }

    public String getTen_SPD() {
        return Ten_SPD;
    }

    public void setTen_SPD(String Ten_SPD) {
        this.Ten_SPD = Ten_SPD;
    }

    public double getGia_DOi() {
        return Gia_DOi;
    }

    public void setGia_DOi(double Gia_DOi) {
        this.Gia_DOi = Gia_DOi;
    }

    public int getSL_IN_HD() {
        return SL_IN_HD;
    }

    public void setSL_IN_HD(int SL_IN_HD) {
        this.SL_IN_HD = SL_IN_HD;
    }

    public int getSLD() {
        return SLD;
    }

    public void setSLD(int SLD) {
        this.SLD = SLD;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    
}
