package java_programming_trainer_24_hour.lesson7.test_pay_increase_interface;

public class TestPayIncreaseInterface {

    public static void main(String[] args) {

        Payable workers[] = new Payable[3];
        workers[0] = new Employee("John");
        workers[1] = new Contractor("Mary");
        workers[2] = new Employee("Steve");

        for(Payable p : workers) {
            ((Payable)p).increasePay(30);
        }
    }
}
