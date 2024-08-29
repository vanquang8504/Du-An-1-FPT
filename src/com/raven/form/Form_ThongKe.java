package com.raven.form;

import impl.CRUD_ThongKe;
import javax.swing.table.DefaultTableModel;
import model.ThongKe;

public class Form_ThongKe extends javax.swing.JPanel {

    public Form_ThongKe() {
        initComponents();
        loadData();
        showDoanhThu(false, 0, 0, 0);
        for (int i = 2000; i < 2024; i++) {
            cboLocDoanhThu.addItem(i + "");
        }
        cboLocDoanhThuThang.setVisible(false);
        cboLocDoanhThuNgay.setVisible(false);
    }

    private DefaultTableModel Model = new DefaultTableModel();

    private CRUD_ThongKe Services_ThongKe = new CRUD_ThongKe();

    int ngay, thang, nam;
    int i = 0;

    private void showDoanhThu(boolean a, int nam, int thang, int ngay) {
        Object o[] = Services_ThongKe.thongKe(a, nam, thang, ngay);
        if (o == null) {
            System.out.println(i + " null");
            i++;
            lblShowHDHuy.setText("0");
            ShowSoHD.setText("0");
            showHDThanhCong.setText("0");
            ShowDoanhThu.setText("0");
        } else {
            lblShowHDHuy.setText(String.valueOf(o[2]));
            ShowSoHD.setText(String.valueOf(o[0]));
            showHDThanhCong.setText(String.valueOf(o[1]));
            ShowDoanhThu.setText(String.valueOf(o[3]));
        }
    }

    private void loadData() {
        Model = (DefaultTableModel) tbl.getModel();
        Model.setRowCount(0);
        for (ThongKe o : Services_ThongKe.list()) {
            Model.addRow(new Object[]{
                o.getIdGiay(),
                o.getTenGiay(),
                o.getSoLuong(),
                o.getGiaBan(),
                o.getDoanhThu()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fame_ThongKe = new javax.swing.JPanel();
        HDThanhCong = new javax.swing.JPanel();
        showHDThanhCong = new javax.swing.JLabel();
        ShowDoanhThu7 = new javax.swing.JLabel();
        HDHuy = new javax.swing.JPanel();
        lblShowHDHuy = new javax.swing.JLabel();
        ShowDoanhThu8 = new javax.swing.JLabel();
        SoHoaDon = new javax.swing.JPanel();
        ShowSoHD = new javax.swing.JLabel();
        ShowDoanhThu6 = new javax.swing.JLabel();
        DoanhThu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        ShowDoanhThu1 = new javax.swing.JLabel();
        cboLocDoanhThu = new javax.swing.JComboBox<>();
        DoanhThu2 = new javax.swing.JPanel();
        ShowDoanhThu = new javax.swing.JLabel();
        ShowDoanhThu5 = new javax.swing.JLabel();
        cboLocDoanhThuThang = new javax.swing.JComboBox<>();
        cboLocDoanhThuNgay = new javax.swing.JComboBox<>();

        Fame_ThongKe.setBackground(new java.awt.Color(255, 255, 255));

        HDThanhCong.setBackground(new java.awt.Color(255, 255, 255));
        HDThanhCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thành Công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24))); // NOI18N

        showHDThanhCong.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        showHDThanhCong.setText("9999");

        ShowDoanhThu7.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        ShowDoanhThu7.setText("Hóa Đơn");

        javax.swing.GroupLayout HDThanhCongLayout = new javax.swing.GroupLayout(HDThanhCong);
        HDThanhCong.setLayout(HDThanhCongLayout);
        HDThanhCongLayout.setHorizontalGroup(
            HDThanhCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HDThanhCongLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(HDThanhCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDThanhCongLayout.createSequentialGroup()
                        .addComponent(showHDThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDThanhCongLayout.createSequentialGroup()
                        .addComponent(ShowDoanhThu7)
                        .addContainerGap())))
        );
        HDThanhCongLayout.setVerticalGroup(
            HDThanhCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HDThanhCongLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(showHDThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowDoanhThu7)
                .addContainerGap())
        );

        HDHuy.setBackground(new java.awt.Color(255, 255, 255));
        HDHuy.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hủy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24))); // NOI18N

        lblShowHDHuy.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        lblShowHDHuy.setText("9999");

        ShowDoanhThu8.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        ShowDoanhThu8.setText("Hóa Đơn");

        javax.swing.GroupLayout HDHuyLayout = new javax.swing.GroupLayout(HDHuy);
        HDHuy.setLayout(HDHuyLayout);
        HDHuyLayout.setHorizontalGroup(
            HDHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HDHuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowDoanhThu8)
                .addContainerGap())
            .addGroup(HDHuyLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblShowHDHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        HDHuyLayout.setVerticalGroup(
            HDHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HDHuyLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblShowHDHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowDoanhThu8)
                .addContainerGap())
        );

        SoHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        SoHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24))); // NOI18N

        ShowSoHD.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        ShowSoHD.setText("999");

        ShowDoanhThu6.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        ShowDoanhThu6.setText("Hóa Đơn");

        javax.swing.GroupLayout SoHoaDonLayout = new javax.swing.GroupLayout(SoHoaDon);
        SoHoaDon.setLayout(SoHoaDonLayout);
        SoHoaDonLayout.setHorizontalGroup(
            SoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SoHoaDonLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(SoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SoHoaDonLayout.createSequentialGroup()
                        .addComponent(ShowDoanhThu6)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SoHoaDonLayout.createSequentialGroup()
                        .addComponent(ShowSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        SoHoaDonLayout.setVerticalGroup(
            SoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SoHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ShowDoanhThu6)
                .addContainerGap())
        );

        DoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        DoanhThu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24))); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Số Lượng", "Giá Bán", "Doanh Thu"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        javax.swing.GroupLayout DoanhThuLayout = new javax.swing.GroupLayout(DoanhThu);
        DoanhThu.setLayout(DoanhThuLayout);
        DoanhThuLayout.setHorizontalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        DoanhThuLayout.setVerticalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ShowDoanhThu1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        ShowDoanhThu1.setText("Thống Kê Theo");

        cboLocDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả" }));
        cboLocDoanhThu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocDoanhThuItemStateChanged(evt);
            }
        });

        DoanhThu2.setBackground(new java.awt.Color(255, 255, 255));
        DoanhThu2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24))); // NOI18N

        ShowDoanhThu.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        ShowDoanhThu.setText("99999999999999999");

        ShowDoanhThu5.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        ShowDoanhThu5.setText("VND");

        javax.swing.GroupLayout DoanhThu2Layout = new javax.swing.GroupLayout(DoanhThu2);
        DoanhThu2.setLayout(DoanhThu2Layout);
        DoanhThu2Layout.setHorizontalGroup(
            DoanhThu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThu2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DoanhThu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoanhThu2Layout.createSequentialGroup()
                        .addComponent(ShowDoanhThu)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoanhThu2Layout.createSequentialGroup()
                        .addComponent(ShowDoanhThu5)
                        .addContainerGap())))
        );
        DoanhThu2Layout.setVerticalGroup(
            DoanhThu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThu2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ShowDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowDoanhThu5)
                .addContainerGap())
        );

        cboLocDoanhThuThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả" }));
        cboLocDoanhThuThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocDoanhThuThangItemStateChanged(evt);
            }
        });

        cboLocDoanhThuNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả" }));
        cboLocDoanhThuNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocDoanhThuNgayItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout Fame_ThongKeLayout = new javax.swing.GroupLayout(Fame_ThongKe);
        Fame_ThongKe.setLayout(Fame_ThongKeLayout);
        Fame_ThongKeLayout.setHorizontalGroup(
            Fame_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_ThongKeLayout.createSequentialGroup()
                .addGroup(Fame_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Fame_ThongKeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Fame_ThongKeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DoanhThu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HDThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HDHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Fame_ThongKeLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(ShowDoanhThu1)
                        .addGap(28, 28, 28)
                        .addComponent(cboLocDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocDoanhThuThang, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        Fame_ThongKeLayout.setVerticalGroup(
            Fame_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_ThongKeLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(Fame_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShowDoanhThu1)
                    .addComponent(cboLocDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocDoanhThuThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Fame_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HDThanhCong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HDHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DoanhThu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fame_ThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fame_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboLocDoanhThuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocDoanhThuItemStateChanged
        if (cboLocDoanhThu.getSelectedIndex() != 0) {
            nam = Integer.parseInt(cboLocDoanhThu.getSelectedItem().toString());
        } else {
            nam = 0;
        }
        if (nam != 0) {
            showDoanhThu(true, nam, 0, 0);
            cboLocDoanhThuThang.removeAllItems();
            cboLocDoanhThuThang.addItem("Tất Cả");
            for (int i = 1; i < 13; i++) {
                cboLocDoanhThuThang.addItem("Tháng" + i);
            }
            cboLocDoanhThuThang.setVisible(true);
        } else {
            showDoanhThu(false, 0, 0, 0);
            cboLocDoanhThuThang.setVisible(false);
            cboLocDoanhThuNgay.setVisible(false);
        }
    }//GEN-LAST:event_cboLocDoanhThuItemStateChanged

    private void cboLocDoanhThuThangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocDoanhThuThangItemStateChanged
        thang = cboLocDoanhThuThang.getSelectedIndex();
        if (thang != 0) {
            cboLocDoanhThuNgay.removeAllItems();
            cboLocDoanhThuNgay.addItem("Tất Cả");
            showDoanhThu(true, nam, thang, 0);
            if (thang == 2) {
                for (int i = 1; i <= 28; i++) {
                    cboLocDoanhThuNgay.addItem(String.valueOf(i));
                }
            } else {
                int i;
                if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
                    i = 31;
                } else {
                    i = 30;
                }
                for (int j = 1; j <= i; j++) {
                    cboLocDoanhThuNgay.addItem("" + j);
                }
            }
            cboLocDoanhThuNgay.setVisible(true);
        } else {
            showDoanhThu(true, nam, 0, 0);
            cboLocDoanhThuNgay.setVisible(false);
        }

    }//GEN-LAST:event_cboLocDoanhThuThangItemStateChanged

    private void cboLocDoanhThuNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocDoanhThuNgayItemStateChanged
        ngay = cboLocDoanhThuNgay.getSelectedIndex();
        if (cboLocDoanhThuNgay.getSelectedIndex() != 0) {
            showDoanhThu(true, nam, thang, ngay);
        } else {
            showDoanhThu(true, nam, thang, 0);
        }
    }//GEN-LAST:event_cboLocDoanhThuNgayItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DoanhThu;
    private javax.swing.JPanel DoanhThu2;
    private javax.swing.JPanel Fame_ThongKe;
    private javax.swing.JPanel HDHuy;
    private javax.swing.JPanel HDThanhCong;
    private javax.swing.JLabel ShowDoanhThu;
    private javax.swing.JLabel ShowDoanhThu1;
    private javax.swing.JLabel ShowDoanhThu5;
    private javax.swing.JLabel ShowDoanhThu6;
    private javax.swing.JLabel ShowDoanhThu7;
    private javax.swing.JLabel ShowDoanhThu8;
    private javax.swing.JLabel ShowSoHD;
    private javax.swing.JPanel SoHoaDon;
    private javax.swing.JComboBox<String> cboLocDoanhThu;
    private javax.swing.JComboBox<String> cboLocDoanhThuNgay;
    private javax.swing.JComboBox<String> cboLocDoanhThuThang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblShowHDHuy;
    private javax.swing.JLabel showHDThanhCong;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
