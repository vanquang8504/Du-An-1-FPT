/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import impl.phieugiamgiarepo;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.loaiphieu;
import model.nhanvien1;
import model.phieugiamgia;
import model.phieugiamgiachitiet;

/**
 *
 * @author phamt
 */
public class Form_P_GG extends javax.swing.JPanel {

    phieugiamgiarepo repo=new phieugiamgiarepo();
    List<phieugiamgia> list;
    List<phieugiamgia> list1;
    List<phieugiamgia> list2;
    List<phieugiamgia> list3;
    List<phieugiamgia> list4;
    List<nhanvien1> listnhanvien;
    List<phieugiamgiachitiet> listpggct;
    List<loaiphieu> listloaiphieu;
    
    public Form_P_GG(boolean role) {
        initComponents();
        filltableall("");
        filltablenhanvien("");
        filltablpggct();
        filltabledangdienra("");
        filltabledaketthuc("");
        filltablesapdienra("");
        filltableloaiphieu();
        filltabledasd("");
        if(!role){
            btnChonLP.setEnabled(false);
            btnChonNV.setEnabled(false);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
        }
    }

    public void filltableall(String key){
        DefaultTableModel model= (DefaultTableModel) tblall.getModel();
        model.setRowCount(0);
        list=repo.getall(key);
        for(phieugiamgia pgg:list){
            model.addRow(new Object[]{
                pgg.getIdPGG(),pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getDonHangToiThieu(),pgg.getNgayBatDau(),pgg.getNgayKetThuc(),pgg.getNgayTao(),
                pgg.getLoaiGiamGia(),pgg.getNhanVienTao()
            });
        }
    }
    public void filltabledasd(String key){
        DefaultTableModel model= (DefaultTableModel) tbldasd.getModel();
        model.setRowCount(0);
        list4=repo.getdasd(key);
        for(phieugiamgia pgg:list4){
            model.addRow(new Object[]{
                pgg.getIdPGG(),pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getDonHangToiThieu()
            });
        }
    }
    public void filltabledangdienra(String key){
        DefaultTableModel model= (DefaultTableModel) tbldangdienra.getModel();
        model.setRowCount(0);
        list1 = repo.getdangdienra();
        for(phieugiamgia pgg:list1){
            model.addRow(new Object[]{
                pgg.getIdPGG(),pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getDonHangToiThieu(),pgg.getNgayBatDau(),pgg.getNgayKetThuc(),pgg.getNgayTao(),
                pgg.getLoaiGiamGia(),pgg.getNhanVienTao()
            });
        }
    }
    public void filltabledaketthuc(String key){
        DefaultTableModel model= (DefaultTableModel) tbldaketthuc.getModel();
        model.setRowCount(0);
        list2=repo.getdaketthuc(key);
        for(phieugiamgia pgg:list2){
            model.addRow(new Object[]{
                pgg.getIdPGG(),pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getDonHangToiThieu(),pgg.getNgayBatDau(),pgg.getNgayKetThuc(),pgg.getNgayTao(),
                pgg.getLoaiGiamGia(),pgg.getNhanVienTao()
            });
        }
    }
    public void filltablesapdienra(String key){
        DefaultTableModel model= (DefaultTableModel) tblsapdienra.getModel();
        model.setRowCount(0);
        list3=repo.getsapdienra(key);
        for(phieugiamgia pgg:list3){
            model.addRow(new Object[]{
                pgg.getIdPGG(),pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getDonHangToiThieu(),pgg.getNgayBatDau(),pgg.getNgayKetThuc(),pgg.getNgayTao(),
                pgg.getLoaiGiamGia(),pgg.getNhanVienTao()
            });
        }
    }
    public void filltablenhanvien(String key){
        DefaultTableModel model= (DefaultTableModel) tblnhanvien.getModel();
        model.setRowCount(0);
        listnhanvien=repo.getnhanvien(key);
        for(nhanvien1 nv:listnhanvien){
            model.addRow(new Object[]{
                nv.getIdNV(),nv.getTenNV(),nv.getChucVu()
            });
        }
    }
    public void filltablpggct(){
        DefaultTableModel model= (DefaultTableModel) tblpggct.getModel();
        model.setRowCount(0);
        listpggct=repo.getphieugiamgiachitiet();
        for(phieugiamgiachitiet pgg:listpggct){
            model.addRow(new Object[]{
                pgg.getMaGiamGia(),pgg.getTenGiamGia(),pgg.getGiaTriGiam(),pgg.getHoaDonToiThieu(),pgg.getLoaiGiam(),pgg.getThoiGianKT()
            });
        }
    }
    public void filltableloaiphieu(){
        DefaultTableModel model= (DefaultTableModel) tblloaima.getModel();
        model.setRowCount(0);
        listloaiphieu=repo.getloaiphieu();
        for(loaiphieu p:listloaiphieu){
            model.addRow(new Object[]{
                p.getTenLoaiPhieu()
            });
        }
    }
    void setform(phieugiamgia pgg){
        lblidgg.setText(Integer.valueOf(+pgg.getIdPGG()).toString());
        lblid.setText("ID: "+Integer.valueOf(+pgg.getIdPGG()).toString()+"");
        txtmagg.setText(Integer.valueOf(pgg.getMaGiamGia()).toString());
        txttengg.setText(pgg.getTenGiamGia());
        txtgiatrigiam.setText(String.valueOf(pgg.getGiaTriGiam()));
        txthoadontt.setText(String.valueOf(pgg.getDonHangToiThieu()));
        txtngaybd.setText(pgg.getNgayBatDau());
        txtngaykt.setText(pgg.getNgayKetThuc());
        lblngaytao.setText(pgg.getNgayTao());
        txtmanvtao.setText(Integer.valueOf(pgg.getMaNV()).toString());
        txtloaiphieu.setText(Integer.valueOf(pgg.getIdLoaiGG()).toString());
        lbltennv.setText(pgg.getNhanVienTao());
        lblloaiphieu.setText(pgg.getLoaiGiamGia());
    }
    void setformloaiphieu(loaiphieu p){
        txtloaiphieu.setText(Integer.valueOf(p.getId()).toString());
        lblloaiphieu.setText(p.getTenLoaiPhieu());
    }
    void setformnhanvien(nhanvien1 nv){
        txtmanvtao.setText(Integer.valueOf(nv.getIdNV()).toString());
        lbltennv.setText("TÊN NHÂN VIÊN: "+nv.getTenNV()+"  |  CHỨC VỤ: "+nv.getChucVu());
    }
    public phieugiamgia getformall(){
        return new phieugiamgia(Integer.valueOf(txtmagg.getText()),
                txttengg.getText(),
                new BigDecimal(txtgiatrigiam.getText()),
                new BigDecimal(txthoadontt.getText()),
                "",
                txtngaybd.getText(),
                txtngaykt.getText(),
                "",
                "",
                Integer.valueOf(txtmanvtao.getText()),
                Integer.valueOf(txtloaiphieu.getText()),
                null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtmagg = new javax.swing.JTextField();
        txttengg = new javax.swing.JTextField();
        txtgiatrigiam = new javax.swing.JTextField();
        txtngaybd = new javax.swing.JTextField();
        txtngaykt = new javax.swing.JTextField();
        txtmanvtao = new javax.swing.JTextField();
        btnChonNV = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbltennv = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblngaytao = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtloaiphieu = new javax.swing.JTextField();
        btnChonLP = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblloaiphieu = new javax.swing.JLabel();
        lblid = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txthoadontt = new javax.swing.JTextField();
        nhanvien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttimkiemnhanvien = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpggct = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblall = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbldangdienra = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbldaketthuc = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblsapdienra = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbldasd = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        loaima = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblloaima = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblidgg = new javax.swing.JLabel();
        lbltt = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("MÃ GIẢM GIÁ");

        jLabel3.setText("TÊN GIẢM GIÁ");

        jLabel4.setText("PHẦN TRĂM GIẢM");

        jLabel5.setText("NGÀY BẮT ĐẦU");

        jLabel6.setText("NGÀY KẾT THÚC");

        jLabel7.setText("NHÂN VIÊN TẠO");

        btnChonNV.setText("CHỌN NHÂN VIÊN");
        btnChonNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNVActionPerformed(evt);
            }
        });

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lbltennv.setText("TÊN NHÂN VIÊN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltennv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltennv)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        lblngaytao.setText("NGÀY TẠO");

        jLabel13.setText("LOẠI PHIẾU");

        btnChonLP.setText("CHỌN LOẠI PHIẾU");
        btnChonLP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonLPActionPerformed(evt);
            }
        });

        lblloaiphieu.setText("LOẠI PHIẾU");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblloaiphieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblloaiphieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblid.setText("ID");

        jLabel8.setText("HOADONTOITHIEU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmagg)
                            .addComponent(txttengg)
                            .addComponent(txtgiatrigiam)
                            .addComponent(txtngaybd)
                            .addComponent(txtngaykt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lblngaytao))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtmanvtao, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnChonNV)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtloaiphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnChonLP, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthoadontt)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmagg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttengg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtgiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txthoadontt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtmanvtao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtloaiphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonLP)
                            .addComponent(jLabel13))
                        .addGap(1, 1, 1)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblngaytao)
                        .addComponent(lblid)))
                .addContainerGap())
        );

        nhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TÊN NHÂN VIÊN", "CHỨC VỤ"
            }
        ));
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        tblnhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblnhanvienKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanvien);

        jButton3.setText("HUỶ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel10.setText("NHÂN VIÊN CHI TIẾT");
        jPanel6.add(jLabel10);

        jLabel12.setText("TÌM KIẾM");

        txttimkiemnhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemnhanvienKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout nhanvienLayout = new javax.swing.GroupLayout(nhanvien);
        nhanvien.setLayout(nhanvienLayout);
        nhanvienLayout.setHorizontalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhanvienLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nhanvienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(nhanvienLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jButton3))
                    .addGroup(nhanvienLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nhanvienLayout.setVerticalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txttimkiemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblpggct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "HOÁ ĐƠN TỐI THIỂU", "LOẠI GIẢM GIÁ", "NGÀY KẾT THÚC"
            }
        ));
        jScrollPane2.setViewportView(tblpggct);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setText("TÌM KIẾM");

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "ĐƠN HÀNG TỐI THIỂU", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "NGÀY TẠO", "LOẠI MÃ", "NGƯỜI TẠO"
            }
        ));
        tblall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblallMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblall);

        jTabbedPane1.addTab("TẤT CẢ", jScrollPane8);

        tbldangdienra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "NGÀY TẠO", "LOẠI MÃ", "NGƯỜI TẠO"
            }
        ));
        tbldangdienra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldangdienraMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbldangdienra);

        jTabbedPane1.addTab("ĐANG DIỄN RA", jScrollPane9);

        tbldaketthuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "NGÀY TẠO", "LOẠI MÃ", "NGƯỜI TẠO"
            }
        ));
        tbldaketthuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldaketthucMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbldaketthuc);

        jTabbedPane1.addTab("ĐÃ KẾT THÚC", jScrollPane10);

        tblsapdienra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC", "NGÀY TẠO", "LOẠI MÃ", "NGƯỜI TẠO"
            }
        ));
        tblsapdienra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsapdienraMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblsapdienra);

        jTabbedPane1.addTab("SẮP DIỄN RA", jScrollPane11);

        tbldasd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ GIẢM GIÁ", "TÊN GIẢM GIÁ", "PHẦN TRĂM GIẢM", "NGƯỜI TẠO"
            }
        ));
        tbldasd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldasdMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbldasd);

        jTabbedPane1.addTab("ĐÃ ĐƯỢC SỬ DỤNG", jScrollPane12);

        btnXoa.setText("XOÁ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        loaima.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblloaima.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ));
        tblloaima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblloaimaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblloaima);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jLabel14.setText("LOẠI PHIẾU");

        jButton7.setText("HUỶ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loaimaLayout = new javax.swing.GroupLayout(loaima);
        loaima.setLayout(loaimaLayout);
        loaimaLayout.setHorizontalGroup(
            loaimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaimaLayout.createSequentialGroup()
                .addGroup(loaimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loaimaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loaimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loaimaLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loaimaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(86, 86, 86))
        );
        loaimaLayout.setVerticalGroup(
            loaimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaimaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loaimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("PHIẾU GIẢM GIÁ");

        lblidgg.setText("ID");

        lbltt.setText("TT");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loaima, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(lblidgg, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbltt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(310, 310, 310)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnXoa)
                                .addGap(397, 397, 397)))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblidgg)
                            .addComponent(lbltt))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(loaima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNVActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <420; i++) {
                    for (int j = 0; j < 280; j++) {
                        nhanvien.setSize(i, j);
                    }
                }
            }
        }).start();
        //        hoadonview hd= new hoadonview();
    }//GEN-LAST:event_btnChonNVActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        repo.insertall(getformall());
        filltableall("");
        filltablpggct();
        filltabledangdienra("");
        filltabledaketthuc("");
        filltablesapdienra("");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChonLPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonLPActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 270; i++) {
                    for (int j = 0; j < 205; j++) {
                        loaima.setSize(i, j);
                    }
                }
            }
        }).start();
    }//GEN-LAST:event_btnChonLPActionPerformed

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked
        // TODO add your handling code here:
        int point = tblnhanvien.getSelectedRow();
        if(point >=0){
            setformnhanvien(listnhanvien.get(point));
        }
    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void tblnhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblnhanvienKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tblnhanvienKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        nhanvien.setSize(0,280);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txttimkiemnhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemnhanvienKeyReleased
        // TODO add your handling code here:
        String key= txttimkiemnhanvien.getText();
        filltablenhanvien(key);
    }//GEN-LAST:event_txttimkiemnhanvienKeyReleased

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        // TODO add your handling code here:
        String key=txttimkiem.getText();
        filltableall(key);
        filltabledangdienra(key);
        filltabledaketthuc(key);
        filltablesapdienra(key);
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void tblallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblallMouseClicked
        // TODO add your handling code here:
        int point = tblall.getSelectedRow();
        if(point >=0){
            setform(list.get(point));
        }
    }//GEN-LAST:event_tblallMouseClicked

    private void tbldangdienraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldangdienraMouseClicked
        // TODO add your handling code here:
        int point = tbldangdienra.getSelectedRow();
        if(point >=0){
            setform(list1.get(point));
        }
    }//GEN-LAST:event_tbldangdienraMouseClicked

    private void tbldaketthucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldaketthucMouseClicked
        // TODO add your handling code here:
        int point = tbldaketthuc.getSelectedRow();
        if(point >=0){
            setform(list2.get(point));
        }
    }//GEN-LAST:event_tbldaketthucMouseClicked

    private void tblsapdienraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsapdienraMouseClicked
        // TODO add your handling code here:
        int point = tblsapdienra.getSelectedRow();
        if(point >=0){
            setform(list3.get(point));
        }
    }//GEN-LAST:event_tblsapdienraMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Integer id= Integer.valueOf(lblidgg.getText());
        repo.updateall(getformall(), id);
        filltableall("");
        filltablpggct();
        filltabledangdienra("");
        filltabledaketthuc("");
        filltablesapdienra("");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblloaimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblloaimaMouseClicked
        // TODO add your handling code here:
        int point = tblloaima.getSelectedRow();
        if(point >=0){
            setformloaiphieu(listloaiphieu.get(point));
        }
    }//GEN-LAST:event_tblloaimaMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        loaima.setSize(0,0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tbldasdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldasdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldasdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonLP;
    private javax.swing.JButton btnChonNV;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblidgg;
    private javax.swing.JLabel lblloaiphieu;
    private javax.swing.JLabel lblngaytao;
    private javax.swing.JLabel lbltennv;
    private javax.swing.JLabel lbltt;
    private javax.swing.JPanel loaima;
    private javax.swing.JPanel nhanvien;
    private javax.swing.JTable tblall;
    private javax.swing.JTable tbldaketthuc;
    private javax.swing.JTable tbldangdienra;
    private javax.swing.JTable tbldasd;
    private javax.swing.JTable tblloaima;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTable tblpggct;
    private javax.swing.JTable tblsapdienra;
    private javax.swing.JTextField txtgiatrigiam;
    private javax.swing.JTextField txthoadontt;
    private javax.swing.JTextField txtloaiphieu;
    private javax.swing.JTextField txtmagg;
    private javax.swing.JTextField txtmanvtao;
    private javax.swing.JTextField txtngaybd;
    private javax.swing.JTextField txtngaykt;
    private javax.swing.JTextField txttengg;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiemnhanvien;
    // End of variables declaration//GEN-END:variables
}
