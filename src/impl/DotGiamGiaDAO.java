/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import model.DotGiamGia;
//import model.PTTT;
//import model.Giay;
import DBConnect.DBConnect;
//import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.HashSet;
import model.DotGiamGiaCT;

/**
 *
 * @author nguyenkhanhdang
 */
public class DotGiamGiaDAO {

    Connection conn = null;
    PreparedStatement pstmt = null;

    public int add(DotGiamGia dgg) {
        try {
            String sSQL = "INSERT INTO DOTGIAMGIA(TenDotGiamGia,HinhAnh,NgayBatDau,NgayKetThuc,Giam,TrangThai,MoTa,ID_PTTT)"
                    + "values(?,?,?,?,?,?,?,?)";
            conn = DBConnect.getConnection();
            pstmt = conn.prepareStatement(sSQL);
            pstmt.setString(1, dgg.getTenDGG());
            pstmt.setString(2, dgg.getHinhAnh());
            pstmt.setString(3, dgg.getNgayBD());
            pstmt.setString(4, dgg.getNgayKT());
            pstmt.setDouble(5, dgg.getGiam());
            pstmt.setBoolean(6, dgg.isTrangThai());
            pstmt.setString(7, dgg.getMoTa());
            pstmt.setInt(8, dgg.getPTTT());
            if (pstmt.executeUpdate() > 0) {
                System.out.println("Insert thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }

        return -1;
    }

    public int addCT(DotGiamGiaCT dggct) {
        try {
            String sSQL = "INSERT INTO ChiTietDotGiamGia (ID_DotGiamGia,ID_GiayChiTiet) VALUES(?,?)";
            conn = DBConnect.getConnection();
            pstmt = conn.prepareStatement(sSQL);
            pstmt.setInt(1, dggct.getMaDGG());
            pstmt.setInt(2, dggct.getMaGiayCT());

            if (pstmt.executeUpdate() > 0) {
                System.out.println("Insert thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }

        return -1;
    }

    public int update(DotGiamGia dgg) {
        try {
            String sSQL = "UPDATE DotGiamGia SET TenDotGiamGia = ?,HinhAnh = ?,NgayBatDau = ?,NgayKetThuc = ?,Giam = ?,TrangThai = ?,MoTa = ?,ID_PTTT = ? WHERE ID_DotGiamGia = ?";
            conn = DBConnect.getConnection();
            pstmt = conn.prepareStatement(sSQL);
            pstmt.setInt(9, dgg.getMaDGG());
            pstmt.setString(1, dgg.getTenDGG());
            pstmt.setString(2, dgg.getHinhAnh());
            pstmt.setString(3, dgg.getNgayBD());
            pstmt.setString(4, dgg.getNgayKT());
            pstmt.setDouble(5, dgg.getGiam());
            pstmt.setBoolean(6, dgg.isTrangThai());
            pstmt.setString(7, dgg.getMoTa());
            pstmt.setInt(8, dgg.getPTTT());
            if (pstmt.executeUpdate() > 0) {
                System.out.println("Update thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }

        return -1;
    }
 

    public ArrayList<DotGiamGia> getAll() {
        ArrayList<DotGiamGia> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = """
                          SELECT DotGiamGia.ID_DotGiamGia,TenDotGiamGia,HinhAnh,NgayBatDau,NgayKetThuc,Giam,TrangThai,MoTa,DotGiamGia.ID_PTTT FROM DotGiamGia  
                          JOIN PhuongThucThanhToan ON DotGiamGia.ID_PTTT = PhuongThucThanhToan.ID_PTTT""";
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGia dgg = new DotGiamGia();
                
                dgg.setMaDGG(rs.getInt(1));
                dgg.setTenDGG(rs.getString(2));
                dgg.setHinhAnh(rs.getString(3));
                dgg.setNgayBD(rs.getString(4));
                dgg.setNgayKT(rs.getString(5));
                dgg.setGiam(rs.getDouble(6));
                dgg.setTrangThai(rs.getBoolean(7));
                dgg.setMoTa(rs.getString(8));
                dgg.setPTTT(rs.getInt(9));
                
                ls.add(dgg);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }
    
        public ArrayList<DotGiamGia> getAllLoc(List<Integer> selectedGiayChiTiet, String ID, String Ten, String NgayBD, String NgayKT, String GiamMin, String GiamMax, String TrangThai) {
        ArrayList<DotGiamGia> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;

        try {
            String itext = " ";
            if (selectedGiayChiTiet != null && !selectedGiayChiTiet.isEmpty()) {
                itext = "JOIN ChiTietDotGiamGia ON DotGiamGia.ID_DotGiamGia = ChiTietDotGiamGia.ID_DotGiamGia";
            }
            String sSQL = "SELECT DISTINCT DotGiamGia.ID_DotGiamGia,TenDotGiamGia,HinhAnh,NgayBatDau,NgayKetThuc,Giam,TrangThai,MoTa,DotGiamGia.ID_PTTT FROM DotGiamGia  \n"
                    + "                          JOIN PhuongThucThanhToan ON DotGiamGia.ID_PTTT = PhuongThucThanhToan.ID_PTTT " + itext + " WHERE 1=1";
            if (ID != null && !ID.isEmpty()) {
                sSQL += " AND ID_DotGiamGia = " + ID;
            }
            if (Ten != null && !Ten.isEmpty()) {
                sSQL += " AND TenDotGiamGia LIKE N'%" + Ten + "%'";
            }
            if (NgayBD != null && NgayKT != null && !NgayBD.isEmpty() && !NgayKT.isEmpty()) {
                sSQL += " AND NgayKetThuc > '" + NgayBD + "' AND NgayKetThuc < '" + NgayKT + "'";
            }
            if (NgayBD != null && NgayKT == null && !NgayBD.isEmpty()) {
                sSQL += " AND NgayKetThuc > '" + NgayBD + "'";
            }
            if (NgayBD == null && NgayKT != null && !NgayKT.isEmpty()) {
                sSQL += " AND NgayKetThuc < '" + NgayKT + "'";
            }
            if (GiamMin != null && GiamMax != null && !GiamMin.isEmpty() && !GiamMax.isEmpty()) {
                sSQL += " AND Giam >= " + GiamMin + " AND Giam <= " + GiamMax;
            }
            if (GiamMin != null && GiamMax == null && !GiamMin.isEmpty()) {
                sSQL += " AND Giam >= " + GiamMin;
            }
            if (GiamMin == null && GiamMax != null && !GiamMax.isEmpty()) {
                sSQL += " AND Giam <= " + GiamMax;
            }
            if (TrangThai != null && TrangThai.equals("1")) {
                sSQL += " AND TrangThai = 1";
            }
            if (TrangThai != null && TrangThai.equals("0")) {
                sSQL += " AND TrangThai = 0";
            }
            if (selectedGiayChiTiet != null && !selectedGiayChiTiet.isEmpty()) {
                String giayChiTietCondition = " AND ID_GiayChiTiet IN (";
                for (int i = 0; i < selectedGiayChiTiet.size(); i++) {
                    giayChiTietCondition += selectedGiayChiTiet.get(i);
                    if (i < selectedGiayChiTiet.size() - 1) {
                        giayChiTietCondition += ",";
                    }
                }
                giayChiTietCondition += ")";
                sSQL += giayChiTietCondition;
            }
            System.out.println(sSQL);
            conn = DBConnect.getConnection();
pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGia dgg = new DotGiamGia();

                dgg.setMaDGG(rs.getInt(1));
                dgg.setTenDGG(rs.getString(2));
                dgg.setHinhAnh(rs.getString(3));
                dgg.setNgayBD(rs.getString(4));
                dgg.setNgayKT(rs.getString(5));
                dgg.setGiam(rs.getDouble(6));
                dgg.setTrangThai(rs.getBoolean(7));
                dgg.setMoTa(rs.getString(8));
                dgg.setPTTT(rs.getInt(9));

                ls.add(dgg);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }
        
    public ArrayList<DotGiamGiaCT> getAllCT() {
        ArrayList<DotGiamGiaCT> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = "SELECT ID_DotGiamGia,ChiTietDotGiamGia.ID_GiayChiTiet,Ten_GiayChiTiet FROM ChiTietDotGiamGia JOIN Giay_ChiTiet ON ChiTietDotGiamGia.ID_GiayChiTiet = Giay_ChiTiet.ID_GiayChiTiet";
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGiaCT dggct = new DotGiamGiaCT();
                
                dggct.setMaDGG(rs.getInt(1));
                dggct.setMaGiayCT(rs.getInt(2));
                dggct.setTenGiay(rs.getString(3));
                
                
                ls.add(dggct);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }
    public ArrayList<DotGiamGiaCT> getAllCTF(int maDGG) {
        ArrayList<DotGiamGiaCT> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = "SELECT ID_DotGiamGia,ChiTietDotGiamGia.ID_GiayChiTiet,Ten_GiayChiTiet FROM ChiTietDotGiamGia JOIN Giay_ChiTiet ON ChiTietDotGiamGia.ID_GiayChiTiet = Giay_ChiTiet.ID_GiayChiTiet";
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGiaCT dggct = new DotGiamGiaCT();
                
                dggct.setMaDGG(rs.getInt(1));
                dggct.setMaGiayCT(rs.getInt(2));
                dggct.setTenGiay(rs.getString(3));
                
                
                ls.add(dggct);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }
    
    public int deleteCT(DotGiamGiaCT dggct){
        try {
            String sSQL = "DELETE FROM ChiTietDotGiamGia WHERE ID_DotGiamGia = ? AND ID_GiayChiTiet = ? ";
            conn = DBConnect.getConnection();
            pstmt = conn.prepareStatement(sSQL);
            pstmt.setInt(1, dggct.getMaDGG());
            pstmt.setInt(2, dggct.getMaGiayCT());

            if (pstmt.executeUpdate() > 0) {
                System.out.println("Delete thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }
        
        return -1;
    }

    public DotGiamGia find(DotGiamGia dgg) {
        ArrayList<DotGiamGia> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = "SELECT * FROM DotGiamGia WHERE ID_DotGiamGia = " + dgg.getMaDGG();
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                
                dgg.setMaDGG(Integer.parseInt(rs.getString(1)));
                dgg.setTenDGG(rs.getString(2));
                dgg.setHinhAnh(rs.getString(3));
                dgg.setNgayBD(rs.getString(4));
                dgg.setNgayKT(rs.getString(5));
                dgg.setGiam(Double.parseDouble(rs.getString(6)));
                dgg.setTrangThai(rs.getBoolean(7));
                dgg.setMoTa(rs.getString(8));
                return dgg;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
    public DotGiamGia getDangDienRa() {
        ArrayList<DotGiamGia> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = """
                      SELECT 
                           DotGiamGia.ID_DotGiamGia,
                           TenDotGiamGia,
                           HinhAnh,
                           NgayBatDau,
                           NgayKetThuc,
                           Giam,
                           TrangThai,
                           MoTa,
                           DotGiamGia.ID_PTTT
    
                      FROM DotGiamGia  
                           JOIN PhuongThucThanhToan ON DotGiamGia.ID_PTTT = PhuongThucThanhToan.ID_PTTT
                      WHERE TrangThai = 1
                            AND NgayBatDau <= GETDATE()
                            AND NgayKetThuc >= GETDATE() """;
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGia dgg = new DotGiamGia();
                
                dgg.setMaDGG(rs.getInt(1));
                dgg.setTenDGG(rs.getString(2));
                dgg.setHinhAnh(rs.getString(3));
                dgg.setNgayBD(rs.getString(4));
                dgg.setNgayKT(rs.getString(5));
                dgg.setGiam(rs.getDouble(6));
                dgg.setTrangThai(rs.getBoolean(7));
                dgg.setMoTa(rs.getString(8));
                dgg.setPTTT(rs.getInt(9));
                ls.add(dgg);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ls.get(0);
    }

    public ArrayList<DotGiamGiaCT> getAllCTforDGG(int idDGG) {
        ArrayList<DotGiamGiaCT> ls = new ArrayList<>();
        ResultSet rs = null;
        Statement pstmt = null;
        try {
            String sSQL = "SELECT "
                    + "         ID_DotGiamGia,"
                    + "         ChiTietDotGiamGia.ID_GiayChiTiet,"
                    + "         Ten_GiayChiTiet "
                    + "    FROM ChiTietDotGiamGia "
                    + "         JOIN Giay_ChiTiet ON ChiTietDotGiamGia.ID_GiayChiTiet = Giay_ChiTiet.ID_GiayChiTiet"
                    + "    WHERE ID_DotGiamGia=" + idDGG;
            conn = DBConnect.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sSQL);
            while (rs.next()) {
                DotGiamGiaCT dggct = new DotGiamGiaCT();

                dggct.setMaDGG(rs.getInt(1));
                dggct.setMaGiayCT(rs.getInt(2));
                dggct.setTenGiay(rs.getString(3));

                ls.add(dggct);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }

    public static void main(String[] args) {
        try {
            for (DotGiamGiaCT o : new DotGiamGiaDAO().getAllCTforDGG(new DotGiamGiaDAO().getDangDienRa().getMaDGG())) {
            System.out.println(o.getMaGiayCT());
        }
        } catch (Exception e) {
        }
    }
}
