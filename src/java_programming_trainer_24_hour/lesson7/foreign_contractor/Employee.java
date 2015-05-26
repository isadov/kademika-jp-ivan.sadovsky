package java_programming_trainer_24_hour.lesson7.foreign_contractor;

public class Employee extends Person implements Payable {


    public Employee(String name) {
        super(name);
    }


    @Override
    public boolean increasePay(int percent) {
        System.out.println("Increasing salary by " + percent + "%. " + getName());
        return true;
    }
}
