package day10.mocksandstubs.stub;

import java.util.List;

public interface StorageService {

    public <T> T store(T object);

    public <T> T getById(Long id);

    public <T> List<T> getAll (Class aClass);

}
