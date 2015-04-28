package mainprojects.magazine2.products;

public abstract class Product {

    private String name;
    private ProductType type;
    private int size;
    private double price;
    private boolean isFresh;
    private CountryManufacturer countryManufacturer;

    public Product() {

    }

    public Product(ProductType type, int size, double price, boolean isFresh, CountryManufacturer countryManufacturer) {
        this.type = type;
        this.size = size;
        this.price = price;
        this.isFresh = isFresh;
        this.countryManufacturer = countryManufacturer;
        this.name = name;
    }


    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setSize(int size) {
        this.size = size;
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
                    this.size == product.getSize() &&
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