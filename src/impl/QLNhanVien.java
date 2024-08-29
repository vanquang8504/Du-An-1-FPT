/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DBConnect.DBConnect;

/**
 *
 * @author HP
 */
public class QLNhanVien {

    public static void AddNV(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(TenNhanVien,NgaySinh,DiaChi,SDT,Email,GioiTinh,HinhAnh)"
                + "VALUES(?,?,?,?,?,?,?)";
//        try(Connection conn= DBUtils.getConnection()) {
        try {

            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getNgaySinh());
            ps.setString(3, nv.getDiaChi());
            ps.setString(4, nv.getSDT());
            ps.setString(5, nv.getEmail());
            ps.setInt(6, nv.getGioiTinh());
            ps.setString(7, nv.getHinhAnh());
            ps.executeUpdate();
            // lấy mã khachs hàng tự động tạo
//            int aff= 
//            if (aff > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    int generatedId = rs.getInt(1);
//                    nv.setIDNV(generatedId);
//                }
//                rs.close();
//                
//            }
//            ps.close();
//            conn.close();
//            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
//        return false;
    }

    public static void updateNV(NhanVien nv) {
        String sql = """
               Update NhanVien
               set TenNhanVien=?,
               NgaySinh=?,
               DiaChi=?,
               SDT=?,
               Email=?,
               GioiTinh=?,
               HinhAnh=?
               where ID_NhanVien=?""";
        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getNgaySinh());
            ps.setString(3, nv.getDiaChi());
            ps.setString(4, nv.getSDT());
            ps.setString(5, nv.getEmail());
            ps.setInt(6, nv.getGioiTinh());
            ps.setString(7, nv.getHinhAnh());
            ps.setInt(8, nv.getIDNV());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    public static void deleteNV(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE ID_NhanVien=?";
//        try(Connection conn= DBUtils.getConnection()) {
        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maNV);
//            return ps.executeUpdate()>0;
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
//        return false;
    }

    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection conn = DBConnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIDNV(rs.getInt(1));
                nv.setTenNhanVien(rs.getString(2));
                nv.setNgaySinh(rs.getString(3));
                nv.setDiaChi(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setEmail(rs.getString(6));
                nv.setGioiTinh(rs.getInt(7));
                nv.setHinhAnh(rs.getString(8));
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien getNhanVienByID(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE ID_NhanVien=?";

        try (Connection conn = DBConnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maNV);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIDNV(rs.getInt(1));
                nv.setTenNhanVien(rs.getString(2));
                nv.setNgaySinh(rs.getString(3));
                nv.setDiaChi(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setEmail(rs.getString(6));
                nv.setGioiTinh(rs.getInt(7));
                nv.setHinhAnh(rs.getString(8));

                return nv;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static ArrayList<NhanVien> search(JTextField txt){
//        String text = txt.getText();
//        String sql = """
//                     select * from NhanVien
//                     WHERE Ma = '"""+text+"'";
//        ArrayList<NhanVien>nv = new ArrayList<>();
//        try {
//            Connection conn= DBUtils.getConnection();
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery(sql);
//            while (rs.next()) {                
//                int ma = Integer.parseInt(rs.getString(1));
//                String ten = rs.getString(2);
//                String ngaysinh = rs.getString(3);
//                 String diachi = rs.getString(4);
//                String sdt = rs.getString(5);
//                String email = rs.getString(6);
//                String ngaybatdau = rs.getString(7);
//                Boolean gt = rs.getBoolean(8);
//                 
//                Boolean vaitro = rs.getBoolean(9);
//                String ghichu = rs.getString(10);
//                 String hinh = rs.getString(11);
//                NhanVien k = new NhanVien(email, ten, ngaysinh, diachi, sdt, email, ngaybatdau, true, true, ghichu, hinh);
//                nv.add(k);
//            }
//        } catch (Exception e) {
//        }
//        return nv;
//    }
}
