package projectone.game;

import java.io.Serializable;

public class TileDescription implements Serializable {		//05.07.2017
	byte latency;		//
	byte defence;		//
	boolean isBuilding;		//
	boolean isHealing;		//
	boolean isWater;		//

	public TileDescription() {
		latency = 1;		
		defence = 0;		
		isBuilding = false;		
		isHealing = false;		
		isWater = false;		
	}
	public TileDescription(String description) {
		String[] strArr = description.split(" ");	
		latency = Byte.valueOf(strArr[1]);
		defence = Byte.valueOf(strArr[2]);
		isBuilding = Integer.valueOf(strArr[3]) == 1? true: false;
		isHealing = Integer.valueOf(strArr[4]) == 1? true: false;
		isWater = Integer.valueOf(strArr[5]) == 1? true: false;
	}
	
                      public boolean isBuilding() {
                          return isBuilding;
                      }
                      public boolean isWater(){
                          return isWater;
                      }
                      public int getLatency(){
                          return latency;
                      }
	public String toString() {
		return String.format("latency = " + latency + ", defence = " + defence + "." + (isBuilding? " Building." : null) + (isHealing? " Healing.": null) + (isWater? " Water.": null));	
	}
}