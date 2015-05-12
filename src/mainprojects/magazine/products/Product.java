package mainprojects.magazine.products;

public class Product<T> extends Goods {

    private TypeOfProducts type;
    private T brand;

    public Product() {
    }

    public Product(String name, TypeOfProducts type, T brand, T price, T id) {

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


//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof Product) {
//			Product p = (Product) obj;
//			if (name != null && name.equals(p.name )) { // type != null && type.equals(p.type)
//				return true;
//			}
//		}
//		return false;
//	}


    @Override
    public boolean equals(Object comparedProduct) {
        if (comparedProduct instanceof Product) {
            Product product = (Product) comparedProduct;
            if (name.equals(product)) {
                return true;
            }
        }
        return false;
    }
}

