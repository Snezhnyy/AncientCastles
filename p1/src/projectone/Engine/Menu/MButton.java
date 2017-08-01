/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.Menu;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Snowdrop
 */
public class MButton {
    Image image;
    Point position;
    Dimension size;
    
    public MButton(Image image, Point position, Dimension size) {
        this.image = image;
        this.position = position;
        this.size = size;
    }
    
    public boolean click(Point mClick) {
        if(mClick.x > position.x && mClick.x < position.x + size.width)
            if(mClick.y > position.y && mClick.y < position.y + size.height)
                return true;
        return false;
    }
    public Image getImage(){
        return image;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public int getX(){
        return position.x;
    }
    public int getY(){
        return position.y;
    }
}
