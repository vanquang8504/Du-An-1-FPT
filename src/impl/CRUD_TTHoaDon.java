package impl;

import DBConnect.DBConnect;
import java.util.ArrayList;
import java.sql.*;

public class CRUD_TTHoaDon {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String sql, name, id, table;

    public ArrayList<String> listTTHD(int i) {
        setUp(i);
        ArrayList<String> list=  new ArrayList<>();
        try {
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                list.add(rs.getString(name));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public void setUp(int i) {
        System.out.println(i);
        switch (i) {
            case 1 -> {
                table = "NhanVien";
                name = "tenNhanVien";
                break;
            }
            case 2 -> {
                table = "PHIEUGIAMGIA";
                name = "TENGIAMGIA";
                break;
            }
            case 3 -> {
                table = "DotGiamGia";
                name = "TENDOTGIAMGIA";
                break;
            }
            case 4 -> {
                table = "PhuongThucThanhToan";
                name = "TenPTTT";
                break;
            }
        }
        sql = "select " + name + " from " + table;
    }
    public static void main(String[] args) {
        for(String o:new CRUD_TTHoaDon().listTTHD(1)){
            System.out.println(o);
        }
    }
}
