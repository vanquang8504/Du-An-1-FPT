/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import Validate_form.VLD;
import impl.CTPDoi_DAO;
import impl.PhieuDoi_DAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.PD_CTiet;
import model.TT_Giay;

/**
 *
 * @author phamt
 */
public class Form_CT_PDoi extends javax.swing.JDialog {
    
    private static final String sql_Cbo_ld = "SELECT * FROM LyDoDoiTra";
    private static final String sql_Cbo_tt = "SELECT * FROM TrangThaiPhieu";
    private static final String sql_Cbo_kho = "SELECT ID_Kho, Ten_Kho FROM Kho_Hang";
    
    DefaultTableModel tblModel = new DefaultTableModel();
    private int index = -1;
    
    public Form_CT_PDoi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        jpn_sp_doi.setVisible(false);
        String id = Form_PhieuDoi.pd.getMa_Dai();
        jlb_ID_phieu.setText(id);
        init_CBo();
        Fill_TBL();
    }
    private void init_CBo(){
        PhieuDoi_DAO.getCBO(cbo_L_Do, sql_Cbo_ld);
        PhieuDoi_DAO.getCBO(cbo_T_Thai, sql_Cbo_tt);
        PhieuDoi_DAO.getCBO(cbo_kho, sql_Cbo_kho);
    }
    public void reset(){
        jlb_ID_CTP.setText("");
        jlb_ID_Giay_CS.setText("");
        txt_MPhieu.setText("");
        jlb_ID_SP_cu.setText("");
        jlb_SP_moi.setText("");
        txt_SLD.setText("");
        txt_SL_Max.setText("");
        txt_SP_HD.setText("");
        txt_SP_D.setText("");
        txt_Gia_cu.setText("");
        txt_Gia_moi.setText("");
        cbo_T_Thai.setSelectedIndex(0);
        cbo_L_Do.setSelectedIndex(0);
        cbo_kho.setSelectedIndex(0);
        txt_note.setText("");
    }
    
    public PD_CTiet setModel(PD_CTiet p){
        jlb_ID_CTP.setText(p.getID_CTPD()+"");
        jlb_ID_Giay_CS.setText(p.getID_Giay()+"");
        txt_MPhieu.setText(p.getMa_PD());
        jlb_ID_SP_cu.setText(p.getID_SP_HD()+"");
        jlb_SP_moi.setText(p.getID_SPD()+"");
        txt_SLD.setText(p.getSLD()+"");
        txt_SL_Max.setText(p.getSL_IN_HD()+"");
        txt_SP_HD.setText(p.getTen_SP_HD());
        txt_SP_D.setText(p.getTen_SPD());
        txt_Gia_cu.setText(p.getGia_DOi()+"");
        txt_Gia_moi.setText(p.getGia_DOi()+"");
        cbo_T_Thai.setSelectedIndex(p.getID_TThai_P());
        cbo_L_Do.setSelectedIndex(p.getID_LDo());
        cbo_kho.setSelectedIndex(p.getID_Kho());
        txt_note.setText(p.getNote());
        return p;
    }
    public PD_CTiet getModel(){
        PD_CTiet p = new PD_CTiet();
        p.setID_PD(Integer.parseInt(jlb_ID_phieu.getText()));
        p.setID_SP_HD(Integer.parseInt(jlb_ID_SP_cu.getText()));
        p.setID_SPD(Integer.parseInt(jlb_SP_moi.getText()));
        p.setSLD(Integer.parseInt(txt_SLD.getText()));
        p.setID_TThai_P(cbo_T_Thai.getSelectedIndex());
        p.setID_LDo(cbo_L_Do.getSelectedIndex());
        p.setID_Kho(cbo_kho.getSelectedIndex());
        p.setNote(txt_note.getText());
        p.setID_CTPD(Integer.parseInt(jlb_ID_CTP.getText()));
        return p;
    }
    
    private void Fill_TBL(){
        tblModel = (DefaultTableModel) tbl_CT_Phieu.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã Phiếu đổi", "SP Trước đổi", "SP Sau đổi", "SL đổi", "Giá", "Lý do", "Trạng thái", "Ghi chú"
        };
        tblModel.setColumnIdentifiers(column);
        for (PD_CTiet p : CTPDoi_DAO.get_CTP(jlb_ID_phieu.getText())) {
            Object[] row = {
                p.getMa_PD(), p.getTen_SP_HD(), p.getTen_SPD(), p.getSLD(), 
                p.getGia_DOi(), p.getLDo(), p.getTThai(), p.getNote()
            };
            tblModel.addRow(row);
        }
    }
    private void Click_TBL (){
        index = tbl_CT_Phieu.getSelectedRow();
        setModel(CTPDoi_DAO.get_CTP(jlb_ID_phieu.getText()).get(index));
        Fill_TBL_SPD();
        
    }
    public void Cap_nhat(){
        index = tbl_CT_Phieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dữ liệu cần thay đổi thông tin.");
        }else if (!vld_Add()) {
            return;
        }  else {
            int poin = JOptionPane.showConfirmDialog(this, "Xác nhận thay đổi thông tin.");
            if (poin == 0) {
                CTPDoi_DAO.UPDATE_DATA(getModel());
                Fill_TBL();
                reset();
                JOptionPane.showMessageDialog(this, "Thành công.");
            }
        }
    }
    private void Fill_TBL_SPD(){
        tblModel = (DefaultTableModel) tbl_SPDoi.getModel();
        tblModel.setRowCount(0);
        String[] column = {
            "Mã giày", "Mã giày CT", "Tên Giày CT", "Giá Bán"
        };
        tblModel.setColumnIdentifiers(column);
        for (TT_Giay o : PhieuDoi_DAO.getSP_Doi( jlb_ID_Giay_CS.getText())) {
            tblModel.addRow(new Object[]{
                o.getId_giay(),
                o.getID_CTG(),
                o.getTGiay_CT(),
                o.getGiaBan()
            });
        }
        jlb_SL_SP_CTD.setText("Số Lượng SP: " + PhieuDoi_DAO.getSP_Doi( jlb_ID_Giay_CS.getText()).size());
    }
    private void Clicked_SPD (){
        index = tbl_SPDoi.getSelectedRow();
        Object id_gct = tbl_SPDoi.getValueAt(index, 0);
        Object ID_SPD = tbl_SPDoi.getValueAt(index, 1);
        Object T_CT = tbl_SPDoi.getValueAt(index, 2);
        Object G_new = tbl_SPDoi.getValueAt(index, 3);
        jlb_SP_moi.setText(id_gct+"");
        jlb_SP_moi.setText(ID_SPD+"");
        txt_SP_D.setText(T_CT+"");
        txt_Gia_moi.setText(G_new+"");
        jpn_sp_doi.setVisible(false);
    }
    public boolean vld_Add(){
        int i = 0;
        String msg = "";
            if (!VLD.check_null(txt_SLD)) {
                msg = msg + "Nhập số lượng cần đổi\n";
                i = -1;
            }
            try {
                int sl_mua = Integer.parseInt(txt_SL_Max.getText());
                int sl_doi = Integer.parseInt(txt_SLD.getText());
                if (sl_doi > sl_mua || sl_doi <= 0) {
                    msg = msg + "Số lượng mua phải > 0 và <= số lượng max\n";
                    i = -1;
                }
            } catch (NumberFormatException e) {
                    msg = msg + "Số lượng mua phải là số nguyên\n";
                    i = -1;
            }
            if (!VLD.check_cbox(cbo_L_Do)) {
                msg = msg + "Chọn lý do đổi\n";
                i = -1;
            }
            if (!VLD.check_cbox(cbo_T_Thai)) {
                msg = msg + "Chọn trạng thái phiếu\n";
                i = -1;
            }
            if (!VLD.check_cbox(cbo_kho)) {
                msg = msg + "Chọn kho lưu sản phẩm đổi\n";
                i = -1;
            }
        if (i == -1) {
            JOptionPane.showMessageDialog(this, msg);
            return false;
        } else {
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_MPhieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_SP_HD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_SLD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_note = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_Gia_cu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jlb_ID_phieu = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_SP_D = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_CT_Phieu = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_SL_Max = new javax.swing.JTextField();
        txt_Gia_moi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbo_L_Do = new javax.swing.JComboBox<>();
        cbo_T_Thai = new javax.swing.JComboBox<>();
        cbo_kho = new javax.swing.JComboBox<>();
        jlb_ID_SP_cu = new javax.swing.JLabel();
        jlb_SP_moi = new javax.swing.JLabel();
        jlb_ID_CTP = new javax.swing.JLabel();
        jlb_ID_Giay_CS = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jpn_sp_doi = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_SPDoi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jlb_SL_SP_CTD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(240, 247, 212));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin chi tiết");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Mã phiếu");

        txt_MPhieu.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Sản phẩm HD");

        txt_SP_HD.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("SL đổi");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Trạng thái");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Lý do");

        txt_note.setColumns(20);
        txt_note.setRows(5);
        jScrollPane1.setViewportView(txt_note);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Ghi chú");

        btn_update.setText("Cập nhật");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Giá bán");

        txt_Gia_cu.setEditable(false);
        txt_Gia_cu.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ID phiếu");

        jlb_ID_phieu.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jlb_ID_phieu.setForeground(new java.awt.Color(255, 0, 0));
        jlb_ID_phieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_ID_phieu.setText("ID");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Sản phẩm đổi");

        txt_SP_D.setEnabled(false);

        tbl_CT_Phieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_CT_Phieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CT_PhieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_CT_Phieu);

        jButton1.setText("Chọn SP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Số lượng max");

        txt_SL_Max.setEditable(false);
        txt_SL_Max.setEnabled(false);

        txt_Gia_moi.setEditable(false);
        txt_Gia_moi.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Giá mới");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Kho hàng");

        cbo_L_Do.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lý do đổi" }));

        cbo_T_Thai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái phiếu" }));

        cbo_kho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kho hàng" }));

        jlb_ID_SP_cu.setBackground(new java.awt.Color(240, 247, 212));
        jlb_ID_SP_cu.setForeground(new java.awt.Color(240, 247, 212));

        jlb_SP_moi.setBackground(new java.awt.Color(240, 247, 212));
        jlb_SP_moi.setForeground(new java.awt.Color(240, 247, 212));

        jlb_ID_CTP.setBackground(new java.awt.Color(240, 247, 212));
        jlb_ID_CTP.setForeground(new java.awt.Color(240, 247, 212));

        jlb_ID_Giay_CS.setBackground(new java.awt.Color(240, 247, 212));
        jlb_ID_Giay_CS.setForeground(new java.awt.Color(240, 247, 212));

        jLabel18.setBackground(new java.awt.Color(240, 247, 212));
        jLabel18.setForeground(new java.awt.Color(240, 247, 212));

        tbl_SPDoi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_SPDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPDoiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_SPDoi);

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Sản phẩm có thể đổi");

        jlb_SL_SP_CTD.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jlb_SL_SP_CTD.setText("Số sản phẩm : . . .");

        javax.swing.GroupLayout jpn_sp_doiLayout = new javax.swing.GroupLayout(jpn_sp_doi);
        jpn_sp_doi.setLayout(jpn_sp_doiLayout);
        jpn_sp_doiLayout.setHorizontalGroup(
            jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlb_SL_SP_CTD, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jpn_sp_doiLayout.setVerticalGroup(
            jpn_sp_doiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_sp_doiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb_SL_SP_CTD)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_MPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                    .addComponent(txt_SP_HD)
                                    .addComponent(txt_SLD, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_Gia_cu)
                                    .addComponent(cbo_T_Thai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_SP_D, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlb_ID_phieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_SL_Max, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Gia_moi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_L_Do, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpn_sp_doi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_kho, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_update)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlb_ID_SP_cu, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_SP_moi, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_ID_CTP, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlb_ID_Giay_CS, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_MPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jlb_ID_phieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_SP_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_SP_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn_sp_doi, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SLD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_SL_Max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btn_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_Gia_cu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_Gia_moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_T_Thai)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cbo_L_Do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_kho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_ID_CTP, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlb_ID_SP_cu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlb_SP_moi, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlb_ID_Giay_CS, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        Cap_nhat();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tbl_CT_PhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CT_PhieuMouseClicked
        Click_TBL();
    }//GEN-LAST:event_tbl_CT_PhieuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        index = tbl_CT_Phieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dữ liệu cần thay đổi thông tin.");
        } else {
            jpn_sp_doi.setVisible(true);
        }
        
//        jpn_sp_doi.setSize(665, 175);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_SPDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPDoiMouseClicked
        Clicked_SPD();
    }//GEN-LAST:event_tbl_SPDoiMouseClicked

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_CT_PDoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            Form_CT_PDoi dialog = new Form_CT_PDoi(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_L_Do;
    private javax.swing.JComboBox<String> cbo_T_Thai;
    private javax.swing.JComboBox<String> cbo_kho;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel jlb_ID_CTP;
    private javax.swing.JLabel jlb_ID_Giay_CS;
    private javax.swing.JLabel jlb_ID_SP_cu;
    private javax.swing.JLabel jlb_ID_phieu;
    private javax.swing.JLabel jlb_SL_SP_CTD;
    private javax.swing.JLabel jlb_SP_moi;
    private javax.swing.JPanel jpn_sp_doi;
    private javax.swing.JTable tbl_CT_Phieu;
    private javax.swing.JTable tbl_SPDoi;
    private javax.swing.JTextField txt_Gia_cu;
    private javax.swing.JTextField txt_Gia_moi;
    private javax.swing.JTextField txt_MPhieu;
    private javax.swing.JTextField txt_SLD;
    private javax.swing.JTextField txt_SL_Max;
    private javax.swing.JTextField txt_SP_D;
    private javax.swing.JTextField txt_SP_HD;
    private javax.swing.JTextArea txt_note;
    // End of variables declaration//GEN-END:variables
}
