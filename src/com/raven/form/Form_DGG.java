/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import impl.CRUD_Giay;
import impl.CRUD_GiayCT;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.max;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import impl.DotGiamGiaDAO;
import model.DotGiamGia;
import model.Giay;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.DotGiamGiaCT;
import model.Giay_ChiTiet;

/**
 *
 * @author nguyenkhanhdang
 */
public class Form_DGG extends javax.swing.JPanel {

    DotGiamGiaDAO sv = new DotGiamGiaDAO();
    CRUD_GiayCT sg = new CRUD_GiayCT();
    DefaultTableModel tblModel = new DefaultTableModel();
    DefaultListModel listModel = new DefaultListModel();
    DefaultListModel listModel1 = new DefaultListModel();
    DefaultListModel listModel2 = new DefaultListModel();
    DefaultListModel<String> giayChiTietListModel = new DefaultListModel<>();
    Map<String, Integer> giayChiTietMap = new HashMap<>();
    DotGiamGia dgg;
    Giay_ChiTiet giay;
    private String imagePath = "";

    /**
     * Creates new form Form_DGG
     */
    public Form_DGG(boolean role) {
        initComponents();
        if (!role) {
            btnHuy.setEnabled(false);
            btnLoc.setEnabled(false);
            btnLoc.setEnabled(false);
            btnClear.setEnabled(false);
            btnXem.setEnabled(false);
            btnChonAll.setEnabled(false);
            btnChonAll1.setEnabled(false);
            btnClear1.setEnabled(false);
            btnClear2.setEnabled(false);
            btnHuy.setEnabled(false);
            btnHuyAll.setEnabled(false);
            btnHuyAll1.setEnabled(false);
            btnThemAnh1.setEnabled(false);
            btnThemAnh2.setEnabled(false);
            btnThemGiay.setEnabled(false);
            btnXem.setEnabled(false);
            btnXoaGiay.setEnabled(false);
            btnTao.setEnabled(false);
            btnSua.setEnabled(false);
     

        }
        initTBL();
        fillTable();
        fillListGiay();

    }

    public boolean ktEmpty(JTextField text, StringBuilder sb, String str) {
        String kt = text.getText();
        if (kt.isEmpty()) {
            sb.append(str);
            return false;
        }
        return true;
    }
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);

        try {
            // Parsing the input string as a date
            Date date = dateFormat.parse(dateStr);
            return false;
        } catch (ParseException e) {
            // Thrown if the input string does not match the expected format
            return true;
        }
    }

    public static Date StringDate(String date) {
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateformat.parse(date);
        } catch (Exception e) {
            System.out.println("Loi chuyen ngay");
            return null;
        }
    }

    public static String DateString(Date date) {
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateformat.format(date);
        } catch (Exception e) {
            System.out.println("Loi chuyen ngay");
            return null;
        }
    }

    public void them() {
        DotGiamGia dgg = new DotGiamGia();
        try {

            // Thay đổi tên file để lưu vào package
            String newFileName = UUID.randomUUID().toString() + ".png";

            // Sao chép file vào package của dự án 
            Path sourcePath = Path.of(imagePath);
            Path destinationPath = Path.of("/Users/nguyenkhanhdang/Desktop/Java/PRO1041_SD18312_Group/src/imageDGG/" + newFileName);  // Đường dẫn package của bạn
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            dgg.setHinhAnh("/Users/nguyenkhanhdang/Desktop/Java/PRO1041_SD18312_Group/src/imageDGG/" + newFileName);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (!ktEmpty(txtTenDGG1, sb, "Khong de trong ten !")) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        if (jdcNgayBD1.getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong de trong ngay bat dau !");
            return;
        }
        if (jdcNgayKT1.getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong de trong ngay ket thuc !");
            return;
        }
        if (!ktEmpty(txtGiam1, sb, "Khong de trong % giam !")) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        if (rdoAP1.isSelected() == false && rdoKAP1.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Chưa chọn trạng thái");
            return;
        }
        if (Double.parseDouble(txtGiam1.getText()) >= 100 || Double.parseDouble(txtGiam1.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Mức giảm % chưa hợp lệ !");
        }
        if (rdoTienMat1.isSelected() == false && rdoATM1.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Chưa chọn PTTT");
            return;
        } else {
            if (rdoTienMat1.isSelected() && rdoATM1.isSelected()) {
                dgg.setPTTT(3);
            } else if (rdoTienMat1.isSelected()) {
                dgg.setPTTT(1);
            } else if (rdoATM1.isSelected()) {
                dgg.setPTTT(2);
            }
        }

        dgg.setTenDGG(txtTenDGG1.getText());
        dgg.setNgayBD(DateString(jdcNgayBD1.getDate()));
        dgg.setNgayKT(DateString(jdcNgayKT1.getDate()));
        dgg.setGiam(Double.parseDouble(txtGiam1.getText()));
        if (rdoAP1.isSelected()) {
            dgg.setTrangThai(true);
        } else if (rdoKAP1.isSelected()) {
            dgg.setTrangThai(false);
        }

        dgg.setMoTa(txtMoTa1.getText());

        sv.add(dgg);
        JOptionPane.showMessageDialog(this, "Thêm đợt giảm giá mới thành công !");
        fillTable();

    }

    public void fillListGiay() {
        giayChiTietListModel.clear();

        for (Giay_ChiTiet giayChiTiet : sg.list(0)) {
            giayChiTietMap.put(giayChiTiet.getTenGiayChiTiet(), giayChiTiet.getIdGiayChiTiet());
        }

        for (String tenGiayChiTiet : giayChiTietMap.keySet()) {
            giayChiTietListModel.addElement(tenGiayChiTiet);
        }

        listSP.setModel(giayChiTietListModel);
    }

    public void fillTblGiay() {
        int index = tblDSDGG.getSelectedRow();
        listModel.clear();
        listModel1.clear();
        listModel2.clear();
        Object[] rows = new Object[2];

        for (DotGiamGiaCT dggct : sv.getAllCT()) {

            if (dggct.getMaDGG() == sv.getAll().get(index).getMaDGG()) {
                rows[0] = dggct.getTenGiay();
                rows[1] = dggct.getMaGiayCT();
                listModel.addElement(rows[0]);
                listModel2.addElement(rows[1]);
            }
        }

        Object[] rows2 = listModel2.toArray();

        for (Giay_ChiTiet giay : sg.list(0)) {
            Object[] rows1 = new Object[1];
            if (!Arrays.asList(rows2).contains(giay.getIdGiayChiTiet())) {
                rows1[0] = giay.getTenGiayChiTiet();
                listModel1.addElement(rows1[0]);

            }

        }
        listDSGiay.setModel(listModel);
        listSP3.setModel(listModel1);
    }

    public void initTBL() {
        String[] cols = new String[]{"Mã đợt giảm giá", "Tên đợt giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Giảm (%)", "Trạng thái", "PTTT"};
        tblModel = (DefaultTableModel) tblDSDGG.getModel();
        tblModel.setColumnIdentifiers(cols);

    }

    public void fillTable() {

        tblModel.setRowCount(0);
        for (DotGiamGia dgg : sv.getAll()) {
            Object[] rows = new Object[7];
            rows[0] = dgg.getMaDGG();
            rows[1] = dgg.getTenDGG();
            rows[2] = dgg.getNgayBD();
            rows[3] = dgg.getNgayKT();
            rows[4] = dgg.getGiam();
            if (dgg.isTrangThai()) {
                rows[5] = "Đang áp dụng";
            } else {
                rows[5] = "Không áp dụng";
            }

            if (dgg.getPTTT() == 1) {
                rows[6] = "Tiền mặt";
            } else if (dgg.getPTTT() == 2) {
                rows[6] = "ATM";
            } else if (dgg.getPTTT() == 3) {
                rows[6] = "Tất cả";
            }
            tblModel.addRow(rows);
        }
    }

    public void showclick() {
        int index = tblDSDGG.getSelectedRow();
        txtMaDGG.setText(String.valueOf(sv.getAll().get(index).getMaDGG()));
        txtTenDGG.setText(sv.getAll().get(index).getTenDGG());
        jdcNgayBD.setDate(StringDate(sv.getAll().get(index).getNgayBD()));
        jdcNgayKT.setDate(StringDate(sv.getAll().get(index).getNgayKT()));
        if (sv.getAll().get(index).isTrangThai()) {
            rdoAP.setSelected(true);
            rdoKAP.setSelected(false);
        } else {
            rdoKAP.setSelected(true);
            rdoAP.setSelected(false);
        }

    }

    public void showclick1() {
        int index = tblDSDGG.getSelectedRow();

        txtTenDGG1.setText(sv.getAll().get(index).getTenDGG());
        jdcNgayBD1.setDate(StringDate(sv.getAll().get(index).getNgayBD()));
        jdcNgayKT1.setDate(StringDate(sv.getAll().get(index).getNgayKT()));
        txtGiam1.setText(String.valueOf(sv.getAll().get(index).getGiam()));
        if (sv.getAll().get(index).isTrangThai()) {
            rdoAP1.setSelected(true);
        } else {
            rdoKAP1.setSelected(true);
        }
        if (sv.getAll().get(index).getPTTT() == 1) {
            rdoATM1.setSelected(false);
            rdoTienMat1.setSelected(true);
        } else if (sv.getAll().get(index).getPTTT() == 2) {
            rdoTienMat1.setSelected(false);
            rdoATM1.setSelected(true);
        } else if (sv.getAll().get(index).getPTTT() == 3) {
            rdoTienMat1.setSelected(true);
            rdoATM1.setSelected(true);
        }

    }

    public void showclick2() {
        int index = tblDSDGG.getSelectedRow();
        txtMaDGG2.setText(String.valueOf(sv.getAll().get(index).getMaDGG()));
        txtTenDGG2.setText(sv.getAll().get(index).getTenDGG());
        jdcNgayBD2.setDate(StringDate(sv.getAll().get(index).getNgayBD()));
        jdcNgayKT2.setDate(StringDate(sv.getAll().get(index).getNgayKT()));
        txtGiam2.setText(String.valueOf(sv.getAll().get(index).getGiam()));
        if (sv.getAll().get(index).isTrangThai()) {
            rdoAP2.setSelected(true);
        } else {
            rdoKAP2.setSelected(true);
        }
        if (sv.getAll().get(index).getPTTT() == 1) {
            rdoATM2.setSelected(false);
            rdoTienMat2.setSelected(true);
        } else if (sv.getAll().get(index).getPTTT() == 2) {
            rdoTienMat2.setSelected(false);
            rdoATM2.setSelected(true);
        } else if (sv.getAll().get(index).getPTTT() == 3) {
            rdoTienMat2.setSelected(true);
            rdoATM2.setSelected(true);
        }

    }

    public void clear() {
        txtTenDGG.setText("");
        jdcNgayBD.setDate(null);
        jdcNgayKT.setDate(null);
        txtGiamMin.setText("");
        txtGiamMax.setText("");
        rdoAP.setSelected(false);
        rdoKAP.setSelected(false);
        listSP.clearSelection();
    }

    public void clear1() {
        txtTenDGG1.setText("");
        jdcNgayBD1.setDate(null);
        jdcNgayKT1.setDate(null);
        txtGiam1.setText("");
        txtMoTa1.setText("");
        rdoTienMat1.setSelected(false);
        rdoATM1.setSelected(false);
        rdoAP1.setSelected(false);
        rdoKAP1.setSelected(false);
        lblImage1.setIcon(null);
        imagePath = "";
    }

    public void clear2() {

        txtTenDGG2.setText("");
        jdcNgayBD2.setDate(null);
        jdcNgayKT2.setDate(null);
        txtGiam2.setText("");
        txtMoTa2.setText("");
        rdoTienMat2.setSelected(false);
        rdoATM2.setSelected(false);
        rdoAP2.setSelected(false);
        rdoKAP2.setSelected(false);
        lblImage2.setIcon(null);
        imagePath = "";

    }

    public void sua() {
        DotGiamGia dgg = new DotGiamGia();
        try {

            // Thay đổi tên file để lưu vào package
            String newFileName = UUID.randomUUID().toString() + ".png";

            // Sao chép file vào package của dự án 
            Path sourcePath = Path.of(imagePath);
            Path destinationPath = Path.of("E:\\GhepCode\\NewVer\\New\\src\\imageDGG\\" + newFileName);  // Đường dẫn package của bạn
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            dgg.setHinhAnh("E:\\GhepCode\\NewVer\\New\\src\\imageDGG\\" + newFileName);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!ktEmpty(txtMaDGG2, sb, "Khong de trong ma !")) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        if (!ktEmpty(txtTenDGG2, sb, "Khong de trong ten !")) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        if (jdcNgayBD2.getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong de trong ngay bat dau !");
            return;
        }
        if (jdcNgayKT2.getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong de trong ngay ket thuc !");
            return;
        }
        if (!ktEmpty(txtGiam2, sb, "Khong de trong % giam !")) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }

        try {
            int check = Integer.parseInt(txtMaDGG2.getText());
            dgg.setMaDGG(Integer.parseInt(txtMaDGG2.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ma khong dung dinh dang so");
            return;
        }
        if (rdoAP2.isSelected() == false && rdoKAP2.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Chưa chọn trạng thái");
            return;
        }
        if (Double.parseDouble(txtGiam2.getText()) >= 100 || Double.parseDouble(txtGiam2.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Mức giảm % chưa hợp lệ !");
        }
        if (rdoTienMat2.isSelected() == false && rdoATM2.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Chưa chọn PTTT");
            return;
        } else {
            if (rdoTienMat2.isSelected() && rdoATM2.isSelected()) {
                dgg.setPTTT(3);
            } else if (rdoTienMat2.isSelected()) {
                dgg.setPTTT(1);
            } else if (rdoATM2.isSelected()) {
                dgg.setPTTT(2);
            }
        }
        dgg.setTenDGG(txtTenDGG2.getText());
        dgg.setNgayBD(DateString(jdcNgayBD2.getDate()));
        dgg.setNgayKT(DateString(jdcNgayKT2.getDate()));
        dgg.setGiam(Double.parseDouble(txtGiam2.getText()));
        if (rdoAP2.isSelected()) {
            dgg.setTrangThai(true);
        } else if (rdoKAP2.isSelected()) {
            dgg.setTrangThai(false);
        }

        dgg.setMoTa(txtMoTa2.getText());

        sv.update(dgg);
        JOptionPane.showMessageDialog(this, "Sửa lại đợt giảm giá thành công !");
        fillTable();

    }

    public void loc() {

        try {
            List<Integer> selectedGiayChiTiet = new ArrayList<>();

            // Xử lý mỗi giá trị được chọn hoặc bỏ chọn
            int[] selectedIndices = listSP.getSelectedIndices();
            for (int selectedIndex : selectedIndices) {
                String selectedTenGiayChiTiet = listSP.getModel().getElementAt(selectedIndex);

                // Lấy idGiayChiTiet từ Map
                int idGiayChiTiet = giayChiTietMap.get(selectedTenGiayChiTiet);

                // Kiểm tra idGiayChiTiet và thêm vào danh sách nếu chưa tồn tại
                if (!selectedGiayChiTiet.contains(idGiayChiTiet)) {
                    selectedGiayChiTiet.add(idGiayChiTiet);
                }
            }

            String ID = null, Ten = null, NgayBD = null, NgayKT = null, GiamMin = null, GiamMax = null, TrangThai = null;
            ID = txtMaDGG.getText();
            Ten = txtTenDGG.getText();
            if (jdcNgayBD.getDate() == null) {
                NgayBD = "";
            } else {
                NgayBD = new SimpleDateFormat("yyyy-MM-dd").format(jdcNgayBD.getDate());
            }
            if (jdcNgayKT.getDate() == null) {
                NgayKT = "";
            } else {
                NgayKT = new SimpleDateFormat("yyyy-MM-dd").format(jdcNgayKT.getDate());
            }
            GiamMin = txtGiamMin.getText();
            GiamMax = txtGiamMax.getText();
            if (rdoAP.isSelected() == true && rdoKAP.isSelected() == true) {
                TrangThai = null;
            }
            if (rdoAP.isSelected() == true && rdoKAP.isSelected() == false) {
                TrangThai = "1";
            }
            if (rdoAP.isSelected() == false && rdoKAP.isSelected() == true) {
                TrangThai = "0";
            }
            ArrayList<DotGiamGia> listLoc = sv.getAllLoc(selectedGiayChiTiet, ID, Ten, NgayBD, NgayKT, GiamMin, GiamMax, TrangThai);
            tblModel.setRowCount(0);
            for (DotGiamGia dgg : listLoc) {
                Object[] rows = new Object[7];
                rows[0] = dgg.getMaDGG();
                rows[1] = dgg.getTenDGG();
                rows[2] = dgg.getNgayBD();
                rows[3] = dgg.getNgayKT();
                rows[4] = dgg.getGiam();
                if (dgg.isTrangThai()) {
                    rows[5] = "Đang áp dụng";
                } else {
                    rows[5] = "Không áp dụng";
                }
                if (dgg.getPTTT() == 1) {
                    rows[6] = "Tiền mặt";
                } else if (dgg.getPTTT() == 2) {
                    rows[6] = "ATM";
                } else if (dgg.getPTTT() == 3) {
                    rows[6] = "Tất cả";
                }
                tblModel.addRow(rows);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void displayImage(File file) {
        try {
            // Đọc hình ảnh từ tệp
            Image originalImage = new ImageIcon(file.getAbsolutePath()).getImage();

            // Lấy kích thước của JLabel
            int labelWidth = lblImage1.getWidth();
            int labelHeight = lblImage1.getHeight();

            // Thay đổi kích thước của hình ảnh để phù hợp với JLabel
            Image resizedImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Hiển thị hình ảnh đã thay đổi kích thước trong JLabel
            ImageIcon icon = new ImageIcon(resizedImage);
            lblImage1.setIcon(icon);

            // Lưu đường dẫn của hình ảnh
            imagePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayImage1(File file) {
        try {
            // Đọc hình ảnh từ tệp
            Image originalImage = new ImageIcon(file.getAbsolutePath()).getImage();

            // Lấy kích thước của JLabel
            int labelWidth = lblImage2.getWidth();
            int labelHeight = lblImage2.getHeight();

            // Thay đổi kích thước của hình ảnh để phù hợp với JLabel
            Image resizedImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Hiển thị hình ảnh đã thay đổi kích thước trong JLabel
            ImageIcon icon = new ImageIcon(resizedImage);
            lblImage2.setIcon(icon);

            // Lưu đường dẫn của hình ảnh
            imagePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chooseAndDisplayImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayImage(selectedFile);
        }
    }

    private void chooseAndDisplayImage1() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayImage1(selectedFile);
        }
    }

    private void saveImage() {
        try {
            // Lấy đường dẫn đầy đủ của file đã chọn
            String imagePath = lblImage1.getIcon().toString().substring(6);

            // Thay đổi tên file để lưu vào package
            String newFileName = "savedImage.png";

            // Sao chép file vào package của dự án 
            Path sourcePath = Path.of(imagePath);
            Path destinationPath = Path.of("E:\\GhepCode\\NewVer\\New\\src\\imageDGG\\" + newFileName);  // Đường dẫn package của bạn
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            JOptionPane.showMessageDialog(this, "Image saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void themGiay() {
        int index = -1;
        index = tblDSDGG.getSelectedRow();

        DotGiamGiaCT dggct1 = new DotGiamGiaCT();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn đợt giảm giá !");
        } else {
            if (listSP3.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm !");

            } else {
                Object[] selectedValues = listSP3.getSelectedValuesList().toArray();
                for (Object selectedValue : selectedValues) {
                    //xac dinh id_giay

                    for (Giay_ChiTiet giay : sg.list(0)) {
                        if (giay.getTenGiayChiTiet().equals(selectedValue)) {
                            dggct1.setMaDGG(sv.getAll().get(index).getMaDGG());
                            dggct1.setMaGiayCT(giay.getIdGiayChiTiet());
                            sv.addCT(dggct1);
                            break;
                        }

                    }

                }

                JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào đợt giảm giá " + sv.getAll().get(index).getTenDGG() + " thành công !");

                fillTblGiay();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        txtMaDGG = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtGiamMin = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtGiamMax = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtTenDGG = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        listSP = new javax.swing.JList<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        rdoAP = new javax.swing.JRadioButton();
        rdoKAP = new javax.swing.JRadioButton();
        btnLoc = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnXem = new javax.swing.JButton();
        jdcNgayBD = new com.toedter.calendar.JDateChooser();
        jdcNgayKT = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenDGG1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtGiam1 = new javax.swing.JTextField();
        btnThemAnh1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTao = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        rdoTienMat1 = new javax.swing.JRadioButton();
        rdoATM1 = new javax.swing.JRadioButton();
        tgbtnPTTT1 = new javax.swing.JToggleButton();
        btnClear1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoAP1 = new javax.swing.JRadioButton();
        rdoKAP1 = new javax.swing.JRadioButton();
        lblImage1 = new javax.swing.JLabel();
        jdcNgayBD1 = new com.toedter.calendar.JDateChooser();
        jdcNgayKT1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnThemAnh2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        rdoTienMat2 = new javax.swing.JRadioButton();
        rdoATM2 = new javax.swing.JRadioButton();
        tgbtnPTTT2 = new javax.swing.JToggleButton();
        btnClear2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa2 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        txtTenDGG2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaDGG2 = new javax.swing.JTextField();
        txtGiam2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rdoAP2 = new javax.swing.JRadioButton();
        rdoKAP2 = new javax.swing.JRadioButton();
        lblImage2 = new javax.swing.JLabel();
        jdcNgayBD2 = new com.toedter.calendar.JDateChooser();
        jdcNgayKT2 = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listSP3 = new javax.swing.JList<>();
        btnThemGiay = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        btnXoaGiay = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listDSGiay = new javax.swing.JList<>();
        btnChonAll = new javax.swing.JButton();
        btnHuyAll = new javax.swing.JButton();
        btnChonAll1 = new javax.swing.JButton();
        btnHuyAll1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSDGG = new javax.swing.JTable();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LỌC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 1, 18))); // NOI18N

        jLabel45.setText("Mã đợt giảm giá:");

        txtMaDGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDGGjTextField2ActionPerformed(evt);
            }
        });

        jLabel46.setText("Khoảng ngày từ:");

        jLabel48.setText("Đến khoảng ngày:");

        jLabel49.setText("Khoảng giảm (%):");

        txtGiamMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamMinjTextField9ActionPerformed(evt);
            }
        });

        jLabel50.setText("~");

        jLabel51.setText("Tên đợt giảm giá:");

        listSP.setAutoscrolls(false);
        listSP.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSPValueChanged(evt);
            }
        });
        jScrollPane10.setViewportView(listSP);

        jLabel52.setText("Sản phẩm:");

        jLabel53.setText("Trạng thái:");

        rdoAP.setText("Đang áp dụng");

        rdoKAP.setText("Không áp dụng");

        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Documents.png"))); // NOI18N
        btnXem.setText("Xem");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });

        jdcNgayBD.setDateFormatString("yyyy-MM-dd");

        jdcNgayKT.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel49)
                    .addComponent(jLabel48)
                    .addComponent(jLabel53))
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdcNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rdoAP)
                        .addGap(18, 18, 18)
                        .addComponent(rdoKAP))
                    .addComponent(txtMaDGG)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtGiamMin, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiamMax, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addGap(18, 18, 18)
                        .addComponent(txtTenDGG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(txtMaDGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenDGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jdcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(txtGiamMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(txtGiamMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(rdoAP)
                            .addComponent(rdoKAP)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoc)
                    .addComponent(btnClear)
                    .addComponent(btnHuy)
                    .addComponent(btnXem))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TẠO ĐỢT GIẢM GIÁ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 1, 18))); // NOI18N

        jLabel7.setText("Hình ảnh background đợt giảm giá:");

        jLabel9.setText("Tên đợt giảm giá:");

        jLabel10.setText("Ngày bắt đầu:");

        jLabel11.setText("Ngày kết thúc:");

        jLabel12.setText("Giảm (%):");

        btnThemAnh1.setText("Thêm");
        btnThemAnh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAnh1ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều kiện"));

        btnTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnTao.setText("Tạo");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        jLabel18.setText("Áp dụng phương thức thanh toán:");

        rdoTienMat1.setText("Tiền mặt");
        rdoTienMat1.setEnabled(false);

        rdoATM1.setText("ATM");
        rdoATM1.setEnabled(false);
        rdoATM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoATM1ActionPerformed(evt);
            }
        });

        tgbtnPTTT1.setText("Mở/Đóng");
        tgbtnPTTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnPTTT1ActionPerformed(evt);
            }
        });

        btnClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        btnClear1.setText("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        txtMoTa1.setColumns(20);
        txtMoTa1.setRows(5);
        jScrollPane3.setViewportView(txtMoTa1);

        jLabel4.setText("Mô tả:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdoTienMat1)
                                .addGap(18, 18, 18)
                                .addComponent(rdoATM1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tgbtnPTTT1)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(tgbtnPTTT1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTienMat1)
                            .addComponent(rdoATM1)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(70, 70, 70))
                        .addComponent(jScrollPane3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear1)
                    .addComponent(btnTao))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel5.setText("Trạng thái:");

        buttonGroup2.add(rdoAP1);
        rdoAP1.setText("Đang áp dụng");

        buttonGroup2.add(rdoKAP1);
        rdoKAP1.setText("Không áp dụng");

        lblImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage1.setText("IMG");
        lblImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jdcNgayBD1.setDateFormatString("yyyy-MM-dd");

        jdcNgayKT1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemAnh1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(rdoAP1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoKAP1))
                    .addComponent(lblImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenDGG1)
                    .addComponent(txtGiam1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgayBD1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addComponent(jdcNgayKT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnThemAnh1))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTenDGG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jdcNgayBD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jdcNgayKT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtGiam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(rdoAP1)
                    .addComponent(rdoKAP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tạo", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SỬA ĐỢT GIẢM GIÁ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 1, 18))); // NOI18N

        btnThemAnh2.setText("Thêm");
        btnThemAnh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAnh2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Tên đợt giảm giá:");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều kiện"));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel23.setText("Áp dụng phương thức thanh toán:");

        rdoTienMat2.setText("Tiền mặt");
        rdoTienMat2.setEnabled(false);

        rdoATM2.setText("ATM");
        rdoATM2.setEnabled(false);
        rdoATM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoATM2ActionPerformed(evt);
            }
        });

        tgbtnPTTT2.setText("Mở/Đóng");
        tgbtnPTTT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnPTTT2ActionPerformed(evt);
            }
        });

        btnClear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        btnClear2.setText("Clear");
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mô tả:");

        txtMoTa2.setColumns(20);
        txtMoTa2.setRows(5);
        jScrollPane2.setViewportView(txtMoTa2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rdoTienMat2)
                                .addGap(28, 28, 28)
                                .addComponent(rdoATM2))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tgbtnPTTT2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnClear2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(184, 184, 184))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear2)
                            .addComponent(btnSua)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(tgbtnPTTT2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTienMat2)
                            .addComponent(rdoATM2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel14.setText("Ngày bắt đầu:");

        jLabel15.setText("Ngày kết thúc:");

        jLabel24.setText("Giảm (%):");

        jLabel3.setText("Mã đợt giảm giá:");

        txtMaDGG2.setEditable(false);
        txtMaDGG2.setBackground(new java.awt.Color(204, 204, 204));
        txtMaDGG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDGG2ActionPerformed(evt);
            }
        });

        txtGiam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiam2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Hình ảnh background đợt giảm giá:");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Trạng thái:");

        buttonGroup1.add(rdoAP2);
        rdoAP2.setText("Đang áp dụng");

        buttonGroup1.add(rdoKAP2);
        rdoKAP2.setText("Không áp dụng");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoAP2)
                .addGap(18, 18, 18)
                .addComponent(rdoKAP2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoAP2)
                    .addComponent(rdoKAP2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage2.setText("IMG");
        lblImage2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jdcNgayBD2.setDateFormatString("yyyy-MM-dd");

        jdcNgayKT2.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemAnh2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel24)
                            .addComponent(jLabel3))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jdcNgayBD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdcNgayKT2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenDGG2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaDGG2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiam2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemAnh2)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMaDGG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenDGG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jdcNgayBD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jdcNgayKT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGiam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sửa", jPanel3);

        listSP3.setToolTipText("");
        listSP3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane6.setViewportView(listSP3);

        btnThemGiay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnThemGiay.setText("Thêm");
        btnThemGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGiayActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 153, 255));
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("Name");

        btnXoaGiay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnXoaGiay.setText("Xoá");
        btnXoaGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGiayActionPerformed(evt);
            }
        });

        listDSGiay.setToolTipText("");
        listDSGiay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane8.setViewportView(listDSGiay);

        btnChonAll.setText("Chọn tất cả");
        btnChonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAllActionPerformed(evt);
            }
        });

        btnHuyAll.setText("Huỷ tất cả");
        btnHuyAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyAllActionPerformed(evt);
            }
        });

        btnChonAll1.setText("Chọn tất cả");
        btnChonAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAll1ActionPerformed(evt);
            }
        });

        btnHuyAll1.setText("Huỷ tất cả");
        btnHuyAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyAll1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Danh sách sản phẩm:");

        jLabel21.setText("Sản phẩm được áp dụng:");

        jLabel22.setText("-------->");

        jLabel25.setText("-------->");

        jLabel26.setText("<--------");

        jLabel27.setText("<--------");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnXoaGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThemGiay, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(119, 119, 119))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnChonAll)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonAll1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyAll1)
                        .addGap(129, 129, 129))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblName)
                        .addGap(163, 163, 163)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnChonAll)
                                    .addComponent(btnHuyAll)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnChonAll1)
                                    .addComponent(btnHuyAll1))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Điều kiện", jPanel11);

        tblDSDGG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDSDGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSDGGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSDGG);

        jScrollPane4.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 506, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(238, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaDGGjTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDGGjTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDGGjTextField2ActionPerformed

    private void txtGiamMinjTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamMinjTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamMinjTextField9ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        //loc();
        loc();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtMaDGG.setText("");
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        // TODO add your handling code here:
        if (txtMaDGG.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 đợt giảm giá !");
        } else {
            int data = tblDSDGG.getSelectedRow();
            View_DGGChiTiet vdggct = new View_DGGChiTiet(data);
            vdggct.setVisible(true);
        }
    }//GEN-LAST:event_btnXemActionPerformed

    private void btnThemAnh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAnh1ActionPerformed
        // TODO add your handling code here:
        chooseAndDisplayImage();
    }//GEN-LAST:event_btnThemAnh1ActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnTaoActionPerformed

    private void rdoATM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoATM1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoATM1ActionPerformed

    private void tgbtnPTTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnPTTT1ActionPerformed
        // TODO add your handling code here:

        if (tgbtnPTTT1.isSelected()) {
            rdoTienMat1.setEnabled(true);
            rdoATM1.setEnabled(true);
        } else {
            rdoTienMat1.setSelected(true);
            rdoTienMat1.setEnabled(false);
            rdoATM1.setSelected(true);
            rdoATM1.setEnabled(false);
        }
    }//GEN-LAST:event_tgbtnPTTT1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:

        clear1();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnThemAnh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAnh2ActionPerformed
        // TODO add your handling code here
        chooseAndDisplayImage1();
    }//GEN-LAST:event_btnThemAnh2ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void rdoATM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoATM2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoATM2ActionPerformed

    private void tgbtnPTTT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnPTTT2ActionPerformed
        // TODO add your handling code here:
        if (tgbtnPTTT2.isSelected()) {
            rdoTienMat2.setEnabled(true);
            rdoATM2.setEnabled(true);
        } else {
            rdoTienMat2.setSelected(true);
            rdoTienMat2.setEnabled(false);
            rdoATM2.setSelected(true);
            rdoATM2.setEnabled(false);
        }
    }//GEN-LAST:event_tgbtnPTTT2ActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void txtMaDGG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDGG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDGG2ActionPerformed

    private void txtGiam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiam2ActionPerformed

    private void btnThemGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGiayActionPerformed
        // TODO add your handling code here:
        themGiay();
    }//GEN-LAST:event_btnThemGiayActionPerformed

    private void btnXoaGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGiayActionPerformed
        // TODO add your handling code here:
        int index = -1;
        index = tblDSDGG.getSelectedRow();
        DotGiamGiaCT dggct1 = new DotGiamGiaCT();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn đợt giảm giá !");
            return;
        } else if (listDSGiay.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm !");
            return;

        } else {
            Object[] selectedValues = listDSGiay.getSelectedValuesList().toArray();
            for (Object selectedValue : selectedValues) {
                for (DotGiamGiaCT dggct : sv.getAllCT()) {
                    // check trung primary key
                    if (dggct.getMaDGG() == sv.getAll().get(index).getMaDGG() && dggct.getTenGiay().equals(selectedValue)) {
                        dggct1.setMaDGG(sv.getAll().get(index).getMaDGG());
                        dggct1.setMaGiayCT(dggct.getMaGiayCT());
                        sv.deleteCT(dggct);

                    }

                }
            }

            JOptionPane.showMessageDialog(this, "Xoá sản phẩm khỏi đợt giảm giá " + sv.getAll().get(index).getTenDGG() + " thành công !");

            fillTblGiay();
        }
    }//GEN-LAST:event_btnXoaGiayActionPerformed

    private void btnChonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAllActionPerformed
        // TODO add your handling code here:
        int firstIndex = 0;
        int lastIndex = listSP3.getModel().getSize() - 1;

        if (lastIndex >= 0) {
            listSP3.setSelectionInterval(firstIndex, lastIndex);
        }
    }//GEN-LAST:event_btnChonAllActionPerformed

    private void btnHuyAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyAllActionPerformed
        // TODO add your handling code here:
        listSP3.clearSelection();
    }//GEN-LAST:event_btnHuyAllActionPerformed

    private void btnChonAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAll1ActionPerformed
        // TODO add your handling code here:
        int firstIndex = 0;
        int lastIndex = listSP3.getModel().getSize() - 1;

        if (lastIndex >= 0) {
            listDSGiay.setSelectionInterval(firstIndex, lastIndex);
        }
    }//GEN-LAST:event_btnChonAll1ActionPerformed

    private void btnHuyAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyAll1ActionPerformed
        // TODO add your handling code here:
        listDSGiay.clearSelection();
    }//GEN-LAST:event_btnHuyAll1ActionPerformed

    private void tblDSDGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSDGGMouseClicked
        // TODO add your handling code here:
        showclick();
        showclick1();
        showclick2();
        fillTblGiay();
        int index = tblDSDGG.getSelectedRow();
        lblName.setText(sv.getAll().get(index).getTenDGG());
    }//GEN-LAST:event_tblDSDGGMouseClicked

    private void listSPValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSPValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listSPValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAll;
    private javax.swing.JButton btnChonAll1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyAll;
    private javax.swing.JButton btnHuyAll1;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThemAnh1;
    private javax.swing.JButton btnThemAnh2;
    private javax.swing.JButton btnThemGiay;
    private javax.swing.JButton btnXem;
    private javax.swing.JButton btnXoaGiay;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcNgayBD;
    private com.toedter.calendar.JDateChooser jdcNgayBD1;
    private com.toedter.calendar.JDateChooser jdcNgayBD2;
    private com.toedter.calendar.JDateChooser jdcNgayKT;
    private com.toedter.calendar.JDateChooser jdcNgayKT1;
    private com.toedter.calendar.JDateChooser jdcNgayKT2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblImage2;
    private javax.swing.JLabel lblName;
    private javax.swing.JList<String> listDSGiay;
    private javax.swing.JList<String> listSP;
    private javax.swing.JList<String> listSP3;
    private javax.swing.JRadioButton rdoAP;
    private javax.swing.JRadioButton rdoAP1;
    private javax.swing.JRadioButton rdoAP2;
    private javax.swing.JRadioButton rdoATM1;
    private javax.swing.JRadioButton rdoATM2;
    private javax.swing.JRadioButton rdoKAP;
    private javax.swing.JRadioButton rdoKAP1;
    private javax.swing.JRadioButton rdoKAP2;
    private javax.swing.JRadioButton rdoTienMat1;
    private javax.swing.JRadioButton rdoTienMat2;
    private javax.swing.JTable tblDSDGG;
    private javax.swing.JToggleButton tgbtnPTTT1;
    private javax.swing.JToggleButton tgbtnPTTT2;
    private javax.swing.JTextField txtGiam1;
    private javax.swing.JTextField txtGiam2;
    private javax.swing.JTextField txtGiamMax;
    private javax.swing.JTextField txtGiamMin;
    private javax.swing.JTextField txtMaDGG;
    private javax.swing.JTextField txtMaDGG2;
    private javax.swing.JTextArea txtMoTa1;
    private javax.swing.JTextArea txtMoTa2;
    private javax.swing.JTextField txtTenDGG;
    private javax.swing.JTextField txtTenDGG1;
    private javax.swing.JTextField txtTenDGG2;
    // End of variables declaration//GEN-END:variables
}
