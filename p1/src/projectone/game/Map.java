package projectone.game;

import java.io.Serializable;

public class Map implements Serializable {		//05.07.2017
	public int[][] ground;
	
	public Map(int[][] ground) {
		this.ground = ground;
	}
                    
                    public int[][] getLatencyMap(Tile[] tiles){
                        int[][] lm = ground;
                        for(int i = 0; i < lm.length; i++)
                            for(int j = 0; j < lm[0].length; j++)
                                lm[i][j] = tiles[ground[i][j]].property.latency;
                        return lm;
                    }
}