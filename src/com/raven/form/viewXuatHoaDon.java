package com.raven.form;

import impl.CRUD_HoaDonCT;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import model.HoaDonChiTiet;
import model.HoaDon_1;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class viewXuatHoaDon extends javax.swing.JFrame {

    public viewXuatHoaDon(HoaDon_1 o, int id) {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        loadDataHDCT(id);
        this.o = o;
        this.id = id;
        showHoaDon();

    }

    DefaultTableModel Model = new DefaultTableModel();
    private CRUD_HoaDonCT Serives_HDCT = new CRUD_HoaDonCT();

    HoaDon_1 o;
    int id;

    private void loadDataHDCT(int id) {
        Model = (DefaultTableModel) tblHoaDon.getModel();
        Model.setRowCount(0);
        int i = 1;
        for (HoaDonChiTiet o : Serives_HDCT.list(id)) {
            Model.addRow(new Object[]{
                i,
                o.getTenGiay(),
                o.getThanhTien() / o.getSoLuong(),
                o.getSoLuong(),
                o.getThanhTien()
            });
            i++;
        }
    }

    private void showHoaDon() {
        tenKH.setText(o.getTenKhachHang());
        SoDT.setText(o.getSoDt());
        IDHOADON.setText(id + "");
        thanhToan.setText("" + o.getThanhToan());
        lblTongTien.setText("" + o.getTongTien());
        giamGiaVC.setText("" + o.getSoTienGGVC());
        giamGiaCT.setText("" + o.getSoTienGGCT());
        lblNgayMua.setText("" + o.getNgayLap());
        lbl_tennv.setText("" + o.getTenNhanVien());
        lbl_idnhanvien.setText("" + o.getIdNhanVien());
    }

    public void inHoaDon() {
        try {
            BufferedImage bi = new BufferedImage(Fame_HoaDon.getWidth(), Fame_HoaDon.getHeight(), BufferedImage.TYPE_INT_RGB);
            Fame_HoaDon.paint(bi.getGraphics());
            String imagePath = "E:\\" + id + "_" + o.getTenKhachHang() + ".jpg";
            ImageIO.write(bi, "jpg", new File(imagePath));
            String pdfPath = "E:\\" + id + "_" + o.getTenKhachHang() + ".pdf";
            convertImageToPdf(imagePath, pdfPath);
            deleteImageFile(imagePath);
        } catch (IOException e) {
        }
    }

    public static void convertImageToPdf(String imagePath, String pdfPath) {
        Document document = new Document(PageSize.A4); // Thiết lập kích thước trang A4

        try {
            FileOutputStream fos = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(document, fos);
            document.open();

            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

            // Thiết lập kích thước hình ảnh để phù hợp với trang
            Image image = Image.getInstance(bufferedImage, null);
            image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());

            document.add(image);

            document.close();
            fos.close();

            System.out.println("Conversion to PDF successful!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteImageFile(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            if (imageFile.delete()) {
                System.out.println("Image file deleted successfully.");
            } else {
                System.out.println("Failed to delete image file.");
            }
        } else {
            System.out.println("Image file does not exist.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fame_HoaDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tenKH = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SoDT = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        lblNgayMua = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        IDHOADON = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        giamGiaVC = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        thanhToan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        giamGiaCT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_tennv = new javax.swing.JLabel();
        lbl_idnhanvien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fame_HoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Giày Tây OxFord");

        tenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tenKH.setText("Tên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số Điện Thoại:");

        SoDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SoDT.setText("012345678");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tên Khách Hàng:");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên", "Đơn Giá", "Số Lượng", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(30);
            tblHoaDon.getColumnModel().getColumn(1).setMinWidth(200);
            tblHoaDon.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien.setText("......................");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày Mua:");

        lblNgayMua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayMua.setText("......................");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("ID Hóa Đơn:");

        IDHOADON.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IDHOADON.setText("......................");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Giảm Giá VC");

        giamGiaVC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        giamGiaVC.setText("......................");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("ThanhToan");

        thanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        thanhToan.setText("......................");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Giảm Giá CT");

        giamGiaCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        giamGiaCT.setText("......................");

        jLabel2.setText("Địa chỉ: FPT Polytechnic, Kiều Mai, Bắc Từ Liêm, Hà Nội");

        jLabel3.setText("Tên nhân viên: ");

        jLabel5.setText("ID nhân viên");

        lbl_tennv.setText("Tên");

        lbl_idnhanvien.setText("ID");

        javax.swing.GroupLayout Fame_HoaDonLayout = new javax.swing.GroupLayout(Fame_HoaDon);
        Fame_HoaDon.setLayout(Fame_HoaDonLayout);
        Fame_HoaDonLayout.setHorizontalGroup(
            Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(giamGiaVC, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(giamGiaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDHOADON)
                                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel1))
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(94, 94, 94)
                                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_idnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 97, Short.MAX_VALUE))))
        );
        Fame_HoaDonLayout.setVerticalGroup(
            Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(IDHOADON))
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Fame_HoaDonLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SoDT)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14)
                    .addComponent(tenKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel9)
                    .addComponent(giamGiaVC)
                    .addComponent(jLabel11)
                    .addComponent(giamGiaCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(thanhToan)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tennv))
                .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblNgayMua))
                        .addContainerGap())
                    .addGroup(Fame_HoaDonLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Fame_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lbl_idnhanvien))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jButton1.setText("Đóng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fame_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fame_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void main(String args[]) {
        new viewXuatHoaDon(null, 0).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fame_HoaDon;
    private javax.swing.JLabel IDHOADON;
    private javax.swing.JLabel SoDT;
    private javax.swing.JLabel giamGiaCT;
    private javax.swing.JLabel giamGiaVC;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblNgayMua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lbl_idnhanvien;
    private javax.swing.JLabel lbl_tennv;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JLabel tenKH;
    private javax.swing.JLabel thanhToan;
    // End of variables declaration//GEN-END:variables
}
