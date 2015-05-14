package mainprojects.magazine.products;

public class Goods<T> {
	protected String name;
	protected int price;
	protected T id;

	public Goods() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}


}
