package com.raven.main;

import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.form.Form_BanHang;
import com.raven.form.Form_Giay;
import com.raven.form.Form_HoaDon;
import com.raven.form.Form_Home;
import com.raven.form.Form_Home1;
import com.raven.form.Form_K_Hang;
import com.raven.form.MainForm;

import com.raven.form.Form_P_GG;
import com.raven.form.Form_ThongKe;

import com.raven.form.Form_DGG;
import com.raven.form.Form_NhanVien;
import com.raven.form.Form_PhieuDoi;

import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;

    public Main(int idnv, String tenNv, boolean role) {
        initComponents();
        init();
        this.idNV = idnv;
        this.tenNv = tenNv;
        this.role = role;
    }

    private int idNV;
    private String tenNv;
    private boolean role;

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        menu.addEvent((int menuIndex, int subMenuIndex) -> {
            System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
            switch (menuIndex) {
                case 0:
                    main.showForm(new Form_Home1());
                    break;
                case 1:
                    switch (subMenuIndex) {
                        case 0:
                            main.showForm(new Form_BanHang(idNV, tenNv)); // gọi bán hàng
                            break;
                        case 1:
                            main.showForm(new Form_HoaDon());
                            break;
                        case 2:
                            main.showForm(new Form_PhieuDoi());
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    if (role) {
                        main.showForm(new Form_ThongKe());
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Xin Lỗi, Nhân Viên Không Được Vào Phần Này.");
                        break;
                    }
                case 3:
                    main.showForm(new Form_Giay(role));
                    break;
                case 4:
                    switch (subMenuIndex) {
                        case 0:
                            main.showForm(new Form_DGG(role));
                            break;
                        case 1:
                            main.showForm(new Form_P_GG(role));
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    main.showForm(new Form_K_Hang());
                    break;
                case 6:
                    if (role) {
                        main.showForm(new Form_NhanVien());
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Xin Lỗi, Nhân Viên Không Được Vào Phần Này.");
                        break;
                    }
//                case 7:
//                    if (role) {
//                        main.showForm(new Form_Giay(role));
//                        break;
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Xin Lỗi, Nhân Viên Không Được Vào Phần Này.");
//                        break;
//                    }
                case 7:
                    new DangNhap().setVisible(true);
                    this.dispose();
                default:
                    break;
            }

        });
        menu.addEventShowPopup((Component com) -> {
            MenuItem item = (MenuItem) com;
            PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
            int x1 = Main.this.getX() + 52;
            int y1 = Main.this.getY() + com.getY() + 86;
            popup.setLocation(x1, y1);
            popup.setVisible(true);
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent((ActionEvent ae) -> {
            if (!animator.isRunning()) {
                animator.start();
            }
            menu.setEnableMenu(false);
            if (menu.isShowMenu()) {
                menu.hideallMenu();
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new Form_Home1());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Main(1, "", true).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
