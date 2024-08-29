package com.raven.form;

import impl.CRUD_ThuocTinh;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ThuocTinh;

public class View_SubThuocTinh extends javax.swing.JFrame {

    public View_SubThuocTinh(int Status) {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        setStatus(Status);
        addTenThuocTinh();
        loadFromThuocTinh();
        loadData();
        Services.setUp(Status);
    }

    ArrayList<String> list = new ArrayList<>();
    DefaultTableModel Model = new DefaultTableModel();
    CRUD_ThuocTinh Services = new CRUD_ThuocTinh();
    int dongChon = -1;

    public static void main(String[] args) {
        new View_SubThuocTinh(0).setVisible(true);
    }

    void addTenThuocTinh() {
        list.add("Xuất Xứ");
        list.add("Danh Mục");
        list.add("Kích Thước");
        list.add("Màu Sắc");
        list.add("Chất Liệu");
        list.add("Kiểu Đế");
        list.add("Lớp Lót");
        list.add("Kiểu Mũi");

    }

    void loadFromThuocTinh() {
        this.setTitle(list.get(Status));
        lblTenThuocTinh.setText(list.get(Status));
        tblListsThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID",
                    list.get(Status)
                }
        ));
        this.setTitle(list.get(Status));
    }

    private int Status = 0;

    private void loadData() {
        Services.setUp(Status);
        Model = (DefaultTableModel) tblListsThuocTinh.getModel();
        Model.setRowCount(0);
        for (ThuocTinh o : Services.listThuocTinh(Status)) {
            Model.addRow(new Object[]{
                o.getIdThuocTinh(), o.getTenThuocTinh()
            });
        }
    }

    private void setStatus(int Status) {
        this.Status = Status;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblTenThuocTinh = new javax.swing.JLabel();
        txtThuocTinh = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListsThuocTinh = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(50, 30));

        jPanel2.setBackground(new java.awt.Color(225, 226, 226));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenThuocTinh.setText("Tên màu sắc");

        txtThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThuocTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRemove.setText("Xóa");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 102, 204));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblListsThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "ID"
            }
        ));
        tblListsThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListsThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListsThuocTinh);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnRemove)
                                .addGap(18, 18, 18)
                                .addComponent(btnNew))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate))
                            .addComponent(lblTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addGap(8, 8, 8))
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lblTenThuocTinh))
                            .addComponent(btnClose))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemove)
                            .addComponent(btnNew)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int check = Services.create(txtThuocTinh.getText());
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Tên " + list.get(Status) + " !!!");
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi Bất Định, Vui Lòng Thử Lại.");
            }
        }
        loadData();

        dongChon = -1;
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if (dongChon == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Dòng " + list.get(Status) + " Cần Xóa!!");
        }
        int check = Services.remove(Integer.parseInt(tblListsThuocTinh.getValueAt(dongChon, 0).toString()));
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi Bất Định, Vui Lòng Thử Lại.");
        }
        loadData();
        dongChon = -1;
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tblListsThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListsThuocTinhMouseClicked
        dongChon = tblListsThuocTinh.getSelectedRow();
        txtThuocTinh.setText(tblListsThuocTinh.getValueAt(dongChon, 1).toString());
    }//GEN-LAST:event_tblListsThuocTinhMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (dongChon == -1) {
            JOptionPane.showMessageDialog(this, "Hãy Chọn Dòng " + list.get(Status) + " Cần Sửa!!");
        }
        int check = Services.update(Integer.parseInt(tblListsThuocTinh.getValueAt(dongChon, 0).toString()), txtThuocTinh.getText());
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Tên " + list.get(Status) + " !!!");
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi Bất Định, Vui Lòng Thử Lại.");
            }
        }
        loadData();
        dongChon = -1;
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTenThuocTinh;
    private javax.swing.JTable tblListsThuocTinh;
    private javax.swing.JTextField txtThuocTinh;
    // End of variables declaration//GEN-END:variables
}
