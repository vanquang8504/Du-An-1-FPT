package model;

public class TT_Giay {
    private int ID_HD, ID_CTG, id_giay, SLuong, ID_img;
    private String TGiay_CT, images;
    private double giaBan;
    
    public TT_Giay() {
    }

    public TT_Giay(int ID_CTG, int id_giay, String TGiay_CT, double giaBan) {
        this.ID_CTG = ID_CTG;
        this.id_giay = id_giay;
        this.TGiay_CT = TGiay_CT;
        this.giaBan = giaBan;
    }

    public TT_Giay(int ID_HD, int ID_CTG, int id_giay, int SLuong, int ID_img, String TGiay_CT, String images, double giaBan) {
        this.ID_HD = ID_HD;
        this.ID_CTG = ID_CTG;
        this.id_giay = id_giay;
        this.SLuong = SLuong;
        this.ID_img = ID_img;
        this.TGiay_CT = TGiay_CT;
        this.images = images;
        this.giaBan = giaBan;
    }

    public int getID_HD() {
        return ID_HD;
    }

    public void setID_HD(int ID_HD) {
        this.ID_HD = ID_HD;
    }

    public int getID_CTG() {
        return ID_CTG;
    }

    public void setID_CTG(int ID_CTG) {
        this.ID_CTG = ID_CTG;
    }

    public int getId_giay() {
        return id_giay;
    }

    public void setId_giay(int id_giay) {
        this.id_giay = id_giay;
    }

    public int getSLuong() {
        return SLuong;
    }

    public void setSLuong(int SLuong) {
        this.SLuong = SLuong;
    }

    public int getID_img() {
        return ID_img;
    }

    public void setID_img(int ID_img) {
        this.ID_img = ID_img;
    }

    public String getTGiay_CT() {
        return TGiay_CT;
    }

    public void setTGiay_CT(String TGiay_CT) {
        this.TGiay_CT = TGiay_CT;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    
}
