package com.aoeivux.ioc;


import com.aoeivux.entity.Address;

import lombok.val;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private Map<String, Object> ioc = new HashMap<>();
    public ClassPathXmlApplicationContext(String path){
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(path);
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while(iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                //通过反射机制创建对象
                Class<?> clazz = Class.forName(className);
                //无参构造函数获取目标对象
                Constructor<?> object = clazz.getConstructor();
                Object o = object.newInstance();
                ioc.put(id, o);
                //给目标赋值
                Iterator<Element> bean = element.elementIterator();
                while (bean.hasNext()) {
                    Element property = bean.next();
                    String name = property.attributeValue("name");
                    String valueStr = property.attributeValue("value");
                    String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
					// System.out.println(name);
					// System.out.println(value);
					Field field = clazz.getDeclaredField(name);
					Method method = clazz.getDeclaredMethod(methodName, field.getType());
					System.out.println(field.getType().getName());
					//根据成员变量的数据类型将value进行转换
					Object value = null;
					if(field.getType().getName() == "java.lang.Integer") {
						value = Integer.parseInt(valueStr);
					}
					if(field.getType().getName() == "java.lang.String") {
						value = valueStr;
					}
					method.invoke(object, value);
                }
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

    }
    @Override
    public Object getBean(String id) {
        return ioc.get(id);
    }

}
