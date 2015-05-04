package mainprojects.store_hashmap_structure.guitar;

public class BassGuitar extends ElectricGuitar {

	public BassGuitar(GuitarBrand guitarBrand, String color, String model,
					  String bodyMaterial, FreatboardMaterial freatboardMaterial,
					  boolean isFreatboardGlued, int numberOfStrings, int numberOfFrets,
					  String manufacturer, int price, String bridgeName, String neckName,
					  boolean isFloydRose) {
		
		super(guitarBrand, color, model, bodyMaterial, freatboardMaterial,
				isFreatboardGlued, numberOfStrings, numberOfFrets, manufacturer, price,
				bridgeName, neckName, isFloydRose);
		this.isFloydRose = false;
		this.guitarType = GuitarType.BASS;
	}


}
