package day10.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUsingPrivateConstructor {

    public static void main(String[] args) {
        TestClass testClass;

        Map<String, Object> map = new HashMap<>();

        map.put("name", new String("Alexander"));
        map.put("age", new Integer(50));
        map.put("id", new Long(1));
        map.put("price", new Integer(35));

        testClass = setPrivates(TestClass.class, map);

        System.out.println(testClass.getName());
        System.out.println(testClass.getAge());
        System.out.println(testClass.getId());
        System.out.println(testClass.getPrice());
    }

    public static class TestClass {

        private String name;
        private int age;
        private long id;
        private int price;


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

    public static TestClass setPrivates(Class<? extends TestClass> c, Map<String, Object> map) {
        TestClass aClass = null;

        try {
            aClass = c.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        for (Map.Entry entry : map.entrySet()) { //Set<Map.Entry<K, V>> entrySet();
            Field field = null;

            try {
                field = c.getDeclaredField((String) entry.getKey());
            } catch (NoSuchFieldException e) { //* Signals that the class doesn't have a field of a specified name.
                e.printStackTrace();
            }

            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(aClass, entry.getValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return aClass;
    }

}
