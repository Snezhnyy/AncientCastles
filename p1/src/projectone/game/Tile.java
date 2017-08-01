package projectone.game;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Tile implements Serializable{		//05.07.2017
	Image img;
	public TileDescription property;
	
	public Tile() {
	}
	public Tile(Image img, TileDescription property) {
		this.img = img;
		this.property = property;
	}
	public Tile(String str) {
		property = new TileDescription(str);			//
		String[] strArr = str.split(" ");
		img = new ImageIcon("src" + File.separator + "projectone" + File.separator + "Image" + File.separator + strArr[0] + ".png").getImage();			//
	}

	public Image getTileImage() {
		return img;
	}
	public String toString() {
		return property.toString();
	}
}