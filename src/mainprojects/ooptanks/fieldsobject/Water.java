package mainprojects.ooptanks.fieldsobject;

import java.awt.Color;

import mainprojects.ooptanks.serviceclass.LoadingImages;

public class Water extends BFObject{

	 public Water(){
		 this.color = new Color(65,105,225);
		 image = LoadingImages.getWater();
	 }
}