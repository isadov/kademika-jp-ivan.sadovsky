package mainprojects.ooptanks.fieldsobject;

import mainprojects.ooptanks.serviceclass.Destroyable;
import mainprojects.ooptanks.serviceclass.LoadingImages;

import java.awt.*;

public class Brick extends BFObject implements Destroyable {

	public Brick() {
		this.color = new Color(250, 100, 100);

		image = LoadingImages.getBricks();
		
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
