
package impl;

import DBConnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import model.PD_CTiet;
import model.TT_Giay;

public class CTPDoi_DAO {
    private static final Connection conn = DBConnect.getConnection();
    public static ArrayList<PD_CTiet> get_CTP(String id){
        ArrayList<PD_CTiet> _list = new ArrayList<>();
        String sql = """
                     SELECT * FROM CTPDoi
                     WHERE ID_Phieu = ?""";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
                int ID_PD = rs.getInt(1);
                int ID_Giay = rs.getInt(2);
                String Ma_PD = rs.getString(3);
                int ID_CTPD = rs.getInt(4);
                int ID_TThai_P = rs.getInt(5);
                String TThai = rs.getString(6);
                int ID_LDo = rs.getInt(7);
                String LDo = rs.getString(8);
                int ID_Kho = rs.getInt(9);
                String Kho = rs.getString(10);
                int ID_SP_HD = rs.getInt(11);
                String Ten_SP_HD = rs.getString(12);
                double Gia_HD = rs.getDouble(13);
                int ID_SPD = rs.getInt(14);
                String Ten_SPD = rs.getString(15);
                double Gia_DOi = rs.getDouble(16);
                int SL_IN_HD = rs.getInt(17);
                int SLD = rs.getInt(18);
                String Note = rs.getString(19);
                PD_CTiet p = new PD_CTiet(ID_PD, ID_Giay, Ma_PD, ID_CTPD, ID_TThai_P, TThai, ID_LDo, LDo, ID_Kho,
                        Kho, ID_SP_HD, Ten_SP_HD, Gia_HD, ID_SPD, Ten_SPD, Gia_DOi, SL_IN_HD, SLD, Note);
                _list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return _list;
    }
    public static void UPDATE_DATA(PD_CTiet p){
        String sql = """
                     UPDATE ChiTietPhieu_ĐoiTra
                     set    ID_TrangThai = ?,
                            ID_LyDo = ?,
                            ID_Kho = ?,
                            ID_SP = ?,
                            ID_SPD = ?,
                            SoLuongDoi_Tra = ?,
                            GhiChu = ?
                     WHERE ID_Phieu_ChiTiet = ?""";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, p.getID_TThai_P());
            pr.setInt(2, p.getID_LDo());
            pr.setInt(3, p.getID_Kho());
            pr.setInt(4, p.getID_SP_HD());
            pr.setInt(5, p.getID_SPD());
            pr.setInt(6, p.getSLD());
            pr.setString(7, p.getNote());
            pr.setInt(8, p.getID_CTPD());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
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
    public static void Them_CTP(PD_CTiet p){
        String sql = """
                     INSERT INTO ChiTietPhieu_ĐoiTra
                     VALUES ((SELECT top 1 ID_Phieu FROM PhieuDoiTra ORDER BY ID_Phieu DESC), ?, ?, ?, ?, ?, ?, ?)""";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, p.getID_TThai_P());
            pr.setInt(2, p.getID_LDo());
            pr.setInt(3, p.getID_SP_HD());
            pr.setInt(4, p.getID_SPD());
            pr.setInt(5, p.getID_Kho());
            pr.setInt(6, p.getSLD());
            pr.setString(7, p.getNote());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
