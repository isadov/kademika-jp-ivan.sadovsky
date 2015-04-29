package mainprojects.magazine2.products;

public class Product {

    private String name;
    private ProductType type;
    private int weight;
    private double price;
    private boolean isFresh;
    private CountryManufacturer countryManufacturer;

    public Product() {

    }

    public Product(String name, ProductType type, int weight, double price, boolean isFresh, CountryManufacturer countryManufacturer) {
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.isFresh = isFresh;
        this.countryManufacturer = countryManufacturer;
        this.name = name;
    }


    public int getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsFresh(boolean isFresh) {
        this.isFresh = isFresh;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public CountryManufacturer getCountryManufacturer() {
        return countryManufacturer;
    }

    public void setCountryManufacturer(CountryManufacturer countryManufacturer) {
        this.countryManufacturer = countryManufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object comparedProduct) {

        if (comparedProduct instanceof Product) {
            Product product = (Product) comparedProduct;
            if (this.type.equals(product.getType()) &&
                    this.weight == product.getWeight() &&
                    this.price == product.getPrice() &&
                    this.isFresh == product.isFresh() &&
                    this.name.equals(product.getName()) &&
                    this.countryManufacturer.equals(product.getCountryManufacturer())) {

                return true;
            }
        }
        return false;
    }
}