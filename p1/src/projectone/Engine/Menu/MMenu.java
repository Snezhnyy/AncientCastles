/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.Menu;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Snowdrop
 */
public class MMenu {
    MButton[] buttons;
    Image image;
    Point position;
    Dimension size;
    
    public MMenu(MButton[] buttons, Image image, Point position, Dimension size){
        this.buttons = buttons;
        this.image = image;
        this.position = position;
        this.size = size;
    }
    
    public int click(MouseEvent e){
        for (int i = 0; i < buttons.length; i++)
            if(buttons[i].click(new Point(e.getX() - position.x, e.getY() - position.y))) return i;
        return -1;
    }
    public boolean clickOnMenu(MouseEvent e){
        if(e.getX() > getX() && e.getX() < getX() + getWidth())
            if(e.getY() > getY() && e.getY() < getY() + getHeight())
                return true;
        return false;
    }
    public MButton[] getButtons(){
        return buttons;
    }
    public Image getImage(){
        return image;
    }
    public int getX(){
        return position.x;
    }
    public int getY(){
        return position.y;
    }
    public int getWidth(){
        return size.width;
    }
    public int getHeight(){
        return size.height;
    }
}
