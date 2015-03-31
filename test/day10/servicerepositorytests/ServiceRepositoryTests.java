package day10.servicerepositorytests;

import day10.generics.servicerepository.Service;
import day10.generics.servicerepository.ServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)

public class ServiceRepositoryTests {

    ServiceRepository<Service> serviceRepository;


    @Before
    public void init() {
        serviceRepository = new ServiceRepository<>();

        serviceRepository.adding(new Service());
    }

    @Test
    public void testAddingToRepository() {
        assertTrue(serviceRepository.getAllService().get(0) instanceof Service);
    }
}
