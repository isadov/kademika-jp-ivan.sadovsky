package mainprojects.store_hashmap_structure.guitar;

import java.io.Serializable;

public abstract class Guitar implements Serializable{

    protected GuitarBrand guitarBrand;
    protected transient GuitarType guitarType;
    protected String model;
    protected transient String color;
    protected transient FreatboardMaterial freatboardMaterial;
    protected transient boolean isFreatboardGlued;
    protected transient int numberOfStrings;
    protected transient int numberOfFrets;
    protected transient String manufacturer;
    protected double price;

    public Guitar(GuitarBrand guitarBrand, String color, String model,
                  FreatboardMaterial freatboardMaterial, boolean isFreatboardGlued,
                  int numberOfStrings, int numberOfFrets, String manufacturer,
                  int price) {

        this.guitarBrand = guitarBrand;
        this.color = color;
        this.model = model;
        this.freatboardMaterial = freatboardMaterial;
        this.isFreatboardGlued = isFreatboardGlued;
        this.numberOfStrings = numberOfStrings;
        this.numberOfFrets = numberOfFrets;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public GuitarBrand getGuitarBrand() {
        return guitarBrand;
    }

    public GuitarType getGuitarType() {
        return guitarType;
    }

    public FreatboardMaterial getFreatboardMaterial() {
        return freatboardMaterial;
    }

    public boolean isFreatboardGlued() {
        return isFreatboardGlued;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public int getNumberOfFrets() {
        return numberOfFrets;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }


    @Override
    public boolean equals(Object comparedGuitar) {

        if(comparedGuitar instanceof Guitar){
            Guitar guitar = (Guitar) comparedGuitar;
            if (this.guitarBrand.equals(guitar.getGuitarBrand()) &&
                    this.guitarType.equals(guitar.getGuitarType()) &&
                    this.model.equals(guitar.getModel()) &&
                    this.color.equals(guitar.getColor()) &&
                    this.freatboardMaterial.equals(freatboardMaterial) &&
                    this.isFreatboardGlued == guitar.isFreatboardGlued() &&
                    this.numberOfStrings == guitar.getNumberOfStrings() &&
                    this.numberOfFrets == guitar.getNumberOfFrets() &&
                    this.manufacturer.equals(guitar.getManufacturer()) &&
                    this.price == guitar.price) {

                return true;

            }
        }

        return false;
    }

}
