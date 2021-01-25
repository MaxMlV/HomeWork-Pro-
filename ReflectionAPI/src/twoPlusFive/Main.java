package twoPlusFive;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static int test(Class<?>... classes) {
        try {
            for (Class<?> cls : classes) {
                Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Test.class)) {
                        Annotation[] annotations = method.getAnnotations();
                        for (Annotation a : annotations) {
                            if (a instanceof Test) {
                                Test annotation = (Test) a;
                                return (Integer) method.invoke(cls.newInstance(), annotation.parameterOne(), annotation.parameterTwo());
                            }
                        }
                    }
                }
            }
            } catch(IllegalAccessException e){
                e.printStackTrace();
            } catch(InvocationTargetException e){
                e.printStackTrace();
            } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return -1;
        }

        public static void main (String[]args){
            System.out.println(test(Tester.class));
        }
    }
