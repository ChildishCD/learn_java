package com.java.intro.reflect;

import com.java.intro.oop.Person;
import com.java.intro.oop.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args)  throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException{
        //use <Class> get the description of a specific class
        //  get the <Class>,<Constructor>,<Flied>,<Method>, and use these get more
        //  create new instance
        //  compare class use "? == <Class>" can skip the child classes than "Instanceof"
        reflectNormal();
        reflectGenerics();
    }

    private static void reflectGenerics() {

    }

    private static void reflectNormal() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException{
        //==> get <Class> at first
        Class<Person> personClass = Person.class;

        //-->get <Class>
        //Class only has one instance in JVM, so the 3 Classes below are the same
        //1.use class name
        Class<Person> personClass1 = Person.class;
        //2.use class instance
        Person person = new Person();
        Class personClass2 = person.getClass();
        //3.use full name
        Class<?> personClass3 = Class.forName("com.java.intro.oop.Person");
        //***use class create instance [without parameter]
        Person person1 = personClass1.newInstance();
        Object o = personClass2.newInstance();
        Object o1 = personClass3.newInstance();

        //-->get <Constructor> [with parameter]
        Constructor<Person> constructor = personClass.getConstructor(String.class, Integer.class);
        Person person2 = constructor.newInstance("Jack", 23);//use constructor(have parameter) create instance
        //-->get <Constructor> of private
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);//set private accessible
        //***use private constructor create instance
        Person person3 = declaredConstructor.newInstance("Mark");

        //-->get <Fields>
        Field[] fields = personClass.getFields();
        //***output field type
        System.out.println(Arrays.toString(fields));//can only get public fields
        for (Field f : fields) {
            System.out.println(f.getName() + ":" + f.getType() + ":" + f.get(person2));//get public fields from instance
        }
        //-->get <Fields> of all (include private)
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);//set private fields accessible
            System.out.println(f.getName() + ":" + f.getType() + ":" + f.get(person2));
        }

        //-->get <Methods>
        Method[] methods = personClass.getMethods();
        //-->get <Methods> of all (include private)
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            m.setAccessible(true);
            System.out.println(m.getName());
        }
        //-->get specific Method
        Method getDied = personClass.getDeclaredMethod("getDied", String.class, Double.class);
        getDied.setAccessible(true);
        //-->invoke method using Method instance
        double years = (Double) getDied.invoke(person2, "AngusStreet", 0.8);
        System.out.println(years);

        //-->get <Super>
        Class<? super Person> superclass = personClass.getSuperclass();

        //-->get <Annotation> use Class/Method/Field
        //***can not get annotation that retention is in source include lombook and in class
        Annotation[] annotations = personClass.getDeclaredAnnotations();
        for(Annotation a : annotations){
            System.out.println(a.annotationType().getSimpleName());
        }
        //***check and return is Annotation(runtime) if Class use it OR null
        System.out.println(personClass.getAnnotation(com.java.intro.annotation.Test1.class).value());
        //-->get ....
    }
}
