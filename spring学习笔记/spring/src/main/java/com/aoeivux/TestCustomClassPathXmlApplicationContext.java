package com.aoeivux;

import com.aoeivux.entity.Student;
import com.aoeivux.ioc.ApplicationContext;
import com.aoeivux.ioc.ClassPathXmlApplicationContext;

public class TestCustomClassPathXmlApplicationContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("src/main/resources/spring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
    }
}
