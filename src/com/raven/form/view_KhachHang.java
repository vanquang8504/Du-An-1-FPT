package com.raven.form;

import impl.CRUD_KhachHang;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import service.Service_KhachHang;
import javax.swing.JOptionPane;

public final class view_KhachHang extends javax.swing.JFrame {

    public view_KhachHang(IDKH aaa) {
        this.setUndecorated(true);
        this.aaa = aaa;
        initComponents();
        fillTable(Service.getAllKhachHang());
        this.setLocationRelativeTo(null);
    }

    Service_KhachHang Service = new CRUD_KhachHang();
    int index = -1;

    IDKH aaa;

    KhachHang getInput() {
        return new KhachHang(0,
                txtTenKH.getText(),
                true,
                txtSDT.getText(),
                "",
                true);
    }

    public void fillTable(ArrayList<KhachHang> list) {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDanhSachKH.getModel();
        model.setRowCount(0);
        for (KhachHang kh : list) {
            model.addRow(new Object[]{kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getSdt(), kh.getDiaChi(), kh.isHangKhach() ? "Hoạt động" : "Ngừng hoạt động"});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        FameKhachHang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachKH = new javax.swing.JTable();
        btnChonKH = new javax.swing.JButton();
        btnAddKH = new javax.swing.JButton();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        FameThemKH = new javax.swing.JPanel();
        lblTimKiem1 = new javax.swing.JLabel();
        lblTimKiem3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thay Đổi KH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 12))); // NOI18N

        FameKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSachKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Giới Tính", "Số ĐT", "Địa Chỉ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachKH);
        if (tblDanhSachKH.getColumnModel().getColumnCount() > 0) {
            tblDanhSachKH.getColumnModel().getColumn(0).setMaxWidth(30);
            tblDanhSachKH.getColumnModel().getColumn(1).setMinWidth(150);
            tblDanhSachKH.getColumnModel().getColumn(1).setMaxWidth(150);
            tblDanhSachKH.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        btnChonKH.setText("Chọn Khách Hàng");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        btnAddKH.setText("Thêm Khách Hàng");
        btnAddKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKHActionPerformed(evt);
            }
        });

        lblTimKiem.setText("Tìm Kiếm");

        txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKHKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout FameKhachHangLayout = new javax.swing.GroupLayout(FameKhachHang);
        FameKhachHang.setLayout(FameKhachHangLayout);
        FameKhachHangLayout.setHorizontalGroup(
            FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameKhachHangLayout.createSequentialGroup()
                .addGroup(FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FameKhachHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChonKH)
                        .addGap(32, 32, 32)
                        .addComponent(btnAddKH))
                    .addGroup(FameKhachHangLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FameKhachHangLayout.createSequentialGroup()
                                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemKH)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        FameKhachHangLayout.setVerticalGroup(
            FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameKhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem)
                    .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FameKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChonKH)
                    .addComponent(btnAddKH))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Khách Hàng", FameKhachHang);

        FameThemKH.setBackground(new java.awt.Color(255, 255, 255));

        lblTimKiem1.setText("Tên Khách Hàng");

        lblTimKiem3.setText("Số Điện Thoại");

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FameThemKHLayout = new javax.swing.GroupLayout(FameThemKH);
        FameThemKH.setLayout(FameThemKHLayout);
        FameThemKHLayout.setHorizontalGroup(
            FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThemKHLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTimKiem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTimKiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        FameThemKHLayout.setVerticalGroup(
            FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FameThemKHLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem1)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(FameThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnThem)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm Khách Hàng Mới", FameThemKH);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(681, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(425, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        if (index != -1) {
            aaa.setIdKH(Integer.parseInt(tblDanhSachKH.getValueAt(index, 0).toString()));
            aaa.setTenKH(tblDanhSachKH.getValueAt(index, 1).toString());
            aaa.setSoDt(tblDanhSachKH.getValueAt(index, 3).toString());
            
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Chưa Chọn Khách Hàng");
        }
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnAddKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKHActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAddKHActionPerformed

    private void tblDanhSachKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKHMouseClicked
        index = tblDanhSachKH.getSelectedRow();
    }//GEN-LAST:event_tblDanhSachKHMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Service.ADD(getInput())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
            fillTable(Service.getAllKhachHang());
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        if (!txtSDT.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số ĐT Chỉ Chứa Số");
            txtSDT.setText(txtSDT.getText().substring(0, txtSDT.getText().length() - 1));
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtTimKiemKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyReleased
        fillTable(Service.find(txtTimKiemKH.getText()));
    }//GEN-LAST:event_txtTimKiemKHKeyReleased

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    public static void main(String args[]) {
        new view_KhachHang(null).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FameKhachHang;
    private javax.swing.JPanel FameThemKH;
    private javax.swing.JButton btnAddKH;
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTimKiem1;
    private javax.swing.JLabel lblTimKiem3;
    private javax.swing.JTable tblDanhSachKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiemKH;
    // End of variables declaration//GEN-END:variables
}
