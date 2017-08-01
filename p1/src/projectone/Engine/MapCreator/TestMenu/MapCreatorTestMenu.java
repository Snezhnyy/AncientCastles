/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.MapCreator.TestMenu;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import projectone.Engine.MapCreator.MapCreatorJFrame;
import projectone.Engine.Menu.*;
import projectone.Engine.UnitTestFrame;

/**
 *
 * @author Snowdrop
 */
public class MapCreatorTestMenu extends MMenuContainer {
    public MapCreatorTestMenu(){        
        MButton[] mb = new MButton[2];
        mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"100x40xpumpum.png").getImage(), new Point(50, 20), new Dimension(100, 40));
        mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"100x40xpompom.png").getImage(), new Point(50, 70), new Dimension(100, 40));
        activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"200x130xpumpum.png").getImage(), new Point(50, 50), new Dimension(200, 130));
    }
    
    public void click(MouseEvent e, JFrame frame){
        int i = activeMenu.click(e);
        switch(i){
            case 0 : frame.dispose(); 
            frame = new MapCreatorJFrame(); 
            frame.enable(true); 
            frame.setVisible(true);
            break;
            case 1 :
            frame.dispose(); 
            frame = new UnitTestFrame(); 
            frame.enable(true); 
            frame.setVisible(true);
            break;
            default: System.out.println("miss");
        }
        }
    }

