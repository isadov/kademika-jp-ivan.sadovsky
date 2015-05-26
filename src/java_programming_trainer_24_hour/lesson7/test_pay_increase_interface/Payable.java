package java_programming_trainer_24_hour.lesson7.test_pay_increase_interface;

public interface Payable {

    final int INCREASE_CAP = 20;
    boolean increasePay(int percent);
}
