package com.aoeivux;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // Object student = applicationContext.getBean("student");
        // System.out.println(student);
		Object bean = applicationContext.getBean("substudent");
		System.out.println(bean);
    }
}
