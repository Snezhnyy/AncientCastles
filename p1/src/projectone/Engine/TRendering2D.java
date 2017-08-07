/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import projectone.Engine.Menu.MMenuContainer;
import projectone.game.Room;

/**
 *
 * @author Snowdrop
 */
public class TRendering2D {
    
    public static Image DrawMap(Room room, Dimension screenPosition, GameJFrame frame) {
      /*  if(screenPosition.width  < 0 || screenPosition.height < 0)
            for(int x = 0;  x < room.map.ground.length; x++)
                for(int y = 0; y < room.map.ground[x].length; y++)
                    g.drawImage(frame.tiles[room.map.ground[x][y]].getTileImage(), 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
        else*/ 
      Image iTemp = frame.createImage(frame.getWidth(), frame.getHeight()); 
            for(int x = (screenPosition.width / 64) >= 0 ? screenPosition.width / 64: 0;  x < screenPosition.width / 64 + frame.getWidth() / 64 + 2 && x < room.map.ground.length; x++)
                for(int y = (screenPosition.height / 64) >= 0 ? screenPosition.height / 64: 0; y < screenPosition.height / 64 + frame.getHeight() / 64 + 2 && y < room.map.ground[x].length; y++)
                    iTemp.getGraphics().drawImage(frame.tiles[room.map.ground[x][y]].getTileImage(), 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
      
      return iTemp;
        }
    public static Image DrawUnit(Room room, Dimension screenPosition, GameJFrame frame, Image iTemp) {
      /*  if(screenPosition.width  < 0 || screenPosition.height < 0)
            for(int x = 0;  x < room.map.ground.length; x++)
                for(int y = 0; y < room.map.ground[x].length; y++)
                    g.drawImage(frame.tiles[room.map.ground[x][y]].getTileImage(), 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
        else*/ 
              iTemp.getGraphics().setFont(new java.awt.Font("Tahoma", 1, 18));
            for(int x = (screenPosition.width / 64) >= 0 ? screenPosition.width / 64: 0;  x < screenPosition.width / 64 + frame.getWidth() / 64 + 2 && x < room.map.ground.length; x++)
                for(int y = (screenPosition.height / 64) >= 0 ? screenPosition.height / 64: 0; y < screenPosition.height / 64 + frame.getHeight() / 64 + 2 && y < room.map.ground[x].length; y++)
                    if(room.units[x][y] != null) {
                        iTemp.getGraphics().drawImage(frame.units[room.units[x][y].getImageId()].getImage(), 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
                        iTemp.getGraphics().drawString(String.valueOf(room.units[x][y].getHP()), 64 * x - screenPosition.width + 4, 64 * y - screenPosition.height + 58);
                        if(!room.units[x][y].canAttack) 
                            iTemp.getGraphics().drawImage(frame.iUnitEndTurn, 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
                    }
      
      return iTemp;
        }
    public static Image DrawCursor(Dimension screenPosition, GameJFrame frame, Image iTemp, Point cursorTile, Image cursor) {
        iTemp.getGraphics().drawImage(cursor, cursorTile.x * 64 - screenPosition.width, cursorTile.y * 64 - screenPosition.height, frame);
        return iTemp;
    }
    public static Image DrawMenu(MMenuContainer mmc, JFrame frame, Image iTemp){
        iTemp.getGraphics().drawImage(mmc.getMenu().getImage(), mmc.getX(), mmc.getY(), frame);
        for(int i = 0; i < mmc.getMenu().getButtons().length; i++)
            iTemp.getGraphics().drawImage(mmc.getMenu().getButtons()[i].getImage(), mmc.getMenu().getButtons()[i].getX() + mmc.getX(), mmc.getMenu().getButtons()[i].getY() + mmc.getY(), frame);
        return iTemp;
    }
    public static Image DrawGraves(Room room, Dimension screenPosition, GameJFrame frame, Image iTemp){
        iTemp.getGraphics().setFont(new java.awt.Font("Tahoma", 1, 18));
            for(int x = (screenPosition.width / 64) >= 0 ? screenPosition.width / 64: 0;  x < screenPosition.width / 64 + frame.getWidth() / 64 + 2 && x < room.map.ground.length; x++)
                for(int y = (screenPosition.height / 64) >= 0 ? screenPosition.height / 64: 0; y < screenPosition.height / 64 + frame.getHeight() / 64 + 2 && y < room.map.ground[x].length; y++)
                    if(room.graves[x][y] != null) {
                        iTemp.getGraphics().drawImage(frame.grave, 64*x - screenPosition.width, 64*y - screenPosition.height, frame);
                    }
      return iTemp;
    }
}
