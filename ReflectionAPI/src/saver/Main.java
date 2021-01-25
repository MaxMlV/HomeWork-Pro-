package saver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        SaveText saveText = new SaveText();
        Class<?> cls = saveText.getClass();

        if (!(cls.isAnnotationPresent(SaveTo.class))) {
            System.out.println("Error!");
            return;
        } else {
            SaveTo saveTo = cls.getAnnotation(SaveTo.class);
            String path = saveTo.path();

            Method[] methods = cls.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    method.invoke(saveText, path);
                    break;
                }
            }
        }
        System.out.println("Done!");
    }
}
