package webapp;

import webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException{
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Method[] method = r.getClass().getDeclaredMethods();
        for (Method m : method) {
            String mname = m.getName();
            if (mname.startsWith("toString"))
                System.out.println(m);
        }
        System.out.println(r);
    }
}