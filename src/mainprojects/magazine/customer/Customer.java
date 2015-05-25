package mainprojects.magazine.customer;

public class Customer {

	private String name;
	private String nickname;
	
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

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
