/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.game;

import java.awt.Point;
import projectone.Engine.GameJFrame;

/**
 *
 * @author Snowdrop
 */
public class Perks {
    public static void waterElemantalWater(GameJFrame frame, Room room, UnitDescription activeUnit, Point position){
        room.map.ground[position.x][position.y] = 25;
        activeUnit.canAttack = false;
        Perks.waterElementalBoost(frame, room, activeUnit, position);
        activeUnit.hp -= 20;
        if(room.units[position.x][position.y].hp < 1){
            room.graves[position.x][position.y] = room.units[position.x][position.y];
            room.units[position.x][position.y] = null;
        }
    }
    public static void waterElementalBoost(GameJFrame frame, Room room, UnitDescription activeUnit, Point position){
        if(frame.tiles[room.map.ground[position.x][position.y]].property.isWater) {
            activeUnit.attackRangeBonus = 1;
            activeUnit.defenceBonus = 5;
            activeUnit.maxHpBonus = 100;
        }
    }
    public static void waterElementalBoostClean(GameJFrame frame, Room room, UnitDescription activeUnit, Point position){
        if(!frame.tiles[room.map.ground[position.x][position.y]].property.isWater()) {
            activeUnit.attackRangeBonus = 0;
            activeUnit.defenceBonus = 0;
            activeUnit.maxHpBonus = 0;
        }
    }
    public static void waterElementalStartTurn(GameJFrame frame, Room room, UnitDescription activeUnit, Point position){
        if(frame.tiles[room.map.ground[position.x][position.y]].property.isWater) {
            Perks.waterElementalBoost(frame, room, activeUnit, position);
            activeUnit.heal(20);
        }
        else {
            activeUnit.loseHP(10);
            if(activeUnit.hp < 1) {
                room.graves[position.x][position.y] = room.units[position.x][position.y];
                room.units[position.x][position.y] = null;
            }
        }
    }
    public static void tracingHelperMoveWaterElemental(int speed, int x, int y, Room room, GameJFrame frame, int[][] moveMap, UnitDescription activeUnit) {
        if(moveMap[x][y] > -1){
            if(x + 1 < moveMap.length) {
                int latency = 0;
                if(frame.tiles[room.map.ground[x + 1][y]].property.isWater) latency = 1;
                else latency = frame.tiles[room.map.ground[x + 1][y]].property.getLatency();
                if(room.units[x + 1][y] == null || room.units[x + 1][y].getOwner() == activeUnit.getOwner())
                if(moveMap[x + 1][y] == -1 && moveMap[x][y] + latency <= speed || moveMap[x][y] + latency <= moveMap[x + 1][y])
                    moveMap[x + 1][y] = moveMap[x][y] + latency;
            }
            if(x - 1 >= 0){
                int latency = 0;
                if(frame.tiles[room.map.ground[x - 1][y]].property.isWater) latency = 1;
                else latency = frame.tiles[room.map.ground[x - 1][y]].property.getLatency();
                if(room.units[x - 1][y] == null || room.units[x - 1][y].getOwner() == activeUnit.getOwner())
                if(moveMap[x - 1][y] == -1 && moveMap[x][y] + latency <= speed || moveMap[x][y] + latency <= moveMap[x - 1][y])
                    moveMap[x - 1][y] = moveMap[x][y] + latency;
            }
            if(y + 1 < moveMap[0].length){
              int latency = 0;
                if(frame.tiles[room.map.ground[x][y + 1]].property.isWater) latency = 1;
                else latency = frame.tiles[room.map.ground[x][y + 1]].property.getLatency();
                if(room.units[x][y + 1] == null || room.units[x][y + 1].getOwner() == activeUnit.getOwner())
                if(moveMap[x][y + 1] == -1 && moveMap[x][y] + latency <= speed || moveMap[x][y] + latency <= moveMap[x][y + 1])
                    moveMap[x][y + 1] = moveMap[x][y] + latency;  
            }
            if(y - 1 >= 0){
              int latency = 0;
                if(frame.tiles[room.map.ground[x][y - 1]].property.isWater) latency = 1;
                else latency = frame.tiles[room.map.ground[x][y - 1]].property.getLatency();
                if(room.units[x][y - 1] == null || room.units[x][y - 1].getOwner() == activeUnit.getOwner())
                if(moveMap[x][y - 1] == -1 && moveMap[x][y] + latency <= speed || moveMap[x][y] + latency <= moveMap[x][y - 1])
                    moveMap[x][y - 1] = moveMap[x][y] + latency;  
            }
        }
    }
    
    public static void healOnField(GameJFrame frame, Room room, UnitDescription activeUnit, Point position){
        int[][] healMap = Perks.tracingHeal(room, activeUnit, position);
        for(int x = 0; x < healMap.length; x++)
                for(int y = 0; y < healMap[0].length; y++)
                    if(healMap[x][y] > -1)
                        if(room.units[x][y] != null) {
                        if(room.units[x][y].imageId == 9 || room.units[x][y].imageId == 10){
                            room.units[x][y].loseHP(30);
                            if(room.units[x][y].hp < 1) room.units[x][y] = null;
                            continue;
                        }
                        if(room.units[x][y].owner == activeUnit.owner) room.units[x][y].heal(15);
                        else {
                            room.units[x][y].loseHP(10);
                            if(room.units[x][y].hp < 1) room.units[x][y] = null;
                    }
        }
        activeUnit.canAttack = false;
    }
    private static int[][] tracingHeal(Room room, UnitDescription activeUnit, Point position){
        int[][] attackMap = new int[room.units.length][room.units[0].length];
        int cRange = activeUnit.getAttackRange();
        for(int x = 0; x < attackMap.length; x++)
            for(int y = 0; y < attackMap[0].length; y++)
                attackMap[x][y] = -1;
        attackMap[position.x][position.y] = 0;
        for(int range = 1; range <= cRange; range++)
            for(int x = 0; x < attackMap.length; x++)
                for(int y = 0; y < attackMap[0].length; y++)
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
        return attackMap;
    }
    
    public static void wolfInBattle(GameJFrame frame, Room room, UnitDescription[][] uMap, Point attacker, Point defender) {
        int heal = uMap[attacker.x][attacker.y].getCAttack() > uMap[defender.x][defender.y].getCDefence()? uMap[attacker.x][attacker.y].getCAttack() - uMap[defender.x][defender.y].getCDefence(): 0;
        uMap[attacker.x][attacker.y].battle(frame, room, uMap, attacker, defender);
        uMap[attacker.x][attacker.y].heal(heal);
        uMap[defender.x][defender.y].wolfDamage += heal;
        uMap[attacker.x][attacker.y].canAttack = false;
    }
}
