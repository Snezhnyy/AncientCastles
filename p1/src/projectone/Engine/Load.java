package projectone.Engine;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import projectone.game.Tile;
import projectone.game.Unit;

public class Load {		//05.07.2017 
	public static Tile[] tiles() {
		try {
			File f = new File("src" + File.separator + "projectone" + File.separator + "Properties" + File.separator + "dt.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			int arrSize = Integer.valueOf(br.readLine());
			Tile[] tiles = new Tile[arrSize];
			for(int i = 0; i < arrSize;) 	//
                                                                               {
                                                                                String str = br.readLine();
				tiles[i] = new Tile(str);
                                i++;
                                                                                        if(tiles[i - 1].property.isBuilding())
                                                                                        {
                                                                                            String[] aStr = str.split(" ");
                                                                                            for (int j = 6; j < aStr.length; j++, i++)
                                                                                                tiles[i] = new Tile(aStr[j] + " " + aStr[1] + " " + aStr[2] + " " + aStr[3] + " " + aStr[4] + " " + aStr[5]);
                                                                                        }
                                                                                        
                        }
			fr.close();
			br.close();
			return tiles;
		} catch(IOException e) { System.out.println(e.toString()); }
		return null;		
	}
        public static Unit[] units(){
            try {
			File f = new File("src" + File.separator + "projectone" + File.separator + "Properties" + File.separator + "du.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			int arrSize = Integer.valueOf(br.readLine());
			Unit[] units = new Unit[arrSize];
			for(int i = 0; i < arrSize; i++) 	//
                                                                               {
                                                                                String str = br.readLine();
				units[i] = new Unit(str, i);                                                                                        
                        }
			fr.close();
			br.close();
                                                                System.out.println(arrSize);
			return units;
		} catch(IOException e) { System.out.println(e.toString()); }
            return null;
        }
}