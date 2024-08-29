package impl;

import DBConnect.DBConnect;
import java.util.ArrayList;
import model.HoaDonChiTiet;
import service.Service_HoaDonCT;
import java.sql.*;

public class CRUD_HoaDonCT implements Service_HoaDonCT {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    @Override
    public ArrayList<HoaDonChiTiet> list(int id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        sql = """
              SELECT
                  HoaDonChiTiet.ID_HoaDonChiTiet,
                  HoaDonChiTiet.ID_HoaDon,
                  HoaDonChiTiet.SoLuong,
                  Giay_ChiTiet.Ten_GiayChiTiet AS Ten_Giay,
                  Giay_ChiTiet.GiaBan
              FROM
                  HoaDonChiTiet
                  INNER JOIN Giay_ChiTiet ON HoaDonChiTiet.ID_GiayChiTiet = Giay_ChiTiet.ID_GiayChiTiet  
                """;
        if (id != 0) {
            sql += "where ID_HoaDon=?";
        }
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            if (id != 0) {
                ps.setInt(1, id);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HoaDonChiTiet(
                        rs.getInt("ID_HoaDonChiTiet"),
                        rs.getInt("ID_HoaDon"),
                        0,
                        rs.getInt("SoLuong"),
                        rs.getString("Ten_Giay"),
                              rs.getInt("GiaBan")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(HoaDonChiTiet o) {
        System.out.println("okookoo2 " + o.getIdHoaDon());
        sql = "Insert into HoaDonChiTiet(ID_HoaDon,ID_GiayChiTiet,SoLuong) "
                + "values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, o.getIdHoaDon());
            ps.setInt(2, o.getIdGiayCT());
            ps.setInt(3, o.getSoLuong());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi e: " + e.getMessage());
            return false;
        }
    }
}
