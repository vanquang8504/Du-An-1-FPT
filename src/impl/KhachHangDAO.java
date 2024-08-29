/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import DBConnect.DBConnect;
import java.util.ArrayList;
import model.KhachHang;
import java.sql.*;
import javax.swing.JTextField;
import impl.NewInterface1;

/**
 *
 * @author Fpt06
 */
public class KhachHangDAO implements NewInterface1 {

    @Override
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select * from KHACHHANG";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                         rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public ArrayList<KhachHang> getAllKhachHangNam() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select ID_KhachHang,TenKH,GioiTinh,SoDT,DiaChi,TrangThai\n"
                + "from KHACHHANG\n"
                + "where GioiTinh ='1'";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                        rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public ArrayList<KhachHang> getAllKhachHangNu() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select ID_KhachHang,TenKH,GioiTinh,SoDT,DiaChi,TrangThai\n"
                + "from KHACHHANG\n  "
                +  "where GioiTinh ='0'";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                         rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public ArrayList<KhachHang> getAllHangKhachLaunam() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "  select ID_KhachHang,Tenkh,GioiTinh,SoDT,DiaChi,TrangThai\n" +
"                 from KHACHHANG\n " +
"                 where TrangThai ='1' ";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                        rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public ArrayList<KhachHang> getAllHangKhachMoi() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select ID_KhachHang,TenKH,GioiTinh,SoDT,DiaChi,TrangThai\n"
                + "from KHACHHANG\n"
                + "where TrangThai ='0'";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                         rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public void insert(KhachHang kh) {
        ArrayList<KhachHang> lst = new ArrayList<>();
        try {
            Connection cn = DBConnect.getConnection();
            Statement s = cn.createStatement();
            String sql = "select ID_KhachHang,TenKH,GioiTinh,SoDT,DiaChi,TrangThai from KHACHHANG";

            ResultSet pstm = s.executeQuery(sql);
            while (pstm.next()) {

                kh.setMaKhachHang(pstm.getInt(1));
                kh.setTenKhachHang(pstm.getString(2));
                kh.setGioiTinh(pstm.getBoolean(3));
                kh.setSdt(pstm.getString(5));
                kh.setHangKhach(pstm.getBoolean(10));
                lst.add(kh);
//            pstm.setString(5, kh.getDiaChi());
//            pstm.setString(1, kh.getTenKhachHang());
//            pstm.setBoolean(2, kh.isGioiTinh());
//            pstm.setString(4, kh.getEmail());
//            pstm.setString(3, kh.getSdt());
//            pstm.setBoolean(6, kh.isHangKhach());
            }
            cn.close();
            s.close();
            pstm.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public Integer update(KhachHang kh) {
        Integer row = null;
        String sql = "update KHACHHANG\n"
                + "set TenKH =?,GioiTinh=?,SoDT=?,DiaChi=?,TrangThai=?\n"
                + "where ID_KhachHang =?";
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, kh.getTenKhachHang());
            pstm.setBoolean(2, kh.isGioiTinh());
            pstm.setString(3, kh.getSdt());
            pstm.setString(4, kh.getDiaChi());
            pstm.setBoolean(5, kh.isHangKhach());
            pstm.setInt(6, kh.getMaKhachHang());
//            pstm.setInt(7, kh.getMaKhachHang());
//            pstm.setString(1, kh.getDiaChi());
//            pstm.setString(2, kh.getTenKhachHang());
//            pstm.setBoolean(3, kh.isGioiTinh());
//            pstm.setString(4, kh.getEmail());
//            pstm.setString(5, kh.getSdt());
//            pstm.setBoolean(6, kh.isHangKhach());
            row = pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public int find(int id) {
        int a = -1;
        for (int i = 0; i < getAllKhachHang().size(); i++) {
            if (getAllKhachHang().get(i).getMaKhachHang() == id) {
                a = i;
                break;
            }
        }
        return a;
    }

    public boolean ADD(KhachHang kh) {
        PreparedStatement pstm = null;
        Connection cn = null;
        try {
            cn = DBConnect.getConnection();
            pstm = cn.prepareStatement("insert into KHACHHANG(TenKH,GioiTinh,SoDT,DiaChi,TrangThai) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, kh.getTenKhachHang());
            pstm.setBoolean(2, kh.isGioiTinh());
            pstm.setString(3, kh.getSdt());
            pstm.setString(4, kh.getDiaChi());
            pstm.setBoolean(5, kh.isHangKhach());
//            pstm.setInt(7, kh.getMaKhachHang());
            int affectedRows = pstm.executeUpdate();

            // Lấy mã khách hàng tự động tạo
            if (affectedRows > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    kh.setMaKhachHang(generatedId);
                }
                rs.close();
            }
//pstm.executeUpdate();

            pstm.close();
            cn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public Integer delete(KhachHang kh) {
//        Integer row = 0;
//        try {
//            String url = "update KHACHHANG set TrangThai = ? \n"
//                    + " where ID_KhachHang = ?";
//            Connection con = DBConnect.getConnection();
//            PreparedStatement psm = con.prepareStatement(url);
//            psm.setBoolean(1, kh.isHangKhach());
//            psm.setInt(2, kh.getMaKhachHang());
//            row = psm.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return row;
//    }
}
