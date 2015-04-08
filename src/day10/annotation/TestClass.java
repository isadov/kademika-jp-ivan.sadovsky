package day10.annotation;

@Service
public class TestClass {

    public TestClass() {

    }

    @Service
    public void defaultMethod() {
        System.out.println("Using Annotation @Service. Default Method");
    }

    @InitService
    public void printInfo() throws NoSuchMethodException {
        System.out.print(this.getClass().getMethod("printInfo").getName() + " --- ");
        System.out.println("@InitService was Used !");
    }
}
