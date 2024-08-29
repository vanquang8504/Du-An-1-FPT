package com.raven.form;

import impl.CRUD_HoaDon;
import impl.CRUD_HoaDonCT;
import impl.CRUD_TTHoaDon;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.HoaDonChiTiet;
import model.HoaDon_1;

public class Form_HoaDon extends javax.swing.JPanel {

    public Form_HoaDon() {
        initComponents();
        addCboLocTheo();
        loadDataHD(Serives_HD.list());
    }

    private DefaultTableModel Model = new DefaultTableModel();
    private CRUD_HoaDon Serives_HD = new CRUD_HoaDon();
    private CRUD_TTHoaDon Serives_TTHD = new CRUD_TTHoaDon();
    private CRUD_HoaDonCT Serives_HDCT = new CRUD_HoaDonCT();

    int indexHD;

    private void addCboLocTheo() {
        cboLocTheo.addItem("Nhân Viên");
        cboLocTheo.addItem("Phiếu Giảm Giá");
        cboLocTheo.addItem("Đợt Giảm Giá");
        cboLocTheo.addItem("Hình Thức Thanh Toán");
        cboLocTheo.addItem("Trạng Thái");
    }

    private void loadListLocHD(int locTheo, int loc) {
        if (locTheo >= 1) {
            if (loc >= 1) {
                loadDataHD(Serives_HD.listLoc(locTheo, loc));
            }
        } else {
            loadDataHD(Serives_HD.list());
        }
    }

    private void addCboLoc(int i) {
        cboLoc.removeAllItems();
        cboLoc.addItem("Chọn");
        if (cboLocTheo.getSelectedIndex() != 5) {
            if (i != 0) {
                for (String o : Serives_TTHD.listTTHD(i)) {
                    cboLoc.addItem(o);
                }
            }
        } else {
            cboLoc.addItem("Thành Công");
            cboLoc.addItem("Bị Hủy");
        }
    }

    private void loadDataHD(ArrayList<HoaDon_1> list) {
        Model = (DefaultTableModel) tblHoaDon.getModel();
        Model.setRowCount(0);
        for (HoaDon_1 o : list) {
            Model.addRow(new Object[]{
                o.getIdHoaDon(),
                o.getTenKhachHang(),
                o.getTenNhanVien(),
                o.getNgayLap(),
                o.getPhuongThucTT(),
                o.getNgayLap(),
                o.getPhieuGG(),
                o.getDotGG(),
                o.getSoTienGGVC(),
                o.getSoTienGGCT(),
                o.getTongTien(),
                o.getThanhToan(),
                o.getTienKhachDua(),
                o.getTienThua(),
                o.getGhiChi(),
                o.getTrangThai()
            });
        }
    }

    private void loadDataHDCT(int id) {
        Model = (DefaultTableModel) tblHDCT.getModel();
        Model.setRowCount(0);
        for (HoaDonChiTiet o : Serives_HDCT.list(id)) {
            Model.addRow(new Object[]{
                o.getIdHoaDonCT(),
                o.getIdHoaDon(),
                o.getTenGiay(),
                o.getSoLuong(),
                o.getGiaBan(),
                o.getThanhTien()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FameChinh = new javax.swing.JPanel();
        FameHDCT = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cboLocTheo = new javax.swing.JComboBox<>();
        cboLoc = new javax.swing.JComboBox<>();

        FameChinh.setBackground(new java.awt.Color(255, 255, 255));

        FameHDCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID Hóa Đơn", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblHDCT);

        javax.swing.GroupLayout FameHDCTLayout = new javax.swing.GroupLayout(FameHDCT);
        FameHDCT.setLayout(FameHDCTLayout);
        FameHDCTLayout.setHorizontalGroup(
            FameHDCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameHDCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
        );
        FameHDCTLayout.setVerticalGroup(
            FameHDCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameHDCTLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 18))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Khách Hàng", "Nhân Viên", "Ngày Lập", "Hình Thức TT", "Ngày Tạo", "Phiếu Giảm Giá", "Đợt GG", "Giảm VC", "Giảm CT", "Tổng Tiền", "Thanh Toán", "Tiền Khách Đưa", "Tiền Thừa", "Ghi Chú", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTimKiem.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 14))); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cboLocTheo.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        cboLocTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));
        cboLocTheo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        cboLocTheo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocTheoItemStateChanged(evt);
            }
        });

        cboLoc.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        cboLoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 14))); // NOI18N
        cboLoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout FameChinhLayout = new javax.swing.GroupLayout(FameChinh);
        FameChinh.setLayout(FameChinhLayout);
        FameChinhLayout.setHorizontalGroup(
            FameChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameChinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FameChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FameChinhLayout.createSequentialGroup()
                        .addComponent(FameHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(FameChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLocTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        FameChinhLayout.setVerticalGroup(
            FameChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(FameChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FameChinhLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboLocTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(FameChinhLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(FameHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FameChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FameChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        indexHD = tblHoaDon.getSelectedRow();
        loadDataHDCT(Integer.parseInt(tblHoaDon.getValueAt(indexHD, 0).toString()));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        loadDataHD(Serives_HD.listTimKiem(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboLocTheoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTheoItemStateChanged
        addCboLoc(cboLocTheo.getSelectedIndex());
    }//GEN-LAST:event_cboLocTheoItemStateChanged

    private void cboLocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocItemStateChanged
        loadListLocHD(cboLocTheo.getSelectedIndex(), cboLoc.getSelectedIndex());
    }//GEN-LAST:event_cboLocItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FameChinh;
    private javax.swing.JPanel FameHDCT;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JComboBox<String> cboLocTheo;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
