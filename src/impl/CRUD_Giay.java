package impl;

import DBConnect.DBConnect;
import java.util.ArrayList;
import model.Giay;
import java.sql.*;
import service.Service_Giay;

public class CRUD_Giay implements Service_Giay {

    private String sql = null;
    private Connection connect = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Giay> list(int idGiay) {
        ArrayList<Giay> list = new ArrayList<>();
        sql = """
              SELECT
                    G.ID_Giay,
                    G.Ten_Giay,
                    X.Ten_XuatXu ,
                    CL.Ten_ChatLieu,
                    KD.Ten_KieuDe  ,
                    LL.Ten_LopLot  ,
                    KM.Ten_KieuMui  ,
                    DM.Ten_DanhMuc 
                FROM
                    Giay G
                JOIN
                    XuatXu X ON G.ID_XuatXu = X.ID_XuatXu
                JOIN
                    ChatLieu CL ON G.ID_ChatLieu = CL.ID_ChatLieu
                JOIN
                    KieuDe KD ON G.ID_KieuDe = KD.ID_KieuDe
                JOIN
                    LopLot LL ON G.ID_LopLot = LL.ID_LopLot
                JOIN
                    KieuMui KM ON G.ID_KieuMui = KM.ID_KieuMui
                JOIN
                    DanhMuc DM ON G.ID_DanhMuc = DM.ID_DanhMuc 
                
              """;
        try {
            if(idGiay!=0){
                sql+=" WHERE ID_GIAY="+idGiay;
            }
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Giay g = new Giay();
                g.setIdGiay(rs.getInt("ID_Giay"));
                g.setTenGiay(rs.getString("Ten_Giay"));
                g.setTenXuatXu(rs.getString("Ten_XuatXu"));
                g.setTenChatLieu(rs.getString("Ten_ChatLieu"));
                g.setTenKieuDe(rs.getString("Ten_KieuDe"));
                g.setTenLopLot(rs.getString("Ten_LopLot"));
                g.setTenKieuMui(rs.getString("Ten_KieuMui"));
                g.setTenDanhMuc(rs.getString("Ten_DanhMuc"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi A01: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Giay> listLoc(int LoaiTT, int IDTT) {
        ArrayList<Giay> list = new ArrayList<>();
        sql = """
              SELECT
                    G.ID_Giay,
                    G.Ten_Giay,
                    X.Ten_XuatXu ,
                    CL.Ten_ChatLieu,
                    KD.Ten_KieuDe  ,
                    LL.Ten_LopLot  ,
                    KM.Ten_KieuMui  ,
                    DM.Ten_DanhMuc 
                FROM
                    Giay G
                JOIN
                    XuatXu X ON G.ID_XuatXu = X.ID_XuatXu
                JOIN
                    ChatLieu CL ON G.ID_ChatLieu = CL.ID_ChatLieu
                JOIN
                    KieuDe KD ON G.ID_KieuDe = KD.ID_KieuDe
                JOIN
                    LopLot LL ON G.ID_LopLot = LL.ID_LopLot
                JOIN
                    KieuMui KM ON G.ID_KieuMui = KM.ID_KieuMui
                JOIN
                    DanhMuc DM ON G.ID_DanhMuc = DM.ID_DanhMuc
              """;
        setUp(LoaiTT, IDTT);
        try {
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Giay g = new Giay();
                g.setIdGiay(rs.getInt("ID_Giay"));
                g.setTenGiay(rs.getString("Ten_Giay"));
                g.setTenXuatXu(rs.getString("Ten_XuatXu"));
                g.setTenChatLieu(rs.getString("Ten_ChatLieu"));
                g.setTenKieuDe(rs.getString("Ten_KieuDe"));
                g.setTenLopLot(rs.getString("Ten_LopLot"));
                g.setTenKieuMui(rs.getString("Ten_KieuMui"));
                g.setTenDanhMuc(rs.getString("Ten_DanhMuc"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi A02: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Giay> listTimKiem(String timKiem) {
        ArrayList<Giay> list = new ArrayList<>();
        sql = """
              SELECT
                    G.ID_Giay,
                    G.Ten_Giay,
                    X.Ten_XuatXu ,
                    CL.Ten_ChatLieu,
                    KD.Ten_KieuDe  ,
                    LL.Ten_LopLot  ,
                    KM.Ten_KieuMui  ,
                    DM.Ten_DanhMuc 
                FROM
                    Giay G
                JOIN
                    XuatXu X ON G.ID_XuatXu = X.ID_XuatXu
                JOIN
                    ChatLieu CL ON G.ID_ChatLieu = CL.ID_ChatLieu
                JOIN
                    KieuDe KD ON G.ID_KieuDe = KD.ID_KieuDe
                JOIN
                    LopLot LL ON G.ID_LopLot = LL.ID_LopLot
                JOIN
                    KieuMui KM ON G.ID_KieuMui = KM.ID_KieuMui
                JOIN
                    DanhMuc DM ON G.ID_DanhMuc = DM.ID_DanhMuc 
                WHERE 
                    G.Ten_Giay like  ?
                    
              """;
        try {
            double check = -1234;
            try {
                check = Double.parseDouble(timKiem);
                sql += """
                                 OR ID_Giay = ?
 
                       """;
            } catch (NumberFormatException e) {
                System.out.println("Không Chuyển Thành Số.");
            }
            connect = DBConnect.getConnection();
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + timKiem + "%");
            if (check != -1234) {
                ps.setDouble(2, check);

            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Giay g = new Giay();
                g.setIdGiay(rs.getInt("ID_Giay"));
                g.setTenGiay(rs.getString("Ten_Giay"));
                g.setTenXuatXu(rs.getString("Ten_XuatXu"));
                g.setTenChatLieu(rs.getString("Ten_ChatLieu"));
                g.setTenKieuDe(rs.getString("Ten_KieuDe"));
                g.setTenLopLot(rs.getString("Ten_LopLot"));
                g.setTenKieuMui(rs.getString("Ten_KieuMui"));
                g.setTenDanhMuc(rs.getString("Ten_DanhMuc"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi A03: " + e.getMessage());
        }
        return list;
    }

    @Override
    public int create(Giay giay) {
        Integer row = null;
        Connection con = DBConnect.getConnection();
        String sql = """
                     INSERT INTO Giay (
                         Ten_Giay,
                         ID_XuatXu,
                         ID_ChatLieu,
                         ID_KieuDe,
                         ID_LopLot,
                         ID_KieuMui,
                         ID_DanhMuc
                     )
                     VALUES
                         (?,?,?,?,?,?,?);""";
        try {

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, giay.getTenGiay());
            pstm.setInt(2, giay.getIdXuatXu());
            pstm.setInt(3, giay.getIdChatLieu());
            pstm.setInt(4, giay.getIdKieuDe());
            pstm.setInt(5, giay.getIdLopLot());
            pstm.setInt(6, giay.getIdKieuMui());
            pstm.setInt(7, giay.getIdDanhMuc());

            row = pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return row;
    }

    @Override
    public int update(Giay giay) {
        Integer row = null;
        Connection con = DBConnect.getConnection();
        String sql = "	update giay set Ten_Giay= ?, ID_XuatXu = ?, ID_ChatLieu = ?, ID_KieuDe = ?, ID_LopLot = ?, ID_KieuMui = ?, ID_DanhMuc = ?\n"
                + "	where ID_Giay = ?";
        try {

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, giay.getTenGiay());
            pstm.setInt(2, giay.getIdXuatXu());
            pstm.setInt(3, giay.getIdChatLieu());
            pstm.setInt(4, giay.getIdKieuDe());
            pstm.setInt(5, giay.getIdLopLot());
            pstm.setInt(6, giay.getIdKieuMui());
            pstm.setInt(7, giay.getIdDanhMuc());
            pstm.setInt(8, giay.getIdGiay());

            row = pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return row;
    }

    @Override
    public int remove(int id) {
        int row = 0;
        Connection con = DBConnect.getConnection();
        String sql = """
                     delete Giay 
                     where ID_Giay = """ + id;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            row = pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return row;

    }

    private void setUp(int LoaiTT, int IDTT) {
        String name = "";
        switch (LoaiTT) {
            case 0 -> {
                name = "G.ID_XuatXu";
            }
            case 1 -> {
                name = "G.ID_DanhMuc";
            }
            case 2 -> {
                name = "G.ID_ChatLieu";
            }
            case 3 -> {
                name = "G.ID_KieuDe";
            }
            case 4 -> {
                name = "G.ID_KieuMui";
            }
            case 5 -> {
                name = "G.ID_LopLot";
            }
        }
        sql += " WHERE " + name + "=" + IDTT;
    }
}
