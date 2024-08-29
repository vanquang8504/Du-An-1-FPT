package impl;

import model.User;
import DBConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
//    List<User> ls= new ArrayList<>();
//    
//    public UserDao(){
//    ls.add(new User("admin", "12345"));
//    }

    public User getUserByID(String username) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = """
                     SELECT
                         TaiKhoanNV.TenTK,
                         NhanVien.TenNhanVien,
                         TaiKhoanNV.MatKhau,
                         TaiKhoanNV.ID_NhanVien,
                         TaiKhoanNV.ID_ChucVu
                     FROM
                         TaiKhoanNV
                     JOIN
                         NhanVien ON TaiKhoanNV.ID_NhanVien = NhanVien.ID_NhanVien
                     WHERE
                         TaiKhoanNV.TenTK = ?""";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User e = new User();
                        e.setUser(rs.getString(1));
                        e.setTenNv(rs.getString(2));
                        e.setPass(rs.getString(3));
                        e.setIdNv(rs.getInt(4));
                        if (rs.getInt(5) == 1) {
                            e.setRole(false);
                        } else {
                            e.setRole(true);
                        }
                        return e;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User checkLogin(String username, String pass) {
        User user = getUserByID(username);
        if (user != null) {
            if (user.getPass().equals(pass)) {
                return user;
            }
        }
        return null;
    }
}
