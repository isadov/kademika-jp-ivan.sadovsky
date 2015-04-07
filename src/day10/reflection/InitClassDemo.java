package day10.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InitClassDemo {

    public static void main(String[] args) {
        TestClass testClass;

        Map<String, Object> map = new HashMap<>();
        map.put("name", new String("Jackie")); // if name write like a Name(!) NoSuchFieldException
        map.put("age", new Integer(25));        // same to line 13
        map.put("id", new Long(123));           // same to line 13
        map.put("price", new Integer(100));     // same to line 13

        testClass = initClass(TestClass.class, map);

        System.out.println("Name: " + testClass.getName());
        System.out.println("Age: " + testClass.getAge());
        System.out.println("Id: " + testClass.getId());
        System.out.println("Price: " + testClass.getPrice());

    }

    public static class TestClass {
        public String name;
        public int age;
        public long id;
        public int price;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public long getId() {
            return id;
        }

        public int getPrice() {
            return price;
        }

    }

    public static TestClass initClass(Class<? extends TestClass> c, Map<String, Object> map) {
        TestClass obj = null;

        try {
            obj = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Map.Entry entry : map.entrySet()) {
            Field f = null;

            try {
                f = c.getField((String) entry.getKey());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (f != null) {
                try {
                    f.set(obj, entry.getValue()); // set(Object obj, Object value)
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }
}
