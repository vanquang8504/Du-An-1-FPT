/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import model.NhanVien;
import model.TaiKhoan;
import DBConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class QLTaiKhoan {
    public static void AddTK(TaiKhoan tk){
    String sql="insert into TaiKhoanNV(TenTK,MatKhau,ID_ChucVu,ID_NhanVien,TrangThai)"
            + "values(?,?,?,?,?)";
        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,tk.getTenTK());
            ps.setString(2,tk.getMatKhau());
            ps.setInt(3,tk.getIDCV());
            ps.setInt(4,tk.getIDNV());
            ps.setString(5,tk.getTrangThai());
            
            ps.executeUpdate();
        } catch (SQLException e) {
              e.printStackTrace(System.out);
        }
    }
    
    public static void updateTK(TaiKhoan tk){
    String sql = "Update TaiKhoanNV set TenTK=?,MatKhau=?,ID_ChucVu=?,ID_NhanVien=?,TrangThai=? where ID_TaiKhoan=?";
        try {
            Connection conn = DBConnect.getConnection();
        PreparedStatement ps= conn.prepareStatement(sql);
            
            ps.setString(1,tk.getTenTK());         
            ps.setString(2,tk.getMatKhau());
            ps.setInt(3,tk.getIDCV());
            ps.setInt(4,tk.getIDNV());
            ps.setString(5, tk.getTrangThai());
            ps.setInt(6,tk.getIDTK());
            ps.executeUpdate();
        } catch (SQLException e) {
               e.printStackTrace(System.out);
        }
    
    }
    
    public static void deleteTK(String ID){
    String sql="delete from TaiKhoanNV where ID_TaiKhoan=?";
    
        try {
             Connection conn= DBConnect.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, ID);
//            return ps.executeUpdate()>0;
    ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public ArrayList<TaiKhoan> getListTaiKhoan(){
        ArrayList<TaiKhoan> list= new ArrayList<>();
    String sql="select* from TaiKhoanNV ";
        try(Connection conn= DBConnect.getConnection()) {
            PreparedStatement ps= conn.prepareStatement(sql);
             ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk= new TaiKhoan();
                tk.setIDTK(rs.getInt(1));
               tk.setTenTK(rs.getString(2));
               tk.setMatKhau(rs.getString(3));
               tk.setIDCV(rs.getInt(4));
               tk.setIDNV(rs.getInt(5));
               tk.setTrangThai(rs.getString(6));
               list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
     public TaiKhoan getTaiKhoanByID(String AD){
    String sql= "SELECT * FROM TaiKhoanNV WHERE ID_TaiKhoan=?";
    
        try (Connection conn= DBConnect.getConnection()){
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,AD);
            
            ResultSet rs= ps.executeQuery();
             while (rs.next()) {
               TaiKhoan tk= new TaiKhoan();
               tk.setIDTK(rs.getInt(1));
              tk.setTenTK(rs.getString(2));
               tk.setMatKhau(rs.getString(3));
               tk.setIDCV(rs.getInt(4));
               tk.setIDNV(rs.getInt(5));
               tk.setTrangThai(rs.getString(6));
               
               
               return tk;
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
