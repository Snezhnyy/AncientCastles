/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.Menu;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import projectone.Engine.GameJFrame;
import projectone.Engine.fAction.AttackAction;
import projectone.Engine.fAction.ChooseAction;
import projectone.Engine.fAction.HealAction;
import projectone.Engine.fAction.MoveAction;
import projectone.Engine.fAction.RessurectAction;
import projectone.Engine.fAction.StoneAction;
import projectone.game.Perks;
import projectone.game.Room;
import projectone.game.UnitDescription;

/**
 *
 * @author Snowdrop
 */
public class MActionChoose extends MMenuContainer {
    public MActionChoose(){        
        MButton[] mb = new MButton[2];
        mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage(), new Point(16, 16), new Dimension(64, 64));
        mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xattack.png").getImage(), new Point(96, 16), new Dimension(64, 64));
        activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"176x96xaction.png").getImage(), new Point(50, 50), new Dimension(176, 96));
    }
    public MActionChoose(UnitDescription ud){
        this();
        if(ud.isWaterElemental) {
            MButton[] mb = new MButton[3];
            mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage(), new Point(16, 16), new Dimension(64, 64));
            mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xattack.png").getImage(), new Point(96, 16), new Dimension(64, 64));
            mb[2] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xwater.png").getImage(), new Point(176, 16), new Dimension(64, 64));
            activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"256x96xaction.png").getImage(), new Point(50, 50), new Dimension(256, 96));
        }
        if(ud.isRess) {
            MButton[] mb = new MButton[3];
            mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage(), new Point(16, 16), new Dimension(64, 64));
            mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xattack.png").getImage(), new Point(96, 16), new Dimension(64, 64));
            mb[2] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xress.png").getImage(), new Point(176, 16), new Dimension(64, 64));
            activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"256x96xaction.png").getImage(), new Point(50, 50), new Dimension(256, 96));
        }
        if(ud.isHeal) {
            MButton[] mb = new MButton[3];
            mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage(), new Point(16, 16), new Dimension(64, 64));
            mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xheal.png").getImage(), new Point(96, 16), new Dimension(64, 64));
            mb[2] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xhealM.png").getImage(), new Point(176, 16), new Dimension(64, 64));
            activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"256x96xaction.png").getImage(), new Point(50, 50), new Dimension(256, 96));
        }
        if(ud.isGolem) {
            MButton[] mb = new MButton[3];
            mb[0] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage(), new Point(16, 16), new Dimension(64, 64));
            mb[1] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xattack.png").getImage(), new Point(96, 16), new Dimension(64, 64));
            mb[2] = new MButton(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xstone.png").getImage(), new Point(176, 16), new Dimension(64, 64));
            activeMenu = new MMenu(mb, new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Backgrounds" + File.separator +"256x96xaction.png").getImage(), new Point(50, 50), new Dimension(256, 96));
        }
    }
    
    public void click(MouseEvent e){
        int i = activeMenu.click(e);
        switch(i){
            case 0 : System.out.println("Menu Item 0 ''move''");
            break;
            case 1 : System.out.println("Menu Item 1 ''attack''");
            break;
            default: System.out.println("miss");
        }
    }
    public void click(MouseEvent e, UnitDescription activeUnit, Room room, GameJFrame frame, Point cursorTile, Point position){
        int i = activeMenu.click(e);
        switch(i){
            case 0 : if(activeUnit.canMove) frame.action = new MoveAction(room, frame, cursorTile, activeUnit, position);
                break;
            case 1 :
                if(activeUnit.isHeal) {
                    frame.action = new HealAction(room, frame, cursorTile, activeUnit, position);
                    break;
                }
                if(activeUnit.canAttack) frame.action = new AttackAction(room, frame, cursorTile, activeUnit, position);
                break;
            case 2 : if(activeUnit.isWaterElemental) {
                Perks.waterElemantalWater(frame, room, activeUnit, position);
                frame.action = new ChooseAction();
            }
                        if(activeUnit.isRess){
                            frame.action = new RessurectAction(room, frame, cursorTile, activeUnit, position);
                        }
                        if(activeUnit.isHeal){
                            Perks.healOnField(frame, room, activeUnit, position);
                            frame.action = new ChooseAction();
                        }
                        if(activeUnit.isGolem){
                            frame.action = new StoneAction(room, frame, cursorTile, activeUnit, position);
                        }
                break;
            default: System.out.println("miss");
        }
    }
    public MButton[] getButtons(){
        return activeMenu.getButtons();
    }
    public void setPosition(Point cursorTile, Dimension screenPosition, JFrame frame){
        activeMenu.position.x = cursorTile.x * 64 - screenPosition.width - 10;
        activeMenu.position.y = cursorTile.y * 64 - screenPosition.height - 106;
        correctMenuPosition(frame);
    }
    private void correctMenuPosition(JFrame frame){
        if(activeMenu.getX() > frame.getWidth() - activeMenu.getWidth() - 10) activeMenu.position.x = frame.getWidth() - activeMenu.getWidth() - 10;
        if(activeMenu.getY() < 10) activeMenu.position.y += 84; 
    }
}

