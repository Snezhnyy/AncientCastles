/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.game;

import java.awt.Point;
import java.io.Serializable;
import projectone.Engine.GameJFrame;
import projectone.Engine.Turn;

/**
 *
 * @author Snowdrop
 */
public class UnitDescription implements Serializable{
    public int hp;
    public int maxHp;
    public int maxHpBonus;
    int speed;
    public int speedBonus;
    int defence;
    public int defenceBonus;
    int attack;
    public int attackBonus;
    int attackRange;
    int exp;
    public int attackRangeBonus;
    public int imageId;
    public int owner;
    public int wolfDamage;
    public boolean canMove, canAttack, isWaterElemental, isRess, isHeal, isWolf, isGolem, isCatapult, isDragon, shield;
    
    public UnitDescription(){
        hp = 100;
        maxHp = 100;
        speed = 4;
        defence = 10;
        attack = 40;
        attackRange = 1;
        imageId = 0;
        owner = -1;
        exp = 0;
        canMove = true;
        canAttack = true;
        isWaterElemental = false;
        isRess = false;
        isHeal = false;
        isWolf = false;
        isGolem = false;
        isCatapult = false;
        isDragon = false;
        shield = false;
        wolfDamage = 0;
    }
    public UnitDescription(UnitDescription u){
        hp = u.hp;
        maxHp = u.maxHp;
        maxHpBonus = u.maxHpBonus;
        speed = u.speed;
        speedBonus = u.speedBonus;
        defence = u.defence;
        defenceBonus = u.defenceBonus;
        attack = u.attack;
        attackBonus = u.attackBonus;
        attackRange = u.attackRange;
        attackRangeBonus = u.attackRangeBonus;
        imageId = u.imageId;
        owner = u.owner;
        exp = u.exp;
        canMove = u.canMove;
        canAttack = u.canAttack;
        isWaterElemental = u.isWaterElemental;
        isRess = u.isRess;
        isHeal = u.isHeal;
        isWolf = u.isWolf;
        isGolem = u.isGolem;
        isCatapult = u.isCatapult;
        isDragon = u.isDragon;
        shield = u.shield;
        wolfDamage = u.wolfDamage;
    }
    public UnitDescription(String description){
        String[] strArr = description.split(" ");
        imageId = 0;
        wolfDamage = 0;
        hp = Integer.valueOf(strArr[1]);
        maxHp = Integer.valueOf(strArr[1]);
        speed = Integer.valueOf(strArr[2]);
        defence = Integer.valueOf(strArr[3]);
        attack = Integer.valueOf(strArr[4]);
        attackRange = Integer.valueOf(strArr[5]);
        owner = Integer.valueOf(strArr[6]);
        canMove = true;
        canAttack = true;
        isWaterElemental = false;
        if(strArr.length > 7)
            for(int i = 7; i < strArr.length; i++)
                switch(strArr[i]) {
                    case "wE": isWaterElemental = true;
                        break;
                    case "ress": isRess = true;
                        break;
                    case "heal": isHeal = true;
                        break;
                    case "wolf": isWolf = true;
                        break;
                    case "golem": isGolem = true;
                        break;
                    case "catapult": isCatapult = true;
                        break;
                    case "dragon": isDragon = true;
                        break;
                    case "shield": shield = true;
                        break;
                    default :break;
                }
    }
    
    public void battle(GameJFrame frame, Room room, UnitDescription[][] uMap, Point attacker, Point defender) {
        attack(uMap[defender.x][defender.y], frame.tiles[room.map.ground[defender.x][defender.y]].property.defence);
        if(attacker.x == defender.x || attacker.y == defender.y)
            if(attacker.x + 1 == defender.x || attacker.x - 1 == defender.x || attacker.y - 1 == defender.y || attacker.y + 1 == defender.y)
                uMap[defender.x][defender.y].attack(this, frame.tiles[room.map.ground[attacker.x][attacker.y]].property.defence);
    }
    private void attack(UnitDescription u2, int defTD){
        if(u2.getCDefence() + defTD < getCAttack()) {
            u2.loseHP(getCAttack() - u2.getCDefence() - defTD);
            exp += getCAttack() - u2.getCDefence() - defTD;
        }
    }
    public void loseHP(int damage){
        hp -= damage;
    }
    public void heal(int health){
        if(hp < maxHp + maxHpBonus - wolfDamage) {
            hp += health;
            if(hp > maxHp + maxHpBonus - wolfDamage) hp = maxHp + maxHpBonus - wolfDamage;
        }
    }
    
    public void setImageId(int id) {
        imageId = id;
    }
    
    public int getCAttack(){
        return (attack + attackBonus) * (50 + hp * 50 / maxHp) / 100 + exp / attack;
    }
    public void setDefence(int def){
        defence = def;
    }
    public int getCDefence(){
        return (defence + defenceBonus) * (50 + hp * 50 / maxHp)  / 100 + exp / attack;
    }
    public int getAttackRange() {
        return attackRange + attackRangeBonus;
    }
    public int getImageId() {
        return imageId;
    }
    public int getHP(){
        return hp;
    }
    public int getOwner(){
        return owner;
    }
    public int getSpeed(){
        return speed + speedBonus;
    }
    
    public boolean isOwner(Turn t){
        return t.getArmy() == owner ? true:false;
    }
    public boolean isDead(){
        if( hp < 1) return true;
        return false;
    }
}
