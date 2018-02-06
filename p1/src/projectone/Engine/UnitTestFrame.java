/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectone.Engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import projectone.Engine.fAction.ChooseAction;

/**
 *
 * @author Snowdrop
 */
public class UnitTestFrame extends GameJFrame {
    /**
     * Creates new form UnitTestFrame
     */
    public UnitTestFrame() {
        initComponents();
        room.loadMap("D:" + File.separator + File.separator + "1.p1map");
        game = true;
        action = new ChooseAction();
        if(room.units.length * 64 < this.getWidth()) this.setSize(room.units.length * 64 + 15, this.getHeight());
        if(room.units[0].length * 64 < this.getHeight()) this.setSize(this.getWidth(), room.units[0].length * 64 + 90);
    }

    public void paint(Graphics g){
        Image iTemp = TRendering2D.DrawMap(room, screenPosition, this);
        TRendering2D.DrawGraves(room, screenPosition, this, iTemp);
        TRendering2D.DrawUnit(room, screenPosition, this, iTemp);
        iTemp.getGraphics().drawImage(cursor, cursorTile.x * 64 - screenPosition.width, cursorTile.y * 64 - screenPosition.height, this);
        TRendering2D.DrawCursor(screenPosition, this, iTemp, cursorTile, cursor);
        action.drawAction(iTemp, room, this, screenPosition);
        g.drawImage(iTemp, 0, 0, this);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTurn = new java.awt.Label();
        labelHelp = new java.awt.Label();
        labelPlayer = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        labelTurn.setText("0");

        labelHelp.setText("Press any key to end your turn");

        labelPlayer.setText("Turn of player");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(labelPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelTurn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTurn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1)
            action.click(room, this, cursorTile, evt);
    }//GEN-LAST:event_formMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        room.turn.next(this, room, cursorTile);
        labelTurn.setText(String.valueOf(room.turn.getArmy()));
        action = new ChooseAction();
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label labelHelp;
    private java.awt.Label labelPlayer;
    private java.awt.Label labelTurn;
    // End of variables declaration//GEN-END:variables
}
