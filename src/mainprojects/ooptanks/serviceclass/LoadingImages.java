package mainprojects.ooptanks.serviceclass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadingImages {

	private static BufferedImage bricks;
	private static BufferedImage eagle;
	private static BufferedImage earth;
	private static BufferedImage rock;
	private static BufferedImage water;

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

	public static BufferedImage getBricks() {
		return bricks;
	}

	public static BufferedImage getEagle() {
		return eagle;
	}

	public static BufferedImage getEarth() {
		return earth;
	}

	public static BufferedImage getRock() {
		return rock;
	}

	public static BufferedImage getWater() {
		return water;
	}

}
