package day10.generics.servicerepository;

public class ServiceRepositoryDemo {

    public static void main(String[] args) {

        ServiceRepository<Service> sr = new ServiceRepository<>();

        sr.adding(new Service(1));
        sr.adding(new Service(2));
        sr.adding(new Service(3));

        for (Service s : sr.getAllService()) {
            s.showInfo();
        }

    }
}