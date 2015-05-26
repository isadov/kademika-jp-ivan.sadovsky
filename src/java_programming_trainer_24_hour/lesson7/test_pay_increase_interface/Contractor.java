package java_programming_trainer_24_hour.lesson7.test_pay_increase_interface;

public class Contractor implements Payable {

    private String name;

    public Contractor(String name) {
        this.name = name;
    }

    @Override
    public boolean increasePay(int percent) {
        if (percent < Payable.INCREASE_CAP) {
            System.out.println("Increasing hourly rate by " + percent + "%. ");
            return true;
        } else {
            System.out.println("Sorry can't increase hourly rate by more than " + Payable.INCREASE_CAP + "%: " + name);
            return false;
        }

    }
}
