package mainprojects.magazine.products;

public class Goods {
	protected String name;
	protected int price;
	protected int id;

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

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
