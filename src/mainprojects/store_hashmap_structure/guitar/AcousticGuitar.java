package mainprojects.store_hashmap_structure.guitar;

public class AcousticGuitar extends Guitar {

	private boolean stringsIsNylon;

	public AcousticGuitar(GuitarBrand guitarBrand, String color, String model,
						  FreatboardMaterial freatboardMaterial, boolean isFreatboardGlued,
						  int numberOfStrings, int numberOfFrets, String manufacturer,
						  int price, boolean stringsIsNylon) {

		super(guitarBrand, color, model, freatboardMaterial, isFreatboardGlued,
				numberOfStrings, numberOfFrets, manufacturer, price);			

		this.stringsIsNylon = stringsIsNylon;
		this.guitarType = GuitarType.ACOUSTIC;

	}

	public boolean isStringsIsNylon() {
		return stringsIsNylon;
	}

	
}
