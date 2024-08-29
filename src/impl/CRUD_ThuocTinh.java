package impl;

import java.sql.*;
import java.util.ArrayList;
import model.ThuocTinh;

public class CRUD_ThuocTinh {

    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String sql = null;
    String sql1 = null;
    String sql2 = null;
    String sql3 = null;
    String id = null;
    String name = null;

    public ArrayList<ThuocTinh> listThuocTinh(int Status) {
        setUp(Status);
        ArrayList<ThuocTinh> list = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            pstm = con.prepareCall(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new ThuocTinh(
                        rs.getString(name),
                        rs.getInt(id)));
            }

            rs.close();
            pstm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: \n" + e.getMessage());
        }
        return list;
    }

    /*
    Trả Về 0: Nếu Thực Hiện Truy Vấn Thành Công
    Trả Về 1: Nếu Thiếu Thuộc Tính
    Trả Về 3: Lỗi Truy Vấn
     */
    public int create(String thuocTinh) {

        System.out.println(thuocTinh);

        int check = kiemTraNL(thuocTinh);
        if (check != 0) {
            return check;
        }
        try {
            con = DBConnect.DBConnect.getConnection();
            pstm = con.prepareCall(sql1);

            pstm.setString(1, thuocTinh);

            pstm.executeUpdate();
            pstm.close();
            con.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: \n" + e.getMessage());
            return 3;
        }
    }

    public int remove(int Id) {

        System.out.println(Id);

        try {
            con = DBConnect.DBConnect.getConnection();
            pstm = con.prepareCall(sql2);

            pstm.setInt(1, Id);
            pstm.executeUpdate();

            pstm.close();
            con.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: \n" + e.getMessage());
            return 3;
        }
    }

    public int update(int Id, String thuocTinh) {

        System.out.println(thuocTinh + Id);

        int check = kiemTraNL(thuocTinh);
        if (check != 0) {
            return check;
        }
        try {
            con = DBConnect.DBConnect.getConnection();
            pstm = con.prepareCall(sql3);

            pstm.setString(1, thuocTinh);
            pstm.setInt(2, Id);
            pstm.executeUpdate();

            pstm.close();
            con.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("Lỗi: \n" + e.getMessage());
            return 3;
        }
    }

    public void setUp(int Status) {
        String table = "";
        switch (Status) {
            case 0 -> {
                table = "XuatXu";
                id = "ID_XuatXu";
                name = "Ten_XuatXu";
                break;
            }
            case 1 -> {
                table = "DanhMuc";
                id = "ID_DanhMuc";
                name = "Ten_DanhMuc";
                break;
            }
            case 2 -> {
                table = "KichThuoc";
                id = "ID_KichThuoc";
                name = "Ten_KichThuoc";
                break;
            }
            case 3 -> {
                table = "MauSac";
                id = "ID_MauSac";
                name = "Ten_MauSac";
                break;
            }
            case 4 -> {
                table = "ChatLieu";
                id = "ID_ChatLieu";
                name = "Ten_ChatLieu";
                break;
            }
            case 5 -> {
                table = "KieuDe";
                id = "ID_KieuDe";
                name = "Ten_KieuDe";
                break;
            }
            case 6 -> {
                table = "LopLot";
                id = "ID_LopLot";
                name = "Ten_LopLot";
                break;
            }
            case 7 -> {
                table = "KieuMui";
                id = "ID_KieuMui";
                name = "Ten_KieuMui";
                break;
            }
            case 8 -> {
                table = "Giay";
                id = "id_Giay";
                name = "Ten_Giay";
                break;
            }
        }
        sql = "Select " + id + ", " + name + " From " + table + "";
        sql1 = "Insert Into " + table + "( " + name + ") Values(?)";
        sql2 = "Delete " + table + " Where " + id + "=?";
        sql3 = "Update " + table + " Set " + name + "=? Where " + id + "=?";
    }

    private int kiemTraNL(String thuocTinh) {
        if (thuocTinh.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

}
