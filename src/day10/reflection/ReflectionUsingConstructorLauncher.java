package day10.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUsingConstructorLauncher {

    public static void main(String[] args) {

        TestClass testClass = null;

        List<Object> objects = new ArrayList<>();


        objects.add(111); // this.age = age;

        objects.add(222); // this.id = id;

        objects.add("Default"); // this.name = name;

        objects.add(444); // this.price = price;


        testClass = initClass(TestClass.class, objects);

        System.out.println(testClass.getName());
        System.out.println(testClass.getId());
        System.out.println(testClass.getAge());
        System.out.println(testClass.getPrice());

    }

    public static class TestClass {

        public int age;
        public long id;
        public String name;
        public int price;

        public TestClass() {
            age = 1;
            id = 2;
            name = "TestUsingEmptyContructor";
            price = 3;
        }

        public TestClass(Integer price) {
            age = 10;
            id = 123;
            name = "TestUsingPriceConstructor";
            this.price = price;
        }

        public TestClass(Integer age, Integer id, String name, Integer price) {
            this.age = age;
            this.id = id;
            this.name = name + " -- TestWithFullConstructor ";
            this.price = price;
        }

        public int getAge() {
            return age;
        }

        public int getPrice() {
            return price;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static TestClass initClass(Class<? extends TestClass> c, List<Object> list) {

        TestClass testClass = null;
        Constructor constructor = null;

        Class[] classes = new Class[list.size()];
        int i = 0;

        for (Object object : list) {
            classes[i] = object.getClass();
            i++;
        }

        try {
            constructor = c.getDeclaredConstructor(classes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (constructor != null) {
            try {
                testClass = (TestClass) constructor.newInstance(list.toArray());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return testClass;
    }
}

