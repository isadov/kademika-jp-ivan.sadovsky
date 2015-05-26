package java_programming_trainer_24_hour.lesson7.test_pay_increase_interface;

public class Employee implements Payable {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public boolean increasePay(int percent) {
        System.out.println("Increasing salary by " + percent + "%: " + name);
        return true;
    }
}
