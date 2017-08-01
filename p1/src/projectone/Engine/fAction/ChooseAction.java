/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.fAction;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import projectone.Engine.GameJFrame;
import projectone.Engine.Menu.MActionChoose;
import projectone.Engine.TRendering2D;
import projectone.game.Room;
import projectone.game.UnitDescription;

/**
 *
 * @author Snowdrop
 */
public class ChooseAction extends GeneralAction {
    MActionChoose mac;
    UnitDescription activeUnit;
    Point position;
    boolean enabled;
    
    public ChooseAction(){
        mac = new MActionChoose();
        enabled = false;
        activeUnit = null;
        position = new Point(0, 0);
    }
    
    public void click(Room room, GameJFrame frame, Point cursorTile, MouseEvent e){
        if(enabled){
            if(mac.clickOnMenu(e)){
                mac.click(e, activeUnit, room, frame, cursorTile, position);
            }
            else macOn(cursorTile, room, frame, e);
             return;
        }
        firstClickAnalize(room, cursorTile, frame);
    }
    private void firstClickAnalize(Room room, Point cursorTile, GameJFrame frame){
        if(!enabled)
            if(room.units[cursorTile.x][cursorTile.y] != null)
                if(room.units[cursorTile.x][cursorTile.y].isOwner(room.turn) && room.units[cursorTile.x][cursorTile.y].canAttack){
                    enabled = true;
                    activeUnit = room.units[cursorTile.x][cursorTile.y];
                    mac = new MActionChoose(activeUnit);
                    mac.setPosition(cursorTile, frame.getScreenPosition(), frame);
                    correctAccordingTo(room.units[cursorTile.x][cursorTile.y]);
                    position = new Point(cursorTile);
                }
    }
    
    @Override
    public void correctAccordingTo(UnitDescription ud)    {
        if(ud.canMove)
            mac.getButtons()[0].setImage(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmove.png").getImage());
        else mac.getButtons()[0].setImage(new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image"+ File.separator +"Menu"+ File.separator +"Buttons" + File.separator +"64x64xmoveN.png").getImage());
    }
    public void drawAction(Image iTemp, Room room, GameJFrame frame, Dimension scrPos){
        if(enabled)
            TRendering2D.DrawMenu(mac, frame, iTemp);
    }
    public static void macOn(Point cursorTile, Room room, GameJFrame frame, MouseEvent e){
        frame.action = new ChooseAction();
        frame.action.click(room, frame, cursorTile, e);
    }
}
