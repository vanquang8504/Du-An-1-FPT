package service;
import java.util.ArrayList;
import model.KhachHang;
public interface Service_KhachHang {
    public ArrayList<KhachHang> getAllKhachHang();
    public boolean ADD(KhachHang kh);
    public Integer update(KhachHang kh);
    public ArrayList<KhachHang> find(String o);
}