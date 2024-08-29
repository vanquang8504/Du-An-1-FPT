package impl;

import DBConnect.DBConnect;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import model.Giay_ChiTiet;
import service.Service_GiayCT;

public class CRUD_GiayCT implements Service_GiayCT {

    private String sql = null;
    private Connection connect = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Giay_ChiTiet> list(int idGiay) {
        ArrayList<Giay_ChiTiet> list = new ArrayList<>();
        sql = """
              SELECT
              	  G.Ten_Giay ,
                  GCT.ID_GiayChiTiet ,
                  GCT.Ten_GiayChiTiet ,
                  GCT.ID_Giay,
                  GCT.ID_MauSac,
                  GCT.ID_KichThuoc,
                  GCT.SoLuong,
                  GCT.GiaBan,
                  MS.Ten_MauSac ,
                  KT.Ten_KichThuoc ,
                  GCT.TrangThai_Giay,
                  HA.Id_HinhAnh,
                  HA.Img
              FROM
                  Giay_ChiTiet GCT
              JOIN
                  Giay G ON GCT.ID_Giay = G.ID_Giay
              JOIN
                  MauSac MS ON GCT.ID_MauSac = MS.ID_MauSac
              JOIN
                  KichThuoc KT ON GCT.ID_KichThuoc = KT.ID_KichThuoc
              JOIN
                  HinhAnh HA ON GCT.ID_HinhAnh = HA.ID_HinhAnh """;
        try {
            connect = DBConnect.getConnection();
            if (idGiay != 0) {
                sql += " WHERE GCT.ID_GIAY=" + idGiay;
            }
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Giay_ChiTiet o = new Giay_ChiTiet(
                        rs.getInt("ID_GiayChiTiet"),
                        rs.getInt("ID_MauSac"),
                        rs.getInt("ID_KichThuoc"),
                        rs.getInt("Ten_KichThuoc"),
                        rs.getInt("Id_HinhAnh"),
                        rs.getInt("SoLuong"),
                        rs.getString("Ten_GiayChiTiet"),
                        rs.getString("Ten_MauSac"),
                        rs.getString("Img"),
                        rs.getBigDecimal("GiaBan"),
                        rs.getString("TrangThai_Giay"),
                        rs.getInt("ID_Giay"),
                        rs.getString("Ten_Giay"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi B01: \n" + e.getMessage());
        }
        return list;
    }

    public ArrayList<Giay_ChiTiet> listLoc(int LoaiTTCT, int IDTTCT) {
        ArrayList<Giay_ChiTiet> list = new ArrayList<>();
        sql = """
              SELECT
              	  G.Ten_Giay ,
                  GCT.ID_GiayChiTiet ,
                  GCT.Ten_GiayChiTiet ,
                  GCT.ID_Giay,
                  GCT.ID_MauSac,
                  GCT.ID_KichThuoc,
                  GCT.SoLuong,
                  GCT.GiaBan,
                  MS.Ten_MauSac ,
                  KT.Ten_KichThuoc ,
                  GCT.TrangThai_Giay,
                  HA.Id_HinhAnh,
                  HA.Img
              FROM
                  Giay_ChiTiet GCT
              JOIN
                  Giay G ON GCT.ID_Giay = G.ID_Giay
              JOIN
                  MauSac MS ON GCT.ID_MauSac = MS.ID_MauSac
              JOIN
                  KichThuoc KT ON GCT.ID_KichThuoc = KT.ID_KichThuoc
              JOIN
                  HinhAnh HA ON GCT.ID_HinhAnh = HA.ID_HinhAnh """;
        setUp(LoaiTTCT, IDTTCT);
        try {
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Giay_ChiTiet o = new Giay_ChiTiet(
                        rs.getInt("ID_GiayChiTiet"),
                        rs.getInt("ID_MauSac"),
                        rs.getInt("ID_KichThuoc"),
                        rs.getInt("Ten_KichThuoc"),
                        rs.getInt("Id_HinhAnh"),
                        rs.getInt("SoLuong"),
                        rs.getString("Ten_GiayChiTiet"),
                        rs.getString("Ten_MauSac"),
                        rs.getString("Img"),
                        rs.getBigDecimal("GiaBan"),
                        rs.getString("TrangThai_Giay"),
                        rs.getInt("ID_Giay"),
                        rs.getString("Ten_Giay"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi B02: \n" + e.getMessage());
        }
        return list;
    }

    public ArrayList<Giay_ChiTiet> listTimKiem(String timKiem) {
        ArrayList<Giay_ChiTiet> list = new ArrayList<>();
        String sql = """
        SELECT
            G.Ten_Giay,
            GCT.ID_GiayChiTiet,
            GCT.Ten_GiayChiTiet,
            GCT.ID_Giay,
            GCT.ID_MauSac,
            GCT.ID_KichThuoc,
            GCT.SoLuong,
            GCT.GiaBan,
            MS.Ten_MauSac,
            KT.Ten_KichThuoc,
            GCT.TrangThai_Giay,
            HA.Id_HinhAnh,
            HA.Img
        FROM
            Giay_ChiTiet GCT
        JOIN
            Giay G ON GCT.ID_Giay = G.ID_Giay
        JOIN
            MauSac MS ON GCT.ID_MauSac = MS.ID_MauSac
        JOIN
            KichThuoc KT ON GCT.ID_KichThuoc = KT.ID_KichThuoc
        JOIN
            HinhAnh HA ON GCT.ID_HinhAnh = HA.ID_HinhAnh 
        WHERE  
            GCT.Ten_GiayChiTiet LIKE ?
            OR G.Ten_Giay LIKE ?
            OR MS.Ten_MauSac LIKE ?
            OR TrangThai_Giay = ?
    """;
        try {
            double check = -123;
            try {
                check = Double.parseDouble(timKiem);
                sql += """
                                 OR ID_GiayChiTiet = ?
                                 OR SoLuong = ?
                                 OR GiaBan = ?
                                 OR Ten_KichThuoc = ?  
                       """;
            } catch (NumberFormatException e) {
                System.out.println("Không Chuyển Thành Số.");
            }
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);

            // Đặt giá trị tham số cho các điều kiện LIKE
            ps.setString(1, "%" + timKiem + "%");
            ps.setString(2, "%" + timKiem + "%");
            ps.setString(3, "%" + timKiem + "%");
            if (timKiem.contains("đ") || timKiem.contains("ạ")) {
                ps.setBoolean(4, true);
            } else {
                if (timKiem.contains("n") || timKiem.contains("ừ")) {
                    ps.setBoolean(4, false);
                } else {
                    ps.setDouble(4, check);
                }
            }
            if (check != -123) {
                ps.setDouble(5, check);
                ps.setDouble(6, check);
                ps.setDouble(7, check);
                ps.setDouble(8, check);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Giay_ChiTiet o = new Giay_ChiTiet(
                        rs.getInt("ID_GiayChiTiet"),
                        rs.getInt("ID_MauSac"),
                        rs.getInt("ID_KichThuoc"),
                        rs.getInt("Ten_KichThuoc"),
                        rs.getInt("Id_HinhAnh"),
                        rs.getInt("SoLuong"),
                        rs.getString("Ten_GiayChiTiet"),
                        rs.getString("Ten_MauSac"),
                        rs.getString("Img"),
                        rs.getBigDecimal("GiaBan"),
                        rs.getString("TrangThai_Giay"),
                        rs.getInt("ID_Giay"),
                        rs.getString("Ten_Giay"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi B03: \n" + e.getMessage());
        }
        return list;
    }

    @Override
    public int create(Giay_ChiTiet o) {
        sql = """
        INSERT INTO Giay_ChiTiet (
            TrangThai_Giay,
            ID_Giay,
            Ten_GiayChiTiet,
            SoLuong,
            GiaBan,
            ID_MauSac,
            ID_KichThuoc,
            ID_HinhAnh
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;
        try {
            int idHinh = createhinhAnh(o.getHinhAnh());
            System.out.println(idHinh);
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setString(1, o.getTrangThai());
            ps.setInt(2, o.getIdGiay());
            ps.setString(3, o.getTenGiayChiTiet());
            ps.setInt(4, o.getSoLuong());
            ps.setBigDecimal(5, o.getGiaBan());
            ps.setInt(6, o.getIdMauSac());
            ps.setInt(7, o.getIdKichThuoc());
            ps.setInt(8, idHinh);

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Lỗi B04: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Giay_ChiTiet o) {
        sql = """
            UPDATE GIAY_CHITIET
            SET
              ID_Giay=?,
              Ten_GiayChiTiet=?,
              SoLuong=?,
              GiaBan=?,
              ID_MauSac=?,
              ID_KichThuoc=?,
              ID_HinhAnh=?,
              TrangThai_Giay=?
            WHERE ID_GIAYCHITIET=?;
              ;""";
        try {
            int idHinh = createhinhAnh(o.getHinhAnh());
            System.out.println(idHinh);
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setInt(1, o.getIdGiay());
            ps.setString(2, o.getTenGiayChiTiet());
            ps.setInt(3, o.getSoLuong());
            ps.setBigDecimal(4, o.getGiaBan());
            ps.setInt(5, o.getIdMauSac());
            ps.setInt(6, o.getIdKichThuoc());
            ps.setInt(7, idHinh);
            ps.setString(8, o.getTrangThai());

            System.out.print(o.getTrangThai());

            ps.setInt(9, o.getIdGiayChiTiet());
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Lỗi B05: " + e.getMessage());
            return 0;
        }
    }

    public void updateSLSP(int id, int soLuong) {
        sql = """
            UPDATE GIAY_CHITIET
            SET
              SoLuong=?
            WHERE ID_GIAYCHITIET=?;
              ;""";
        try {
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setInt(1, soLuong);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi B09: " + e.getMessage());
        }
    }

    @Override
    public int remove(int id) {
        sql = """
            UPDATE GIAY_CHITIET 
              SET TRANGTHAI_GIAY=N'Ngừng Hoạt Động'
            WHERE ID_GIAYCHITIET=?;
              ;""";
        try {
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi B06: " + e.getMessage());
            return 0;
        }
    }

    private int createhinhAnh(String path) {
        // Kiểm Tra Xem Đã Có Ảnh Tương Tự Trong SQL Chưa
        String selectSql = "SELECT ID_HINHANH FROM HINHANH WHERE IMG = ?";
        String insertSql = "INSERT INTO HINHANH (IMG) OUTPUT INSERTED.ID_HINHANH VALUES (?)";

        try (
                Connection connection = DBConnect.getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql); PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            // Kiểm tra xem ảnh đã có trong SQL chưa
            selectStatement.setString(1, path);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu ảnh đã có, trả về ID
                return resultSet.getInt("ID_HINHANH");
            } else {
                // Nếu ảnh chưa có, thêm ảnh mới và trả về ID
                insertStatement.setString(1, path);
                boolean executed = insertStatement.execute();

                if (executed) {
                    ResultSet generatedKeys = insertStatement.getResultSet();

                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi B07: " + e.getMessage());
        }

        // Trả về 0 nếu có lỗi
        return 0;
    }

//    private void setUp(int LoaiTTCT, int IDTTCT) {
//        String name = "";
//        switch (LoaiTTCT) {
//            case 0 -> {
//                name = "GCT.ID_Giay";
//            }
//            case 1 -> {
//                name = "GCT.ID_MauSac";
//            }
//            case 2 -> {
//                name = "GCT.ID_KichThuoc";
//            }
//            case 3 -> {
//                name = "GCT.TrangThai_Giay";
//            }
//        }
//        if (LoaiTTCT == 3) {
//            sql += " WHERE " + name + "=" + (IDTTCT - 1);
//        } else {
//            sql += " WHERE " + name + "=" + IDTTCT;
//        }
//    }
    private void setUp(int LoaiTTCT, int IDTTCT) {
        String name = "";
        switch (LoaiTTCT) {
            case 0 -> {
                name = "GCT.ID_Giay";
            }
            case 1 -> {
                name = "GCT.ID_MauSac";
            }
            case 2 -> {
                name = "GCT.ID_KichThuoc";
            }
            case 3 -> {
                name = "GCT.TrangThai_Giay";
            }
        }
        if (LoaiTTCT == 3) {
            if (IDTTCT == 1) {
                sql += " WHERE " + name + " LIKE N'%Ngừng%'";
            } else {
                sql += " WHERE " + name + " LIKE N'%Đang%'";
            }
        } else {
            sql += " WHERE " + name + "=" + IDTTCT;
        }
    }

    public static void main(String[] args) {
        new CRUD_GiayCT().create(new Giay_ChiTiet(1, 2, 3, 4, 5, 6, "sad", "", "dfsfds", new BigDecimal(123), "Đang Hoạt Động", 1, ""));
    }
}
