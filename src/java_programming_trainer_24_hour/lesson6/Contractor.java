package java_programming_trainer_24_hour.lesson6;

public class Contractor extends Person implements Payable {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean increasePay(int percent) {
        if(percent > INCREASE_CAP) {
            System.out.println("Sorry, can't increase hourly rate by more than 20%. "  + getName());
        } else {
            System.out.println("Increasing salary by " + percent + "%. " + getName());
        }
        return true;
    }
}
