package com.aoeivux.ioc;


import com.aoeivux.entity.Address;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                Constructor<?> constructor = clazz.getConstructor();
                Object o = constructor.newInstance();
                ioc.put(id, o);
                //给目标赋值
                Iterator<Element> bean = element.elementIterator();
                while (bean.hasNext()) {
                    Element property = bean.next();
                    String name = property.attributeValue("name");
                    String value = property.attributeValue("value");
                    String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

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
        }

    }
    @Override
    public Object getBean(String id) {
        return ioc.get(id);
    }

}
