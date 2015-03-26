package day10.generics.servicerepository;

public class Service {

    public int uniqueNumber;

    public Service(int uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(int uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public void showInfo() {
        System.out.println("Testing Service # " + getUniqueNumber());
    }
}
