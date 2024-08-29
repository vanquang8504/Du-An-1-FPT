/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DBConnect.DBConnect;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.LichSuGiaoDich;
import java.sql.*;
import impl.KhachHangDAO;
import impl.LichSuGiaoDichDAO;

public class Form_K_Hang extends javax.swing.JPanel {

    public Form_K_Hang() {
        initComponents();
        fillTable();
        fillTableLichSu();
    }

    KhachHangDAO dao = new KhachHangDAO();
    KhachHang kh = new KhachHang();

    public void fillTable() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lst = dao.getAllKhachHang();
        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }
    }

    public KhachHang getModel() {
        KhachHang kh = new KhachHang();

        kh.setTenKhachHang(txtTenKhachHang.getText());

        if (rdoNam.isSelected()) {
            kh.setGioiTinh(true);
        } else {
            kh.setGioiTinh(false);
        }
        kh.setSdt(txtSoDt.getText());

        kh.setDiaChi(txtDiaChi.getText());
        if (rdolauNam.isSelected()) {
            kh.setHangKhach(true);
        } else {
            kh.setHangKhach(false);
        }

        return kh;
    }

    public KhachHang getModelUpdate() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(Integer.parseInt(txtMaKhachHang.getText()));
        kh.setTenKhachHang(txtTenKhachHang.getText());

        if (rdoNam.isSelected()) {
            kh.setGioiTinh(true);
        } else {
            kh.setGioiTinh(false);
        }
        kh.setSdt(txtSoDt.getText());

        kh.setDiaChi(txtDiaChi.getText());
        if (rdolauNam.isSelected()) {
            kh.setHangKhach(true);
        } else {
            kh.setHangKhach(false);
        }

        return kh;
    }

    public void setModel(KhachHang kh) {
        System.out.println(kh);
        txtMaKhachHang.setText(kh.getMaKhachHang() + "");
        txtTenKhachHang.setText(kh.getTenKhachHang());
        if (kh.isGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSoDt.setText(kh.getSdt() + "");

        txtDiaChi.setText(kh.getDiaChi());
        if (kh.isHangKhach() == true) {
            rdolauNam.setSelected(true);
        } else {
            rdoMoi.setSelected(true);
        }
    }

    public void reset() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
//        buttonGroup1.clearSelection();
        txtSoDt.setText("");

        txtDiaChi.setText("");
//        buttonGroup2.clearSelection();
    }

    public boolean checkform() {
//        if (txtTenKhachHang.getText().isEmpty() || txtSoDt.getText().isEmpty()
//               || txtDiaChi.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin");
//            return false;
//        }
        try {
            int so = Integer.parseInt(txtSoDt.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "số điện thoại phải là số");
            return false;
        }
        if (txtSoDt.getText().length() < 10 || txtSoDt.getText().length() > 11) {
            JOptionPane.showMessageDialog(this, "SDT thì 10 hoặc 11 số ");
            return false;
        }
        return true;
    }

    public void fillTableKhachHangNam() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lst = dao.getAllKhachHangNam();
        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }

    }

    public void fillTableKhachHangNu() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lst = dao.getAllKhachHangNu();
        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }

    }

    public void fillTableKhachHangLauNam() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lst = dao.getAllHangKhachLaunam();
        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }

    }

    public void fillTableKhachHangMoi() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);
        ArrayList<KhachHang> lst = dao.getAllHangKhachMoi();
        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }

    }

    ///////////lich su giao dich
    LichSuGiaoDichDAO lsdao = new LichSuGiaoDichDAO();

    public void fillTableLichSu() {
        DefaultTableModel model = new DefaultTableModel();
        System.out.println(123);

        model.setRowCount(0);
        ArrayList<LichSuGiaoDich> lst = lsdao.getAllLichSuGiaoDich();
        for (LichSuGiaoDich ls : lst) {
            model.addRow(new Object[]{ls.getTenKH(), ls.getTenSp(), ls.getSdt(), ls.getSoLuong(),
                ls.getTongTien(), ls.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"});
        }
    }
    ArrayList<LichSuGiaoDich> arr = new ArrayList<>();

    public void fillArrayListLichSu() {
    
    }

    public void Lichsu() {

    }
    ArrayList<KhachHang> lst = new ArrayList<>();

    public void fillTableArrayList() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbBangKhachHang.getModel();
        model.setRowCount(0);

        for (KhachHang kh : lst) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        txtSoDt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSUa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        rdolauNam = new javax.swing.JRadioButton();
        rdoMoi = new javax.swing.JRadioButton();
        tabs = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBangKhachHang = new javax.swing.JTable();
        btnDau = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        txtTimKiemKH = new javax.swing.JTextField();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết lập thông tin khách hàng"));

        jLabel2.setText("Mã Khách hàng");

        jLabel3.setText("Tên Khách hàng");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Số điện thoại");

        txtMaKhachHang.setEditable(false);

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        buttonGroup2.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSUa.setText("Sửa");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jLabel8.setText("Trạng Thái");

        buttonGroup1.add(rdolauNam);
        rdolauNam.setText("Hoạt động");
        rdolauNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdolauNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMoi);
        rdoMoi.setText("Ngừng hoạt động");
        rdoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDt, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdolauNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(rdoMoi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSUa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(txtSoDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnSUa))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdolauNam)
                            .addComponent(rdoMoi)
                            .addComponent(rdoNu)
                            .addComponent(rdoNam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoi)
                        .addGap(43, 43, 43))))
        );

        tabs.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        tbBangKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Giới tính", "SDT", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tbBangKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbBangKhachHangMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbBangKhachHang);

        btnDau.setText("|<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnCuoi.setText(">|");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel1.setText("Giới Tính ");

        jLabel9.setText("Trạng Thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Hoạt động", "Ngừng hoạt động" }));
        cboTrangThai.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cboTrangThaiAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Nam", "Nữ" }));
        cboGioiTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGioiTinhItemStateChanged(evt);
            }
        });
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 177, Short.MAX_VALUE))
                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        txtTimKiemKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKHCaretUpdate(evt);
            }
        });
        txtTimKiemKH.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTimKiemKHPropertyChange(evt);
            }
        });
        txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKHKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemKH)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnDau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(btnCuoi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau)
                    .addComponent(btnBack)
                    .addComponent(btnNext)
                    .addComponent(btnCuoi))
                .addContainerGap(161, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("Thông tin cá nhân", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabs)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (checkform()) {
            dao.ADD(getModel());
            fillTableLichSu();
            fillTable();

            JOptionPane.showMessageDialog(this, "Thêm thành công");

            //   reset();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed
        if (checkform()) {
            dao.update(getModelUpdate());
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            fillTable();
            reset();
        }

    }//GEN-LAST:event_btnSUaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        reset();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void rdolauNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdolauNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdolauNamActionPerformed

    private void rdoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMoiActionPerformed

    private void txtTimKiemKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyReleased

        lst.clear();
        try {
            Connection cn = DBConnect.getConnection();
            String sql = "select ID_KhachHang,TenKH,GioiTinh,SoDT,Email,DiaChi,TrangThai\n"
            + "from KHACHHANG\n"
            + "where SoDT like '%" + txtTimKiemKH.getText() + "%'";

            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID_KhachHang"), rs.getString("TenKH"),
                    rs.getBoolean("GioiTinh"), rs.getString("SoDT"),
                    rs.getString("DiaChi"), rs.getBoolean("TrangThai"));
                lst.add(kh);
            }
            cn.close();
            fillTableArrayList();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_txtTimKiemKHKeyReleased

    private void txtTimKiemKHPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTimKiemKHPropertyChange

        System.out.println(txtTimKiemKH.getText());
        //cho tôi code ví dụ về JTextField DocumentListener
    }//GEN-LAST:event_txtTimKiemKHPropertyChange

    private void txtTimKiemKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKHCaretUpdate
        //fillTableTimKiem();
    }//GEN-LAST:event_txtTimKiemKHCaretUpdate

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            fillTableKhachHangNam();
        } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
            fillTableKhachHangNu();
        } else {
            fillTable();
        }
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void cboGioiTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGioiTinhItemStateChanged

    }//GEN-LAST:event_cboGioiTinhItemStateChanged

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        String trangThai = (String) cboTrangThai.getSelectedItem();
        if (trangThai.equalsIgnoreCase("Hoạt động")) {
            fillTableKhachHangLauNam();
        } else if (trangThai.equalsIgnoreCase("Ngừng hoạt động")) {
            fillTableKhachHangMoi();
        } else {
            fillTable();
        }
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void cboTrangThaiAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cboTrangThaiAncestorAdded

    }//GEN-LAST:event_cboTrangThaiAncestorAdded

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        setModel(dao.getAllKhachHang().get(dao.getAllKhachHang().size() - 1));
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int row = dao.find(Integer.parseInt(txtMaKhachHang.getText()));
        if (row == dao.getAllKhachHang().size() - 1) {
            setModel(dao.getAllKhachHang().get(0));
        } else {
            setModel(dao.getAllKhachHang().get(row + 1));
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        int row = dao.find(Integer.parseInt(txtMaKhachHang.getText()));
        if (row == 0) {
            setModel(dao.getAllKhachHang().get(dao.getAllKhachHang().size() - 1));
        } else {
            setModel(dao.getAllKhachHang().get(row - 1));
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        setModel(dao.getAllKhachHang().get(0));
    }//GEN-LAST:event_btnDauActionPerformed

    private void tbBangKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangKhachHangMousePressed

    }//GEN-LAST:event_tbBangKhachHangMousePressed

    private void tbBangKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangKhachHangMouseClicked
        int row = tbBangKhachHang.getSelectedRow();
        try {
            setModel(dao.getAllKhachHang().get(row));
            Lichsu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbBangKhachHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSUa;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoMoi;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdolauNam;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbBangKhachHang;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSoDt;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiemKH;
    // End of variables declaration//GEN-END:variables
}
