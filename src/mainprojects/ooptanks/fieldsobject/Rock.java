package mainprojects.ooptanks.fieldsobject;

import java.awt.Color;

import mainprojects.ooptanks.serviceclass.Destroyable;
import mainprojects.ooptanks.serviceclass.LoadingImages;

public class Rock extends BFObject implements Destroyable{

	boolean tigerCanDestroy = true; 
	
	public Rock(){
		this.color = new Color(128,128,128);
		image = LoadingImages.getRock();
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
