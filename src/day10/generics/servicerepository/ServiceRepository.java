package day10.generics.servicerepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceRepository<T extends Service> {

    private List<T> serviceList;

    public ServiceRepository() {

        serviceList = new ArrayList<>();
    }

    public void adding(T obj) {
        serviceList.add(obj);
    }

    public void removingService(T obj) {
        serviceList.remove(obj);
    }

    public List<T> getAllService() {
        return serviceList;
    }
}

