package day10.annotation;

import java.lang.reflect.InvocationTargetException;

public class Launcher {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        TestClass testClass = ApplicationManager.getServiceClass(TestClass.class);

//        testClass.defaultMethod();
//        testClass.printInfo();



    }
}
