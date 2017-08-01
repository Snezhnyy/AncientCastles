/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine.Menu;

import java.awt.event.MouseEvent;


/**
 *
 * @author Snowdrop
 */
public abstract class MMenuContainer {
    protected MMenu[] menues;
    protected MMenu activeMenu;
    public boolean enabled;
    
    public void click(MouseEvent e) {
       
    }
    public boolean clickOnMenu(MouseEvent e){
        return activeMenu.clickOnMenu(e);
    }
    public MMenu getMenu(){
        return activeMenu;
    }
    public int getX(){
        return activeMenu.position.x;
    }
    public int getY(){
        return activeMenu.position.y;
    }
}
