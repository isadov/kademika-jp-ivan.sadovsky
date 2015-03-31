package mainprojects.ooptanks.serviceclass;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoadingImages {

	private static Image bricks;
	private static Image eagle;
	private static Image earth;
	private static Image rock;
	private static Image water;

	public LoadingImages() {
		try {
			bricks = ImageIO.read(new File("tanks_image/Blocks.jpg").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			eagle = ImageIO.read(new File("tanks_image/GoldenStar.png").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			earth = ImageIO.read(new File("tanks_image/GreenGrass.png").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			rock = ImageIO.read(new File("tanks_image/Stones.jpg").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			water = ImageIO.read(new File("tanks_image/Water.png").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Image getBricks() {
		return bricks;
	}

	public static Image getEagle() {
		return eagle;
	}

	public static Image getEarth() {
		return earth;
	}

	public static Image getRock() {
		return rock;
	}

	public static Image getWater() {
		return water;
	}

}
