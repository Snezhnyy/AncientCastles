/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone;

import projectone.Engine.MapCreator.TestMenu.MenuTestFrame;

/**
 *
 * @author Snowdrop
 */
public class EnterancePoint {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new MenuTestFrame().setVisible(true); 
            }
        });
    }
}
