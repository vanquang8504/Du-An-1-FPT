package model;

public class DotGiamGiaCT {

    public int MaDGG;
    public int MaGiayCT;
    public String TenGiay;

    public DotGiamGiaCT() {
    }

    public DotGiamGiaCT(int MaDGG, int MaGiayCT, String TenGiay) {
        this.MaDGG = MaDGG;
        this.MaGiayCT = MaGiayCT;
        this.TenGiay = TenGiay;
    }

    public int getMaDGG() {
        return MaDGG;
    }

    public void setMaDGG(int MaDGG) {
        this.MaDGG = MaDGG;
    }

    public int getMaGiayCT() {
        return MaGiayCT;
    }

    public void setMaGiayCT(int MaGiayCT) {
        this.MaGiayCT = MaGiayCT;
    }

    public String getTenGiay() {
        return TenGiay;
    }

    public void setTenGiay(String TenGiay) {
        this.TenGiay = TenGiay;
    }

    @Override
    public String toString() {
        return "DotGiamGiaCT{" + "MaDGG=" + MaDGG + ", MaGiay=" + MaGiayCT + '}';
    }

}
