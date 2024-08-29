package impl;

import DBConnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import model.HoaDon_1;

public class CRUD_HoaDon implements service.Service_HoaDon {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public int getIDHoaDon() {
        int rowCount = 0;
        String sql = "SELECT COUNT(*) AS TotalRows FROM HoaDon";

        try (Connection connection = DBConnect.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt("TotalRows");
            }

        } catch (SQLException e) {
        }

        return rowCount;
    }

    @Override
    public int create(HoaDon_1 o) {
        String sql = """
         INSERT INTO HOADON(
                [NgayLap]
               ,[ID_NhanVien]
               ,[ID_KhachHang]
               ,[ID_PGG]
               ,[ID_DotGiamGia]
               ,[ID_PTTT]
               ,[TongTien]
               ,[SoTienGiamGiaVC]
               ,[ThanhToan]
               ,[TienKhachDua]
               ,[TienTralaiKhach]
               ,[GhiChu]
               ,[SoTienGiamGiaCT]
               ,[TrangThai])
         OUTPUT INSERTED.ID_HoaDon AS LastInsertedID
         VALUES(GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);""";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, o.getIdNhanVien());
            ps.setInt(2, o.getIdKhachHang());

            if (o.getIdPhieuGG() != 0) {
                ps.setInt(3, o.getIdPhieuGG());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            if (o.getIdDotGG() != 0) {
                ps.setInt(4, o.getIdDotGG());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            if (o.getIdPhuongThucTT() != 0) {
                ps.setInt(5, o.getIdPhuongThucTT());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setDouble(6, o.getTongTien());
            ps.setInt(7, o.getSoTienGGVC());
            ps.setInt(8, o.getThanhToan());
            ps.setInt(9, o.getTienKhachDua());
            ps.setInt(10, o.getTienThua());
            ps.setString(11, o.getGhiChi());
            ps.setInt(12, o.getSoTienGGCT());
            ps.setString(13, o.getTrangThai());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int lastInsertedID = rs.getInt("LastInsertedID");
                System.out.println("ID của hóa đơn mới: " + lastInsertedID);
                return lastInsertedID;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi C01: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public int update(HoaDon_1 o) {
        return 0;
    }

    public ArrayList<HoaDon_1> list() {
        ArrayList<HoaDon_1> list = new ArrayList<>();
        String sql = """
                   SELECT
                       HoaDon.ID_HoaDon,
                       HoaDon.NgayLap,
                       NhanVien.TenNhanVien AS TenNhanVien,
                       KhachHang.TenKH AS TenKhachHang,
                       PhieuGiamGia.TENGIAMGIA AS TenPhieuGiamGia,
                       DotGiamGia.TenDotGiamGia AS TenDotGiamGia,
                       PhuongThucThanhToan.TenPTTT AS TenPhuongThucThanhToan,
                       HoaDon.TrangThai,
                       HoaDon.TongTien,
                       HoaDon.SoTienGiamGiaVC,
                       HoaDon.SoTienGiamGiaCT,
                       HoaDon.ThanhToan,
                       HoaDon.TienKhachDua,
                       HoaDon.TienTralaiKhach,
                       HoaDon.GhiChu
                   FROM
                       HoaDon
                       INNER JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien
                       INNER JOIN KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID_KhachHang
                       LEFT JOIN PhieuGiamGia ON HoaDon.ID_PGG = PhieuGiamGia.ID_PGG
                       LEFT JOIN DotGiamGia ON HoaDon.ID_DotGiamGia = DotGiamGia.ID_DotGiamGia
                       LEFT JOIN PhuongThucThanhToan ON HoaDon.ID_PTTT = PhuongThucThanhToan.ID_PTTT;""";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon_1 o = new HoaDon_1();

                o.setIdHoaDon(rs.getInt("ID_HoaDon"));
                o.setNgayLap(rs.getString("NgayLap"));
                o.setTenNhanVien(rs.getString("TenNhanVien"));
                o.setTenKhachHang(rs.getString("TenKhachHang"));
                o.setPhieuGG(rs.getString("TenPhieuGiamGia"));
                o.setDotGG(rs.getString("TenDotGiamGia"));
                o.setPhuongThucTT(rs.getString("TenPhuongThucThanhToan"));
                o.setTrangThai(rs.getString("TrangThai"));
                o.setTongTien(rs.getInt("TongTien"));
                o.setSoTienGGVC(rs.getInt("SoTienGiamGiaVC"));
                o.setSoTienGGCT(rs.getInt("SoTienGiamGiaCT"));
                o.setThanhToan(rs.getInt("ThanhToan"));
                o.setTienKhachDua(rs.getInt("TienKhachDua"));
                o.setTienThua(rs.getInt("TienTralaiKhach"));
                o.setGhiChi(rs.getString("GhiChu"));

                list.add(o);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi D01: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<HoaDon_1> listTimKiem(String timKiem) {
        ArrayList<HoaDon_1> list = new ArrayList<>();
        sql = """
                   SELECT
                       HoaDon.ID_HoaDon,
                       HoaDon.NgayLap,
                       NhanVien.TenNhanVien AS TenNhanVien,
                       KhachHang.TenKH AS TenKhachHang,
                       PhieuGiamGia.TENGIAMGIA AS TenPhieuGiamGia,
                       DotGiamGia.TenDotGiamGia AS TenDotGiamGia,
                       PhuongThucThanhToan.TenPTTT AS TenPhuongThucThanhToan,
                       HoaDon.TrangThai,
                       HoaDon.TongTien,
                       HoaDon.SoTienGiamGiaVC,
                       HoaDon.SoTienGiamGiaCT,
                       HoaDon.ThanhToan,
                       HoaDon.TienKhachDua,
                       HoaDon.TienTralaiKhach,
                       HoaDon.GhiChu
                   FROM
                       HoaDon
                       INNER JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien
                       INNER JOIN KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID_KhachHang
                       LEFT JOIN PhieuGiamGia ON HoaDon.ID_PGG = PhieuGiamGia.ID_PGG
                       LEFT JOIN DotGiamGia ON HoaDon.ID_DotGiamGia = DotGiamGia.ID_DotGiamGia
                       LEFT JOIN PhuongThucThanhToan ON HoaDon.ID_PTTT =PhuongThucThanhToan.ID_PTTT
                   WHERE 
                        NhanVien.TenNhanVien LIKE ?
                     OR KhachHang.TenKH LIKE ?
                     OR PhieuGiamGia.TENGIAMGIA LIKE ?
                     OR DotGiamGia.TenDotGiamGia LIKE ?
                     OR PhuongThucThanhToan.TenPTTT LIKE ?
                     OR HoaDon.GhiChu  LIKE ?""";
        int timkiemI = -42324234;
        try {
            timkiemI = Integer.parseInt(timKiem);
            sql += """
                  OR HoaDon.ID_HoaDon = ?
                  OR HoaDon.TongTien =?
                  OR HoaDon.SoTienGiamGiaVC =?
                  OR ThanhToan =?
                  OR HoaDon.TienKhachDua =?
                  OR HoaDon.TienTralaiKhach =?
                  OR HoaDon.SoTienGiamGiaCT =?
                  """;
        } catch (NumberFormatException e) {
        }
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, "%" + timKiem + "%");
            ps.setString(2, "%" + timKiem + "%");
            ps.setString(3, "%" + timKiem + "%");
            ps.setString(4, "%" + timKiem + "%");
            ps.setString(5, "%" + timKiem + "%");
            ps.setString(6, "%" + timKiem + "%");

            if (timkiemI != -42324234) {
                ps.setInt(7, timkiemI);
                ps.setInt(8, timkiemI);
                ps.setInt(9, timkiemI);
                ps.setInt(10, timkiemI);
                ps.setInt(11, timkiemI);
                ps.setInt(12, timkiemI);
                ps.setInt(13, timkiemI);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon_1 o = new HoaDon_1();
                o.setIdHoaDon(rs.getInt("ID_HoaDon"));
                o.setNgayLap(rs.getString("NgayLap"));
                o.setTenNhanVien(rs.getString("TenNhanVien"));
                o.setTenKhachHang(rs.getString("TenKhachHang"));
                o.setPhieuGG(rs.getString("TenPhieuGiamGia"));
                o.setDotGG(rs.getString("TenDotGiamGia"));
                o.setPhuongThucTT(rs.getString("TenPhuongThucThanhToan"));
                o.setTrangThai(rs.getString("TrangThai"));
                o.setTongTien(rs.getInt("TongTien"));
                o.setSoTienGGVC(rs.getInt("SoTienGiamGiaVC"));
                o.setSoTienGGCT(rs.getInt("SoTienGiamGiaCT"));
                o.setThanhToan(rs.getInt("ThanhToan"));
                o.setTienKhachDua(rs.getInt("TienKhachDua"));
                o.setTienThua(rs.getInt("TienTralaiKhach"));
                o.setGhiChi(rs.getString("GhiChu"));

                list.add(o);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi D02: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<HoaDon_1> listLoc(int locTheo, int loc) {
        ArrayList<HoaDon_1> list = new ArrayList<>();
        sql = """
                   SELECT
                       HoaDon.ID_HoaDon,
                       HoaDon.NgayLap,
                       NhanVien.TenNhanVien AS TenNhanVien,
                       KhachHang.TenKH AS TenKhachHang,
                       PhieuGiamGia.TENGIAMGIA AS TenPhieuGiamGia,
                       DotGiamGia.TenDotGiamGia AS TenDotGiamGia,
                       PhuongThucThanhToan.TenPTTT AS TenPhuongThucThanhToan,
                       HoaDon.TrangThai,
                       HoaDon.TongTien,
                       HoaDon.SoTienGiamGiaVC,
                       HoaDon.SoTienGiamGiaCT,
                       HoaDon.ThanhToan,
                       HoaDon.TienKhachDua,
                       HoaDon.TienTralaiKhach,
                       HoaDon.GhiChu
                   FROM
                       HoaDon
                       INNER JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien
                       INNER JOIN KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID_KhachHang
                       LEFT JOIN PhieuGiamGia ON HoaDon.ID_PGG = PhieuGiamGia.ID_PGG
                       LEFT JOIN DotGiamGia ON HoaDon.ID_DotGiamGia = DotGiamGia.ID_DotGiamGia
                       LEFT JOIN PhuongThucThanhToan ON HoaDon.ID_PTTT =PhuongThucThanhToan.ID_PTTT 
                   """;
        setUp(locTheo, loc);
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon_1 o = new HoaDon_1();
                o.setIdHoaDon(rs.getInt("ID_HoaDon"));
                o.setNgayLap(rs.getString("NgayLap"));
                o.setTenNhanVien(rs.getString("TenNhanVien"));
                o.setTenKhachHang(rs.getString("TenKhachHang"));
                o.setPhieuGG(rs.getString("TenPhieuGiamGia"));
                o.setDotGG(rs.getString("TenDotGiamGia"));
                o.setPhuongThucTT(rs.getString("TenPhuongThucThanhToan"));
                o.setTrangThai(rs.getString("TrangThai"));
                o.setTongTien(rs.getInt("TongTien"));
                o.setSoTienGGVC(rs.getInt("SoTienGiamGiaVC"));
                o.setSoTienGGCT(rs.getInt("SoTienGiamGiaCT"));
                o.setThanhToan(rs.getInt("ThanhToan"));
                o.setTienKhachDua(rs.getInt("TienKhachDua"));
                o.setTienThua(rs.getInt("TienTralaiKhach"));
                o.setGhiChi(rs.getString("GhiChu"));

                list.add(o);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi D02: " + e.getMessage());
            return null;
        }
    }

    private void setUp(int LocTheo, int Loc) {
        System.out.println("lọc Theo: " + LocTheo + "\nLoc: " + Loc);
        switch (LocTheo) {
            case 1 -> {
                sql += " WHERE HOADON.ID_NHANVIEN=" + Loc;
                break;
            }
            case 2 -> {
                sql += " WHERE HOADON.ID_PGG=" + Loc;
                break;
            }
            case 3 -> {
                sql += " WHERE HOADON.ID_DotGiamGia=" + Loc;
                break;
            }
            case 4 -> {
                sql += " WHERE HOADON.ID_PTTT=" + Loc;
                break;
            }
            case 5 -> {
                if (Loc == 1) {
                    sql += " WHERE HOADON.TrangThai= N'Thành Công'";
                } else {
                    sql += " WHERE HOADON.TrangThai= N'Bị Hủy'";
                }
                break;
            }
        }
    }
}
