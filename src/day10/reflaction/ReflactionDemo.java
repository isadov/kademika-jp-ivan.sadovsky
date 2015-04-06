package day10.reflaction;

import day10.domainmodel.Fruits.Apple;
import day10.domainmodel.Fruits.Fruit;

import java.util.Arrays;

public class ReflactionDemo {

    public static void main(String[] args) {

        Test test = new Test();
        Class t = test.getClass();

        printClassFields(t);    // this method bring us x and y fields
        printClassMethods(t);   // in our class Test we have printInfo and this method show us the name of method

        SecondTest secondTest = new SecondTest();
        printClassInfo(secondTest.getClass());

        ThirdTest thirdTest = new ThirdTest();
        printClassInfo(thirdTest.getClass());   // Object is Test.class cause
                                                // we have extends Test.class in ThirdTest. Same to Line 17 - 18

        Fruit a = new Apple();
        printClassInfo(a.getClass());   // Object is a Fruit.class (we get parents of our classes)
                                        // and in console we get that Fruit its our Object
    }

        static class Test {

            int x;
            int y;

            public void printInfo() {
                System.out.println("Location and full name of class: " + Test.class.getName());
            }
        }

        static class SecondTest extends Test {

        }

        static class ThirdTest extends Test {

        }

    public static void printClassInfo(Class c) {
        System.out.println("Name Of Class: " + c.getSimpleName());
        do {
            System.out.print(c.getSuperclass().getSimpleName() + " ");
            c = c.getSuperclass();
        }
        while (!(c.getSuperclass() == null));
            System.out.println();
    }

    public static void printClassFields(Class c) {
        System.out.println("Name Of Class: " + c.getSimpleName());
        System.out.println("Name Of Fields in Class: " + Arrays.toString(c.getDeclaredFields()));
        System.out.println();
    }

    public static void printClassMethods(Class c) {
        System.out.println("Name Of Class: " + c.getSimpleName());
        System.out.println("Name Of Methods: " + Arrays.toString(c.getDeclaredMethods()));
        System.out.println();
        }
    }

