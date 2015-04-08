package day10.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationManager {

    public static <T> T getServiceClass(Class<T> aClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Annotation a : aClass.getAnnotations()) {
            if (a.annotationType() == Service.class) {
                System.out.print(aClass.getSimpleName() + " --- ");
                System.out.println("@ServiceAnnotation was Used !");

                Method[] methods = aClass.getMethods();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(InitService.class)) {
                        T t = aClass.newInstance();
                        m.invoke(t);
                        return t;
                    }

                }

            }

        }
        return null;
    }

}
