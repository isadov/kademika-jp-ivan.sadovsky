package mainprojects.magazine.products;

public class Product<T> extends Goods {

    private TypeOfProducts type;
    private T brand;

    public Product() {
    }

    public Product(String name, TypeOfProducts type, T brand, int price, T id) {

        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfProducts getType() {
        return type;
    }

    public void setType(TypeOfProducts type) {
        this.type = type;
    }

    public T getBrand() {
        return brand;
    }

    public void setBrand(T brand) {
        this.brand = brand;
    }


    @Override
    public boolean equals(Object comparedProduct) {
        if (comparedProduct instanceof Product) {
            Product product = (Product) comparedProduct;
            if (name != null && name.equals(product)) {
                return true;
            }
        }
        return false;
    }
}

