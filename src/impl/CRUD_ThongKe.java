package impl;

import DBConnect.DBConnect;
import java.util.ArrayList;
import model.ThongKe;
import java.sql.*;

public class CRUD_ThongKe {

    private String sql;
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<ThongKe> list() {
        sql = """
              SELECT 
                  GCT.ID_GiayChiTiet AS ID_GiayChitiet,
                  GCT.Ten_GiayChiTiet AS Ten_Giay,
                  SUM(HDC.SoLuong) AS SoLuongBan,
                  GCT.GiaBan,
                  SUM(HDC.SoLuong * GCT.GiaBan) AS DoanhThu
              FROM 
                  HoaDonChiTiet HDC
              JOIN 
                  Giay_ChiTiet GCT ON HDC.ID_GiayChiTiet = GCT.ID_GiayChiTiet
              GROUP BY 
                  GCT.ID_GiayChiTiet, GCT.Ten_GiayChiTiet, GCT.GiaBan""";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            ArrayList<ThongKe> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new ThongKe(
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi :" + e.getMessage());
            return null;
        }
    }

    public Object[] thongKe(boolean a, int nam, int thang, int ngay) {
        System.out.println("Nam:"+nam+"\n thang"+thang+"\n ngay"+ngay);
        sql = """
          SELECT
              DAY(NGAYLAP) AS Ngay,
              MONTH(NgayLap) AS Thang,
              YEAR(NgayLap) AS Nam,
              COUNT(ID_HoaDon) AS SoHoaDon,
              SUM(CASE WHEN TrangThai = N'Thành công' THEN 1 ELSE 0 END) AS SoHoaDonThanhCong,
              SUM(CASE WHEN TrangThai = N'Bị hủy' THEN 1 ELSE 0 END) AS SoHoaDonBiHuy,
              SUM(TongTien) AS DoanhThu
          FROM HoaDon
          """;
        if (a) { // Start with a true condition
            if (nam != 0) {
                sql += " WHERE YEAR(NgayLap) = " + nam;
                if (thang != 0) {
                    sql += " AND MONTH(NgayLap) = " + thang;
                    if (ngay != 0) {
                        sql += " AND DAY(NgayLap) = " + ngay;
                    }
                }
            }
        }

        sql += """
              GROUP BY
               DAY(NGAYLAP), MONTH(NgayLap), YEAR(NgayLap)""";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Object[]{
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7)
                };
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
