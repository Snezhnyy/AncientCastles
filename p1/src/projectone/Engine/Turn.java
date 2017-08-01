/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine;

import java.awt.Point;
import java.io.Serializable;
import projectone.game.Perks;
import projectone.game.Room;
import projectone.game.UnitDescription;

/**
 *
 * @author Snowdrop
 */
public class Turn implements Serializable{
    int count;
    int[] army;
    
    public Turn(){
        count = 0;
        army = new int[2];
        for(int i = 0; i < army.length; i++)
            army[i] = i;
    }
    
    public void next(GameJFrame frame, Room room, Point position){
        if(count + 1 < army.length) count++;
        else count = 0;
        for(int x = 0; x < room.units.length; x++)
            for(int y = 0; y < room.units[0].length; y++) {
                if(room.units[x][y] != null){
                    room.units[x][y].canAttack = true;
                    room.units[x][y].canMove = true;
                    if(room.units[x][y].owner == army[count]){
                        this.BoostCancel(frame, room, x, y);
                        if(room.units[x][y].isWaterElemental) Perks.waterElementalStartTurn(frame, room, room.units[x][y], new Point(x, y));
                    }
                }
            }
    }   
    
    private void BoostCancel(GameJFrame frame, Room room,  int x, int y){
        room.units[x][y].attackBonus = 0;
        room.units[x][y].attackRangeBonus = 0;
        room.units[x][y].defenceBonus = 0;
        room.units[x][y].maxHpBonus = 0;
        room.units[x][y].speedBonus = 0;
    }
    
    public int getArmy(){
        return army[count];
    }
}
