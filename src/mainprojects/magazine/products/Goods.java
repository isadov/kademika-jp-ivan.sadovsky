package mainprojects.magazine.products;

public class Goods<T> {
	protected String name;
	protected T price;
	protected T id;

	public Goods() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getPrice() {
		return price;
	}

	public void setPrice(T price) {
		this.price = price;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}


}
