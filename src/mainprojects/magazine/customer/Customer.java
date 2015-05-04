package mainprojects.magazine.customer;

public class Customer {

	private String name;
	
	public Customer() {
		name = "Default Customer";
	}

	public Customer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
