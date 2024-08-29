package service;

import java.util.ArrayList;
import model.HoaDonChiTiet;

public interface Service_HoaDonCT {
    ArrayList<HoaDonChiTiet> list(int id);
    boolean create(HoaDonChiTiet o);
}
