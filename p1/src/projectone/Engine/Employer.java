/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine;

/**
 *
 * @author Snowdrop
 */
public class Employer {
    public static int convertToInteger(String str) {
        try {
            int i = Integer.valueOf(str);
            return i;
        }
        catch(Exception e) { e.printStackTrace();}
        return 0;
    }
    public static void toIntegerField(javax.swing.JTextField textField) {
        textField.setText(String.valueOf(convertToInteger(textField.getText())));
    }
}
