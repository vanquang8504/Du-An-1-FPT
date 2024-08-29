package service;

import model.Giay;

public interface Service_Giay {
    public int create(Giay giay);
    public int update(Giay giay);
    public int remove(int id);
}
