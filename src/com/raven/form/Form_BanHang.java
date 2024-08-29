package com.raven.form;

import impl.CRUD_GiayCT;
import impl.CRUD_HoaDon;
import impl.CRUD_HoaDonCT;
import impl.CRUD_ThuocTinh;
import impl.DotGiamGiaDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.DotGiamGiaCT;
import model.Giay_ChiTiet;
import model.GioHang;
import model.HoaDonChiTiet;
import model.HoaDonCho;
import model.HoaDon_1;
import model.ThuocTinh;

public class Form_BanHang extends javax.swing.JPanel {

    public Form_BanHang(int idNV, String tenNhanVien) {
        initComponents();
        this.idNV = idNV;
        this.tenNhanVien = tenNhanVien;
        btnCapNhat.setEnabled(false);
        btnVouCher.setEnabled(false);
        loadView();
    }

    private final CRUD_GiayCT Services_SPCT = new CRUD_GiayCT();
    private final CRUD_ThuocTinh Services_TT = new CRUD_ThuocTinh();
    private final CRUD_HoaDon Services_HD = new CRUD_HoaDon();
    private final CRUD_HoaDonCT Services_HDCT = new CRUD_HoaDonCT();
    private final DotGiamGiaDAO Services_DGGCT = new DotGiamGiaDAO();

    ArrayList<ArrayList<GioHang>> dsGioHang = new ArrayList<>();
    ArrayList<HoaDonCho> listHoaDon = new ArrayList<>();

    DefaultTableModel Model = new DefaultTableModel();
    int indexSPCT = -1;
    int thuocTinhLocCT;
    int idKH = 1;
    int idNV = 1;
    int indexHD = -1;
    int indexGH = -1;
    int hinhThucTT = 3;
    int idDotgg = 0;

    private void updateTienGGDGG(ArrayList<GioHang> list) {
        try {
            int tienGG = 0;
            idDotgg = Services_DGGCT.getDangDienRa().getMaDGG();
            int aa = Services_DGGCT.getDangDienRa().getPTTT();
            if (aa != 0) {
                hinhThucTT = aa;
            }
            double giamPhanTram = Services_DGGCT.getDangDienRa().getGiam();
            for (GioHang o : list) {
                for (DotGiamGiaCT o1 : Services_DGGCT.getAllCTforDGG(idDotgg)) {
                    if (o.getIdSanPham() == o1.getMaGiayCT()) {
                        System.out.println("\n\nidSP:" + o.getIdSanPham());
                        System.out.println(o.getThanhTien());
                        System.out.println(giamPhanTram + "");
                        tienGG += o.getThanhTien() * giamPhanTram / 100;
                        System.out.println("tiền gg: " + tienGG);
                    }
                }
            }
            lblGiamGiaCT.setText(tienGG + "");
            setTienTT_Thua();
        } catch (Exception e) {
        }
    }

    ArrayList<Integer> listIDPGG = new ArrayList<>();

    String tenNhanVien;

    IDKH aaa = new IDKH();
    PGG pgg = new PGG();

    private boolean checkPgg(int idPgg) {
        for (int o : listIDPGG) {
            if (idPgg == o) {
                return false;
            }
        }
        return true;
    }

    private void addHDCT(int id) {
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            if (Services_HDCT.create(new HoaDonChiTiet(
                    0,
                    id,
                    Integer.parseInt(tblGioHang.getValueAt(i, 0).toString()),
                    Integer.parseInt(tblGioHang.getValueAt(i, 2).toString()),
                    "",
                    0))) {
            }
        }

    }

    HoaDon_1 getInputHD() {
        if (txtTienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Tiền Khách Đưa");
            return null;
        }
        if (Integer.parseInt(txtTienKhachDua.getText()) < Integer.parseInt(lblThanhToan1.getText())) {
            JOptionPane.showMessageDialog(this, "Không Đủ Tiền");
            return null;
        }
        if (cboPTTT.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn Phương Thức Thanh Toán");
            return null;
        }
        if (Integer.parseInt(lblGiamGiaCT.getText()) != 0) {
            if (cboPTTT.getSelectedIndex() != hinhThucTT && hinhThucTT !=3) {
                int luaChon = JOptionPane.showConfirmDialog(this, "Hình Thức Thanh Toán Này Không Áp Dụng Giảm Giá Chương Trình\nBạn Có Muốn Đổi Không?", "Thanh Toán", 1);
                if (luaChon == 0) {
                    return null;
                } else {
                    lblGiamGiaCT.setText("0");
                    setTienTT_Thua();
                    listHoaDon.get(indexHD).setSoTienGGDG(0);
                }
            }
        }
        int soTienGGVC = Integer.parseInt(lblGiamGiaVC.getText());
        int soTienGGCT = Integer.parseInt(lblGiamGiaCT.getText());

        int tienThua = Integer.parseInt(txtTienKhachDua.getText()) + soTienGGVC + soTienGGCT - Integer.parseInt(lblThanhToan1.getText());
        System.out.println("sdadsf" + listHoaDon.get(indexHD).getIdMaGG());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH/mm/ss dd/MM/yyyy");
        String formattedDateTime = now.format(formatter);
        return new HoaDon_1(
                0,
                idNV,
                listHoaDon.get(indexHD).getIdKhachHang(),
                listHoaDon.get(indexHD).getIdMaGG(),
                idDotgg,
                cboPTTT.getSelectedIndex(),
                Integer.parseInt(lblTienSp.getText()),
                soTienGGVC,
                soTienGGCT,
                Integer.parseInt(lblThanhToan1.getText()),
                Integer.parseInt(txtTienKhachDua.getText()),
                tienThua,
                tenNhanVien,
                tblHoaDon.getValueAt(indexHD, 1).toString(),
                null,
                null,
                null,
                formattedDateTime,
                "Thành Công",
                txtChuThich.getText(),
                listHoaDon.get(indexHD).getSoDt()
        );
    }

    void clearTT(boolean a) {
        if (a) {
            dsGioHang.remove(indexHD);
            listHoaDon.remove(indexHD);
        }
        loadHoaDon();
        Model = (DefaultTableModel) tblGioHang.getModel();
        Model.setRowCount(0);
        lblMaHD.setText("Chọn Hóa Đơn.");
        lblTenKH.setText("Chọn Hóa Đơn.");
        lblTenKhachHang.setText("Chọn Hóa Đơn.");
        lblTenNV.setText("Chọn Hóa Đơn.");
        lblMaNV.setText("Chọn Hóa Đơn.");
        lblNgayTao.setText("Chọn Hóa Đơn.");
        lblTienSp.setText("0");
        lblThanhToan1.setText("0");
        lblGiamGiaCT.setText("0");
        lblGiamGiaVC.setText("0");
        txtTienKhachDua.setText("");
        cboPTTT.setSelectedIndex(0);
        lblTraKhach.setText("0");
        indexHD = -1;
    }

    void showHD() {
        lblMaHD.setText(tblHoaDon.getValueAt(indexHD, 0).toString());
        lblMaNV.setText(String.valueOf(idNV));
        lblTenNV.setText(tblHoaDon.getValueAt(indexHD, 2).toString());
        if (tblHoaDon.getValueAt(indexHD, 1).toString().isEmpty()) {
            lblTenKhachHang.setText("Chưa Chọn Khách Hàng");
            lblTenKH.setText("Chưa Chọn Khách Hàng");
        } else {
            lblTenKhachHang.setText(tblHoaDon.getValueAt(indexHD, 1).toString());
            lblTenKH.setText(tblHoaDon.getValueAt(indexHD, 1).toString());
        }
        lblNgayTao.setText(tblHoaDon.getValueAt(indexHD, 3).toString());
        if (listHoaDon.get(indexHD).getIdLoaiGG() == 1) {
            lblGiamGiaVC.setText(listHoaDon.get(indexHD).getSoTienGGVC() + "");
        } else {
            if (listHoaDon.get(indexHD).getIdLoaiGG() == 2) {
                lblGiamGiaVC.setText(listHoaDon.get(indexHD).getSoTienGGVC() + "");
            } else {
                lblGiamGiaVC.setText("0");
            }
        }
    }

    void setTienTT_Thua() {
        int tienKHDua;
        try {
            tienKHDua = Integer.parseInt(txtTienKhachDua.getText());
        } catch (NumberFormatException e) {
            tienKHDua = 0;
        }
        int tienTT = Integer.parseInt(lblTienSp.getText()) - Integer.parseInt(lblGiamGiaCT.getText()) - Integer.parseInt(lblGiamGiaVC.getText());
        lblThanhToan1.setText(String.valueOf(tienTT));
        int tienThua = tienKHDua - Integer.parseInt(lblThanhToan1.getText());
        lblTraKhach.setText(String.valueOf(tienThua));
    }

    private void loadCboSL(int i) {
        cboSoLuong.removeAllItems();
        for (int j = 0; j <= i; j++) {
            cboSoLuong.addItem(String.valueOf(j));
        }
    }

    private boolean checkKhiXoaSP() {
        if (listHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy Tạo Và Mở Hóa Đơn Trước");
            return true;
        } else {
            if (indexHD == -1) {
                JOptionPane.showMessageDialog(this, "Hãy Chọn Hóa Đơn và Chọn Sản Phẩm Muốn Xóa");
                return true;
            } else {
                if (dsGioHang.get(indexHD).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Giỏ Hàng Trống");
                    return true;
                }

            }
        }
        return false;
    }

    private void updateSLSP(boolean a, int idsp) {
        int soLuong = 0;
        if (a) {
            soLuong = Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 4).toString()) - cboSoLuong.getSelectedIndex();
            Services_SPCT.updateSLSP(Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 0).toString()), soLuong);
        } else {
            for (Giay_ChiTiet b : Services_SPCT.list(0)) {
                if (idsp == b.getIdGiayChiTiet()) {
                    soLuong = b.getSoLuong();
                    break;
                }
            }
            soLuong += Integer.parseInt(tblGioHang.getValueAt(indexGH, 2).toString());
            Services_SPCT.updateSLSP(Integer.parseInt(tblGioHang.getValueAt(indexGH, 0).toString()), soLuong);
        }
        loadDataCT(Services_SPCT.list(0));
    }

    private void loadView() {
        cboLocThuocTinhSPCT.setVisible(false);
        loadHoaDon();
        loadDataCT(Services_SPCT.list(0));
    }

    private void loadHoaDon() {
        Model = (DefaultTableModel) tblHoaDon.getModel();
        Model.setRowCount(0);
        int i = 1;
        for (HoaDonCho o : listHoaDon) {
            Model.addRow(new Object[]{
                i,
                o.getTenKhachHang(),
                o.getTenNhanVien(),
                o.getNgayTao()
            });
            i++;
        }
    }

    private void loadGioHang(ArrayList<GioHang> list) {
        Model = (DefaultTableModel) tblGioHang.getModel();
        Model.setRowCount(0);
        for (GioHang o : list) {
            Model.addRow(new Object[]{
                o.getIdSanPham(),
                o.getTenSanPham(),
                o.getSoLuong(),
                o.getDonGia(),
                o.getThanhTien()
            });
        }
    }

    private void loadDataCT(ArrayList<Giay_ChiTiet> list) {
        Model = (DefaultTableModel) tblDanhSachGiayCT.getModel();
        Model.setRowCount(0);
        for (Giay_ChiTiet o : list) {
            Model.addRow(new Object[]{
                o.getIdGiayChiTiet(),
                o.getTenGiayChiTiet(),
                o.getKichThuoc(),
                o.getMauSac(),
                o.getSoLuong(),
                o.getGiaBan().toString().substring(0, o.getGiaBan().toString().length() - 2),
                o.getTrangThai()});
        }
    }

    private void addItemLocCT() {
        cboLocThuocTinhSPCT.removeAllItems();
        cboLocThuocTinhSPCT.addItem("Tất Cả");
        ArrayList<ThuocTinh> ListThuocTinh;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fame_BanHang = new javax.swing.JPanel();
        Fame = new javax.swing.JPanel();
        Fame_HoaDon = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        lbl9 = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lbl15 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        cboPTTT = new javax.swing.JComboBox<>();
        lblTienSp = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTraKhach = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChuThich = new javax.swing.JTextArea();
        lbl16 = new javax.swing.JLabel();
        lblGiamGiaVC = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        lbl18 = new javax.swing.JLabel();
        btnVouCher = new javax.swing.JButton();
        lblGiamGiaCT = new javax.swing.JLabel();
        lbl19 = new javax.swing.JLabel();
        lblGiamGiaVC1 = new javax.swing.JLabel();
        lblGiamGiaVC2 = new javax.swing.JLabel();
        lblGiamGiaVC3 = new javax.swing.JLabel();
        lblThanhToan1 = new javax.swing.JLabel();
        lblGiamGiaVC4 = new javax.swing.JLabel();
        btnSuaKH = new javax.swing.JLabel();
        btnEditVoucher = new javax.swing.JLabel();
        Fame_ThaoTacHoaDon = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        FameGioHang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        FameThongTinHD = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        btnXoaKhoiGioHang = new javax.swing.JButton();
        btnThemVaoGioHang = new javax.swing.JButton();
        FameSanPhamBH = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachGiayCT = new javax.swing.JTable();
        btnXoaHetGioHang = new javax.swing.JButton();
        Fame_HD = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        lblChuThich1 = new javax.swing.JLabel();
        lblChuThich3 = new javax.swing.JLabel();
        FameTimKiemCT = new javax.swing.JPanel();
        txtTimKiemSPCT = new javax.swing.JTextField();
        FameLocCT = new javax.swing.JPanel();
        cboLocLoaiTTSPCT = new javax.swing.JComboBox<>();
        cboLocThuocTinhSPCT = new javax.swing.JComboBox<>();
        lblChuThich2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboSoLuong = new javax.swing.JComboBox<>();
        btnTaoHD = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();

        Fame_BanHang.setBackground(new java.awt.Color(255, 255, 255));
        Fame_BanHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bán Hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Courier New", 1, 18))); // NOI18N
        Fame_BanHang.setForeground(new java.awt.Color(102, 102, 0));

        Fame.setBackground(new java.awt.Color(0, 0, 0));
        Fame.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bán Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        Fame.setForeground(new java.awt.Color(255, 255, 255));

        Fame_HoaDon.setBackground(new java.awt.Color(51, 51, 51));
        Fame_HoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 0, 10), new java.awt.Color(255, 255, 255))); // NOI18N
        Fame_HoaDon.setForeground(new java.awt.Color(51, 51, 51));

        lbl8.setBackground(new java.awt.Color(255, 255, 255));
        lbl8.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl8.setForeground(new java.awt.Color(255, 255, 255));
        lbl8.setText("Khách Hàng:");

        lblTenKH.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(255, 255, 255));
        lblTenKH.setText("Nguyễn Mạnh Dũng");

        btnCapNhat.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        lbl9.setBackground(new java.awt.Color(255, 255, 255));
        lbl9.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl9.setForeground(new java.awt.Color(255, 255, 255));
        lbl9.setText("Tiền Sản Phẩm:");

        lbl14.setBackground(new java.awt.Color(255, 255, 255));
        lbl14.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl14.setForeground(new java.awt.Color(255, 255, 255));
        lbl14.setText("Tiền Khách Đưa:");

        lbl15.setBackground(new java.awt.Color(255, 255, 255));
        lbl15.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl15.setForeground(new java.awt.Color(255, 255, 255));
        lbl15.setText("Trả Khách:");

        lbl13.setBackground(new java.awt.Color(255, 255, 255));
        lbl13.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl13.setForeground(new java.awt.Color(255, 255, 255));
        lbl13.setText("Hình Thức Thanh Toán");

        cboPTTT.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        cboPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn", "Tiền Mặt", "Chuyển Khoản" }));

        lblTienSp.setBackground(new java.awt.Color(255, 255, 255));
        lblTienSp.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblTienSp.setForeground(new java.awt.Color(255, 255, 255));
        lblTienSp.setText("0");

        txtTienKhachDua.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        lblTraKhach.setBackground(new java.awt.Color(255, 255, 255));
        lblTraKhach.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lblTraKhach.setForeground(new java.awt.Color(255, 255, 255));
        lblTraKhach.setText("0");

        txtChuThich.setColumns(20);
        txtChuThich.setRows(5);
        txtChuThich.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane2.setViewportView(txtChuThich);

        lbl16.setBackground(new java.awt.Color(255, 255, 255));
        lbl16.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl16.setForeground(new java.awt.Color(255, 255, 255));
        lbl16.setText("Chú Thích:");

        lblGiamGiaVC.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaVC.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC.setText("0");

        lbl17.setBackground(new java.awt.Color(255, 255, 255));
        lbl17.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl17.setForeground(new java.awt.Color(255, 255, 255));
        lbl17.setText("Giảm Giá Voucher:");

        lblThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblThanhToan.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        lblThanhToan.setText("VND");

        lbl18.setBackground(new java.awt.Color(255, 255, 255));
        lbl18.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl18.setForeground(new java.awt.Color(255, 255, 255));
        lbl18.setText("Thanh Toán:");

        btnVouCher.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        btnVouCher.setText("Áp Dụng");
        btnVouCher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVouCherActionPerformed(evt);
            }
        });

        lblGiamGiaCT.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaCT.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaCT.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaCT.setText("0");

        lbl19.setBackground(new java.awt.Color(255, 255, 255));
        lbl19.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl19.setForeground(new java.awt.Color(255, 255, 255));
        lbl19.setText("Giảm Giá Chương Trình:");

        lblGiamGiaVC1.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC1.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaVC1.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC1.setText("VND");

        lblGiamGiaVC2.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC2.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaVC2.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC2.setText("VND");

        lblGiamGiaVC3.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC3.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaVC3.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC3.setText("VND");

        lblThanhToan1.setBackground(new java.awt.Color(255, 255, 255));
        lblThanhToan1.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        lblThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        lblThanhToan1.setText("0");

        lblGiamGiaVC4.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC4.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        lblGiamGiaVC4.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGiaVC4.setText("VND");

        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btnSuaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaKHMouseClicked(evt);
            }
        });

        btnEditVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btnEditVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditVoucherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Fame_HoaDonLayout = new javax.swing.GroupLayout(Fame_HoaDon);
        Fame_HoaDon.setLayout(Fame_HoaDonLayout);
        Fame_HoaDonLayout.setHorizontalGroup(
            Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addComponent(lbl17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditVoucher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblGiamGiaVC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGiamGiaVC3))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblGiamGiaCT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGiamGiaVC2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_HoaDonLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblTienSp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGiamGiaVC1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_HoaDonLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblThanhToan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblThanhToan))
                    .addComponent(txtTienKhachDua)
                    .addComponent(cboPTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblTraKhach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGiamGiaVC4))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl14)
                            .addComponent(lbl9)
                            .addComponent(lbl16)
                            .addComponent(lbl18)
                            .addComponent(lbl19)
                            .addComponent(lbl13)
                            .addComponent(lbl15))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addComponent(lbl8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_HoaDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        Fame_HoaDonLayout.setVerticalGroup(
            Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl8)
                        .addComponent(btnCapNhat))
                    .addComponent(btnSuaKH, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addComponent(lblTenKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl9)
                .addGap(9, 9, 9)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienSp)
                    .addComponent(lblGiamGiaVC1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGiaCT)
                    .addComponent(lblGiamGiaVC2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl17)
                        .addComponent(btnVouCher))
                    .addComponent(btnEditVoucher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGiaVC)
                    .addComponent(lblGiamGiaVC3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl18)
                .addGap(10, 10, 10)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThanhToan)
                    .addComponent(lblThanhToan1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl15)
                .addGap(8, 8, 8)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTraKhach)
                    .addComponent(lblGiamGiaVC4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Fame_ThaoTacHoaDon.setBackground(new java.awt.Color(51, 51, 51));
        Fame_ThaoTacHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao Tác Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 0, 10), new java.awt.Color(255, 255, 255))); // NOI18N
        Fame_ThaoTacHoaDon.setForeground(new java.awt.Color(51, 51, 51));

        btnThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Toán");

        jLabel23.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Thanh");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Notes.png"))); // NOI18N

        javax.swing.GroupLayout btnThanhToanLayout = new javax.swing.GroupLayout(btnThanhToan);
        btnThanhToan.setLayout(btnThanhToanLayout);
        btnThanhToanLayout.setHorizontalGroup(
            btnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThanhToanLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addGap(30, 30, 30))
        );
        btnThanhToanLayout.setVerticalGroup(
            btnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThanhToanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(btnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabel22)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Fame_ThaoTacHoaDonLayout = new javax.swing.GroupLayout(Fame_ThaoTacHoaDon);
        Fame_ThaoTacHoaDon.setLayout(Fame_ThaoTacHoaDonLayout);
        Fame_ThaoTacHoaDonLayout.setHorizontalGroup(
            Fame_ThaoTacHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_ThaoTacHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        Fame_ThaoTacHoaDonLayout.setVerticalGroup(
            Fame_ThaoTacHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_ThaoTacHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FameLayout = new javax.swing.GroupLayout(Fame);
        Fame.setLayout(FameLayout);
        FameLayout.setHorizontalGroup(
            FameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fame_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Fame_ThaoTacHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        FameLayout.setVerticalGroup(
            FameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLayout.createSequentialGroup()
                .addComponent(Fame_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fame_ThaoTacHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FameGioHang.setBackground(new java.awt.Color(255, 255, 255));
        FameGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), new java.awt.Color(0, 102, 102))); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tblGioHang.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        tblGioHang.setForeground(new java.awt.Color(51, 51, 51));
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        javax.swing.GroupLayout FameGioHangLayout = new javax.swing.GroupLayout(FameGioHang);
        FameGioHang.setLayout(FameGioHangLayout);
        FameGioHangLayout.setHorizontalGroup(
            FameGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        FameGioHangLayout.setVerticalGroup(
            FameGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        FameThongTinHD.setBackground(new java.awt.Color(255, 255, 255));
        FameThongTinHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), new java.awt.Color(0, 102, 102))); // NOI18N
        FameThongTinHD.setMaximumSize(new java.awt.Dimension(323, 206));

        lbl1.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl1.setText("Mã Hóa Đơn:");

        lbl2.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl2.setText("ID NV");

        lbl3.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl3.setText("Khách Hàng:");

        lblMaNV.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblMaNV.setText("Không Hiện Thị");

        lblTenKhachHang.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblTenKhachHang.setText("Không Hiện Thị");

        lbl4.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl4.setText("Ngày Tạo:");

        lblNgayTao.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblNgayTao.setText("Không Hiện Thị");

        lblMaHD.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lblMaHD.setText("Không Hiện Thị");

        lbl6.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        lbl6.setText("Nhân Viên:");

        lblTenNV.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblTenNV.setText("Không Hiện Thị");

        javax.swing.GroupLayout FameThongTinHDLayout = new javax.swing.GroupLayout(FameThongTinHD);
        FameThongTinHD.setLayout(FameThongTinHDLayout);
        FameThongTinHDLayout.setHorizontalGroup(
            FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinHDLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FameThongTinHDLayout.createSequentialGroup()
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FameThongTinHDLayout.createSequentialGroup()
                        .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FameThongTinHDLayout.createSequentialGroup()
                        .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FameThongTinHDLayout.createSequentialGroup()
                        .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FameThongTinHDLayout.setVerticalGroup(
            FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThongTinHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lblMaHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(lblMaNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNV)
                    .addComponent(lbl6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayTao)
                    .addComponent(lbl4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameThongTinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(lblTenKhachHang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXoaKhoiGioHang.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnXoaKhoiGioHang.setText("Xóa Khỏi Giỏ Hàng");
        btnXoaKhoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiGioHangActionPerformed(evt);
            }
        });

        btnThemVaoGioHang.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnThemVaoGioHang.setText("Thêm Vào Giỏ Hàng");
        btnThemVaoGioHang.setEnabled(false);
        btnThemVaoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoGioHangActionPerformed(evt);
            }
        });

        FameSanPhamBH.setBackground(new java.awt.Color(255, 255, 255));
        FameSanPhamBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), new java.awt.Color(0, 102, 102))); // NOI18N

        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tblDanhSachGiayCT.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        tblDanhSachGiayCT.setForeground(new java.awt.Color(51, 51, 51));
        tblDanhSachGiayCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Kích Thước", "Màu Sắc", "Số Lượng", "Đơn Giá", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachGiayCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachGiayCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachGiayCT);
        if (tblDanhSachGiayCT.getColumnModel().getColumnCount() > 0) {
            tblDanhSachGiayCT.getColumnModel().getColumn(0).setMaxWidth(50);
            tblDanhSachGiayCT.getColumnModel().getColumn(2).setMaxWidth(70);
            tblDanhSachGiayCT.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        javax.swing.GroupLayout FameSanPhamBHLayout = new javax.swing.GroupLayout(FameSanPhamBH);
        FameSanPhamBH.setLayout(FameSanPhamBHLayout);
        FameSanPhamBHLayout.setHorizontalGroup(
            FameSanPhamBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        FameSanPhamBHLayout.setVerticalGroup(
            FameSanPhamBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        btnXoaHetGioHang.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnXoaHetGioHang.setText("Xóa Hết");
        btnXoaHetGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHetGioHangActionPerformed(evt);
            }
        });

        Fame_HD.setBackground(new java.awt.Color(255, 255, 255));
        Fame_HD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18), new java.awt.Color(0, 102, 102))); // NOI18N

        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tblHoaDon.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        tblHoaDon.setForeground(new java.awt.Color(51, 51, 51));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên KH", "Tên NV", "Ngày Tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout Fame_HDLayout = new javax.swing.GroupLayout(Fame_HD);
        Fame_HD.setLayout(Fame_HDLayout);
        Fame_HDLayout.setHorizontalGroup(
            Fame_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HDLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        Fame_HDLayout.setVerticalGroup(
            Fame_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HDLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblChuThich1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblChuThich1.setText("Nhấp Vào Hóa Đơn Để XEM GIỎ HÀNG Hoặc Thao Tác THANH TOÁN Hoặc HỦY...");

        lblChuThich3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblChuThich3.setText("Nhấp Vào Dòng Của GIỎ HÀNG Để Thao Tác Xóa Sản Phẩm Không Có Nhu Cầu Mua Nữa");

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
            .addGroup(FameTimKiemCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );
        FameTimKiemCTLayout.setVerticalGroup(
            FameTimKiemCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKiemSPCT)
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
                .addComponent(cboLocThuocTinhSPCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        FameLocCTLayout.setVerticalGroup(
            FameLocCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameLocCTLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(cboLocLoaiTTSPCT))
            .addComponent(cboLocThuocTinhSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        lblChuThich2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblChuThich2.setText("Chọn Sản Phẩm Rồi Nhấn Vào \"Thêm Vào Giỏ Hàng\" Để Thêm Sản Phảm Vào Giỏ Hàng");

        jLabel13.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        jLabel13.setText("Số Lượng:");

        cboSoLuong.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        cboSoLuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hãy Chọn SP" }));

        btnTaoHD.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnTaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnTaoHD.setText("Tạo");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnHuyHD.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        btnHuyHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnHuyHD.setText("Hủy");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Fame_BanHangLayout = new javax.swing.GroupLayout(Fame_BanHang);
        Fame_BanHang.setLayout(Fame_BanHangLayout);
        Fame_BanHangLayout.setHorizontalGroup(
            Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_BanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(lblChuThich3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaHetGioHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaKhoiGioHang))
                    .addComponent(FameGioHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(lblChuThich2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cboSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnThemVaoGioHang))
                    .addComponent(FameSanPhamBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(FameTimKiemCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FameLocCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(Fame_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTaoHD, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHuyHD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FameThongTinHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(lblChuThich1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Fame_BanHangLayout.setVerticalGroup(
            Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_BanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FameThongTinHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Fame_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_BanHangLayout.createSequentialGroup()
                            .addComponent(btnTaoHD)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnHuyHD))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChuThich1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FameLocCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FameTimKiemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FameSanPhamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_BanHangLayout.createSequentialGroup()
                        .addComponent(lblChuThich2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemVaoGioHang)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FameGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaKhoiGioHang)
                        .addComponent(btnXoaHetGioHang))
                    .addComponent(lblChuThich3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
            .addGroup(Fame_BanHangLayout.createSequentialGroup()
                .addComponent(Fame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fame_BanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fame_BanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        listHoaDon.get(indexHD).setIdKhachHang(aaa.getIdKH());
        listHoaDon.get(indexHD).setTenKhachHang(aaa.getTenKH());
        listHoaDon.get(indexHD).setSoDt(aaa.getSoDt());
        loadHoaDon();
        showHD();
        btnCapNhat.setEnabled(false);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        if (!txtTienKhachDua.getText().isEmpty()) {
            if (!txtTienKhachDua.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Tiền Chỉ Có Số!");
                txtTienKhachDua.setText(txtTienKhachDua.getText().substring(0, txtTienKhachDua.getText().length() - 1));
            }
        }
        setTienTT_Thua();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnVouCherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVouCherActionPerformed
        listHoaDon.get(indexHD).setIdMaGG(pgg.getIdPGG());

        if (pgg.getToiThieu() <= Integer.parseInt(lblThanhToan1.getText())) {

            listHoaDon.get(indexHD).setIdMaGG(pgg.getIdPGG());
            listHoaDon.get(indexHD).setIdLoaiGG(pgg.getLoaiGiam());

            System.out.println("Loại Giảm: " + listHoaDon.get(indexHD).getIdLoaiGG());
            if (listHoaDon.get(indexHD).getIdLoaiGG() == 1) {
                listHoaDon.get(indexHD).setSoTienGGVC(pgg.getGiamGia());
                lblGiamGiaVC.setText("" + (pgg.getGiamGia()));
            } else {
                listHoaDon.get(indexHD).setSoTienGGVC(Integer.parseInt(lblTienSp.getText()) * pgg.getGiamGia() / 100);
                lblGiamGiaVC.setText("" + (pgg.getGiamGia() * Integer.parseInt(lblTienSp.getText()) / 100));
            }
            setTienTT_Thua();

        } else {
            JOptionPane.showMessageDialog(this, "Hóa Đơn Chưa Đạt Giá Trị Tối Thiểu");
        }
        System.out.println(pgg.getIdPGG() + " " + pgg.getToiThieu());
        btnVouCher.setEnabled(false);
    }//GEN-LAST:event_btnVouCherActionPerformed

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        Object[] luaChon = {"Tạo", "Hủy"};
        ImageIcon icon = new ImageIcon("C:\\Users\\tungb\\OneDrive\\Documents\\NetBeansProjects\\DuAn\\src\\icon\\Create.png");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String formattedDateTime = now.format(formatter);
        if (JOptionPane.showOptionDialog(this, "Tạo Hóa Đơn Mới", "Tạo Hóa Đơn", 0, 0, icon, luaChon, EXIT_ON_CLOSE) == 0) {
            dsGioHang.add(new ArrayList<GioHang>());
            listHoaDon.add(new HoaDonCho(
                    0,
                    idNV,
                    idKH,
                    0,
                    0,
                    tenNhanVien,
                    "Khách Mua Lẻ",
                    formattedDateTime,
                    0,
                    0,
                    null));
            loadHoaDon();
        }
        btnVouCher.setEnabled(false);
        clearTT(false);
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn");
        } else {
            Object[] luaChon = {"Xóa", "Hủy"};
            int option = JOptionPane.showOptionDialog(this, "Bạn Có Muốn XÓA Hóa Đơn Không", "Xóa", 0, 0, null, luaChon, EXIT_ON_CLOSE);
            if (option == 0) {
                if (Services_HD.create(
                        new HoaDon_1(option,
                                idNV,
                                idKH,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                tenNhanVien,
                                null,
                                null,
                                null,
                                null,
                                null,
                                "Bị Hủy",
                                txtChuThich.getText(),
                                null)) != -1) {
                    for (indexGH = 0; indexGH < tblGioHang.getRowCount(); indexGH++) {
                        updateSLSP(false, Integer.parseInt(tblGioHang.getValueAt(indexGH, 0).toString()));
                    }
                    dsGioHang.get(indexHD).clear();
                    loadGioHang(dsGioHang.get(indexHD));
                    listHoaDon.remove(indexHD);
                    dsGioHang.remove(indexHD);
                    loadHoaDon();
                } else {
                    JOptionPane.showMessageDialog(this, "Hủy Thất Bại");
                }
            }
        }
        btnThemVaoGioHang.setEnabled(false);
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Hóa Đơn Cần Thanh Toán");
        } else {
            if (!dsGioHang.get(indexHD).isEmpty()) {
                HoaDon_1 hd = getInputHD();
                if (hd != null) {
                    if (Integer.parseInt(lblThanhToan1.getText()) - 1 >= hd.getTienKhachDua()) {
                        JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Phải Lớn Hơn Tiền Thanh Toán");
                    } else {
                        if (JOptionPane.showOptionDialog(this, "Xác Nhận Thanh Toán", "Thanh Toán", 0, 0, null, new Object[]{"Xác Nhận", "Hủy"}, EXIT_ON_CLOSE) == 0) {
                            int id = Services_HD.create(hd);
                            if (id != -1) {
                                if (checkPgg(hd.getIdPhieuGG())) {
                                    addHDCT(id);
                                    if (hd.getIdPhieuGG() != 0) {
                                        listIDPGG.add(hd.getIdPhieuGG());
                                    }
                                    new viewXuatHoaDon(hd, id).inHoaDon();
                                    clearTT(true);
                                    btnThemVaoGioHang.setEnabled(true);
                                    JOptionPane.showMessageDialog(this, "Thành Công");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Rất Tiếc, Phiếu Giảm Giá Đã CÓ Người Dùng Trước\nVui Lòng Chọn Phiếu Khác!!");
                                    lblGiamGiaVC.setText("0");
                                    listHoaDon.get(indexHD).setIdMaGG(0);
                                    listHoaDon.get(indexHD).setSoTienGGVC(0);
                                    lblThanhToan1.setText(lblTienSp.getText());
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Thất Bại");
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Trước Khi Thanh Toán");
            }

        }
    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        indexGH = tblGioHang.getSelectedRow();
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnXoaKhoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiGioHangActionPerformed
        if (checkKhiXoaSP()) {
            return;
        }
        if (indexGH != -1) {
            updateSLSP(false, Integer.parseInt(tblGioHang.getValueAt(indexGH, 0).toString()));
            dsGioHang.get(indexHD).remove(indexGH);
            loadGioHang(dsGioHang.get(indexHD));
            cboSoLuong.removeAllItems();
            indexGH = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Chọn Sản Phẩm Cần Xóa");
        }
        try {
            int tienSp = 0;
            for (GioHang o : dsGioHang.get(indexHD)) {
                tienSp += o.getThanhTien();
            }
            lblTienSp.setText(String.valueOf(tienSp));
        } catch (Exception e) {
        }
        setTienTT_Thua();
        if (pgg.getToiThieu() > Integer.parseInt(lblThanhToan1.getText())) {
            lblGiamGiaVC.setText("0");
            listHoaDon.get(indexHD).setIdDotGG(0);
            listHoaDon.get(indexHD).setSoTienGGVC(0);
            setTienTT_Thua();
            JOptionPane.showMessageDialog(this, "Hóa Đơn Chưa Đạt Giá Trị Tối Thiểu,VouCher Sẽ Bị Hủy");
        }
        updateTienGGDGG(dsGioHang.get(indexHD));
    }//GEN-LAST:event_btnXoaKhoiGioHangActionPerformed

    private void btnThemVaoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoGioHangActionPerformed
        //kiem tra xem đã có hóa đơn nào chưa
        if (listHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy Thêm Hóa Đơn");
        } else {
            if (indexHD == -1) {
                JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn");
            } else {
                if (indexSPCT == -1) {
                    JOptionPane.showMessageDialog(this, "Hãy Chọn Sản Phẩm.");
                } else {
                    if (cboSoLuong.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(this, "Hãy Chọn Số Lượng");
                    } else {
                        int idSP = Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 0).toString());
                        updateSLSP(true, idSP);
                        int soLuong = Integer.parseInt(cboSoLuong.getSelectedItem().toString());
                        String tenSP = tblDanhSachGiayCT.getValueAt(indexSPCT, 1).toString();
                        int donGia = Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 5).toString());
                        ArrayList<GioHang> gioHangList = dsGioHang.get(indexHD);
                        if (gioHangList.isEmpty()) {
                            gioHangList.add(new GioHang(tenSP, idSP, soLuong, donGia));
                            loadGioHang(gioHangList);
                        } else {
                            boolean found = false;
                            for (GioHang gioHang : gioHangList) {
                                if (idSP == gioHang.getIdSanPham()) {
                                    gioHang.setSoLuong(gioHang.getSoLuong() + soLuong);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                gioHangList.add(new GioHang(tenSP, idSP, soLuong, donGia));
                            }
                            loadGioHang(gioHangList);
                            loadCboSL(Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 2).toString()));
                        }
                    }
                }
            }
        }
        try {
            int tienSp = 0;
            for (GioHang o : dsGioHang.get(indexHD)) {
                tienSp += o.getThanhTien();
            }
            lblTienSp.setText(String.valueOf(tienSp));
            updateTienGGDGG(dsGioHang.get(indexHD));
            setTienTT_Thua();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThemVaoGioHangActionPerformed

    private void tblDanhSachGiayCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachGiayCTMouseClicked
        indexSPCT = tblDanhSachGiayCT.getSelectedRow();
        loadCboSL(Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 4).toString()));
        if (tblDanhSachGiayCT.getValueAt(indexSPCT, 6).toString().contains("Ngừng")) {
            btnThemVaoGioHang.setEnabled(false);
        } else {
            btnThemVaoGioHang.setEnabled(true);
        }
    }//GEN-LAST:event_tblDanhSachGiayCTMouseClicked

    private void btnXoaHetGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHetGioHangActionPerformed
        if (checkKhiXoaSP()) {
            return;
        }
        Object[] luaChon = {"Xóa", "Hủy"};
        int option = JOptionPane.showOptionDialog(this, "Bạn Có Muốn XÓA HẾT Giỏ Hàng Không", "Xóa Hết", 0, 0, null, luaChon, EXIT_ON_CLOSE);
        if (option == 0) {
            for (indexGH = 0; indexGH < tblGioHang.getRowCount(); indexGH++) {
                updateSLSP(false, Integer.parseInt(tblGioHang.getValueAt(indexGH, 0).toString()));
            }
            dsGioHang.get(indexHD).clear();
            listHoaDon.get(indexHD).setIdMaGG(0);
            listHoaDon.get(indexHD).setSoTienGGVC(0);
            listHoaDon.get(indexHD).setSoTienGGDG(0);
            lblGiamGiaVC.setText("0");
            loadGioHang(dsGioHang.get(indexHD));
            loadCboSL(Integer.parseInt(tblDanhSachGiayCT.getValueAt(indexSPCT, 2).toString()));
            indexGH = -1;
            indexSPCT = -1;
        }
        lblTienSp.setText("0");
        updateTienGGDGG(dsGioHang.get(indexHD));
        setTienTT_Thua();
    }//GEN-LAST:event_btnXoaHetGioHangActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        indexHD = tblHoaDon.getSelectedRow();
        loadGioHang(dsGioHang.get(indexHD));
        showHD();
        try {
            int tienSp = 0;
            for (GioHang o : dsGioHang.get(indexHD)) {
                tienSp += o.getThanhTien();
            }
            lblTienSp.setText(String.valueOf(tienSp));
        } catch (Exception e) {
        }
        updateTienGGDGG(dsGioHang.get(indexHD));
        setTienTT_Thua();
        btnThemVaoGioHang.setEnabled(true);
        btnVouCher.setEnabled(false);
    }//GEN-LAST:event_tblHoaDonMouseClicked

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

    private void btnSuaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaKHMouseClicked
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn Thay Đổi");
        } else {
            new view_KhachHang(aaa).setVisible(true);
            btnCapNhat.setEnabled(true);
        }
    }//GEN-LAST:event_btnSuaKHMouseClicked

    private void btnEditVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditVoucherMouseClicked
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn Thay Đổi");
        } else {
            new view_PhieuGG(pgg).setVisible(true);
            btnVouCher.setEnabled(true);
        }
    }//GEN-LAST:event_btnEditVoucherMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fame;
    private javax.swing.JPanel FameGioHang;
    private javax.swing.JPanel FameLocCT;
    private javax.swing.JPanel FameSanPhamBH;
    private javax.swing.JPanel FameThongTinHD;
    private javax.swing.JPanel FameTimKiemCT;
    private javax.swing.JPanel Fame_BanHang;
    private javax.swing.JPanel Fame_HD;
    private javax.swing.JPanel Fame_HoaDon;
    private javax.swing.JPanel Fame_ThaoTacHoaDon;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JLabel btnEditVoucher;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JLabel btnSuaKH;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JPanel btnThanhToan;
    private javax.swing.JButton btnThemVaoGioHang;
    private javax.swing.JButton btnVouCher;
    private javax.swing.JButton btnXoaHetGioHang;
    private javax.swing.JButton btnXoaKhoiGioHang;
    private javax.swing.JComboBox<String> cboLocLoaiTTSPCT;
    private javax.swing.JComboBox<String> cboLocThuocTinhSPCT;
    private javax.swing.JComboBox<String> cboPTTT;
    private javax.swing.JComboBox<String> cboSoLuong;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl18;
    private javax.swing.JLabel lbl19;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblChuThich1;
    private javax.swing.JLabel lblChuThich2;
    private javax.swing.JLabel lblChuThich3;
    private javax.swing.JLabel lblGiamGiaCT;
    private javax.swing.JLabel lblGiamGiaVC;
    private javax.swing.JLabel lblGiamGiaVC1;
    private javax.swing.JLabel lblGiamGiaVC2;
    private javax.swing.JLabel lblGiamGiaVC3;
    private javax.swing.JLabel lblGiamGiaVC4;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblThanhToan1;
    private javax.swing.JLabel lblTienSp;
    private javax.swing.JLabel lblTraKhach;
    private javax.swing.JTable tblDanhSachGiayCT;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextArea txtChuThich;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemSPCT;
    // End of variables declaration//GEN-END:variables
}
