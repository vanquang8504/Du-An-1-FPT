/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate_form;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author phamt
 */
public class VLD {
    public static boolean check_null(JTextField txt){
        String text = txt.getText();
        return !(text.isEmpty() || text.equals(""));
    }
    public static boolean check_cbox(JComboBox cbox){
        int index = cbox.getSelectedIndex();
        return !(index == -1 || index == 0);
    }
}
