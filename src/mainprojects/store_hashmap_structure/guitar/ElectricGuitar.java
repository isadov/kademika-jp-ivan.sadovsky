package mainprojects.store_hashmap_structure.guitar;

public class ElectricGuitar extends Guitar {
	
	protected String bodyMaterial;
	protected String neckName;
	protected String bridgeName;	
	protected boolean isFloydRose;	

	
	public ElectricGuitar(GuitarBrand guitarBrand, String color, String model, String bodyMaterial,
						  FreatboardMaterial freatboardMaterial, boolean isFreatboardGlued, int numberOfStrings, int numberOfFrets,
						  String manufacturer, int price, String bridgeName, String neckName, boolean isFloydRose) {

		super(guitarBrand, color, model, freatboardMaterial, isFreatboardGlued, numberOfStrings, numberOfFrets, manufacturer, price);

		this.bodyMaterial = bodyMaterial;
		this.neckName = neckName;
		this.bridgeName = bridgeName;
		this.isFloydRose = isFloydRose;
		this.guitarType = GuitarType.ELECTRIC;

	}

	public String getBodyMaterial() {
		return bodyMaterial;
	}

	public String getNeckName() {
		return neckName;
	}
	
	public String getBridgeName() {
		return bridgeName;
	}	

	public boolean isFloydRose() {
		return isFloydRose;
	}
	
	
}
