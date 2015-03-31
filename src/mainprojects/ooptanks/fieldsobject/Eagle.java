package mainprojects.ooptanks.fieldsobject;

import java.awt.Color;

import mainprojects.ooptanks.serviceclass.Destroyable;
import mainprojects.ooptanks.serviceclass.LoadingImages;

public class Eagle extends BFObject implements Destroyable{

	public Eagle(){
		this.color = new Color(255,255,255);
		image = LoadingImages.getEagle();
	}
	
	@Override
	public void destroy() throws Exception {
		
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
