package service;

import model.Giay_ChiTiet;

public interface Service_GiayCT {
    public int create(Giay_ChiTiet o);
    public int update(Giay_ChiTiet o);
    public int remove(int id);
}
