package com.aoeivux.javaweb.多线程.使用线程池创建多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



// 实现Runnable的方式创建多线程类
class NumberThread implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + "=>" + i);
			}
		}
	}

}
// 继承Thread的方式创建多线程类
class NumberThread1 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + "=>" + i);
			}
		}
	}
}


// 实现Callable接口的方式创建多线程类
class NumberThread2 implements Callable<Integer>{
	private int sum = 0;

	@Override
	public Integer call() throws Exception {
		for (int i = 0; i <= 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + "=>" + i); 
				sum += i;
			}
		}
		return sum;
	}
}



public class ThreadPool {
	public static void main(String[] args) {
		//创建并指定线程数量的线程池
		ExecutorService service = Executors.newFixedThreadPool(10);
		//ThreadPoolExecutor是ExecutorService接口的实现类
		ThreadPoolExecutor service1 = (ThreadPoolExecutor)service; //多态的运用，向下转型
		service1.setCorePoolSize(16);

		//执行指定的线程操作,需要提供实现Runnable接口或者Callable接口的实现类对象
		service.execute(new NumberThread()); // 线程池执行实现Runnable接口的方式
		service.execute(new NumberThread1()); // 线程池执行继承Thread类的方式 
		service.submit(new NumberThread2());//线程池执行实现了Callable接口实现类的方法

		//关闭连接池
		service.shutdown();
	}
}
