/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import model.HoaDon;
import model.PhieuDoi;
import model.TT_Giay;
import impl.PhieuDoi_DAO;
import Validate_form.VLD;
import impl.CTPDoi_DAO;
import impl.KhachHangDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.PD_CTiet;
import model.nhanvien11;

public class Form_PhieuDoi extends javax.swing.JPanel {
    DefaultTableModel tblModel = new DefaultTableModel();
    KhachHangDAO kh_dao = new KhachHangDAO();
    KhachHang kh = new KhachHang();
    private int poin = -1;

    private static final String sql_Cbo_ld = "SELECT * FROM LyDoDoiTra";
    private static final String sql_Cbo_tt = "SELECT * FROM TrangThaiPhieu";
    private static final String sql_Cbo_kho = "SELECT ID_Kho, Ten_Kho FROM Kho_Hang";
    private static final String sql_view_sp = "SELECT * FROM Show_SP";
    private static final String sql_view_hd = "SELECT * FROM Show_HD";
    private static final String sql_view_pd = "SELECT * FROM Show_PD";
    private static final String sql_Giay_tk = "SELECT * FROM Show_SP\n"
                                                + "WHERE ID_HoaDon = ?";
    private static final String sql_TK_HD = "SELECT * FROM Show_HD\n"
                                                + "WHERE ID_KhachHang LIKE ?";
    private static final String sql_TK_HD_ID = "SELECT * FROM Show_HD\n"
                                                + "WHERE ID_HoaDon LIKE ?";
    private static final String sql_TK_PD_KH = "SELECT * FROM Show_PD\n"
                                                + "WHERE TenKH LIKE ?";
    private static final String sql_TK_PD_NV = "SELECT * FROM Show_PD\n"
                                                + "WHERE TenNhanVien LIKE ?";
    private static final String sql_TK_PD_IDKH = "SELECT * FROM Show_PD\n"
                                                + "WHERE ID_KhachHang LIKE ? ";
    private static final String sql_TK_PD_IDHD = "SELECT * FROM Show_PD\n"
                                                + "WHERE ID_HoaDon LIKE ? ";
    private static final String sql_TK_HD_KH = "SELECT * FROM Show_HD\n"
                                                + "WHERE TenKH LIKE ?";
    private static final String sql_TK_HD_NV = "SELECT * FROM Show_HD\n"
                                                + "WHERE TenNhanVien LIKE ?";
    DecimalFormat number = new DecimalFormat("#.##");
    
    public static PhieuDoi pd;

    public Form_PhieuDoi() {
        initComponents();
        thread_timer(); 
        init_CBo();
        Fill_tbl_SP();
        Fill_tbl_HD();
        Fill_tbl_PD();
        Fill_TBL_KH();
        nhanvien.setVisible(false);
        jpn_sp_doi.setVisible(false);
    }

    private void thread_timer(){
        SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            Date date = new Date();
            String time_date = dates.format(date);
            
            jlb_timer.setText(time_date);
        });
        timer.start();
    }
    private void init_CBo(){
        PhieuDoi_DAO.getCBO(cbo_L_Do, sql_Cbo_ld);
        PhieuDoi_DAO.getCBO(cbo_T_Thai, sql_Cbo_tt);
        PhieuDoi_DAO.getCBO(cbo_kho, sql_Cbo_kho);
    }
    void Fill_TBL_KH(){
        tblModel = (DefaultTableModel) tbl_KH.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã","Khách hàng", "Số ĐT"
        };
        tblModel.setColumnIdentifiers(column);
        ArrayList<KhachHang> kh = kh_dao.getAllKhachHang();
        for (KhachHang k : kh) {
            Object[] row = {
                k.getMaKhachHang(), k.getTenKhachHang(), k.getSdt()
            };
            tblModel.addRow(row);
        }
    }
    KhachHang setModel(KhachHang kh){
        jlb_id_kh.setText(kh.getMaKhachHang()+"");
        txt_K_Hang.setText(kh.getTenKhachHang());
        return kh;
    }
    void CLick_KH(){
        ArrayList<KhachHang> kh = kh_dao.getAllKhachHang();
        int index = tbl_KH.getSelectedRow();
        Object values = tbl_KH.getValueAt(index, 0);
        setModel(kh.get(index));
        Find_PD(sql_TK_PD_IDKH, values+"");
        Find_HD( sql_TK_HD, values+"");
//        JOptionPane.showMessageDialog(this, values);
        
    }
    void click_HD(){
        int index = tbl_HD.getSelectedRow();
        Object values = tbl_HD.getValueAt(index, 0);
        setModel_HD(PhieuDoi_DAO.get_view_all_HD(sql_view_hd).get(index));
        Find_SP(values+"");
        Find_PD(sql_TK_PD_IDHD, values+"");
    }
    void Find_KH(){
        tblModel = (DefaultTableModel) tbl_KH.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã","Khách hàng", "Số ĐT"
        };
        tblModel.setColumnIdentifiers(column);
        for (KhachHang k : PhieuDoi_DAO.search_KH(txt_search_KH.getText())) {
            Object[] row = {
                k.getMaKhachHang(), k.getTenKhachHang(), k.getSdt()
            };
            tblModel.addRow(row);
        }
    }
    
    public void Fill_tbl_SP(){
        tblModel = (DefaultTableModel) tbl_TT_SP.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã HD", "Mã giày", "Mã giày CT", "Tên giày", "Số lượng", "Giá Bán", "Hình ảnh"
        };
        tblModel.setColumnIdentifiers(column);
        for ( TT_Giay g : PhieuDoi_DAO.get_view_all_SP(sql_view_sp)) {
            Object[] row = {
                g.getID_HD(), g.getId_giay(), g.getID_CTG(), g.getTGiay_CT(), g.getSLuong(), g.getGiaBan(), g.getImages()
            };
            tblModel.addRow(row);
        }
    }
    public void Click_sp(){
        int index = tbl_TT_SP.getSelectedRow();
        Object ID_CTG = tbl_TT_SP.getValueAt(index, 2);
        Object sp = tbl_TT_SP.getValueAt(index, 3);
        Object id_sp = tbl_TT_SP.getValueAt(index, 1);
        Object gia = tbl_TT_SP.getValueAt(index, 5);
        Object sluong = tbl_TT_SP.getValueAt(index, 4);
        txt_SPh.setText(sp+"");
        txt_old_Gia.setText(gia+"");
        jlb_id_sp.setText(id_sp+"");
        jlb_SL_MAX.setText(sluong+"");
        jlb_ID_CTSP.setText(ID_CTG+"");
        Fill_TBL_SPD();
        
    }
    public void Fill_tbl_HD(){
        tblModel = (DefaultTableModel) tbl_HD.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã HD", "Khách Hàng", "Nhân Viên", "Tổng Tiền", "Ngày Tạo"
        };
        tblModel.setColumnIdentifiers(column);
        for (HoaDon h : PhieuDoi_DAO.get_view_all_HD(sql_view_hd)) {
            Object[] row = {
                h.getID_HoaDon(), h.getK_Hang(), h.getN_Vien(), h.getT_Tien(), h.getN_Tao()
            };
            tblModel.addRow(row);
        }
    }
    void fill_nv(){
        tblModel = (DefaultTableModel) tbl_DSNV.getModel();
        tblModel.setRowCount(0);
        for (nhanvien11 nv : PhieuDoi_DAO.get_DSNV()) {
            Object[] row = {
                nv.getIDNV(), nv.getTenNhanVien(), nv.getChucVu()
            };
            tblModel.addRow(row);
        }
    }
    public HoaDon setModel_HD(HoaDon h){
        jlb_ID_HD.setText(h.getID_HoaDon()+"");
        txt_K_Hang.setText(h.getK_Hang());
        jlb_id_kh.setText(h.getID_K_Hang()+"");
        txt_N_Vien.setText(h.getN_Vien());
        jlb_id_nv.setText(h.getID_N_Vien()+"");
        txt_SPh.setText(h.getS_Pham());
        jlb_id_sp.setText(h.getID_S_Pham()+"");
        txt_old_Gia.setText(h.getG_Ban()+"");
        jlb_SL_MAX.setText(h.getS_Luong()+"");
        return h;
    }
    public void Fill_tbl_PD(){
        tblModel = (DefaultTableModel) tbl_PDoi.getModel();
        tblModel.setRowCount(0);
        for (PhieuDoi p : PhieuDoi_DAO.get_view_all_PD(sql_view_pd)) {
            Object[] row = {
                p.getID_Phieu(), p.getK_Hang(),
                p.getN_vien(), p.getID_H_Don(), p.getN_Tao()
            };
            tblModel.addRow(row);
        }
    }
    public PhieuDoi getModel(){
        PhieuDoi p = new PhieuDoi();
        p.setID_K_Hang(Integer.parseInt(jlb_id_kh.getText()));
        p.setID_N_Vien(Integer.parseInt(jlb_id_nv.getText()));
        p.setID_S_Pham(Integer.parseInt(jlb_id_sp.getText()));
        p.setSL_Doi(Integer.parseInt(txt_SL_doi.getText()));
        p.setG_Ban(Float.parseFloat(txt_old_Gia.getText()));
        p.setLyDo(cbo_L_Do.getSelectedIndex());
        p.setT_Thai(cbo_T_Thai.getSelectedIndex());
        p.setKho_luu(cbo_kho.getSelectedIndex());
        p.setNote(txt_Note.getText());
        p.setID_H_Don(Integer.parseInt(jlb_ID_HD.getText()));
        p.setN_Tao(jlb_timer.getText());
        return p;
    }
    
    public void Find_SP(String values){
        tblModel = (DefaultTableModel) tbl_TT_SP.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã HD", "Mã giày", "Mã giày CT", "Tên giày", "Số lượng", "Giá Bán", "Hình ảnh"
        };
        tblModel.setColumnIdentifiers(column);
        for ( TT_Giay g : PhieuDoi_DAO.search_SP(sql_Giay_tk, values)) {
            Object[] row = {
                g.getID_HD(), g.getId_giay(), g.getID_CTG(), g.getTGiay_CT(), g.getSLuong(), g.getGiaBan(), g.getImages()
            };
            tblModel.addRow(row);
        }
    }
    public void Find_HD( String sql, String values){
        tblModel = (DefaultTableModel) tbl_HD.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã HD", "Khách Hàng", "Nhân Viên", "Tổng Tiền", "Ngày Tạo"
        };
        tblModel.setColumnIdentifiers(column);
        for (HoaDon h : PhieuDoi_DAO.search_HD(sql, values)) {
            Object[] row = {
                h.getID_HoaDon(), h.getK_Hang(), h.getN_Vien(), h.getT_Tien(), h.getN_Tao()
            };
            tblModel.addRow(row);
        }
    }
    public void Find_PD(String sql, String tk){
        tblModel = (DefaultTableModel) tbl_PDoi.getModel();
        tblModel.setRowCount(0);
        for (PhieuDoi p : PhieuDoi_DAO.search_PD(sql, tk)) {
            Object[] row = {
                p.getID_Phieu(), p.getK_Hang(),
                p.getN_vien(), p.getID_H_Don(), p.getN_Tao()
            };
            tblModel.addRow(row);
        }
    }
    
    public PhieuDoi setModel(PhieuDoi p){
        jlb_ID_phieu.setText(p.getID_Phieu()+"");
        jlb_ID_HD.setText(p.getID_H_Don()+"");
        jlb_id_sp.setText(p.getID_S_Pham()+"");
        jlb_id_nv.setText(p.getID_N_Vien()+"");
        jlb_id_kh.setText(p.getID_K_Hang()+"");
        txt_K_Hang.setText(p.getK_Hang());
        txt_N_Vien.setText(p.getN_vien());
        txt_SPh.setText(p.getS_Pham());
        txt_SL_doi.setText(p.getSL_Doi()+"");
        jlb_SL_MAX.setText(p.getSL_Mua()+"");
        txt_old_Gia.setText(p.getG_Ban()+"");
        txt_Note.setText(p.getNote());
        cbo_L_Do.setSelectedIndex(p.getLyDo());
        cbo_T_Thai.setSelectedIndex(p.getT_Thai());
        cbo_kho.setSelectedIndex(p.getKho_luu());
        return p;
    }
    public void load_trang(){
        jlb_ID_phieu.setText("");
        jlb_ID_HD.setText("");
        jlb_id_sp.setText("");
        txt_SP_doi.setText("");
        jlb_ID_GCT.setText("");
        jlb_ID_CTSP.setText("");
        jlb_id_nv.setText("");
        jlb_id_kh.setText("");
        txt_K_Hang.setText("");
        txt_N_Vien.setText("");
        txt_SPh.setText("");
        txt_SL_doi.setText("");
        jlb_SL_MAX.setText("");
        txt_old_Gia.setText("");
        txt_Note.setText("");
        cbo_L_Do.setSelectedIndex(0);
        cbo_T_Thai.setSelectedIndex(0);
        txt_New_Gia.setText("");
        jlb_CThem.setText("");
        jlb_tra_khach.setText("");
        cbo_kho.setSelectedIndex(0);
        Fill_tbl_SP();
        Fill_tbl_HD();
        Fill_tbl_PD();
        Fill_TBL_KH();
        nhanvien.setVisible(false);
        jpn_sp_doi.setVisible(false);
    }
    public void them(){
        jlb_ID_phieu.setText("");
        jlb_ID_HD.setText("");
        jlb_id_sp.setText("");
        jlb_id_nv.setText("");
        jlb_id_kh.setText("");
        txt_K_Hang.setText("");
        txt_N_Vien.setText("");
        txt_SPh.setText("");
        txt_SL_doi.setText("");
        jlb_SL_MAX.setText("");
        txt_old_Gia.setText("");
        txt_Note.setText("");
        cbo_L_Do.setSelectedIndex(0);
        cbo_T_Thai.setSelectedIndex(0);
        txt_New_Gia.setText("");
        jlb_CThem.setText("");
        jlb_tra_khach.setText("");
        cbo_kho.setSelectedIndex(0);
    }
    public boolean vld_Add(){
        int i = 0; 
                int a = -1;
                int b = -1; 
                int c = -1;
        String msg = "";
            if (!VLD.check_null(txt_SL_doi)) {
                msg = msg + "Nhập số lượng cần đổi\n";
                i = -1;
            } else {
                try {
                  int sl_doi = Integer.parseInt(txt_SL_doi.getText());
                  if (sl_doi > Integer.parseInt(jlb_SL_MAX.getText()) || sl_doi <= 0) {
                    msg = msg + "Số lượng mua phải > 0 và <= số lượng max\n";
                    i = -1;
                  }
                } catch (NumberFormatException e) {
                    msg = msg + "Số lượng mua phải là số nguyên\n";
                    i = -1;
                }
            }
            a = tbl_TT_SP.getSelectedRow();
            b= tbl_SP_doi.getSelectedRow();
            if (a == -1) {
                msg = msg + "Chọn Sản phẩm cần đổi\n";
                i = -1;
            }
            if (b == -1) {
                msg = msg + "Chọn Sản phẩm đổi\n";
                i = -1;
            }   
            if (!VLD.check_cbox(cbo_L_Do)) {
                msg = msg + "Chọn lý do đổi\n";
                i = -1;
            }
            if (!VLD.check_cbox(cbo_T_Thai)) {
                msg = msg + "Chọn trạng thái phiếu\n";
                i = -1;
            }
            if (!VLD.check_cbox(cbo_kho)) {
                msg = msg + "Chọn kho lưu sản phẩm đổi\n";
                i = -1;
            }
        if (i == -1) {
            JOptionPane.showMessageDialog(this, msg);
            return false;
        } else {
            return true;
        }
    }
    public PD_CTiet getModel_CTP(){
        PD_CTiet p = new PD_CTiet();
        if (!jlb_ID_phieu.getText().isEmpty()) {
            p.setID_PD(Integer.parseInt(jlb_ID_phieu.getText()));
        }
        
        p.setID_TThai_P(cbo_T_Thai.getSelectedIndex());
        p.setID_LDo(cbo_L_Do.getSelectedIndex());
        p.setID_Kho(cbo_kho.getSelectedIndex());
        p.setID_SP_HD(Integer.parseInt(jlb_ID_CTSP.getText()));
        p.setID_SPD(Integer.parseInt(jlb_ID_GCT.getText()));
        p.setSLD(Integer.parseInt(txt_SL_doi.getText()));
        p.setNote(txt_Note.getText());
        return p;
    }
    
    public void ADD_P(){
        poin = tbl_HD.getSelectedRow();
        if (poin == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hoá đơn cần đổi hàng.");
        } else {
            if (!vld_Add()) {
            } else {
                int index = JOptionPane.showConfirmDialog(this, "Xác nhận tạo phiếu đổi", "Thông báo",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (index == 0) {
                    PhieuDoi_DAO.Add_PD(getModel());
                    CTPDoi_DAO.Them_CTP(getModel_CTP());
                    load_trang();
                    JOptionPane.showMessageDialog(this, "Thành công");
                }
            }
        }
        
    }
    public void TT_CT_P(){
        poin = tbl_PDoi.getSelectedRow();
        if (poin == -1) {
            JOptionPane.showMessageDialog(this, "Chọn phiếu đổi cần cập nhật");
        } else {
            pd = new PhieuDoi(jlb_ID_phieu.getText());
            new Form_CT_PDoi(null, true).setVisible(true);
        }
    }
    public void Xoa_phieu(){
        if (jlb_ID_phieu.getText().equals("") || jlb_ID_phieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn phiếu đổi cần Xoá");
        } else {
            int index = JOptionPane.showConfirmDialog(this, "Xác nhận xoá phiếu đổi", "Thông báo",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (index == 0) {
//                PhieuDoi_DAO.Delete_PD(sql_xoa_pd, jlb_ID_phieu.getText());
                load_trang();
                JOptionPane.showMessageDialog(this, "Thành công");
            }
                
        }
    }
    void search_PD_KH(){
        Find_HD(sql_TK_HD_KH, txt_search_pd_kh.getText());
        Find_PD(sql_TK_PD_KH, txt_search_pd_kh.getText());
    }
    void search_PD_NV(){
        Find_HD(sql_TK_HD_NV, txt_tk_pd_nv.getText());
        Find_PD(sql_TK_PD_NV, txt_tk_pd_nv.getText());
    }
    void click_PD(){
        int index = tbl_PDoi.getSelectedRow();
        Object values = tbl_PDoi.getValueAt(index, 3);
        Object id_phieu = tbl_PDoi.getValueAt(index, 0);
        setModel(PhieuDoi_DAO.get_view_all_PD(sql_view_pd).get(index));
        Find_HD(sql_TK_HD_ID, values+"");
        jlb_ID_phieu.setText(id_phieu+"");
    }
    void setModel_NV(nhanvien11 nv){
        jlb_id_nv.setText(nv.getIDNV()+"");
        txt_N_Vien.setText(nv.getTenNhanVien());
    }
    private void Fill_TBL_SPD(){
        tblModel = (DefaultTableModel) tbl_SP_doi.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã giày", "Mã giày CT", "Tên Giày CT", "Giá Bán"
        };
        tblModel.setColumnIdentifiers(column);
        for (TT_Giay o : PhieuDoi_DAO.getSP_Doi( jlb_id_sp.getText())) {
            tblModel.addRow(new Object[]{
                o.getId_giay(),
                o.getID_CTG(),
                o.getTGiay_CT(),
                o.getGiaBan()
            });
        }
        jlb_SL_SP_CTD.setText("Số Lượng SP: " + PhieuDoi_DAO.getSP_Doi( jlb_id_sp.getText()).size());
    }
    private void Clicked_SPD (){
        poin = tbl_SP_doi.getSelectedRow();
        Object id_gct = tbl_SP_doi.getValueAt(poin, 0);
        Object T_CT = tbl_SP_doi.getValueAt(poin, 2);
        Object G_new = tbl_SP_doi.getValueAt(poin, 3);
        jlb_ID_GCT.setText(id_gct+"");
        txt_SP_doi.setText(T_CT+"");
        txt_New_Gia.setText(G_new+"");
        jpn_sp_doi.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_K_Hang = new javax.swing.JTextField();
        txt_SPh = new javax.swing.JTextField();
        cbo_T_Thai = new javax.swing.JComboBox<>();
        cbo_L_Do = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Note = new javax.swing.JTextArea();
        btn_Tao = new javax.swing.JButton();
        btn_load_form = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_old_Gia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_N_Vien = new javax.swing.JTextField();
        jlb_id_kh = new javax.swing.JLabel();
        jlb_id_sp = new javax.swing.JLabel();
        jlb_id_nv = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlb_SL_MAX = new javax.swing.JLabel();
        jlb_ID_HD = new javax.swing.JLabel();
        jlb_ID_phieu = new javax.swing.JLabel();
        nhanvien = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_DSNV = new javax.swing.JTable();
        btn_hide = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_search_nv = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btn_input_NV = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txt_SP_doi = new javax.swing.JTextField();
        btn_doi = new javax.swing.JButton();
        jlb_ID_GCT = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_New_Gia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlb_CThem = new javax.swing.JLabel();
        jlb_tra_khach = new javax.swing.JLabel();
        txt_SL_doi = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cbo_kho = new javax.swing.JComboBox<>();
        jlb_ID_CTSP = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_Inphieu = new javax.swing.JButton();
        btn_load_trang = new javax.swing.JButton();
        txt_search_KH = new javax.swing.JTextField();
        txt_tk_pd_nv = new javax.swing.JTextField();
        txt_search_pd_kh = new javax.swing.JTextField();
        btn_Show_PD = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_TT_SP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_HD = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_PDoi = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_KH = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jpn_sp_doi = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_SP_doi = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jlb_SL_SP_CTD = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlb_timer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Khách hàng");

        jLabel7.setText("Sản phẩm");

        jLabel8.setText("SL đổi");

        jLabel9.setText("Lý do");

        jLabel10.setText("Trạng thái ");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("Thông tin phiếu");

        txt_K_Hang.setEnabled(false);

        txt_SPh.setEnabled(false);

        cbo_T_Thai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái phiếu" }));

        cbo_L_Do.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lý do đổi" }));

        jLabel12.setText("Ghi chú");

        txt_Note.setColumns(20);
        txt_Note.setRows(5);
        jScrollPane1.setViewportView(txt_Note);

        btn_Tao.setText("Tạo phiếu");
        btn_Tao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoActionPerformed(evt);
            }
        });

        btn_load_form.setText("Thêm mới");
        btn_load_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_load_formActionPerformed(evt);
            }
        });

        jLabel14.setText("Giá cũ");

        txt_old_Gia.setEnabled(false);

        jLabel15.setText("Nhân viên");

        txt_N_Vien.setEnabled(false);

        jlb_id_kh.setForeground(new java.awt.Color(255, 255, 255));
        jlb_id_kh.setToolTipText("");

        jlb_id_sp.setForeground(new java.awt.Color(255, 255, 255));
        jlb_id_sp.setToolTipText("");

        jlb_id_nv.setForeground(new java.awt.Color(255, 255, 255));
        jlb_id_nv.setToolTipText("");

        jLabel5.setText("SL đổi MAX");

        jlb_SL_MAX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlb_SL_MAX.setForeground(new java.awt.Color(255, 0, 0));
        jlb_SL_MAX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_SL_MAX.setText("0");
        jlb_SL_MAX.setToolTipText("");
        jlb_SL_MAX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlb_ID_HD.setForeground(new java.awt.Color(255, 255, 255));
        jlb_ID_HD.setToolTipText("");

        jlb_ID_phieu.setForeground(new java.awt.Color(255, 255, 255));
        jlb_ID_phieu.setToolTipText("");

        nhanvien.setBackground(new java.awt.Color(255, 255, 255));
        nhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tbl_DSNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TÊN NHÂN VIÊN", "CHỨC VỤ"
            }
        ));
        tbl_DSNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DSNVMouseClicked(evt);
            }
        });
        tbl_DSNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_DSNVKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_DSNV);

        btn_hide.setText("HUỶ");
        btn_hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hideActionPerformed(evt);
            }
        });

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel18.setText("TÌM KIẾM");

        txt_search_nv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_nvKeyReleased(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Nhân viên");

        javax.swing.GroupLayout nhanvienLayout = new javax.swing.GroupLayout(nhanvien);
        nhanvien.setLayout(nhanvienLayout);
        nhanvienLayout.setHorizontalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nhanvienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(nhanvienLayout.createSequentialGroup()
                        .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nhanvienLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(nhanvienLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(nhanvienLayout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(btn_hide)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        nhanvienLayout.setVerticalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_search_nv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_hide)
                .addContainerGap())
        );

        btn_input_NV.setText("Chọn nhân viên");
        btn_input_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_input_NVActionPerformed(evt);
            }
        });

        jLabel19.setText("SP đổi");

        txt_SP_doi.setEnabled(false);

        btn_doi.setText("Đổi");
        btn_doi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiActionPerformed(evt);
            }
        });

        jlb_ID_GCT.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Giá mới");

        txt_New_Gia.setEnabled(false);

        jLabel21.setText("Tiền thêm");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Trả lại ");

        jlb_CThem.setForeground(new java.awt.Color(255, 0, 0));
        jlb_CThem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_CThem.setText("XXX");

        jlb_tra_khach.setForeground(new java.awt.Color(255, 0, 0));
        jlb_tra_khach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_tra_khach.setText("XXX");

        txt_SL_doi.setText("0");
        txt_SL_doi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_SL_doiCaretUpdate(evt);
            }
        });

        jLabel23.setText("Kho nhận");

        cbo_kho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kho hàng" }));

        jlb_ID_CTSP.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jlb_ID_CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlb_id_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jlb_id_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlb_id_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(59, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_K_Hang)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_N_Vien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_input_NV, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_SPh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_doi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_old_Gia)
                                            .addComponent(txt_SL_doi))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlb_SL_MAX, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                            .addComponent(txt_New_Gia)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_SP_doi))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_T_Thai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_L_Do, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_kho, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_CThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlb_tra_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jlb_ID_GCT, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlb_ID_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlb_ID_phieu, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(btn_Tao)
                                        .addGap(43, 43, 43)
                                        .addComponent(btn_load_form)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jScrollPane1)))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jlb_id_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_id_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_id_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_ID_CTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_K_Hang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_N_Vien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_input_NV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_SPh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_doi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_SP_doi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_SL_MAX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txt_SL_doi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_old_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_New_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_CThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(jlb_tra_khach)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbo_L_Do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbo_T_Thai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbo_kho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlb_ID_phieu, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlb_ID_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlb_ID_GCT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_load_form)
                    .addComponent(btn_Tao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(153, 153, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btn_Inphieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icon-print.png"))); // NOI18N
        btn_Inphieu.setText("In phiếu");
        btn_Inphieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InphieuActionPerformed(evt);
            }
        });

        btn_load_trang.setText("Load DS");
        btn_load_trang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_load_trangActionPerformed(evt);
            }
        });

        txt_search_KH.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        txt_search_KH.setForeground(new java.awt.Color(102, 102, 102));
        txt_search_KH.setText("TK KH theo SDT");
        txt_search_KH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_search_KHCaretUpdate(evt);
            }
        });
        txt_search_KH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_search_KHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_search_KHFocusLost(evt);
            }
        });

        txt_tk_pd_nv.setForeground(new java.awt.Color(102, 102, 102));
        txt_tk_pd_nv.setText("TK PD theo NV");
        txt_tk_pd_nv.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tk_pd_nvCaretUpdate(evt);
            }
        });
        txt_tk_pd_nv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tk_pd_nvFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tk_pd_nvFocusLost(evt);
            }
        });

        txt_search_pd_kh.setForeground(new java.awt.Color(102, 102, 102));
        txt_search_pd_kh.setText("TK PD theo KH");
        txt_search_pd_kh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_search_pd_khCaretUpdate(evt);
            }
        });
        txt_search_pd_kh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_search_pd_khFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_search_pd_khFocusLost(evt);
            }
        });

        btn_Show_PD.setText("Thông tin phiếu đổi");
        btn_Show_PD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Show_PDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_search_KH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tk_pd_nv)
                    .addComponent(txt_search_pd_kh)
                    .addComponent(btn_Inphieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(btn_Show_PD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_load_trang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_load_trang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txt_search_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search_pd_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tk_pd_nv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Show_PD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Inphieu, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setText("Sản phẩm trong hoá đơn");

        tbl_TT_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã SPCT", "Tên SP", "Xuất xứ", "Chất liệu", "Màu sắc", "Size", "Số lượng", "Giá bán", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_TT_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TT_SPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_TT_SP);

        tbl_HD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID HĐ", "Khách hàng", "Nhân viên", "Tổng tiền", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_HD);
        if (tbl_HD.getColumnModel().getColumnCount() > 0) {
            tbl_HD.getColumnModel().getColumn(0).setResizable(false);
            tbl_HD.getColumnModel().getColumn(0).setPreferredWidth(40);
        }

        jLabel3.setText("Hoá đơn bán hàng");

        jLabel4.setText("Phiếu đổi");

        tbl_PDoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID phiếu", "Khách hàng", "Nhân viên tạo", "Mã hoá đơn", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PDoiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_PDoi);

        tbl_KH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Khách hàng", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KHMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_KH);
        if (tbl_KH.getColumnModel().getColumnCount() > 0) {
            tbl_KH.getColumnModel().getColumn(0).setResizable(false);
            tbl_KH.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        jLabel16.setText("Khách hàng");

        tbl_SP_doi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_SP_doi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SP_doiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_SP_doi);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Sản phẩm có thể đổi");

        jlb_SL_SP_CTD.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jlb_SL_SP_CTD.setText("Số sản phẩm : . . .");

        javax.swing.GroupLayout jpn_sp_doiLayout = new javax.swing.GroupLayout(jpn_sp_doi);
        jpn_sp_doi.setLayout(jpn_sp_doiLayout);
        jpn_sp_doiLayout.setHorizontalGroup(
            jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlb_SL_SP_CTD, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jpn_sp_doiLayout.setVerticalGroup(
            jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb_SL_SP_CTD)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpn_sp_doi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 456, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn_sp_doi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jlb_timer.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jlb_timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_timer.setText("times");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đổi hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlb_timer, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlb_timer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoActionPerformed
        ADD_P();
    }//GEN-LAST:event_btn_TaoActionPerformed

    private void btn_load_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_load_formActionPerformed
        them();
    }//GEN-LAST:event_btn_load_formActionPerformed

    private void btn_load_trangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_load_trangActionPerformed
        load_trang();
    }//GEN-LAST:event_btn_load_trangActionPerformed

    private void txt_search_KHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_search_KHCaretUpdate
        Find_KH();
    }//GEN-LAST:event_txt_search_KHCaretUpdate

    private void txt_search_KHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_search_KHFocusGained
        if (txt_search_KH.getText().trim().toLowerCase().equalsIgnoreCase("TK KH theo SDT")) {

            txt_search_KH.setForeground(Color.black);
            txt_search_KH.setText("");

        }
    }//GEN-LAST:event_txt_search_KHFocusGained

    private void txt_search_KHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_search_KHFocusLost
        if (txt_search_KH.getText().trim().toLowerCase().equalsIgnoreCase("TK KH theo SDT") ||
            txt_search_KH.getText().trim().toLowerCase().equals("")) {

            txt_search_KH.setForeground(new Color(153, 153, 153));
            txt_search_KH.setText("TK KH theo SDT");
        }
    }//GEN-LAST:event_txt_search_KHFocusLost

    private void txt_tk_pd_nvCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tk_pd_nvCaretUpdate
        search_PD_NV();
    }//GEN-LAST:event_txt_tk_pd_nvCaretUpdate

    private void txt_tk_pd_nvFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tk_pd_nvFocusGained
        if (txt_tk_pd_nv.getText().trim().toLowerCase().equalsIgnoreCase("TK PD theo NV")) {

            txt_tk_pd_nv.setForeground(Color.black);
            txt_tk_pd_nv.setText("");
        }
    }//GEN-LAST:event_txt_tk_pd_nvFocusGained

    private void txt_tk_pd_nvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tk_pd_nvFocusLost
        if (txt_tk_pd_nv.getText().trim().toLowerCase().equalsIgnoreCase("TK PD theo NV") ||
            txt_tk_pd_nv.getText().trim().toLowerCase().equals("")) {

            txt_tk_pd_nv.setForeground(new Color(153, 153, 153));
            txt_tk_pd_nv.setText("TK PD theo NV");
        }
    }//GEN-LAST:event_txt_tk_pd_nvFocusLost

    private void txt_search_pd_khCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_search_pd_khCaretUpdate
        search_PD_KH();
    }//GEN-LAST:event_txt_search_pd_khCaretUpdate

    private void txt_search_pd_khFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_search_pd_khFocusGained
        if (txt_search_pd_kh.getText().trim().toLowerCase().equalsIgnoreCase("TK PD theo KH")) {

            txt_search_pd_kh.setForeground(Color.black);
            txt_search_pd_kh.setText("");
        }
    }//GEN-LAST:event_txt_search_pd_khFocusGained

    private void txt_search_pd_khFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_search_pd_khFocusLost
        if (txt_search_pd_kh.getText().trim().toLowerCase().equalsIgnoreCase("TK PD theo KH") ||
            txt_search_pd_kh.getText().trim().toLowerCase().equals("")) {

            txt_search_pd_kh.setForeground(new Color(153, 153, 153));
            txt_search_pd_kh.setText("TK PD theo KH");
        }
    }//GEN-LAST:event_txt_search_pd_khFocusLost

    private void tbl_TT_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TT_SPMouseClicked
        Click_sp();
    }//GEN-LAST:event_tbl_TT_SPMouseClicked

    private void tbl_HDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HDMouseClicked
        click_HD();
    }//GEN-LAST:event_tbl_HDMouseClicked

    private void tbl_PDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PDoiMouseClicked
        click_PD();
    }//GEN-LAST:event_tbl_PDoiMouseClicked

    private void btn_InphieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InphieuActionPerformed
        JOptionPane.showMessageDialog(this, "Chức năng đang được nâng cấp.");
    }//GEN-LAST:event_btn_InphieuActionPerformed

    private void tbl_KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KHMouseClicked
        CLick_KH();
    }//GEN-LAST:event_tbl_KHMouseClicked

    private void tbl_DSNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DSNVMouseClicked
        int index = tbl_DSNV.getSelectedRow();
        setModel_NV(PhieuDoi_DAO.get_DSNV().get(index));
        nhanvien.setVisible(false);
    }//GEN-LAST:event_tbl_DSNVMouseClicked

    private void tbl_DSNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_DSNVKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_DSNVKeyReleased

    private void btn_hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hideActionPerformed
        nhanvien.setVisible(false);
    }//GEN-LAST:event_btn_hideActionPerformed

    private void txt_search_nvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_nvKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_nvKeyReleased

    private void btn_input_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_input_NVActionPerformed
        fill_nv();
        nhanvien.setVisible(true);
    }//GEN-LAST:event_btn_input_NVActionPerformed

    private void btn_doiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiActionPerformed
        poin = tbl_TT_SP.getSelectedRow();
        if (poin == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần đổi");
        } else {
            Fill_TBL_SPD();
            jpn_sp_doi.setVisible(true);
        }
        
    }//GEN-LAST:event_btn_doiActionPerformed

    private void tbl_SP_doiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SP_doiMouseClicked
        Clicked_SPD();
    }//GEN-LAST:event_tbl_SP_doiMouseClicked

    private void txt_SL_doiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_SL_doiCaretUpdate
        
//        String ThanhTien;
//        if (txt_SL_doi.getText().equals("") || txt_SL_doi.getText().isEmpty()) {
//            jlb_tra_khach.setText("0");
//            jlb_CThem.setText("0");
//        } else if (!txt_SL_doi.getText().isEmpty() || !txt_SL_doi.getText().equals("")) {
//            int SL_D = Integer.parseInt(txt_SL_doi.getText());
//            int SLmax = Integer.parseInt(jlb_SL_MAX.getText());
//            if (SL_D > SLmax) {
//                jlb_tra_khach.setText("0");
//                jlb_CThem.setText("0");
//            } else {
//                double new_Gia = Double.parseDouble(txt_New_Gia.getText());
//                double ole_Gia = Double.parseDouble(txt_old_Gia.getText());
//                double TTien = (SLmax*ole_Gia) - (SL_D*new_Gia);
//                if (TTien > 0) {
//                    ThanhTien = number.format(TTien);
//                    jlb_tra_khach.setText(ThanhTien);
//                    jlb_CThem.setText("0");
//
//                } else {
//                    ThanhTien = number.format(Math.copySign(TTien, 1.0));
//                    jlb_tra_khach.setText("0");
//                    jlb_CThem.setText(ThanhTien);
//                }
//            }
//        }
        String ThanhTien;
    String txt_SL_doi_Text = txt_SL_doi.getText();
    if (txt_SL_doi_Text == null || txt_SL_doi_Text.equals("")) {
        jlb_tra_khach.setText("0");
        jlb_CThem.setText("0");
    } else {
        try {
            int SL_D = Integer.parseInt(txt_SL_doi_Text);
            int SLmax = Integer.parseInt(jlb_SL_MAX.getText());
            if (SL_D > SLmax) {
                jlb_tra_khach.setText("0");
                jlb_CThem.setText("0");
            } else {
                double new_Gia = Double.parseDouble(txt_New_Gia.getText());
                double ole_Gia = Double.parseDouble(txt_old_Gia.getText());
                double TTien = (SLmax*ole_Gia) - (SL_D*new_Gia);
                if (TTien > 0) {
                    ThanhTien = number.format(TTien);
                    jlb_tra_khach.setText(ThanhTien);
                    jlb_CThem.setText("0");
                } else {
                    ThanhTien = number.format(Math.copySign(TTien, 1.0));
                    jlb_tra_khach.setText("0");
                    jlb_CThem.setText(ThanhTien);
                }
            }
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ ở đây
        }
    }
    }//GEN-LAST:event_txt_SL_doiCaretUpdate

    private void btn_Show_PDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Show_PDActionPerformed
        TT_CT_P();
        
    }//GEN-LAST:event_btn_Show_PDActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Inphieu;
    private javax.swing.JButton btn_Show_PD;
    private javax.swing.JButton btn_Tao;
    private javax.swing.JButton btn_doi;
    private javax.swing.JButton btn_hide;
    private javax.swing.JButton btn_input_NV;
    private javax.swing.JButton btn_load_form;
    private javax.swing.JButton btn_load_trang;
    private javax.swing.JComboBox<String> cbo_L_Do;
    private javax.swing.JComboBox<String> cbo_T_Thai;
    private javax.swing.JComboBox<String> cbo_kho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel jlb_CThem;
    private javax.swing.JLabel jlb_ID_CTSP;
    private javax.swing.JLabel jlb_ID_GCT;
    private javax.swing.JLabel jlb_ID_HD;
    private javax.swing.JLabel jlb_ID_phieu;
    private javax.swing.JLabel jlb_SL_MAX;
    private javax.swing.JLabel jlb_SL_SP_CTD;
    private javax.swing.JLabel jlb_id_kh;
    private javax.swing.JLabel jlb_id_nv;
    private javax.swing.JLabel jlb_id_sp;
    private javax.swing.JLabel jlb_timer;
    private javax.swing.JLabel jlb_tra_khach;
    private javax.swing.JPanel jpn_sp_doi;
    private javax.swing.JPanel nhanvien;
    private javax.swing.JTable tbl_DSNV;
    private javax.swing.JTable tbl_HD;
    private javax.swing.JTable tbl_KH;
    private javax.swing.JTable tbl_PDoi;
    private javax.swing.JTable tbl_SP_doi;
    private javax.swing.JTable tbl_TT_SP;
    private javax.swing.JTextField txt_K_Hang;
    private javax.swing.JTextField txt_N_Vien;
    private javax.swing.JTextField txt_New_Gia;
    private javax.swing.JTextArea txt_Note;
    private javax.swing.JTextField txt_SL_doi;
    private javax.swing.JTextField txt_SP_doi;
    private javax.swing.JTextField txt_SPh;
    private javax.swing.JTextField txt_old_Gia;
    private javax.swing.JTextField txt_search_KH;
    private javax.swing.JTextField txt_search_nv;
    private javax.swing.JTextField txt_search_pd_kh;
    private javax.swing.JTextField txt_tk_pd_nv;
    // End of variables declaration//GEN-END:variables

}
