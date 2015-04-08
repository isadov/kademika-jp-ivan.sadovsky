package day10.mocksandstubs.stub;

import java.util.List;

public class StorageServiceStub implements StorageService{
    @Override
    public <T> T store(T object) {
        return null;
    }

    @Override
    public <T> T getById(Long id) {
        return null;
    }

    @Override
    public <T> List<T> getAll(Class aClass) {
        return null;
    }
}
