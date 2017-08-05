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
import projectone.game.Room;
import projectone.game.UnitDescription;

/**
 *
 * @author Snowdrop
 */
public class StoneAction extends GeneralAction{
    Image magicTile;
    UnitDescription activeUnit;
    Point position;
    boolean selected;
    int[][] attackMap;
    
    public StoneAction(){
        selected = true;
        magicTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"magicGP.png").getImage();
        attackMap = null;
    }
    public StoneAction(Room room, GameJFrame frame, Point cursorTile, UnitDescription ud, Point pos) {
        selected = true;
        magicTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"magicGP.png").getImage();
        activeUnit = ud;
        position = new Point(pos);
        attackMap = new int[room.units.length][room.units[0].length]; //initialization of array
        for(int x = 0; x < attackMap.length; x++)             //filling the array by negative values
            for(int y = 0; y < attackMap[0].length; y++)
                attackMap[x][y] = -1;
        attackMap[position.x][position.y] = 0;            //setting up the start point
        int range = ud.getAttackRange();  //load unit speed
        for(int i = 1; i <= range; i++)                                 //create move map
            for(int x = 0; x < attackMap.length; x++)
                for(int y = 0; y < attackMap[0].length; y++)
                    tracingHelperAttack(range, x, y); 
        }

    public void click(Room room, GameJFrame frame, Point cursorTile, MouseEvent e) {
        if(selected){
            stoneAction(cursorTile, room, frame);
           if(!activeUnit.canAttack) macOn(cursorTile, room, frame, e);
           if(attackMap[cursorTile.x][cursorTile.y] < 1) 
               macOn(cursorTile, room, frame, e);
           else macOn(cursorTile, room, frame, e);
        }
    }
    private void stoneAction(Point cursorTile, Room room, GameJFrame frame) {
        if(attackMap[cursorTile.x][cursorTile.y] > 0 && room.units[cursorTile.x][cursorTile.y] == null){
            if(room.map.ground[cursorTile.x][cursorTile.y] == 25) {
                room.map.ground[cursorTile.x][cursorTile.y] = 1;
            }
            else {
                room.units[cursorTile.x][cursorTile.y] = new UnitDescription(frame.units[17 + activeUnit.owner].ud);
                room.units[cursorTile.x][cursorTile.y].canAttack = false;
            }
            activeUnit.canAttack = false;
        }
    }
    
    private void tracingHelperAttack(int range, int x, int y){
        if(attackMap[x][y] > -1 && attackMap[x][y] + 1 <= range){
            if(x + 1 < attackMap.length)
                if(attackMap[x + 1][y] == -1)
                    attackMap[x + 1][y] = attackMap[x][y] + 1;
            if(x - 1 >= 0)
                if(attackMap[x - 1][y] == -1)
                    attackMap[x - 1][y] = attackMap[x][y] + 1;
            if(y + 1 < attackMap[0].length)
                if(attackMap[x][y + 1] == -1)
                    attackMap[x][y + 1] = attackMap[x][y] + 1;
            if(y - 1 >= 0)
                if(attackMap[x][y - 1] == -1)
                    attackMap[x][y - 1] = attackMap[x][y] + 1;
        }
    }
        
    @Override
    public void drawAction(Image iTemp, Room room, GameJFrame frame, Dimension scrPos) {
            for(int x = 0; x < attackMap.length; x++)
                for(int y = 0; y < attackMap[0].length; y++)
                    if(attackMap[x][y] > 0) iTemp.getGraphics().drawImage(magicTile, x * 64 - scrPos.width, y * 64 - scrPos.height, frame);
        }
    
    public static void macOn(Point cursorTile, Room room, GameJFrame frame, MouseEvent e){
        frame.action = new ChooseAction();
        frame.action.click(room, frame, cursorTile, e);
    }
}

