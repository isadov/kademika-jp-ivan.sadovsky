package day10_hw.get_service_application;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {

    private List<Object> objectList;

    ApplicationManager() {
        objectList = new ArrayList<>();
    }


    public void addService(Object object) throws NoSuchMethodException {

        if (object.getClass().isAnnotationPresent(Service.class)) {

            try {
                Method m = TestClass.class.getMethod("init");

                if (m != null) {
                    if (m.isAnnotationPresent(InitService.class)) {
                        ((TestClass) object).init();
                    }
                }

                objectList.add(object);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Object> getObjectList() {
        return new ArrayList<>(objectList);
    }

    public Object getService(Class aClass) {
        Object returnObject = null;

        for (Object object : objectList) {
            if (object.getClass().equals(aClass)) {
                returnObject = object;
            }
        }

        return returnObject;
    }

    public void printServiceList() {
        for (Object o : objectList) {
            System.out.print(o.getClass().getSimpleName() + ": ");
            TestClass testClass = (TestClass) o;
            System.out.println(testClass.getStatus() + " !");

        }
    }
}
