package mainprojects.ooptanks.fieldsobject;

import java.awt.Color;

import mainprojects.ooptanks.serviceclass.LoadingImages;

public class Earth extends BFObject {

	public Earth() {
		this.color = new Color(102, 255, 102);
		image = LoadingImages.getEarth();
	}

	public Earth(int x, int y) {
		this.color = new Color(102, 255, 102);
		this.x = x;
		this.y = y;
		image = LoadingImages.getEarth();
		}
	}
