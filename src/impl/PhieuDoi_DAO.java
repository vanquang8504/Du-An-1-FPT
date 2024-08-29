
package impl;

import model.HoaDon;
import model.PhieuDoi;
import model.TT_Giay;
import javax.swing.JComboBox;
import java.sql.*;
import java.util.ArrayList;
import DBConnect.DBConnect;
import model.KhachHang;
import model.nhanvien11;

public class PhieuDoi_DAO {
    private static final Connection conn = DBConnect.getConnection();
    public static void getCBO(JComboBox cbo, String sql){
        int i = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                int id = rs.getInt(1);
                String EX = rs.getString(2);
                cbo.addItem(EX);
                System.out.println("ok = "+i++ );
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    public static ArrayList<nhanvien11> get_DSNV(){
        ArrayList<nhanvien11> _list = new ArrayList<>();
        String sql = """
                     SELECT nv.ID_NhanVien,TenNhanVien,TenChucVu 
                     FROM NhanVien nv 
                     JOIN TaiKhoanNV tk on nv.ID_NhanVien = tk.ID_NhanVien 
                     JOIN chucvu cv on cv.ID_ChucVu = tk.ID_ChucVu""";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                int ID_NV = rs.getInt(1);
                String Ten_NV = rs.getString(2);
                String CV = rs.getString(3);
                nhanvien11 nv = new nhanvien11(ID_NV, Ten_NV, CV);
                _list.add(nv);
            }
        } catch (SQLException e) {
            System.out.println(System.out);
        }
        return _list;
    }
    public static ArrayList<TT_Giay> get_view_all_SP(String sql){
        ArrayList<TT_Giay> _list = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {    
                int ID_HD = rs.getInt(1);
                int ID_ctg = rs.getInt(3);
                int ID_sp = rs.getInt(2);
                String Ten_sp = rs.getString(4);
                int SL = rs.getInt(5);
                double Gia_sp = rs.getDouble(6);
                int ID_IMG = rs.getInt(7);
                String Img_sp = rs.getString(8);
                TT_Giay sp = new TT_Giay(ID_HD, ID_ctg, ID_IMG, SL, ID_IMG, Ten_sp, Img_sp, Gia_sp);
                _list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    public static ArrayList<HoaDon> get_view_all_HD(String sql){
        ArrayList<HoaDon> _list = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int ID_hd = rs.getInt(1);
                int ID_kh = rs.getInt(2);
                String Ten_kh = rs.getString(3);
                int ID_nv = rs.getInt(4);
                String Ten_nv = rs.getString(5);
                float T_Tien = rs.getFloat(6);
                Date date = Date.valueOf(rs.getString(7));
                HoaDon hd = new HoaDon(ID_hd, ID_kh, Ten_kh, ID_nv, Ten_nv, T_Tien, date);
                _list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    
    public static ArrayList<TT_Giay> search_SP(String sql, String tk){
        ArrayList<TT_Giay> _list = new ArrayList<>();
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, tk);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) { 
                int ID_HD = rs.getInt(1);
                int ID_ctg = rs.getInt(3);
                int ID_sp = rs.getInt(2);
                String Ten_sp = rs.getString(4);
                int SL = rs.getInt(5);
                double Gia_sp = rs.getDouble(6);
                int ID_IMG = rs.getInt(7);
                String Img_sp = rs.getString(8);
                TT_Giay sp = new TT_Giay(ID_HD, ID_ctg, ID_IMG, SL, ID_IMG, Ten_sp, Img_sp, Gia_sp);
                _list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    public static ArrayList<HoaDon> search_HD(String sql, String tk){
        ArrayList<HoaDon> _list = new ArrayList<>();
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + tk + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) { 
                int ID_hd = rs.getInt(1);
                int ID_kh = rs.getInt(2);
                String Ten_kh = rs.getString(3);
                int ID_nv = rs.getInt(4);
                String Ten_nv = rs.getString(5);
                float T_Tien = rs.getFloat(6);
                Date date = Date.valueOf(rs.getString(7));
                HoaDon hd = new HoaDon(ID_hd, ID_kh, Ten_kh, ID_nv, Ten_nv, T_Tien, date);
                _list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    public static ArrayList<PhieuDoi> search_PD(String sql, String tk){
        ArrayList<PhieuDoi> _list = new ArrayList<>();
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%"+ tk +"%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) { 
                int ID_Phieu = rs.getInt(1);
                int ID_kh = rs.getInt(2);
                String Ten_kh = rs.getString(3);
                int ID_nv = rs.getInt(4);
                String Ten_nv = rs.getString(5);
                int ID_hd = rs.getInt(6);
                String date = rs.getString(7);
                PhieuDoi pd = new PhieuDoi(ID_Phieu, ID_kh, Ten_kh, ID_nv, Ten_nv, ID_hd, date);
                _list.add(pd);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    public static ArrayList<PhieuDoi> get_view_all_PD(String sql){
        ArrayList<PhieuDoi> _list = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int ID_Phieu = rs.getInt(1);
                int ID_kh = rs.getInt(2);
                String Ten_kh = rs.getString(3);
                int ID_nv = rs.getInt(4);
                String Ten_nv = rs.getString(5);
                int ID_hd = rs.getInt(6);
                String date = rs.getString(7);
                PhieuDoi pd = new PhieuDoi(ID_Phieu, ID_kh, Ten_kh, ID_nv, Ten_nv, ID_hd, date);
                _list.add(pd);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return _list;
    }
    public static void Add_PD(PhieuDoi p){
        String sql = """
                     INSERT INTO PhieuDoiTra (ID_KhachHang,ID_NhanVien,ID_HoaDon,Ngay_Doi)
                     VALUES ( ?, ?, ?, ?)
                     UPDATE PhieuDoiTra
                     SET Ma_Phieu = CONCAT('PD','000',ID_Phieu)""";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            
            pr.setInt(1, p.getID_K_Hang());
            pr.setInt(2, p.getID_N_Vien());
            pr.setInt(3, p.getID_H_Don());
            pr.setString(4, p.getN_Tao()+"");
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public static ArrayList<KhachHang> search_KH(String TK){
        ArrayList<KhachHang> _List = new ArrayList<>();
        String sql =    """
                        SELECT * FROM KHACHHANG
                        where sodt LIKE ? """;
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%"+ TK +"%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                        rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                         rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                _List.add(kh);
            }
        } catch (SQLException e) {
            System.out.println(System.out);
        }
        return _List;
    }
    public static ArrayList<TT_Giay> getSP_Doi(String ID){
        ArrayList<TT_Giay> list = new ArrayList<>();
        String sql = """
                     SELECT ID_Giay, ID_GiayChiTiet, Ten_GiayChiTiet, GiaBan
                     FROM Giay_ChiTiet 
                     WHERE ID_Giay = ?""";
        try {            
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, ID);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int ID_GCT = rs.getInt(2);
                String Ten_GCT = rs.getString(3);
                int ID_G = rs.getInt(1);
                double Gia = rs.getDouble(4);
                TT_Giay gct = new TT_Giay(ID_GCT, ID_G, Ten_GCT, Gia);
                list.add(gct);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi B01: \n" + e.getMessage());
            e.printStackTrace(System.out);
        }
        return list;
    }
}
