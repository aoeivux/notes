import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    @org.junit.Test
    public void test1(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object student = applicationContext.getBean("student");
        System.out.println(student);

    }

    @org.junit.Test
    public void test2() {
        try {
            Class<?> address = Class.forName("com.aoeivux.entity.Address");
            Constructor<?> constructor = address.getConstructor(Integer.class, String.class);
            Object o = constructor.newInstance(1, "贵州");
            System.out.println(o);
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

	@org.junit.Test
	public void test3() {
		try {
			Class<?> clazz = Class.forName("com.aoeivux.entity.Address");
			clazz.getDeclaredMethod();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
