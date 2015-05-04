package mainprojects.store_hashmap_structure.customer;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private String name;
	
	public Customer(){
		name="Temporary customer";
	}

	public Customer(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
