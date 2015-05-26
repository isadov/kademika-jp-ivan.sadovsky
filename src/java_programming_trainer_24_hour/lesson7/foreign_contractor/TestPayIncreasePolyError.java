package java_programming_trainer_24_hour.lesson7.foreign_contractor;

import java_programming_trainer_24_hour.lesson7.foreign_contractor.Contractor;
import java_programming_trainer_24_hour.lesson7.foreign_contractor.Employee;
import java_programming_trainer_24_hour.lesson7.foreign_contractor.Payable;

public class TestPayIncreasePolyError {

    public static void main(String[] args) {

        Payable workers[] = new Payable[3];// Person workers[] = new Person[3]
        workers[0] = new Employee("John");
        workers[1] = new Contractor("Mary");
//        workers[2] = new ForeignContractor("Boris");

        for (Payable p : workers) {
            ((Payable)p).increasePay(30);
        }
    }
}
