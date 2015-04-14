package day10_hw.get_service_application;

public class Launcher {

    public static void main(String[] args) throws NoSuchMethodException {

        ApplicationManager applicationManager = new ApplicationManager();

        applicationManager.addService(new TestClass());
        applicationManager.addService(new TestClass());
        applicationManager.addService(new AnotherClass());

        applicationManager.printServiceList();

        TestClass testClass = (TestClass) applicationManager.getService(TestClass.class);

        System.out.println(testClass.getClass().getSimpleName());

    }


    static class AnotherClass {

    }
}
