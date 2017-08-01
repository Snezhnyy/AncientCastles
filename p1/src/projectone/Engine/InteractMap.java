/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import projectone.game.Room;
/**
 *
 * @author Snowdrop
 */
public class InteractMap {
    public static void setNewScreenPosition(Dimension cScreenP, Dimension mousePP, MouseEvent e){
        cScreenP.width = mousePP.width - e.getX() + cScreenP.width;
        cScreenP.height = mousePP.height - e.getY() + cScreenP.height;
    }
    public static void setCursorTilePosition(Point cursorTile, Dimension screenPosition, MouseEvent e) {
        cursorTile.x = (screenPosition.width + e.getX()) / 64;
        cursorTile.y = (screenPosition.height + e.getY()) / 64;
    }
    public static void setMousePosition(Dimension mousePosition, MouseEvent evt) {
        mousePosition.width = evt.getX(); mousePosition.height = evt.getY();
    }
    public static void lagsScreenFix(Dimension screenPosition, Room room, JFrame fr){
        screenPosition.width = screenPosition.width < 0? 0:screenPosition.width;
        screenPosition.width = screenPosition.width > room.map.ground.length * 64 - fr.getSize().width ? room.map.ground.length *64 -  fr.getSize().width:screenPosition.width;
        screenPosition.height = screenPosition.height < -32? -32:screenPosition.height;
        screenPosition.height = screenPosition.height > (room.map.ground[0].length * 64 -  fr.getSize().height) ? (room.map.ground[0].length * 64 -  fr.getSize().height):screenPosition.height;
    }
}
