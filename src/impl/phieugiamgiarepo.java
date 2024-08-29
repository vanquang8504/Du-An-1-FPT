package impl;

import DBConnect.DBConnect;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.phieugiamgia;
import java.sql.*;
import model.loaiphieu;
import model.nhanvien1;
import model.phieugiamgiachitiet;

public class phieugiamgiarepo {

    public List<phieugiamgia> getall(String key) {
        List<phieugiamgia> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT magiamgia, tengiamgia,giatrigiam,donhangtoithieu, a.ngaytao,thoigianbatdau,thoigianketthuc,tennhanvien,tenloaigiamgia,c.id_nhanvien,b.id,id_pgg from phieugiamgia a join LOAIGIAMGIA b on a.IDLOAIGIAMGIA=b.ID join NHANVIEN c on a.ID_NHANVIEN=c.ID_nhanvien where magiamgia like ? or tenloaigiamgia like ? or id_pgg like ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            stm.setString(2, "%" + key + "%");
            stm.setString(3, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgia(rs.getInt(1),
                        rs.getString(2), rs.getBigDecimal(3), rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<phieugiamgia> getdasd(String key) {
        List<phieugiamgia> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT magiamgia, tengiamgia,giatrigiam,donhangtoithieu, a.ngaytao,thoigianbatdau,thoigianketthuc,tennhanvien,tenloaigiamgia,c.id_nhanvien,b.id,id_pgg from phieugiamgia a join LOAIGIAMGIA b on a.IDLOAIGIAMGIA=b.ID join NHANVIEN c on a.ID_NHANVIEN=c.ID_nhanvien where id_pgg like ? and trangthai=1";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgia(rs.getInt(1),
                        rs.getString(2), rs.getBigDecimal(3), rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<nhanvien1> getnhanvien(String key) {
        List<nhanvien1> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select a.id_nhanvien,tennhanvien,tenchucvu from NHANVIEN a join TaiKhoanNV b on a.ID_NhanVien=b.ID_NhanVien join chucvu c on b.ID_ChucVu=c.ID_ChucVu where tenchucvu like N'%Quản lý%' and a.id_nhanvien like ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new nhanvien1(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<phieugiamgiachitiet> getphieugiamgiachitiet() {
        List<phieugiamgiachitiet> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select id_pgg,magiamgia,tengiamgia,giatrigiam,donhangtoithieu,b.id,tenloaigiamgia, thoigianketthuc, count(magiamgia) from PHIEUGIAMGIA a join LOAIGIAMGIA b on a.IDLOAIGIAMGIA=b.ID where DATEADD(DAY, 0, CURRENT_TIMESTAMP) >= THOIGIANBATDAU and THOIGIANKETTHUC >=  DATEADD(DAY, 0, CURRENT_TIMESTAMP) and trangthai = 0 group by id_pgg,magiamgia,tengiamgia,giatrigiam,donhangtoithieu,id, thoigianketthuc,tenloaigiamgia ";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgiachitiet(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<phieugiamgia> getdangdienra() {
        List<phieugiamgia> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = """
                        SELECT 
                             A.MAGIAMGIA,
                             A.TENGIAMGIA,
                             A.GIATRIGIAM,
                             A.DONHANGTOITHIEU,
                             A.NGAYTAO,
                             A.THOIGIANBATDAU,
                             A.THOIGIANKETTHUC,
                             C.TENNHANVIEN,
                             B.TENLOAIGIAMGIA,
                             C.ID_NHANVIEN,
                             B.ID,
                             A.ID_PGG,
                             A.TRANGTHAI
                         FROM 
                             PHIEUGIAMGIA A
                         JOIN 
                             LOAIGIAMGIA B ON A.IDLOAIGIAMGIA = B.ID
                         JOIN 
                             NHANVIEN C ON A.ID_NHANVIEN = C.ID_NHANVIEN
                         WHERE 
                             A.THOIGIANBATDAU <= CURRENT_TIMESTAMP
                             AND A.THOIGIANKETTHUC >= CURRENT_TIMESTAMP
                             AND A.TRANGTHAI=0""";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgia(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<phieugiamgia> getdaketthuc(String key) {
        List<phieugiamgia> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT magiamgia, tengiamgia,giatrigiam,donhangtoithieu, a.ngaytao,thoigianbatdau,thoigianketthuc,tennhanvien,tenloaigiamgia,c.id_nhanvien,b.id,id_pgg from phieugiamgia a join LOAIGIAMGIA b on a.IDLOAIGIAMGIA=b.ID join NHANVIEN c on a.ID_NHANVIEN=c.ID_nhanvien\n"
                    + "WHERE THOIGIANKETTHUC < DATEADD(DAY, 0, CURRENT_TIMESTAMP) and trangthai = 0  and id_pgg like ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgia(rs.getInt(1),
                        rs.getString(2), rs.getBigDecimal(3), rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<phieugiamgia> getsapdienra(String key) {
        List<phieugiamgia> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT magiamgia, tengiamgia,giatrigiam,donhangtoithieu, a.ngaytao,thoigianbatdau,thoigianketthuc,tennhanvien,tenloaigiamgia,c.id_nhanvien,b.id,id_pgg from phieugiamgia a join LOAIGIAMGIA b on a.IDLOAIGIAMGIA=b.ID join NHANVIEN c on a.ID_NHANVIEN=c.ID_nhanvien\n"
                    + "WHERE DATEADD(DAY, 0, CURRENT_TIMESTAMP) < THOIGIANBATDAU and THOIGIANKETTHUC >  DATEADD(DAY, 0, CURRENT_TIMESTAMP) and trangthai = 0  and id_pgg like ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new phieugiamgia(rs.getInt(1),
                        rs.getString(2), rs.getBigDecimal(3), rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<loaiphieu> getloaiphieu() {
        List<loaiphieu> list = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from loaigiamgia";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new loaiphieu(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertall(phieugiamgia pgg) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "INSERT INTO PHIEUGIAMGIA \n"
                    + "(MAGIAMGIA, TENGIAMGIA, giatrigiam,donhangtoithieu, NGAYTAO, THOIGIANBATDAU, THOIGIANKETTHUC, ID_NHANVIEN, IDLOAIGIAMGIA, TRANGTHAI)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,getdate(),?,?,?,?,0)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, pgg.getMaGiamGia());
            stm.setString(2, pgg.getTenGiamGia());
            stm.setBigDecimal(3, pgg.getGiaTriGiam());
            stm.setBigDecimal(4, pgg.getDonHangToiThieu());
            stm.setString(5, pgg.getNgayBatDau());
            stm.setString(6, pgg.getNgayKetThuc());
            stm.setInt(7, pgg.getMaNV());
            stm.setInt(8, pgg.getIdLoaiGG());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateall(phieugiamgia pgg, Integer id) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "update phieugiamgia set magiamgia =?, tengiamgia = ?, giatrigiam =?,donhangtoithieu=?,ngaytao = getdate(), thoigianbatdau = ?, thoigianketthuc = ?, id_nhanvien=?,idloaigiamgia=? where id_pgg=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, pgg.getMaGiamGia());
            stm.setString(2, pgg.getTenGiamGia());
            stm.setBigDecimal(3, pgg.getGiaTriGiam());
            stm.setBigDecimal(4, pgg.getDonHangToiThieu());
            stm.setString(5, pgg.getNgayBatDau());
            stm.setString(6, pgg.getNgayKetThuc());
            stm.setInt(7, pgg.getMaNV());
            stm.setInt(8, pgg.getIdLoaiGG());
            stm.setInt(9, id);
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteall(Integer id) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "delete from phieugiamgia where id_pgg=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
