package day10.mocksandstubs.mock;



import day10.mocksandstubs.stub.StorageService;

import java.util.ArrayList;
import java.util.List;

public class StorageServiceMock implements StorageService {

    private List<Object> objects;

    public StorageServiceMock() {
        this.objects = new ArrayList<>();
    }

    @Override
    public <T> T store(T object) {
        objects.add(object);
        return object;
    }

    @Override
    public <T> T getById(Long id) {
        return null;
    }

    @Override
    public <T> List<T> getAll(Class aClass) {
        return (List<T>) new ArrayList<>(objects);
    }

}
