/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import impl.CRUD_ThuocTinh;
import impl.CRUD_Giay;
import impl.CRUD_GiayCT;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Giay;
import model.Giay_ChiTiet;
import model.ThuocTinh;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Manh Dung
 */
public class Form_Giay extends javax.swing.JPanel {

    public Form_Giay(boolean role) {
        initComponents();
        this.loadAll();
        if (!role) {
            btnThem.setEnabled(false);
            btnThemSpCT.setEnabled(false);
            btnSua.setEnabled(false);
            btnSuaSpCT.setEnabled(false);
            btnXoaSpCT.setEnabled(false);
            btnMoi.setEnabled(false);
            btnMoiCt.setEnabled(false);
        }
    }

    DefaultTableModel Model = new DefaultTableModel();
    CRUD_Giay Service = new CRUD_Giay();
    CRUD_ThuocTinh Services_TT = new CRUD_ThuocTinh();
    CRUD_GiayCT Services_SPCT = new CRUD_GiayCT();
    int thuocTinhLoc, thuocTinhLocCT, indexSP = -1, indexSPCT = -1;
    String pathHinhAnh = "";

    private void loadAll() {
        loadData(Service.list(0));
        loadDataCT(Services_SPCT.list(0));
        this.addItemCbo(0);
        this.addItemCbo(1);
        this.addItemCbo(2);
        this.addItemCbo(3);
        this.addItemCbo(4);
        this.addItemCbo(5);
        this.addItemCbo(6);
        this.addItemCbo(7);
        this.addItemCbo(8);
        this.addItemCbo(9);

        cboLocThuocTinh.setVisible(false);
        cboLocThuocTinhSPCT.setVisible(false);

    }

    private void addItemCbo(int i) {
        ArrayList<ThuocTinh> ListThuocTinh = new ArrayList<>();
        switch (i) {
            case 0 -> {
                cboXuatXu.removeAllItems();
                cboXuatXu.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(0);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboXuatXu.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 1 -> {
                cboDanhMuc.removeAllItems();
                cboDanhMuc.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(1);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboDanhMuc.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 2 -> {
                cboChatLieu.removeAllItems();
                cboChatLieu.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(4);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboChatLieu.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 3 -> {
                cboKieuDe.removeAllItems();
                cboKieuDe.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(5);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboKieuDe.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 4 -> {
                cboKieuMui.removeAllItems();
                cboKieuMui.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(7);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboKieuMui.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 5 -> {
                cboLopLot.removeAllItems();
                cboLopLot.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(6);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLopLot.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 7 -> {
                cboMauSac.removeAllItems();
                cboMauSac.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(3);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboMauSac.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 8 -> {
                cboKichThuoc.removeAllItems();
                cboKichThuoc.addItem("Chọn");
                ListThuocTinh = Services_TT.listThuocTinh(2);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboKichThuoc.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
        }
    }

    //==============================Code Fame Sản Phẩm========================
    private void loadData(ArrayList<Giay> list) {
        Model = (DefaultTableModel) tblDanhSachGiay.getModel();
        Model.setRowCount(0);
        for (Giay o : list) {
            Model.addRow(new Object[]{
                o.getIdGiay(),
                o.getTenGiay(),
                o.getTenXuatXu(),
                o.getTenDanhMuc(),
                o.getTenChatLieu(),
                o.getTenKieuMui(),
                o.getTenKieuDe(),
                o.getTenLopLot()
            });
        }
    }

    private void addItemLoc() {
        cboLocThuocTinh.removeAllItems();
        cboLocThuocTinh.addItem("Tất Cả");
        ArrayList<ThuocTinh> ListThuocTinh = new ArrayList<>();
        thuocTinhLoc = cboLocLoaiTT.getSelectedIndex() - 1;
        switch (thuocTinhLoc) {
            case 0 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(0);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 1 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(1);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 2 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(4);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 3 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(5);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 4 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(7);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 5 -> {
                cboLocThuocTinh.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(6);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinh.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            default -> {
                cboLocThuocTinh.setVisible(false);
                loadData(Service.list(0));
            }
        }
    }

    private void showData(int id) {
        Giay o = Service.list(id).get(0);
        txtIDGiay.setText(String.valueOf(o.getIdGiay()));
        txtTenGiay.setText(o.getTenGiay());
        cboXuatXu.setSelectedItem(o.getTenXuatXu());
        cboDanhMuc.setSelectedItem(o.getTenDanhMuc());
        cboChatLieu.setSelectedItem(o.getTenChatLieu());
        cboKieuMui.setSelectedItem(o.getTenKieuMui());
        cboKieuDe.setSelectedItem(o.getTenKieuDe());
        cboLopLot.setSelectedItem(o.getTenLopLot());
    }

    private void clearGiay() {
        txtTenGiay.setText("");
        txtIDGiay.setText("Không Cần Nhập");
        cboXuatXu.setSelectedIndex(0);
        cboDanhMuc.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboKieuDe.setSelectedIndex(0);
        cboKieuMui.setSelectedIndex(0);
        cboLopLot.setSelectedIndex(0);

    }

    private Giay getInput() {
        Giay nv = new Giay();
        try {
            nv.setIdGiay(Integer.parseInt(txtIDGiay.getText()));
        } catch (NumberFormatException e) {
            nv.setIdGiay(0);
        }
        nv.setTenGiay(txtTenGiay.getText());
        nv.setIdXuatXu(cboXuatXu.getSelectedIndex());
        nv.setIdDanhMuc(cboDanhMuc.getSelectedIndex());
        nv.setIdChatLieu(cboChatLieu.getSelectedIndex());
        nv.setIdKieuDe(cboKieuDe.getSelectedIndex());
        nv.setIdKieuMui(cboKieuMui.getSelectedIndex());
        nv.setIdLopLot(cboLopLot.getSelectedIndex());
        return nv;

    }

    private boolean checkInput(Giay o) {
        String thongBao = "Bạn Chưa:";
        boolean check = false;
        if (o.getTenGiay().isEmpty()) {
            thongBao += "\n    +) Nhập Tên Giày";
            check = true;
        }
        if (o.getIdChatLieu() == 0) {
            thongBao += "\n    +) Chọn Chất Liệu";
            check = true;
        }
        if (o.getIdKieuDe() == 0) {
            thongBao += "\n    +) Chọn Kiểu Đế";
            check = true;
        }
        if (o.getIdDanhMuc() == 0) {
            thongBao += "\n    +) Chọn Danh Mục";
            check = true;
        }
        if (o.getIdKieuMui() == 0) {
            thongBao += "\n    +) Chọn Kiểu Mũi";
            check = true;
        }
        if (o.getIdLopLot() == 0) {
            thongBao += "\n    +) Chọn Lớp Lót";
            check = true;
        }
        if (o.getIdXuatXu() == 0) {
            thongBao += "\n    +) Chọn Xuất Xứ";
            check = true;
        }
        if (check) {
            JOptionPane.showMessageDialog(this, thongBao);
        }
        return check;
    }

    //==============================Code Fame SPCT===========================
    private void loadDataCT(ArrayList<Giay_ChiTiet> list) {
        Model = (DefaultTableModel) tblDanhSachGiayCT.getModel();
        Model.setRowCount(0);
        String trangThai = null;
        for (Giay_ChiTiet o : list) {
            Model.addRow(new Object[]{
                o.getIdGiayChiTiet(),
                o.getTenGiayChiTiet(),
                o.getTenGiay(),
                o.getMauSac(),
                o.getKichThuoc(),
                o.getGiaBan().toString().substring(0, o.getGiaBan().toString().length() - 2),
                o.getSoLuong(),
                o.getTrangThai()});
        }
        lblSoLuongSP.setText("Số Lượng SP: " + list.size());
    }

    private void showDataCT(int id) {
        txtIDGiayCT.setText(tblDanhSachGiayCT.getValueAt(indexSPCT, 0).toString());
        txtTenGiayCT.setText(tblDanhSachGiayCT.getValueAt(indexSPCT, 1).toString());
        cboMauSac.setSelectedItem(tblDanhSachGiayCT.getValueAt(indexSPCT, 3).toString());
        cboKichThuoc.setSelectedItem(tblDanhSachGiayCT.getValueAt(indexSPCT, 4).toString());
        txtGiaban.setText(tblDanhSachGiayCT.getValueAt(indexSPCT, 5).toString());
        txtSoluong.setText(tblDanhSachGiayCT.getValueAt(indexSPCT, 6).toString());
        if (tblDanhSachGiayCT.getValueAt(indexSPCT, 7).toString().contains("Đang")) {
            cboTrangThai.setSelectedIndex(0);
        } else {
            cboTrangThai.setSelectedIndex(1);
        }
        if (indexSP == -1) {
            id = 0;
        }
        for (Giay_ChiTiet o : Services_SPCT.list(id)) {

            setHinh(o.getHinhAnh());
        }
    }

    private void addItemLocCT() {
        cboLocThuocTinhSPCT.removeAllItems();
        cboLocThuocTinhSPCT.addItem("Tất Cả");
        ArrayList<ThuocTinh> ListThuocTinh = new ArrayList<>();
        thuocTinhLocCT = cboLocLoaiTTSPCT.getSelectedIndex() - 1;
        switch (thuocTinhLocCT) {
            case 0 -> {
                cboLocThuocTinhSPCT.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(8);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinhSPCT.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 1 -> {
                cboLocThuocTinhSPCT.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(3);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinhSPCT.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 2 -> {
                cboLocThuocTinhSPCT.setVisible(true);
                ListThuocTinh = Services_TT.listThuocTinh(2);
                for (int j = 0; j < ListThuocTinh.size(); j++) {
                    cboLocThuocTinhSPCT.addItem(ListThuocTinh.get(j).getTenThuocTinh());
                }
            }
            case 3 -> {
                cboLocThuocTinhSPCT.setVisible(true);
                cboLocThuocTinhSPCT.addItem("Ngừng Hoạt Động");
                cboLocThuocTinhSPCT.addItem("Đang Hoạt Động");
            }
            default -> {
                cboLocThuocTinhSPCT.setVisible(false);
            }
        }
    }

    private void clearGiayCT() {
        txtTenGiayCT.setText("");
        txtIDGiayCT.setText("Không Cần Nhập");

        cboTrangThai.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
        txtSoluong.setText("0");
        txtGiaban.setText("0");
        Avatar.setIcon(null);
        Avatar.setText("        Nhấn Vào Đây Để Chọn Ảnh");
        pathHinhAnh = "";
    }

    private Giay_ChiTiet getInputCT() {

        if (indexSP == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Giày");
            return null;
        }
        return new Giay_ChiTiet(
                Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 0).toString()),
                cboMauSac.getSelectedIndex(),
                cboKichThuoc.getSelectedIndex(),
                0,
                0,
                Integer.parseInt(txtSoluong.getText()),
                txtTenGiayCT.getText(),
                null,
                pathHinhAnh,
                new BigDecimal(txtGiaban.getText()),
                cboTrangThai.getSelectedItem().toString(),
                Integer.parseInt(tblDanhSachGiay.getValueAt(indexSP, 0).toString()),
                null);
    }

    private boolean checkInputCT(Giay_ChiTiet o) {
        boolean check = false;
        String thongBao = "Bạn Chưa: ";
        if (o.getTenGiayChiTiet().isEmpty()) {
            thongBao += "    \n+) Nhập Tên Giày Chi Tiết";
            check = true;
        }
        if (o.getIdGiay() == 0) {
            thongBao += "    \n+) Chọn Giày";
            check = true;
        }
        if (o.getIdKichThuoc() == 0) {
            thongBao += "    \n+) Chọn Kích Thước";
            check = true;
        }
        if (o.getIdMauSac() == 0) {
            thongBao += "    \n+) Chọn Màu Sắc";
            check = true;
        }
        if (o.getSoLuong() <= 0) {
            thongBao += "    \n+) Nhập Số Lượng Lớn Hơn 0";
            check = true;
        }
        if (Integer.parseInt(o.getGiaBan().toString()) <= 0) {
            thongBao += "    \n+) Nhập Giá Bán Lớn Hơn 1000";
            check = true;
        }
        if (o.getHinhAnh().isEmpty()) {
            thongBao += "    \n+) Thêm Hình Ảnh";
            check = true;
        }
        if (check) {
            JOptionPane.showMessageDialog(this, thongBao);
        }
        return check;
    }

    private void getHinhAnh() {
        JFileChooser jf = new JFileChooser("E:\\GhepCode\\PassMon\\New\\src\\avatar\\");
        if (jf.showSaveDialog(this) == 0) {
            pathHinhAnh = jf.getSelectedFile().getPath();
            setHinh(pathHinhAnh);
        }
    }

    private void setHinh(String path) {
        ImageIcon Avatar = new ImageIcon(path);
        pathHinhAnh = path;
        System.out.println(path);
        this.Avatar.setIcon(Avatar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FameSanPham = new javax.swing.JPanel();
        FameThongTin = new javax.swing.JPanel();
        lblGiay = new javax.swing.JLabel();
        lblIDGiay = new javax.swing.JLabel();
        lblKieuDe = new javax.swing.JLabel();
        lblChatLieu = new javax.swing.JLabel();
        lblLopLot = new javax.swing.JLabel();
        lblKieuMui = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        lblXuatXu = new javax.swing.JLabel();
        txtTenGiay = new javax.swing.JTextField();
        txtIDGiay = new javax.swing.JTextField();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboDanhMuc = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKieuDe = new javax.swing.JComboBox<>();
        cboKieuMui = new javax.swing.JComboBox<>();
        cboLopLot = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btn_addXuatXu = new javax.swing.JButton();
        btn_addDanhMuc = new javax.swing.JButton();
        btn_addChatLieu = new javax.swing.JButton();
        btn_addKieuDe = new javax.swing.JButton();
        btn_addKieuMui = new javax.swing.JButton();
        btn_addLopLot = new javax.swing.JButton();
        FameDanhSach = new javax.swing.JPanel();
        DanhSach = new javax.swing.JScrollPane();
        tblDanhSachGiay = new javax.swing.JTable();
        FameTimKiem = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        FameLoc = new javax.swing.JPanel();
        cboLocLoaiTT = new javax.swing.JComboBox<>();
        cboLocThuocTinh = new javax.swing.JComboBox<>();
        FameSanPhamChiTiet = new javax.swing.JPanel();
        FameThongTinCT = new javax.swing.JPanel();
        lblGiayCT = new javax.swing.JLabel();
        lblIDGiayCT = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtTenGiayCT = new javax.swing.JTextField();
        txtIDGiayCT = new javax.swing.JTextField();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        txtGiaban = new javax.swing.JTextField();
        Fame_HinhAnh = new javax.swing.JPanel();
        Avatar = new javax.swing.JLabel();
        btnThemSpCT = new javax.swing.JButton();
        btnSuaSpCT = new javax.swing.JButton();
        btnXoaSpCT = new javax.swing.JButton();
        btnMoiCt = new javax.swing.JButton();
        txtSoluong = new javax.swing.JTextField();
        btn_addMauSac = new javax.swing.JButton();
        btn_addKichThuoc = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        FameDanhSachSPCT = new javax.swing.JPanel();
        DanhSachSPCT = new javax.swing.JScrollPane();
        tblDanhSachGiayCT = new javax.swing.JTable();
        lblSoLuongSP = new javax.swing.JLabel();
        FameTimKiemCT = new javax.swing.JPanel();
        txtTimKiemSPCT = new javax.swing.JTextField();
        FameLocCT = new javax.swing.JPanel();
        cboLocLoaiTTSPCT = new javax.swing.JComboBox<>();
        cboLocThuocTinhSPCT = new javax.swing.JComboBox<>();

        FameSanPham.setBackground(new java.awt.Color(255, 255, 255));

        FameThongTin.setBackground(new java.awt.Color(255, 255, 255));
        FameThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        lblGiay.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblGiay.setText("Giày");

        lblIDGiay.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblIDGiay.setText("ID Giày");

        lblKieuDe.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblKieuDe.setText("Kiểu Đế");

        lblChatLieu.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblChatLieu.setText("Chất Liệu");

        lblLopLot.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblLopLot.setText("Lớp Lót");

        lblKieuMui.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblKieuMui.setText("Kiểu Mũi");

        lblDanhMuc.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblDanhMuc.setText("Danh Mục");

        lblXuatXu.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblXuatXu.setText("Xuất Xứ");

        txtTenGiay.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        txtIDGiay.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtIDGiay.setEnabled(false);

        cboXuatXu.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboXuatXu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboXuatXuFocusGained(evt);
            }
        });

        cboDanhMuc.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboDanhMuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboDanhMucFocusGained(evt);
            }
        });

        cboChatLieu.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboChatLieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboChatLieuFocusGained(evt);
            }
        });

        cboKieuDe.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboKieuDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboKieuDe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboKieuDeFocusGained(evt);
            }
        });

        cboKieuMui.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboKieuMui.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboKieuMuiFocusGained(evt);
            }
        });

        cboLopLot.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboLopLot.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboLopLotFocusGained(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btn_addXuatXu.setText("+");
        btn_addXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addXuatXuActionPerformed(evt);
            }
        });

        btn_addDanhMuc.setText("+");
        btn_addDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addDanhMucActionPerformed(evt);
            }
        });

        btn_addChatLieu.setText("+");
        btn_addChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addChatLieuActionPerformed(evt);
            }
        });

        btn_addKieuDe.setText("+");
        btn_addKieuDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addKieuDeActionPerformed(evt);
            }
        });

        btn_addKieuMui.setText("+");
        btn_addKieuMui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addKieuMuiActionPerformed(evt);
            }
        });

        btn_addLopLot.setText("+");
        btn_addLopLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addLopLotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FameThongTinLayout = new javax.swing.GroupLayout(FameThongTin);
        FameThongTin.setLayout(FameThongTinLayout);
        FameThongTinLayout.setHorizontalGroup(
            FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameThongTinLayout.createSequentialGroup()
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FameThongTinLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FameThongTinLayout.createSequentialGroup()
                                        .addComponent(lblKieuDe)
                                        .addGap(12, 12, 12)
                                        .addComponent(cboKieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_addKieuDe))
                                    .addGroup(FameThongTinLayout.createSequentialGroup()
                                        .addComponent(lblChatLieu)
                                        .addGap(12, 12, 12)
                                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_addChatLieu))))
                            .addGroup(FameThongTinLayout.createSequentialGroup()
                                .addComponent(lblKieuMui)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboKieuMui, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addKieuMui))
                            .addGroup(FameThongTinLayout.createSequentialGroup()
                                .addComponent(lblLopLot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboLopLot, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addLopLot)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FameThongTinLayout.createSequentialGroup()
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FameThongTinLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(34, 34, 34))
                            .addGroup(FameThongTinLayout.createSequentialGroup()
                                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDanhMuc)
                                    .addComponent(lblXuatXu))
                                .addGap(18, 18, 18)
                                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_addXuatXu)
                                    .addComponent(btn_addDanhMuc))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnMoi)
                        .addGap(196, 196, 196))))
        );

        FameThongTinLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChatLieu, lblKieuDe, lblKieuMui, lblLopLot});

        FameThongTinLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblDanhMuc, lblGiay, lblIDGiay, lblXuatXu});

        FameThongTinLayout.setVerticalGroup(
            FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinLayout.createSequentialGroup()
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDGiay)
                    .addComponent(txtIDGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDanhMuc)
                    .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_addDanhMuc)))
                .addGap(8, 8, 8)
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXuatXu)
                    .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addXuatXu))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoi)
                    .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua))))
            .addGroup(FameThongTinLayout.createSequentialGroup()
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboKieuMui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_addKieuMui))
                    .addGroup(FameThongTinLayout.createSequentialGroup()
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChatLieu)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addChatLieu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKieuDe)
                            .addComponent(cboKieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addKieuDe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblKieuMui)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLopLot)
                    .addComponent(cboLopLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addLopLot))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        FameDanhSach.setBackground(new java.awt.Color(255, 255, 255));
        FameDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Giày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        tblDanhSachGiay.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        tblDanhSachGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID Giày", "Giày"
            }
        ));
        tblDanhSachGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachGiayMouseClicked(evt);
            }
        });
        DanhSach.setViewportView(tblDanhSachGiay);
        if (tblDanhSachGiay.getColumnModel().getColumnCount() > 0) {
            tblDanhSachGiay.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        FameTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        FameTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập Để Tìm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout FameTimKiemLayout = new javax.swing.GroupLayout(FameTimKiem);
        FameTimKiem.setLayout(FameTimKiemLayout);
        FameTimKiemLayout.setHorizontalGroup(
            FameTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        FameTimKiemLayout.setVerticalGroup(
            FameTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameTimKiemLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FameDanhSachLayout = new javax.swing.GroupLayout(FameDanhSach);
        FameDanhSach.setLayout(FameDanhSachLayout);
        FameDanhSachLayout.setHorizontalGroup(
            FameDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameDanhSachLayout.createSequentialGroup()
                        .addComponent(FameTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(DanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                .addContainerGap())
        );
        FameDanhSachLayout.setVerticalGroup(
            FameDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameDanhSachLayout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(FameTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        FameLoc.setBackground(new java.awt.Color(255, 255, 255));
        FameLoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        cboLocLoaiTT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboLocLoaiTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Xuất Xứ", "Danh Mục", "Chất Liệu", "Kiểu Đế", "Kiểu Mũi", "Lớp Lót" }));
        cboLocLoaiTT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N
        cboLocLoaiTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocLoaiTTItemStateChanged(evt);
            }
        });

        cboLocThuocTinh.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboLocThuocTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N
        cboLocThuocTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocThuocTinhItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout FameLocLayout = new javax.swing.GroupLayout(FameLoc);
        FameLoc.setLayout(FameLocLayout);
        FameLocLayout.setHorizontalGroup(
            FameLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboLocLoaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocThuocTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        FameLocLayout.setVerticalGroup(
            FameLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboLocThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(cboLocLoaiTT)
        );

        FameSanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));

        FameThongTinCT.setBackground(new java.awt.Color(255, 255, 255));
        FameThongTinCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin CT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        lblGiayCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblGiayCT.setText("Giày CT");

        lblIDGiayCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblIDGiayCT.setText("ID Giày CT");

        lblKichThuoc.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblKichThuoc.setText("Kích Thước");

        lblMauSac.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblMauSac.setText("Màu Sắc");

        lblGiaBan.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblGiaBan.setText("Giá Bán");

        lblSoLuong.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblSoLuong.setText("Số Lượng");

        txtTenGiayCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtTenGiayCT.setEnabled(false);

        txtIDGiayCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtIDGiayCT.setEnabled(false);

        cboMauSac.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboMauSac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboMauSacFocusGained(evt);
            }
        });

        cboKichThuoc.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboKichThuoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboKichThuocFocusGained(evt);
            }
        });

        txtGiaban.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtGiaban.setText("0");
        txtGiaban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiabanKeyReleased(evt);
            }
        });

        Fame_HinhAnh.setBorder(javax.swing.BorderFactory.createTitledBorder("Hình Minh Họa"));
        Fame_HinhAnh.setMaximumSize(new java.awt.Dimension(235, 37));
        Fame_HinhAnh.setMinimumSize(new java.awt.Dimension(235, 37));

        Avatar.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        Avatar.setText("Hình ảnh");
        Avatar.setMaximumSize(new java.awt.Dimension(187, 14));
        Avatar.setMinimumSize(new java.awt.Dimension(187, 14));
        Avatar.setPreferredSize(new java.awt.Dimension(187, 14));
        Avatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvatarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Fame_HinhAnhLayout = new javax.swing.GroupLayout(Fame_HinhAnh);
        Fame_HinhAnh.setLayout(Fame_HinhAnhLayout);
        Fame_HinhAnhLayout.setHorizontalGroup(
            Fame_HinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HinhAnhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Fame_HinhAnhLayout.setVerticalGroup(
            Fame_HinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HinhAnhLayout.createSequentialGroup()
                .addComponent(Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnThemSpCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnThemSpCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThemSpCT.setText("Thêm");
        btnThemSpCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpCTActionPerformed(evt);
            }
        });

        btnSuaSpCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnSuaSpCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        btnSuaSpCT.setText("Sửa");
        btnSuaSpCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSpCTActionPerformed(evt);
            }
        });

        btnXoaSpCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnXoaSpCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnXoaSpCT.setText("Xóa");
        btnXoaSpCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSpCTActionPerformed(evt);
            }
        });

        btnMoiCt.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnMoiCt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnMoiCt.setText("Mới");
        btnMoiCt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiCtActionPerformed(evt);
            }
        });

        txtSoluong.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtSoluong.setText("0");
        txtSoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoluongKeyReleased(evt);
            }
        });

        btn_addMauSac.setText("+");
        btn_addMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addMauSacActionPerformed(evt);
            }
        });

        btn_addKichThuoc.setText("+");
        btn_addKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addKichThuocActionPerformed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblTrangThai.setText("Trạng Thái");

        cboTrangThai.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Hoạt Động", "Ngừng Hoạt Động" }));

        javax.swing.GroupLayout FameThongTinCTLayout = new javax.swing.GroupLayout(FameThongTinCT);
        FameThongTinCT.setLayout(FameThongTinCTLayout);
        FameThongTinCTLayout.setHorizontalGroup(
            FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoLuong)
                    .addComponent(lblGiaBan)
                    .addComponent(lblGiayCT)
                    .addComponent(lblIDGiayCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameThongTinCTLayout.createSequentialGroup()
                        .addComponent(btnThemSpCT)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSpCT)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSpCT)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoiCt))
                    .addGroup(FameThongTinCTLayout.createSequentialGroup()
                        .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenGiayCT)
                            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGiaban))
                        .addGap(18, 18, 18)
                        .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                                .addComponent(lblMauSac)
                                .addGap(32, 32, 32)
                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addMauSac))
                            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                                .addComponent(lblTrangThai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThai, 0, 1, Short.MAX_VALUE)
                                .addGap(29, 29, 29))))
                    .addGroup(FameThongTinCTLayout.createSequentialGroup()
                        .addComponent(txtIDGiayCT, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKichThuoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addKichThuoc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Fame_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        FameThongTinCTLayout.setVerticalGroup(
            FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiayCT)
                    .addComponent(txtTenGiayCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMauSac)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblKichThuoc)
                        .addComponent(txtIDGiayCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_addKichThuoc))
                    .addComponent(lblIDGiayCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTrangThai)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaBan)
                    .addComponent(txtGiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FameThongTinCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSpCT)
                    .addComponent(btnSuaSpCT)
                    .addComponent(btnXoaSpCT)
                    .addComponent(btnMoiCt)))
            .addGroup(FameThongTinCTLayout.createSequentialGroup()
                .addComponent(Fame_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        FameDanhSachSPCT.setBackground(new java.awt.Color(255, 255, 255));
        FameDanhSachSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Giày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        tblDanhSachGiayCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Giày CT", "Giày", "Màu Sắc", "Kích Thước", "Giá Bán", "Số Lượng", "Trạng Thái"
            }
        ));
        tblDanhSachGiayCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachGiayCTMouseClicked(evt);
            }
        });
        DanhSachSPCT.setViewportView(tblDanhSachGiayCT);
        if (tblDanhSachGiayCT.getColumnModel().getColumnCount() > 0) {
            tblDanhSachGiayCT.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        lblSoLuongSP.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblSoLuongSP.setText("Số Lượng Giày: ");

        FameTimKiemCT.setBackground(new java.awt.Color(255, 255, 255));
        FameTimKiemCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 15))); // NOI18N

        txtTimKiemSPCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtTimKiemSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        txtTimKiemSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPCTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout FameTimKiemCTLayout = new javax.swing.GroupLayout(FameTimKiemCT);
        FameTimKiemCT.setLayout(FameTimKiemCTLayout);
        FameTimKiemCTLayout.setHorizontalGroup(
            FameTimKiemCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
        );
        FameTimKiemCTLayout.setVerticalGroup(
            FameTimKiemCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameTimKiemCTLayout.createSequentialGroup()
                .addComponent(txtTimKiemSPCT)
                .addContainerGap())
        );

        javax.swing.GroupLayout FameDanhSachSPCTLayout = new javax.swing.GroupLayout(FameDanhSachSPCT);
        FameDanhSachSPCT.setLayout(FameDanhSachSPCTLayout);
        FameDanhSachSPCTLayout.setHorizontalGroup(
            FameDanhSachSPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameDanhSachSPCTLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(DanhSachSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(FameDanhSachSPCTLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(FameTimKiemCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FameDanhSachSPCTLayout.setVerticalGroup(
            FameDanhSachSPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameDanhSachSPCTLayout.createSequentialGroup()
                .addGroup(FameDanhSachSPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameDanhSachSPCTLayout.createSequentialGroup()
                        .addComponent(FameTimKiemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(FameDanhSachSPCTLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSoLuongSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(DanhSachSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        FameLocCT.setBackground(new java.awt.Color(255, 255, 255));
        FameLocCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N

        cboLocLoaiTTSPCT.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cboLocLoaiTTSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Giày", "Màu Sắc", "Kích Thước", "Trạng Thái" }));
        cboLocLoaiTTSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N
        cboLocLoaiTTSPCT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocLoaiTTSPCTItemStateChanged(evt);
            }
        });

        cboLocThuocTinhSPCT.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        cboLocThuocTinhSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        cboLocThuocTinhSPCT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocThuocTinhSPCTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout FameLocCTLayout = new javax.swing.GroupLayout(FameLocCT);
        FameLocCT.setLayout(FameLocCTLayout);
        FameLocCTLayout.setHorizontalGroup(
            FameLocCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLocCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboLocLoaiTTSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocThuocTinhSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        FameLocCTLayout.setVerticalGroup(
            FameLocCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLocCTLayout.createSequentialGroup()
                .addGroup(FameLocCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocLoaiTTSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocThuocTinhSPCT))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FameSanPhamChiTietLayout = new javax.swing.GroupLayout(FameSanPhamChiTiet);
        FameSanPhamChiTiet.setLayout(FameSanPhamChiTietLayout);
        FameSanPhamChiTietLayout.setHorizontalGroup(
            FameSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameSanPhamChiTietLayout.createSequentialGroup()
                .addComponent(FameThongTinCT, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(FameSanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FameLocCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameSanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FameDanhSachSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );
        FameSanPhamChiTietLayout.setVerticalGroup(
            FameSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameSanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FameThongTinCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FameLocCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(FameDanhSachSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout FameSanPhamLayout = new javax.swing.GroupLayout(FameSanPham);
        FameSanPham.setLayout(FameSanPhamLayout);
        FameSanPhamLayout.setHorizontalGroup(
            FameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FameLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FameSanPhamLayout.createSequentialGroup()
                        .addGroup(FameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FameThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FameDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FameSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        FameSanPhamLayout.setVerticalGroup(
            FameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameSanPhamLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(FameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FameSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FameSanPhamLayout.createSequentialGroup()
                        .addComponent(FameThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FameLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FameDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(FameSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(FameSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboXuatXuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboXuatXuFocusGained
        addItemCbo(0);
    }//GEN-LAST:event_cboXuatXuFocusGained

    private void cboDanhMucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboDanhMucFocusGained
        addItemCbo(1);
    }//GEN-LAST:event_cboDanhMucFocusGained

    private void cboChatLieuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboChatLieuFocusGained
        addItemCbo(2);
    }//GEN-LAST:event_cboChatLieuFocusGained

    private void cboKieuDeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboKieuDeFocusGained
        addItemCbo(3);
    }//GEN-LAST:event_cboKieuDeFocusGained

    private void cboKieuMuiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboKieuMuiFocusGained
        addItemCbo(4);
    }//GEN-LAST:event_cboKieuMuiFocusGained

    private void cboLopLotFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboLopLotFocusGained
        addItemCbo(5);
    }//GEN-LAST:event_cboLopLotFocusGained

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkInput(getInput())) {
            return;
        }
        if (Service.create(getInput()) != 0) {
            JOptionPane.showMessageDialog(this, "Hoàn Thành Thêm");
            loadData(Service.list(0));
        } else {
            JOptionPane.showMessageDialog(this, "Chưa Hoàn Thành Thêm");
        }
        indexSP = -1;
        addItemCbo(6);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (indexSP == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Dòng");
            return;
        }

        if (checkInput(getInput())) {
            return;
        }

        if (Service.update(getInput()) != 0) {
            JOptionPane.showMessageDialog(this, "Hoàn Thành Sửa");
            loadData(Service.list(0));
        } else {
            JOptionPane.showMessageDialog(this, "Chưa Hoàn Thành Sửa");
        }
        indexSP = -1;
        addItemCbo(6);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearGiay();
        indexSP = -1;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btn_addXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addXuatXuActionPerformed
        new View_SubThuocTinh(0).setVisible(true);
    }//GEN-LAST:event_btn_addXuatXuActionPerformed

    private void btn_addDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addDanhMucActionPerformed
        new View_SubThuocTinh(1).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addDanhMucActionPerformed

    private void btn_addChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addChatLieuActionPerformed
        new View_SubThuocTinh(4).setVisible(true);
    }//GEN-LAST:event_btn_addChatLieuActionPerformed

    private void btn_addKieuDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addKieuDeActionPerformed
        new View_SubThuocTinh(5).setVisible(true);
    }//GEN-LAST:event_btn_addKieuDeActionPerformed

    private void btn_addKieuMuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addKieuMuiActionPerformed
        new View_SubThuocTinh(7).setVisible(true);
    }//GEN-LAST:event_btn_addKieuMuiActionPerformed

    private void btn_addLopLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addLopLotActionPerformed
        new View_SubThuocTinh(6).setVisible(true);
    }//GEN-LAST:event_btn_addLopLotActionPerformed

    private void tblDanhSachGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachGiayMouseClicked
        indexSP = tblDanhSachGiay.getSelectedRow();
        showData(Integer.parseInt(tblDanhSachGiay.getValueAt(indexSP, 0).toString()));
        loadDataCT(Services_SPCT.list(Integer.valueOf(tblDanhSachGiay.getValueAt(indexSP, 0).toString())));
    }//GEN-LAST:event_tblDanhSachGiayMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        loadData(Service.listTimKiem(txtTimKiem.getText()));
        indexSPCT = -1;
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboLocLoaiTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocLoaiTTItemStateChanged
        addItemLoc();
    }//GEN-LAST:event_cboLocLoaiTTItemStateChanged

    private void cboLocThuocTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocThuocTinhItemStateChanged
        if (cboLocThuocTinh.getSelectedIndex() == 0) {
            loadData(Service.list(0));
        } else {
            loadData(Service.listLoc(thuocTinhLoc, cboLocThuocTinh.getSelectedIndex()));
        }
        indexSP = -1;
    }//GEN-LAST:event_cboLocThuocTinhItemStateChanged

    private void cboMauSacFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboMauSacFocusGained
        addItemCbo(7);
    }//GEN-LAST:event_cboMauSacFocusGained

    private void cboKichThuocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboKichThuocFocusGained
        addItemCbo(8);
    }//GEN-LAST:event_cboKichThuocFocusGained

    private void txtGiabanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiabanKeyReleased
        String id = txtGiaban.getText();
        if ("".equals(id)) {
            txtSoluong.setText("0");
            return;
        }
        if (!id.matches("\\d+")) {
            id = id.substring(0, id.length() - 1);
            JOptionPane.showMessageDialog(this, "Giá Chỉ Được Chứa Số");
            txtGiaban.setText(id);
        }
    }//GEN-LAST:event_txtGiabanKeyReleased

    private void AvatarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AvatarMouseClicked
        getHinhAnh();
    }//GEN-LAST:event_AvatarMouseClicked

    private void btnThemSpCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpCTActionPerformed

        if (getInputCT() != null) {
            if (checkInputCT(getInputCT())) {
                return;
            }
            if (Services_SPCT.create(getInputCT()) != 0) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công");
                loadDataCT(Services_SPCT.list(0));
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thất bại");
                return;
            }
        }
        indexSPCT = -1;
    }//GEN-LAST:event_btnThemSpCTActionPerformed

    private void btnSuaSpCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSpCTActionPerformed
        if (getInputCT() != null) {
            if (indexSPCT == -1) {
                JOptionPane.showMessageDialog(this, "Hãy Chọn Dòng");
                return;
            }
            if (checkInputCT(getInputCT())) {
                return;
            }
            if (Services_SPCT.update(getInputCT()) != 0) {
                JOptionPane.showMessageDialog(this, "Hoàn Thành Sửa");
                loadDataCT(Services_SPCT.list(0));
            } else {
                JOptionPane.showMessageDialog(this, "Chưa Hoàn Thành Sửa");
                return;
            }
        }
        indexSPCT = -1;
    }//GEN-LAST:event_btnSuaSpCTActionPerformed

    private void btnXoaSpCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSpCTActionPerformed
        if (indexSPCT == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Dòng");
            return;
        }
        if (Services_SPCT.remove((int) tblDanhSachGiayCT.getValueAt(tblDanhSachGiayCT.getSelectedRow(), 0)) != 0) {
            JOptionPane.showMessageDialog(this, "Hoàn Thành Xóa");
            loadDataCT(Services_SPCT.list(0));
        } else {
            JOptionPane.showMessageDialog(this, "Chưa Hoàn Thành Xóa");
            return;
        }
        indexSPCT = -1;
    }//GEN-LAST:event_btnXoaSpCTActionPerformed

    private void btnMoiCtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiCtActionPerformed
        clearGiayCT();
        indexSPCT -= 1;
    }//GEN-LAST:event_btnMoiCtActionPerformed

    private void txtSoluongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoluongKeyReleased
        String id = txtSoluong.getText();
        if ("".equals(id)) {
            txtSoluong.setText("0");
            return;
        }
        if (!id.matches("\\d+")) {
            id = id.substring(0, id.length() - 1);
            JOptionPane.showMessageDialog(this, "Số Lượng Chỉ Được Chứa Số");
            txtSoluong.setText(id);
        }
        if ("".equals(id)) {
            txtSoluong.setText("0");
        }
    }//GEN-LAST:event_txtSoluongKeyReleased

    private void btn_addMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addMauSacActionPerformed
        new View_SubThuocTinh(3).setVisible(true);
    }//GEN-LAST:event_btn_addMauSacActionPerformed

    private void btn_addKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addKichThuocActionPerformed
        new View_SubThuocTinh(2).setVisible(true);
    }//GEN-LAST:event_btn_addKichThuocActionPerformed

    private void tblDanhSachGiayCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachGiayCTMouseClicked
        indexSPCT = tblDanhSachGiayCT.getSelectedRow();
        showDataCT(Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 0).toString()));
    }//GEN-LAST:event_tblDanhSachGiayCTMouseClicked

    private void txtTimKiemSPCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPCTKeyReleased
        loadDataCT(Services_SPCT.listTimKiem(txtTimKiemSPCT.getText()));
        indexSPCT = -1;
    }//GEN-LAST:event_txtTimKiemSPCTKeyReleased

    private void cboLocLoaiTTSPCTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocLoaiTTSPCTItemStateChanged
        addItemLocCT();
    }//GEN-LAST:event_cboLocLoaiTTSPCTItemStateChanged

    private void cboLocThuocTinhSPCTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocThuocTinhSPCTItemStateChanged
        if (cboLocThuocTinhSPCT.getSelectedIndex() == 0) {
            loadDataCT(Services_SPCT.list(0));
        } else {
            loadDataCT(Services_SPCT.listLoc(thuocTinhLocCT, cboLocThuocTinhSPCT.getSelectedIndex()));
        }
        indexSPCT = -1;
    }//GEN-LAST:event_cboLocThuocTinhSPCTItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Avatar;
    private javax.swing.JScrollPane DanhSach;
    private javax.swing.JScrollPane DanhSachSPCT;
    private javax.swing.JPanel FameDanhSach;
    private javax.swing.JPanel FameDanhSachSPCT;
    private javax.swing.JPanel FameLoc;
    private javax.swing.JPanel FameLocCT;
    private javax.swing.JPanel FameSanPham;
    private javax.swing.JPanel FameSanPhamChiTiet;
    private javax.swing.JPanel FameThongTin;
    private javax.swing.JPanel FameThongTinCT;
    private javax.swing.JPanel FameTimKiem;
    private javax.swing.JPanel FameTimKiemCT;
    private javax.swing.JPanel Fame_HinhAnh;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnMoiCt;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaSpCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemSpCT;
    private javax.swing.JButton btnXoaSpCT;
    private javax.swing.JButton btn_addChatLieu;
    private javax.swing.JButton btn_addDanhMuc;
    private javax.swing.JButton btn_addKichThuoc;
    private javax.swing.JButton btn_addKieuDe;
    private javax.swing.JButton btn_addKieuMui;
    private javax.swing.JButton btn_addLopLot;
    private javax.swing.JButton btn_addMauSac;
    private javax.swing.JButton btn_addXuatXu;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboKieuDe;
    private javax.swing.JComboBox<String> cboKieuMui;
    private javax.swing.JComboBox<String> cboLocLoaiTT;
    private javax.swing.JComboBox<String> cboLocLoaiTTSPCT;
    private javax.swing.JComboBox<String> cboLocThuocTinh;
    private javax.swing.JComboBox<String> cboLocThuocTinhSPCT;
    private javax.swing.JComboBox<String> cboLopLot;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiay;
    private javax.swing.JLabel lblGiayCT;
    private javax.swing.JLabel lblIDGiay;
    private javax.swing.JLabel lblIDGiayCT;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblKieuDe;
    private javax.swing.JLabel lblKieuMui;
    private javax.swing.JLabel lblLopLot;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JTable tblDanhSachGiay;
    private javax.swing.JTable tblDanhSachGiayCT;
    private javax.swing.JTextField txtGiaban;
    private javax.swing.JTextField txtIDGiay;
    private javax.swing.JTextField txtIDGiayCT;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenGiay;
    private javax.swing.JTextField txtTenGiayCT;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemSPCT;
    // End of variables declaration//GEN-END:variables
}
