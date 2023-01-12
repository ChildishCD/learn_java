package com.java.intro.oop;

public class Leader extends Person {
    public Leader(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Leader{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //静态内部类
    //public static int classes;
    //和静态变量差不多
    public static class Supervisor {
        private String name;
        public Supervisor(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

