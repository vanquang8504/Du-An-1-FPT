/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import DBConnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import model.LichSuGiaoDich;

/**
 *
 *
 * @author Fpt06
 */
public class LichSuGiaoDichDAO {

    public ArrayList<LichSuGiaoDich> getAllLichSuGiaoDich() {
        ArrayList<LichSuGiaoDich> lst = new ArrayList<>();

        try {
            Connection cn = DBConnect.getConnection();
            PreparedStatement pstm = cn.prepareStatement(
                    "select TenKH, Giay.Ten_Giay, SoDT,SoLuong, SoTiem_Mua,ThanhToan\n"
                    + "			from KHACHHANG join LichSuMuaHang on KHACHHANG.ID_KhachHang = LichSuMuaHang.ID_KhachHang\n"
                    + "		               join Giay on LichSuMuaHang.ID_Giay = Giay.ID_Giay"
            );

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LichSuGiaoDich ls = new LichSuGiaoDich(
                        rs.getString("TenKH"),
                        rs.getString("Ten_Giay"),
                        rs.getString("SoDT"),
                        rs.getFloat("SoLuong"),
                        rs.getBigDecimal("SoTiem_Mua"),
                        rs.getBoolean("ThanhToan")
                );
                lst.add(ls);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

}
