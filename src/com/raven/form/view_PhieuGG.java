package com.raven.form;

import impl.phieugiamgiarepo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.phieugiamgia;

public class view_PhieuGG extends javax.swing.JFrame {

    phieugiamgiarepo repo = new phieugiamgiarepo();
    List<phieugiamgia> list;

    public view_PhieuGG(PGG pgg) {
        this.setUndecorated(true);
        initComponents();
        filltableall();
        this.setLocationRelativeTo(null);
        this.pgg = pgg;
    }
    PGG pgg;
    int index = -1;

    public void filltableall() {
        DefaultTableModel model = (DefaultTableModel) tblshow.getModel();
        model.setRowCount(0);
        list = repo.getdangdienra();
        for (phieugiamgia pgg : list) {
            model.addRow(new Object[]{
                pgg.getIdPGG(), pgg.getTenGiamGia(), pgg.getMaGiamGia(), pgg.getGiaTriGiam(), pgg.getDonHangToiThieu(), pgg.getLoaiGiamGia(), pgg.getNgayKetThuc()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblshow = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_Chon = new javax.swing.JButton();
        btn_Thoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblshow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TÊN GIẢM GIÁ", "MÃ GIẢM GIÁ", "GIÁ TRỊ GIẢM", "ĐƠN HÀNG TỐI THIỂU", "LOẠI MÃ", "HẠN SỬ DỤNG"
            }
        ));
        tblshow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblshowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblshow);
        if (tblshow.getColumnModel().getColumnCount() > 0) {
            tblshow.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Phiếu giảm giá");
        jPanel2.add(jLabel1);

        btn_Chon.setText("Chọn");
        btn_Chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChonActionPerformed(evt);
            }
        });

        btn_Thoi.setText("Thôi");
        btn_Thoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 288, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Chon)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Thoi)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Thoi)
                    .addComponent(btn_Chon))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChonActionPerformed
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Mã");
        } else {
            pgg.setIdPGG(Integer.parseInt(tblshow.getValueAt(index, 0).toString()));
            pgg.setGiamGia(Integer.parseInt(tblshow.getValueAt(index, 3).toString()));
            pgg.setToiThieu(Integer.parseInt(tblshow.getValueAt(index, 4).toString()));
            if (tblshow.getValueAt(index, 1).toString().contains("%")) {
                pgg.setLoaiGiam(2);
            } else {
                pgg.setLoaiGiam(1);
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_btn_ChonActionPerformed

    private void tblshowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblshowMouseClicked
        index = tblshow.getSelectedRow();
    }//GEN-LAST:event_tblshowMouseClicked

    private void btn_ThoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoiActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_ThoiActionPerformed
    public static void main(String args[]) {
        new view_PhieuGG(null).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Chon;
    private javax.swing.JButton btn_Thoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblshow;
    // End of variables declaration//GEN-END:variables
}
