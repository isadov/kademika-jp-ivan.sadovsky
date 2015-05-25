package mainprojects.magazine.customer;

import java.util.LinkedList;

public class CustomerDataBase {

    private LinkedList<Customer> customerList;

    public CustomerDataBase() {
        customerList = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

}
