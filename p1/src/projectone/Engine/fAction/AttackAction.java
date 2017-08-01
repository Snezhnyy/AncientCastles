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
import projectone.game.Perks;
import projectone.game.Room;
import projectone.game.UnitDescription;

/**
 *
 * @author Snowdrop
 */
public class AttackAction extends GeneralAction{
    Image attackTile;
    UnitDescription activeUnit;
    Point position;
    boolean selected;
    int[][] attackMap;
    
    public AttackAction(){
        selected = true;
        attackTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"attackGP.png").getImage();
        attackMap = null;
    }
    public AttackAction(Room room, GameJFrame frame, Point cursorTile, UnitDescription ud, Point pos) {
        selected = true;
        attackTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"attackGP.png").getImage();
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
            attackAction(frame, cursorTile, room);
           if(!activeUnit.canAttack) {
               macOn(cursorTile, room, frame, e);
               if(room.units[position.x][position.y].getHP() < 1 ) {
                   if(room.units[position.x][position.y].imageId != 18 && room.units[position.x][position.y].imageId != 17 && room.units[position.x][position.y].imageId != 9 &&room.units[position.x][position.y].imageId != 10 && !room.units[position.x][position.y].isHeal)
                            room.graves[position.x][position.y] = room.units[position.x][position.y];
                   room.units[position.x][position.y] = null;
               }
               if(room.units[cursorTile.x][cursorTile.y].getHP() < 1) {
                   if(room.units[cursorTile.x][cursorTile.y].imageId != 18 && room.units[cursorTile.x][cursorTile.y].imageId != 17 && room.units[cursorTile.x][cursorTile.y].imageId != 9 && room.units[cursorTile.x][cursorTile.y].imageId != 10 && !room.units[cursorTile.x][cursorTile.y].isHeal)
                            room.graves[cursorTile.x][cursorTile.y] = room.units[cursorTile.x][cursorTile.y];
                   room.units[cursorTile.x][cursorTile.y] = null;
               }
           }
           if(attackMap[cursorTile.x][cursorTile.y] < 1 || room.units[cursorTile.x][cursorTile.y] != null) {
               macOn(cursorTile, room, frame, e);
            }
        }
    }
    
    private void attackAction(GameJFrame frame, Point cursorTile, Room room) {
        if(attackMap[cursorTile.x][cursorTile.y] > 0)
            if(room.units[cursorTile.x][cursorTile.y] != null && activeUnit.canAttack)
                if(room.units[cursorTile.x][cursorTile.y].getOwner() != activeUnit.getOwner()) {
                    if(activeUnit.isWolf) Perks.wolfInBattle(frame, room, room.units, position, cursorTile);
                    else {
                        activeUnit.battle(frame, room, room.units, position, cursorTile);
                        activeUnit.canAttack = false;
                    }
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
                    if(attackMap[x][y] > 0) iTemp.getGraphics().drawImage(attackTile, x * 64 - scrPos.width, y * 64 - scrPos.height, frame);
        }
    
    public static void macOn(Point cursorTile, Room room, GameJFrame frame, MouseEvent e){
        frame.action = new ChooseAction();
        frame.action.click(room, frame, cursorTile, e);
    }
}
