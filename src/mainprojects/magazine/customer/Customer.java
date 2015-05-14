package mainprojects.magazine.customer;

public class Customer {

	private String name;
	private String email;
	
	public Customer() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
