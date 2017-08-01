package projectone.game;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectone.Engine.MapCreator.MapCreatorJFrame;
import projectone.Engine.Turn;

public class Room implements Serializable {		//05.07.2017
	public Map map;
                      public UnitDescription[][] units;
                      public UnitDescription[][] graves;
                      public UnitDescription activeUnit;
                      public Turn turn;
                      
                      public Room(){
                          units = new UnitDescription[1][1];
                          graves = new UnitDescription[1][1];
                          units[0][0] = null;
                          graves[0][0] = null;
                          map = new Map(new int[1][1]);
                          activeUnit = null;
                          turn = new Turn();
                      }                 
	public Room(Map map) {
		this.map = map;
                                            units = new UnitDescription[map.ground.length][map.ground[0].length];
                                            graves = new UnitDescription[map.ground.length][map.ground[0].length];
                                            for(int i = 0; i < units.length; i++)
                                                for(int j = 0; j < units[0].length; j++)
                                                {
                                                    units[i][j] = null;
                                                    graves[i][j] = null;
                                                }
                                            activeUnit = null;
                                            turn = new Turn();
	}
                      
           public void setActiveUnit(Point cursor){
               if(units[cursor.x][cursor.y] != null) activeUnit = units[cursor.x][cursor.y];
           }
        //<editor-fold defaultstate="collapsed" desc="save/load from file">
        public void saveRoom(String path) {
        File f = new File(path);
        try {
            FileOutputStream fw = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fw);
            oos.writeObject(this);
            oos.close(); fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MapCreatorJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public Room loadRoom(String path) {
        File f = new File(path);
        Room room = new Room();
        try {
            FileInputStream fw = new FileInputStream(f);
            ObjectInputStream oos = new ObjectInputStream(fw);
            room = (Room)oos.readObject();
            room.units = new UnitDescription[room.map.ground.length][room.map.ground[0].length];
            oos.close(); fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MapCreatorJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MapCreatorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return room;
        }
        
        public void saveMap(String path){
            try {
                File f = new File(path);
                File f2 = new File(path + "2");
                File f3 = new File(path + "g");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(f2));
                ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream(f3));
                oos.writeObject(map.ground);
                oos2.writeObject(units);
                oos3.writeObject(graves);
                oos.close();
                oos2.close();
                oos3.close();
                } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        public void loadMap(String path) {
            
            try {
                File f = new File(path);
                File f2 = new File(path + "2");
                File f3 = new File(path + "g");
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(f2));
                map = new Map((int[][])ois.readObject());
                try {
                    units = (UnitDescription[][])ois2.readObject();
                    ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(f3));
                    graves = (UnitDescription[][])ois3.readObject();
                    ois3.close();
                }
                catch(IOException e) {
                    units = new UnitDescription[map.ground.length][map.ground[0].length];
                    graves = new UnitDescription[map.ground.length][map.ground[0].length];
                    for(int i = 0; i < units.length; i++)
                      for(int j = 0; j < units[0].length; j++){
                        units[i][j] = null;
                        graves[i][j] = null;
                      }
                }
                ois.close();
                ois2.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        //</editor-fold>
    }