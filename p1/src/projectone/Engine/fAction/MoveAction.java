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
public class MoveAction extends GeneralAction {
    Image moveTile, imoveDot, ifinish;
    UnitDescription activeUnit;
    Point position;
    boolean selected;
    int[][] moveMap;
    
    public MoveAction(){
        selected = true;
        moveTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveGP.png").getImage();
        imoveDot = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveDot.png").getImage();
        ifinish = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveFDot.png").getImage();
        moveMap = null;
    }
    public MoveAction(Room room, GameJFrame frame, Point cursorTile, UnitDescription ud, Point pos) {
        selected = true;
        moveTile = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveGP.png").getImage();
        imoveDot = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveDot.png").getImage();
        ifinish = new ImageIcon("src" + File.separator + "projectone"+File.separator+"Image"+File.separator+"Cursor"+File.separator+"moveFDot.png").getImage();
        activeUnit = ud;
        position = new Point(pos);
        moveMap = new int[room.units.length][room.units[0].length]; //initialization of array
        for(int x = 0; x < moveMap.length; x++)             //filling the array by negative values
            for(int y = 0; y < moveMap[0].length; y++)
                moveMap[x][y] = -1;
        moveMap[position.x][position.y] = 0;            //setting up the start point
        int speed = ud.getSpeed();  //load unit speed
        for(int i = 1; i <= speed; i++)                                 //create move map
            for(int x = 0; x < moveMap.length; x++)
                for(int y = 0; y < moveMap[0].length; y++)
                    if(ud.isWaterElemental) Perks.tracingHelperMoveWaterElemental(speed, x, y, room, frame, moveMap, activeUnit);
                    else if(ud.isDragon) Perks.tracingHelperDragonFly(speed, x, y, moveMap);
                         else tracingHelperMove(speed, x, y, room, frame); 
        }

    @Override
    public void click(Room room, GameJFrame frame, Point cursorTile, MouseEvent e) {
        if(selected){
            moveAction(cursorTile, room);
           if(!activeUnit.canMove) {
               macOn(cursorTile, room, frame, e);
               if(activeUnit.isWaterElemental) {
                   Perks.waterElementalBoostClean(frame, room, activeUnit, cursorTile);
                   Perks.waterElementalBoost(frame, room, activeUnit, cursorTile);
               }
           }
           if(moveMap[cursorTile.x][cursorTile.y] < 1 || room.units[cursorTile.x][cursorTile.y].getOwner() == activeUnit.getOwner()) macOn(cursorTile, room, frame, e);
        }
    }
    
    private void tracingHelperMove(int speed, int x, int y, Room room, GameJFrame frame) {
        if(moveMap[x][y] > -1){
            if(x + 1 < moveMap.length)
                if(room.units[x + 1][y] == null || room.units[x + 1][y].getOwner() == activeUnit.getOwner())
                if(moveMap[x + 1][y] == -1 && moveMap[x][y] + frame.tiles[room.map.ground[x + 1][y]].property.getLatency() <= speed || moveMap[x][y] + frame.tiles[room.map.ground[x + 1][y]].property.getLatency() <= moveMap[x + 1][y])
                    moveMap[x + 1][y] = moveMap[x][y] + frame.tiles[room.map.ground[x + 1][y]].property.getLatency();
            if(x - 1 >= 0)
                if(room.units[x - 1][y] == null || room.units[x - 1][y].getOwner() == activeUnit.getOwner())
                if(moveMap[x - 1][y] == -1 && moveMap[x][y] + frame.tiles[room.map.ground[x - 1][y]].property.getLatency() <= speed || moveMap[x][y] + frame.tiles[room.map.ground[x - 1][y]].property.getLatency() <= moveMap[x - 1][y])
                    moveMap[x - 1][y] = moveMap[x][y] + frame.tiles[room.map.ground[x - 1][y]].property.getLatency();
            if(y + 1 < moveMap[0].length)
                if(room.units[x][y + 1] == null || room.units[x][y + 1].getOwner() == activeUnit.getOwner())
                if(moveMap[x][y + 1] == -1 && moveMap[x][y] + frame.tiles[room.map.ground[x][y + 1]].property.getLatency() <= speed || moveMap[x][y] + frame.tiles[room.map.ground[x][y + 1]].property.getLatency() <= moveMap[x][y + 1])
                    moveMap[x][y + 1] = moveMap[x][y] + frame.tiles[room.map.ground[x][y + 1]].property.getLatency();
            if(y - 1 >= 0)
                if(room.units[x][y - 1] == null || room.units[x][y - 1].getOwner() == activeUnit.getOwner())
                if(moveMap[x][y - 1] == -1 && moveMap[x][y] + frame.tiles[room.map.ground[x][y - 1]].property.getLatency() <= speed || moveMap[x][y] + frame.tiles[room.map.ground[x][y - 1]].property.getLatency() <= moveMap[x][y - 1])
                    moveMap[x][y - 1] = moveMap[x][y] + frame.tiles[room.map.ground[x][y - 1]].property.getLatency();
        }
    }
    
    private void moveAction(Point cursorTile, Room room) {
        if(moveMap[cursorTile.x][cursorTile.y] > 0 && activeUnit.canMove && room.units[cursorTile.x][cursorTile.y] == null)
        {
            activeUnit.canMove = false;
            if(activeUnit.isCatapult) activeUnit.canAttack = false;
            room.units[position.x][position.y] = null;
            room.units[cursorTile.x][cursorTile.y] = activeUnit;            
        }
    }    
    @Override
    public void drawAction(Image iTemp, Room room, GameJFrame frame, Dimension scrPos) {
            for(int x = 0; x < moveMap.length; x++)
                for(int y = 0; y < moveMap[0].length; y++)
                    if(moveMap[x][y] > 0 ) iTemp.getGraphics().drawImage(moveTile, x * 64 - scrPos.width, y * 64 - scrPos.height, frame);
            if(moveMap[frame.getCursorTile().x][frame.getCursorTile().y] > 0 && room.units[frame.getCursorTile().x][frame.getCursorTile().y] == null){
              Point dot = new Point(frame.getCursorTile());  
              iTemp.getGraphics().drawImage(ifinish, dot.x * 64 - scrPos.width, dot.y * 64 - scrPos.height, frame);
              while(moveMap[dot.x][dot.y] > 0){
                  Point temp = new Point(dot);
                  if(dot.x + 1 < moveMap.length)
                    if(moveMap[dot.x][dot.y] > moveMap[dot.x + 1][dot.y] && moveMap[dot.x + 1][dot.y] > -1) { 
                      if(moveMap[temp.x][temp.y] > moveMap[dot.x + 1][dot.y])
                          temp = new Point(dot.x + 1, dot.y);
                  } 
                  if(dot.x - 1 > 0)
                    if(moveMap[dot.x][dot.y] > moveMap[dot.x - 1][dot.y] && moveMap[dot.x - 1][dot.y] > -1) {
                      if(moveMap[temp.x][temp.y] > moveMap[dot.x - 1][dot.y])
                          temp = new Point(dot.x - 1, dot.y);
                  } 
                  if(dot.y + 1 < moveMap[0].length)
                    if(moveMap[dot.x][dot.y] > moveMap[dot.x][dot.y + 1] && moveMap[dot.x][dot.y + 1] > -1) {
                      if(moveMap[temp.x][temp.y] > moveMap[dot.x][dot.y + 1])
                          temp = new Point(dot.x, dot.y + 1);
                  } 
                  if(dot.y - 1 > 0)
                    if(moveMap[dot.x][dot.y] > moveMap[dot.x][dot.y - 1] && moveMap[dot.x][dot.y - 1] > -1) {
                      if(moveMap[temp.x][temp.y] > moveMap[dot.x][dot.y - 1])
                          temp = new Point(dot.x, dot.y - 1);
                  }
                  dot = new Point(temp);
                  if(moveMap[dot.x][dot.y] > 0) 
                          iTemp.getGraphics().drawImage(imoveDot, dot.x * 64 - scrPos.width, dot.y * 64 - scrPos.height, frame);
              }
            }
        }
    
    public static void macOn(Point cursorTile, Room room, GameJFrame frame, MouseEvent e){
        frame.action = new ChooseAction();
        frame.action.click(room, frame, cursorTile, e);
    }
}
