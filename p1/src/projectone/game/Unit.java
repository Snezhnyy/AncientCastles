/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.game;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Snowdrop
 */
public class Unit {
    Image image;
    public UnitDescription ud;
    
    public Unit(){
        ud = new UnitDescription();
        image = new ImageIcon("src" + File.separator + "projectone" + File.separator+ "Image" + File.separator + "Unit" + File.separator + "default.png").getImage();
    }
    public Unit(String str, int id){
        ud = new UnitDescription(str);
        image = new ImageIcon("src" + File.separator + "projectone" + File.separator+ "Image" + File.separator + "Unit" + File.separator + str.split(" ")[0] + ".png").getImage();
        ud.setImageId(id);
    }
    
    public Image getImage(){
        return image;
    }
}
